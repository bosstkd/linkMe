/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.contact.contactFinderOne.fct;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
//import javax.servlet.http.HttpServletRequest;

import org.json.JSONException;
import org.json.JSONObject;
/**
 *
 * @author Amine
 */
public class ipAdresse {
    
 
    
  /*
  public static void main(String[] args) throws IOException{
    //JSONObject json = readJsonFromUrl("https://graph.facebook.com/19292868552");
      JSONObject json = readJsonFromUrl("https://api.ipdata.co/77.136.42.12?api-key=test");
    System.out.println(json.toString());
    System.out.println(json.get("city")+" "+json.get("country_name"));
  }
  */
    
  /*
  public String getClientIpAddress(){
      HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }
        System.out.println("ipAddress:" + ipAddress);
        return ipAddress;
  }
  
  public String getClientZone() throws IOException{
      return getZoneFormIpAddress(getClientIpAddress());
  }
  */
  
    private static String readAll(Reader rd) throws IOException {
    StringBuilder sb = new StringBuilder();
    int cp;
    while ((cp = rd.read()) != -1) {
      sb.append((char) cp);
    }
    return sb.toString();
  }

    
  public String getZoneFormIpAddress(String ip) throws IOException{
      String zone = "inconnu";
      try {
          JSONObject json = readJsonFromUrl("https://api.ipdata.co/"+ip+"?api-key=test");
          zone = json.get("country_name")+" "+json.get("city");
      } catch (Exception e) {
      }
        
      //  System.out.println(json.toString());
      
      return zone;
  }   
  
  
  public String getJsonCodeFromUrl(String url) throws IOException{
      JSONObject json = readJsonFromUrl(url);
      return json.toString();
  }
    
  public String getJsonValueByKey(String url, String key) throws IOException{
      JSONObject json = readJsonFromUrl(url);
      return (String) json.get(key);
  }
    
  public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
    InputStream is = new URL(url).openStream();
    try {
      BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
      String jsonText = readAll(rd);
      JSONObject json = new JSONObject(jsonText);
      return json;
    } finally {
      is.close();
    }
  }


    
    
}
