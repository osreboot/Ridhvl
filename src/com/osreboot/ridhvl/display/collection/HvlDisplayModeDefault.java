package com.osreboot.ridhvl.display.collection;

import org.lwjgl.opengl.Display;

import com.osreboot.ridhvl.display.HvlDisplayMode;

public class HvlDisplayModeDefault extends HvlDisplayMode{

	@Override
	public void configureDisplay(){
		Display.setResizable(false);
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
