package org.jade.util.collection.tree;

import java.util.ArrayList;
import java.util.List;

import org.jade.util.EqualsUtil;
import org.jade.util.FreeStringBuilder;
import org.jade.util.HashCodeBuilder;
import org.jade.util.collection.CollectionUtil;

public class MultiTreeNode<T> extends TreeNode<T> {

	private final List<MultiTreeNode<T>> children;

	public MultiTreeNode(T value) {
		this(value, 4);
	}

	public MultiTreeNode(T value, int defaultChildrenCapacity) {
		super(value);
		this.children = new ArrayList<>(defaultChildrenCapacity);
	}

	@Override
	public MultiTreeNode<T> getParent() {
		return (MultiTreeNode<T>) parent;
	}

	public void setParent(MultiTreeNode<T> parent) {
		this.parent = parent;
	}

	@Override
	public List<MultiTreeNode<T>> getChildren() {
		return children;
	}

	@Override
	public MultiTreeNode<T> getChild(int index) {
		return CollectionUtil.getElement(children, index);
	}

	@Override
	public MultiTreeNode<T> getFirstChild() {
		return CollectionUtil.getFirst(children);
	}

	@Override
	public MultiTreeNode<T> getLastChild() {
		return CollectionUtil.getLast(children);
	}

	public void addChild(MultiTreeNode<T> child) {
		children.add(child);
	}

	@Override
	public void removeChild(TreeNode<T> child) {
		children.remove(child);
	}

	@Override
	public boolean hasChildren() {
		return !children.isEmpty();
	}

	@Override
	public boolean isLeaf() {
		return children.isEmpty();
	}

	@Override
	public int getChildrenSize() {
		return children.size();
	}

	@Override
	public String toString() {
		return new FreeStringBuilder().append(value).toS();
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object o) {
		if (o instanceof MultiTreeNode) {
			MultiTreeNode<T> other = (MultiTreeNode<T>) o;
			return EqualsUtil.isEquals(value, other.value);
		}
		return false;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(value).toValue();
	}

}
