package com.zhaisoft.lib.utils;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;


import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * 此类暂时不使用
 *
 */
public class AES {
	private static SecretKey secretKey = null;// key对象
	private static Cipher cipher = null; // 私鈅加密对象Cipher
	// private static String keyString = "anydatalbsaeskey";// 密钥
	// private static String AES_KEY = "jududaka2015aesk";// 密钥

	private static String AES_KEY = "111111";

	static {
		try {
			secretKey = new SecretKeySpec(AES_KEY.getBytes(), "AES");//
			/* 获得一个私鈅加密类Cipher，DESede是算法，ECB是加密模式，PKCS5Padding是填充方式 */
			cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		} catch (Exception e) {
		}
	}

	/**
	 * 加密
	 * 
	 * @param message Base64
	 * @return
	 */
	public static String encrypt(String message) {
		String result = ""; // AES加密字符串
		String newResult = "";// 去掉换行符后的加密字符串
		try {
			cipher.init(Cipher.ENCRYPT_MODE, secretKey); // 设置工作模式为加密模式，给出密钥
			byte[] resultBytes = cipher.doFinal(message.getBytes("UTF-8")); // 正式执行加密操作
			BASE64Encoder enc = new BASE64Encoder();
			result = enc.encode(resultBytes);// 进行BASE64编码
			newResult = filter(result); // 去掉加密串中的换行符
		} catch (Exception e) {
		}
		return newResult;
	}

	/**
	 * 解密
	 * 
	 * @param message
	 * @return
	 * @throws Exception
	 */
	public static String decrypt(String message) throws Exception {
		String result = "";
		try {
			BASE64Decoder dec = new BASE64Decoder();
			byte[] messageBytes = dec.decodeBuffer(message); // 进行BASE64编码
			cipher.init(Cipher.DECRYPT_MODE, secretKey); // 设置工作模式为解密模式，给出密钥
			byte[] resultBytes = cipher.doFinal(messageBytes);// 正式执行解密操作
			result = new String(resultBytes, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 去掉加密字符串换行符
	 * 
	 * @param str
	 * @return
	 */
	public static String filter(String str) {
		String output = "";
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < str.length(); i++) {
			int asc = str.charAt(i);
			if (asc != 10 && asc != 13) {
				sb.append(str.subSequence(i, i + 1));
			}
		}
		output = new String(sb);
		return output;
	}

	/**
	 * 加密解密测试
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			// String strText = "0000-00-00";
			// String deseResult = encrypt(strText);//加密
			// System.out.println("加密结果："+deseResult);

			String desdResult = decrypt("xXudO37rpXl9B5I+uuF1Jw==");// 解密
			System.out.println("解密结果：" + desdResult);
			// System.out.println(desdResult.length());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
