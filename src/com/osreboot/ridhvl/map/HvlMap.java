package com.osreboot.ridhvl.map;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;
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
import com.osreboot.ridhvl.HvlReflectionUtil;
import com.osreboot.ridhvl.painter.painter2d.HvlPainter2D;

public class HvlMap {

	private float x, y;
	private float tileWidth, tileHeight;
	private int tilesAcross, tilesTall;

	private int[][][] tiles;
	private boolean[] layerCollisionsEnabled;

	private Texture texture;

	private Map<Integer, HvlMapCollisionProfile> collisionData;

	private List<HvlEntity> entities;
	private List<HvlEntity> entitiesToAdd;

	private boolean collisionDebugDraw;

	private float overdrawAmount;

	public HvlMap(float xArg, float yArg, float tWidthArg, float tHeightArg, int tilesAcrossArg, int tilesTallArg,
			int layersArg, int mWidthArg, int mHeightArg, Texture tArg) {
		x = xArg;
		y = yArg;
		tileWidth = tWidthArg;
		tileHeight = tHeightArg;
		tilesAcross = tilesAcrossArg;
		tilesTall = tilesTallArg;
		texture = tArg;
		layerCollisionsEnabled = new boolean[layersArg];
		tiles = new int[layersArg][][];
		for (int l = 0; l < layersArg; l++) {
			tiles[l] = new int[mHeightArg][];
			layerCollisionsEnabled[l] = true;
			for (int x = 0; x < mHeightArg; x++) {
				tiles[l][x] = new int[mWidthArg];
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
			int[][] layer = tiles[l];
			for (int tY = 0; tY < layer.length; tY++) {
				for (int tX = 0; tX < layer[tY].length; tX++) {
					int tile = layer[tY][tX];

					if (tile < 0)
						continue;

					int mapTileX = tile % tilesAcross;
					int mapTileY = tile / tilesAcross;
					HvlPainter2D.hvlDrawQuad(x + (tX * tileWidth), y + (tY * tileHeight), tileWidth + overdrawAmount,
							tileHeight + overdrawAmount, (float) mapTileX / tilesAcross, (float) mapTileY / tilesTall,
							((float) mapTileX / tilesAcross) + (1.0f / tilesAcross),
							((float) mapTileY / tilesTall) + (1.0f / tilesTall), texture);
				}
			}
		}

		if (collisionDebugDraw) {
			for (int l = 0; l < tiles.length; l++) {
				if (!layerCollisionsEnabled[l])
					continue;
				int[][] layer = tiles[l];
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

	public void mapTileToCollision(int tile, HvlMapCollisionProfile profile) {
		collisionData.put(tile, profile);
	}

	public void setLayerCollisonEnabled(int layer, boolean enabled) {
		layerCollisionsEnabled[layer] = enabled;
	}

	public boolean getLayerCollisionEnabled(int layer) {
		return layerCollisionsEnabled[layer];
	}

	public List<HvlCoord> raytrace(final HvlCoord start, HvlCoord end) {
		List<HvlCoord> collisions = new ArrayList<HvlCoord>();

		for (int l = 0; l < tiles.length; l++) {
			if (!layerCollisionsEnabled[l])
				continue;
			int[][] layer = tiles[l];
			
			int yT1 = worldYToTile(start.y);
			int yT2 = worldYToTile(end.y);
			
			int xT1 = worldXToTile(start.x);
			int xT2 = worldXToTile(end.x);
			
			for (int tY = Math.max(0, Math.min(yT1, yT2) - 1); tY < Math.min(layer.length, Math.max(yT1, yT2) + 1); tY++) {
				for (int tX = Math.max(0, Math.min(xT1, xT2) - 1); tX < Math.min(layer[tY].length, Math.max(xT1, xT2) + 1); tX++) {
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
			if (!layerCollisionsEnabled[l])
				continue;
			int[][] layer = tiles[l];
			
			int yT1 = worldYToTile(start.y);
			int yT2 = worldYToTile(end.y);
			
			int xT1 = worldXToTile(start.x);
			int xT2 = worldXToTile(end.x);
			
			for (int tY = Math.max(0, Math.min(yT1, yT2) - 1); tY < Math.min(layer.length, Math.max(yT1, yT2) + 1); tY++) {
				for (int tX = Math.max(0, Math.min(xT1, xT2) - 1); tX < Math.min(layer[tY].length, Math.max(xT1, xT2) + 1); tX++) {
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

	public static HvlMap load(String path, float x, float y, float tileWidth, float tileHeight, Texture tilemap,
			int tilemapWidth, int tilemapHeight) {
		try {
			BufferedReader read = new BufferedReader(new FileReader("res/" + path + ".hvlmap"));

			String first = read.readLine();
			String[] firstParts = first.split(",");
			int tilesAcross = Integer.parseInt(firstParts[0]);
			int tilesTall = Integer.parseInt(firstParts[1]);
			int layers = Integer.parseInt(firstParts[2]);

			HvlMap tr = new HvlMap(x, y, tileWidth, tileHeight, tilemapWidth, tilemapHeight, layers, tilesAcross,
					tilesTall, tilemap);

			int mode = -1;
			int layer = -1;
			int row = 0;

			String line;

			while ((line = read.readLine()) != null) {
				if (line.equals("BeginMap") && mode == -1) {
					mode = 0;
					continue;
				}
				if (line.equals("EndMap") && mode == 0) {
					mode = -1;
					continue;
				}
				if (line.equals("BeginEntities") && mode == -1) {
					mode = 1;
					continue;
				}
				if (line.equals("EndEntities") && mode == 1) {
					mode = -1;
					continue;
				}

				switch (mode) {
				case 0: {
					if (line.startsWith("Layer:")) {
						layer = Integer.parseInt(line.replaceFirst("Layer:", ""));
						row = 0;
						continue;
					}
					String[] spl = line.split(",");
					for (int i = 0; i < spl.length; i++) {
						if (spl[i].isEmpty())
							continue;
						tr.setTile(layer, i, row, Integer.parseInt(spl[i]));
					}
					row++;
				}
					break;
				case 1: {
					String[] spl = line.split(",");
					String className = spl[0];
					float entX = Float.parseFloat(spl[1]);
					float entY = Float.parseFloat(spl[2]);

					Class<?> entClass = Class.forName(className);
					if (!HvlEntity.class.isAssignableFrom(entClass))
						throw new Exception("Class " + className + " is not of type HvlEntity.");

					Constructor<?>[] constructors = entClass.getConstructors();

					for (Constructor<?> constructor : constructors) {
						// If this doesn't meet the minimal constructor
						// requirements, skip it.
						if (constructor.getParameterTypes().length < 3)
							continue;

						// If this doesn't have the same number of arguments as
						// given in the file, skip it.
						if (spl.length != constructor.getParameterTypes().length)
							continue;

						try {
							Object[] params = new Object[constructor.getParameterTypes().length];
							params[0] = entX;
							params[1] = entY;
							params[2] = tr;
							for (int i = 3; i < spl.length; i++) {
								params[i] = HvlReflectionUtil.genericParse(constructor.getParameterTypes()[i], spl[i]);
							}
							HvlEntity newEnt = (HvlEntity) constructor.newInstance(params);

							tr.addEntity(newEnt);
							break;
						} catch (Exception e) {

						}
					}
				}
					break;
				}
			}

			read.close();

			return tr;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public void save(String path) {
		try {
			BufferedWriter write = new BufferedWriter(new FileWriter("res/" + path + ".hvlmap"));

			write.write(tilesAcross + "," + tilesTall + "," + tiles.length + System.lineSeparator());

			write.write("BeginMap" + System.lineSeparator());

			for (int l = 0; l < tiles.length; l++) {
				int[][] layer = tiles[l];
				write.write("Layer:" + l + System.lineSeparator());
				for (int tY = 0; tY < layer.length; tY++) {
					for (int tX = 0; tX < layer[tY].length; tX++) {
						int tile = layer[tY][tX];

						write.write(tile + ",");
					}
					write.write(System.lineSeparator());
				}
			}

			write.write("EndMap" + System.lineSeparator());

			if (!entities.isEmpty() || !entitiesToAdd.isEmpty()) {
				write.write("BeginEntities" + System.lineSeparator());

				for (HvlEntity ent : entities) {
					write.write(ent.getClass().getName() + "," + ent.getRelX() + "," + ent.getRelY()
							+ System.lineSeparator());
				}
				for (HvlEntity ent : entitiesToAdd) {
					write.write(ent.getClass().getName() + "," + ent.getRelX() + "," + ent.getRelY());
					List<Object> toWrite = ent.getSaveParameters();
					for (Object o : toWrite) {
						write.write("," + o.toString());
					}
					write.write(System.lineSeparator());
				}

				write.write("EndEntities" + System.lineSeparator());
			}

			write.close();

		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
	}

	public float getOverdrawAmount() {
		return overdrawAmount;
	}

	public void setOverdrawAmount(float overdrawAmount) {
		this.overdrawAmount = overdrawAmount;
	}

	public float tileXToWorld(int tX) {
		return x + (tileWidth * tX);
	}

	public float tileYToWorld(int tY) {
		return y + (tileHeight * tY);
	}
	
	public int worldXToTile(float wX) {
		return (int)((wX - x) / tileWidth);
	}
	
	public int worldYToTile(float wY) {
		return (int)((wY - y) / tileHeight);
	}
}
