package com.zhenyulaw.jf.common.util;

import java.util.UUID;

public class UUIDUtils {
	/**  
     * 生成32位编码  
     * @return string  
     */    
    public static String getUUID(){
        String uuid = UUID.randomUUID().toString().trim();    
        return uuid;    
    }
    
    /**  
     * 生成32位编码  
     * @return string  
     */    
    public static String getFileName(){
        String uuid = getUUID().replace("-", "");   
        return uuid;    
    }
}
