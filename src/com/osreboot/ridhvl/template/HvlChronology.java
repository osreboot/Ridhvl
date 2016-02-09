package com.osreboot.ridhvl.template;

import java.lang.reflect.Field;
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

	@SuppressWarnings("unchecked")
	public static void registerChronology(Class<?> c){
		try{
			for(Field f : c.getFields()){
				if(f.isAnnotationPresent(HvlChronologyInitialize.class) && f.getType() == HvlAction0.class) new Initialize((HvlAction0)f.get(null), f);
				else if(f.isAnnotationPresent(HvlChronologyUpdate.class) && f.getType() == HvlAction1.class) new Update((HvlAction1<Float>)f.get(null), f);
			}
		}catch(IllegalAccessException e){}
	}

	private static class Initialize{
		private static ArrayList<HvlAction0> queue = new ArrayList<>();

		public Initialize(HvlAction0 actionArg, Field f){
			int chronologyArg = f.getAnnotation(HvlChronologyInitialize.class).chronology();
			if((chronologyArg < INIT_CHRONOLOGY_EARLIEST || chronologyArg > INIT_CHRONOLOGY_LATEST) && chronologyArg != -1) throw new BrokenChronologyException();
			else{
				if(chronologyArg == -1) defaultAdd(actionArg);
				else if(chronoInit.containsKey(chronologyArg)) throw new PredefinedChronologyException(f.getDeclaringClass(), chronologyArg);
				else{
					if(debugOutput) System.out.println("HvlChronology: manually assigning initialize " + f.getDeclaringClass().getName() + " action to slot " + chronologyArg + ".");
					chronoInit.put(chronologyArg, actionArg);
				}
			}
		}

		private static void sortQueue(){
			for(HvlAction0 action : queue) defaultAdd(action);
		}

		private static void defaultAdd(HvlAction0 action){
			for(int i = INIT_CHRONOLOGY_LATEST; i >= INIT_CHRONOLOGY_EARLIEST; i--) if(!chronoInit.containsKey(i)){
				if(debugOutput) System.out.println("HvlChronology: automatically assigning an initialize action to slot " + i + ".");
				chronoInit.put(i, action);
			}
		}
	}

	private static class Update{
		private static ArrayList<HvlAction1<Float>> queue = new ArrayList<>();

		public Update(HvlAction1<Float> actionArg, Field f){
			int chronologyArg = f.getAnnotation(HvlChronologyUpdate.class).chronology();
			if((chronologyArg < UPDATE_CHRONOLOGY_PRE_EARLIEST || chronologyArg > UPDATE_CHRONOLOGY_POST_LATEST) && chronologyArg != -1) throw new BrokenChronologyException();
			else{
				if(chronologyArg == -1) defaultAdd(actionArg);
				else if(chronoUpdate.containsKey(chronologyArg)) throw new PredefinedChronologyException(f.getDeclaringClass(), chronologyArg);
				else{
					if(debugOutput) System.out.println("HvlChronology: manually assigning updating " + f.getDeclaringClass().getName() + " action to slot " + chronologyArg + ".");
					chronoUpdate.put(chronologyArg, actionArg);
				}
			}
		}

		private static void sortQueue(){
			for(HvlAction1<Float> action : queue) defaultAdd(action);
		}

		private static void defaultAdd(HvlAction1<Float> action){
			for(int i = UPDATE_CHRONOLOGY_PRE_LATEST; i >= UPDATE_CHRONOLOGY_PRE_EARLIEST; i--) if(!chronoUpdate.containsKey(i)){
				if(debugOutput) System.out.println("HvlChronology: automatically assigning an updating action to slot " + i + ".");
				chronoUpdate.put(i, action);
				break;
			}
		}
	}

	protected static void initialize(){
		Initialize.sortQueue();
		Update.sortQueue();
		initialized = true;
		for(int i = INIT_CHRONOLOGY_EARLIEST; i <= INIT_CHRONOLOGY_LATEST; i++) if(chronoInit.containsKey(i)){
			try{
				chronoInit.get(i).run();
			}catch(Exception e){
				System.err.println("HvlChronology: exception thrown by initialize action " + i + "!");
				e.printStackTrace();
			}
		}
	}

	protected static void preUpdate(float delta){
		for(int i = UPDATE_CHRONOLOGY_PRE_EARLIEST; i <= UPDATE_CHRONOLOGY_PRE_LATEST; i++) if(chronoUpdate.containsKey(i)){
			try{
				chronoUpdate.get(i).run(delta);
			}catch(Exception e){
				System.err.println("HvlChronology: exception thrown by pre-update action " + i + "!");
				e.printStackTrace();
			}
		}
	}

	protected static void postUpdate(float delta){
		for(int i = UPDATE_CHRONOLOGY_POST_EARLIEST; i <= UPDATE_CHRONOLOGY_POST_LATEST; i++) if(chronoUpdate.containsKey(i)){
			try{
				chronoUpdate.get(i).run(delta);
			}catch(Exception e){
				System.err.println("HvlChronology: exception thrown by post-update action " + i + "!");
				e.printStackTrace();
			}
		}
	}

	@SuppressWarnings("serial")
	static class BrokenChronologyException extends RuntimeException{}

	@SuppressWarnings("serial")
	static class PredefinedChronologyException extends RuntimeException{
		public PredefinedChronologyException(Class<?> c, int chronologyArg){
			super("Can't allocate an action from " + c.getName() + " to slot " + chronologyArg + ", it is already occupied!");
		}
	}

}
