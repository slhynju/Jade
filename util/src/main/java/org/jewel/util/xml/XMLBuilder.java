package org.jewel.util.xml;

import java.util.ArrayDeque;
import java.util.Deque;

import org.jewel.JewelException;
import org.jewel.util.FreeStringBuilder;
import org.jewel.util.StringUtil;

public final class XMLBuilder {

	private final FreeStringBuilder sb;

	private final Deque<String> queue;

	private State state;

	public XMLBuilder(String root) {
		sb = new FreeStringBuilder();
		sb.append('<').append(root);
		queue = new ArrayDeque<>();
		queue.push(root);
		state = State.TAG_STARTED;
	}

	public XMLBuilder start(String tag) {
		switch (state) {
		case TAG_STARTED:
			sb.append("><").append(tag);
			queue.push(tag);
			return this;
		case BODY_EXPECTED:
			sb.append('<').append(tag);
			queue.push(tag);
			state = State.TAG_STARTED;
			return this;
		default:
			throw new JewelException("Root element is already closed.");
		}
	}

	public XMLBuilder attribute(String name, String value) {
		switch (state) {
		case TAG_STARTED:
			sb.append(' ').append(name).append("=\"").append(value)
					.append('\"');
			return this;
		case BODY_EXPECTED:
			throw new JewelException(
					"attribute() shall be called after start() method.");
		default:
			throw new JewelException("Root element is already closed.");
		}
	}

	public XMLBuilder attribute(String name, int value) {
		switch (state) {
		case TAG_STARTED:
			sb.append(' ').append(name).append("=\"").append(value)
					.append('\"');
			return this;
		case BODY_EXPECTED:
			throw new JewelException(
					"attribute() shall be called after start() method.");
		default:
			throw new JewelException("Root element is already closed.");
		}
	}

	public XMLBuilder attribute(String name, long value) {
		switch (state) {
		case TAG_STARTED:
			sb.append(' ').append(name).append("=\"").append(value)
					.append('\"');
			return this;
		case BODY_EXPECTED:
			throw new JewelException(
					"attribute() shall be called after start() method.");
		default:
			throw new JewelException("Root element is already closed.");
		}
	}

	public XMLBuilder attribute(String name, boolean value) {
		switch (state) {
		case TAG_STARTED:
			sb.append(' ').append(name).append("=\"").append(value)
					.append('\"');
			return this;
		case BODY_EXPECTED:
			throw new JewelException(
					"attribute() shall be called after start() method.");
		default:
			throw new JewelException("Root element is already closed.");
		}
	}

	public XMLBuilder attribute(String name, double value) {
		switch (state) {
		case TAG_STARTED:
			sb.append(' ').append(name).append("=\"").append(value)
					.append('\"');
			return this;
		case BODY_EXPECTED:
			throw new JewelException(
					"attribute() shall be called after start() method.");
		default:
			throw new JewelException("Root element is already closed.");
		}
	}

	public XMLBuilder attribute(String name, char value) {
		switch (state) {
		case TAG_STARTED:
			sb.append(' ').append(name).append("=\"").append(value)
					.append('\"');
			return this;
		case BODY_EXPECTED:
			throw new JewelException(
					"attribute() shall be called after start() method.");
		default:
			throw new JewelException("Root element is already closed.");
		}
	}

	public XMLBuilder body(String body) {
		switch (state) {
		case TAG_STARTED:
			sb.append('>').append(body);
			state = State.BODY_EXPECTED;
			return this;
		case BODY_EXPECTED:
			sb.append(body);
			return this;
		default:
			throw new JewelException("Root element is already closed.");
		}
	}

	public XMLBuilder close() {
		switch (state) {
		case TAG_STARTED:
			sb.append("/>");
			queue.pop();
			if (queue.isEmpty()) {
				state = State.ROOT_CLOSED;
			} else {
				state = State.BODY_EXPECTED;
			}
			return this;
		case BODY_EXPECTED:
			String tag = queue.pop();
			sb.append("</").append(tag).append('>');
			if (queue.isEmpty()) {
				state = State.ROOT_CLOSED;
			}
			return this;
		default:
			throw new JewelException("Root element is already closed.");
		}
	}

	public XMLBuilder child(String tag) {
		start(tag);
		close();
		return this;
	}

	public XMLBuilder child(String tag, String text) {
		start(tag);
		if (StringUtil.notEmpty(text)) {
			body(text);
		}
		close();
		return this;
	}

	public XMLBuilder closeAll() {
		while (!queue.isEmpty()) {
			close();
		}
		return this;
	}

	public String toS() {
		return sb.toS();
	}

	@Override
	public String toString() {
		return toS();
	}

	private static enum State {
		TAG_STARTED, BODY_EXPECTED, ROOT_CLOSED
	}

}
