package com.suntf.pkmserver.dao.test;

import java.sql.Connection;
import java.sql.SQLException;

import com.suntf.pkmserver.dao.UserDAO;
import com.suntf.pkmserver.util.JDBCUtil;

import junit.framework.TestCase;


public class UserDAOTest extends TestCase{
	Connection conn;
	public void test_check()
	{
		try {
			conn = JDBCUtil.getConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		UserDAO udb = new UserDAO();
		int i =udb.checkUser("123", "123",conn);
		System.out.println(i);
	}
}