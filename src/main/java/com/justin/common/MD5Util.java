package com.justin.common;

import org.bouncycastle.pqc.math.linearalgebra.ByteUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;

/**
 * @Descp MD5-工具类
 * @Author shiqiang
 * @Date 2022/10/17 10:19
 * @Version
 **/
public class MD5Util {
	private static final String ENCRYPTSTR = "MD5";
	/**
	 * 获取单个文件的MD5值！
	 * @param file
	 * @return
	 */
	public static String getFileMD5(File file) {
		if (!file.isFile()){
			return null;
		}
		MessageDigest digest = null;
		FileInputStream in=null;
		byte[] buffer = new byte[1024];
		int len = 0;
		try {
			digest = MessageDigest.getInstance(ENCRYPTSTR);
			in = new FileInputStream(file);
			while ((len = in.read(buffer, 0, 1024)) != -1) {
				digest.update(buffer, 0, len);
			}
			in.close();
		} catch (Exception e) {
			if (in!=null) {
				try {
					in.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			e.printStackTrace();
			return null;
		} finally {
			if (in!=null) {
				try {
					in.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
		BigInteger bigInt = new BigInteger(1, digest.digest());
		return ByteUtils.toHexString(bigInt.toByteArray());
	}

	/**
	 * 获取文件夹中文件的MD5值
	 * @param file
	 * @param listChild ;true递归子目录中的文件
	 * @return
	 */
	public static Map<String, String> getDirMD5(File file,boolean listChild) {
		if(!file.isDirectory()){
			return null;
		}
		//<filepath,md5>
		Map<String, String> map=new HashMap<String, String>();
		String md5 = "";
		File[] files=file.listFiles();
		for(int i=0;i<files.length;i++){
			File f=files[i];
			if(f.isDirectory()&&listChild){
				map.putAll(getDirMD5(f, listChild));
			} else {
				md5=getFileMD5(f);
				if(md5!=null){
					map.put(f.getPath(), md5);
				}
			}
		}
		return map;
	}

	/**
	 * 获取字符串的md5值
	 * @param md5Str
	 * @return
	 */
	public static String getStrMD(String md5Str) {
		StringBuffer buf = null;
		try {
			//生成实现指定摘要算法的 MessageDigest 对象。
			MessageDigest md = MessageDigest.getInstance(ENCRYPTSTR);  
			//使用指定的字节数组更新摘要。
			md.update(md5Str.getBytes(StandardCharsets.UTF_8));
			//通过执行诸如填充之类的最终操作完成哈希计算。
			byte[] b = md.digest();
			//生成具体的md5密码到buf数组
			int i = 0;
			buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0) {
					i += 256;
				}
				if (i < 16) {
					buf.append("0");
				}
				buf.append(Integer.toHexString(i));
			}
//	        System.out.println("32位: " + buf.toString());// 32位的加密
//	        System.out.println("16位: " + buf.toString().substring(8, 24));// 16位的加密，其实就是32位加密后的截取
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return buf.toString();
	}
	/**
	 * 获取字符串的md5值
	 * @param md5Str
	 * @return
	 * @throws Exception 
	 */
	public static byte[] getStr16MD(String md5Str) throws Exception {
		//生成实现指定摘要算法的 MessageDigest 对象。
		MessageDigest md = MessageDigest.getInstance(ENCRYPTSTR);  
		//使用指定的字节数组更新摘要。
		md.update(md5Str.getBytes(StandardCharsets.UTF_8));
		//通过执行诸如填充之类的最终操作完成哈希计算。
		byte[] b = md.digest();
		return b;
	}
}
