package com.osreboot.ridhvl.display.collection;

import org.lwjgl.opengl.Display;

import com.osreboot.ridhvl.display.HvlDisplayMode;

public class HvlDisplayModeDefault extends HvlDisplayMode{

	@Override
	public void configureDisplay(){
		Display.setResizable(false);
	}
	
}
