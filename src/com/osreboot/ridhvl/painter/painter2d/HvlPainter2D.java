package com.osreboot.ridhvl.painter.painter2d;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

public class HvlPainter2D {

	public static enum HvlPainter2DProfile{
		DEFAULT
	}

	public static void hvlGL11Init(HvlPainter2DProfile profile){
		switch(profile){
		default:
			GL11.glEnable(GL11.GL_TEXTURE_2D);
			GL11.glEnable(GL11.GL_COLOR_MATERIAL);
			GL11.glClearColor(0, 0, 0, 0);
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			GL11.glMatrixMode(GL11.GL_MODELVIEW);
			break;
		}
	}

	public static void hvlGL11Ortho(int displayWidth, int displayHeight){
		GL11.glMatrixMode(GL11.GL_MATRIX_MODE);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, displayWidth, displayHeight, 0, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
	}
	
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
