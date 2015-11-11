package com.osreboot.ridhvl.map;

import com.osreboot.ridhvl.HvlCoord;
import com.osreboot.ridhvl.HvlMath;

public class HvlMapRaytraceResult implements Comparable<HvlMapRaytraceResult>{
	public static class MapTileTraceSource {
		private int tileX, tileY;
		private int layer;
		private int tile;

		public MapTileTraceSource(int layer, int tileX, int tileY, int tile) {
			this.layer = layer;
			this.tileX = tileX;
			this.tileY = tileY;
			this.tile = tile;
		}

		public int getTileX() {
			return tileX;
		}

		public int getTileY() {
			return tileY;
		}
		
		public int getLayer() {
			return layer;
		}

		public int getTile() {
			return tile;
		}
	}

	private Object source;
	private HvlCoord location;
	private HvlCoord traceStart, traceEnd;

	public HvlMapRaytraceResult(Object source, HvlCoord location, HvlCoord traceStart, HvlCoord traceEnd) {
		super();
		this.source = source;
		this.location = location;
		this.traceStart = traceStart;
		this.traceEnd = traceEnd;
	}

	public Object getSource() {
		return source;
	}

	public HvlCoord getLocation() {
		return location;
	}

	public HvlCoord getTraceStart() {
		return traceStart;
	}

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
