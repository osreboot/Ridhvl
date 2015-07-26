package com.osreboot.ridhvl.tile;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.newdawn.slick.opengl.Texture;

import com.osreboot.ridhvl.painter.painter2d.HvlPainter2D;

public class HvlTileMap {

	public class TileMapInfo {
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
	private HvlTile[] tiles;
	private float tileWidth, tileHeight;
	private int mapWidth, mapHeight;
	private float x, y;
	private boolean cutOff;
	private float xLeft, xRight, yTop, yBottom;
	private float opacity;

	public HvlTileMap(Texture tArg, int tilesAcrossArg, int tilesTallArg,
			int mapWidthArg, int mapHeightArg, float xArg, float yArg,
			float tileWidthArg, float tileHeightArg) {
		this.info = new TileMapInfo(tArg, tilesAcrossArg, tilesTallArg);
		this.mapWidth = mapWidthArg;
		this.mapHeight = mapHeightArg;
		this.tileWidth = tileWidthArg;
		this.tileHeight = tileHeightArg;
		this.x = xArg;
		this.y = yArg;
		this.tiles = new HvlTile[mapWidth * mapHeight];
		this.opacity = 1.0f;
	}

	public void draw(float delta) {
//		if (mapWidth > 0 && mapHeight > 0)
//		{
//			HvlTile current = tiles[mapWidth * 0 + 0];
//			if (current != null)
//			{
//				float xMin = x + (tileWidth * 0);
//				float yMin = y + (tileHeight * 0);
//				
//				current.draw(info, xMin, yMin, tileWidth, tileHeight, delta, true, opacity);
//			}
//		}
				
		HvlPainter2D.hvlForceRefresh();
		
		for (int currentX = 0; currentX < mapWidth; currentX++) {
			for (int currentY = 0; currentY < mapHeight; currentY++) {
//				System.out.println("Mag: " + (glGetTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER) == GL11.GL_LINEAR));
//				System.out.println("Min: " + (glGetTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER) == GL11.GL_LINEAR));
				HvlTile current = tiles[mapWidth * currentY + currentX];
				if (current == null)
					continue;

				float xMin = x + (tileWidth * (float) currentX);
				float yMin = y + (tileHeight * (float) currentY);
				float xMax = xMin + tileWidth;
				float yMax = yMin + tileHeight;
				
				boolean inRange = xMax > xLeft && xMin < xRight && yMax > yTop && yMin < yBottom;
				
				current.update(info, delta);
				
				if (!cutOff || inRange)
					current.draw(info, xMin, yMin, tileWidth, tileHeight, delta, opacity);
			}
		}
	}

	public HvlTile getTile(int xArg, int yArg) {
		return tiles[yArg * mapWidth + xArg];
	}

	public void setTile(int xArg, int yArg, HvlTile tile) {
		tiles[yArg * mapWidth + xArg] = tile;
	}

	public void fill(HvlTile tile) {
		for (int currentX = 0; currentX < mapWidth; currentX++) {
			for (int currentY = 0; currentY < mapHeight; currentY++) {
				setTile(currentX, currentY, tile);
			}
		}
	}

	public TileMapInfo getInfo() {
		return info;
	}

	public void setInfo(TileMapInfo info) {
		this.info = info;
	}

	public float getTileWidth() {
		return tileWidth;
	}

	public void setTileWidth(float tileWidth) {
		this.tileWidth = tileWidth;
	}

	public float getTileHeight() {
		return tileHeight;
	}

	public void setTileHeight(float tileHeight) {
		this.tileHeight = tileHeight;
	}

	public int getMapWidth() {
		return mapWidth;
	}

	public void setMapWidth(int mapWidth) {
		this.mapWidth = mapWidth;
	}

	public int getMapHeight() {
		return mapHeight;
	}

	public void setMapHeight(int mapHeight) {
		this.mapHeight = mapHeight;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public static String save(HvlTileMap... maps) {
		StringBuilder sb = new StringBuilder();

		for (HvlTileMap map : maps) {
			sb.append(String.format("<TileMap %d, %d, %d, %d>%n",
					map.getInfo().tileWidth, map.getInfo().tileHeight,
					map.getMapWidth(), map.getMapHeight()));
			for (int currentX = 0; currentX < map.getMapWidth(); currentX++) {
				for (int currentY = 0; currentY < map.getMapHeight(); currentY++) {
					HvlTile tile = map.getTile(currentX, currentY);
					if (tile == null)
						continue;

					sb.append(String.format("<%s, %d, %d>%n", tile.getClass()
							.getName(), currentX, currentY));
					try {
						sb.append((String) tile.getClass()
								.getMethod("save", tile.getClass())
								.invoke(null, tile));
					} catch (IllegalAccessException | IllegalArgumentException
							| InvocationTargetException | NoSuchMethodException
							| SecurityException e) {
						e.printStackTrace();
					}
					sb.append(String.format("%n</%s>%n", tile.getClass()
							.getName()));
				}
			}

			sb.append(String.format("</TileMap>"));
		}

		return sb.toString();
	}

	public static HvlTileMap[] load(String inArg, Texture texArg, float xArg,
			float yArg, float tileWidthArg, float tileHeightArg) {

		List<HvlTileMap> loaded = new ArrayList<>();
		
		Pattern headerPattern = Pattern.compile("\\<TileMap (\\d+), (\\d+), (\\d+), (\\d+)\\>([\\s\\S]*?)\\<\\/TileMap\\>");
		Matcher headerMatcher = headerPattern.matcher(inArg);
		
		while (headerMatcher.find()) {
			int tilesAcross = Integer.parseInt(headerMatcher.group(1).trim());
			int tilesTall = Integer.parseInt(headerMatcher.group(2).trim());
			int mapWidth = Integer.parseInt(headerMatcher.group(3).trim());
			int mapHeight = Integer.parseInt(headerMatcher.group(4).trim());
			HvlTileMap toReturn = new HvlTileMap(texArg, tilesAcross,
					tilesTall, mapWidth, mapHeight, xArg, yArg, tileWidthArg,
					tileHeightArg);

			Pattern p = Pattern
					.compile("\\<(\\S+), (\\d+), (\\d+)\\>([\\s\\S]*?)<\\/\\1>");
			Matcher m = p.matcher(headerMatcher.group(5));
			while (m.find()) {
				int coordX = Integer.parseInt(m.group(2));
				int coordY = Integer.parseInt(m.group(3));

				try {
					Class<?> tileClass = Class.forName(m.group(1));
					
					HvlTile created = (HvlTile) tileClass.getMethod("load",
							String.class).invoke(null, m.group(4));
					toReturn.setTile(coordX, coordY, created);

				} catch (ClassNotFoundException | NoSuchMethodException
						| SecurityException | IllegalAccessException
						| IllegalArgumentException | InvocationTargetException e) {
					e.printStackTrace();
				}
			}

			loaded.add(toReturn);
		}
		return loaded.toArray(new HvlTileMap[0]);
	}

	public void resize(int widthArg, int heightArg)
	{
		HvlTile[] newTiles = new HvlTile[widthArg*heightArg];
		for (int x = 0; x < widthArg; x++)
		{
			for (int y = 0; y < heightArg; y++)
			{
				if (x < getMapWidth() && y < getMapHeight())
				{
					newTiles[y*widthArg+x] = getTile(x, y);
				}
			}
		}
		
		this.tiles = newTiles;
		this.mapWidth = widthArg;
		this.mapHeight = heightArg;
	}

	
	public boolean isCutOff() {
		return cutOff;
	}

	
	public void setCutOff(boolean cutOff) {
		this.cutOff = cutOff;
	}

	
	public float getxLeft() {
		return xLeft;
	}

	
	public void setxLeft(float xLeft) {
		this.xLeft = xLeft;
	}

	
	public float getxRight() {
		return xRight;
	}

	
	public void setxRight(float xRight) {
		this.xRight = xRight;
	}

	
	public float getyTop() {
		return yTop;
	}

	
	public void setyTop(float yTop) {
		this.yTop = yTop;
	}

	
	public float getyBottom() {
		return yBottom;
	}

	
	public void setyBottom(float yBottom) {
		this.yBottom = yBottom;
	}

	public float getOpacity() {
		return opacity;
	}

	public void setOpacity(float opacity) {
		this.opacity = opacity;
	}
}
