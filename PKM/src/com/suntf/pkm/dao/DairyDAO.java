package com.suntf.pkm.dao;

import java.util.ArrayList;

import com.suntf.pkm.model.Dairy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DairyDAO
{
	private DBOpenHelper helper;
	private SQLiteDatabase db; 
	
	public DairyDAO(Context context)
	{
		helper = new DBOpenHelper(context);
	}
	
	public void add(Dairy dairy)
	{
		db = helper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("title", dairy.getTitle());
		values.put("content", dairy.getContent());
		values.put("epoch", dairy.getEpoch());
		values.put("target", dairy.getTarget());
		values.put("time", dairy.getTime());
		values.put("category", dairy.getCategory());
		values.put("synchro", dairy.getSynchro());
		long m = db.insert("dairy", "title", values);
		Log.i("dairy","新插入的dairy的id:"+String.valueOf(m));
		db.close();
	}
	
	/*
	 * 获取所有文章列表
	 */
	public ArrayList<Dairy> getAll()
	{
		ArrayList<Dairy> dairys = new ArrayList<Dairy>();
		db = helper.getWritableDatabase();
		Cursor cursor = db.query("dairy", new String[]{"id","title"
				,"content","epoch","target","time","category","synchro"}, null, 
				null, null, null, "id desc");
		while(cursor.moveToNext())
		{
			dairys.add(new Dairy(cursor.getInt(0),cursor.getString(1),cursor.getString(2),
					cursor.getInt(3),cursor.getString(4),cursor.getString(5),cursor.getInt(6),cursor.getInt(7)));
			
		}
		cursor.close();
		db.close();
		return dairys;
	}
	
	/*
	 * 根据category获取文章列表
	 *  
	 *  Dairy(int id, String title, String content, int epoch,String target, String time, int category, int synchro)
	 */
	public ArrayList<Dairy> getByCategory(int category)
	{
		ArrayList<Dairy> dairys = new ArrayList<Dairy>();
		db = helper.getWritableDatabase();
		Cursor cursor = db.query("dairy", new String[]{"id","title"
				,"content","epoch","target","time","category","synchro"}, "category=? ", new String[]
						{String.valueOf(category)}, null, null, "id desc");
		while(cursor.moveToNext())
		{
			dairys.add(new Dairy(cursor.getInt(0),cursor.getString(1),cursor.getString(2),
					cursor.getInt(3),cursor.getString(4),cursor.getString(5),cursor.getInt(6),cursor.getInt(7)));
			
		}
		cursor.close();
		db.close();
		return dairys;
	}
	
	
	public Dairy getById(int id)
	{
		Dairy dairy = new Dairy();
		db = helper.getWritableDatabase();
		Cursor cursor = db.query("dairy", new String[]{"id","title"
				,"content","epoch","target","time","category","synchro"}, "id=? ", new String[]
						{String.valueOf(id)}, null, null,null);
		if(cursor.moveToNext())
		{
			dairy.setId(cursor.getInt(0));
			dairy.setTitle(cursor.getString(1));
			dairy.setContent(cursor.getString(2));
			dairy.setEpoch(cursor.getInt(3));
			dairy.setTarget(cursor.getString(4));
			dairy.setTime(cursor.getString(5));
			dairy.setCategory(cursor.getInt(6));
			dairy.setSynchro(cursor.getInt(7));
		}
		cursor.close();
		db.close();
		return dairy;
	}
	
	public int deleteById(int id)
	{
		db = helper.getWritableDatabase();
		int i = db.delete("dairy", "id=?", new String[]{String.valueOf(id)});
		Log.i("delete row: ",String.valueOf(i));
		db.close();
		return i;		
	}
	
	public int updateById(Dairy dairy)
	{
		db = helper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("title", dairy.getTitle());
		values.put("content", dairy.getContent());
		values.put("epoch", dairy.getEpoch());
		values.put("target", dairy.getTarget());
		values.put("time", dairy.getTime());
		values.put("category", dairy.getCategory());
		values.put("synchro", dairy.getSynchro());
		int i = db.update("dairy", values, "id=?", new String[]{String.valueOf(dairy.getId())});
		db.close();
		return i;
	}
}
