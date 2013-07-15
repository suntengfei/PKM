package com.suntf.pkmserver.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import org.json.JSONArray;

import com.suntf.pkmserver.dao.ShDairyDAO;
import com.suntf.pkmserver.model.ShDairy;
import com.suntf.pkmserver.util.JDBCUtil;

public class ShDairyService {
	private Connection conn;
	private ShDairyDAO shdairydao;
	
	public JSONArray getShDairyCT(int count,long etime)
	{
		shdairydao = new ShDairyDAO();
		JSONArray ja = new JSONArray();
		try {
			conn = JDBCUtil.getConnection();
			conn.setAutoCommit(false);
			ArrayList<ShDairy> dairys = shdairydao.getShDairyCT(count, etime, conn);
			conn.commit();
			
			for(int i = 0;i<dairys.size();i++)
				ja.put(i, dairys.get(i).toJSONObject());			
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
		return ja;
	}
	
	public int addShDairy(ShDairy sd)
	{
		int i = 0;
		shdairydao = new ShDairyDAO();
		try {
			conn = JDBCUtil.getConnection();
			conn.setAutoCommit(false);
			i = shdairydao.addShDairy(sd, conn);
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
}
