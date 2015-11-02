package com.osreboot.ridhvl.map;

import com.osreboot.ridhvl.HvlCoord;

public abstract class HvlTileCollisionProfile {

	public HvlTileCollisionProfile() {

	}

	public abstract HvlCoord raytrace(HvlCoord start, HvlCoord end, HvlMap map, int layer, int tileX, int tileY);

	public abstract void debugDraw(float delta, HvlMap map, int layer, int tileX, int tileY);
}
