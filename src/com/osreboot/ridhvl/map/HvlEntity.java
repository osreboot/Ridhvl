package com.osreboot.ridhvl.map;

import java.util.LinkedList;
import java.util.List;

import com.osreboot.ridhvl.HvlCoord;

/**
 * Represents an entity in a tilemap. This is essentially an object with a
 * position not bound to the map grid.
 */
public abstract class HvlEntity {

	private HvlCoord pos;

	private HvlMap map;

	private boolean shouldBeDeleted;

	/**
	 * A basic entity constructor.
	 * @param xArg The X position of the entity.
	 * @param yArg The Y position of the entity.
	 * @param mapArg The map that owns this entity (that it was added to)
	 */
	public HvlEntity(float xArg, float yArg, HvlMap mapArg) {
		pos = new HvlCoord(xArg, yArg);
		map = mapArg;
	}

	/**
	 * Called every update loop and should be used for non-drawing-related methods.
	 * @param delta The time (in seconds) since the last update loop.
	 */
	public abstract void update(float delta);

	/**
	 * Called every update loop and should be used for drawing-related methods.
	 * @param delta The time (in seconds) since the last update loop.
	 */
	public abstract void draw(float delta);

	/**
	 * Gets the ABSOLUTE X position of this entity (position relative to the origin, not to the map)
	 * @return The absolute Y position.
	 */
	public float getX() {
		return pos.x + map.getX();
	}

	/**
	 * Gets the ABSOLUTE Y position of this entity (position relative to the origin, not to the map)
	 * @return The absolute Y position.
	 */
	public float getY() {
		return pos.y + map.getY();
	}

	/**
	 * Gets the RELATIVE X position this entity (position relative to the upper left corner of the map)
	 * @return The relative X position.
	 */
	public float getRelX() {
		return pos.x;
	}

	/**
	 * Gets the RELATIVE Y position this entity (position relative to the upper left corner of the map)
	 * @return The relative Y position.
	 */
	public float getRelY() {
		return pos.y;
	}

	/**
	 * Sets the relative X position of this entity
	 * @param xArg The relative X position
	 */
	public void setRelX(float xArg) {
		pos.x = xArg;
	}

	/**
	 * Sets the relative Y position of this entity
	 * @param yArg The relative Y position
	 */
	public void setRelY(float yArg) {
		pos.y = yArg;
	}

	/**
	 * Gets the map containing this entity
	 * @return The map containing this entity.
	 */
	public HvlMap getMap() {
		return map;
	}

	/**
	 * Removes this entity from the map at the end of the next update loop.
	 */
	public void delete() {
		shouldBeDeleted = true;
	}

	/**
	 * Gets whether this entity is marked for deletion.
	 * @return Whether this entity is marked for deletion.
	 */
	public boolean shouldBeDeleted() {
		return shouldBeDeleted;
	}

	/**
	 * Gets a list of parameters to write to a file.
	 * @return The paramters to write to a file (empty by default).
	 */
	public List<Object> getSaveParameters() {
		return new LinkedList<>();
	}
}
