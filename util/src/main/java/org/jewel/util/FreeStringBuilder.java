package org.jewel.util;

import java.io.Serializable;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

import org.jewel.JewelException;
import org.jewel.util.collection.CollectionUtil;
import org.jewel.util.date.DateUtil;

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
		String s = StringUtil.toS(d, minFraction, maxFraction, groupingUsed);
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
		String s = DateUtil.format(d, format);
		sb.append(s);
		return this;
	}

	public FreeStringBuilder append(Class<?> c) {
		String s = StringUtil.toS(c);
		sb.append(s);
		return this;
	}

	public FreeStringBuilder append(Type type) {
		if (type == null) {
			return this;
		}
		if (type instanceof Class<?>) {
			return append((Class<?>) type);
		}
		if (type instanceof ParameterizedType) {
			return append((ParameterizedType) type);
		}
		if (type instanceof GenericArrayType) {
			return append((GenericArrayType) type);
		}
		if (type instanceof TypeVariable<?>) {
			return append((TypeVariable<?>) type);
		}
		if (type instanceof WildcardType) {
			return append((WildcardType) type);
		}
		// shall never happen
		String message = String.format("Jewel doesn't understand Type %s.",
				type);
		throw new JewelException(message);
	}

	public FreeStringBuilder append(ParameterizedType type) {
		if (type == null) {
			return this;
		}
		append(type.getRawType());
		append(type.getActualTypeArguments(), "<", ", ", ">");
		return this;
	}

	public FreeStringBuilder append(GenericArrayType type) {
		if (type == null) {
			return this;
		}
		append(type.getGenericComponentType());
		sb.append("[]");
		return this;
	}

	public FreeStringBuilder append(TypeVariable<?> type) {
		if (type == null) {
			return this;
		}
		sb.append(type.getName());
		return this;
	}

	public FreeStringBuilder append(WildcardType type) {
		if (type == null) {
			return this;
		}
		// the upper bounds and lower bounds are omitted.
		sb.append('?');
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
		if (isEmpty()) {
			return this;
		}
		char ch = charAt(0);
		if (Character.isUpperCase(ch)) {
			return this;
		}
		char upperCase = Character.toUpperCase(ch);
		setCharAt(0, upperCase);
		return this;
	}

	public FreeStringBuilder firstCharToLowerCase() {
		if (isEmpty()) {
			return this;
		}
		char ch = charAt(0);
		if (Character.isLowerCase(ch)) {
			return this;
		}
		char lowerCase = Character.toLowerCase(ch);
		setCharAt(0, lowerCase);
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
