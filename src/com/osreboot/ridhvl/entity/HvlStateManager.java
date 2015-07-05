package com.osreboot.ridhvl.entity;

import java.util.HashMap;
import java.util.Map;

public class HvlStateManager {
	private Map<String, HvlState> states;
	
	public HvlStateManager()
	{
		states = new HashMap<>();
	}
	
	public void update(float delta)
	{
		for (Map.Entry<String, HvlState> entry : states.entrySet())
		{
			entry.getValue().update(delta);
		}
	}
	
	public void draw(float delta)
	{
		for (Map.Entry<String, HvlState> entry : states.entrySet())
		{
			entry.getValue().draw(delta);
		}
	}
	
	public boolean addState(String name, HvlState state)
	{
		if (states.containsKey(name)) return false;
		
		states.put(name, state);
		return true;
	}

	public void removeState(String name)
	{
		states.remove(name);
	}
	
	public HvlState getState(String name)
	{
		if (!states.containsKey(name)) return null;
		
		return states.get(name);
	}
}
