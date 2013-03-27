package org.jewel.util.collection;

/**
 * @author slhynju
 */
public interface ListMatcher<T> {

	public boolean matches(int index, T value);
}
