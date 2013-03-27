package org.jewel.util.collection;

/**
 * @author slhynju
 */
public interface Matcher2<K, V> {

	public boolean matches(K key, V value);

}