package com.suntf.pkm;

import java.util.ArrayList;
import java.util.HashMap;

import com.suntf.pkm.dao.TagDAO;
import com.suntf.pkm.model.Dairy;
import com.suntf.pkm.model.Tag;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class TagFragment extends Fragment implements AdapterView.OnItemClickListener,
AdapterView.OnItemLongClickListener
{
	private ListView list;
	private TagDAO tagdao; 
	private ArrayList<Tag> tags;
	
	public TagFragment()
	{
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		tagdao = new TagDAO(getActivity());
		View rootView = inflater.inflate(R.layout.activity_tag,
				container, false);
		Button creatnt = (Button)rootView.findViewById(R.id.ntbt);
		creatnt.setOnClickListener(btnlistener);
		list = (ListView)rootView.findViewById(R.id.listViewTag);
		list.setOnItemClickListener(this);
		list.setOnItemLongClickListener(this);
		tags = tagdao.getAll();
		if(tags.size()==0)
			return rootView;
		SimpleAdapter listAdapter = new SimpleAdapter(this.getActivity(),makeList(tags), 
				R.layout.taglist,new String[]{"name","count"},new int[]{R.id.tagname,R.id.tagcount});
		list.setAdapter(listAdapter);
		return rootView;
	}

	


	@Override
	public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int arg2,
			long arg3)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3)
	{
		// TODO Auto-generated method stub
		
	}
	
	//创建tag的onclicklistener
	private OnClickListener btnlistener = new OnClickListener(){
		@Override
		public void onClick(View v)
		{
			LinearLayout addform = (LinearLayout)getActivity().getLayoutInflater().inflate(R.layout.category_add,null);
			AlertDialog.Builder builder = new Builder(getActivity());
			builder.setTitle("添加标签");
			builder.setView(addform);
			builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which)
				{
					AlertDialog ad = (AlertDialog)dialog;
					String cname = ((EditText)ad.findViewById(R.id.addcategory)).getText().toString();
					if(cname=="")
						Toast.makeText(getActivity(),"请输入内容", Toast.LENGTH_SHORT).show();
					else
					{
						tagdao.addTag(cname);
						Intent i = new Intent(getActivity(),TagActivity.class);
						getActivity().finish();
						startActivity(i);
					}
					dialog.dismiss();
				}	
			});
			builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which)
				{
					dialog.dismiss();
				}
			});
			builder.create().show();
		}
	};	
	
	public ArrayList<HashMap<String,Object>> makeList(ArrayList<Tag> tags)
	{
		ArrayList<HashMap<String,Object>> listItem = new ArrayList<HashMap<String,Object>>();
		for(int i = 0;i<tags.size();i++)
		{
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("name", tags.get(i).getName());
			map.put("count", tagdao.getDCountById(tags.get(i).getId()));
			listItem.add(map);
		}
		return listItem;
	}
	
}
