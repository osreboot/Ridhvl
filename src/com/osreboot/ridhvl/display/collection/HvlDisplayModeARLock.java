package com.osreboot.ridhvl.display.collection;

import org.lwjgl.opengl.Display;

import com.osreboot.ridhvl.display.HvlDisplayMode;

public class HvlDisplayModeARLock extends HvlDisplayMode{

	private float aspectRatio, initialHeight, initialWidth;

	@Override
	public void configureDisplay(){
		initialHeight = Display.getHeight();
		initialWidth = Display.getWidth();
		aspectRatio = (float)Display.getWidth()/(float)Display.getHeight();
		Display.setResizable(true);
	}

	@Override
	public void update(){
		aspectRatio = (float)Display.getWidth()/(float)Display.getHeight();
	}
	
	@Override
	public float getTransformedX(float xArg){
		return xArg;
	}

	@Override
	public float getTransformedY(float yArg){
		return yArg;
	}

	@Override
	public float getScaledX(float xArg){
		return xArg;
	}

	@Override
	public float getScaledY(float yArg){
		return yArg;
	}

	@Override
	public float getUnscaledX(float xArg){
		return xArg;
	}

	@Override
	public float getUnscaledY(float yArg){
		return yArg;
	}

}
