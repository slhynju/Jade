package org.jade.util.collection.tree;

public class PostOrderIterator<T, N extends TreeNode<T>> extends TreeIterator<T,N> {

	public PostOrderIterator(Tree<N> tree) {
		super(tree);
		next = getFirstNode();
	}

	@SuppressWarnings("unchecked")
	@Override
	protected N getPrevNode(N node) {
		if (node.hasChildren()) {
			return (N)node.getLastChild();
		}
		while (node != null && !node.hasPrevSibling()) {
			node = (N)node.getParent();
		}
		if (node == null) {
			return null;
		}
		return (N)node.getPrevSibling();
	}

	@SuppressWarnings("unchecked")
	@Override
	protected N getNextNode(N node) {
		if (node == null) {
			return getFirstNode();
		}
		if (node.hasNextSibling()) {
			node = (N)node.getNextSibling();
			while (node.hasChildren()) {
				node = (N)node.getFirstChild();
			}
			return node;
		}
		return (N)node.getParent();
	}

	@SuppressWarnings("unchecked")
	private N getFirstNode() {
		TreeNode<T> node = tree.getRoot();
		while (node != null && node.hasChildren()) {
			node = node.getFirstChild();
		}
		return (N)node;
	}

}
