package org.jade.util.collection.graph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jade.util.collection.CollectionUtil;
import org.jade.util.collection.Matcher2;
import org.jade.util.collection.PropertiesSupport;

public class Graph<N, E> extends PropertiesSupport<Object, Object> {

	private final Map<N, GraphNode<N>> nodes;

	public Graph() {
		nodes = new HashMap<>();
	}

	public Graph(Collection<? extends N> c) {
		nodes = new HashMap<>(c.size());
		for (N o : c) {
			GraphNode<N> node = new GraphNode<>(o);
			nodes.put(o, node);
		}
	}

	public Graph(Collection<? extends N> c, Matcher2<N, N> matcher) {
		this(c);
		for (GraphNode<N> fromNode : nodes.values()) {
			for (GraphNode<N> toNode : nodes.values()) {
				if (matcher.matches(fromNode.getValue(), toNode.getValue())) {
					link(fromNode, toNode);
				}
			}
		}
	}

	public boolean isEmpty() {
		return nodes.isEmpty();
	}

	public boolean notEmpty() {
		return !isEmpty();
	}

	public int size() {
		return nodes.size();
	}

	public Collection<GraphNode<N>> getNodes() {
		return nodes.values();
	}

	public GraphNode<N> getNode(N value) {
		return nodes.get(value);
	}

	public GraphNode<N> toNode(N value) {
		GraphNode<N> node = nodes.get(value);
		if (node == null) {
			node = new GraphNode<>(value);
			nodes.put(value, node);
		}
		return node;
	}

	public GraphEdge<E> link(N from, N to) {
		return link(from, to, null);
	}

	public GraphEdge<E> link(N from, N to, E value) {
		GraphNode<N> fromNode = toNode(from);
		GraphNode<N> toNode = toNode(to);
		return link(fromNode, toNode, value);
	}

	public GraphEdge<E> link(GraphNode<N> from, GraphNode<N> to) {
		return link(from, to, null);
	}

	public GraphEdge<E> link(GraphNode<N> from, GraphNode<N> to, E value) {
		GraphEdge<E> edge = new GraphEdge<>(from, to, value);
		from.addOutcoming(edge);
		to.addIncoming(edge);
		return edge;
	}

	public boolean containsNode(N value) {
		return nodes.containsKey(value);
	}

	public void removeNode(N value) {
		GraphNode<N> node = nodes.remove(value);
		if (node == null) {
			return;
		}
		removeInvalidEdges(node);
	}

	public void removeNode(GraphNode<N> node) {
		nodes.remove(node.getValue());
		removeInvalidEdges(node);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static void removeInvalidEdges(GraphNode<?> node) {
		for (GraphEdge incoming : node.getIncomings()) {
			GraphNode from = incoming.getFrom();
			from.removeOutcoming(incoming);
		}
		for (GraphEdge outcoming : node.getOutcomings()) {
			GraphNode to = outcoming.getTo();
			to.removeIncoming(outcoming);
		}
	}

	public GraphNode<N> findNodeWithoutIncoming() {
		for (GraphNode<N> node : nodes.values()) {
			if (node.hasNoIncoming()) {
				return node;
			}
		}
		return null;
	}

	public GraphNode<N> findNodeWithoutOutcoming() {
		for (GraphNode<N> node : nodes.values()) {
			if (node.hasNoOutcoming()) {
				return node;
			}
		}
		return null;
	}

	public void reduceToCircle() {
		GraphNode<N> node = findNodeWithoutIncoming();
		while (node != null) {
			removeNode(node);
			node = findNodeWithoutIncoming();
		}
		node = findNodeWithoutOutcoming();
		while (node != null) {
			removeNode(node);
			node = findNodeWithoutOutcoming();
		}
	}

	public void forEachNode(NodeClosure<N> closure) {
		CollectionUtil.forEach(nodes, closure);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Collection<GraphEdge<E>> getEdges() {
		List edges = new ArrayList();
		for (GraphNode<N> node : nodes.values()) {
			edges.addAll(node.getIncomings());
		}
		return edges;
	}

	public void forEachEdge(EdgeClosure<E> closure) {
		for (GraphEdge<E> edge : getEdges()) {
			closure.call(edge.getValue(), edge);
		}
	}

}
