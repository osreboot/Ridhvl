package com.osreboot.ridhvl.map;

import java.util.List;

import com.osreboot.ridhvl.HvlCoord;

/**
 * Represents a tile collision profile, which can be used to designate how a
 * tile collides with other objects.
 */
public interface HvlMapCollisionProfile {

	/**
	 * Performs a raytrace with this tile.
	 * 
	 * @param start
	 *            The starting point of the raytrace.
	 * @param end
	 *            The ending point of the raytrace.
	 * @param map
	 *            The map to raytrace with.
	 * @param layer
	 *            The layer to raytrace on.
	 * @param tileX
	 *            The X coordinate of the tile being traced against.
	 * @param tileY
	 *            The Y coordinate of the tile being traced against.
	 * @return A list of collision results with this tile.
	 */
	public List<HvlMapRaytraceResult> raytrace(HvlCoord start, HvlCoord end, HvlMap map, int layer, int tileX,
			int tileY);

	/**
	 * Draws something representing the collision "shape" of this tile (for
	 * example, lines representing collidale surfaces).
	 * 
	 * @param delta
	 *            The time (in seconds) since the last update loop.
	 * @param map
	 *            The map to draw with.
	 * @param layer
	 *            The layer to draw with.
	 * @param tileX
	 *            The X coordinate of the tile being drawn.
	 * @param tileY
	 *            The Y coordinate of the tile being drawn.
	 */
	public void debugDraw(float delta, HvlMap map, int layer, int tileX, int tileY);
}
