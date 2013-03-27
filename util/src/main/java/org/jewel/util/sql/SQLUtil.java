package org.jewel.util.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jewel.JewelException;
import org.slf4j.Logger;

public final class SQLUtil {

	public static Date toDate(java.sql.Date sqlDate) {
		if (sqlDate == null) {
			return null;
		}
		return new Date(sqlDate.getTime());
	}

	public static java.sql.Date toSQLDate(Date d) {
		if (d == null) {
			return null;
		}
		return new java.sql.Date(d.getTime());
	}

	public static Date toDate(java.sql.Timestamp sqlTimestamp) {
		if (sqlTimestamp == null) {
			return null;
		}
		return new Date(sqlTimestamp.getTime());
	}

	public static java.sql.Timestamp toSQLTimestamp(Date d) {
		if (d == null) {
			return null;
		}
		return new java.sql.Timestamp(d.getTime());
	}

	public static void commit(Connection con, Logger log) {
		if (con == null) {
			return;
		}
		try {
			con.commit();
		} catch (SQLException e) {
			if (log.isErrorEnabled()) {
				log.error("Failed to commit transaction.", e);
			}
		}
	}

	public static void rollback(Connection con, Logger log) {
		if (con == null) {
			return;
		}
		try {
			con.rollback();
		} catch (SQLException e) {
			if (log.isWarnEnabled()) {
				log.warn("Failed to rollback transaction.", e);
			}
		}
	}

	public static void close(ResultSet rs, Logger log) {
		if (rs == null) {
			return;
		}
		try {
			rs.close();
		} catch (SQLException e) {
			if (log.isWarnEnabled()) {
				log.warn("Failed to close ResultSet.", e);
			}
		}
	}

	public static void close(PreparedStatement ps, Logger log) {
		if (ps == null) {
			return;
		}
		try {
			ps.close();
		} catch (SQLException e) {
			if (log.isWarnEnabled()) {
				log.warn("Failed to close PreparedStatement.", e);
			}
		}
	}

	public static void close(Connection con, Logger log) {
		if (con == null) {
			return;
		}
		try {
			con.close();
		} catch (SQLException e) {
			if (log.isErrorEnabled()) {
				log.error("Failed to close connection.", e);
			}
		}
	}

	public static <T> List<T> executeQuery(PreparedStatement ps,
			RowReader<T> reader) throws SQLException {
		List<T> list = new ArrayList<>();
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			T o = reader.readRow(rs);
			if (o != null) {
				list.add(o);
			}
		}
		rs.close();
		return list;
	}

	public static <T> T executeQueryFirst(PreparedStatement ps,
			RowReader<T> reader) throws SQLException {
		ResultSet rs = ps.executeQuery();
		T o = null;
		while (rs.next()) {
			o = reader.readRow(rs);
			if (o != null) {
				break;
			}
		}
		rs.close();
		return o;
	}

	public static int executeCount(PreparedStatement ps) throws SQLException {
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			int i = rs.getInt(1);
			rs.close();
			return i;
		}
		// shall never happen.
		rs.close();
		throw new JewelException("No ResultSet returned from query.");
	}

}
