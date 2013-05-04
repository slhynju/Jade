package org.jade.util.collection.tree;

import java.util.Iterator;
import java.util.NoSuchElementException;

public abstract class TreeIterator<T, N extends TreeNode<T>> implements
		Iterator<N> {

	protected N current;

	protected N next;

	protected Tree<N> tree;

	public TreeIterator(Tree<N> tree) {
		this.current = null;
		this.next = null;
		this.tree = tree;
	}

	@Override
	public boolean hasNext() {
		return next != null;
	}

	@Override
	public N next() {
		if (next == null) {
			throw new NoSuchElementException(
					"No more TreeNode to iterate in the Tree.");
		}
		current = next;
		next = getNextNode(next);
		return current;
	}

	@Override
	public void remove() {
		if (current == null) {
			throw new IllegalStateException(
					"remove() method shall be called at most once after each next() method.");
		}
		if (current.isRoot()) {
			tree.setRoot(null);
			current = null;
			next = null;
			return;
		}
		N prev = getPrevNode(current);
		TreeNode<T> parent = current.getParent();
		parent.removeChild(current);
		current = null;
		next = getNextNode(prev);
	}

	protected abstract N getPrevNode(N node);

	protected abstract N getNextNode(N node);

}
