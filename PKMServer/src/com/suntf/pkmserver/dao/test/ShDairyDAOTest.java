package com.suntf.pkmserver.dao.test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import org.json.JSONException;


import com.suntf.pkmserver.dao.ShDairyDAO;
import com.suntf.pkmserver.model.ShDairy;
import com.suntf.pkmserver.util.JDBCUtil;

import junit.framework.TestCase;

public class ShDairyDAOTest  extends TestCase{
	Connection conn;
	public void test_check() throws JSONException
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
		ShDairyDAO udb = new ShDairyDAO();
		ArrayList<ShDairy> ai =udb.getShDairyCT(20, Long.MAX_VALUE, conn);
		for(int i = 0;i<ai.size();i++)
			System.out.println(ai.get(i).toJSONObject().toString());
	}
	
	public void test_add()
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
		ShDairy sd = new ShDairy();
		sd.setName("test");
		sd.setTitle("J2EE测试");
		sd.setContent("J2EE测试J2EE测试J2EE测试J2EE测试J2EE测试J2EE测试J2EE测试J2EE测试J2EE测试");
		sd.setTime("2013-05-21 / 16:36");
		sd.setEtime(new Date().getTime());
		ShDairyDAO udb = new ShDairyDAO();
		udb.addShDairy(sd, conn);
	}
}
