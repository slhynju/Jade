package org.jewel.util.collection.tree;

public class PostOrderIterator<T> extends AbstractTreeIterator<T> {

	public PostOrderIterator(Tree<T> tree) {
		super(tree);
		next = getFirstNode();
	}

	@Override
	protected TreeNode<T> getPrevNode(TreeNode<T> node) {
		if (node.hasChildren()) {
			return node.getLastChild();
		}
		while (node != null && !node.hasPrevSibling()) {
			node = node.getParent();
		}
		if (node == null) {
			return null;
		}
		return node.getPrevSibling();
	}

	@Override
	protected TreeNode<T> getNextNode(TreeNode<T> node) {
		if (node == null) {
			return getFirstNode();
		}
		if (node.hasNextSibling()) {
			node = node.getNextSibling();
			while (node.hasChildren()) {
				node = node.getFirstChild();
			}
			return node;
		}
		return node.getParent();
	}

	private TreeNode<T> getFirstNode() {
		TreeNode<T> node = tree.getRoot();
		while (node != null && node.hasChildren()) {
			node = node.getFirstChild();
		}
		return node;
	}

}
