package org.jade.util.collection;

/**
 * @author slhynju
 */
public interface Closure2<K, V> {

	public void call(K key, V value);
}
