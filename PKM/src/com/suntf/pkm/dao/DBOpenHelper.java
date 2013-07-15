package com.suntf.pkm.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBOpenHelper extends SQLiteOpenHelper
{
	private static final int VERSION = 1;
	private static final String DBNAME = "data.db";
	private static final String DAIRY = "dairy";
	private static final String TAG = "tag";
	private static final String CATEGORY = "category";
	private static final String TAG_DAIRY = "tagdairy";
	private static final String INFORMATION = "information";

	public DBOpenHelper(Context context)
	{
		super(context, DBNAME, null, VERSION);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void onCreate(SQLiteDatabase db)
	{
		db.execSQL("create table "+DAIRY+"(id integer primary key," +
				"title varchar(20),content text," +
				"epoch integer,target varchar(50),time text,category integer,synchro integer)");
		db.execSQL("create table "+TAG+"(id integer primary key," +
				"name varchar(20))");
		db.execSQL("create table "+CATEGORY+"(id integer primary key," +
				"name varchar(20))");
		db.execSQL("create table "+TAG_DAIRY+"(id integer primary key," +
				"tag_id integer,dairy_target varchar(50))");
		db.execSQL("create table "+INFORMATION+"(id integer primary key," +
				"name varchar(20),password varchar(20),locald integer,"+
				"dcount integer,shared integer,time text)");
		String sql = "insert into dairy (title,content,epoch,target,time,category,synchro" +
				") values('欢迎你','第一篇文章，希望你能喜欢这个应用！',0,'000000','2013-05-03 18:17',0,0)";
		db.execSQL(sql);
		sql = "insert into tag (name) values('标签一')";
		db.execSQL(sql);

	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2)
	{
		// TODO Auto-generated method stub

	}

}
