package com.osreboot.ridhvl.display;

public abstract class HvlDisplayMode {

	public HvlDisplayMode(){}
	
	public abstract void configureDisplay();
	
	public abstract float getTransformedX(float xArg);
	public abstract float getTransformedY(float yArg);
	
	public abstract float getScaledX(float xArg);
	public abstract float getScaledY(float yArg);
	
	public abstract float getUnscaledX(float xArg);
	public abstract float getUnscaledY(float yArg);
	
}
