package org.jewel.util.date;

import java.io.Serializable;
import java.util.Date;

import org.jewel.util.BeanStringBuilder;
import org.jewel.util.EqualsUtil;
import org.jewel.util.HashCodeBuilder;

public class TimeInterval implements Serializable {

	private static final long serialVersionUID = 760291420251515470L;

	private Date start;

	private Date end;

	public TimeInterval() {
		this.start = null;
		this.end = null;
	}

	public TimeInterval(Date start, Date end) {
		this.start = start;
		this.end = end;
	}

	public boolean contains(Date d) {
		return !start.after(d) && end.after(d);
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(start).append(end).toValue();
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof TimeInterval) {
			TimeInterval other = (TimeInterval) o;
			return EqualsUtil.isEquals(start, other.start)
					&& EqualsUtil.isEquals(end, other.end);
		}
		return false;
	}

	@Override
	public String toString() {
		return toString(DateUtil.SIMPLE_FORMAT);
	}

	public String toString(String format) {
		return new BeanStringBuilder(TimeInterval.class)
				.append("start", start, format).append("end", end, format)
				.toS();
	}

}
