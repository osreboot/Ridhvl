package com.osreboot.ridhvl.input;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import com.osreboot.ridhvl.HvlMath;
import com.osreboot.ridhvl.action.HvlAction1;
import com.osreboot.ridhvl.action.HvlAction2r;
import com.osreboot.ridhvl.template.HvlChronology;
import com.osreboot.ridhvl.template.HvlChronologyUpdate;

import net.java.games.input.Controller;
import net.java.games.input.Event;
import net.java.games.input.EventQueue;


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
	private LinkedHashMap<Controller, Boolean> responseValues = new LinkedHashMap<>();
	private ArrayList<Controller> controllerIndexes = new ArrayList<>();
	private LinkedHashMap<String, HvlAction2r<Float, Controller, HvlControllerProfile>> pollCustom = new LinkedHashMap<>();

	@SuppressWarnings("unchecked")
	public HvlControllerProfile(Class<? extends HvlControllerProfile> hostArg, String controllerIdentifierArg){
		controllerIdentifier = controllerIdentifierArg;

		String value = null;
		for(Field f : hostArg.getDeclaredFields()){
			if(f.isAnnotationPresent(HvlPollValue.class)){
				if(f.getType() == String.class){
					try{
						if(!f.isAccessible()) f.setAccessible(true);
						value = (String)f.get(hostArg);
					}catch(IllegalArgumentException | IllegalAccessException e){
						e.printStackTrace();
					}
					if(!f.getAnnotation(HvlPollValue.class).custom().equals("")){
						try{
							Field c = hostArg.getField(f.getAnnotation(HvlPollValue.class).custom());
							if(c.getType() == HvlAction2r.class){
								pollCustom.put(value, (HvlAction2r<Float, Controller, HvlControllerProfile>)c.get(HvlAction2r.class));
							}
						}catch(Exception e){
							e.printStackTrace();
							throw new CPCustomNotFoundException();
						}
					}
					staticPollAnnotations.put(value, f.getAnnotation(HvlPollValue.class));
					staticPollValues.add(value);
				}else throw new CPImproperTypeException();
			}
		}

		syncControllers();

		profiles.add(this);
	}

	@SuppressWarnings("unchecked")
	public HvlControllerProfile(Class<? extends HvlControllerProfile> hostArg, HvlAction2r<Boolean, HvlControllerProfile, Controller> actionIsControllerArg){
		actionIsController = actionIsControllerArg;

		String value = null;
		for(Field f : hostArg.getDeclaredFields()){
			if(f.isAnnotationPresent(HvlPollValue.class)){
				if(f.getType() == String.class){
					try{
						if(!f.isAccessible()) f.setAccessible(true);
						value = (String)f.get(hostArg);
					}catch(IllegalArgumentException | IllegalAccessException e){
						e.printStackTrace();
					}
					if(!f.getAnnotation(HvlPollValue.class).custom().equals("")){
						try{
							Field c = hostArg.getField(f.getAnnotation(HvlPollValue.class).custom());
							if(c.getType() == HvlAction2r.class){
								pollCustom.put(value, (HvlAction2r<Float, Controller, HvlControllerProfile>)c.get(HvlAction2r.class));
							}
						}catch(Exception e){
							e.printStackTrace();
							throw new CPCustomNotFoundException();
						}
					}
					staticPollAnnotations.put(value, f.getAnnotation(HvlPollValue.class));
					staticPollValues.add(value);
				}else throw new CPImproperTypeException();
			}
		}

		syncControllers();

		profiles.add(this);
	}

	protected void syncControllers(){
		pollValues.clear();
		responseValues.clear();
		if(autoIndex) controllerIndexes.clear();
		for(Controller c : HvlController.getControllers()){
			if(isOfType(c)){
				if(debugOutput) System.out.println("HvlControllerProfile: Found controller: \"" + c.getName() + "\"");
				ArrayList<Float> list = new ArrayList<>();
				for(int i = 0; i < staticPollValues.size(); i++) list.add(0f);
				pollValues.put(c, list);
				responseValues.put(c, true);
				if(autoIndex) controllerIndexes.add(c);
			}
		}
	}

	protected void pollValues(){
		for(Controller c : pollValues.keySet()){
			responseValues.put(c, c.poll());
			EventQueue queue = c.getEventQueue();
			Event event = new Event();
			while(queue.getNextEvent(event)){
				if(debugOutput) System.out.println("HvlControllerProfile: Controller event at: " + event.getComponent().getName() + " with a value of " + event.getValue());
				for(String s : staticPollValues){
					if(event.getComponent().getName().contains(staticPollAnnotations.get(s).component())){
						float value = event.getValue();
						if(Math.abs(value) < Math.abs(staticPollAnnotations.get(s).deadZone())) value = 0;
						value *= staticPollAnnotations.get(s).amplifier();
						value = HvlMath.limit(value, staticPollAnnotations.get(s).min(), staticPollAnnotations.get(s).max());
						ArrayList<Float> list = pollValues.get(c);
						list.set(staticPollValues.indexOf(s), value);
						pollValues.put(c, list);
					}
				}
			}
		}
	}
	
	public boolean isResponding(int controllerIndex){
		if(controllerIndexes.size() <= controllerIndex) return false;
		else{
			return responseValues.get(controllerIndexes.get(controllerIndex));
		}
	}

	public float getValue(String fieldArg){
		float output = 0;
		for(Controller c : pollValues.keySet()){
			output += getMetaValue(c, fieldArg);
		}
		output = (float)Math.max(staticPollAnnotations.get(fieldArg).min(), Math.min(staticPollAnnotations.get(fieldArg).max(), output));
		return output;
	}

	public float getValue(String fieldArg, int controllerIndex){
		if(controllerIndexes.size() <= controllerIndex) return 0;
		else{
			return getMetaValue(controllerIndexes.get(controllerIndex), fieldArg);
		}
	}

	private float getMetaValue(Controller c, String fieldArg){
		if(pollCustom.containsKey(fieldArg)) return pollCustom.get(fieldArg).run(c, this);
		else return pollValues.get(c).get(staticPollValues.indexOf(fieldArg));
	}

	public float getRawValue(Controller c, String fieldArg){
		return pollValues.get(c).get(staticPollValues.indexOf(fieldArg));
	}

	public boolean isOfType(Controller c){
		return (controllerIdentifier != null && c.getName().contains(controllerIdentifier)) || 
				(actionIsController != null && actionIsController.run(this, c));
	}

	public boolean isRangeActive(String[] fieldArgs){
		boolean active = false;
		for(int i = 0; i < fieldArgs.length; i++){
			if(getValue(fieldArgs[i]) != 0) active = true;
		}
		return active;
	}

	public boolean isRangeActive(String[] fieldArgs, int controllerIndexArg){
		boolean active = false;
		for(int i = 0; i < fieldArgs.length; i++){
			if(getValue(fieldArgs[i], controllerIndexArg) != 0) active = true;
		}
		return active;
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

	@SuppressWarnings("serial")
	static class CPCustomNotFoundException extends RuntimeException{}

}