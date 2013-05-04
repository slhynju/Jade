package org.jade.util.collection;

import org.jade.util.BeanStringBuilder;
import org.jade.util.EqualsUtil;
import org.jade.util.HashCodeBuilder;

public class KeyValue<K, V> {

	private K key;

	private V value;

	public KeyValue() {
		this(null, null);
	}

	public KeyValue(K key) {
		this(key, null);
	}

	public KeyValue(K key, V value) {
		this.key = key;
		this.value = value;
	}

	public K getKey() {
		return key;
	}

	public void setKey(K key) {
		this.key = key;
	}

	public V getValue() {
		return value;
	}

	public void setValue(V value) {
		this.value = value;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(key).append(value).toValue();
	}

	@SuppressWarnings("rawtypes")
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof KeyValue) {
			KeyValue other = (KeyValue) obj;
			return EqualsUtil.isEquals(key, other.key)
					&& EqualsUtil.isEquals(value, other.value);
		}
		return false;
	}

	@Override
	public String toString() {
		return new BeanStringBuilder(KeyValue.class).append("key", key)
				.append("value", value).toS();
	}

}
