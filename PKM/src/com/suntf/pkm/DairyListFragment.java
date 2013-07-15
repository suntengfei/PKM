/**
 * 
 */
package com.suntf.pkm;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;


import com.suntf.pkm.dao.DairyDAO;
import com.suntf.pkm.dummy.DummyContent;
import com.suntf.pkm.model.Dairy;
import com.suntf.pkm.util.AbstractAsyncResponseListener;
import com.suntf.pkm.util.AsyncHttpClient;
import com.suntf.pkm.util.Constant;
import com.suntf.pkm.util.FileService;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author suntengfei
 *
 */
public class DairyListFragment extends Fragment implements AdapterView.OnItemClickListener,
AdapterView.OnItemLongClickListener
{
	public static final String ARG_ITEM_ID = "item_id";
	private DummyContent.DummyItem mItem;
	
	private ListView list;
	
	private DairyDAO dairydao; 
	private ArrayList<Dairy> dairys;
	private int selectedid=-1;
	
	public DairyListFragment()
	{
	}

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		if (getArguments().containsKey(ARG_ITEM_ID))
		{
			// Load the dummy content specified by the fragment
			// arguments. In a real-world scenario, use a Loader
			// to load content from a content provider.
			mItem = DummyContent.ITEM_MAP.get(getArguments().getString(
					ARG_ITEM_ID));
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		View rootView = inflater.inflate(R.layout.dairylist,
				container, false);

		// Show the dummy content as text in a TextView.
		if (mItem != null)
		{
			int value = Integer.valueOf(mItem.id);
		}
		
		Button creatnd = (Button)rootView.findViewById(R.id.ndbt);
		creatnd.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v)
			{
				Intent wdintent = new Intent(getActivity(),WriteDActivity.class);
				startActivity(wdintent);
			}
			
		});
		
		list = (ListView)rootView.findViewById(R.id.listView1);
		list.setOnItemClickListener(this);
		list.setOnItemLongClickListener(this);
		list.setOnCreateContextMenuListener(this);//ListView长按监听器
		Intent i = getActivity().getIntent();
		int cid = i.getIntExtra("cid", -1);
		dairydao = new DairyDAO(this.getActivity());
		if(cid==-1)
			dairys = dairydao.getAll();
		else
			dairys = dairydao.getByCategory(cid);
		if(dairys.size()==0)
			return rootView;
		SimpleAdapter listAdapter = new SimpleAdapter(this.getActivity(),makeList(dairys), 
				R.layout.mylist,new String[]{"title","time","abstract"},new int[]{R.id.Text01,R.id.Text02,R.id.Text03});
		list.setAdapter(listAdapter);
		return rootView;
	}
	


	public ArrayList<HashMap<String,Object>> makeList(ArrayList<Dairy> dairys)
	{
		ArrayList<HashMap<String,Object>> listItem = new ArrayList<HashMap<String,Object>>();
		for(int i = 0;i<dairys.size();i++)
		{
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("title", dairys.get(i).getTitle());
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
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3)
	{
		Intent readIntent = new Intent(this.getActivity(),ReadActivity.class);
		if(dairys==null)
			return;
		readIntent.putExtra("id", dairys.get(arg2).getId());
		startActivity(readIntent);
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int arg2,
			long arg3)
	{
		selectedid = arg2;
		return false;
	}
	
	
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo)
	{
		menu.setHeaderTitle("操作");
		menu.add(0,1,0,"删除");
		menu.add(0,2,0,"编辑");
		menu.add(0,3,0,"导出");
		menu.add(0,4,0,"分享");
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {//长按弹出菜单响应函数
		Log.i("selectedid : ",String.valueOf(selectedid));
		switch (item.getItemId())
		{
		case 1://删除
			dairydelete();
			break;
		case 2://编辑
			modify();
			break;
		case 3://导出
			export();
			break;
		case 4://共享
			try
			{share();} 
			catch (Exception e)
			{e.printStackTrace();}
			break;
		}
		return super.onContextItemSelected(item);
	}
	
	public void dairydelete()//删除dairy
	{
		AlertDialog.Builder builder = new Builder(this.getActivity());
		builder.setMessage("确认要删除出吗？");
		builder.setTitle("提示");
		builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
    		   int id = dairys.get(selectedid).getId();
    		   if(dairydao.deleteById(id)>0)
    		   {
    			   Toast.makeText(getActivity(),"删除成功", Toast.LENGTH_SHORT).show();
    			   dairys.remove(selectedid);
    			   list.setAdapter(new SimpleAdapter(getActivity(),makeList(dairys), 
    					   R.layout.mylist,new String[]{"title","time","abstract"}
    			   			,new int[]{R.id.Text01,R.id.Text02,R.id.Text03}));
    		   }
    		   else
    			   Toast.makeText(getActivity(),"删除失败", Toast.LENGTH_SHORT).show();	    		   
    		   dialog.dismiss();
			}
	    });
		builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
		   public void onClick(DialogInterface dialog, int which) {
			   dialog.dismiss();
		   }
		});
		builder.create().show();
	}
	
	public void export()
	{
		Dairy exdairy = dairys.get(selectedid);
		FileService service = new FileService(getActivity().getApplicationContext());
		try {
			//判断SDCard是否存在，并且可以读写
			if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
				String name = (exdairy.getTitle()+"_"+exdairy.getTarget()).trim();
				service.saveToSDCard(name,exdairy.getContent());
		
				Toast.makeText(getActivity().getApplicationContext(), "导出成功", Toast.LENGTH_SHORT).show();
			}else{
				Toast.makeText(getActivity().getApplicationContext(), "导出失败", Toast.LENGTH_SHORT).show();
			}				
		} 
				
		catch (Exception e) {
			Toast.makeText(getActivity().getApplicationContext(),"导出失败", Toast.LENGTH_SHORT).show();
			e.printStackTrace();
		}
	}
	
	public void modify()
	{
		Dairy mdairy = dairys.get(selectedid);
		Intent intent = new Intent(getActivity(),WriteDActivity.class);
		intent.putExtra("update", true);
		intent.putExtra("id", mdairy.getId());
		intent.putExtra("title", mdairy.getTitle());
		intent.putExtra("content", mdairy.getContent());
		startActivity(intent);
	}
	
	public void share() throws Exception
	{
		if(!Constant.LOGIN)
		{
			Toast.makeText(getActivity().getApplicationContext(),"请先登陆", 1).show();
			return;
		}
		Dairy mdairy = dairys.get(selectedid);
		HashMap<String, String> map = new HashMap<String, String>();
		String url = "http://10.0.2.2:8080/PKMServer/WantShareServlet";
		Log.i("LoginTAG","title :"+URLEncoder.encode(mdairy.getTitle(),"utf-8"));
		Log.i("LoginTAG","content :"+URLEncoder.encode(mdairy.getContent(),"utf-8"));
		map.put("title", URLEncoder.encode(mdairy.getTitle()));
		map.put("content", URLEncoder.encode(mdairy.getContent()));							
		HttpPost hp = makeHttpPost(url,map);
		AsyncHttpClient.sendRequest(this.getActivity(), hp,   
                new AbstractAsyncResponseListener(AbstractAsyncResponseListener.RESPONSE_TYPE_STRING){    
            @Override  
            protected void onSuccess(String response){  
                Log.i("LoginTAG", "UploadAndMatch.onSuccess()...");  
                Log.i("LoginTAG",response);
                if(response.trim().equalsIgnoreCase("success"))
                    Toast.makeText(getActivity(),"共享成功", Toast.LENGTH_SHORT).show(); 
                else if(response.equals("failed"))
                    Toast.makeText(getActivity(),"共享失败", Toast.LENGTH_SHORT).show(); 
                else if(response.trim().equals("error"))
                {
                	Toast.makeText(getActivity(),"共享时出现错误", Toast.LENGTH_SHORT).show();
                	Constant.LOGIN = false;
                }
            }              
            @Override  
            protected void onFailure(Throwable e) {  
                Log.e("LoginTAG", "UploadAndMatch.onFailure(), error: " + e.getMessage(), e);  
                //update UI              
                Toast.makeText(getActivity(),"共享失败", Toast.LENGTH_SHORT).show(); 
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
				params,HTTP.UTF_8));
		return post;
	}
	
}
