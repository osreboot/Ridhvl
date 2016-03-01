package com.osreboot.ridhvl.display;

import org.lwjgl.opengl.Display;

import com.osreboot.ridhvl.action.HvlAction0;
import com.osreboot.ridhvl.action.HvlAction1;
import com.osreboot.ridhvl.action.HvlEvent0;
import com.osreboot.ridhvl.template.HvlChronology;
import com.osreboot.ridhvl.template.HvlChronologyInitialize;
import com.osreboot.ridhvl.template.HvlChronologyUpdate;

public class HvlDisplay {

	public static final int INIT_CHRONOLOGY = HvlChronology.INIT_CHRONOLOGY_EARLY - HvlChronology.UPDATE_CHRONOLOGY_DFLTINTVL;

	@HvlChronologyInitialize(chronology = INIT_CHRONOLOGY)
	public static final HvlAction0 INIT_ACTION = new HvlAction0(){
		@Override
		public void run(){
			initializeDisplayMode();
		}
	};

	public static final int UPDATE_CHRONOLOGY = HvlChronology.UPDATE_CHRONOLOGY_PRE_EARLY + HvlChronology.UPDATE_CHRONOLOGY_DFLTINTVL;

	@HvlChronologyUpdate(chronology = UPDATE_CHRONOLOGY)
	public static final HvlAction1<Float> UPDATE_ACTION = new HvlAction1<Float>(){
		@Override
		public void run(Float delta){
			preUpdate(delta);
		}
	};
	
	public static final int UPDATE_CHRONOLOGY2 = HvlChronology.UPDATE_CHRONOLOGY_POST_MIDDLE + HvlChronology.UPDATE_CHRONOLOGY_DFLTINTVL;

	@HvlChronologyUpdate(chronology = UPDATE_CHRONOLOGY2)
	public static final HvlAction1<Float> UPDATE_ACTION2 = new HvlAction1<Float>(){
		@Override
		public void run(Float delta){
			postUpdate(delta);
		}
	};
	
	public static final HvlEvent0 EVENT_DISPLAY_RESIZED = new HvlEvent0();

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
		if(hasBeenResized()) EVENT_DISPLAY_RESIZED.trigger();
		oldWidth = Display.getWidth();
		oldHeight = Display.getHeight();
		displayMode.preUpdate(delta);
	}

	public static void postUpdate(float delta){
		displayMode.postUpdate(delta);
	}

	private static boolean hasBeenResized(){
		return oldWidth != Display.getWidth() || oldHeight != Display.getHeight();
	}
}
