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
		state.initialize();
		return true;
	}
	
	public String addStateUniqueName(String nameBase, HvlState state)
	{
		String name = getUniqueName(nameBase);
		states.put(name, state);
		state.initialize();
		return name;
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

	public boolean containsState(String name)
	{
		return states.containsKey(name);
	}
	
	// TODO: This might not work without overriding .equals
	public boolean containsState(HvlState state)
	{
		return states.containsValue(state);
	}

	public String getUniqueName(String baseName)
	{
		if (!states.containsKey(baseName)) return baseName;
		
		int index = -1;
		
		while (states.containsKey(baseName + ++index));
		
		return baseName + index;
	}
}
