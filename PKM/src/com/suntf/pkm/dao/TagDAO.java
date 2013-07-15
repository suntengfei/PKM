package com.suntf.pkm.dao;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map.Entry;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.suntf.pkm.model.Tag;
import com.suntf.pkm.model.TagDairy;
public class TagDAO
{
	private DBOpenHelper helper;
	private SQLiteDatabase db; 
	public TagDAO(Context context)
	{
		this.helper = new DBOpenHelper(context);
	}
	public ArrayList<Tag> getAll()
	{
		ArrayList<Tag> list = new ArrayList<Tag>();
		db = helper.getWritableDatabase();
		Cursor cursor = db.query("tag", new String[]{"id","name"}, null, 
				null, null, null, "id asc");
		while(cursor.moveToNext())
		{
			list.add(new Tag(cursor.getInt(0),cursor.getString(1)));
		}
		cursor.close();
		db.close();
		return list;
	}
	
	//add tag by name
	public void addTag(String name)
	{
		db = helper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("name", name);
		db.insert("tag", "name", values);
		db.close();
	}
	
	
	//delete tag by id
	public int deleteTag(int id)
	{
		int i = 0;
		db = helper.getWritableDatabase();
		i = db.delete("tag", "id=?", new String[]{String.valueOf(id)});
		db.close();
		return i;
	}
	
	public int getDCountById(int tid)
	{
		int i = 0;
		db = helper.getWritableDatabase();
		Cursor cursor = db.query("tagdairy", new String[]{"COUNT(*)"}, "tag_id=?", 
				new String[]{String.valueOf(tid)}, null, null, null);
		if(cursor.moveToNext())
		{
			i = cursor.getInt(0);
		}
		cursor.close();
		db.close();
		return i;
	}
	
	public ArrayList<TagDairy> getDContentById(int tid)
	{
		db = helper.getWritableDatabase();
		ArrayList<TagDairy> list = new ArrayList<TagDairy>();
		Cursor cursor = db.query("tagdairy", new String[]{"id","tag_id","dairy_target"}, "tag_id=?", 
				new String[]{String.valueOf(tid)}, null, null, null);
		while(cursor.moveToNext())
		{
			list.add(new TagDairy(cursor.getInt(0),cursor.getInt(1),cursor.getString(2)));
		}
		cursor.close();
		db.close();
		return list;
	}
	
	public Hashtable<Integer,String> getTDByTarget(String dairy_target)
	{
		Hashtable<Integer,String> ht = new Hashtable<Integer,String>();
		db = helper.getWritableDatabase();
		Cursor cursor = db.query("tagdairy", new String[]{"tag_id","dairy_target"},
				"dairy_target=?",new String[]{dairy_target}, null, null, null);
		while(cursor.moveToNext())
		{
			ht.put(cursor.getInt(0), cursor.getString(1));
		}
		cursor.close();
		db.close();
		return ht;
	}
	
	public int addTD(Hashtable<Integer,String> tb,String dairy_target)
	{
		int i = 0;
		db = helper.getWritableDatabase();
		Iterator<Entry<Integer, String>> it = tb.entrySet().iterator();
		ContentValues values = new ContentValues();
		while(it.hasNext())
		{
			Entry<Integer, String> entry = it.next();
			values.clear();
			values.put("tag_id", entry.getKey());
			values.put("dairy_target", dairy_target);
			long m = db.insert("tagdairy", "tag_id", values);
			if(m!=-1)
				i++;
		}
		db.close();
		return i;
	}
	
	public int deleteTD(Hashtable<Integer,String> tb)
	{
		int i = 0;
		db = helper.getWritableDatabase();
		Iterator<Entry<Integer, String>> it = tb.entrySet().iterator();
		while(it.hasNext())
		{
			Entry<Integer, String> entry = it.next();
			i+=db.delete("tagdairy", "tag_id=? and dairy_target=?",
					new String[]{entry.getKey().toString(),entry.getValue()});		
		}
		db.close();
		return i;
	}
}
