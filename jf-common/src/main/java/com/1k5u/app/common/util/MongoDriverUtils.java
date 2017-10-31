package com.zhenyulaw.jf.common.util;


import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;

import com.zhenyulaw.jf.common.config.PropertiesConfig;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MongoDriverUtils {
	private static MongoClient mongoClient;
	private static MongoDatabase mongoDatabase;
    

	public static void mongoInsert(String host, String table, Object obj) throws JsonProcessingException{
	      try{   
	       mongoClient = new MongoClient(host , 27017 );
	       mongoDatabase = mongoClient.getDatabase("adapi");  
	       MongoCollection<Document> collection = mongoDatabase.getCollection(table);
	       
	       
	       Map<String, Object> map = transBean2Map(obj);
	       Document document = new Document(map);
	       collection.insertOne(document);
	       
//	       System.out.println("Connect to database successfully");  
	       
	       }catch(Exception e){
	          e.printStackTrace();
	       } finally {
	    	   mongoClient.close();
		}
	  }
	
	  public static Map<String, Object> transBean2Map(Object obj) {  
	        if(obj == null){  
	            return null;  
	        }          
	        Map<String, Object> map = new HashMap<String, Object>();  
	        try {  
	            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());  
	            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();  
	            for (PropertyDescriptor property : propertyDescriptors) {  
	                String key = property.getName();  
	  
	                // 过滤class属性  
	                if (!key.equals("class")) {  
	                    // 得到property对应的getter方法  
	                    Method getter = property.getReadMethod();  
	                    Object value = getter.invoke(obj);  
	  
	                    map.put(key, value);  
	                }  
	  
	            }  
	        } catch (Exception e) {  
	            System.out.println("transBean2Map Error " + e);  
	        }  
	  
	        return map;  
	  
	    }
	  public static void transMap2Bean(Map<String, Object> map, Object obj) {  
		  
	        try {  
	            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());  
	            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();  
	  
	            for (PropertyDescriptor property : propertyDescriptors) {  
	                String key = property.getName();  
	  
	                if (map.containsKey(key)) {  
	                    Object value = map.get(key);  
	                    // 得到property对应的setter方法  
	                    Method setter = property.getWriteMethod();  
	                    setter.invoke(obj, value);  
	                }  
	  
	            }  
	  
	        } catch (Exception e) {  
	            System.out.println("transMap2Bean Error " + e);  
	        }  
	  
	        return;  
	  
	    }  
}
