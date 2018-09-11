package com.limon.util;

import java.net.URLDecoder;
import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;

/**
 * 3DES加密工具类
 */
public class Des3 {

	/**
	 * 24字节的密钥
	 */
	// private static final byte[] keyBytes = { 0x10, 0x20, 0x3F, 0x58,
	// (byte) 0x88, 0x07, 0x30, 0x38, 0x28, 0x25, 0x79, 0x51, (byte) 0xCB,
	// (byte) 0xDD, (byte) 0xEF, 0x66, 0x78, 0x23, 0x76, (byte) 0x98,
	// (byte) 0x81, 0x40, 0x34, (byte) 0xE2 };
	/**
	 * 24字节的密钥
	 */
	private final static String secretKey = "bjcjdkejiyxgs1qaz2wsx123";
	/**
	 * 向量
	 */
	private final static String iv = "01237654";
	/**
	 * 加解密统一使用的编码方式
	 */
	private final static String encoding = "utf-8";

	/**
	 * @Title: bytesToHexString
	 * @Description: byte字节转换为16进制字符串
	 * @param @param src
	 * @param @return
	 * @return String
	 * @throws
	 */
	public static String bytesToHexStr(byte[] src) {
		StringBuilder stringBuilder = new StringBuilder("");
		if (src == null || src.length <= 0) {
			return null;
		}
		for (int i = 0; i < src.length; i++) {
			int v = src[i] & 0xFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				stringBuilder.append(0);
			}
			stringBuilder.append(hv);
		}
		return stringBuilder.toString();
	}

	/**
	 * @Title: Hex2Bytes
	 * @Description: 16进制字符串转换为byte字节
	 * @param @param hexString
	 * @param @return
	 * @return byte[]
	 * @throws
	 */
	public static byte[] Hex2Bytes(String hexString) {
		if (hexString == null || hexString.equals("")) {
			return null;
		}
		hexString = hexString.toUpperCase();
		int length = hexString.length() / 2;
		char[] hexChars = hexString.toCharArray();
		byte[] d = new byte[length];
		for (int i = 0; i < length; i++) {
			int pos = i * 2;
			d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
		}
		return d;
	}

	/**
	 * @Title: charToByte
	 * @Description: 字符转字节
	 * @param @param c
	 * @param @return
	 * @return byte
	 * @throws
	 */
	private static byte charToByte(char c) {
		return (byte) "0123456789ABCDEF".indexOf(c);
	}

	/**
	 * 3DES加密
	 * 
	 * @return
	 * @throws Exception
	 */
	public static byte[] encode(byte[] src) throws Exception {
		Key deskey = null;
		DESedeKeySpec spec = new DESedeKeySpec(secretKey.getBytes());
		SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
		deskey = keyfactory.generateSecret(spec);

		Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
		IvParameterSpec ips = new IvParameterSpec(iv.getBytes());
		cipher.init(Cipher.ENCRYPT_MODE, deskey, ips);
		byte[] encryptData = cipher.doFinal(src);
		return encryptData;
	}

	/**
	 * 3DES解密
	 * 
	 * @return
	 * @throws Exception
	 */
	public static byte[] decode(byte[] src) throws Exception {
		Key deskey = null;
		DESedeKeySpec spec = new DESedeKeySpec(secretKey.getBytes());
		SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
		deskey = keyfactory.generateSecret(spec);
		Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
		IvParameterSpec ips = new IvParameterSpec(iv.getBytes());
		cipher.init(Cipher.DECRYPT_MODE, deskey, ips);

		byte[] decryptData = cipher.doFinal(src);

		return decryptData;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {

			String szSrc = "This is a 3DES test. 测试 @#$%^&*(<>CVBNIKIUG654564";
			szSrc = "1231235fbb050a8339b08c99dccb1ca31402a2f7e0f19a9c25bec6cb7d14a1ce8388f1c467ac2e9857510fa6099d8fd811e8367904d7313bc6e7aaeccddfcd1648231c875c9c29e05d75e0b2609e93381f4b83cb1b843fcc86963779a8ca9fb792f06d6836807abe854ebccaba09133287c459306b18861f05b1840749952c6af989abe0ca4d1952dd082aae749e2112345112f611eb38454d3c8f176b62012520ecacfcc24b2b4b45a9d9a30dcdef886942644b8a52d9878e15af558f81b6a3a576e9ae6d2ec0ba13c4b339e0561c9c2b47cd7f12780ed4df40c4acdfbb5500b175523a987d6a008878c477395c66024b56d4c017279193c3e006f33ed587f95058413b2d3fd2ae5734f9fbb724b643b556b906157e7b05adc5bcf03995208845e84071bcd40786f1df3bc65c39ee5331b1f730fecc826b748e389b3517871ce7d87db2606f3f0833ffa8f739afdb504e78964cc32e90010fc10f";
			System.out.println(URLDecoder.decode(szSrc,"utf-8"));
			byte[] bytes = Des3.encode(szSrc.getBytes("utf-8"));

			System.out.println("加密前的字符串:" + szSrc);
			System.out.println("加密后的字符串:"
					+ new String(Des3.encode(szSrc.getBytes("utf-8"))));
			System.out.println("加密后的hex字符串:"
					+ Des3.bytesToHexStr(Des3.encode(szSrc.getBytes("utf-8"))));
			System.out.println("解密后的字符串:"
					+ new String(Des3.decode(bytes), "utf-8"));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}
}
