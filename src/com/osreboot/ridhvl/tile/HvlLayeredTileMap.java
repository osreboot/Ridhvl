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
	
	public HvlTileMap getLayer(int layerArg) {
		return layers.get(layerArg);
	}

	public static HvlLayeredTileMap load(String inArg, Texture tArg, float xArg,
			float yArg, float tileWidthArg, float tileHeightArg) {
		return new HvlLayeredTileMap(xArg, yArg, tileWidthArg, tileHeightArg,
				HvlTileMap.load(inArg, tArg, xArg, yArg, tileWidthArg,
						tileHeightArg));
	}
	
	public static String save(HvlLayeredTileMap map)
	{
		StringBuilder sb = new StringBuilder();
		
		for (HvlTileMap layer : map.layers)
		{
			sb.append(HvlTileMap.save(layer));
			sb.append(System.lineSeparator());
		}
		
		return sb.toString();
	}
}
