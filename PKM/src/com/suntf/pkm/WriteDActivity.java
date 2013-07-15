package com.suntf.pkm;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import com.suntf.pkm.dao.*;
import com.suntf.pkm.model.*;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class WriteDActivity extends FragmentActivity implements
		ActionBar.OnNavigationListener,View.OnClickListener
{
 
	/**
	 * The serialization (saved instance state) Bundle key representing the
	 * current dropdown position.
	 */
	private static final String STATE_SELECTED_NAVIGATION_ITEM = "selected_navigation_item";

	private boolean update = false;
	private String updatet = null;
	private int updateid = 0;
	private TagDAO tagdao;
	private CategoryDAO categorydao;
	private DairyDAO dairydao;
	private ArrayList<Category> clist;
	private ArrayList<Tag> tlist;
	
	private int cselected = 0;
	private Hashtable<Integer,String> tselected = new Hashtable<Integer,String>();
	private Hashtable<Integer,String> alselected;
	
	private Button save;
	private EditText title;
	private EditText content;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.write_dairy);
		
		// Set up the action bar to show a dropdown list.
		final ActionBar actionBar = getActionBar();
		actionBar.setDisplayShowTitleEnabled(false);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
		
		tagdao = new TagDAO(this);
		dairydao = new DairyDAO(this);
		categorydao = new CategoryDAO(this);
		clist = categorydao.getAll();
		
		//填充category
		String[] cs = new String[clist.size()+1];
		cs[0] = "默认";
		for(int i = 0 ;i<clist.size();i++)
			cs[i+1] = clist.get(i).getName();
		
		// Set up the dropdown list navigation in the action bar.
		actionBar.setListNavigationCallbacks(
		// Specify a SpinnerAdapter to populate the dropdown list.
				new ArrayAdapter<String>(actionBar.getThemedContext(),
						android.R.layout.simple_list_item_1,
						android.R.id.text1, cs), this);
		
		title = (EditText)findViewById(R.id.title);
		content = (EditText)findViewById(R.id.content);
		save = (Button)findViewById(R.id.save);
		save.setOnClickListener(this);
		Intent i = getIntent();
		update = i.getBooleanExtra("update", false);
		if(update)
		{
			updateid = i.getIntExtra("id", 0);
			title.setText(i.getStringExtra("title"));
			content.setText(i.getStringExtra("content"));
			alselected = tagdao.getTDByTarget(dairydao.getById(updateid).getTarget());
		}
	}

	@Override
	public void onRestoreInstanceState(Bundle savedInstanceState)
	{
		// Restore the previously serialized current dropdown position.
		if (savedInstanceState.containsKey(STATE_SELECTED_NAVIGATION_ITEM))
		{
			getActionBar().setSelectedNavigationItem(
					savedInstanceState.getInt(STATE_SELECTED_NAVIGATION_ITEM));
		}
	}

	@Override
	public void onSaveInstanceState(Bundle outState)
	{
		// Serialize the current dropdown position.
		outState.putInt(STATE_SELECTED_NAVIGATION_ITEM, getActionBar()
				.getSelectedNavigationIndex());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		Log.i("optionCreate",menu.toString());
		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.write_dairy, menu);
		
		tagdao = new TagDAO(this);
		tlist = tagdao.getAll();
		for(int i = 0;i<tlist.size();i++)
			menu.add(0, tlist.get(i).getId(), i, tlist.get(i).getName());
		return true;
	}
	
	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		Log.i("option",item.toString());
		if(tselected.get(Integer.valueOf(item.getItemId()))!=null)
		{
			item.setTitle(tselected.get(item.getItemId()));
			tselected.remove(item.getItemId());
		}
		else
		{
			tselected.put(item.getItemId(), item.getTitle().toString());
			item.setTitle("● "+item.getTitle());
		}
		return super.onOptionsItemSelected(item);
	}

	//section某项被选择所触发的selected事件
	@Override
	public boolean onNavigationItemSelected(int position, long id)
	{
		// When the given dropdown item is selected, show its contents in the
		// container view.
		Log.i("item","position:"+String.valueOf(position)+"  id:"+String.valueOf(id));
/*		Fragment fragment = new DummySectionFragment();
		Bundle args = new Bundle();
		args.putInt(DummySectionFragment.ARG_SECTION_NUMBER, position + 1);
		fragment.setArguments(args);
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.container, fragment).commit();*/
		if(position==0)
			cselected = 0;
		else
			cselected = clist.get(position-1).getId();
		return true;
	}

	/**
	 * A dummy fragment representing a section of the app, but that simply
	 * displays dummy text.
	 */
	public static class DummySectionFragment extends Fragment
	{
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		public static final String ARG_SECTION_NUMBER = "section_number";

		public DummySectionFragment()
		{
		}
		
		//section 选择后所触发的事件
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState)
		{
			// Create a new TextView and set its text to the fragment's section
			// number argument value.
/*			TextView textView = new TextView(getActivity());
			textView.setGravity(Gravity.CENTER);
			textView.setText(Integer.toString(getArguments().getInt(
					ARG_SECTION_NUMBER)));
			return textView;*/
			return null;
		}
	}

	@Override
	public void onClick(View v)
	{
		if(content.getText().toString().trim()==""||title.getText().toString().trim()=="")
		{
			Toast.makeText(this,"请输入完整内容", Toast.LENGTH_SHORT).show();
			return;
		}
		else
		{
			Dairy dairy = new Dairy(title.getText().toString().trim(),content.getText().toString().trim(),
					0,makeTarget(),makeTime(),cselected,0);
			if(!update)
			{
				dairydao.add(dairy);
				tagdao.addTD(tselected, dairy.getTarget());
				Toast.makeText(this,"保存成功", Toast.LENGTH_SHORT).show();
			}else
			{
				tagdao.deleteTD(alselected);
				tagdao.addTD(tselected,dairy.getTarget());
				dairy.setId(updateid);
				dairydao.updateById(dairy);
				Toast.makeText(this,"修改成功", Toast.LENGTH_SHORT).show();
			}
			Intent intent = new Intent(this,DairyListActivity.class);
			this.finish();
			startActivity(intent);
		}
	}
	
	public String makeTime()
	{
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);//年
		int month = calendar.get(Calendar.MONTH)+1;//月
		int day = calendar.get(Calendar.DAY_OF_MONTH);//日
		int hour = calendar.get(Calendar.HOUR_OF_DAY);//时
		int minute = calendar.get(Calendar.MINUTE);//分
		return ""+year+"-"+month+"-"+day+" / "+hour+":"+minute;
	}
	
	public String makeTarget()
	{
		Date date = new Date();
		Random rd = new Random();
		return String.valueOf(date.getTime())+String.valueOf(rd.nextInt()%10000);
	}
	
	
	//返回new中有，old中没有的Tag
	public Hashtable<Integer,String> compareT(Hashtable<Integer,String> oldh,Hashtable<Integer,String> newh)
	{
		Hashtable<Integer,String> rt = new Hashtable<Integer,String>();
		Iterator<Entry<Integer, String>> newi = newh.entrySet().iterator();
		while(newi.hasNext())
		{
			Map.Entry<Integer,String> entry = (Map.Entry<Integer,String>)newi.next();
			if(oldh.get(entry.getKey())==null)
			{
				rt.put(entry.getKey(), entry.getValue());
			}
		}
		return rt;
	}
	
}
