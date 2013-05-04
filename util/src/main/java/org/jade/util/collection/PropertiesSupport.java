package org.jade.util.collection;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Utility class to support objects with dynamic properties.
 * 
 * @author slhynju
 */
public class PropertiesSupport<K, V> {

	protected final Map<K, V> properties;

	public PropertiesSupport() {
		properties = new HashMap<>();
	}

	public PropertiesSupport(int capacity) {
		properties = new HashMap<>(capacity);
	}

	public boolean containsProperty(K key) {
		return properties.containsKey(key);
	}

	public V getProperty(K key) {
		return properties.get(key);
	}

	public V getProperty(K key, V defaultValue) {
		V value = getProperty(key);
		if (value == null) {
			return defaultValue;
		}
		return value;
	}

	public void setProperty(K key, V value) {
		properties.put(key, value);
	}

	public void addAllProperties(Map<K, V> other) {
		properties.putAll(other);
	}

	public void addAllProperties(PropertiesSupport<K, V> other) {
		addAllProperties(other.getProperties());
	}

	public Map<K, V> getProperties() {
		return properties;
	}

	public Iterator<Map.Entry<K, V>> iterator() {
		return properties.entrySet().iterator();
	}

}