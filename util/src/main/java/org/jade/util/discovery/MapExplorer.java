package org.jade.util.discovery;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import org.jade.util.collection.CollectionUtil;

/**
 * TODO review this class
 * @author slhynju
 * @param <K>
 * @param <T>
 */
public class MapExplorer<K, T> {

	protected final Map<K, T> map;

	protected final Queue<T> queue;

	protected final List<Probe<K, T>> probes;

	public MapExplorer() {
		map = new HashMap<>();
		queue = new ArrayDeque<>();
		probes = new ArrayList<>();
	}

	public MapExplorer<K, T> addProbe(Probe<K, T> probe) {
		probes.add(probe);
		return this;
	}

	public void explore(T object) {
		queue.add(object);
		explore();
	}

	@SuppressWarnings("unchecked")
	public void explore(T... objects) {
		CollectionUtil.addAll(queue, objects);
		explore();
	}

	public void explore(Collection<T> objects) {
		queue.addAll(objects);
		explore();
	}

	public Map<K, T> toMap() {
		return map;
	}

	private void explore() {
		while (!queue.isEmpty()) {
			T current = queue.poll();
			if (map.containsValue(current)) {
				continue;
			}
			for (Probe<K, T> probe : probes) {
				K key = probe.explore(current, queue);
				if (key != null) {
					map.put(key, current);
				}
			}
		}
	}

}
