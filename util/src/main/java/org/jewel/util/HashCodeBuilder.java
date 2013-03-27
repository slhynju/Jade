package org.jewel.util;

/**
 * Utility class to support hashCode() method.
 * 
 * @author slhynju
 * @NotThreadSafe
 */
public final class HashCodeBuilder {

	private int value = 23;

	private static final int multiplier = 17;

	public HashCodeBuilder append(Object obj) {
		value *= multiplier;
		if (obj != null) {
			value += obj.hashCode();
		}
		return this;
	}

	public HashCodeBuilder append(int i) {
		value *= multiplier;
		value += i;
		return this;
	}

	public HashCodeBuilder append(boolean b) {
		value *= multiplier;
		if (b) {
			value++;
		}
		return this;
	}

	public HashCodeBuilder append(long l) {
		value *= multiplier;
		value += l ^ (l >>> 32);
		return this;
	}

	public HashCodeBuilder append(double d) {
		append(Double.doubleToLongBits(d));
		return this;
	}

	public HashCodeBuilder append(char c) {
		value *= multiplier;
		value += c;
		return this;
	}

	public int toValue() {
		return value;
	}
}
