package com.osreboot.ridhvl.template;

import java.util.HashMap;

import com.osreboot.ridhvl.action.HvlAction0;
import com.osreboot.ridhvl.action.HvlAction1;

public class HvlChronology {

	public static final int INIT_CHRONOLOGY_DFLTINTVL = 5,
			INIT_CHRONOLOGY_EARLIEST = 0,
			INIT_CHRONOLOGY_EARLY = 25,
			INIT_CHRONOLOGY_MIDDLE = 50,
			INIT_CHRONOLOGY_LATE = 75,
			INIT_CHRONOLOGY_LATEST = 100,
			UPDATE_CHRONOLOGY_DFLTINTVL = 3,
			UPDATE_CHRONOLOGY_PRE_EARLIEST = 0,
			UPDATE_CHRONOLOGY_PRE_EARLY = 15,
			UPDATE_CHRONOLOGY_PRE_MIDDLE = 25,
			UPDATE_CHRONOLOGY_PRE_LATE = 35,
			UPDATE_CHRONOLOGY_PRE_LATEST = 50,
			UPDATE_CHRONOLOGY_POST_EARLIEST = 51,
			UPDATE_CHRONOLOGY_POST_EARLY = 65,
			UPDATE_CHRONOLOGY_POST_MIDDLE = 75,
			UPDATE_CHRONOLOGY_POST_LATE = 85,
			UPDATE_CHRONOLOGY_POST_LATEST = 100;

	private static HashMap<Integer, HvlAction0> chronoInit = new HashMap<>();
	private static HashMap<Integer, HvlAction1<Float>> updateInit = new HashMap<>();

	public static class Initialize{
		public Initialize(int chronologyArg, HvlAction0 actionArg){
			if(chronologyArg < 0 || chronologyArg > 100) throw new BrokenChronologyException();
			else{
				if(chronoInit.containsKey(chronologyArg)) throw new PredefinedChronologyException();
				else chronoInit.put(chronologyArg, actionArg);
			}
		}
	}

	public static class Update{
		public Update(int chronologyArg, HvlAction1<Float> actionArg){
			if(chronologyArg < 0 || chronologyArg > 100) throw new BrokenChronologyException();
			else{
				if(updateInit.containsKey(chronologyArg)) throw new PredefinedChronologyException();
				else updateInit.put(chronologyArg, actionArg);
			}
		}
	}
	
	protected static void initialize(){
		for(int i = INIT_CHRONOLOGY_EARLIEST; i <= INIT_CHRONOLOGY_LATEST; i++) if(chronoInit.containsKey(i)) chronoInit.get(i).run();
	}
	
	protected static void preUpdate(float delta){
		for(int i = UPDATE_CHRONOLOGY_PRE_EARLIEST; i <= UPDATE_CHRONOLOGY_PRE_LATEST; i++) if(updateInit.containsKey(i)) updateInit.get(i).run(delta);
	}

	protected static void postUpdate(float delta){
		for(int i = UPDATE_CHRONOLOGY_POST_EARLIEST; i <= UPDATE_CHRONOLOGY_POST_LATEST; i++) if(updateInit.containsKey(i)) updateInit.get(i).run(delta);
	}

	@SuppressWarnings("serial")
	static class BrokenChronologyException extends RuntimeException{}
	
	@SuppressWarnings("serial")
	static class PredefinedChronologyException extends RuntimeException{}

}
