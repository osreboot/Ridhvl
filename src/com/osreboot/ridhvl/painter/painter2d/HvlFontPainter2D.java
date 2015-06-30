package com.osreboot.ridhvl.painter.painter2d;

import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

import com.osreboot.ridhvl.HvlFontUtil;

public class HvlFontPainter2D {
	
	private Texture image;
	private char[] layout;
	private float textureWidth, textureHeight, fontWidth, fontHeight;
	private int rowCount;
	
	public HvlFontPainter2D(Texture imageArg, char[] layoutArg, float textureWidthArg, float textureHeightArg, float fontWidthArg, float fontHeightArg, int rowCountArg){
		image = imageArg;
		layout = layoutArg;
		textureWidth = textureWidthArg;
		textureHeight = textureHeightArg;
		fontWidth = fontWidthArg;
		fontHeight = fontHeightArg;
		rowCount = rowCountArg;
	}
	
	public void drawWord(String word, float x, float y, float width, float height, Color c){
		for(int i = 0; i < word.toCharArray().length; i++){
			if(HvlFontUtil.containsChar(layout, word.toCharArray()[i])){
				int xpos = HvlFontUtil.indexOfChar(layout, word.toCharArray()[i]) % rowCount;
				int ypos = HvlFontUtil.indexOfChar(layout, word.toCharArray()[i]) / rowCount;
				float charLength = width/word.toCharArray().length;//TODO could be more efficient
				
				HvlPainter2D.hvlDrawQuad(x + (i*charLength), y, charLength, height, (fontWidth/textureWidth)*xpos, (fontHeight/textureHeight)*ypos, (fontWidth/textureWidth)*xpos + (fontWidth/textureHeight), (fontHeight/textureHeight)*ypos + (fontHeight/textureHeight), image, c);
			}
		}
	}
	
	public void drawWord(String word, float x, float y, Color c){
		for(int i = 0; i < word.toCharArray().length; i++){
			if(HvlFontUtil.containsChar(layout, word.toCharArray()[i])){
				int xpos = HvlFontUtil.indexOfChar(layout, word.toCharArray()[i]) % rowCount;
				int ypos = HvlFontUtil.indexOfChar(layout, word.toCharArray()[i]) / rowCount;
				
				HvlPainter2D.hvlDrawQuad(x + (i*fontWidth), y, fontWidth, fontHeight, (fontWidth/textureWidth)*xpos, (fontHeight/textureHeight)*ypos, (fontWidth/textureWidth)*xpos + (fontWidth/textureHeight), (fontHeight/textureHeight)*ypos + (fontHeight/textureHeight), image, c);
			}
		}
	}
	
	public void drawWord(String word, float x, float y, float scale, Color c){
		for(int i = 0; i < word.toCharArray().length; i++){
			if(HvlFontUtil.containsChar(layout, word.toCharArray()[i])){
				int xpos = HvlFontUtil.indexOfChar(layout, word.toCharArray()[i]) % rowCount;
				int ypos = HvlFontUtil.indexOfChar(layout, word.toCharArray()[i]) / rowCount;
				
				HvlPainter2D.hvlDrawQuad(x + (i*fontWidth*scale), y, fontWidth*scale, fontHeight*scale, (fontWidth/textureWidth)*xpos, (fontHeight/textureHeight)*ypos, (fontWidth/textureWidth)*xpos + (fontWidth/textureHeight), (fontHeight/textureHeight)*ypos + (fontHeight/textureHeight), image, c);
			}
		}
	}
	
	public float getFontWidth() {
		return fontWidth;
	}

	public float getFontHeight() {
		return fontHeight;
	}

	public float getLineWidth(String text)
	{
		return getFontWidth() * text.length();
	}
	
}
