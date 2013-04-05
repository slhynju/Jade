package org.jewel.util.collection.tree;

import java.util.Iterator;
import java.util.NoSuchElementException;

public abstract class AbstractTreeIterator<T> implements Iterator<TreeNode<T>> {

	protected TreeNode<T> current;

	protected TreeNode<T> next;

	protected Tree<T> tree;

	public AbstractTreeIterator(Tree<T> tree) {
		this.current = null;
		this.next = null;
		this.tree = tree;
	}

	@Override
	public boolean hasNext() {
		return next != null;
	}

	@Override
	public TreeNode<T> next() {
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
		TreeNode<T> prev = getPrevNode(current);
		TreeNode<T> parent = current.getParent();
		parent.removeChild(current);
		current = null;
		next = getNextNode(prev);
	}

	protected abstract TreeNode<T> getPrevNode(TreeNode<T> node);

	protected abstract TreeNode<T> getNextNode(TreeNode<T> node);

}
