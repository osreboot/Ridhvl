package com.osreboot.ridhvl.menu;

import java.util.HashMap;
import java.util.Map;

public class HvlComponentDefault {
	
	private static Map<Class<? extends HvlComponent>, HvlComponent> defaults = new HashMap<>();
	
	public static void setDefault(HvlComponent component)
	{
		defaults.put(component.getClass(), component);
	}
	
	public static void setDefault(Class<? extends HvlComponent> type, HvlComponent component)
	{
		defaults.put(type, component);
	}
	
	public static boolean hasDefault(Class<? extends HvlComponent> type) {
		return defaults.containsKey(type) && defaults.get(type) != null;
	}
	
	@SuppressWarnings("unchecked")
	public static <T extends HvlComponent> T getDefault(Class<? extends T> type) {
		return (T) defaults.get(type);
	}
}
