package com.osreboot.ridhvl.loader;

import java.util.ArrayList;

public abstract class HvlContentSeriesLoader<T> {
	
	@SuppressWarnings("rawtypes")
	public static ArrayList<HvlContentSeriesLoader> loaders = new ArrayList<HvlContentSeriesLoader>();
	
	private String path;
	
	private ArrayList<ArrayList<T>> resources;
	
	public HvlContentSeriesLoader(String pathArg){
		path = pathArg;
		resources = new ArrayList<ArrayList<T>>();
		loaders.add(this);
	}
	
	public int addResource(ArrayList<T> resourceArg){//TODO exception for not enough room in array
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
	
	public ArrayList<T> getResource(int indexArg){
		return resources.get(indexArg);
	}

	public abstract boolean loadResource(String nameArg, int lengthArg);
	public abstract boolean loadResource(String nameArg, int lengthArg, int intervalArg);
	
}