package org.jewel.util.collection.tree;

public class PreOrderIterator<T> extends AbstractTreeIterator<T> {

	public PreOrderIterator(Tree<T> tree) {
		super(tree);
		this.next = tree.getRoot();
	}

	@Override
	protected TreeNode<T> getPrevNode(TreeNode<T> node) {
		if (node.hasPrevSibling()) {
			node = node.getPrevSibling();
			while (node.hasChildren()) {
				node = node.getLastChild();
			}
			return node;
		}
		return node.getParent();
	}

	@Override
	protected TreeNode<T> getNextNode(TreeNode<T> node) {
		if (node.hasChildren()) {
			return node.getFirstChild();
		}
		while (node != null && !node.hasNextSibling()) {
			node = node.getParent();
		}
		if (node == null) {
			return null;
		}
		return node.getNextSibling();
	}

}
