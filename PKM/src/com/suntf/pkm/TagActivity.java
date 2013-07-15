package com.suntf.pkm;

import android.os.Bundle;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;

public class TagActivity extends FragmentActivity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_item_detail);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		if (savedInstanceState == null)
		{
			// Create the detail fragment and add it to the activity
			// using a fragment transaction.
			Bundle arguments = new Bundle();
			arguments.putString(DairyListFragment.ARG_ITEM_ID, getIntent()
					.getStringExtra(DairyListFragment.ARG_ITEM_ID));
			TagFragment fragment = new TagFragment();
			fragment.setArguments(arguments);
			getSupportFragmentManager().beginTransaction()
					.add(R.id.item_detail_container, fragment).commit();
		}
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch (item.getItemId())
		{
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpTo(this,
					new Intent(this, ItemListActivity.class));
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
