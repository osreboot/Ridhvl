package com.osreboot.ridhvl.entity;

import java.util.HashMap;
import java.util.Map;

public abstract class HvlState {
	private Map<String, HvlEntity> entities;
	
	public HvlState()
	{
		entities = new HashMap<>();
	}
	
	public void update(float delta)
	{
		for (Map.Entry<String, HvlEntity> entity : entities.entrySet())
		{
			entity.getValue().update(delta);
		}
	}
	
	public void draw(float delta)
	{
		for (Map.Entry<String, HvlEntity> entity : entities.entrySet())
		{
			entity.getValue().draw(delta);
		}
	}

	public boolean addEntity(String name, HvlEntity state)
	{
		if (entities.containsKey(name)) return false;
		
		entities.put(name, state);
		return true;
	}

	public void removeEntity(String name)
	{
		entities.remove(name);
	}
	
	public HvlEntity getEntity(String name)
	{
		if (!entities.containsKey(name)) return null;
		
		return entities.get(name);
	}

	public boolean containsEntity(String name)
	{
		return entities.containsKey(name);
	}
	
	// TODO: Might not work without overriding .equals
	public boolean containsEntity(HvlEntity entity)
	{
		return entities.containsValue(entity);
	}
}
