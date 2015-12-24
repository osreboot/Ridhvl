package com.osreboot.ridhvl;

public class HvlFontUtil {

	public static final char[] DEFAULT				 = "abcdefghijklmnopqrstuvwxyz0123456789!?.()[]".toCharArray();
	public static final char[] DEFAULTCASE			 = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!?.()[]".toCharArray();
	public static final char[] NUMERICAL			 = "!\"#$%&\'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~".toCharArray();
	public static final char[] SIMPLISTIC			 = "abcdefghijklmnopqrstuvwxyz1234567890.!?\"()[]:;-+".toCharArray();
	public static final char[] SIMPLISTICCASE		 = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890.!?\"()[]:;-+".toCharArray();
	public static final char[] MATHEMATICS			 = "1234567890+-*/".toCharArray();
	public static final char[] COMPUTATIONAL		 = "abcdefghijklmnopqrstuvwxyz0123456789/\\'\")(][?!.,_@*^-+=|:;><".toCharArray();
	public static final char[] COMPUTATIONALCASE	 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789/\\'\")(][?!.,_@*^-+=|;:><abcdefghijklmnopqrstuvwxyz".toCharArray();
	
	public static boolean containsChar(char[] layout, char c){
		for(int i = 0; i < layout.length; i++){
			if(layout[i] == c) return true;
		}
		return false;
	}
	
	public static int indexOfChar(char[] layout, char c){
		for(int i = 0; i < layout.length; i++){
			if(layout[i] == c) return i;
		}
		return -1;
	}

}
