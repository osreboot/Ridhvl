package com.osreboot.ridhvl.display;


public class HvlDisplay {
	
	private static HvlDisplayMode displayMode;
	
	public static void setDisplayMode(HvlDisplayMode displayModeArg){
		displayMode = displayModeArg;
		displayMode.configureDisplay();
	}
	
	public static HvlDisplayMode getDisplayMode(){
		return displayMode;
	}
	
	public static void initializeDisplayMode(){
		displayMode.initialize();
	}
	
	public static void preUpdate(float delta){
		displayMode.preUpdate(delta);
	}
	
	public static void postUpdate(float delta){
		displayMode.postUpdate(delta);
	}
	
}
