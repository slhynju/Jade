package org.jade.util.collection.graph;

import java.util.HashSet;
import java.util.Set;

import org.jade.util.EqualsUtil;
import org.jade.util.FreeStringBuilder;
import org.jade.util.HashCodeBuilder;
import org.jade.util.collection.PropertiesSupport;

public class GraphNode<N> extends PropertiesSupport<Object, Object> {

	private final N value;

	private final Set<GraphEdge<?>> incomings;

	private final Set<GraphEdge<?>> outcomings;

	public GraphNode(N value) {
		this(value, 4);
	}

	public GraphNode(N value, int defaultEdgeCapacity) {
		this.value = value;
		incomings = new HashSet<>(defaultEdgeCapacity);
		outcomings = new HashSet<>(defaultEdgeCapacity);
	}

	public N getValue() {
		return value;
	}

	public Set<GraphEdge<?>> getIncomings() {
		return incomings;
	}

	public void addIncoming(GraphEdge<?> incoming) {
		incomings.add(incoming);
	}

	public void removeIncoming(GraphEdge<?> incoming) {
		incomings.remove(incoming);
	}

	public boolean hasIncoming() {
		return !incomings.isEmpty();
	}

	public boolean hasNoIncoming() {
		return incomings.isEmpty();
	}

	public Set<GraphEdge<?>> getOutcomings() {
		return outcomings;
	}

	public void addOutcoming(GraphEdge<?> outcoming) {
		outcomings.add(outcoming);
	}

	public void removeOutcoming(GraphEdge<?> outcoming) {
		outcomings.remove(outcoming);
	}

	public boolean hasOutcoming() {
		return !outcomings.isEmpty();
	}

	public boolean hasNoOutcoming() {
		return outcomings.isEmpty();
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object o) {
		if (o instanceof GraphNode) {
			GraphNode<N> other = (GraphNode<N>) o;
			return EqualsUtil.isEquals(value, other.value);
		}
		return false;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(value).toValue();
	}

	@Override
	public String toString() {
		return new FreeStringBuilder().append(value).toS();
	}
}
