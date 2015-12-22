package com.osreboot.ridhvl.map;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

import com.osreboot.ridhvl.HvlCoord;
import com.osreboot.ridhvl.HvlReflectionUtil;
import com.osreboot.ridhvl.painter.painter2d.HvlPainter2D;

/**
 * Represents a map made out of rectangular tiles.
 */
public class HvlMap {

	private HvlCoord pos;
	private float tileWidth, tileHeight;
	private int tilesAcross, tilesTall;

	private int[][][] tiles;
	private float[] opacities;
	private boolean[] layerCollisionsEnabled;

	private Texture texture;

	private Map<Integer, HvlMapCollisionProfile> collisionData;

	private List<HvlEntity> entities;
	private List<HvlEntity> entitiesToAdd;

	private boolean collisionDebugDraw;

	private float overdrawAmount;

	/**
	 * Basic constructor.
	 * 
	 * @param xArg
	 *            The X position of the map.
	 * @param yArg
	 *            The Y position of the map.
	 * @param tWidthArg
	 *            The width of a tile in pixels.
	 * @param tHeightArg
	 *            The height of a tile in pixels.
	 * @param tilesAcrossArg
	 *            How many tiles across the tilemap texture is.
	 * @param tilesTallArg
	 *            How many tiles tall the tilemap texture is.
	 * @param layersArg
	 *            The number of layers the map has.
	 * @param mWidthArg
	 *            How wide the map is in tiles.
	 * @param mHeightArg
	 *            How tall the map is in tiles.
	 * @param tArg
	 *            The tilemap texture.
	 */
	public HvlMap(float xArg, float yArg, float tWidthArg, float tHeightArg, int tilesAcrossArg, int tilesTallArg,
			int layersArg, int mWidthArg, int mHeightArg, Texture tArg) {
		pos = new HvlCoord(xArg, yArg);
		tileWidth = tWidthArg;
		tileHeight = tHeightArg;
		tilesAcross = tilesAcrossArg;
		tilesTall = tilesTallArg;
		texture = tArg;
		layerCollisionsEnabled = new boolean[layersArg];
		tiles = new int[layersArg][][];
		opacities = new float[layersArg];
		for (int l = 0; l < layersArg; l++) {
			tiles[l] = new int[mHeightArg][];
			opacities[l] = 1.0f;
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

	/**
	 * Updates the map and entities.
	 * 
	 * @param delta
	 *            The time (in seconds) since the last update loop.
	 */
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

	/**
	 * Draws the map.
	 * 
	 * @param delta
	 *            The time (in seconds) since the last update loop.
	 */
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
					HvlPainter2D.hvlDrawQuad(pos.x + (tX * tileWidth), pos.y + (tY * tileHeight),
							tileWidth + overdrawAmount, tileHeight + overdrawAmount, (float) mapTileX / tilesAcross,
							(float) mapTileY / tilesTall, ((float) mapTileX / tilesAcross) + (1.0f / tilesAcross),
							((float) mapTileY / tilesTall) + (1.0f / tilesTall), texture,
							new Color(1.0f, 1.0f, 1.0f, opacities[l]));
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

	/**
	 * Gets the width of the map in tiles.
	 * 
	 * @return The width of the map in tiles.
	 */
	public int getMapWidth() {
		return tiles[0][0].length;
	}

	/**
	 * Gets the height of the map in tiles.
	 * 
	 * @return The height of the map in tiles.
	 */
	public int getMapHeight() {
		return tiles[0].length;
	}

	/**
	 * Gets how many layers this map has.
	 * 
	 * @return The number of layers this map has.
	 */
	public int getLayerCount() {
		return tiles.length;
	}

	/**
	 * Sets a tile in the map.
	 * 
	 * @param layer
	 *            The layer of the tile.
	 * @param x
	 *            The X position of the tile.
	 * @param y
	 *            The Y position of the tile.
	 * @param tile
	 *            The new tile.
	 */
	public void setTile(int layer, int x, int y, int tile) {
		tiles[layer][y][x] = tile;
	}

	/**
	 * Gets a tile in the map.
	 * 
	 * @param layer
	 *            The layer of the tile.
	 * @param x
	 *            The X position of the tile.
	 * @param y
	 *            The Y position of the tile.
	 * @return The tile at the given location.
	 */
	public int getTile(int layer, int x, int y) {
		return tiles[layer][y][x];
	}

	/**
	 * Fills a layer with the given tile.
	 * 
	 * @param layer
	 *            The layer to fill.
	 * @param tile
	 *            The tile to fill the layer with.
	 */
	public void fill(int layer, int tile) {
		fill(layer, 0, 0, getMapWidth() - 1, getMapHeight() - 1, tile);
	}

	/**
	 * Fills a rectangular area with the given tile.
	 * 
	 * @param layer
	 *            The layer of the rectangle.
	 * @param sX
	 *            The starting X coordinate of the rectangle.
	 * @param sY
	 *            The starting Y coordinate of the rectangle.
	 * @param eX
	 *            The ending X coordinate of the rectangle.
	 * @param eY
	 *            The ending Y coordinate of the rectangle.
	 * @param tile
	 *            The tile to fill the rectangle with.
	 */
	public void fill(int layer, int sX, int sY, int eX, int eY, int tile) {
		for (int tX = sX; tX <= eX; tX++) {
			for (int tY = sY; tY <= eY; tY++) {
				setTile(layer, tX, tY, tile);
			}
		}
	}

	/**
	 * Gets the X position of the map.
	 * 
	 * @return The X position of the map.
	 */
	public float getX() {
		return pos.x;
	}

	/**
	 * Sets the X position of the map.
	 * 
	 * @param x
	 *            The new X position of the map.
	 */
	public void setX(float x) {
		pos.x = x;
	}

	/**
	 * Gets the Y position of the map.
	 * 
	 * @return The Y position of the map.
	 */
	public float getY() {
		return pos.y;
	}

	/**
	 * Sets the Y position of the map.
	 * 
	 * @param y
	 *            The new Y position of the map.
	 */
	public void setY(float y) {
		pos.y = y;
	}

	/**
	 * Gets the width of a tile in pixels.
	 * 
	 * @return The width of a tile in pixels.
	 */
	public float getTileWidth() {
		return tileWidth;
	}

	/**
	 * Sets the width of a tile in pixels.
	 * 
	 * @param tileWidth
	 *            The new width of a tile in pixels.
	 */
	public void setTileWidth(float tileWidth) {
		this.tileWidth = tileWidth;
	}

	/**
	 * Gets the height of a tile in pixels.
	 * 
	 * @return The height of a tile in pixels.
	 */
	public float getTileHeight() {
		return tileHeight;
	}

	/**
	 * Sets the height of a tile in pixels.
	 * 
	 * @param tileHeight
	 *            The new height of a tile in pixels.
	 */
	public void setTileHeight(float tileHeight) {
		this.tileHeight = tileHeight;
	}

	/**
	 * Gets how many tiles wide the tilemap texture is.
	 * 
	 * @return How many tiles wide the tilemap texture is.
	 */
	public int getTilesAcross() {
		return tilesAcross;
	}

	/**
	 * Sets how many tiles wide the tilemap texture is.
	 * 
	 * @param tilesAcross
	 *            How many tiles wide the tilemap texture is.
	 */
	public void setTilesAcross(int tilesAcross) {
		this.tilesAcross = tilesAcross;
	}

	/**
	 * Gets how many tiles tall the tilemap texture is.
	 * 
	 * @return How many tiles tall the tilemap texture is.
	 */
	public int getTilesTall() {
		return tilesTall;
	}

	/**
	 * Sets how many tiles tall the tilemap texture is.
	 * 
	 * @param tilesTall
	 *            How many tiles tall the tilemap texture is.
	 */
	public void setTilesTall(int tilesTall) {
		this.tilesTall = tilesTall;
	}

	/**
	 * Gets the texture of the tilemap.
	 * 
	 * @return The tilemap texture.
	 */
	public Texture getTexture() {
		return texture;
	}

	/**
	 * Sets the texture of the tilemap.
	 * 
	 * @param texture
	 *            The new tilemap texture.
	 */
	public void setTexture(Texture texture) {
		this.texture = texture;
	}

	/**
	 * Gets whether collision debug draw is enabled.
	 * 
	 * @return Whether collision debug draw is enabled.
	 */
	public boolean isCollisionDebugDraw() {
		return collisionDebugDraw;
	}

	/**
	 * Sets whether collision debug draw is enabled.
	 * 
	 * @param collisionDebugDraw
	 *            Whether collision debug draw is enabled.
	 */
	public void setCollisionDebugDraw(boolean collisionDebugDraw) {
		this.collisionDebugDraw = collisionDebugDraw;
	}

	/**
	 * Adds an entity to the map.
	 * 
	 * @param toAdd
	 *            The entity to add.
	 */
	public void addEntity(HvlEntity toAdd) {
		entitiesToAdd.add(toAdd);
	}

	/**
	 * Maps a collision profile to a tile. This means that any instances of this
	 * tile will have the given collision profile.
	 * 
	 * @param tile
	 *            The tile to map to.
	 * @param profile
	 *            The profile to map to the tile.
	 */
	public void mapTileToCollision(int tile, HvlMapCollisionProfile profile) {
		collisionData.put(tile, profile);
	}

	/**
	 * Sets whether collision is enabled for a given layer.
	 * 
	 * @param layer
	 *            The layer to set collision for.
	 * @param enabled
	 *            Whether collision is enabled for this layer or not.
	 */
	public void setLayerCollisonEnabled(int layer, boolean enabled) {
		layerCollisionsEnabled[layer] = enabled;
	}

	/**
	 * Gets whether collision is enabled for a given layer.
	 * 
	 * @param layer
	 *            The layer to check.
	 * @return Whether oollision is enabled for this layer.
	 */
	public boolean getLayerCollisionEnabled(int layer) {
		return layerCollisionsEnabled[layer];
	}

	/**
	 * Traces a line from the start to end point and gets all collisions along
	 * that line.
	 * 
	 * @param start
	 *            The start point of the line.
	 * @param end
	 *            The end point of the line.
	 * @return The collision results along this line, sorted closest to furthest
	 *         from the start point.
	 */
	public List<HvlMapRaytraceResult> raytrace(final HvlCoord start, HvlCoord end) {
		int[] layerIndices = new int[tiles.length];
		for (int i = 0; i < tiles.length; i++)
			layerIndices[i] = i;

		return raytrace(start, end, layerIndices);
	}

	/**
	 * Traces a line from the start to end point and gets all collision along
	 * that line in the given layers.
	 * 
	 * @param start
	 *            The start point of the line.
	 * @param end
	 *            The end point of the line.
	 * @param layers
	 *            The layers to raytrace in.
	 * @return The collision results along this line in the given layers, sorted
	 *         closest to furthest from the start point.
	 */
	public List<HvlMapRaytraceResult> raytrace(final HvlCoord start, HvlCoord end, int... layers) {
		List<HvlMapRaytraceResult> collisions = new ArrayList<HvlMapRaytraceResult>();

		for (int l : layers) {
			if (!layerCollisionsEnabled[l])
				continue;
			int[][] layer = tiles[l];

			int yT1 = worldYToTile(start.y);
			int yT2 = worldYToTile(end.y);

			int xT1 = worldXToTile(start.x);
			int xT2 = worldXToTile(end.x);

			for (int tY = Math.max(0, Math.min(yT1, yT2) - 1); tY < Math.min(layer.length,
					Math.max(yT1, yT2) + 1); tY++) {
				for (int tX = Math.max(0, Math.min(xT1, xT2) - 1); tX < Math.min(layer[tY].length,
						Math.max(xT1, xT2) + 1); tX++) {
					int tile = layer[tY][tX];

					if (tile < 0)
						continue;

					if (collisionData.containsKey(tile)) {
						collisions.addAll(collisionData.get(tile).raytrace(start, end, this, l, tX, tY));
					}
				}
			}
		}

		Collections.sort(collisions);

		return collisions;
	}

	/**
	 * Traces a line from the start to end point and gets all collisions with
	 * entities along that line.
	 * 
	 * @param start
	 *            The start point of the line.
	 * @param end
	 *            The end point of the line.
	 * @return The collision results along this line in the given layers, sorted
	 *         closest to furthest from the start point.
	 */
	public List<HvlMapRaytraceResult> raytraceEntities(final HvlCoord start, HvlCoord end) {
		List<HvlMapRaytraceResult> collisions = new ArrayList<HvlMapRaytraceResult>();

		for (HvlEntity ent : entities) {
			if (!(ent instanceof HvlMapCollisionProfile))
				continue;

			HvlMapCollisionProfile coll = (HvlMapCollisionProfile) ent;

			// Giving negative numbers means that this isn't grid-bound.
			collisions.addAll(coll.raytrace(start, end, this, -1, -1, -1));
		}

		Collections.sort(collisions);

		return collisions;
	}

	/**
	 * Loads a map from file.
	 * 
	 * @param path
	 *            The name of the file to load. This should have the extension
	 *            ".hvlmap". Do not include the extension in the name.
	 * @param x
	 *            The X position of the map once it's loaded.
	 * @param y
	 *            The Y position of the map once it's loaded.
	 * @param tileWidth
	 *            The width of a tile in pixels.
	 * @param tileHeight
	 *            The height of a tile in pixels.
	 * @param tilemap
	 *            The tilemap texture.
	 * @param tilemapWidth
	 *            The width of the tilemap texture in tiles.
	 * @param tilemapHeight
	 *            The height of the tilemap texture in tiles.
	 * @return The loaded map.
	 */
	public static HvlMap load(String path, float x, float y, float tileWidth, float tileHeight, Texture tilemap,
			int tilemapWidth, int tilemapHeight) {
		return load(path, x, y, tileWidth, tileHeight, tilemap, tilemapWidth, tilemapHeight, true);
	}

	/**
	 * Loads a map from file.
	 * 
	 * @param path
	 *            The name of the file to load. This should have the extension
	 *            ".hvlmap". Do not include the extension in the name.
	 * @param x
	 *            The X position of the map once it's loaded.
	 * @param y
	 *            The Y position of the map once it's loaded.
	 * @param tileWidth
	 *            The width of a tile in pixels.
	 * @param tileHeight
	 *            The height of a tile in pixels.
	 * @param tilemap
	 *            The tilemap texture.
	 * @param tilemapWidth
	 *            The width of the tilemap texture in tiles.
	 * @param tilemapHeight
	 *            The height of the tilemap texture in tiles.
	 * @param replaceArbitrary
	 * 			  Whether arbitrary entities should be replaced with their correct version.
	 * @return The loaded map.
	 */
	public static HvlMap load(String path, float x, float y, float tileWidth, float tileHeight, Texture tilemap,
			int tilemapWidth, int tilemapHeight, boolean replaceArbitrary) {
		try {
			BufferedReader read = new BufferedReader(new FileReader(path + ".hvlmap"));

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
						if (constructor.getParameterCount() < 3)
							continue;

						// If this doesn't have the same number of arguments as
						// given in the file, skip it (unless it's an arbitrary entity)
						if (!entClass.equals(HvlArbitraryEntity.class) && spl.length != constructor.getParameterCount())
							continue;

						try {
							Object[] params = new Object[constructor.getParameterCount()];
							params[0] = entX;
							params[1] = entY;
							params[2] = tr;
							if (entClass.equals(HvlArbitraryEntity.class)) {
								params[3] = spl[3];
								String[] others = new String[spl.length - 4];
								for (int i = 4; i < spl.length; i++) {
									others[i - 4] = spl[i];
								}
								params[4] = others;
								HvlArbitraryEntity newEnt = (HvlArbitraryEntity) constructor.newInstance(params);
								tr.addEntity(replaceArbitrary ? newEnt.toActualEntity() : newEnt);
							} else {
								for (int i = 3; i < spl.length; i++) {
									params[i] = HvlReflectionUtil.genericParse(constructor.getParameterTypes()[i],
											spl[i]);
								}
								HvlEntity newEnt = (HvlEntity) constructor.newInstance(params);
								tr.addEntity(newEnt);
							}
								
							break;
						} catch (Exception e) {
							e.printStackTrace();
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
	
	/**
	 * Saves a map to file.
	 * 
	 * @param path
	 *            The path to save to. Will be in the format "[filename].hvlmap"
	 */
	public void save(String path) {
		try {
			BufferedWriter write = new BufferedWriter(new FileWriter(path + ".hvlmap"));

			write.write(getMapWidth() + "," + getMapHeight() + "," + tiles.length + System.lineSeparator());

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

	/**
	 * Gets the amount of overlap (in pixels) between tiles.
	 * 
	 * @return The amount of overlap between tiles in pixels.
	 */
	public float getOverdrawAmount() {
		return overdrawAmount;
	}

	/**
	 * Sets the amount of overlap (in pixels) between tiles.
	 * 
	 * @param overdrawAmount
	 *            The new amount of overlap between tiles in pixels.
	 */
	public void setOverdrawAmount(float overdrawAmount) {
		this.overdrawAmount = overdrawAmount;
	}

	/**
	 * Converts tile X coordinates into world coordinates (results in upper left
	 * corner).
	 * 
	 * @param tX
	 *            The tile X coordinate to convert.
	 * @return The converted coordinate.
	 */
	public float tileXToWorld(int tX) {
		return pos.x + (tileWidth * tX);
	}

	/**
	 * Converts tile Y coordinates into world coordinates (results in upper left
	 * corner).
	 * 
	 * @param tY
	 *            The tile Y coordinate to convert.
	 * @return The converted coordinate.
	 */
	public float tileYToWorld(int tY) {
		return pos.y + (tileHeight * tY);
	}

	/**
	 * Converts world X coordinates into tile coordinates.
	 * 
	 * @param wX
	 *            The world X coordinate to convert.
	 * @return The converted coordinate.
	 */
	public int worldXToTile(float wX) {
		return (int) ((wX - pos.x) / tileWidth);
	}

	/**
	 * Converts world Y coordinates into tile coordinates.
	 * 
	 * @param wY
	 *            The world Y coordinate to convert.
	 * @return The converted coordinate.
	 */
	public int worldYToTile(float wY) {
		return (int) ((wY - pos.y) / tileHeight);
	}

	/**
	 * Gets the draw opacity for a layer.
	 * 
	 * @param layer
	 *            They layer to get the opacity of.
	 * @return The opacity of the layer.
	 */
	public float getLayerOpacity(int layer) {
		return opacities[layer];
	}

	/**
	 * Sets the draw opacity for a layer.
	 * 
	 * @param layer
	 *            The layer to set the opacity for.
	 * @param opacity
	 *            The new opacity for the layer.
	 */
	public void setLayerOpacity(int layer, float opacity) {
		opacities[layer] = opacity;
	}
}
