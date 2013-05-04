package org.jade.util.sql;

import java.sql.ResultSet;
import java.sql.SQLException;

public final class StringRowReader implements RowReader<String> {

	@Override
	public String readRow(ResultSet rs) throws SQLException {
		return rs.getString(1);
	}

}
