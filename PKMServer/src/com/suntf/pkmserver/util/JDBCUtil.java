package com.suntf.pkmserver.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtil {
	private static Connection conn;

	private static String driverName = "com.mysql.jdbc.Driver";

	private static String url = "jdbc:mysql://127.0.0.1:3306/pkmdb";

	private static String user = "root";

	private static String pwd = "suntengfei";

	public static Connection getConnection() throws ClassNotFoundException,
			SQLException {
		Class.forName(driverName);
		conn = DriverManager.getConnection(url, user, pwd);
		return conn;
	}
}