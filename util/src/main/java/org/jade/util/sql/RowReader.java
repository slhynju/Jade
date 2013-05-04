package org.jade.util.sql;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RowReader<T> {

	/**
	 * Read a row from ResultSet and populate it to an object. You may return
	 * null to indicate SQLUtil.executeQuery() method to ignore it.
	 */
	public T readRow(ResultSet rs) throws SQLException;

}
