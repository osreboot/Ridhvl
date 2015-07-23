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
}
