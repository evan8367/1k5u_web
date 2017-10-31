package com.zhenyulaw.jf.common.config;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.zhenyulaw.jf.common.util.HttpUtils;

@Configuration
public class SmsConfig {
	
	@Autowired
	Properties propertiesConfig;
	public final void sendSms(String recNum, String paramString) {
		String host = "http://sms.market.alicloudapi.com";
	    String path = "/singleSendSms";
	    String method = "GET";
	    String appcode = propertiesConfig.getProperty("sms.appCode");
	    String signName = propertiesConfig.getProperty("sms.signName");
	    String templateCode = propertiesConfig.getProperty("sms.templateCode");
	    Map<String, String> headers = new HashMap<String, String>();
	    headers.put("Authorization", "APPCODE " + appcode);
	    Map<String, String> querys = new HashMap<String, String>();
	    querys.put("ParamString", paramString);
	    querys.put("RecNum", recNum);
	    querys.put("SignName", signName);
	    querys.put("TemplateCode", templateCode);
	    try {
	    	/**
	    	* 重要提示如下:
	    	* HttpUtils请从
	    	* https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
	    	* 下载
	    	*
	    	* 相应的依赖请参照
	    	* https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
	    	*/
	    	HttpResponse response =  HttpUtils.doGet(host, path, method, headers, querys);
	    	System.out.println(response.toString());
	    	//获取response的body
	    	//System.out.println(EntityUtils.toString(response.getEntity()));
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	}
}
