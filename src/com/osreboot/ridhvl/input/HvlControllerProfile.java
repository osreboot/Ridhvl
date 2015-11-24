package com.osreboot.ridhvl.input;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import net.java.games.input.Controller;
import net.java.games.input.Event;
import net.java.games.input.EventQueue;

import com.osreboot.ridhvl.action.HvlAction1;
import com.osreboot.ridhvl.action.HvlAction2r;
import com.osreboot.ridhvl.template.HvlChronology;
import com.osreboot.ridhvl.template.HvlChronologyUpdate;


public abstract class HvlControllerProfile {

	public static final int UPDATE_CHRONOLOGY = HvlChronology.UPDATE_CHRONOLOGY_PRE_EARLY + (HvlChronology.UPDATE_CHRONOLOGY_DFLTINTVL * 2);

	@HvlChronologyUpdate(chronology = UPDATE_CHRONOLOGY)
	public static final HvlAction1<Float> UPDATE_ACTION = new HvlAction1<Float>(){
		@Override
		public void run(Float delta){
			for(HvlControllerProfile p : profiles) p.pollValues();
		}
	};

	private static boolean debugOutput = false;

	public static boolean getDebugOutput() {
		return debugOutput;
	}

	public static void setDebugOutput(boolean debugOutputArg) {
		debugOutput = debugOutputArg;
	}

	private static ArrayList<HvlControllerProfile> profiles = new ArrayList<>();

	protected static ArrayList<HvlControllerProfile> getProfiles(){
		return profiles;
	}

	public boolean autoIndex = true;
	private String controllerIdentifier = null;
	private HvlAction2r<Boolean, HvlControllerProfile, Controller> actionIsController;
	private LinkedHashMap<String, HvlPollValue> staticPollAnnotations = new LinkedHashMap<>();
	private ArrayList<String> staticPollValues = new ArrayList<>();
	private LinkedHashMap<Controller, ArrayList<Float>> pollValues = new LinkedHashMap<>();
	private ArrayList<Controller> controllerIndexes = new ArrayList<>();

	public HvlControllerProfile(Class<? extends HvlControllerProfile> hostArg, String controllerIdentifierArg){
		controllerIdentifier = controllerIdentifierArg;

		String value = null;
		for(Field f : hostArg.getDeclaredFields()){
			if(f.isAnnotationPresent(HvlPollValue.class)){
				if(f.getType() == String.class){
					try{
						value = (String)f.get(hostArg);
					}catch(IllegalArgumentException | IllegalAccessException e){
						e.printStackTrace();
					}
					staticPollAnnotations.put(value, f.getAnnotation(HvlPollValue.class));
					staticPollValues.add(value);
				}else throw new CPImproperTypeException();
			}
		}

		syncControllers();

		profiles.add(this);
	}

	public HvlControllerProfile(Class<? extends HvlControllerProfile> hostArg, HvlAction2r<Boolean, HvlControllerProfile, Controller> actionIsControllerArg){
		actionIsController = actionIsControllerArg;

		String value = null;
		for(Field f : hostArg.getDeclaredFields()){
			if(f.isAnnotationPresent(HvlPollValue.class)){
				if(f.getType() == String.class){
					try{
						value = (String)f.get(hostArg);
					}catch(IllegalArgumentException | IllegalAccessException e){
						e.printStackTrace();
					}
					staticPollAnnotations.put(value, f.getAnnotation(HvlPollValue.class));
					staticPollValues.add(value);
				}else throw new CPImproperTypeException();
			}
		}

		syncControllers();

		profiles.add(this);
	}
	
	public void syncControllers(){
		pollValues.clear();
		if(autoIndex) controllerIndexes.clear();
		for(Controller c : HvlController.getControllers()){
			if(isOfType(c)){
				if(debugOutput) System.out.println("HvlControllerProfile: Found controller: \"" + c.getName() + "\"");
				ArrayList<Float> list = new ArrayList<>();
				for(int i = 0; i < staticPollValues.size(); i++) list.add(0f);
				pollValues.put(c, list);
				if(autoIndex) controllerIndexes.add(c);
			}
		}
	}

	public void pollValues(){
		for(Controller c : pollValues.keySet()){
			c.poll();
			EventQueue queue = c.getEventQueue();
			Event event = new Event();
			while(queue.getNextEvent(event)){
				if(debugOutput) System.out.println("HvlControllerProfile: Controller event at: " + event.getComponent().getName() + " with a value of " + event.getValue());
				for(String s : staticPollValues){
					if(event.getComponent().getName().contains(staticPollAnnotations.get(s).component())){
						float value = event.getValue();
						if(Math.abs(value) < Math.abs(staticPollAnnotations.get(s).deadZone())) value = 0;
						ArrayList<Float> list = pollValues.get(c);
						list.set(staticPollValues.indexOf(s), value);
						pollValues.put(c, list);
					}
				}
			}
		}
	}

	public float getValue(String fieldArg){
		float output = 0;
		for(Controller c : pollValues.keySet()){
			output += pollValues.get(c).get(staticPollValues.indexOf(fieldArg));
		}
		output = (float)Math.max(staticPollAnnotations.get(fieldArg).min(), Math.min(staticPollAnnotations.get(fieldArg).max(), output));
		return output;
	}

	public float getValue(String fieldArg, int controllerIndex){
		if(controllerIndexes.size() <= controllerIndex) return 0;
		else{
			return pollValues.get(controllerIndexes.get(controllerIndex)).get(staticPollValues.indexOf(fieldArg));
		}
	}

	public boolean isOfType(Controller c){
		return (controllerIdentifier != null && c.getName().contains(controllerIdentifier)) || 
				(actionIsController != null && actionIsController.run(this, c));
	}

	public boolean getAutoIndex(){
		return autoIndex;
	}

	public void setAutoIndex(boolean autoIndexArg){
		autoIndex = autoIndexArg;
	}

	public ArrayList<Controller> getControllerIndexes(){
		return controllerIndexes;
	}

	public void setControllerIndexes(ArrayList<Controller> controllerIndexesArg){
		controllerIndexes = controllerIndexesArg;
	}

	@SuppressWarnings("serial")
	static class CPUndefinedFieldException extends RuntimeException{}

	@SuppressWarnings("serial")
	static class CPImproperTypeException extends RuntimeException{}

}