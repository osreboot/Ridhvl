package com.osreboot.ridhvl.loader;

import java.util.ArrayList;

public abstract class HvlContentLoader<T> {
	
	public static ArrayList<HvlContentLoader<?>> loaders = new ArrayList<HvlContentLoader<?>>();
	
	private String path;
	
	private ArrayList<T> resources;
	
	public HvlContentLoader(String pathArg){
		path = pathArg;
		resources = new ArrayList<T>();
		loaders.add(this);
	}
	
	public int addResource(T resourceArg){//TODO exception for not enough room in array
		int index = 0;
		for(int i = 0; i < resources.size(); i++){
			if(resources.get(i) == null){
				index = i;
				break;
			}
		}
		resources.add(resourceArg);
		return index;
	}
	
	public String getPath(){
		return path;
	}
	
	public T getResource(int indexArg){
		return resources.get(indexArg);
	}

	public abstract boolean loadResource(String nameArg);
	
}