package com.shp.dev.network.common.util;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @CreateBy: shp
 * @version：1.0
 * @Description: TODO MD5加密
 * @CreateTime: 2020-07-05 10:23
 */
public class MD5 {

	public static void main(String[] args) {
		System.out.println(MD5.md5("123"));
	}

	/**
	 * 加密解密算法 执行一次加密，执行两次解密
	 */
	public static String convertMD5(String inStr){
		char[] a = inStr.toCharArray();
		for (int i = 0; i < a.length; i++){
			a[i] = (char) (a[i] ^ 't');
		}
		return new String(a);

	}

	public static String md5(String buffer){
		String string = null;
		char hexDigist[] = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
			md.update(buffer.getBytes());
			byte[] datas = md.digest(); //16个字节的长整数

			char[] str = new char[2*16];
			int k = 0;

			for(int i=0;i<16;i++){
				byte b   = datas[i];
				str[k++] = hexDigist[b>>>4 & 0xf];//高4位
				str[k++] = hexDigist[b & 0xf];//低4位
			}
			string = new String(str);
		} catch (NoSuchAlgorithmException e){
			e.printStackTrace();
		}
		return string;
	}

}
