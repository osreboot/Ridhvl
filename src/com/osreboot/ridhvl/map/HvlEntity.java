package com.osreboot.ridhvl.map;

import java.util.LinkedList;
import java.util.List;

public abstract class HvlEntity {

	private float x, y;
	
	private HvlMap map;
	
	private boolean shouldBeDeleted;
	
	public HvlEntity(float xArg, float yArg, HvlMap mapArg) {
		x = xArg;
		y = yArg;
		map = mapArg;
	}
	
	public abstract void update(float delta);
	
	public abstract void draw(float delta);
	
	public float getX() {
		return x + map.getX();
	}
	
	public float getY() {
		return y + map.getY();
	}
	
	public float getRelX() {
		return x;
	}
	
	public float getRelY() {
		return y;
	}
	
	public void setRelX(float xArg) {
		x = xArg;
	}
	
	public void setRelY(float yArg) {
		y = yArg;
	}
	
	public HvlMap getMap() {
		return map;
	}

	public void delete()
	{
		shouldBeDeleted = true;
	}
	
	public boolean shouldBeDeleted() {
		return shouldBeDeleted;
	}

	public List<Object> getSaveParameters() {
		return new LinkedList<>();
	}
}
