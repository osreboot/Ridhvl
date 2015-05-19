package com.osreboot.ridhvl;

import org.lwjgl.opengl.Display;

public class HvlDisplay {

	public enum HvlDisplayMode{
		DEFAULT, ARLOCK, SCALABLE
	}
	
	public static void configureDisplay(HvlDisplayMode displayModeArg){
		switch(displayModeArg){
		case ARLOCK:
			Display.setResizable(true);
			break;
		case SCALABLE: 
			Display.setResizable(false);
			break;
		default: break;
		}
	}
	
}
