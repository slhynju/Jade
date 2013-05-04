package org.jade.util.collection.tree;

import java.util.ArrayList;
import java.util.List;

import org.jade.util.EqualsUtil;
import org.jade.util.FreeStringBuilder;
import org.jade.util.HashCodeBuilder;

public class BinaryTreeNode<T> extends TreeNode<T> {

	private BinaryTreeNode<T> leftChild;

	private BinaryTreeNode<T> rightChild;

	public BinaryTreeNode(T value) {
		super(value);
		leftChild = null;
		rightChild = null;
	}

	@Override
	public BinaryTreeNode<T> getParent() {
		return (BinaryTreeNode<T>) parent;
	}

	public void setParent(BinaryTreeNode<T> parent) {
		this.parent = parent;
	}

	@Override
	public List<BinaryTreeNode<T>> getChildren() {
		List<BinaryTreeNode<T>> list = new ArrayList<>(2);
		if (leftChild != null) {
			list.add(leftChild);
		}
		if (rightChild != null) {
			list.add(rightChild);
		}
		return list;
	}

	@Override
	public BinaryTreeNode<T> getChild(int index) {
		return (BinaryTreeNode<T>) super.getChild(index);
	}

	@Override
	public BinaryTreeNode<T> getFirstChild() {
		if (leftChild != null) {
			return leftChild;
		}
		return rightChild;
	}

	@Override
	public BinaryTreeNode<T> getLastChild() {
		if (rightChild != null) {
			return rightChild;
		}
		return leftChild;
	}

	@Override
	public void removeChild(TreeNode<T> child) {
		if (leftChild == child) {
			leftChild = null;
		}
		if (rightChild == child) {
			rightChild = null;
		}
	}

	@Override
	public boolean hasChildren() {
		return leftChild != null || rightChild != null;
	}

	@Override
	public boolean isLeaf() {
		return leftChild == null && rightChild == null;
	}

	@Override
	public int getChildrenSize() {
		int count = 0;
		if (leftChild != null) {
			count++;
		}
		if (rightChild != null) {
			count++;
		}
		return count;
	}

	public boolean isLeftChild() {
		BinaryTreeNode<T> p = (BinaryTreeNode<T>) parent;
		return p != null && p.leftChild == this;
	}

	public boolean hasLeftChild() {
		return leftChild != null;
	}

	public BinaryTreeNode<T> getLeftChild() {
		return leftChild;
	}

	public boolean isRightChild() {
		BinaryTreeNode<T> p = (BinaryTreeNode<T>) parent;
		return p != null && p.rightChild == this;
	}

	public void setLeftChild(BinaryTreeNode<T> leftChild) {
		this.leftChild = leftChild;
	}

	public boolean hasRightChild() {
		return rightChild != null;
	}

	public BinaryTreeNode<T> getRightChild() {
		return rightChild;
	}

	public void setRightChild(BinaryTreeNode<T> rightChild) {
		this.rightChild = rightChild;
	}

	@Override
	public String toString() {
		return new FreeStringBuilder().append(value).toS();
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object o) {
		if (o instanceof BinaryTreeNode) {
			BinaryTreeNode<T> other = (BinaryTreeNode<T>) o;
			return EqualsUtil.isEquals(value, other.value);
		}
		return false;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(value).toValue();
	}

}
