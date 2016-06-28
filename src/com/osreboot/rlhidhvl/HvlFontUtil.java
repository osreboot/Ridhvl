package com.osreboot.rlhidhvl;

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

	public static String wrapText(String text, int charsPerLine) {
		if (text.length() < charsPerLine) return text;
		
		StringBuilder tr = new StringBuilder();
		
		int charsSoFar = 0;
		
		String[] parts = text.split(" ");
		
		for (String part : parts) {
			charsSoFar += part.length();
			if (charsSoFar > charsPerLine) {
				tr.append("\n");
				tr.append(part + " ");
				charsSoFar = part.length() + 1;
			} else if (charsSoFar == charsPerLine) {
				tr.append(part);
				tr.append("\n");
				charsSoFar = 0;
			} else {
				tr.append(part);
				tr.append(" ");
				charsSoFar++;
			}
		}
		
		return tr.toString();
	}
	
	// TODO: variant of wrapText that takes pixels rather than characters (due to support for uneven char sizes)
}
