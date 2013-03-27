package org.jewel.util.collection;

/**
 * @author slhynju
 */
public interface ListClosure<T> {

	public void call(int index, T value);
}
