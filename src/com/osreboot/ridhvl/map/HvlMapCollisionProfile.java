package com.osreboot.ridhvl.map;

import com.osreboot.ridhvl.HvlCoord;

public interface HvlMapCollisionProfile {

	public HvlCoord raytrace(HvlCoord start, HvlCoord end, HvlMap map, int layer, int tileX, int tileY);

	public void debugDraw(float delta, HvlMap map, int layer, int tileX, int tileY);
}
