package org.jade.util.io;

import java.io.Serializable;

import org.jade.util.EqualsUtil;
import org.jade.util.FreeStringBuilder;
import org.jade.util.HashCodeBuilder;

public class TextLine implements Serializable {

	private static final long serialVersionUID = 238200120506659624L;

	private String text;

	// 1-based index
	private long lineNumber;

	public TextLine(String text, long lineNumber) {
		this.text = text;
		this.lineNumber = lineNumber;
	}

	public String getText() {
		return text;
	}

	public long getLineNumber() {
		return lineNumber;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setLineNumber(long lineNumber) {
		this.lineNumber = lineNumber;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof TextLine) {
			TextLine other = (TextLine) o;
			return EqualsUtil.isEquals(text, other.text)
					&& EqualsUtil.isEquals(lineNumber, other.lineNumber);
		}
		return false;
	}

	@Override
	public String toString() {
		return new FreeStringBuilder("line ").append(lineNumber).append(": ")
				.append(text).toS();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(text).append(lineNumber).toValue();
	}

}
