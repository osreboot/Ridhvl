package com.osreboot.ridhvl.display;

import org.lwjgl.opengl.Display;

import com.osreboot.ridhvl.action.HvlAction0;
import com.osreboot.ridhvl.template.HvlChronology;

public class HvlDisplay {
	
	public static final int CHRONOLOGY = HvlChronology.INIT_CHRONOLOGY_EARLY - 1;
	{
		new HvlChronology.Initialize(CHRONOLOGY, new HvlAction0(){
			@Override
			public void run(){
				initializeDisplayMode();
			}
		});
	}
	
	private static int oldWidth, oldHeight;
	
	private static HvlDisplayMode displayMode;
	
	public static void setDisplayMode(HvlDisplayMode displayModeArg){
		displayMode = displayModeArg;
		displayMode.configureDisplay();
	}
	
	public static HvlDisplayMode getDisplayMode(){
		return displayMode;
	}
	
	public static void initializeDisplayMode(){
		displayMode.initialize();
	}
	
	public static void preUpdate(float delta){
		displayMode.preUpdate(delta);
	}
	
	public static void postUpdate(float delta){
		displayMode.postUpdate(delta);
	}
	
	public static boolean hasBeenResized(){
		if(oldWidth != Display.getWidth() || oldHeight != Display.getHeight()){
			oldWidth = Display.getWidth();
			oldHeight = Display.getHeight();
			return true;
		}
		return false;
	}
}
