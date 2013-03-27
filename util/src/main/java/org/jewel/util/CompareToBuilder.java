package org.jewel.util;

import java.lang.reflect.Method;


/**
 * Utility class to support compareTo() method and Comparators.
 * 
 * @author slhynju
 * @NotThreadSafe
 */
public final class CompareToBuilder {

	private int value = 0;

	public <T> CompareToBuilder append(Comparable<T> o1, T o2) {
		if (bypass(o1, o2)) {
			return this;
		}
		value = o1.compareTo(o2);
		return this;
	}

	public <T> CompareToBuilder append(Comparable<T>[] a1, T[] a2) {
		if (bypass(a1, a2)) {
			return this;
		}
		int length = Math.min(a1.length, a2.length);
		for (int I = 0; I < length; I++) {
			append(a1[I], a2[I]);
			if (value != 0) {
				return this;
			}
		}
		compareInt(a1.length, a2.length);
		return this;
	}

	public CompareToBuilder append(int i1, int i2) {
		if (value != 0) {
			return this;
		}
		compareInt(i1, i2);
		return this;
	}

	public CompareToBuilder append(int[] a1, int[] a2) {
		if (bypass(a1, a2)) {
			return this;
		}
		int length = Math.min(a1.length, a2.length);
		for (int I = 0; I < length; I++) {
			compareInt(a1[I], a2[I]);
			if (value != 0) {
				return this;
			}
		}
		compareInt(a1.length, a2.length);
		return this;
	}

	public CompareToBuilder append(long l1, long l2) {
		if (value != 0) {
			return this;
		}
		compareLong(l1, l2);
		return this;
	}

	public CompareToBuilder append(long[] a1, long[] a2) {
		if (bypass(a1, a2)) {
			return this;
		}
		int length = Math.min(a1.length, a2.length);
		for (int I = 0; I < length; I++) {
			compareLong(a1[I], a2[I]);
			if (value != 0) {
				return this;
			}
		}
		compareInt(a1.length, a2.length);
		return this;
	}

	public CompareToBuilder append(double d1, double d2) {
		if (value != 0) {
			return this;
		}
		compareDouble(d1, d2);
		return this;
	}

	public CompareToBuilder append(double[] a1, double[] a2) {
		if (bypass(a1, a2)) {
			return this;
		}
		int length = Math.min(a1.length, a2.length);
		for (int I = 0; I < length; I++) {
			compareDouble(a1[I], a2[I]);
			if (value != 0) {
				return this;
			}
		}
		compareInt(a1.length, a2.length);
		return this;
	}

	public CompareToBuilder append(boolean b1, boolean b2) {
		if (value != 0) {
			return this;
		}
		compareBoolean(b1, b2);
		return this;
	}

	public CompareToBuilder append(boolean[] a1, boolean[] a2) {
		if (bypass(a1, a2)) {
			return this;
		}
		int length = Math.min(a1.length, a2.length);
		for (int I = 0; I < length; I++) {
			compareBoolean(a1[I], a2[I]);
			if (value != 0) {
				return this;
			}
		}
		compareInt(a1.length, a2.length);
		return this;
	}

	public CompareToBuilder append(char c1, char c2) {
		if (value != 0) {
			return this;
		}
		compareChar(c1, c2);
		return this;
	}

	public CompareToBuilder append(Class<?> c1, Class<?> c2) {
		if (bypass(c1, c2)) {
			return this;
		}
		compareClass(c1, c2);
		return this;
	}

	public CompareToBuilder append(Class<?>[] a1, Class<?>[] a2) {
		if (bypass(a1, a2)) {
			return this;
		}
		int length = Math.min(a1.length, a2.length);
		for (int I = 0; I < length; I++) {
			compareClass(a1[I], a2[I]);
			if (value != 0) {
				return this;
			}
		}
		compareInt(a1.length, a2.length);
		return this;
	}

	public CompareToBuilder append(Method m1, Method m2) {
		if (bypass(m1, m2)) {
			return this;
		}
		compareMethod(m1, m2);
		return this;
	}

	public CompareToBuilder append(Method[] a1, Method[] a2) {
		if (bypass(a1, a2)) {
			return this;
		}
		int length = Math.min(a1.length, a2.length);
		for (int I = 0; I < length; I++) {
			compareMethod(a1[I], a2[I]);
			if (value != 0) {
				return this;
			}
		}
		compareInt(a1.length, a2.length);
		return this;
	}

	public int toValue() {
		return value;
	}

	private boolean bypass(Object o1, Object o2) {
		if (value != 0) {
			return true;
		}
		if (o1 == o2) {
			return true;
		}
		if (o1 == null) {
			value = -1;
			return true;
		}
		if (o2 == null) {
			value = 1;
			return true;
		}
		return false;
	}

	private void compareInt(int i1, int i2) {
		if (i1 < i2) {
			value = -1;
		} else if (i1 > i2) {
			value = 1;
		}
	}

	private void compareLong(long l1, long l2) {
		if (l1 < l2) {
			value = -1;
		} else if (l1 > l2) {
			value = 1;
		}
	}

	private void compareDouble(double d1, double d2) {
		if (d1 < d2) {
			value = -1;
		} else if (d1 > d2) {
			value = 1;
		}
	}

	private void compareBoolean(boolean b1, boolean b2) {
		if (b1 != b2) {
			if (b1) {
				value = 1;
			} else {
				value = -1;
			}
		}
	}

	private void compareChar(char c1, char c2) {
		if (c1 < c2) {
			value = -1;
		} else if (c2 > c2) {
			value = 1;
		}
	}

	private void compareClass(Class<?> c1, Class<?> c2) {
		String name1 = StringUtil.toS(c1);
		String name2 = StringUtil.toS(c2);
		append(name1, name2);
	}

	private void compareMethod(Method m1, Method m2) {
		append(m1.getName(), m2.getName());
		if (value != 0) {
			return;
		}
		append(m1.getParameterTypes(), m2.getParameterTypes());
	}
}
