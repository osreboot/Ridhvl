package com.osreboot.ridhvl.painter.painter2d;

import static org.lwjgl.opengl.GL11.*;

import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

import com.osreboot.ridhvl.HvlTextureUtil;
import com.osreboot.ridhvl.painter.HvlAnimatedTextureArray;
import com.osreboot.ridhvl.painter.HvlAnimatedTextureUV;
import com.osreboot.ridhvl.painter.HvlRenderFrame;

public class HvlPainter2D {

	@Deprecated
	private static Texture refreshTexture;
	
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
		
		refreshTexture = HvlTextureUtil.getColoredRect(1, 1, Color.transparent);
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
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
		}
		
		@Override
		public void disable(){
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
		}
	};
	
	public static void hvlDrawLine(float x1, float y1, float x2, float y2, Color c){
		HvlLinePainter2D.hvlDrawLine(x1, y1, x2, y2, c);
	}
	
	public static void hvlDrawLine(float x1, float y1, float x2, float y2, Color c, float width){
		HvlLinePainter2D.hvlDrawLine(x1, y1, x2, y2, c, width);
	}
	
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
	
	public static void hvlDrawQuad(float x, float y, float xl, float yl, HvlRenderFrame renderFrame){
		HvlQuadPainter2D.hvlDrawQuad(x, y, xl, yl, renderFrame);
	}
	
	public static void hvlDrawQuad(float x, float y, float xl, float yl, HvlAnimatedTextureArray texture){
		HvlQuadPainter2D.hvlDrawQuad(x, y, xl, yl, texture);
	}
	
	public static void hvlDrawQuad(float x, float y, float xl, float yl, HvlAnimatedTextureUV texture){
		HvlQuadPainter2D.hvlDrawQuad(x, y, xl, yl, texture);
	}
	
	//TODO hvlDrawQuad(float x, float y, float xl, float yl, Color c)
	
	public static void hvlRotate(float x, float y, float degrees){
		HvlSwivel2D.hvlRotate(x, y, degrees);
	}
	
	public static void hvlResetRotation(){
		HvlSwivel2D.hvlResetRotation();
	}
	
	@Deprecated
	public static void hvlForceRefresh(){
		hvlDrawQuad(0f, 0f, 0f, 0f, refreshTexture);
	}

}
