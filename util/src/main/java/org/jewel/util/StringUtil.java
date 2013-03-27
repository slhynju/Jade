package org.jewel.util;

import java.io.UnsupportedEncodingException;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

import org.jewel.JewelException;

/**
 * Various String utility methods.
 * 
 * @author slhynju
 * @ThreadSafe
 */
public final class StringUtil {

	/**
	 * Trim the String. Specially, null trimmed to empty String.
	 * 
	 * @param s
	 *            String to trim.
	 * @return String trimmed String.
	 */
	public static String trim(String s) {
		if (isEmpty(s)) {
			return "";
		}
		return s.trim();
	}

	public static boolean isEmpty(String s) {
		return s == null || s.isEmpty();
	}

	public static boolean notEmpty(String s) {
		return !isEmpty(s);
	}

	public static boolean isYes(String s) {
		return "y".equalsIgnoreCase(s) || "yes".equalsIgnoreCase(s)
				|| "t".equalsIgnoreCase(s) || "true".equalsIgnoreCase(s);
	}

	public static boolean isNo(String s) {
		return "n".equalsIgnoreCase(s) || "no".equalsIgnoreCase(s)
				|| "f".equalsIgnoreCase(s) || "false".equalsIgnoreCase(s);
	}

	public static boolean startWith(CharSequence s, String str) {
		if (str.length() > s.length()) {
			return false;
		}
		for (int I = 0; I < str.length(); I++) {
			char a = s.charAt(I);
			char b = str.charAt(I);
			if (a != b) {
				return false;
			}
		}
		return true;
	}

	public static String start(String s, int startLength) {
		if (startLength >= s.length()) {
			return s;
		}
		return s.substring(0, startLength);
	}

	public static void deleteStart(StringBuilder sb, int startLength) {
		if (startLength >= sb.length()) {
			sb.setLength(0);
			return;
		}
		sb.delete(0, startLength);
	}

	public static String deleteStart(String s, int startLength) {
		if (startLength >= s.length()) {
			return "";
		}
		return s.substring(startLength);
	}

	public static boolean endsWith(CharSequence sb, String str) {
		if (str.length() > sb.length()) {
			return false;
		}
		for (int I = 0; I < str.length(); I++) {
			char a = sb.charAt(sb.length() - str.length() + I);
			char b = str.charAt(I);
			if (a != b) {
				return false;
			}
		}
		return true;
	}

	public static String end(String s, int endLength) {
		int length = s.length();
		if (endLength >= length) {
			return s;
		}
		return s.substring(length - endLength);
	}

	public static void deleteEnd(StringBuilder sb, int endLength) {
		int length = sb.length();
		if (length <= endLength) {
			sb.setLength(0);
			return;
		}
		sb.delete(length - endLength, length);
	}

	public static String deleteEnd(String s, int endLength) {
		int end = s.length() - endLength;
		if (end <= 0) {
			return "";
		}
		return s.substring(0, end);
	}

	public static String firstCharToUpperCase(String s) {
		if (isEmpty(s)) {
			return "";
		}
		char ch = s.charAt(0);
		if (Character.isUpperCase(ch)) {
			return s;
		}
		StringBuilder sb = new StringBuilder(s);
		sb.setCharAt(0, Character.toUpperCase(ch));
		return sb.toString();
	}

	public static String firstCharToLowerCase(String s) {
		if (isEmpty(s)) {
			return "";
		}
		char ch = s.charAt(0);
		if (Character.isLowerCase(ch)) {
			return s;
		}
		StringBuilder sb = new StringBuilder(s);
		sb.setCharAt(0, Character.toLowerCase(ch));
		return sb.toString();
	}

	public static String[] splitPair(String s, String separator) {
		String[] pair = new String[] { "", "" };
		if (isEmpty(s)) {
			return pair;
		}
		int index = s.indexOf(separator);
		if (index == -1) {
			pair[0] = trim(s);
			return pair;
		}
		pair[0] = trim(s.substring(0, index));
		int valueStart = index + separator.length();
		if (valueStart >= s.length()) {
			return pair;
		}
		pair[1] = trim(s.substring(valueStart));
		return pair;
	}

	/**
	 * Split a string into an array of String, with given separators. Example 1:
	 * splitTokens("hello world", "l", "or") = ["he","lo w","ld"]. Example 2:
	 * splitTokens("fee = 3.45$", "=" ) = ["fee", "3.45$"]. Example 3:
	 * splitTokens(null, ":", "#") = ["","",""].
	 * 
	 * @param s
	 *            the string to split
	 * @param separators
	 *            separates array. shall never be null.
	 * @return an array of String
	 */
	public static String[] splitTokens(String s, String... separators) {
		String[] tokens = new String[separators.length + 1];
		Arrays.fill(tokens, "");
		if (isEmpty(s)) {
			return tokens;
		}
		int from = 0;
		for (int I = 0; I < separators.length; I++) {
			String separator = separators[I];
			int index = s.indexOf(separator, from);
			if (index == -1) {
				tokens[I] = trim(s.substring(from));
				return tokens;
			}
			tokens[I] = trim(s.substring(from, index));
			from = index + separator.length();
			if (from >= s.length()) {
				return tokens;
			}
		}
		tokens[separators.length] = trim(s.substring(from));
		return tokens;
	}

	public static String toS(Object[] objects, String separator) {
		return new FreeStringBuilder().append(objects, "", separator, "").toS();
	}

	public static String toS(Collection<?> objects, String separator) {
		return new FreeStringBuilder().append(objects, "", separator, "").toS();
	}

	public static String toS(Map<?, ?> map, String keyValueSeparator,
			String entrySeparator) {
		return new FreeStringBuilder().append(map, "", keyValueSeparator,
				entrySeparator, "").toS();
	}

	public static String toS(Class<?> c) {
		if (c == null) {
			return "";
		}
		return c.getSimpleName();
	}

	public static String toS(double d, int fraction) {
		return toS(d, fraction, fraction, false);
	}

	public static String toS(double d, int minFraction, int maxFraction,
			boolean groupingUsed) {
		NumberFormat format = NumberUtil.newFormat(minFraction, maxFraction,
				groupingUsed);
		return format.format(d);
	}

	public static String decode(byte[] data, String charset) {
		try {
			return new String(data, charset);
		} catch (UnsupportedEncodingException e) {
			String message = String.format(
					"Cannot decode bytes into String with unknown charset %s.",
					charset);
			throw new JewelException(message, e);
		}
	}

}
