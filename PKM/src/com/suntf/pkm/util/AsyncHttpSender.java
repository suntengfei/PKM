package com.suntf.pkm.util;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.BufferedHttpEntity;

import android.os.AsyncTask;

public class AsyncHttpSender extends AsyncTask<InputHolder, Void, OutputHolder> {  
	  
    @Override  
    protected OutputHolder doInBackground(InputHolder... params) {  
        HttpEntity entity = null;  
        InputHolder input = params[0];  
        try {  
            HttpResponse response = AsyncHttpClient.getClient().execute((HttpUriRequest) input.getRequest());  
            StatusLine status = response.getStatusLine();  
              
            if(status.getStatusCode() >= 300) {  
                return new OutputHolder(  
                        new HttpResponseException(status.getStatusCode(), status.getReasonPhrase()),  
                        input.getResponseListener());  
            }  
              
            entity = response.getEntity();  
//            Log.i(TAG, "isChunked:" + entity.isChunked());  
            if(entity != null) {  
                try{  
                    entity = new BufferedHttpEntity(entity);  
                }catch(Exception e){  
//                    Log.e(<span style="background-color: #ffffff;">TAG</span>, e.getMessage(), e);  
                    //ignore?  
                }  
            }             
        } catch (ClientProtocolException e) {  
//            Log.e(<span style="background-color: #ffffff;">TAG</span>, e.getMessage(), e);  
            return new OutputHolder(e, input.getResponseListener());  
        } catch (IOException e) {  
//            Log.e(<span style="background-color: #ffffff;">TAG</span>, e.getMessage(), e);  
            return new OutputHolder(e, input.getResponseListener());  
        }  
        return new OutputHolder(entity, input.getResponseListener());  
    }  
      
    @Override  
    protected void onPreExecute(){  
//        Log.i(<span style="background-color: #ffffff;">TAG</span>, "AsyncHttpSender.onPreExecute()");  
        super.onPreExecute();  
    }  
      
    @Override  
    protected void onPostExecute(OutputHolder result) {  
 //       Log.i(<span style="background-color: #ffffff;">TAG</span>, "AsyncHttpSender.onPostExecute()");  
        super.onPostExecute(result);  
          
        if(isCancelled()){  
 //           Log.i(<span style="background-color: #ffffff;">TAG</span>, "AsyncHttpSender.onPostExecute(): isCancelled() is true");  
            return; //Canceled, do nothing  
        }  
          
        AsyncResponseListener listener = result.getResponseListener();  
        HttpEntity response = result.getResponse();  
        Throwable exception = result.getException();  
        if(response!=null){  
//            Log.i(<span style="background-color: #ffffff;">TAG</span>, "AsyncHttpSender.onResponseReceived(response)");  
            listener.onResponseReceived(response);  
        }else{  
 //           Log.i(<span style="background-color: #ffffff;">TAG</span>, "AsyncHttpSender.onResponseReceived(exception)");  
            listener.onResponseReceived(exception);  
        }  
    }  
      
    @Override  
    protected void onCancelled(){  
  //      Log.i(<span style="background-color: #ffffff;">TAG</span>, "AsyncHttpSender.onCancelled()");  
        super.onCancelled();  
        //this.isCancelled = true;  
    }  
}