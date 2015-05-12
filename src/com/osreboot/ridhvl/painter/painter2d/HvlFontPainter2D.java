package com.osreboot.ridhvl.painter.painter2d;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

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
				GL11.glColor4f(c.r, c.g, c.b, c.a);
				image.bind();
				GL11.glBegin(GL11.GL_QUADS);
				GL11.glTexCoord2f((fontWidth/textureWidth)*xpos, (fontHeight/textureHeight)*ypos);
				GL11.glVertex2f(x + (i*charLength), y);
				GL11.glTexCoord2f((fontWidth/textureWidth)*xpos + (fontWidth/textureHeight), (fontHeight/textureHeight)*ypos);
				GL11.glVertex2f(x + charLength + (i*charLength), y);
				GL11.glTexCoord2f((fontWidth/textureWidth)*xpos + (fontWidth/textureWidth), (fontHeight/textureHeight)*ypos + (fontHeight/textureHeight));
				GL11.glVertex2f(x + charLength + (i*charLength), y + height);
				GL11.glTexCoord2f((fontWidth/textureWidth)*xpos, (fontHeight/textureHeight)*ypos + (fontHeight/textureHeight));
				GL11.glVertex2f(x + (i*charLength), y + height);
				GL11.glEnd();
			}
		}
	}
	
	public void hvlDrawWord(String word, float x, float y, Color c){
		for(int i = 0; i < word.toCharArray().length; i++){
			if(HvlFontUtil.containsChar(layout, word.toCharArray()[i])){
				int xpos = HvlFontUtil.indexOfChar(layout, word.toCharArray()[i]) % rowCount;
				int ypos = HvlFontUtil.indexOfChar(layout, word.toCharArray()[i]) / rowCount;
				GL11.glColor4f(c.r, c.g, c.b, c.a);
				image.bind();
				GL11.glBegin(GL11.GL_QUADS);
				GL11.glTexCoord2f((fontWidth/textureWidth)*xpos, (fontHeight/textureHeight)*ypos);
				GL11.glVertex2f(x + (i*fontWidth), y);
				GL11.glTexCoord2f((fontWidth/textureWidth)*xpos + (fontWidth/textureHeight), (fontHeight/textureHeight)*ypos);
				GL11.glVertex2f(x + fontWidth + (i*fontWidth), y);
				GL11.glTexCoord2f((fontWidth/textureWidth)*xpos + (fontWidth/textureWidth), (fontHeight/textureHeight)*ypos + (fontHeight/textureHeight));
				GL11.glVertex2f(x + fontWidth + (i*fontWidth), y + fontHeight);
				GL11.glTexCoord2f((fontWidth/textureWidth)*xpos, (fontHeight/textureHeight)*ypos + (fontHeight/textureHeight));
				GL11.glVertex2f(x + (i*fontWidth), y + fontHeight);
				GL11.glEnd();
			}
		}
	}
	
	public void hvlDrawWord(String word, float x, float y, float scale, Color c){
		for(int i = 0; i < word.toCharArray().length; i++){
			if(HvlFontUtil.containsChar(layout, word.toCharArray()[i])){
				int xpos = HvlFontUtil.indexOfChar(layout, word.toCharArray()[i]) % rowCount;
				int ypos = HvlFontUtil.indexOfChar(layout, word.toCharArray()[i]) / rowCount;
				GL11.glColor4f(c.r, c.g, c.b, c.a);
				image.bind();
				GL11.glBegin(GL11.GL_QUADS);
				GL11.glTexCoord2f((fontWidth/textureWidth)*xpos, (fontHeight/textureHeight)*ypos);
				GL11.glVertex2f(x + (i*fontWidth*scale), y);
				GL11.glTexCoord2f((fontWidth/textureWidth)*xpos + (fontWidth/textureHeight), (fontHeight/textureHeight)*ypos);
				GL11.glVertex2f(x + (fontWidth*scale) + (i*fontWidth*scale), y);
				GL11.glTexCoord2f((fontWidth/textureWidth)*xpos + (fontWidth/textureWidth), (fontHeight/textureHeight)*ypos + (fontHeight/textureHeight));
				GL11.glVertex2f(x + (fontWidth*scale) + (i*fontWidth*scale), y + (fontHeight*scale));
				GL11.glTexCoord2f((fontWidth/textureWidth)*xpos, (fontHeight/textureHeight)*ypos + (fontHeight/textureHeight));
				GL11.glVertex2f(x + (i*fontWidth*scale), y + (fontHeight*scale));
				GL11.glEnd();
			}
		}
	}
	
}
