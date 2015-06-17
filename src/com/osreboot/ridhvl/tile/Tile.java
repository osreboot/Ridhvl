package com.osreboot.ridhvl.tile;

public abstract class Tile {	
	public Tile() {
		
	}
	
	public abstract void draw(TileMap.TileMapInfo info, float x, float y, float width, float height, float delta);
}
