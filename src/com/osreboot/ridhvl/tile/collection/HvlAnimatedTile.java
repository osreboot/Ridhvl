package com.osreboot.ridhvl.tile.collection;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.newdawn.slick.Color;

import com.osreboot.ridhvl.painter.painter2d.HvlPainter2D;
import com.osreboot.ridhvl.tile.HvlTile;
import com.osreboot.ridhvl.tile.HvlTileMap.TileMapInfo;

public class HvlAnimatedTile extends HvlTile {

	private List<Integer> tileCoords;
	private int currentTile;
	private float timer, timeBetweenAnimations;
	
	public HvlAnimatedTile(float timeArg, int... tilesArg) {
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
			float width, float height, float delta, boolean inRange, float opacity) {
		timer += delta;
		
		if (timer >= timeBetweenAnimations)
		{
			timer = 0;
			currentTile++;
			if (currentTile >= tileCoords.size())
				currentTile = 0;
		}
		
		if (!inRange) return;
		
		int tileX = tileCoords.get(currentTile) % info.tileWidth;
		int tileY = tileCoords.get(currentTile) / info.tileHeight;
		
		float uvx1 = (float) tileX / info.tileWidth;
		float uvy1 = (float) tileY / info.tileHeight;
		float uvx2 = (float) (tileX + 1) / info.tileWidth;
		float uvy2 = (float) (tileY + 1) / info.tileHeight;

		HvlPainter2D.TEXMAGBLUR.disable();
		
		HvlPainter2D.hvlDrawQuad(x, y, width, height, uvx1, uvy1, uvx2, uvy2, info.texture, new Color(1.0f, 1.0f, 1.0f, opacity));

		HvlPainter2D.TEXMAGBLUR.enable();
	}

	public List<Integer> getTileCoords() {
		return tileCoords;
	}

	public float getTimeBetweenAnimations() {
		return timeBetweenAnimations;
	}

	public void setTimeBetweenAnimations(float timeBetweenAnimations) {
		this.timeBetweenAnimations = timeBetweenAnimations;
	}

	public static String save(HvlAnimatedTile tile)
	{
		StringBuilder sb = new StringBuilder();
		
		sb.append(String.format("TimeBetweenAnimations:%f%n", tile.getTimeBetweenAnimations()));
		for (Integer i : tile.getTileCoords())
		{
			sb.append(String.format("AnimationFrame:%d%n", i));
		}
		
		return sb.toString();
	}
	
	public static HvlAnimatedTile load(String in)
	{
		String[] lines = in.split(Pattern.quote(System.lineSeparator()));
		
		float timeBetweenAnimations = 0.0f;
		List<Integer> frames = new ArrayList<>();
		
		for (String line : lines)
		{
			if (line.startsWith("TimeBetweenAnimations:"))
			{
				timeBetweenAnimations = Float.parseFloat(line.replaceFirst(Pattern.quote("TimeBetweenAnimations:"), "").trim());
			}
			if (line.startsWith("AnimationFrame:"))
			{
				frames.add(Integer.parseInt(line.replaceFirst(Pattern.quote("AnimationFrame:"), "").trim()));
			}
		}
		
		int[] framesArray = new int[frames.size()];
		
		for (int i = 0; i < frames.size(); i++)
			framesArray[i] = frames.get(i);
		
		return new HvlAnimatedTile(timeBetweenAnimations, framesArray);
	}
}
