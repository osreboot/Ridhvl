package com.osreboot.ridhvl.display;

public abstract class HvlDisplayMode {

	public HvlDisplayMode(){}
	
	public abstract void configureDisplay();
	
	public void initialize(){}
	
	public void preUpdate(float delta){}
	public void postUpdate(float delta){}
	
}
