package org.jade.util.collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.jade.util.EqualsUtil;

public class KeyValueList<K, V> implements List<KeyValue<K, V>> {

	private final ArrayList<KeyValue<K, V>> list;

	public KeyValueList() {
		list = new ArrayList<>();
	}

	public KeyValueList(int capacity) {
		list = new ArrayList<>(capacity);
	}

	public boolean containsKey(K key) {
		for (KeyValue<K, V> keyValue : list) {
			if (EqualsUtil.isEquals(key, keyValue.getKey())) {
				return true;
			}
		}
		return false;
	}

	public V getFirst(K key) {
		for (KeyValue<K, V> keyValue : list) {
			if (EqualsUtil.isEquals(key, keyValue.getKey())) {
				return keyValue.getValue();
			}
		}
		return null;
	}

	public void add(K key, V value) {
		add(new KeyValue<>(key, value));
	}

	@Override
	public int size() {
		return list.size();
	}

	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	@Override
	public boolean contains(Object o) {
		return list.contains(o);
	}

	@Override
	public Iterator<KeyValue<K, V>> iterator() {
		return list.iterator();
	}

	@Override
	public Object[] toArray() {
		return list.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return list.toArray(a);
	}

	@Override
	public boolean add(KeyValue<K, V> e) {
		return list.add(e);
	}

	@Override
	public boolean remove(Object o) {
		return list.remove(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return list.containsAll(c);
	}

	@Override
	public boolean addAll(Collection<? extends KeyValue<K, V>> c) {
		return list.addAll(c);
	}

	@Override
	public boolean addAll(int index, Collection<? extends KeyValue<K, V>> c) {
		return list.addAll(index, c);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return list.removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return list.retainAll(c);
	}

	@Override
	public void clear() {
		list.clear();
	}

	@Override
	public KeyValue<K, V> get(int index) {
		return list.get(index);
	}

	@Override
	public KeyValue<K, V> set(int index, KeyValue<K, V> element) {
		return list.set(index, element);
	}

	@Override
	public void add(int index, KeyValue<K, V> element) {
		list.add(index, element);
	}

	@Override
	public KeyValue<K, V> remove(int index) {
		return list.remove(index);
	}

	@Override
	public int indexOf(Object o) {
		return list.indexOf(o);
	}

	@Override
	public int lastIndexOf(Object o) {
		return list.lastIndexOf(o);
	}

	@Override
	public ListIterator<KeyValue<K, V>> listIterator() {
		return list.listIterator();
	}

	@Override
	public ListIterator<KeyValue<K, V>> listIterator(int index) {
		return list.listIterator(index);
	}

	@Override
	public List<KeyValue<K, V>> subList(int fromIndex, int toIndex) {
		return list.subList(fromIndex, toIndex);
	}

}
