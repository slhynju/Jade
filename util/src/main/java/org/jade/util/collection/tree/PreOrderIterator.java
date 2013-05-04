package org.jade.util.collection.tree;

public class PreOrderIterator<T, N extends TreeNode<T>> extends
		TreeIterator<T, N> {

	public PreOrderIterator(Tree<N> tree) {
		super(tree);
		this.next = tree.getRoot();
	}

	@SuppressWarnings("unchecked")
	@Override
	protected N getPrevNode(N node) {
		
		if (node.hasPrevSibling()) {
			node = (N) node.getPrevSibling();
			while (node.hasChildren()) {
				node = (N) node.getLastChild();
			}
			return node;
		}
		return (N) node.getParent();
	}

	@SuppressWarnings("unchecked")
	@Override
	protected N getNextNode(N node) {
		if (node == null) {
			return tree.getRoot();
		}
		if (node.hasChildren()) {
			return (N) node.getFirstChild();
		}
		while (node != null && !node.hasNextSibling()) {
			node = (N) node.getParent();
		}
		if (node == null) {
			return null;
		}
		return (N) node.getNextSibling();
	}

}
