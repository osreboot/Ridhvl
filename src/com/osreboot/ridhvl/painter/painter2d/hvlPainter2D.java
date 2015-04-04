package com.osreboot.ridhvl.painter.painter2d;

import java.awt.Color;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

public class hvlPainter2D {

	public static enum TemplateType{
		DEFAULT
	}

	public static void GL11_Init(TemplateType type){
		switch(type){
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

	public static void GL11_Ortho(int displayWidth, int displayHeight){
		GL11.glMatrixMode(GL11.GL_MATRIX_MODE);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, displayWidth, displayHeight, 0, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
	}

	public static void drawQuad(float x, float y, float xl, float yl, Texture t){
		hvlQuadPainter2D.drawQuad(x, y, xl, yl, t);
	}

	public static void drawQuad(float x, float y, float xl, float yl, Texture t, Color c){
		hvlQuadPainter2D.drawQuad(x, y, xl, yl, t, c);
	}

	public static void drawQuad(float x, float y, float xl, float yl, float uvx1, float uvy1, float uvx2, float uvy2, Texture t){
		hvlQuadPainter2D.drawQuad(x, y, xl, yl, uvx1, uvy1, uvx2, uvy2, t);
	}

	public static void drawQuad(float x, float y, float xl, float yl, float uvx1, float uvy1, float uvx2, float uvy2, Texture t, Color c){
		hvlQuadPainter2D.drawQuad(x, y, xl, yl, uvx1, uvy1, uvx2, uvy2, t, c);
	}
	
	//TODO drawQuad(float x, float y, float xl, float yl, Color c)

}
