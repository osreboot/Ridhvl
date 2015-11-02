package com.osreboot.ridhvl.map;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.newdawn.slick.opengl.Texture;

import com.osreboot.ridhvl.HvlCoord;
import com.osreboot.ridhvl.HvlMath;
import com.osreboot.ridhvl.painter.painter2d.HvlPainter2D;

public class HvlMap {

	private float x, y;
	private float tileWidth, tileHeight;
	private int tilesAcross, tilesTall;

	private Integer[][][] tiles;

	private Texture texture;

	private Map<Integer, HvlTileCollisionProfile> collisionData;

	private List<HvlEntity> entities;
	private List<HvlEntity> entitiesToAdd;

	private boolean collisionDebugDraw;

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
		entities = new LinkedList<>();
		entitiesToAdd = new LinkedList<>();
		collisionData = new HashMap<>();
	}

	public void update(float delta) {
		List<HvlEntity> toRemove = new LinkedList<>();

		for (HvlEntity ent : entitiesToAdd) {
			entities.add(ent);
		}
		entitiesToAdd.clear();

		for (HvlEntity ent : entities) {
			ent.update(delta);
			if (ent.shouldBeDeleted())
				toRemove.add(ent);
		}

		for (HvlEntity tr : toRemove) {
			entities.remove(tr);
		}
	}

	public void draw(float delta) {
		for (int l = 0; l < tiles.length; l++) {
			Integer[][] layer = tiles[l];
			for (int tY = 0; tY < layer.length; tY++) {
				for (int tX = 0; tX < layer[tY].length; tX++) {
					int tile = layer[tY][tX];

					if (tile < 0)
						continue;

					int mapTileX = tile % tilesAcross;
					int mapTileY = tile / tilesAcross;
					HvlPainter2D.hvlDrawQuad(x + (tX * tileWidth), y + (tY * tileHeight), tileWidth, tileHeight,
							(float) mapTileX / tilesAcross, (float) mapTileY / tilesTall,
							((float) mapTileX / tilesAcross) + (1.0f / tilesAcross),
							((float) mapTileY / tilesTall) + (1.0f / tilesTall), texture);
				}
			}
		}

		if (collisionDebugDraw) {
			for (int l = 0; l < tiles.length; l++) {
				Integer[][] layer = tiles[l];
				for (int tY = 0; tY < layer.length; tY++) {
					for (int tX = 0; tX < layer[tY].length; tX++) {
						int tile = layer[tY][tX];

						if (tile < 0)
							continue;

						if (collisionData.containsKey(tile)) {
							collisionData.get(tile).debugDraw(delta, this, l, tX, tY);
						}
					}
				}
			}
		}

		for (HvlEntity ent : entities) {
			ent.draw(delta);
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

	public void fill(int layer, int tile) {
		fill(layer, 0, 0, getMapWidth() - 1, getMapHeight() - 1, tile);
	}

	public void fill(int layer, int sX, int sY, int eX, int eY, int tile) {
		for (int tX = sX; tX <= eX; tX++) {
			for (int tY = sY; tY <= eY; tY++) {
				setTile(layer, tX, tY, tile);
			}
		}
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

	public int getTilesAcross() {
		return tilesAcross;
	}

	public void setTilesAcross(int tilesAcross) {
		this.tilesAcross = tilesAcross;
	}

	public int getTilesTall() {
		return tilesTall;
	}

	public void setTilesTall(int tilesTall) {
		this.tilesTall = tilesTall;
	}

	public Texture getTexture() {
		return texture;
	}

	public void setTexture(Texture texture) {
		this.texture = texture;
	}

	public boolean isCollisionDebugDraw() {
		return collisionDebugDraw;
	}

	public void setCollisionDebugDraw(boolean collisionDebugDraw) {
		this.collisionDebugDraw = collisionDebugDraw;
	}

	public void addEntity(HvlEntity toAdd) {
		entitiesToAdd.add(toAdd);
	}

	public void mapTileToCollision(Integer tile, HvlTileCollisionProfile profile) {
		collisionData.put(tile, profile);
	}

	public List<HvlCoord> raytrace(final HvlCoord start, HvlCoord end) {
		List<HvlCoord> collisions = new ArrayList<HvlCoord>();

		for (int l = 0; l < tiles.length; l++) {
			Integer[][] layer = tiles[l];
			for (int tY = 0; tY < layer.length; tY++) {
				for (int tX = 0; tX < layer[tY].length; tX++) {
					int tile = layer[tY][tX];

					if (tile < 0)
						continue;

					if (collisionData.containsKey(tile)) {
						HvlCoord coll = collisionData.get(tile).raytrace(start, end, this, l, tX, tY);
						if (coll != null)
							collisions.add(coll);
					}
				}
			}
		}

		Collections.sort(collisions, new Comparator<HvlCoord>() {

			@Override
			public int compare(HvlCoord o1, HvlCoord o2) {
				float d1 = HvlMath.distance(start.x, start.y, o1.x, o1.y);
				float d2 = HvlMath.distance(start.x, start.y, o2.x, o2.y);
				if (d1 < d2)
					return -1;
				if (d1 > d2)
					return 1;
				return 0;
			}
		});

		return collisions;
	}

	public List<HvlCoord> raytrace(final HvlCoord start, HvlCoord end, int... layers) {
		List<HvlCoord> collisions = new ArrayList<HvlCoord>();

		for (int l : layers) {
			Integer[][] layer = tiles[l];
			for (int tY = 0; tY < layer.length; tY++) {
				for (int tX = 0; tX < layer[tY].length; tX++) {
					int tile = layer[tY][tX];

					if (tile < 0)
						continue;

					if (collisionData.containsKey(tile)) {
						HvlCoord coll = collisionData.get(tile).raytrace(start, end, this, l, tX, tY);
						if (coll != null)
							collisions.add(coll);
					}
				}
			}
		}

		Collections.sort(collisions, new Comparator<HvlCoord>() {

			@Override
			public int compare(HvlCoord o1, HvlCoord o2) {
				float d1 = HvlMath.distance(start.x, start.y, o1.x, o1.y);
				float d2 = HvlMath.distance(start.x, start.y, o2.x, o2.y);
				if (d1 < d2)
					return -1;
				if (d1 > d2)
					return 1;
				return 0;
			}
		});
		
		return collisions;
	}
}
