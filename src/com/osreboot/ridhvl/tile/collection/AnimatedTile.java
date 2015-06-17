package com.osreboot.ridhvl.tile.collection;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Color;

import com.osreboot.ridhvl.painter.painter2d.HvlPainter2D;
import com.osreboot.ridhvl.tile.Tile;
import com.osreboot.ridhvl.tile.TileMap.TileMapInfo;

public class AnimatedTile extends Tile {

	private List<Integer> tileCoords;
	private int currentTile;
	private float timer, timeBetweenAnimations;
	
	public AnimatedTile(float timeArg, int... tilesArg) {
		if (tilesArg.length == 0)
			throw new IllegalArgumentException("You must have at least one tile in an AnimatedTile.");

		timeBetweenAnimations = timeArg;
		tileCoords = new ArrayList<>();
		
		for (int tile : tilesArg)
		{
			this.tileCoords.add(tile);
		}
	}

	@Override
	public void draw(TileMapInfo info, float x, float y,
			float width, float height, float delta) {
		timer += delta;
		
		if (timer >= timeBetweenAnimations)
		{
			timer = 0;
			currentTile++;
			if (currentTile >= tileCoords.size())
				currentTile = 0;
		}
		
		int tileX = tileCoords.get(currentTile) % info.tileWidth;
		int tileY = tileCoords.get(currentTile) / info.tileHeight;
		
		float uvx1 = (float) tileX / info.tileWidth;
		float uvy1 = (float) tileY / info.tileHeight;
		float uvx2 = (float) (tileX + 1) / info.tileWidth;
		float uvy2 = (float) (tileY + 1) / info.tileHeight;
		
		HvlPainter2D.hvlDrawQuad(x, y, width, height, uvx1, uvy1, uvx2, uvy2, info.texture, Color.white);
	}

}
