package com.osreboot.ridhvl.entity;

import java.util.HashMap;
import java.util.Map;

public abstract class HvlState {
	private Map<String, HvlEntity> entities;
	
	public void initialize()
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

	public boolean addEntity(String name, HvlEntity entity)
	{
		if (entities.containsKey(name)) return false;
		
		entities.put(name, entity);
		entity.initialize();
		return true;
	}
	
	public String addEntityUniqueName(String nameBase, HvlEntity entity)
	{
		String name = getUniqueName(nameBase);
		entities.put(name, entity);
		entity.initialize();
		return name;
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

	public String getUniqueName(String baseName)
	{
		if (!entities.containsKey(baseName)) return baseName;
		
		int index = -1;
		
		while (entities.containsKey(baseName + ++index));
		
		return baseName + index;
	}
}
