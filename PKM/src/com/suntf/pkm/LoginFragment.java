package com.suntf.pkm;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.suntf.pkm.dummy.DummyContent;
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
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginFragment extends Fragment implements View.OnClickListener
{
	public static final String ARG_ITEM_ID = "item_id";
	private DummyContent.DummyItem mItem;
	private EditText name;
	private EditText password;
	
	public LoginFragment()
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
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		if(Constant.LOGIN)
		{
			View rootView = inflater.inflate(R.layout.userinfo,
					container, false);
			fillUserInfo(rootView,Constant.name,Constant.dairy_count,Constant.sh_count);
/*			((TextView)rootView.findViewById(R.id.linearlayoutuserinfot1)).setText(Constant.name);
			((TextView)rootView.findViewById(R.id.linearlayoutuserinfot2)).setText(String.valueOf(Constant.dairy_count));
			((TextView)rootView.findViewById(R.id.linearlayoutuserinfot3)).setText(String.valueOf(Constant.sh_count));
			Button ufobtn = (Button)rootView.findViewById(R.id.userinfobtn);
			ufobtn.setTag(3);
			ufobtn.setOnClickListener(this);*/
			return rootView;
		}		
		View rootView = inflater.inflate(R.layout.login,
				container, false);
/*		if (mItem != null)
		{
			int value = Integer.valueOf(mItem.id);
		}*/
		
		name = (EditText)rootView.findViewById(R.id.uid);
		password = (EditText)rootView.findViewById(R.id.pwd);
		
		Button login = (Button)rootView.findViewById(R.id.login);
		login.setTag(1); 
		login.setOnClickListener(this);
		
		Button register = (Button)rootView.findViewById(R.id.register);	
		register.setTag(2);
		register.setOnClickListener(this);
		
		return rootView;
	}

	@Override
	public void onClick(View v)
	{
		int tag = (Integer)v.getTag();
		switch (tag){
		case 1://login button
			try
			{
				loginMethod();
			} catch (Exception e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 2://register button
			Intent res = new Intent(getActivity(),RegisterActivity.class);
			startActivity(res);
			break;
		case 3:
			Constant.LOGIN = false;
			Intent login = new Intent(getActivity(),LoginActivity.class);
			getActivity().finish();
			startActivity(login);
		}
	}
	
	public void loginMethod() throws Exception
	{
		if(name.getText().toString()==""||password.getText().toString()=="")
		{
			Toast.makeText(getActivity(),"请填入完整信息", Toast.LENGTH_SHORT).show();
			return;
		}
		HashMap<String, String> map = new HashMap<String, String>();
		String url = "http://10.0.2.2:8080/PKMServer/LoginServlet";
		map.put("name", name.getText().toString().trim());
		map.put("pwd", password.getText().toString().trim());							
		HttpPost hp = makeHttpPost(url,map);
		
		AsyncHttpClient.sendRequest(this.getActivity(), hp,   
                new AbstractAsyncResponseListener(AbstractAsyncResponseListener.RESPONSE_TYPE_JSON_OBJECT){    
            @Override  
            protected void onSuccess(JSONObject response){  
                Log.i("LoginTAG", "UploadAndMatch.onSuccess()...");  
                Log.i("LoginTAG",response.toString());
                JSONObject candidate = response;  
                if(candidate!=null && candidate.length()>0){  
                    try
					{
                    	if(candidate.getString("status").equalsIgnoreCase("failed"))
                    	{
                            Toast.makeText(getActivity(),"用户名或密码错误", Toast.LENGTH_SHORT).show(); 
                    		return;
                    	}
						Activity activity = getActivity();
                    	String name = candidate.getString("name");
						int dairy_count = candidate.getInt("dairy_count");
						int sh_count = candidate.getInt("sh_count");
						activity.setContentView(R.layout.userinfo);
						TextView tv1 = (TextView)activity.findViewById(R.id.linearlayoutuserinfot1);
						tv1.setText(name);
						Constant.name = name;
						TextView tv2 = (TextView)activity.findViewById(R.id.linearlayoutuserinfot2);
						tv2.setText(String.valueOf(dairy_count));
						Constant.dairy_count = dairy_count;
						TextView tv3 = (TextView)activity.findViewById(R.id.linearlayoutuserinfot3);
						tv3.setText(String.valueOf(sh_count));
						Constant.sh_count = sh_count;
						Button btn = (Button)activity.findViewById(R.id.userinfobtn);
						btn.setOnClickListener(new OnClickListener(){
							@Override
							public void onClick(View v)
							{
								Intent login = new Intent(getActivity(),LoginActivity.class);
								getActivity().finish();
								startActivity(login);
							}});
						Constant.LOGIN = true;
					} catch (JSONException e)
					{
						e.printStackTrace();
					}                     
                    //update UI  
                }else{  
                    Log.i("LoginTAG", "No HIT!");                        
                    Toast.makeText(getActivity(),"登陆失败", Toast.LENGTH_SHORT).show(); 
                }  
            }              
            @Override  
            protected void onFailure(Throwable e) {  
                Log.e("LoginTAG", "UploadAndMatch.onFailure(), error: " + e.getMessage(), e);  
                //update UI              
                Toast.makeText(getActivity(),"登陆失败", Toast.LENGTH_SHORT).show(); 
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
	
	public void fillUserInfo(View rootView,String name,int dcount,int scount)
	{
		((TextView)rootView.findViewById(R.id.linearlayoutuserinfot1)).setText(Constant.name);
		((TextView)rootView.findViewById(R.id.linearlayoutuserinfot2)).setText(String.valueOf(Constant.dairy_count));
		((TextView)rootView.findViewById(R.id.linearlayoutuserinfot3)).setText(String.valueOf(Constant.sh_count));
		Button ufobtn = (Button)rootView.findViewById(R.id.userinfobtn);
		ufobtn.setTag(3);
		ufobtn.setOnClickListener(this);
	}
	
}
