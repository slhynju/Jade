package org.jade.util.sql;

import java.sql.ResultSet;
import java.sql.SQLException;

public final class IntegerRowReader implements RowReader<Integer> {

	@Override
	public Integer readRow(ResultSet rs) throws SQLException {
		int n = rs.getInt(1);
		return new Integer(n);
	}

}
