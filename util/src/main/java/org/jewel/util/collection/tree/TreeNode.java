package org.jewel.util.collection.tree;

import java.util.ArrayList;
import java.util.List;

import org.jewel.util.EqualsUtil;
import org.jewel.util.FreeStringBuilder;
import org.jewel.util.HashCodeBuilder;
import org.jewel.util.collection.CollectionUtil;
import org.jewel.util.collection.PropertiesSupport;

public class TreeNode<T> extends PropertiesSupport<Object, Object> {

	private final T value;

	private TreeNode<T> parent;

	private final List<TreeNode<T>> children;

	public TreeNode(T value) {
		this(value, 4);
	}

	public TreeNode(T value, int defaultChildrenCapacity) {
		this.value = value;
		this.parent = null;
		this.children = new ArrayList<>(defaultChildrenCapacity);
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

	public List<TreeNode<T>> getChildren() {
		return children;
	}

	public TreeNode<T> getChild(int index) {
		return CollectionUtil.getElement(children, index);
	}

	public TreeNode<T> getFirstChild() {
		return CollectionUtil.getFirst(children);
	}

	public TreeNode<T> getLastChild() {
		return CollectionUtil.getLast(children);
	}

	public void addChild(TreeNode<T> child) {
		children.add(child);
	}

	public void removeChild(TreeNode<T> child) {
		children.remove(child);
	}

	public boolean hasChildren() {
		return !children.isEmpty();
	}

	public boolean isLeaf() {
		return children.isEmpty();
	}

	public int getChildrenSize() {
		return children.size();
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

	public int getChildIndex(TreeNode<T> child) {
		for (int I = 0; I < children.size(); I++) {
			TreeNode<T> t = children.get(I);
			// shall use == here instead of equals() method.
			if (t == child) {
				return I;
			}
		}
		return -1;
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
