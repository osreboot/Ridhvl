package com.osreboot.ridhvl.template;

import java.util.HashMap;

import com.osreboot.ridhvl.action.HvlAction0;
import com.osreboot.ridhvl.action.HvlAction1;

public class HvlChronology {

	public static final int INIT_CHRONOLOGY_EARLIEST = 0,
			INIT_CHRONOLOGY_LATEST = 100,
			INIT_CHRONOLOGY_MIDDLE = 50,
			UPDATE_CHRONOLOGY_PRE_EARLIEST = 0,
			UPDATE_CHRONOLOGY_PRE_MIDDLE = 25,
			UPDATE_CHRONOLOGY_PRE_LATEST = 50,
			UPDATE_CHRONOLOGY_POST_EARLIEST = 51,
			UPDATE_CHRONOLOGY_POST_MIDDLE = 75,
			UPDATE_CHRONOLOGY_POST_LATEST = 100;

	private static HashMap<HvlAction0, Integer> chronoInit = new HashMap<>();
	private static HashMap<HvlAction1<Float>, Integer> updateInit = new HashMap<>();

	public static class HvlChronoInitialize{
		public HvlChronoInitialize(HvlAction0 actionArg, int chronologyArg){
			if(chronologyArg < 0 || chronologyArg > 100) throw new BrokenChronologyException();
			else chronoInit.put(actionArg, chronologyArg);
		}
	}

	public static class HvlChronoUpdate{
		public HvlChronoUpdate(HvlAction1<Float> actionArg, int chronologyArg){
			if(chronologyArg < 0 || chronologyArg > 100) throw new BrokenChronologyException();
			else updateInit.put(actionArg, chronologyArg);
		}
	}

	protected static void preUpdate(float delta){

	}

	protected static void postUpdate(float delta){

	}

	protected static void initialize(){

	}

	@SuppressWarnings("serial")
	static class BrokenChronologyException extends RuntimeException{}

}
