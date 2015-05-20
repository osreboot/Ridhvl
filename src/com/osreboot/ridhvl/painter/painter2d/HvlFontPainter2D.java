package com.osreboot.ridhvl.painter.painter2d;

import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

import com.osreboot.ridhvl.HvlDisplay;
import com.osreboot.ridhvl.HvlFontUtil;
import com.osreboot.ridhvl.HvlFontUtil.HvlFontLayout;

public class HvlFontPainter2D {
	
	private Texture image;
	private HvlFontLayout layout;
	private float textureWidth, textureHeight, fontWidth, fontHeight;
	private int rowCount;
	
	public HvlFontPainter2D(Texture imageArg, HvlFontLayout layoutArg, float textureWidthArg, float textureHeightArg, float fontWidthArg, float fontHeightArg, int rowCountArg){
		image = imageArg;
		layout = layoutArg;
		textureWidth = textureWidthArg;
		textureHeight = textureHeightArg;
		fontWidth = fontWidthArg;
		fontHeight = fontHeightArg;
		rowCount = rowCountArg;
	}
	
	public void hvlDrawWord(String word, float x, float y, float width, float height, Color c){
		for(int i = 0; i < word.toCharArray().length; i++){
			if(HvlFontUtil.containsChar(layout, word.toCharArray()[i])){
				int xpos = HvlFontUtil.indexOfChar(layout, word.toCharArray()[i]) % rowCount;
				int ypos = HvlFontUtil.indexOfChar(layout, word.toCharArray()[i]) / rowCount;
				float charLength = width/word.toCharArray().length;//TODO could be more efficient
				
				HvlPainter2D.hvlDrawQuad(x + (i*charLength), y, charLength, height, (fontWidth/textureWidth)*xpos, (fontHeight/textureHeight)*ypos, (fontWidth/textureWidth)*xpos + (fontWidth/textureHeight), (fontHeight/textureHeight)*ypos + (fontHeight/textureHeight), image, c);
			}
		}
	}
	
	public void hvlDrawWord(String word, float x, float y, Color c){
		for(int i = 0; i < word.toCharArray().length; i++){
			if(HvlFontUtil.containsChar(layout, word.toCharArray()[i])){
				int xpos = HvlFontUtil.indexOfChar(layout, word.toCharArray()[i]) % rowCount;
				int ypos = HvlFontUtil.indexOfChar(layout, word.toCharArray()[i]) / rowCount;
				
				HvlPainter2D.hvlDrawQuad(x + (i*HvlDisplay.getUnscaledX(fontWidth)), y, HvlDisplay.getUnscaledX(fontWidth), HvlDisplay.getUnscaledY(fontHeight), (fontWidth/textureWidth)*xpos, (fontHeight/textureHeight)*ypos, (fontWidth/textureWidth)*xpos + (fontWidth/textureHeight), (fontHeight/textureHeight)*ypos + (fontHeight/textureHeight), image, c);
			}
		}
	}
	
	public void hvlDrawWord(String word, float x, float y, float scale, Color c){
		for(int i = 0; i < word.toCharArray().length; i++){
			if(HvlFontUtil.containsChar(layout, word.toCharArray()[i])){
				int xpos = HvlFontUtil.indexOfChar(layout, word.toCharArray()[i]) % rowCount;
				int ypos = HvlFontUtil.indexOfChar(layout, word.toCharArray()[i]) / rowCount;
				
				HvlPainter2D.hvlDrawQuad(x + (i*HvlDisplay.getUnscaledX(fontWidth)*scale), y, HvlDisplay.getUnscaledX(fontWidth*scale), HvlDisplay.getUnscaledY(fontHeight*scale), (fontWidth/textureWidth)*xpos, (fontHeight/textureHeight)*ypos, (fontWidth/textureWidth)*xpos + (fontWidth/textureHeight), (fontHeight/textureHeight)*ypos + (fontHeight/textureHeight), image, c);
			}
		}
	}
	
}
