package org.jewel.util;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

import org.jewel.JewelException;
import org.jewel.util.date.DateUtil;

/**
 * Utility class to support toString() method in POJO classes.
 * 
 * @author slhynju
 * @NotThreadSafe
 */
public final class BeanStringBuilder implements Serializable, CharSequence {

	private static final long serialVersionUID = -7420097406605565120L;

	private final FreeStringBuilder sb;

	private State state;

	/**
	 * Construct the BeanStringBuilder with POJO class.
	 * 
	 * @param beanClass
	 *            POJO class. It shall not be array or anonymous class.
	 */
	public BeanStringBuilder(Class<?> beanClass) {
		sb = new FreeStringBuilder();
		sb.append(beanClass.getName());
		sb.append('{');
		state = State.STARTED;
	}

	public BeanStringBuilder append(String name, int value) {
		appendPropertyName(name);
		sb.append(value);
		return this;
	}

	public BeanStringBuilder append(String name, long value) {
		appendPropertyName(name);
		sb.append(value);
		return this;
	}

	public BeanStringBuilder append(String name, double value) {
		appendPropertyName(name);
		sb.append(value);
		return this;
	}

	public BeanStringBuilder append(String name, double value, int minFraction,
			int maxFraction, boolean groupingUsed) {
		appendPropertyName(name);
		sb.append(value, minFraction, maxFraction, groupingUsed);
		return this;
	}

	public BeanStringBuilder append(String name, Double value, int minFraction,
			int maxFraction, boolean groupingUsed) {
		if (value == null) {
			return appendNullProperty(name);
		}
		return append(name, value.doubleValue(), minFraction, maxFraction,
				groupingUsed);
	}

	public BeanStringBuilder append(String name, boolean value) {
		appendPropertyName(name);
		sb.append(value);
		return this;
	}

	public BeanStringBuilder append(String name, char value) {
		appendPropertyName(name);
		sb.appendQuoted(value);
		return this;
	}

	public BeanStringBuilder append(String name, Character value) {
		if (value == null) {
			return appendNullProperty(name);
		}
		return append(name, value.charValue());
	}

	public BeanStringBuilder appendNullProperty(String name) {
		appendPropertyName(name);
		sb.append("null");
		return this;
	}

	public BeanStringBuilder append(String name, String value) {
		if (value == null) {
			return appendNullProperty(name);
		}
		appendPropertyName(name);
		sb.appendQuoted(value);
		return this;
	}

	public BeanStringBuilder append(String name, CharSequence value) {
		if (value == null) {
			return appendNullProperty(name);
		}
		appendPropertyName(name);
		sb.appendQuoted(value);
		return this;
	}

	public BeanStringBuilder append(String name, Date value) {
		return append(name, value, DateUtil.SIMPLE_FORMAT);
	}

	public BeanStringBuilder append(String name, Date value, String format) {
		if (value == null) {
			return appendNullProperty(name);
		}
		appendPropertyName(name);
		sb.append(value, format);
		return this;
	}

	public BeanStringBuilder append(String name, Class<?> value) {
		if (value == null) {
			return appendNullProperty(name);
		}
		appendPropertyName(name);
		sb.append(value);
		return this;
	}

	public BeanStringBuilder append(String name, Collection<?> c) {
		appendPropertyName(name);
		sb.append(c);
		return this;
	}

	public BeanStringBuilder append(String name, Object[] a) {
		appendPropertyName(name);
		sb.append(a);
		return this;
	}

	public BeanStringBuilder append(String name, Map<?, ?> m) {
		appendPropertyName(name);
		sb.append(m);
		return this;
	}

	public BeanStringBuilder append(String name, Object o) {
		if (o == null) {
			return appendNullProperty(name);
		}
		if (o instanceof String) {
			return append(name, (String) o);
		}
		if (o instanceof Collection<?>) {
			return append(name, (Collection<?>) o);
		}
		if (o instanceof Map<?, ?>) {
			return append(name, (Map<?, ?>) o);
		}
		if (o instanceof Date) {
			return append(name, (Date) o);
		}
		if (o instanceof Object[]) {
			return append(name, (Object[]) o);
		}
		if (o instanceof Class<?>) {
			return append(name, (Class<?>) o);
		}
		if (o instanceof Character) {
			return append(name, (Character) o);
		}
		if (o instanceof CharSequence) {
			return append(name, (CharSequence) o);
		}
		appendPropertyName(name);
		sb.append(o);
		return this;
	}

	public String toS() {
		if (state != State.CLOSED) {
			sb.append('}');
			state = State.CLOSED;
		}
		return sb.toS();
	}

	@Override
	public String toString() {
		return toS();
	}

	@Override
	public int length() {
		return sb.length();
	}

	@Override
	public char charAt(int index) {
		return sb.charAt(index);
	}

	@Override
	public CharSequence subSequence(int start, int end) {
		return sb.subSequence(start, end);
	}

	private void appendPropertyName(String name) {
		switch (state) {
		case STARTED:
			state = State.PROPERTY_APPENDED;
			break;
		case PROPERTY_APPENDED:
			sb.append(", ");
			break;
		case CLOSED:
			throw new JewelException("BeanStringBuilder is already closed.");
		}
		sb.append(name).append(": ");
	}

	private static enum State {
		STARTED, PROPERTY_APPENDED, CLOSED
	}

}
