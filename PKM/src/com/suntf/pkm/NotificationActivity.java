package com.suntf.pkm;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class NotificationActivity extends Activity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		setContentView(R.layout.activity_notification);
		
		TextView content = (TextView)findViewById(R.id.notificontent);
		content.setText("        通知测试，管理员编写好通知信息，并向JPush服务器发送推送通知简要内容，而后JPush向用户推送通知，用户收到通知，点击通知后进入PKM软件的通知Activity,该Activity从本项目服务器端获取通知详细内容。");
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch (item.getItemId())
		{
		case android.R.id.home:
			NavUtils.navigateUpTo(this,
					new Intent(this, ItemListActivity.class));
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
