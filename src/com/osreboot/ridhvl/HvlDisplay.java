package com.osreboot.ridhvl;

import org.lwjgl.opengl.Display;

public class HvlDisplay {

	public enum HvlDisplayMode{
		DEFAULT, ARLOCK, SCALABLE
	}
	
	private static HvlDisplayMode displayMode;
	private static float aspectRatio;
	
	public static void setDisplayMode(HvlDisplayMode displayModeArg){
		displayMode = displayModeArg;
	}
	
	public static HvlDisplayMode getDisplayMode(){
		return displayMode;
	}
	
	public static void configureDisplay(HvlDisplayMode displayModeArg, float aspectRatioArg){//TODO better way to manage aspect ratio
		switch(displayModeArg){
		case ARLOCK:
			Display.setResizable(true);
			break;
		case SCALABLE: 
			Display.setResizable(false);
			break;
		default: break;
		}
		setDisplayMode(displayModeArg);
		
		aspectRatio = aspectRatioArg;
	}
	
	public static float getTransformedX(float xArg){
		switch(displayMode){
		case ARLOCK: return xArg*Display.getWidth()*aspectRatio;//TODO work with blank space
		case SCALABLE: return xArg*Display.getWidth()*aspectRatio;
		default: return xArg;
		}
	}
	
	public static float getTransformedY(float yArg){
		switch(displayMode){
		case ARLOCK: return yArg*Display.getHeight()*(1/aspectRatio);//TODO work with blank space
		case SCALABLE: return yArg*Display.getHeight()*(1/aspectRatio);
		default: return yArg;
		}
	}
	
	public static float getScaledX(float xArg){
		switch(displayMode){
		case ARLOCK: return xArg*Display.getWidth()*aspectRatio;
		case SCALABLE: return xArg*Display.getWidth()*aspectRatio;
		default: return xArg;
		}
	}
	
	public static float getScaledY(float yArg){
		switch(displayMode){
		case ARLOCK: return yArg*Display.getHeight()*(1/aspectRatio);
		case SCALABLE: return yArg*Display.getHeight()*(1/aspectRatio);
		default: return yArg;
		}
	}
	
	public static float getUnscaledX(float xArg){
		switch(displayMode){
		case ARLOCK: return xArg/Display.getWidth()/aspectRatio;
		case SCALABLE: return xArg/Display.getWidth()/aspectRatio;
		default: return xArg;
		}
	}
	
	public static float getUnscaledY(float yArg){
		switch(displayMode){
		case ARLOCK: return yArg/Display.getHeight()/(1/aspectRatio);
		case SCALABLE: return yArg/Display.getHeight()/(1/aspectRatio);
		default: return yArg;
		}
	}
	
}
