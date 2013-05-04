package org.jade.util.collection.graph;

import org.jade.util.EqualsUtil;
import org.jade.util.FreeStringBuilder;
import org.jade.util.HashCodeBuilder;
import org.jade.util.collection.PropertiesSupport;

public class GraphEdge<E> extends PropertiesSupport<Object, Object> {

	private final GraphNode<?> from;

	private final GraphNode<?> to;

	private E value;

	public GraphEdge(GraphNode<?> from, GraphNode<?> to) {
		this(from, to, null);
	}

	public GraphEdge(GraphNode<?> from, GraphNode<?> to, E value) {
		this.from = from;
		this.to = to;
		this.value = value;
	}

	public GraphNode<?> getFrom() {
		return from;
	}

	public GraphNode<?> getTo() {
		return to;
	}

	public E getValue() {
		return value;
	}

	public void setValue(E value) {
		this.value = value;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object o) {
		if (o instanceof GraphEdge) {
			GraphEdge<E> other = (GraphEdge<E>) o;
			return EqualsUtil.isEquals(from, other.from)
					&& EqualsUtil.isEquals(to, other.to)
					&& EqualsUtil.isEquals(value, other.value);
		}
		return false;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(from).append(to).append(value)
				.toValue();
	}

	@Override
	public String toString() {
		return new FreeStringBuilder().append(from).append(" --> ").append(to)
				.append(" : ").append(value).toS();
	}

}
