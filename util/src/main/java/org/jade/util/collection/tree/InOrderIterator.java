package org.jade.util.collection.tree;

public class InOrderIterator<T> extends TreeIterator<T, BinaryTreeNode<T>> {

	public InOrderIterator(Tree<BinaryTreeNode<T>> tree) {
		super(tree);
		this.next = getFirstNode(tree.getRoot());
	}

	@Override
	protected BinaryTreeNode<T> getPrevNode(BinaryTreeNode<T> node) {
		if (node.hasLeftChild()) {
			node = node.getLeftChild();
			while (node.hasRightChild()) {
				node = node.getRightChild();
			}
			return node;
		}
		while (node.isLeftChild()) {
			node = node.getParent();
		}
		if (node.isRightChild()) {
			return node.getParent();
		}
		return null;
	}

	@Override
	protected BinaryTreeNode<T> getNextNode(BinaryTreeNode<T> node) {
		if (node == null) {
			return getFirstNode(tree.getRoot());
		}
		if (node.hasRightChild()) {
			return getFirstNode(node.getRightChild());
		}
		while (node.isRightChild()) {
			node = node.getParent();
		}
		if (node.isLeftChild()) {
			return node.getParent();
		}
		return null;
	}

	@SuppressWarnings("static-method")
	private BinaryTreeNode<T> getFirstNode(BinaryTreeNode<T> node) {
		if (node == null) {
			return null;
		}
		while (node.hasLeftChild()) {
			node = node.getLeftChild();
		}
		return node;
	}

}
