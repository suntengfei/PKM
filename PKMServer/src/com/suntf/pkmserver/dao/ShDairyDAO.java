package com.suntf.pkmserver.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.suntf.pkmserver.model.ShDairy;

public class ShDairyDAO {
	private String sql;
	
	public ArrayList<ShDairy> getShDairyCT(int count,long etime,Connection conn)
	{
		sql = "select * from sh_dairy where" +
				" sh_dairy.etime < ? and sh_dairy.check = 1 order by etime desc limit 0,?";
		ArrayList<ShDairy> shdairys = new ArrayList<ShDairy>();
		
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setLong(1, etime);
			pst.setInt(2, count);
			System.out.println(pst.toString());
			ResultSet rs = pst.executeQuery();
			while(rs.next())
			{
				ShDairy sd = new ShDairy(rs.getInt("id"),rs.getString("name"),
						rs.getString("title"),rs.getString("content"),
						rs.getString("time"),rs.getLong("etime"),
						rs.getInt("check"));
				shdairys.add(sd);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return shdairys;
	}
	
	public int checkShDairy(int id,int result,Connection conn)
	{
		sql = "update sh_dairy set check = ? where id = ?";
		int i = 0;
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, result);
			pst.setInt(2, id);
			i = pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return i;
	}
	
	public int addShDairy(ShDairy sdairy,Connection conn)
	{
		int i = 0;
		sql = "insert into sh_dairy ( name, title, content, time, etime) values( ?, ?, ?, ?, ?)";
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, sdairy.getName());
			pst.setString(2, sdairy.getTitle());
			pst.setString(3, sdairy.getContent());
			pst.setString(4, sdairy.getTime());
			pst.setLong(5, sdairy.getEtime());
			i = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}
}







