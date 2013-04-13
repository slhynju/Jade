package org.jewel.util.collection;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * Various Collection utility methods.
 * 
 * @author slhynju
 * @ThreadSafe
 */
public final class CollectionUtil {

	public static boolean isEmpty(Collection<?> c) {
		return c == null || c.isEmpty();
	}

	public static boolean notEmpty(Collection<?> c) {
		return !isEmpty(c);
	}

	public static boolean isEmpty(Map<?, ?> map) {
		return map == null || map.isEmpty();
	}

	public static boolean notEmpty(Map<?, ?> map) {
		return !isEmpty(map);
	}

	@SafeVarargs
	public static <T> boolean isEmpty(T... array) {
		return array == null || array.length == 0;
	}

	@SafeVarargs
	public static <T> boolean notEmpty(T... array) {
		return !isEmpty(array);
	}

	@SafeVarargs
	public static <T> List<T> toList(T... array) {
		if (isEmpty(array)) {
			return new ArrayList<>(0);
		}
		return new ArrayList<>(Arrays.asList(array));
	}

	public static <T> List<T> toList(Collection<T> c) {
		if (isEmpty(c)) {
			return new ArrayList<>(0);
		}
		return new ArrayList<>(c);
	}

	public static List<String> toList(StringTokenizer tokenizer) {
		List<String> list = new ArrayList<>();
		while (tokenizer.hasMoreTokens()) {
			list.add(tokenizer.nextToken());
		}
		return list;
	}

	public static List<Integer> toList(int... array) {
		if (isEmpty(array)) {
			return new ArrayList<>(0);
		}
		List<Integer> list = new ArrayList<>(array.length);
		for (int I : array) {
			list.add(new Integer(I));
		}
		return list;
	}

	public static List<Double> toList(double... array) {
		if (isEmpty(array)) {
			return new ArrayList<>(0);
		}
		List<Double> list = new ArrayList<>(array.length);
		for (double d : array) {
			list.add(new Double(d));
		}
		return list;
	}

	public static List<Boolean> toList(boolean... array) {
		if (isEmpty(array)) {
			return new ArrayList<>(0);
		}
		List<Boolean> list = new ArrayList<>(array.length);
		for (boolean b : array) {
			list.add(Boolean.valueOf(b));
		}
		return list;
	}

	public static List<Long> toList(long... array) {
		if (isEmpty(array)) {
			return new ArrayList<>(0);
		}
		List<Long> list = new ArrayList<>(array.length);
		for (long l : array) {
			list.add(new Long(l));
		}
		return list;
	}

	public static <T> List<T> toList(Enumeration<T> enumeration) {
		List<T> list = new ArrayList<>();
		addAll(list, enumeration);
		return list;
	}

	public static <F, T> List<T> transform(List<F> fromList,
			Transformer<? super F, ? extends T> transformer) {
		if (isEmpty(fromList)) {
			return new ArrayList<>(0);
		}
		List<T> list = new ArrayList<>(fromList.size());
		for (F from : fromList) {
			list.add(transformer.transform(from));
		}
		return list;
	}

	@SafeVarargs
	public static <T> Set<T> toSet(T... array) {
		if (isEmpty(array)) {
			return new HashSet<>(0);
		}
		return new HashSet<>(Arrays.asList(array));
	}

	public static <T> Set<T> toSet(Collection<T> c) {
		if (isEmpty(c)) {
			return new HashSet<>(0);
		}
		return new HashSet<>(c);
	}

	public static Set<Integer> toSet(int... array) {
		if (isEmpty(array)) {
			return new HashSet<>(0);
		}
		Set<Integer> set = new HashSet<>(array.length);
		for (int I : array) {
			set.add(new Integer(I));
		}
		return set;
	}

	public static Set<Double> toSet(double... array) {
		if (isEmpty(array)) {
			return new HashSet<>(0);
		}
		Set<Double> set = new HashSet<>(array.length);
		for (double d : array) {
			set.add(new Double(d));
		}
		return set;
	}

	public static Set<Boolean> toSet(boolean... array) {
		if (isEmpty(array)) {
			return new HashSet<>(0);
		}
		Set<Boolean> set = new HashSet<>(array.length);
		for (boolean b : array) {
			set.add(Boolean.valueOf(b));
		}
		return set;
	}

	public static Set<Long> toSet(long... array) {
		if (isEmpty(array)) {
			return new HashSet<>(0);
		}
		Set<Long> set = new HashSet<>(array.length);
		for (long l : array) {
			set.add(new Long(l));
		}
		return set;
	}

	public static <F, T> Set<T> transform(Set<F> fromSet,
			Transformer<? super F, ? extends T> transformer) {
		if (isEmpty(fromSet)) {
			return new HashSet<>(0);
		}
		Set<T> set = new HashSet<>(fromSet.size());
		for (F from : fromSet) {
			set.add(transformer.transform(from));
		}
		return set;
	}

	public static int[] toIntArray(List<Integer> list, int defaultValue) {
		if (isEmpty(list)) {
			return new int[0];
		}
		int[] array = new int[list.size()];
		for (int I = 0; I < list.size(); I++) {
			Integer v = list.get(I);
			if (v == null) {
				array[I] = defaultValue;
			} else {
				array[I] = v.intValue();
			}
		}
		return array;
	}

	public static double[] toDoubleArray(List<Double> list, double defaultValue) {
		if (isEmpty(list)) {
			return new double[0];
		}
		double[] array = new double[list.size()];
		for (int I = 0; I < list.size(); I++) {
			Double v = list.get(I);
			if (v == null) {
				array[I] = defaultValue;
			} else {
				array[I] = v.doubleValue();
			}
		}
		return array;
	}

	public static boolean[] toBooleanArray(List<Boolean> list,
			boolean defaultValue) {
		if (isEmpty(list)) {
			return new boolean[0];
		}
		boolean[] array = new boolean[list.size()];
		for (int I = 0; I < list.size(); I++) {
			Boolean v = list.get(I);
			if (v == null) {
				array[I] = defaultValue;
			} else {
				array[I] = v.booleanValue();
			}
		}
		return array;
	}

	public static long[] toLongArray(List<Long> list, long defaultValue) {
		if (isEmpty(list)) {
			return new long[0];
		}
		long[] array = new long[list.size()];
		for (int I = 0; I < list.size(); I++) {
			Long v = list.get(I);
			if (v == null) {
				array[I] = defaultValue;
			} else {
				array[I] = v.longValue();
			}
		}
		return array;
	}

	public static String[] toStringArray(List<String> list) {
		if (isEmpty(list)) {
			return new String[0];
		}
		String[] array = new String[list.size()];
		return list.toArray(array);
	}

	public static Date[] toDateArray(List<Date> list) {
		if (isEmpty(list)) {
			return new Date[0];
		}
		Date[] array = new Date[list.size()];
		return list.toArray(array);
	}

	public static Class<?>[] toClassArray(List<Class<?>> list) {
		if (isEmpty(list)) {
			return new Class<?>[0];
		}
		Class<?>[] array = new Class<?>[list.size()];
		return list.toArray(array);
	}

	public static Method[] toMethodArray(List<Method> list) {
		if (isEmpty(list)) {
			return new Method[0];
		}
		Method[] array = new Method[list.size()];
		return list.toArray(array);
	}

	public static <T> T getFirst(List<T> list) {
		if (isEmpty(list)) {
			return null;
		}
		return list.get(0);
	}

	public static <T> T getFirst(T[] array) {
		if (isEmpty(array)) {
			return null;
		}
		return array[0];
	}

	public static <T> T getLast(List<T> list) {
		if (isEmpty(list)) {
			return null;
		}
		return list.get(list.size() - 1);
	}

	public static <T> T getLast(T[] array) {
		if (isEmpty(array)) {
			return null;
		}
		return array[array.length - 1];
	}

	public static <T> T getFirst(List<T> list, Matcher<? super T> matcher) {
		if (isEmpty(list)) {
			return null;
		}
		for (T obj : list) {
			if (matcher.matches(obj)) {
				return obj;
			}
		}
		return null;
	}

	public static <T> T getFirst(List<T> list, ListMatcher<? super T> matcher) {
		if (isEmpty(list)) {
			return null;
		}
		for (int I = 0; I < list.size(); I++) {
			T obj = list.get(I);
			if (matcher.matches(I, obj)) {
				return obj;
			}
		}
		return null;
	}

	public static <T> T getFirst(T[] array, Matcher<? super T> matcher) {
		if (isEmpty(array)) {
			return null;
		}
		for (T obj : array) {
			if (matcher.matches(obj)) {
				return obj;
			}
		}
		return null;
	}

	public static <T> T getFirst(T[] array, ListMatcher<? super T> matcher) {
		if (isEmpty(array)) {
			return null;
		}
		for (int I = 0; I < array.length; I++) {
			T obj = array[I];
			if (matcher.matches(I, obj)) {
				return obj;
			}
		}
		return null;
	}

	public static <T> T getLast(List<T> list, Matcher<? super T> matcher) {
		if (isEmpty(list)) {
			return null;
		}
		ListIterator<T> it = list.listIterator(list.size());
		while (it.hasPrevious()) {
			T obj = it.previous();
			if (matcher.matches(obj)) {
				return obj;
			}
		}
		return null;
	}

	public static <T> T getLast(List<T> list, ListMatcher<? super T> matcher) {
		if (isEmpty(list)) {
			return null;
		}
		for (int I = list.size() - 1; I >= 0; I--) {
			T obj = list.get(I);
			if (matcher.matches(I, obj)) {
				return obj;
			}
		}
		return null;
	}

	public static <T> T getLast(T[] array, Matcher<? super T> matcher) {
		if (isEmpty(array)) {
			return null;
		}
		for (int I = array.length - 1; I >= 0; I--) {
			T obj = array[I];
			if (matcher.matches(obj)) {
				return obj;
			}
		}
		return null;
	}

	public static <T> T getLast(T[] array, ListMatcher<? super T> matcher) {
		if (isEmpty(array)) {
			return null;
		}
		for (int I = array.length - 1; I >= 0; I--) {
			T obj = array[I];
			if (matcher.matches(I, obj)) {
				return obj;
			}
		}
		return null;
	}

	public static <T> T getElement(List<T> list, int index) {
		if (isEmpty(list)) {
			return null;
		}
		if (index < 0 || index >= list.size()) {
			return null;
		}
		return list.get(index);
	}

	public static <T> T getElement(T[] array, int index) {
		if (isEmpty(array)) {
			return null;
		}
		if (index < 0 || index >= array.length) {
			return null;
		}
		return array[index];
	}

	public static <T> void addAll(Collection<T> c, T[] array) {
		if (isEmpty(array)) {
			return;
		}
		c.addAll(Arrays.asList(array));
	}

	public static <T> void addAll(Collection<T> c, Enumeration<T> enumeration) {
		while (enumeration.hasMoreElements()) {
			c.add(enumeration.nextElement());
		}
	}

	public static <T> void addAllKeys(Collection<T> c, Map<T, ?> map) {
		c.addAll(map.keySet());
	}

	public static <T> void addAllValues(Collection<T> c, Map<?, T> map) {
		c.addAll(map.values());
	}

	public static <T> void addAllMatches(Collection<T> c, Collection<T> c2,
			Matcher<? super T> matcher) {
		for (T obj : c2) {
			if (matcher.matches(obj)) {
				c.add(obj);
			}
		}
	}

	public static <T> void addAllMatches(Collection<T> c, List<T> c2,
			ListMatcher<? super T> matcher) {
		for (int I = 0; I < c2.size(); I++) {
			T obj = c2.get(I);
			if (matcher.matches(I, obj)) {
				c.add(obj);
			}
		}
	}

	public static <T> void addAllMatches(Collection<T> c, T[] array,
			Matcher<? super T> matcher) {
		for (T obj : array) {
			if (matcher.matches(obj)) {
				c.add(obj);
			}
		}
	}

	public static <T> void addAllMatches(Collection<T> c, T[] array,
			ListMatcher<? super T> matcher) {
		for (int I = 0; I < array.length; I++) {
			T obj = array[I];
			if (matcher.matches(I, obj)) {
				c.add(obj);
			}
		}
	}

	public static <K, V> void addAllMatches(Map<K, V> map, Map<K, V> map2,
			Matcher2<? super K, ? super V> matcher) {
		Iterator<Entry<K, V>> it = map2.entrySet().iterator();
		while (it.hasNext()) {
			Entry<K, V> entry = it.next();
			K key = entry.getKey();
			V value = entry.getValue();
			if (matcher.matches(key, value)) {
				map.put(key, value);
			}
		}
	}

	public static <T> void removeAllMatches(Collection<T> c,
			Matcher<? super T> matcher) {
		Iterator<T> it = c.iterator();
		while (it.hasNext()) {
			T obj = it.next();
			if (matcher.matches(obj)) {
				it.remove();
			}
		}
	}

	public static <T> void removeAllMatches(List<T> list,
			ListMatcher<? super T> matcher) {
		for (int I = list.size() - 1; I >= 0; I--) {
			T obj = list.get(I);
			if (matcher.matches(I, obj)) {
				list.remove(I);
			}
		}
	}

	public static <K, V> void removeAllMatches(Map<K, V> map,
			Matcher2<? super K, ? super V> matcher) {
		Iterator<Entry<K, V>> it = map.entrySet().iterator();
		while (it.hasNext()) {
			Entry<K, V> entry = it.next();
			K key = entry.getKey();
			V value = entry.getValue();
			if (matcher.matches(key, value)) {
				it.remove();
			}
		}
	}

	public static <T> void retainAllMatches(Collection<T> c,
			Matcher<? super T> matcher) {
		Iterator<T> it = c.iterator();
		while (it.hasNext()) {
			T obj = it.next();
			if (!matcher.matches(obj)) {
				it.remove();
			}
		}
	}

	public static <T> void retainAllMatches(List<T> list,
			ListMatcher<? super T> matcher) {
		for (int I = list.size() - 1; I >= 0; I--) {
			T obj = list.get(I);
			if (!matcher.matches(I, obj)) {
				list.remove(I);
			}
		}
	}

	public static <K, V> void retainAllMatches(Map<K, V> map,
			Matcher2<? super K, ? super V> matcher) {
		Iterator<Entry<K, V>> it = map.entrySet().iterator();
		while (it.hasNext()) {
			Entry<K, V> entry = it.next();
			K key = entry.getKey();
			V value = entry.getValue();
			if (!matcher.matches(key, value)) {
				it.remove();
			}
		}
	}

	public static <T> List<T> sub(List<T> list, Matcher<? super T> matcher) {
		List<T> subList = new ArrayList<>();
		addAllMatches(subList, list, matcher);
		return subList;
	}

	public static <T> List<T> sub(List<T> list, ListMatcher<? super T> matcher) {
		List<T> subList = new ArrayList<>();
		addAllMatches(subList, list, matcher);
		return subList;
	}

	public static <T> List<T> sub(T[] array, Matcher<? super T> matcher) {
		List<T> subList = new ArrayList<>();
		addAllMatches(subList, array, matcher);
		return subList;
	}

	public static <T> List<T> sub(T[] array, ListMatcher<? super T> matcher) {
		List<T> subList = new ArrayList<>();
		addAllMatches(subList, array, matcher);
		return subList;
	}

	public static <T> Set<T> sub(Set<T> set, Matcher<? super T> matcher) {
		Set<T> subSet = new HashSet<>();
		addAllMatches(subSet, set, matcher);
		return subSet;
	}

	public static <K, V> Map<K, V> sub(Map<K, V> map,
			Matcher2<? super K, ? super V> matcher) {
		Map<K, V> subMap = new HashMap<>();
		addAllMatches(subMap, map, matcher);
		return subMap;
	}

	public static <T> void forEach(Collection<T> c, Closure<? super T> closure) {
		if (isEmpty(c)) {
			return;
		}
		for (T o : c) {
			closure.call(o);
		}
	}

	public static <T> void forEach(List<T> c, ListClosure<? super T> closure) {
		if (isEmpty(c)) {
			return;
		}
		for (int I = 0; I < c.size(); I++) {
			T o = c.get(I);
			closure.call(I, o);
		}
	}

	public static <T> void forEach(T[] a, Closure<? super T> closure) {
		if (isEmpty(a)) {
			return;
		}
		for (T o : a) {
			closure.call(o);
		}
	}

	public static <T> void forEach(T[] a, ListClosure<? super T> closure) {
		if (isEmpty(a)) {
			return;
		}
		for (int I = 0; I < a.length; I++) {
			T o = a[I];
			closure.call(I, o);
		}
	}

	public static <K, V> void forEach(Map<K, V> map,
			Closure2<? super K, ? super V> closure) {
		if (isEmpty(map)) {
			return;
		}
		Iterator<Entry<K, V>> it = map.entrySet().iterator();
		while (it.hasNext()) {
			Entry<K, V> entry = it.next();
			K key = entry.getKey();
			V value = entry.getValue();
			closure.call(key, value);
		}
	}

}
