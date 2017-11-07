package com.twitter.utils;

public class Utilities {
	
	public static boolean isEmpty(String str){
		return str == null || str.trim().equals("");
	}
	
	public static boolean isNotEmpty(String str){
		return !isEmpty(str);
	}

}
