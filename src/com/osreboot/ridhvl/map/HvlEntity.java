package com.osreboot.ridhvl.map;

import java.util.LinkedList;
import java.util.List;

import com.osreboot.ridhvl.HvlCoord;

public abstract class HvlEntity {

	private HvlCoord pos;
	
	private HvlMap map;
	
	private boolean shouldBeDeleted;
	
	public HvlEntity(float xArg, float yArg, HvlMap mapArg) {
		pos = new HvlCoord(xArg, yArg);
		map = mapArg;
	}
	
	public abstract void update(float delta);
	
	public abstract void draw(float delta);
	
	public float getX() {
		return pos.x + map.getX();
	}
	
	public float getY() {
		return pos.y + map.getY();
	}
	
	public float getRelX() {
		return pos.x;
	}
	
	public float getRelY() {
		return pos.y;
	}
	
	public void setRelX(float xArg) {
		pos.x = xArg;
	}
	
	public void setRelY(float yArg) {
		pos.y = yArg;
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
