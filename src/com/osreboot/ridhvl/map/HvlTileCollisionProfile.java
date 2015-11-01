package com.osreboot.ridhvl.map;

public abstract class HvlTileCollisionProfile {

	public HvlTileCollisionProfile() {
		
	}

	public abstract void debugDraw(float delta, HvlMap map, int layer, int tileX, int tileY);
}
