package com.osreboot.ridhvl.loader;

import java.util.ArrayList;
import java.util.HashMap;

public class HvlSequentialLoader{

	private int index, rate;
	private HashMap<String, ? extends HvlContentLoader<?>> objects;

	public HvlSequentialLoader(HashMap<String, ? extends HvlContentLoader<?>> objectsArg, int rateArg){
		objects = objectsArg;
		index = 0;
		rate = rateArg;
	}
	
	public HvlSequentialLoader(HashMap<String, ? extends HvlContentLoader<?>> objectsArg){
		objects = objectsArg;
		index = 0;
		rate = 1;
	}

	public void load(){
		for(int i = 0; i < rate; i++){
			loadObject();
		}
	}
	
	private void loadObject(){
		if(index < objects.size()){
			String resource = new ArrayList<String>(objects.keySet()).get(index);
			if(HvlContentLoader.isResourceASeries(resource)){
				if(!objects.get(resource).isLocalLoadComplete()) objects.get(resource).loadResourceLocal(resource);
				else index++;
			}else{
				objects.get(resource).loadResource(resource);
				index++;
			}
		}
	}

	public boolean isFinished(){
		return index == objects.size();
	}

	public float getProgress(){
		return (float)index/objects.size();
	}
	
	public int getRate(){
		return rate;
	}

	public void setRate(int rateArg){
		rate = rateArg;
	}

	public HashMap<String, ? extends HvlContentLoader<?>> getObjects(){
		return objects;
	}

}
