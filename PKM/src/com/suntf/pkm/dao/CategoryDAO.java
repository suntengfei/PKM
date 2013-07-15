package com.suntf.pkm.dao;

import java.util.ArrayList;

import com.suntf.pkm.model.Category;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class CategoryDAO
{
	private DBOpenHelper helper;
	private SQLiteDatabase db; 
	public CategoryDAO(Context context)
	{
		this.helper = new DBOpenHelper(context);
	}
	
	public ArrayList<Category> getAll()
	{
		ArrayList<Category> list = new ArrayList<Category>();
		db = helper.getWritableDatabase();
		Cursor cursor = db.query("category", new String[]{"id","name"}, null, 
				null, null, null, null);	
		while(cursor.moveToNext())
		{
			list.add(new Category(cursor.getInt(0),cursor.getString(1)));
		}
		cursor.close();
		db.close();
		return list;
	}
	public void add(String name)
	{
		db = helper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("name", name);
		db.insert("category", "name", values);
		db.close();
	}
	
	public int deleteByName(String name)
	{
		db = helper.getWritableDatabase();
		int i = db.delete("category", "name=?", new String[]{name});
		db.close();
		return i;
	}
	
	public boolean check(String name)
	{
		db = helper.getWritableDatabase();
		Cursor cursor = db.query("category", new String[]{"id","name"}, "name=?",
				new String[]{name}, null, null, null);
		boolean b = cursor.moveToNext();
		cursor.close();
		db.close();
		return b;
	}
}
