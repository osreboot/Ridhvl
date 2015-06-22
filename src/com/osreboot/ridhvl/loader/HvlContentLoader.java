package com.osreboot.ridhvl.loader;

import java.lang.reflect.Array;
import java.util.ArrayList;

public abstract class HvlContentLoader<T> {
	
	@SuppressWarnings("rawtypes")
	public static ArrayList<HvlContentLoader> loaders = new ArrayList<HvlContentLoader>();
	
	private String defaultPath;
	
	private T[] resources;
	
	@SuppressWarnings("unchecked")
	public HvlContentLoader(Class<T> c, String defaultPathArg, int arrayLength){
		defaultPath = defaultPathArg;
		resources = (T[]) Array.newInstance(c, arrayLength);
		loaders.add(this);
	}
	
	public int addResource(T resourceArg){//TODO exception for not enough room in array
		int index = 0;
		for(int i = 0; i < resources.length; i++){
			if(resources[i] == null){
				index = i;
				break;
			}
		}
		resources[index] = resourceArg;
		return index;
	}
	
	public String getDefaultPath(){
		return defaultPath;
	}
	
	public T getResource(int indexArg){
		return resources[indexArg];
	}

	public abstract boolean loadResource(String nameArg);
	
}
