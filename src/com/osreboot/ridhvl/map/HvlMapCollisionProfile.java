package com.osreboot.ridhvl.map;

import java.util.List;

import com.osreboot.ridhvl.HvlCoord;

public interface HvlMapCollisionProfile {

	public List<HvlMapRaytraceResult> raytrace(HvlCoord start, HvlCoord end, HvlMap map, int layer, int tileX, int tileY);

	public void debugDraw(float delta, HvlMap map, int layer, int tileX, int tileY);
}
