package com.suntf.pkm.util;

import org.apache.http.HttpRequest;

public class InputHolder{  
    private HttpRequest request;  
    private AsyncResponseListener responseListener;  
      
    public InputHolder(HttpRequest request, AsyncResponseListener responseListener){  
        this.request = request;  
        this.responseListener = responseListener;  
    }  
      
      
    public HttpRequest getRequest() {  
        return request;  
    }  
  
    public AsyncResponseListener getResponseListener() {  
        return responseListener;  
    }  
} 
