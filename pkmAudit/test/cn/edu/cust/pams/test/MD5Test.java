package cn.edu.cust.pams.test;

import cn.edu.cust.common.util.Md5Util;

public class MD5Test {
	public static void main(String[] args) {
		System.out.println(Md5Util.getMD5Str("123{}"));
	}
}
