package com.osreboot.ridhvl.display.collection;

import org.lwjgl.opengl.Display;

import com.osreboot.ridhvl.display.HvlDisplayMode;

@Deprecated
public class HvlDisplayModeARLock extends HvlDisplayMode{

	//private float aspectRatio, initialHeight, initialWidth;

	@Override
	public void configureDisplay(){
		//initialHeight = Display.getHeight();
		//initialWidth = Display.getWidth();
		//aspectRatio = (float)Display.getWidth()/(float)Display.getHeight();
		Display.setResizable(true);
	}

}
