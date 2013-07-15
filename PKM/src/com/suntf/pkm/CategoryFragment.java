package com.suntf.pkm;

import java.util.ArrayList;

import com.suntf.pkm.dao.CategoryDAO;
import com.suntf.pkm.dummy.DummyContent;
import com.suntf.pkm.model.Category;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class CategoryFragment extends Fragment 
implements GridView.OnItemClickListener,GridView.OnItemSelectedListener 
{
	public static final String ARG_ITEM_ID = "item_id";
	private DummyContent.DummyItem mItem;
	
	private CategoryDAO cagetorgdao;
	private ArrayList<Category> categorys;
	private int size;
	
	private GridView gridview;
	
	private ArrayList<String> list = new ArrayList();
	
	public CategoryFragment()
	{		
	}

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		if (getArguments().containsKey(ARG_ITEM_ID))
		{
			mItem = DummyContent.ITEM_MAP.get(getArguments().getString(
					ARG_ITEM_ID));
		}
		cagetorgdao = new CategoryDAO(getActivity());
		categorys = cagetorgdao.getAll();
		list.add("默认");
		for(int i = 0;i<categorys.size();i++)
			list.add(categorys.get(i).getName());
		list.add("添加");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		View rootView = inflater.inflate(R.layout.category,
				container, false);

		gridview = (GridView)rootView.findViewById(R.id.grid);
		BaseAdapter adapter = new BaseAdapter()
		{

			@Override
			public int getCount()
			{
				return list.size();
			}

			@Override
			public Object getItem(int position)
			{
				return list.get(position);
			}

			@Override
			public long getItemId(int position)
			{
				return position;
			}

			@Override
			public View getView(int position, View convertView, ViewGroup parent)
			{
/*				View v = convertView;
				if(v==null)
				{
		    		LayoutInflater vi=(LayoutInflater)getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
					v=vi.inflate(R.layout.category_item, parent);
					v.setClickable(true);
				}*/
				TextView tex = new TextView(getActivity());
				// 获取文本宽度
				tex.setWidth((int) getResources().getDimension(R.dimen.width));
				// 获取文本高度
				tex.setHeight((int) getResources().getDimension(R.dimen.height));
				if(position==list.size()-1)
				{
//					Drawable a = getActivity().getResources().getDrawable(R.drawable.bgb);
					tex.setBackgroundResource(R.color.gainsboro);
//					return tex;
				}
				else
					tex.setBackgroundResource(R.color.gray);
				// 获取文本
				tex.setText(list.get(position));
				// 获取文本背景
//				tex.setBackgroundDrawable(getActivity().getResources().getDrawable(R.drawable.bga));

				// 获取文本字体大小
				tex.setTextSize(20);
				// 定义文本样式
				tex.setGravity(Gravity.CENTER);
				// 返回值
				return tex;
			}
			
		};
		gridview.setAdapter(adapter);
		gridview.setOnItemClickListener(this);
		return rootView;
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3)
	{
		if(arg2 == list.size()-1)//添加
		{
			createCView();
		}
		else
		{
			int id=0;
			if(arg2!=0)
				id = categorys.get(arg2-1).getId();
			Intent intent = new Intent(getActivity(),DairyListActivity.class);
			intent.putExtra("cid", id);
			startActivity(intent);
		}
	}

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3)
	{
		
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0)
	{
		// TODO Auto-generated method stub
		
	}
	
	public void createCView()
	{
		LinearLayout addform = (LinearLayout)getActivity().getLayoutInflater().inflate(R.layout.category_add,null);
		AlertDialog.Builder builder = new Builder(this.getActivity());
		builder.setTitle("添加分类");
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
					cagetorgdao.add(cname);
					Intent i = new Intent(getActivity(),CategoryActivity.class);
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
}
