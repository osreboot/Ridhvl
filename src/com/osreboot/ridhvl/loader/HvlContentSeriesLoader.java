package com.osreboot.ridhvl.loader;

import java.lang.reflect.Array;
import java.util.ArrayList;

public abstract class HvlContentSeriesLoader<T> {
	
	@SuppressWarnings("rawtypes")
	public static ArrayList<HvlContentSeriesLoader> loaders = new ArrayList<HvlContentSeriesLoader>();
	
	private String path;
	
	private ArrayList<T>[] resources;
	
	@SuppressWarnings("unchecked")
	public HvlContentSeriesLoader(Class<T> c, String pathArg, int arrayLength){
		path = pathArg;
		resources = (ArrayList<T>[]) Array.newInstance(c, arrayLength);
		loaders.add(this);
	}
	
	public int addResource(ArrayList<T> resourceArg){//TODO exception for not enough room in array
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
	
	public String getPath(){
		return path;
	}
	
	public ArrayList<T> getResource(int indexArg){
		return resources[indexArg];
	}

	public abstract boolean loadResource(String nameArg, int lengthArg);
	
}