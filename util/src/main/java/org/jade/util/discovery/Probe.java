package org.jade.util.discovery;

import java.util.Queue;

/**
 * TODO review this class
 * @author slhynju
 * @param <K>
 * @param <T>
 */
public interface Probe<K, T> {

	public K explore(T obj, Queue<T> queue);
}
