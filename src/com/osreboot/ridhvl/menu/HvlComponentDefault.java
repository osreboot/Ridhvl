package com.osreboot.ridhvl.menu;

import java.util.HashMap;

@Deprecated //remove this when the class becomes usable
public class HvlComponentDefault<T extends HvlComponent> {

	private static HashMap<Class<? extends HvlComponent>, HvlComponentDefault<? extends HvlComponent>> defaults = new HashMap<>();
	
	public static void setDefault(Class<? extends HvlComponent> c, HvlComponentDefault<? extends HvlComponent> d){
		defaults.put(c, d);
	}
	
	public static HvlComponentDefault<? extends HvlComponent> getDefault(Class<? extends HvlComponent> c){
		return defaults.get(c);
	}
	
	private T instance;
	
	public HvlComponentDefault(T instanceArg){
		instance = instanceArg;
	}
	
}
