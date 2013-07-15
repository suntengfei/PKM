package com.suntf.pkmserver.service;

import java.sql.Connection;
import java.sql.SQLException;

import com.suntf.pkmserver.dao.UserDAO;
import com.suntf.pkmserver.model.User;
import com.suntf.pkmserver.util.JDBCUtil;

public class UserService {
	private UserDAO ud;
	private Connection conn;
	
	public int check_login(String name,String pwd)
	{
		ud = new UserDAO();
		int i = 0;
		try {
			conn = JDBCUtil.getConnection();
			conn.setAutoCommit(false);
			i = ud.checkUser(name, pwd, conn);
			conn.commit();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return i;
	}
	
	public User getUInfo(String name)
	{
		ud = new UserDAO();
		User user = new User();
		try {
			conn = JDBCUtil.getConnection();
			conn.setAutoCommit(false);
			user = ud.getInfo(name, conn);
			conn.commit();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return user;
	}
	
}
