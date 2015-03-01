package org.djjs.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

public class DBUtil {

	private static Logger log = Logger.getLogger(DBUtil.class);

	public static java.sql.Connection getConnection() {
		DataSource ds = null;
		java.sql.Connection con = null;
		try {
			InitialContext ic = new InitialContext();
			String cc = "java:comp/env/jdbc/fm";
			ds = (DataSource) ic.lookup(cc);
			ic.close();
			con = ds.getConnection();
			con.setAutoCommit(false);
			con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
		} catch (Exception exp) {
			log.error(exp.getLocalizedMessage());
		}
		return con;

	}

	public static boolean closeConnection(java.sql.Connection con) {
		boolean flag = false;
		try {
			//con.commit();
			con.close();
			flag = true;
		} catch (Exception exp) {
			try {
				con.rollback();
				log.error("Failed to close DB connection");
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return flag;

	}
	
	public static void commitDB(Connection con) {
		try {
			con.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static java.sql.Connection getTestConnection() {
		DataSource ds = null;
		java.sql.Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/djjs_webapp", "root", "l0ckd0wn");
			con.setAutoCommit(false);
			con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
		} catch (Exception exp) {
			log.error(exp.getLocalizedMessage());
		}
		return con;

	}
	
	static {
		String info = "org.djjs.utility.DBUtil loaded sucessfully";
		log.info(info);
	}
	
	
	
}

