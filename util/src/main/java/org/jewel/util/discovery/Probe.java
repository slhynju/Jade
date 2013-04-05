package org.jewel.util.discovery;

import java.util.Queue;

public interface Probe<K, T> {

	public K explore(T obj, Queue<T> queue);
}
