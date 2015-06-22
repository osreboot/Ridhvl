package com.osreboot.ridhvl.tile;

import java.util.LinkedList;
import java.util.List;

import org.newdawn.slick.opengl.Texture;

public class HvlLayeredTileMap {
	private List<HvlTileMap> layers;
	private float x, y;
	private float tileWidth, tileHeight;

	public HvlLayeredTileMap(float xArg, float yArg, float tileWidthArg,
			float tileHeightArg, HvlTileMap... layersArg) {
		x = xArg;
		y = yArg;
		tileWidth = tileWidthArg;
		tileHeight = tileHeightArg;

		layers = new LinkedList<>();

		for (HvlTileMap layer : layersArg) {
			layers.add(layer);
		}
	}

	public void draw(float delta) {
		for (HvlTileMap map : layers) {
			map.setX(x);
			map.setY(y);
			map.setTileWidth(tileWidth);
			map.setTileHeight(tileHeight);
			map.draw(delta);
		}
	}

	public static HvlLayeredTileMap load(float xArg, float yArg, float tileWidthArg,
			float tileHeightArg, Texture tArg, String inArg) {
		return new HvlLayeredTileMap(xArg, yArg, tileWidthArg, tileHeightArg,
				HvlTileMap.load(inArg, tArg, xArg, yArg, tileWidthArg,
						tileHeightArg));
	}
}
