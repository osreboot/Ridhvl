package com.osreboot.ridhvl.painter.painter2d;

import static org.lwjgl.opengl.GL11.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

public class HvlPainter2D {

	public static enum HvlPainter2DProfile{
		DEFAULT
	}

	public static void hvlGL11Init(HvlPainter2DProfile profile){
		switch(profile){
		default:
			glEnable(GL_TEXTURE_2D);
			glEnable(GL_COLOR_MATERIAL);
			glClearColor(0, 0, 0, 0);
			glEnable(GL_BLEND);
			glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
			glMatrixMode(GL_MODELVIEW);
			break;
		}
	}

	public static void hvlGL11Ortho(int displayWidth, int displayHeight){
		glMatrixMode(GL_MATRIX_MODE);
		glLoadIdentity();
		glOrtho(0, displayWidth, displayHeight, 0, 1, -1);
		glMatrixMode(GL_MODELVIEW);
	}
	
	public static HvlFlag2D TEXMAGBLUR = new HvlFlag2D(true){
		@Override
		public void enable(){
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
		}
		
		@Override
		public void disable(){
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
		}
	};
	
	//TODO account for HvlDisplayMode
	public static void hvlDrawQuad(float x, float y, float xl, float yl, Texture t){
		HvlQuadPainter2D.hvlDrawQuad(x, y, xl, yl, t);
	}

	public static void hvlDrawQuad(float x, float y, float xl, float yl, Texture t, Color c){
		HvlQuadPainter2D.hvlDrawQuad(x, y, xl, yl, t, c);
	}

	public static void hvlDrawQuad(float x, float y, float xl, float yl, float uvx1, float uvy1, float uvx2, float uvy2, Texture t){
		HvlQuadPainter2D.hvlDrawQuad(x, y, xl, yl, uvx1, uvy1, uvx2, uvy2, t);
	}

	public static void hvlDrawQuad(float x, float y, float xl, float yl, float uvx1, float uvy1, float uvx2, float uvy2, Texture t, Color c){
		HvlQuadPainter2D.hvlDrawQuad(x, y, xl, yl, uvx1, uvy1, uvx2, uvy2, t, c);
	}
	
	@Deprecated
	public static void hvlDrawQuad(float x, float y, float xl, float yl, int textureID){
		HvlQuadPainter2D.hvlDrawQuad(x, y, xl, yl, textureID);
	}
	
	//TODO hvlDrawQuad(float x, float y, float xl, float yl, Color c)
	
	public static void hvlRotate(float x, float y, float degrees){
		HvlSwivel2D.hvlRotate(x, y, degrees);
	}
	
	public static void hvlResetRotation(){
		HvlSwivel2D.hvlResetRotation();
	}

}
