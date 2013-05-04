package org.jade.util;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Date;
import java.util.Deque;
import java.util.Iterator;
import java.util.Map;

import org.jade.util.collection.CollectionUtil;
import org.jade.util.date.DateUtil;
import org.jade.util.reflect.ClassUtil;

/**
 * Utility class to freely build a String.
 * 
 * @author slhynju
 * @NotThreadSafe
 */
public final class FreeStringBuilder implements Serializable, Appendable,
		CharSequence {

	private static final long serialVersionUID = 5622464332073959812L;

	private final StringBuilder sb;

	public static String toS(Map<?, ?> map, String keyValueSeparator,
			String entrySeparator) {
		return new FreeStringBuilder().append(map, "", keyValueSeparator,
				entrySeparator, "").toS();
	}

	public static String toS(Collection<?> objects, String separator) {
		return new FreeStringBuilder().append(objects, "", separator, "").toS();
	}

	public static String toS(Object[] objects, String separator) {
		return new FreeStringBuilder().append(objects, "", separator, "").toS();
	}

	public FreeStringBuilder() {
		sb = new StringBuilder();
	}

	public FreeStringBuilder(int capacity) {
		sb = new StringBuilder(capacity);
	}

	public FreeStringBuilder(String s) {
		sb = new StringBuilder(s);
	}

	public FreeStringBuilder append(int i) {
		sb.append(i);
		return this;
	}

	public FreeStringBuilder append(long l) {
		sb.append(l);
		return this;
	}

	public FreeStringBuilder append(double d) {
		sb.append(d);
		return this;
	}

	public FreeStringBuilder append(double d, int minFraction, int maxFraction,
			boolean groupingUsed) {
		String s = NumberUtil.toS(d, minFraction, maxFraction, groupingUsed);
		sb.append(s);
		return this;
	}

	public FreeStringBuilder append(boolean b) {
		sb.append(b);
		return this;
	}

	@Override
	public FreeStringBuilder append(char c) {
		sb.append(c);
		return this;
	}

	public FreeStringBuilder appendQuoted(char c) {
		sb.append('\'').append(c).append('\'');
		return this;
	}

	public FreeStringBuilder append(String s) {
		sb.append(s);
		return this;
	}

	public FreeStringBuilder appendQuoted(String s) {
		sb.append('"').append(s).append('"');
		return this;
	}

	@Override
	public FreeStringBuilder append(CharSequence chars) {
		sb.append(chars);
		return this;
	}

	public FreeStringBuilder appendQuoted(CharSequence chars) {
		sb.append('"').append(chars).append('"');
		return this;
	}

	@Override
	public FreeStringBuilder append(CharSequence chars, int start, int end) {
		sb.append(chars, start, end);
		return this;
	}

	public FreeStringBuilder append(Date d, String format) {
		String s = DateUtil.toS(d, format);
		sb.append(s);
		return this;
	}

	public FreeStringBuilder append(Class<?> c) {
		String s = ClassUtil.toS(c);
		sb.append(s);
		return this;
	}

	public FreeStringBuilder append(Type type) {
		String s = ClassUtil.toS(type);
		sb.append(s);
		return this;
	}

	public FreeStringBuilder append(Collection<?> objects) {
		return append(objects, "[", ", ", "]");
	}

	public FreeStringBuilder appendWithoutBoundary(Collection<?> objects) {
		return append(objects, "", ", ", "");
	}

	public FreeStringBuilder append(Collection<?> objects, String leftBoundary,
			String separator, String rightBoundary) {
		sb.append(leftBoundary);
		if (CollectionUtil.notEmpty(objects)) {
			for (Object o : objects) {
				append(o);
				sb.append(separator);
			}
			deleteEnd(separator.length());
		}
		sb.append(rightBoundary);
		return this;
	}

	public FreeStringBuilder appendStack(Deque<?> stack, String leftBoundary,
			String separator, String rightBoundary) {
		sb.append(leftBoundary);
		if (CollectionUtil.notEmpty(stack)) {
			Iterator<?> it = stack.descendingIterator();
			while (it.hasNext()) {
				append(it.next());
				sb.append(separator);
			}
			deleteEnd(separator.length());
		}
		sb.append(rightBoundary);
		return this;
	}

	public FreeStringBuilder append(Object[] objects) {
		return append(objects, "[", ", ", "]");
	}

	public FreeStringBuilder appendWithoutBoundary(Object[] objects) {
		return append(objects, "", ", ", "");
	}

	public FreeStringBuilder append(Object[] objects, String leftBoundary,
			String separator, String rightBoundary) {
		sb.append(leftBoundary);
		if (CollectionUtil.notEmpty(objects)) {
			for (Object o : objects) {
				append(o);
				sb.append(separator);
			}
			deleteEnd(separator.length());
		}
		sb.append(rightBoundary);
		return this;
	}

	public FreeStringBuilder append(Map<?, ?> map) {
		return append(map, "{", ": ", ", ", "}");
	}

	public FreeStringBuilder appendWithoutBoundary(Map<?, ?> map) {
		return append(map, "", ": ", ", ", "");
	}

	public FreeStringBuilder append(Map<?, ?> map, String leftBoundary,
			String keyValueSeparator, String entrySeparator,
			String rightBoundary) {
		sb.append(leftBoundary);
		if (CollectionUtil.notEmpty(map)) {
			for (Map.Entry<?, ?> entry : map.entrySet()) {
				Object key = entry.getKey();
				Object value = entry.getValue();
				append(key);
				sb.append(keyValueSeparator);
				append(value);
				sb.append(entrySeparator);
			}
			deleteEnd(entrySeparator.length());
		}
		sb.append(rightBoundary);
		return this;
	}

	public FreeStringBuilder append(Object o) {
		if (o == null) {
			return this;
		}
		if (o instanceof String) {
			return append((String) o);
		}
		if (o instanceof Collection<?>) {
			return append((Collection<?>) o);
		}
		if (o instanceof Map<?, ?>) {
			return append((Map<?, ?>) o);
		}
		if (o instanceof Date) {
			return append((Date) o, DateUtil.SIMPLE_FORMAT);
		}
		if (o instanceof Object[]) {
			return append((Object[]) o);
		}
		if (o instanceof Class<?>) {
			return append((Class<?>) o);
		}
		if (o instanceof Type) {
			return append((Type) o);
		}
		if (o instanceof CharSequence) {
			return append((CharSequence) o);
		}
		sb.append(o);
		return this;
	}

	public FreeStringBuilder indent(int indentation) {
		for (int I = 0; I < indentation; I++) {
			sb.append('\t');
		}
		return this;
	}

	public boolean endsWith(String str) {
		return StringUtil.endsWith(sb, str);
	}

	public FreeStringBuilder deleteEnd(int endLength) {
		StringUtil.deleteEnd(sb, endLength);
		return this;
	}

	public FreeStringBuilder deleteEndIf(String str) {
		if (endsWith(str)) {
			deleteEnd(str.length());
		}
		return this;
	}

	public FreeStringBuilder firstCharToUpperCase() {
		StringUtil.firstCharToUpperCase(sb);
		return this;
	}

	public FreeStringBuilder firstCharToLowerCase() {
		StringUtil.firstCharToLowerCase(sb);
		return this;
	}

	public FreeStringBuilder clear() {
		sb.setLength(0);
		return this;
	}

	public boolean isEmpty() {
		return length() == 0;
	}

	@Override
	public int length() {
		return sb.length();
	}

	@Override
	public char charAt(int index) {
		return sb.charAt(index);
	}

	public FreeStringBuilder setCharAt(int index, char ch) {
		sb.setCharAt(index, ch);
		return this;
	}

	@Override
	public CharSequence subSequence(int start, int end) {
		return sb.subSequence(start, end);
	}

	public String toS() {
		return sb.toString();
	}

	@Override
	public String toString() {
		return toS();
	}

}
