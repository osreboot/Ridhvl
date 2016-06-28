package com.osreboot.ridhvl.input;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;

import net.java.games.input.Controller;

import com.osreboot.ridhvl.action.HvlAction2r;

public abstract class HvlCPGroup extends HvlControllerProfile{

	private ArrayList<HvlControllerProfile> profiles;
	private LinkedHashMap<String, HvlPollValueGroup> staticPollGroupAnnotations = new LinkedHashMap<>();
	
	public HvlCPGroup(Class<? extends HvlControllerProfile> hostArg){
		super(hostArg, new HvlAction2r<Boolean, HvlControllerProfile, Controller>(){
			@Override
			public Boolean run(HvlControllerProfile p, Controller c){
				for(HvlControllerProfile pr : ((HvlCPGroup)p).getSubProfiles()){
					if(pr.isOfType(c)) return true;
				}
				return false;
			}
		});
		profiles = new ArrayList<>(Arrays.asList(instantiateSubProfiles()));
		
		String value = null;
		for(Field f : hostArg.getDeclaredFields()){
			if(f.isAnnotationPresent(HvlPollValueGroup.class)){
				if(f.getType() == String.class){
					try{
						value = (String)f.get(hostArg);
					}catch(IllegalArgumentException | IllegalAccessException e){
						e.printStackTrace();
					}
					if(f.getAnnotation(HvlPollValueGroup.class).value().length == profiles.size()) staticPollGroupAnnotations.put(value, f.getAnnotation(HvlPollValueGroup.class));
					else throw new CPIncorrectArraySizeException();
				}else throw new CPImproperTypeException();
			}
		}
		
		syncControllers();
	}
	
	public abstract HvlControllerProfile[] instantiateSubProfiles();
	
	@Override
	public void syncControllers(){
		if(profiles != null) for(HvlControllerProfile p : profiles) p.syncControllers();
	}

	@Override
	public void pollValues(){
		for(HvlControllerProfile p : profiles) p.pollValues();
	}

	@Override
	public float getValue(String fieldArg){
		float output = 0;
		for(HvlControllerProfile p : profiles){
			if(!staticPollGroupAnnotations.get(fieldArg).value()[profiles.indexOf(p)].equals(""))
				output += p.getValue(staticPollGroupAnnotations.get(fieldArg).value()[profiles.indexOf(p)]);
		}
		output = (float)Math.max(staticPollGroupAnnotations.get(fieldArg).min(), Math.min(staticPollGroupAnnotations.get(fieldArg).max(), output));
		return output;
	}

	@Override
	public float getValue(String fieldArg, int controllerIndex){
		float output = 0;
		for(HvlControllerProfile p : profiles){
			if(!staticPollGroupAnnotations.get(fieldArg).value()[profiles.indexOf(p)].equals("") && p.getControllerIndexes().size() > controllerIndex)
			output += p.getValue(staticPollGroupAnnotations.get(fieldArg).value()[profiles.indexOf(p)], controllerIndex);
		}
		output = (float)Math.max(staticPollGroupAnnotations.get(fieldArg).min(), Math.min(staticPollGroupAnnotations.get(fieldArg).max(), output));
		return output;
	}

	public ArrayList<HvlControllerProfile> getSubProfiles(){
		return profiles;
	}

	@SuppressWarnings("serial")
	static class CPIncorrectArraySizeException extends RuntimeException{}
	
}
