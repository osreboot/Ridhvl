package com.osreboot.ridhvl;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.util.BufferedImageUtil;

public class HvlTextureUtil {
	public static Texture getColoredRect(int width, int height, Color color)
	{
		BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		
//		System.out.println(color.getRed());
		
		java.awt.Color awtColor = new java.awt.Color(color.r, color.g, color.b, color.a);
		
		Graphics2D graphics = img.createGraphics();
		graphics.setColor(awtColor);
		graphics.fillRect(0, 0, width, height);
		
		try {
			Texture tr = BufferedImageUtil.getTexture("" + Math.random(), img);
			return tr;
		} catch (IOException e) {
			return null;
		}
	}
	
	public static float getPixelR(Texture t, int index) {
		byte original = t.getTextureData()[index * 4];

		return (original < 0 ? ((float) (255 + original) / 255) : ((float) original / 255));
	}
	
	public static float getPixelR(Texture t, int x, int y) {
		byte original = t.getTextureData()[(y * t.getImageWidth() + x) * 4];
		
		return (original < 0 ? ((float) (255 + original) / 255) : ((float) original / 255));
	}
	
	public static float getPixelG(Texture t, int index) {
		byte original = t.getTextureData()[index * 4 + 1];

		return (original < 0 ? ((float) (255 + original) / 255) : ((float) original / 255));
	}
	
	public static float getPixelG(Texture t, int x, int y) {
		byte original = t.getTextureData()[(y * t.getImageWidth() + x) * 4 + 1];

		return (original < 0 ? ((float) (255 + original) / 255) : ((float) original / 255));
	}
	
	public static float getPixelB(Texture t, int index) {
		byte original = t.getTextureData()[index * 4 + 2];

		return (original < 0 ? ((float) (255 + original) / 255) : ((float) original / 255));
	}
	
	public static float getPixelB(Texture t, int x, int y) {
		byte original = t.getTextureData()[(y * t.getImageWidth() + x) * 4 + 2];

		return (original < 0 ? ((float) (255 + original) / 255) : ((float) original / 255));
	}
	
	public static float getPixelA(Texture t, int index) {
		byte original = t.getTextureData()[index * 4 + 3];

		return (original < 0 ? ((float) (255 + original) / 255) : ((float) original / 255));
	}
	
	public static float getPixelA(Texture t, int x, int y) {
		byte original = t.getTextureData()[(y * t.getImageWidth() + x) * 4 + 3];

		return (original < 0 ? ((float) (255 + original) / 255) : ((float) original / 255));
	}
	
	public static Color getPixel(Texture t, int index) {
		return new Color(getPixelR(t, index), getPixelG(t, index), getPixelB(t, index), getPixelA(t, index));
	}
	
	public static Color getPixel(Texture t, int x, int y) {
		return new Color(getPixelR(t, x, y), getPixelG(t, x, y), getPixelB(t, x, y), getPixelA(t, x, y));
	}
}
