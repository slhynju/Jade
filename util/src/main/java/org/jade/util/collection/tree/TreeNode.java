package org.jade.util.collection.tree;

import java.util.List;

import org.jade.util.EqualsUtil;
import org.jade.util.FreeStringBuilder;
import org.jade.util.HashCodeBuilder;
import org.jade.util.collection.CollectionUtil;
import org.jade.util.collection.PropertiesSupport;

public abstract class TreeNode<T> extends PropertiesSupport<Object, Object> {

	protected final T value;

	protected TreeNode<T> parent;

	public TreeNode(T value) {
		this.value = value;
		this.parent = null;
	}

	public T getValue() {
		return value;
	}

	public boolean hasParent() {
		return parent != null;
	}

	public boolean isRoot() {
		return parent == null;
	}

	public TreeNode<T> getParent() {
		return parent;
	}

	public void setParent(TreeNode<T> parent) {
		this.parent = parent;
	}

	public abstract List<? extends TreeNode<T>> getChildren();

	public TreeNode<T> getChild(int index) {
		return CollectionUtil.getElement(getChildren(), index);
	}

	public abstract TreeNode<T> getFirstChild();

	public abstract TreeNode<T> getLastChild();

	public abstract void removeChild(TreeNode<T> child);

	public abstract boolean hasChildren();

	public abstract boolean isLeaf();

	public abstract int getChildrenSize();

	@SuppressWarnings("unchecked")
	public int getChildIndex(TreeNode<T> child) {
		List<TreeNode<T>> children = (List<TreeNode<T>>) getChildren();
		for (int I = 0; I < children.size(); I++) {
			TreeNode<T> t = children.get(I);
			// shall use == here instead of equals() method.
			if (t == child) {
				return I;
			}
		}
		return -1;
	}

	public boolean hasPrevSibling() {
		if (isRoot()) {
			return false;
		}
		int index = parent.getChildIndex(this);
		if (index <= 0) {
			return false;
		}
		return true;
	}

	public boolean hasNextSibling() {
		if (isRoot()) {
			return false;
		}
		int index = parent.getChildIndex(this);
		if (index >= parent.getChildrenSize() - 1) {
			return false;
		}
		return true;
	}

	public TreeNode<T> getPrevSibling() {
		if (isRoot()) {
			return null;
		}
		int index = parent.getChildIndex(this);
		if (index <= 0) {
			return null;
		}
		return parent.getChild(index - 1);
	}

	public TreeNode<T> getNextSibling() {
		if (isRoot()) {
			return null;
		}
		int index = parent.getChildIndex(this);
		if (index >= parent.getChildrenSize() - 1) {
			return null;
		}
		return parent.getChild(index + 1);
	}

	@Override
	public String toString() {
		return new FreeStringBuilder().append(value).toS();
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object o) {
		if (o instanceof TreeNode) {
			TreeNode<T> other = (TreeNode<T>) o;
			return EqualsUtil.isEquals(value, other.value);
		}
		return false;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(value).toValue();
	}
}