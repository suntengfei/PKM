package com.suntf.pkm.dao;

import java.util.ArrayList;

import com.suntf.pkm.model.*;

import android.test.AndroidTestCase;
import android.util.Log;

public class DAOTest extends AndroidTestCase
{
	private final static String TAG = "DAOtest";
	public void testDairyGetAll()
	{
		DairyDAO dairydao = new DairyDAO(this.getContext());
		ArrayList<Dairy> dairys = dairydao.getAll();
		for(int i = 0;i<dairys.size();i++)
			Log.i("dairydao", dairys.get(i).toString());
	}
	
	public void testTagGetAll()
	{
		TagDAO tagdao  = new TagDAO(this.getContext());
		ArrayList<Tag> tags = tagdao.getAll();
		for(int i = 0;i<tags.size();i++)
			Log.i("tagdao",tags.get(i).toString());
	}
}
