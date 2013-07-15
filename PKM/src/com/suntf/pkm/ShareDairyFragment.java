package com.suntf.pkm;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.suntf.pkm.model.Dairy;
import com.suntf.pkm.model.ShareDairy;
import com.suntf.pkm.util.AbstractAsyncResponseListener;
import com.suntf.pkm.util.AsyncHttpClient;
import com.suntf.pkm.util.Constant;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class ShareDairyFragment extends Fragment implements AdapterView.OnItemClickListener,
AdapterView.OnItemLongClickListener
{

	private ArrayList<ShareDairy> sdairys = new ArrayList<ShareDairy>();
	
	public ShareDairyFragment(){}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		View rootView = inflater.inflate(R.layout.sharelist,
				container, false);
		ListView slist = (ListView)rootView.findViewById(R.id.listViews1);
		try
		{
			loginMethod(20,new Date().getTime());
		} catch (Exception e)
		{
			Toast.makeText(getActivity(),"获取失败", Toast.LENGTH_SHORT).show(); 
		}
		return rootView;
	}
	
	
	public void loginMethod(int count,long etime) throws Exception
	{
		HashMap<String, String> map = new HashMap<String, String>();
		String url = "http://10.0.2.2:8080/PKMServer/ShareServlet";
		map.put("count", String.valueOf(count));
		map.put("etime", String.valueOf(etime));							
		HttpPost hp = makeHttpPost(url,map);
		
		AsyncHttpClient.sendRequest(this.getActivity(), hp,   
                new AbstractAsyncResponseListener(AbstractAsyncResponseListener.RESPONSE_TYPE_JSON_ARRAY){    
            @Override  
            protected void onSuccess(JSONArray response){  
                Log.i("LoginTAG", "UploadAndMatch.onSuccess()...");  
                Log.i("LoginTAG",response.toString());
                JSONArray candidate = response;  
                if(candidate!=null && candidate.length()>0){  
                    try
					{
						for(int i = 0;i<candidate.length();i++)
						{
							sdairys.add(new ShareDairy((JSONObject)candidate.get(i)));
						}
						
						ListView slist = (ListView)getActivity().findViewById(R.id.listViews1);
						SimpleAdapter listAdapter = new SimpleAdapter(getActivity(),makeList(sdairys), 
								R.layout.share_item,new String[]{"title","name","time","abstract"}
								,new int[]{R.id.Text01,R.id.Text02,R.id.Text03,R.id.Text04}); 
						slist.setAdapter(listAdapter);
						slist.setOnItemClickListener(ShareDairyFragment.this);
					} catch (JSONException e)
					{
						Toast.makeText(getActivity(),"获取共享笔记时出现错误", Toast.LENGTH_SHORT).show(); 
						e.printStackTrace();
					}                     
                    //update UI  
                }else{  
                    Log.i("LoginTAG", "No HIT!");                        
                    Toast.makeText(getActivity(),"已无新共享笔记", Toast.LENGTH_SHORT).show(); 
                }  
            }              
            @Override  
            protected void onFailure(Throwable e) {  
                Log.e("LoginTAG", "UploadAndMatch.onFailure(), error: " + e.getMessage(), e);  
                //update UI              
                Toast.makeText(getActivity(),"获取失败", Toast.LENGTH_SHORT).show(); 
            }                
        });		
	}

	
	public HttpPost makeHttpPost(String url,HashMap<String, String> map) throws Exception
	{
		HttpPost post = new HttpPost(url);
		List<NameValuePair> params = 
				new ArrayList<NameValuePair>();
			for(String key : map.keySet())
			{
				//封装请求参数
				params.add(new BasicNameValuePair(key 
					, map.get(key)));
			}
			// 设置请求参数
			post.setEntity(new UrlEncodedFormEntity(
				params));
		return post;
	}
	
	public ArrayList<HashMap<String,Object>> makeList(ArrayList<ShareDairy> dairys)
	{
		ArrayList<HashMap<String,Object>> listItem = new ArrayList<HashMap<String,Object>>();
		for(int i = 0;i<dairys.size();i++)
		{
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("title", dairys.get(i).getTitle());
			map.put("name", dairys.get(i).getName());
			map.put("time", dairys.get(i).getTime());
			String abstractt=dairys.get(i).getContent();
			if(abstractt.length()>10)
				abstractt = abstractt.substring(0, 10);
			map.put("abstract", abstractt);
			listItem.add(map);
		}
		return listItem;
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
		Intent readSIntent = new Intent(this.getActivity(),ReadShareActivity.class);
		if(sdairys==null)
			return;
		readSIntent.putExtra("title", sdairys.get(arg2).getTitle());
		readSIntent.putExtra("name", sdairys.get(arg2).getName());
		readSIntent.putExtra("time", sdairys.get(arg2).getTime());
		readSIntent.putExtra("content", sdairys.get(arg2).getContent());
		startActivity(readSIntent);
	}
	
	
	
}
