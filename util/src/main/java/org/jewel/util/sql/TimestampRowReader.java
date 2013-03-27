package org.jewel.util.sql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public final class TimestampRowReader implements RowReader<Date> {

	@Override
	public Date readRow(ResultSet rs) throws SQLException {
		return SQLUtil.toDate(rs.getTimestamp(1));
	}

}
