package com.suntf.pkm;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.TextView;

public class ReadShareActivity extends Activity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_share_read);
		Intent i = getIntent();
		TextView title = (TextView)findViewById(R.id.titleview);
		TextView name = (TextView)findViewById(R.id.usercount);
		TextView time = (TextView)findViewById(R.id.timeview);
		TextView content = (TextView)findViewById(R.id.contentview);
		title.setText(i.getStringExtra("title"));
		name.setText(name.getText()+i.getStringExtra("name"));
		time.setText(i.getStringExtra("time"));
		content.setText(i.getStringExtra("content"));
	}
}
