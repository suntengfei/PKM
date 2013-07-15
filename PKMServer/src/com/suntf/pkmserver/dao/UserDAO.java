package com.suntf.pkmserver.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.suntf.pkmserver.model.User;

public class UserDAO {
	private String sql;
	
	public int checkUser(String name,String password,Connection conn)
	{
		int i = 0;
		sql = "select * from user where name= ? AND password= ?";
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, name);
			pst.setString(2, password);
			ResultSet rs = pst.executeQuery();
			if(rs.next())
				i = rs.getInt("id");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}
	
	public int add(User user,Connection conn)
	{
		int i = 0;
		sql = "insert into user ( name, password ) values( ?, ?)";
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, user.getName());
			pst.setString(2, user.getPassword());
			i = pst.executeUpdate();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}
	
	public User getInfo(String name,Connection conn)
	{
		User user = new User();
		sql = "select name,password,dairy_count,sh_count from user where name= ?";
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, name);
			ResultSet rs = pst.executeQuery();
			if(rs.next())
			{
				user.setName(rs.getString("name"));
				user.setPassword(rs.getString("password"));
				user.setDairy_count(rs.getInt("dairy_count"));
				user.setSh_count(rs.getInt("sh_count"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	
}












