package com.suntf.pkm.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;


/** 
 * Abstract Async Response Listener implementation 
 *  
 * Subclass should implement at lease two methods. 
 * 1. onSuccess() to handle the corresponding successful response object 
 * 2. onFailure() to handle the exception if any 
 *  
 * @author bright_zheng 
 * 
 */  
public abstract class AbstractAsyncResponseListener implements AsyncResponseListener{  
    public static final int RESPONSE_TYPE_STRING = 1;  
    public static final int RESPONSE_TYPE_JSON_ARRAY = 2;  
    public static final int RESPONSE_TYPE_JSON_OBJECT = 3;  
    public static final int RESPONSE_TYPE_STREAM = 4;  
    private int responseType;  
      
    public AbstractAsyncResponseListener(){  
        this.responseType = RESPONSE_TYPE_STRING; // default type  
    }  
      
    public AbstractAsyncResponseListener(int responseType){  
        this.responseType = responseType;  
    }  
      
    public void onResponseReceived(HttpEntity response){  
        try {  
            switch(this.responseType){  
                case RESPONSE_TYPE_JSON_ARRAY:{  
                    String responseBody = EntityUtils.toString(response);     
 //                   Log.i(<span style="background-color: #ffffff;">TAG</span>, "Return JSON String: " + responseBody);  
                    JSONArray json = null;  
                    if(responseBody!=null && responseBody.trim().length()>0){  
                        json = (JSONArray) new JSONTokener(responseBody).nextValue();  
                    }  
                    onSuccess(json);  
                    break;  
                }  
                case RESPONSE_TYPE_JSON_OBJECT:{  
                    String responseBody = EntityUtils.toString(response);     
//                    Log.i(<span style="background-color: #ffffff;">TAG</span>, "Return JSON String: " + responseBody);  
                    JSONObject json = null;  
                    if(responseBody!=null && responseBody.trim().length()>0){  
                        json = (JSONObject) new JSONTokener(responseBody).nextValue();  
                    }  
                    onSuccess(json);      
                    break;  
                }  
                case RESPONSE_TYPE_STREAM:{  
                    onSuccess(response.getContent());  
                    break;  
                }  
                default:{  
                    String responseBody = EntityUtils.toString(response);  
                    onSuccess(responseBody);  
                }           
            }  
        } catch(IOException e) {  
            onFailure(e);  
        } catch (JSONException e) {  
            onFailure(e);  
        }     
    }  
      
    public void onResponseReceived(Throwable response){  
        onFailure(response);  
    }  
      
    protected void onSuccess(JSONArray response){}  
      
    protected void onSuccess(JSONObject response){}  
      
    protected void onSuccess(InputStream response){}  
      
    protected void onSuccess(String response) {}  
  
    protected void onFailure(Throwable e) {}  
}  