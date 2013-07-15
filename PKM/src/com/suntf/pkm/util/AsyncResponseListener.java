package com.suntf.pkm.util;

import org.apache.http.HttpEntity;

/** 
 * The call back interface for   
 *  
 * @author bright_zheng 
 * 
 */  
public interface AsyncResponseListener {  
    /** Handle successful response */  
    public void onResponseReceived(HttpEntity response);  
      
    /** Handle exception */  
    public void onResponseReceived(Throwable response);  
} 
