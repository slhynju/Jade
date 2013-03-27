package org.jewel.util.collection;

/**
 * Transform object from one type to another type.
 * @author slhynju
 */
public interface Transformer<F, T> {

	public T transform(F from);
}
