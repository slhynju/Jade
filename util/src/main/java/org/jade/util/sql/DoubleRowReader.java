package org.jade.util.sql;

import java.sql.ResultSet;
import java.sql.SQLException;

public final class DoubleRowReader implements RowReader<Double> {

	@Override
	public Double readRow(ResultSet rs) throws SQLException {
		double d = rs.getDouble(1);
		return new Double(d);
	}

}
