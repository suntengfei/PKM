package com.suntf.pkm;

import com.suntf.pkm.dao.DairyDAO;
import com.suntf.pkm.model.Dairy;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ReadActivity extends Activity
{

	private DairyDAO dairydao;
	private TextView title;
	private TextView content;
	private TextView time;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_read);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		Intent i = getIntent();
		int id = i.getIntExtra("id", 0);
		if(id==0)
			return;
		dairydao = new DairyDAO(this);
		Dairy dairy = dairydao.getById(id);
		title = (TextView)findViewById(R.id.titleview);
		content = (TextView)findViewById(R.id.contentview);
		time = (TextView)findViewById(R.id.timeview);
		
		title.setText(dairy.getTitle());
		content.setText(dairy.getContent());
		time.setText(dairy.getTime());
	}

	
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch (item.getItemId())
		{
		case android.R.id.home:
			this.finish();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
