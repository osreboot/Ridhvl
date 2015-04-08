package com.osreboot.ridhvl.loaders;

import java.lang.reflect.Array;
import java.util.ArrayList;

public abstract class hvlContentLoader<T> {
	
	@SuppressWarnings("rawtypes")
	public static ArrayList<hvlContentLoader> loaders = new ArrayList<hvlContentLoader>();
	
	private String defaultPath;
	
	private T[] resources;
	
	@SuppressWarnings("unchecked")
	public hvlContentLoader(Class<T> c, String defaultPathArg, int arrayLength){
		defaultPath = defaultPathArg;
		resources = (T[]) Array.newInstance(c, arrayLength);
		loaders.add(this);
	}
	
	public int addResource(T resourceArg){
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
