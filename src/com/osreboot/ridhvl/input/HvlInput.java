package com.osreboot.ridhvl.input;


public class HvlInput {
	
	public static abstract class HvlInputFilter{
		public abstract float getCurrentOutput();
	}
	
	private HvlInputFilter filter;
	
	public HvlInput(HvlInputFilter filterArg){
		filter = filterArg;
	}
	
	public float getCurrentOutput(){
		return filter.getCurrentOutput();
	}
	
	public HvlInputFilter getFilter(){
		return filter;
	}

	public void setFilter(HvlInputFilter filterArg){
		filter = filterArg;
	}
	
}
