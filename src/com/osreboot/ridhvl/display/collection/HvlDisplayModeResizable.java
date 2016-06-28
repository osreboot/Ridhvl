package com.osreboot.ridhvl.display.collection;

import org.lwjgl.opengl.Display;

import com.osreboot.ridhvl.display.HvlDisplayMode;

public class HvlDisplayModeResizable extends HvlDisplayMode{

	@Override
	public void configureDisplay(){
		Display.setResizable(true);
	}
	
	@Override
	public void preUpdate(float delta){
		if(hasBeenResized()) resizePerspective(getCoordinateWidth(), getCoordinateHeight(), Display.getWidth(), Display.getHeight(), true);
	}

}
