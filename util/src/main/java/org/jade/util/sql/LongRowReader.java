package org.jade.util.sql;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LongRowReader implements RowReader<Long> {

	@Override
	public Long readRow(ResultSet rs) throws SQLException {
		long l = rs.getLong(1);
		return new Long(l);
	}

}
