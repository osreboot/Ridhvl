package com.osreboot.ridhvl.template;

import java.util.ArrayList;
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
	private static HashMap<Integer, HvlAction1<Float>> chronoUpdate = new HashMap<>();
	private static boolean initialized = false, debugOutput = false;
	
	public static boolean isInitialized(){
		return initialized;
	}

	public static boolean getDebugOutput(){
		return debugOutput;
	}

	public static void setDebugOutput(boolean debugOutput){
		HvlChronology.debugOutput = debugOutput;
	}

	public static class Initialize{
		private static ArrayList<HvlAction0> queue = new ArrayList<>();
		
		public Initialize(int chronologyArg, HvlAction0 actionArg){
			if(chronologyArg < INIT_CHRONOLOGY_EARLIEST || chronologyArg > INIT_CHRONOLOGY_LATEST) throw new BrokenChronologyException();
			else{
				if(chronoInit.containsKey(chronologyArg)) throw new PredefinedChronologyException();
				else{
					System.out.println("HvlChronology: manually assigning an Initialize action to slot " + chronologyArg);
					chronoInit.put(chronologyArg, actionArg);
				}
			}
		}
		
		/**
		 * Creates an Initialize chronology that is automatically stacked towards the latest possible slot (defined chronologies have priority over this).
		 * @param actionArg
		 */
		public Initialize(HvlAction0 actionArg){
			if(!isInitialized())
			queue.add(actionArg);
			else defaultAdd(actionArg);
		}
		
		private static void sortQueue(){
			for(HvlAction0 action : queue) defaultAdd(action);
		}
		
		private static void defaultAdd(HvlAction0 action){
			for(int i = INIT_CHRONOLOGY_LATEST; i >= INIT_CHRONOLOGY_EARLIEST; i--) if(!chronoInit.containsKey(i)){
				System.out.println("HvlChronology: automatically assigning an Initialize action to slot " + i);
				chronoInit.put(i, action);
			}
		}
	}

	public static class Update{
		private static ArrayList<HvlAction1<Float>> queue = new ArrayList<>();
		
		public Update(int chronologyArg, HvlAction1<Float> actionArg){
			if(chronologyArg < UPDATE_CHRONOLOGY_PRE_EARLIEST || chronologyArg > UPDATE_CHRONOLOGY_POST_LATEST) throw new BrokenChronologyException();
			else{
				if(chronoUpdate.containsKey(chronologyArg)) throw new PredefinedChronologyException();
				else{
					System.out.println("HvlChronology: manually assigning an Update action to slot " + chronologyArg);
					chronoUpdate.put(chronologyArg, actionArg);
				}
			}
		}
		
		/**
		 * Creates an Update chronology that is automatically stacked towards the latest possible pre-draw slot (defined chronologies have priority over this).
		 * @param actionArg
		 */
		public Update(HvlAction1<Float> actionArg){
			if(!isInitialized())
			queue.add(actionArg);
			else defaultAdd(actionArg);
		}
		
		private static void sortQueue(){
			for(HvlAction1<Float> action : queue) defaultAdd(action);
		}
		
		private static void defaultAdd(HvlAction1<Float> action){
			for(int i = UPDATE_CHRONOLOGY_PRE_LATEST; i >= UPDATE_CHRONOLOGY_PRE_EARLIEST; i--) if(!chronoUpdate.containsKey(i)){
				System.out.println("HvlChronology: automatically assigning an Update action to slot " + i);
				chronoUpdate.put(i, action);
			}
		}
	}
	
	protected static void initialize(){
		Initialize.sortQueue();
		Update.sortQueue();
		initialized = true;
		for(int i = INIT_CHRONOLOGY_EARLIEST; i <= INIT_CHRONOLOGY_LATEST; i++) if(chronoInit.containsKey(i)) chronoInit.get(i).run();
	}
	
	protected static void preUpdate(float delta){
		for(int i = UPDATE_CHRONOLOGY_PRE_EARLIEST; i <= UPDATE_CHRONOLOGY_PRE_LATEST; i++) if(chronoUpdate.containsKey(i)) chronoUpdate.get(i).run(delta);
	}

	protected static void postUpdate(float delta){
		for(int i = UPDATE_CHRONOLOGY_POST_EARLIEST; i <= UPDATE_CHRONOLOGY_POST_LATEST; i++) if(chronoUpdate.containsKey(i)) chronoUpdate.get(i).run(delta);
	}

	@SuppressWarnings("serial")
	static class BrokenChronologyException extends RuntimeException{}
	
	@SuppressWarnings("serial")
	static class PredefinedChronologyException extends RuntimeException{}

}
