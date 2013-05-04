package org.jade.util.collection.tree;

import java.util.Iterator;

import org.jade.JadeException;
import org.jade.util.BeanStringBuilder;
import org.jade.util.EqualsUtil;
import org.jade.util.HashCodeBuilder;
import org.jade.util.collection.PropertiesSupport;

public class Tree<N extends TreeNode<?>> extends
		PropertiesSupport<Object, Object> {

	private N root;

	public Tree(N rootNode) {
		this.root = rootNode;
	}

	public N getRoot() {
		return root;
	}

	public void setRoot(N root) {
		this.root = root;
	}

	public boolean isBinaryTree() {
		return root instanceof BinaryTreeNode;
	}

	public boolean isMultiTree() {
		return root instanceof MultiTreeNode;
	}

	public TreeType getType() {
		if (isBinaryTree()) {
			return TreeType.BINARY;
		}
		return TreeType.MULTI;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Iterator<N> preOrderIterator() {
		return new PreOrderIterator(this);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Iterator<N> postOrderIterator() {
		return new PostOrderIterator(this);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public InOrderIterator<N> inOrderIterator() {
		if (isBinaryTree()) {
			return new InOrderIterator(this);
		}
		throw new JadeException(
				"Cannot call in-order iterator for non-binary tree.");
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object o) {
		if (o instanceof Tree) {
			Tree<N> other = (Tree<N>) o;
			return EqualsUtil.isEquals(root, other.root);
		}
		return false;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(root).toValue();
	}

	@Override
	public String toString() {
		return new BeanStringBuilder(Tree.class).append("root", root).toS();
	}
}
