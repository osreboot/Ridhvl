package com.osreboot.ridhvl.painter.painter2d;

import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

import com.osreboot.ridhvl.HvlFontUtil;

public class HvlFontPainter2D {

	public static final Preset BITZAP = new Preset(112, 144, 0),
			CHALK = new Preset(128, 160, 0);
	
	private Texture image;
	private char[] layout;
	private float textureWidth, textureHeight, fontWidth, fontHeight;
	private int rowCount;
	private float rowSpacing; // TODO: Convert to percentage
	private float scale;

	public HvlFontPainter2D(Texture imageArg, char[] layoutArg, Preset presetArg){
		image = imageArg;
		layout = layoutArg;
		textureWidth = imageArg.getImageWidth();
		textureHeight = imageArg.getImageHeight();
		fontWidth = presetArg.getFontWidth();
		fontHeight = presetArg.getFontHeight();
		rowCount = (int)(textureWidth/fontWidth);
		rowSpacing = presetArg.getRowSpace();
		scale = 1f;
	}
	
	public HvlFontPainter2D(Texture imageArg, char[] layoutArg, Preset presetArg, float scaleArg){
		image = imageArg;
		layout = layoutArg;
		textureWidth = imageArg.getImageWidth();
		textureHeight = imageArg.getImageHeight();
		fontWidth = presetArg.getFontWidth();
		fontHeight = presetArg.getFontHeight();
		rowCount = (int)(textureWidth/fontWidth);
		rowSpacing = presetArg.getRowSpace();
		scale = scaleArg;
	}
	
	public HvlFontPainter2D(Texture imageArg, char[] layoutArg, float fontWidthArg, float fontHeightArg) {
		image = imageArg;
		layout = layoutArg;
		textureWidth = imageArg.getImageWidth();
		textureHeight = imageArg.getImageHeight();
		fontWidth = fontWidthArg;
		fontHeight = fontHeightArg;
		rowCount = (int)(textureWidth/fontWidth);
		rowSpacing = 0f;
		scale = 1f;
	}

	public HvlFontPainter2D(Texture imageArg, char[] layoutArg, float fontWidthArg, float fontHeightArg, float rowSpaceArg) {
		image = imageArg;
		layout = layoutArg;
		textureWidth = imageArg.getImageWidth();
		textureHeight = imageArg.getImageHeight();
		fontWidth = fontWidthArg;
		fontHeight = fontHeightArg;
		rowCount = (int)(textureWidth/fontWidth);
		rowSpacing = rowSpaceArg;
		scale = 1f;
	}
	
	public HvlFontPainter2D(Texture imageArg, char[] layoutArg, float fontWidthArg, float fontHeightArg, float rowSpaceArg, float scaleArg) {
		image = imageArg;
		layout = layoutArg;
		textureWidth = imageArg.getImageWidth();
		textureHeight = imageArg.getImageHeight();
		fontWidth = fontWidthArg;
		fontHeight = fontHeightArg;
		rowCount = (int)(textureWidth/fontWidth);
		rowSpacing = rowSpaceArg;
		scale = scaleArg;
	}

	public void drawWord(String text, float x, float y, float width, float height, Color c) {
		drawWord(text, x, y, width, height, c, false);
	}
	
	public void drawWord(String text, float x, float y, float width, float height, Color c, boolean individualLineStretch) {
		String[] lines = text.split("\n");

		int longestLine = 0;
		
		for (String line : lines) {
			longestLine = Math.max(longestLine, line.length());
		}
		
		for (int l = 0; l < lines.length; l++) {
			String line = lines[l];
			int lineLength = line.length();
			for (int i = 0; i < lineLength; i++) {
				if (HvlFontUtil.containsChar(layout, line.charAt(i))) {
					int xpos = HvlFontUtil.indexOfChar(layout, line.charAt(i)) % rowCount;
					int ypos = HvlFontUtil.indexOfChar(layout, line.charAt(i)) / rowCount;
					float charWidth;
					
					if (individualLineStretch)
						charWidth = width / line.length();
					else
						charWidth = width / longestLine;

					HvlPainter2D.hvlDrawQuad(x + (i * charWidth),
							y + (l * ((height - (rowSpacing * lines.length)) / lines.length)) + (l * rowSpacing),
							charWidth, height / lines.length - (lines.length - 1) * rowSpacing,
							(fontWidth / textureWidth) * xpos, (fontHeight / textureHeight) * ypos,
							(fontWidth / textureWidth) * xpos + (fontWidth / textureHeight),
							(fontHeight / textureHeight) * ypos + (fontHeight / textureHeight), image, c);
				}
			}
		}
	}

	public void drawWord(String text, float x, float y, Color c) {
		drawWord(text, x, y, 1.0f, c);
	}

	public void drawWord(String text, float x, float y, float scaleArg, Color c) {
		String[] lines = text.split("\n");

		for (int l = 0; l < lines.length; l++) {
			String line = lines[l];

			for (int i = 0; i < line.length(); i++) {
				if (HvlFontUtil.containsChar(layout, line.charAt(i))) {
					int xpos = HvlFontUtil.indexOfChar(layout, line.charAt(i)) % rowCount;
					int ypos = HvlFontUtil.indexOfChar(layout, line.charAt(i)) / rowCount;

					HvlPainter2D.hvlDrawQuad(x + (i * fontWidth * scale * scaleArg), y + (l * fontHeight * scale * scaleArg) + (l * rowSpacing * scale * scaleArg), fontWidth * scale * scaleArg, fontHeight * scale * scaleArg,
							(fontWidth / textureWidth) * xpos, (fontHeight / textureHeight) * ypos,
							(fontWidth / textureWidth) * xpos + (fontWidth / textureHeight),
							(fontHeight / textureHeight) * ypos + (fontHeight / textureHeight), image, c);
				}
			}
		}
	}

	public float getFontWidth() {
		return fontWidth;
	}

	public float getFontHeight() {
		return fontHeight;
	}

	public float getLineWidth(String text) {
		return getFontWidth() * text.length();
	}
	
	public float getScale() {
		return scale;
	}

	public static class Preset{
		
		private int fontWidth, fontHeight;
		private float rowSpace;
		
		public Preset(int fontWidthArg, int fontHeightArg, float rowSpaceArg){
			fontWidth = fontWidthArg;
			fontHeight = fontHeightArg;
			rowSpace = rowSpaceArg;
		}

		public int getFontWidth(){
			return fontWidth;
		}

		public int getFontHeight(){
			return fontHeight;
		}

		public float getRowSpace(){
			return rowSpace;
		}
		
	}

}
