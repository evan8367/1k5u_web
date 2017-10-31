package com.zhenyulaw.jf.common.util;

public class MoneyUtils {
	public static Long convertToCent(Double amount) {
		Integer fraction = (amount+"").length()-(amount+"").indexOf(".")-1;
		if(fraction > 2)
			throw new RuntimeException("只能保留两位小数");
		
		return Math.round(amount*100);
	}
	
	public static Double convertToYuan(Long amount) {
		return ((double)amount)/100;
	}
}
