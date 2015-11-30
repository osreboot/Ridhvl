package com.osreboot.ridhvl.loader;

import java.util.ArrayList;
import java.util.HashMap;

public class HvlSequentialLoader{

	private int progress;
	private HashMap<String, ? extends HvlContentLoader<?>> objects;

	public HvlSequentialLoader(HashMap<String, ? extends HvlContentLoader<?>> objectsArg){
		objects = objectsArg;
		progress = 0;
	}

	public void loadObject(){
		if(progress < objects.size()){
			String resource = new ArrayList<String>(objects.keySet()).get(progress);
			objects.get(resource).loadResource(resource);
			progress++;
		}
	}

	public boolean isFinished(){
		return progress == objects.size();
	}

	public float getProgress(){
		return (float)progress/objects.size();
	}

	public HashMap<String, ? extends HvlContentLoader<?>> getObjects(){
		return objects;
	}

}
