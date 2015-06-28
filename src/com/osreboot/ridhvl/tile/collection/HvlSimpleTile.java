package com.osreboot.ridhvl.tile.collection;

import java.util.regex.Pattern;

import org.newdawn.slick.Color;

import com.osreboot.ridhvl.painter.painter2d.HvlPainter2D;
import com.osreboot.ridhvl.tile.HvlTile;
import com.osreboot.ridhvl.tile.HvlTileMap.TileMapInfo;

public class HvlSimpleTile extends HvlTile {

	private int tile;

	public HvlSimpleTile(int tileArg) {
		this.tile = tileArg;
	}

	@Override
	public void draw(TileMapInfo info, float x, float y, float width,
			float height, float delta, boolean inRange) {
		if (!inRange) return;
		
		int tileX = tile % info.tileWidth;
		int tileY = tile / info.tileHeight;

		float uvx1 = (float) tileX / info.tileWidth;
		float uvy1 = (float) tileY / info.tileHeight;
		float uvx2 = (float) (tileX + 1) / info.tileWidth;
		float uvy2 = (float) (tileY + 1) / info.tileHeight;

		HvlPainter2D.TEXMAGBLUR.disable();
		
		HvlPainter2D.hvlDrawQuad(x, y, width, height, uvx1, uvy1, uvx2, uvy2,
				info.texture, Color.white);
		
		HvlPainter2D.TEXMAGBLUR.enable();
	}

	public int getTile() {
		return tile;
	}

	public void setTile(int tile) {
		this.tile = tile;
	}

	public static String save(HvlSimpleTile tile) {
		return String.format("Tile:%d", tile.getTile());
	}

	public static HvlSimpleTile load(String in) {
		return new HvlSimpleTile(
				Integer.parseInt(in.split(Pattern.quote(":"))[1].trim()));
	}
}
