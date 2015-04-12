package com.osreboot.ridhvl.painter.painter2d;

import java.util.Arrays;

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
	
	public void hvlDrawWord(String word, float x, float y, float width, float height, Color c){//TODO finish this method
		for(int i = 0; i < word.toCharArray().length; i++){
			if(Arrays.asList(HvlFontUtil.getLayout(layout)).contains(word.toCharArray()[i])){
				int pos = Arrays.asList(HvlFontUtil.getLayout(layout)).indexOf(word.toCharArray()[i]);
				GL11.glColor4f(c.r, c.g, c.b, c.a);
				image.bind();
				GL11.glBegin(GL11.GL_QUADS);
				GL11.glTexCoord2f((fontWidth/textureWidth)*pos, 0);//TODO dimension args incorrect, use variables
				GL11.glVertex2f(x + (i*fontWidth), y);
				GL11.glTexCoord2f((fontWidth/textureWidth)*pos + (fontWidth/textureHeight), 0);
				GL11.glVertex2f(x + fontWidth + (i*fontWidth), y);
				GL11.glTexCoord2f((fontWidth/textureWidth)*pos + (fontWidth/textureWidth), fontHeight/textureHeight);
				GL11.glVertex2f(x + fontWidth + (i*fontWidth), y + fontHeight);
				GL11.glTexCoord2f((fontWidth/textureWidth)*pos, fontHeight/textureHeight);
				GL11.glVertex2f(x + (i*fontWidth), y + fontHeight);
				GL11.glEnd();
			}
		}
	}
	
}
