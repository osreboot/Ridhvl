package com.osreboot.ridhvl.map;

import com.osreboot.ridhvl.HvlCoord;
import com.osreboot.ridhvl.HvlMath;

/**
 * Represents a raytrace result from calling some version of HvlMap.raytrace().
 */
public class HvlMapRaytraceResult implements Comparable<HvlMapRaytraceResult>{
	/**
	 * Represents a source that came from hitting a map tile.
	 */
	public static class MapTileTraceSource {
		private int tileX, tileY;
		private int layer;
		private int tile;

		/**
		 * A basic constructor.
		 * @param layer The layer of the collision.
		 * @param tileX The X coordinate of the collision.
		 * @param tileY The Y coordinate of the collision.
		 * @param tile The actual tile hit.
		 */
		public MapTileTraceSource(int layer, int tileX, int tileY, int tile) {
			this.layer = layer;
			this.tileX = tileX;
			this.tileY = tileY;
			this.tile = tile;
		}

		/**
		 * Gets the X coordinate of the collision.
		 * @return The X coordinate of the collision.
		 */
		public int getTileX() {
			return tileX;
		}

		/**
		 * Gets the Y coordinate of the collision.
		 * @return The Y coordinate of the collision.
		 */
		public int getTileY() {
			return tileY;
		}
		
		/**
		 * Gets the layer of the collision.
		 * @return The layer of the collision.
		 */
		public int getLayer() {
			return layer;
		}

		/**
		 * Gets the tile of the collision.
		 * @return The tile of the collision.
		 */
		public int getTile() {
			return tile;
		}
	}

	private Object source;
	private HvlCoord location;
	private HvlCoord traceStart, traceEnd;

	/**
	 * A basic constructor.
	 * @param source What the raytrace result actually hit.
	 * @param location The exact location that the raytrace hit.
	 * @param traceStart The starting point of the raytrace.
	 * @param traceEnd The ending point of the raytrace.
	 */
	public HvlMapRaytraceResult(Object source, HvlCoord location, HvlCoord traceStart, HvlCoord traceEnd) {
		super();
		this.source = source;
		this.location = location;
		this.traceStart = traceStart;
		this.traceEnd = traceEnd;
	}

	/**
	 * Gets what the raytrace actually hit.
	 * @return What the raytrace actually hit.
	 */
	public Object getSource() {
		return source;
	}

	/**
	 * Gets the specific location that the raytrace hit.
	 * @return The specific location that the raytrace hit.
	 */
	public HvlCoord getLocation() {
		return location;
	}

	/**
	 * Gets the starting location of the raytrace.
	 * @return The starting location of the raytrace.
	 */
	public HvlCoord getTraceStart() {
		return traceStart;
	}

	/**
	 * Gets the ending location of the raytrace.
	 * @return The ending location of the raytrace.
	 */
	public HvlCoord getTraceEnd() {
		return traceEnd;
	}

	@Override
	public int compareTo(HvlMapRaytraceResult o) {
		float d1 = HvlMath.distance(traceStart.x, traceStart.y, location.x, location.y);
		float d2 = HvlMath.distance(traceStart.x, traceStart.y, o.location.x, o.location.y);
		if (d1 < d2)
			return 1;
		if (d1 > d2)
			return -1;
		return 0;
	}
}
