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
import com.osreboot.ridhvl.HvlMath;

public class HvlGradient {
	public enum Style {
		LINEAR
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
		default:
			return null;
		}
	}

	private Texture genLinear(int w, int h, int startX, int startY, int endX, int endY) {
		BufferedImage img = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

		// java.awt.Color awtColor = new java.awt.Color(color.r, color.g,
		// color.b, color.a);
		//
		// Graphics2D graphics = img.createGraphics();
		// graphics.setColor(awtColor);
		// graphics.fillRect(0, 0, width, height);

		Graphics2D graphics = img.createGraphics();

		graphics.setColor(java.awt.Color.CYAN);
		graphics.fillRect(0, 0, w, h);

		float angle = (float) Math.atan2(endY - startY, endX - startX);

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
				
				// graphics.setColor(awtColor);
				// {
				// HvlCoord start = new HvlCoord(endX - startX, endY - startY);
				// HvlCoord point = new HvlCoord(x - startX, y - startY);
				// float dot = start.dot(point);
				//
				// if (dot <= 0.0f)
				// {
				// java.awt.Color awtColor = new
				// java.awt.Color(HvlMath.randomFloatBetween(0, 1),
				// HvlMath.randomFloatBetween(0, 1),
				// HvlMath.randomFloatBetween(0,
				// 1), 1.0f);
				// graphics.setColor(awtColor);
				// graphics.fillRect(x, y, 1, 1);
				// }
				// }
				// {
				// HvlCoord end = new HvlCoord(endX, endY);
				// HvlCoord point = new HvlCoord(endX - x, endY - y);
				// float dot = point.dot(end);
				// System.out.println(x + ", " + y + " : " + dot);
				//
				// if (dot <= 0.0f)
				// {
				// java.awt.Color awtColor = new
				// java.awt.Color(HvlMath.randomFloatBetween(0, 1),
				// HvlMath.randomFloatBetween(0, 1),
				// HvlMath.randomFloatBetween(0,
				// 1), 1.0f);
				// graphics.setColor(awtColor);
				// graphics.fillRect(x, y, 1, 1);
				// }
				// }

				// graphics.fillRect(x, y, 1, 1);
			}
		}

		try {
			return BufferedImageUtil.getTexture("" + Math.random(), img);
		} catch (IOException e) {
			return null;
		}
	}
}
