package com.osreboot.ridhvl.painter;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.util.BufferedImageUtil;

import com.osreboot.ridhvl.HvlColorUtil;
import com.osreboot.ridhvl.HvlCoord;

public class HvlGradient {
	public enum Style {
		LINEAR, RADIAL
	}

	private Map<Float, Color> stops;
	private Style style;

	public HvlGradient(Style styleArg) {
		stops = new HashMap<>();
		style = styleArg;
	}

	public void addStop(float locationArg, Color colorArg) {
		stops.put(locationArg, colorArg);
	}

	public Texture toTexture(int w, int h, int startX, int startY, int endX, int endY) {
		switch (style) {
		case LINEAR:
			return genLinear(w, h, startX, startY, endX, endY);
		case RADIAL:
			return genRadial(w, h, startX, startY, endX, endY);
		default:
			return null;
		}
	}

	private Texture genLinear(int w, int h, int startX, int startY, int endX, int endY) {
		BufferedImage img = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

		Graphics2D graphics = img.createGraphics();

		for (int x = 0; x < w; x++) {
			for (int y = 0; y < h; y++) {
				float len = (float) Math.sqrt(Math.pow(endX - startX, 2) + Math.pow(endY - startY, 2));

				HvlCoord start = new HvlCoord(endX - startX, endY - startY);
				HvlCoord point = new HvlCoord(x - startX, y - startY);
				float dot = start.dot(point) / len;

				float val = Math.max(0, Math.min(1, dot / len));

				float closestAbove = 1.0f;
				float closestBelow = 0.0f;

				for (Map.Entry<Float, Color> entry : stops.entrySet()) {
					if (entry.getKey() >= val && entry.getKey() < closestAbove)
						closestAbove = entry.getKey();
					if (entry.getKey() <= val && entry.getKey() > closestBelow)
						closestBelow = entry.getKey();
				}
				
				float diff = closestAbove - closestBelow;
				float adjVal;
				if (diff == 0)
					adjVal = 0.0f;
				else
					adjVal = (val - closestBelow) / diff;
				
				Color below = stops.get(closestBelow);
				Color above = stops.get(closestAbove);
				
				Color toDraw = HvlColorUtil.lerpColor(below, above, adjVal);
				java.awt.Color awtColor = new java.awt.Color(toDraw.r, toDraw.g, toDraw.b);
				graphics.setColor(awtColor);
				graphics.fillRect(x, y, 1, 1);
			}
		}

		try {
			return BufferedImageUtil.getTexture("" + Math.random(), img);
		} catch (IOException e) {
			return null;
		}
	}
	
	private Texture genRadial(int w, int h, int startX, int startY, int endX, int endY) {
		BufferedImage img = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

		Graphics2D graphics = img.createGraphics();

		float mainDistance = (float) Math.sqrt(Math.pow(endX - startX, 2) + Math.pow(endY - startY, 2));
		
		for (int x = 0; x < w; x++) {
			for (int y = 0; y < h; y++) {
				float distance = (float) Math.sqrt(Math.pow(x - startX, 2) + Math.pow(y - startY, 2));
				
				float val = Math.min(distance / mainDistance, 1);
				
				float closestAbove = 1.0f;
				float closestBelow = 0.0f;

				for (Map.Entry<Float, Color> entry : stops.entrySet()) {
					if (entry.getKey() >= val && entry.getKey() < closestAbove)
						closestAbove = entry.getKey();
					if (entry.getKey() <= val && entry.getKey() > closestBelow)
						closestBelow = entry.getKey();
				}
				
				float diff = closestAbove - closestBelow;
				float adjVal;
				if (diff == 0)
					adjVal = 0.0f;
				else
					adjVal = (val - closestBelow) / diff;
				
				Color below = stops.get(closestBelow);
				Color above = stops.get(closestAbove);
				
				Color toDraw = HvlColorUtil.lerpColor(below, above, adjVal);
				java.awt.Color awtColor = new java.awt.Color(toDraw.r, toDraw.g, toDraw.b);
				graphics.setColor(awtColor);
				graphics.fillRect(x, y, 1, 1);
			}
		}

		try {
			return BufferedImageUtil.getTexture("" + Math.random(), img);
		} catch (IOException e) {
			return null;
		}
	}
}
