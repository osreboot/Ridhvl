package com.osreboot.ridhvl.display.collection;

import org.lwjgl.opengl.Display;

import com.osreboot.ridhvl.display.HvlDisplayMode;

public class HvlDisplayModeScalable extends HvlDisplayMode{
	
	private float initialHeight, initialWidth;
	
	@Override
	public void configureDisplay(){
		initialHeight = Display.getHeight();
		initialWidth = Display.getWidth();
		Display.setResizable(true);
	}
	
	@Override
	public float getTransformedX(float xArg){
		return xArg/initialWidth*(float)Display.getWidth();
	}

	@Override
	public float getTransformedY(float yArg){
		return yArg/initialHeight*(float)Display.getHeight();
	}

	@Override
	public float getScaledX(float xArg){
		return xArg/initialWidth*(float)Display.getWidth();
	}

	@Override
	public float getScaledY(float yArg){
		return yArg/initialHeight*(float)Display.getHeight();
	}

	@Override
	public float getUnscaledX(float xArg){
		return xArg*initialWidth/(float)Display.getWidth();
	}

	@Override
	public float getUnscaledY(float yArg){
		return yArg*initialHeight/(float)Display.getHeight();
	}

}
