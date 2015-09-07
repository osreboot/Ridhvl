package com.osreboot.ridhvl.loader;

import java.util.ArrayList;

public class HvlSequentialLoader <T extends HvlContentLoader<?>>{

	private T loader;
	private int progress;
	private ArrayList<String> objects;
	
	public HvlSequentialLoader(T loaderArg, ArrayList<String> objectsArg){
		loader = loaderArg;
		objects = objectsArg;
		progress = 0;
	}

	//TODO update and draw
	
	public T getLoader(){
		return loader;
	}

	public int getProgress(){
		return progress;
	}

	public ArrayList<String> getObjects(){
		return objects;
	}
	
}
