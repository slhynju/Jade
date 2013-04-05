package org.jewel.util.collection.tree;

import java.util.Iterator;

import org.jewel.util.BeanStringBuilder;
import org.jewel.util.EqualsUtil;
import org.jewel.util.HashCodeBuilder;
import org.jewel.util.collection.PropertiesSupport;

public class Tree<T> extends PropertiesSupport<Object, Object> {

	private TreeNode<T> root;

	public Tree(T rootValue) {
		root = new TreeNode<>(rootValue);
	}

	public TreeNode<T> getRoot() {
		return root;
	}

	public void setRoot(TreeNode<T> root) {
		this.root = root;
	}

	public Iterator<TreeNode<T>> preOrderIterator() {
		return new PreOrderIterator<>(this);
	}
	
	public Iterator<TreeNode<T>> postOrderIterator() {
		return new PostOrderIterator<>(this);
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object o) {
		if (o instanceof Tree) {
			Tree<T> other = (Tree<T>) o;
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
