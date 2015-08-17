package com.osreboot.ridhvl.tile.entity;

import com.osreboot.ridhvl.tile.HvlLayeredTileMap;

public abstract class HvlEntity {

	private float x, y;
	private HvlLayeredTileMap parent;
	private boolean markedForDeletion;

	public HvlEntity(float xArg, float yArg, HvlLayeredTileMap parentArg) {
		this.x = xArg;
		this.y = yArg;
		this.parent = parentArg;
	}

	public abstract void update(float delta);

	public abstract void draw(float delta);

	public float getX() {
		return x;
	}

	public int getTileX() {
		return (int) ((x - parent.getX()) / parent.getTileWidth());
	}
	
	public float getWorldX() {
		return parent.getX() + x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public int getTileY() {
		return (int) ((y - parent.getY()) / parent.getTileHeight());
	}
	
	public float getWorldY() {
		return parent.getY() + y;
	}

	public void setY(float y) {
		this.y = y;
	}

	/**
	 * Deletes this entity from the tilemap after the current update loop (will
	 * still draw and finish updating before removal).
	 */
	public void delete() {
		markedForDeletion = true;
	}

	public boolean isMarkedForDeletion() {
		return markedForDeletion;
	}
	
	public String save() {
		return "";
	}

	public HvlLayeredTileMap getParent() {
		return parent;
	}
}
