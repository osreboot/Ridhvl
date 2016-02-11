package com.osreboot.ridhvl.loader;

import java.util.ArrayList;

public abstract class HvlContentLoader<T> {

	public static final String VALUE_SEPARATOR = ":::";

	public static ArrayList<HvlContentLoader<?>> loaders = new ArrayList<>();

	public static boolean isResourceASeries(String nameArg){
		return nameArg.contains(VALUE_SEPARATOR);
	}

	public static int getSeriesLength(String nameArg){
		return Integer.parseInt(nameArg.split(VALUE_SEPARATOR)[1]);
	}

	public static int getSeriesInterval(String nameArg){
		return nameArg.split(VALUE_SEPARATOR).length > 2 ? Integer.parseInt(nameArg.split(VALUE_SEPARATOR)[2]) : -1;
	}

	private String path;

	private ArrayList<T> resources;
	private ArrayList<ArrayList<T>> resourceSeries;

	public HvlContentLoader(String pathArg){
		path = pathArg;
		resources = new ArrayList<>();
		resourceSeries = new ArrayList<>();
		loaders.add(this);
	}

	public String getPath(){
		return path;
	}

	public T getResource(int indexArg){
		return resources.get(indexArg);
	}

	public ArrayList<T> getResourceSeries(int indexArg){
		return resourceSeries.get(indexArg);
	}

	public ArrayList<T> getResources(){
		return resources;
	}

	public boolean loadResource(String nameArg){
		if(isResourceASeries(nameArg)){
			ArrayList<T> localSeries = new ArrayList<>();
			for(int i = 0; i < getSeriesLength(nameArg); i += getSeriesInterval(nameArg) != -1 ? getSeriesInterval(nameArg) : 1){
				try{
					localSeries.add(loadResourceMeta(String.format(nameArg.split(VALUE_SEPARATOR)[0], i)));
				}catch(Exception e){
					e.printStackTrace();
					return false;
				}
			}
			resourceSeries.add(localSeries);
			return true;
		}else{
			T localResource;
			try{
				localResource = loadResourceMeta(nameArg);
			}catch(Exception e){
				e.printStackTrace();
				return false;
			}
			resources.add(localResource);
			return true;
		}
	}

	public abstract T loadResourceMeta(String nameArg) throws Exception;

	private int localIndex = 0;
	private String localName;
	private ArrayList<T> localSeries = new ArrayList<>();

	protected void loadResourceLocal(String nameArg){
		if(localName != nameArg){
			localName = nameArg;
			localIndex = 0;
		}
		T t = null;
		try{
			t = loadResourceMeta(String.format(nameArg.split(VALUE_SEPARATOR)[0], localIndex));
		}catch(Exception e){
			e.printStackTrace();
		}
		if(t != null) localSeries.add(t);
		localIndex++;
		if(isLocalLoadComplete()){
			resourceSeries.add(localSeries);
			localSeries.clear();
		}
	}

	protected boolean isLocalLoadComplete(){
		return localName != null && localIndex == getSeriesLength(localName);
	}

}