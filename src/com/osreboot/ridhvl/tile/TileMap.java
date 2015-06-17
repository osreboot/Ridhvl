package com.osreboot.ridhvl.tile;

import org.newdawn.slick.opengl.Texture;

public class TileMap {

	public class TileMapInfo
	{
		public TileMapInfo(Texture texture, int tileWidth, int tileHeight) {
			super();
			this.texture = texture;
			this.tileWidth = tileWidth;
			this.tileHeight = tileHeight;
		}
		public Texture texture;
		public int tileWidth, tileHeight;
	}
	
	private TileMapInfo info;
	private Tile[] tiles;
	private float tileWidth, tileHeight;
	private int mapWidth, mapHeight;
	private float x, y;

	public TileMap(Texture tArg, int tilesAcrossArg, int tilesTallArg,
			int mapWidthArg, int mapHeightArg, float xArg, float yArg,
			float tileWidthArg, float tileHeightArg) {
		this.info = new TileMapInfo(tArg, tilesAcrossArg, tilesTallArg);
		this.mapWidth = mapWidthArg;
		this.mapHeight = mapHeightArg;
		this.tileWidth = tileWidthArg;
		this.tileHeight = tileHeightArg;
		this.x = xArg;
		this.y = yArg;
		this.tiles = new Tile[mapWidth * mapHeight];
	}

	public void draw(float delta) {
		for (int currentX = 0; currentX < mapWidth; currentX++)
		{
			for (int currentY = 0; currentY < mapHeight; currentY++)
			{
				Tile current = tiles[mapWidth * currentY + currentX];
				if (current == null) continue;
				
				current.draw(info, x + (tileWidth * currentX),
						y + (tileHeight * currentY), tileWidth,
						tileHeight, delta);
			}
		}
	}

	public Tile getTile(int xArg, int yArg)
	{
		return tiles[yArg * mapWidth + xArg];
	}
	
	public void setTile(int xArg, int yArg, Tile tile)
	{
		tiles[yArg * mapWidth + xArg] = tile;
	}

	public void fill(Tile tile)
	{
		for (int currentX = 0; currentX < mapWidth; currentX++)
		{
			for (int currentY = 0; currentY < mapHeight; currentY++)
			{
				setTile(currentX, currentY, tile);
			}
		}
	}
}
