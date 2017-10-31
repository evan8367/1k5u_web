package com.zhenyulaw.jf.common.util;

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

public class EncryptionUtils {
	public final static String SHA1(String plainString) {
		// SHA1加密
		String sha1Str = DigestUtils.sha1Hex(plainString);
		return sha1Str;
	}

	public final static String Base64Encryption(String plainString) {
		byte[] base64Byte = null;
		String base64Str = "";
		try {
			base64Byte = Base64.encodeBase64(plainString.getBytes("utf-8"), true);
			base64Str = new String(base64Byte);
			System.out.println("Base64加密为：" + base64Str);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return base64Str;
	}
}
