package com.osreboot.ridhvl.test;

public class TestConfigType {
	public static class TCT1 {
		public TCT1() {
			
		}
		
		public String aString;
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
		
		public boolean prop;
		public int value;
		public TCT5[] nestedClassArray;
	}
	
	public static class TCT5 {
		public TCT5() {
			
		}
		
		public String val;
	}
	
	public String aString;
	public boolean someBool;
	public int[] anIntArray;
	public TCT1 aClass;
	public TCT2 anotherClass;
	public TCT4[] aClassArray;
}
