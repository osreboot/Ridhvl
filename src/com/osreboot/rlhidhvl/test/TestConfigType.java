package com.osreboot.rlhidhvl.test;

import java.util.List;

public class TestConfigType {
	public static class TCT1 {
		public TCT1() {
			
		}
		
		public String aString;
		public static String aTestStaticNestedString;
	}
	
	public static class TCT2 {
		public TCT2() {
			
		}
		
		public int[] anIntArray;
		public TCT3 nestedClass;
	}

	public static class TCT3 {
		public TCT3() {
			
		}
		
		public String property;
	}
	
	public static class TCT4 {
		public TCT4() {
			
		}
		
		public TCT4(boolean p, int v, TCT5... nca) {
			prop = p;
			value = v;
			nestedClassArray = nca;
		}
		
		public boolean prop;
		public int value;
		public TCT5[] nestedClassArray;
	}
	
	public static class TCT5 {
		public TCT5() {
			
		}
		
		public TCT5(String v) {
			val = v;
		}
		
		public String val;
	}
	
	public String aString;
	public boolean someBool;
	public List<Integer> anIntArray;
	public TCT1 aClass;
	public TCT2 anotherClass;
	public List<TCT4> aClassArray;
	public static String testingStatics;
	public static TCT1 staticTestClass;
}
