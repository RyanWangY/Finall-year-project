package yuan.project.utils;

import java.sql.ResultSet;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSetMetaData;



import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
 


import net.sf.json.JSONArray;

import net.sf.json.JSONException;

import net.sf.json.JSONObject;

public class JsonFormat {
	/** 
	 
     * ��resultSetת��ΪJSONObject 
 
     * @param rs 
 
     * @return 
 
     * @throws SQLException 
 
     * @throws JSONException 
     * 
     * @author yuan
 
     */  
  
    public static JSONObject resultSetToJsonObject(ResultSet rs) throws SQLException,JSONException   
  
    {   
  
       // json����  
  
        JSONObject jsonObj = new JSONObject();       
  
       // ��ȡ����   
  
       ResultSetMetaData metaData = rs.getMetaData();   
  
       int columnCount = metaData.getColumnCount();   
  
       // ����ResultSet�е�ÿ������   
  
        if (rs.next()) {   
  
            // ����ÿһ��   
  
            for (int i = 1; i <= columnCount; i++) {   
  
                String columnName =metaData.getColumnLabel(i);   
  
                String value = rs.getString(columnName);   
  
                jsonObj.put(columnName, value);   
  
            }     
  
        }  
  
       return jsonObj;   
  
    }  
  
    /** 
    
     * ��resultSetת��ΪJSON���� 
   
     * @param rs 
   
     * @return 
   
     * @throws SQLException 
   
     * @throws JSONException 
   
     */  
    
    public JSONArray resultSetToJsonArry(ResultSet rs) throws SQLException,JSONException   
    
    {   
    
       // json����   
    
       JSONArray array = new JSONArray();   
    
           
    
       // ��ȡ����   
    
       ResultSetMetaData metaData = rs.getMetaData();   
    
       int columnCount = metaData.getColumnCount();   
    
           
    
       // ����ResultSet�е�ÿ������   
    
        while (rs.next()) {   
    
            JSONObject jsonObj = new JSONObject();   
    
                
    
            // ����ÿһ��   
    
            for (int i = 1; i <= columnCount; i++) {   
    
                String columnName =metaData.getColumnLabel(i);   
    
                String value = rs.getString(columnName);   
    
                jsonObj.put(columnName, value);   
    
            }    
    
            array.add(jsonObj);    
    
        }   
    
           
    
       return array;   
    }

    public static final List<Object> RsToJson(ResultSet rs) {
		JsonObject element = null;
		JsonArray ja = new JsonArray();
		ResultSetMetaData rsmd = null;
		String columnName, columnValue = null;
		List<Object> list=new ArrayList<Object>();
		try {
			rsmd = rs.getMetaData();
			while (rs.next()) {
				element = new JsonObject();
				for (int i = 0; i < rsmd.getColumnCount(); i++) {
					columnName = rsmd.getColumnName(i + 1);
					columnValue = rs.getString(columnName);
					element.addProperty(columnName, columnValue);
				}
				ja.add(element);
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for (JsonElement jsonElement : ja) {
			list.add(jsonElement);
		}
		return list;
	}
}
