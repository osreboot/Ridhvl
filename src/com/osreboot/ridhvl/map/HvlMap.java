package com.osreboot.ridhvl.map;

import java.util.Map;

import org.newdawn.slick.opengl.Texture;

import com.osreboot.ridhvl.painter.painter2d.HvlPainter2D;

public class HvlMap {

	private float x, y;
	private float tileWidth, tileHeight;
	private int tilesAcross, tilesTall;

	private Integer[][][] tiles;

	private Texture texture;

	private Map<Integer, HvlTileCollisionProfile> collisionData;

	public HvlMap(float xArg, float yArg, float tWidthArg, float tHeightArg, int tilesAcrossArg, int tilesTallArg,
			int layersArg, int mWidthArg, int mHeightArg, Texture tArg) {
		x = xArg;
		y = yArg;
		tileWidth = tWidthArg;
		tileHeight = tHeightArg;
		tilesAcross = tilesAcrossArg;
		tilesTall = tilesTallArg;
		texture = tArg;
		tiles = new Integer[layersArg][][];
		for (int l = 0; l < layersArg; l++) {
			tiles[l] = new Integer[mHeightArg][];
			for (int x = 0; x < mHeightArg; x++) {
				tiles[l][x] = new Integer[mWidthArg];
				for (int y = 0; y < mWidthArg; y++) {
					tiles[l][x][y] = -1;
				}
			}
		}
	}

	public void draw(float delta) {
		for (int l = 0; l < tiles.length; l++) {
			Integer[][] layer = tiles[l];
			for (int tY = 0; tY < layer.length; tY++) {
				for (int tX = 0; tX < layer[tY].length; tX++) {
					int tile = layer[tY][tX];

					if (tile < 0) continue;
					
					int mapTileX = tile % tilesAcross;
					int mapTileY = tile / tilesAcross;
					HvlPainter2D.hvlDrawQuad(x + (tX * tileWidth), y + (tY * tileHeight), tileWidth, tileHeight, (float) mapTileX / tilesAcross,
							(float) mapTileY / tilesTall, ((float) mapTileX / tilesAcross) + (1.0f / tilesAcross),
							((float) mapTileY / tilesTall) + (1.0f / tilesTall), texture);
				}
			}
		}
	}

	public int getMapWidth() {
		return tiles[0][0].length;
	}
	
	public int getMapHeight() {
		return tiles[0].length;
	}
	
	public void setTile(int layer, int x, int y, int tile) {
		tiles[layer][y][x] = tile;
	}

	public int getTile(int layer, int x, int y) {
		return tiles[layer][y][x];
	}

	public void fill(int layer, int tile)
	{
		fill(layer, 0, 0, getMapWidth() - 1, getMapHeight() - 1, tile);
	}
	
	public void fill(int layer, int sX, int sY, int eX, int eY, int tile)
	{
		for (int tX = sX; tX <= eX; tX++)
		{
			for (int tY = sY; tY <= eY; tY++)
			{
				setTile(layer, tX, tY, tile);
			}
		}
	}
}
