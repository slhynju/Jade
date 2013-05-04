package org.jade.util;

import java.util.Arrays;

/**
 * Various String utility methods.
 * 
 * This class shall only use standard JDK API.
 * 
 * @author slhynju
 */
public final class StringUtil {

	public static boolean isEmpty(CharSequence chars) {
		return chars == null || chars.length() == 0;
	}

	public static boolean notEmpty(CharSequence chars) {
		return !isEmpty(chars);
	}

	/**
	 * Trim the String. null is trimmed to empty String.
	 */
	public static String trim(String s) {
		if (isEmpty(s)) {
			return "";
		}
		return s.trim();
	}

	public static boolean isYes(String s) {
		return "y".equalsIgnoreCase(s) || "yes".equalsIgnoreCase(s)
				|| "t".equalsIgnoreCase(s) || "true".equalsIgnoreCase(s);
	}

	public static boolean isNo(String s) {
		return "n".equalsIgnoreCase(s) || "no".equalsIgnoreCase(s)
				|| "f".equalsIgnoreCase(s) || "false".equalsIgnoreCase(s);
	}

	public static boolean isDigits(CharSequence s) {
		if (isEmpty(s)) {
			return false;
		}
		for (int I = 0; I < s.length(); I++) {
			char c = s.charAt(I);
			if (!Character.isDigit(c)) {
				return false;
			}
		}
		return true;
	}

	public static boolean notDigits(CharSequence s) {
		return !isDigits(s);
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

	public static String subStart(String s, int startLength) {
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
		int lengthL = sb.length();
		int lengthS = str.length();
		if (lengthS > lengthL) {
			return false;
		}
		for (int I = 0; I < lengthS; I++) {
			char a = sb.charAt(lengthL - lengthS + I);
			char b = str.charAt(I);
			if (a != b) {
				return false;
			}
		}
		return true;
	}

	public static String subEnd(String s, int endLength) {
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

	public static String wrap(String s, String wrapper) {
		StringBuilder sb = new StringBuilder(wrapper);
		sb.append(s).append(wrapper);
		return sb.toString();
	}

	public static String unwrap(String s, int wrapperLength) {
		if (wrapperLength * 2 >= s.length()) {
			return "";
		}
		return s.substring(wrapperLength, s.length() - wrapperLength);
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

	public static void firstCharToUpperCase(StringBuilder sb) {
		if (isEmpty(sb)) {
			return;
		}
		char ch = sb.charAt(0);
		if (Character.isUpperCase(ch)) {
			return;
		}
		sb.setCharAt(0, Character.toUpperCase(ch));
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

	public static void firstCharToLowerCase(StringBuilder sb) {
		if (isEmpty(sb)) {
			return;
		}
		char ch = sb.charAt(0);
		if (Character.isLowerCase(ch)) {
			return;
		}
		sb.setCharAt(0, Character.toLowerCase(ch));
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

}
