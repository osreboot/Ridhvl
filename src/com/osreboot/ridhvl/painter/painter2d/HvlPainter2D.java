package com.osreboot.ridhvl.painter.painter2d;

import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_COLOR_MATERIAL;
import static org.lwjgl.opengl.GL11.GL_MATRIX_MODE;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glOrtho;

import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

import com.osreboot.ridhvl.HvlTextureUtil;
import com.osreboot.ridhvl.action.HvlAction0;
import com.osreboot.ridhvl.painter.HvlAnimatedTextureArray;
import com.osreboot.ridhvl.painter.HvlAnimatedTextureUV;
import com.osreboot.ridhvl.painter.HvlRenderFrame;

public class HvlPainter2D {
	
	@Deprecated
	private static Texture refreshTexture;
	private static Texture white512;//TODO support more sizes

	private static HvlAction0 actionInitialize = HvlPainter2D.ACTION_INITIALIZE_DEFAULT;
	
	public static final HvlAction0 ACTION_INITIALIZE_DEFAULT = new HvlAction0(){
		@Override
		public void run(){
			glEnable(GL_TEXTURE_2D);
			glEnable(GL_COLOR_MATERIAL);
			glClearColor(0, 0, 0, 0);
			glEnable(GL_BLEND);
			glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
			glMatrixMode(GL_MODELVIEW);
		}
	};

	public static HvlAction0 getActionInitialize(){
		return actionInitialize;
	}

	public static void setActionInitialize(HvlAction0 actionInitializeArg){
		actionInitialize = actionInitializeArg;
	}

	public static void hvlGL11Init(){
		if(actionInitialize != null) actionInitialize.run();

		refreshTexture = HvlTextureUtil.getColoredRect(1, 1, Color.transparent);
		white512 = HvlTextureUtil.getColoredRect(512, 512, Color.white);
	}

	public static void hvlGL11Ortho(int displayWidth, int displayHeight){
		glMatrixMode(GL_MATRIX_MODE);
		glLoadIdentity();
		glOrtho(0, displayWidth, displayHeight, 0, 1, -1);
		glMatrixMode(GL_MODELVIEW);
	}

	public static Texture getWhite512(){
		return white512;
	}

	public static HvlFlag2D TEXMAGBLUR = new HvlFlag2D(false){
		@Override
		public void enable(){}
		@Override
		public void disable(){}
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
	
	public static void hvlDrawQuad(float x, float y, float xl, float yl, HvlAnimatedTextureUV texture, Color c){
		HvlQuadPainter2D.hvlDrawQuad(x, y, xl, yl, texture, c);
	}

	public static void hvlDrawQuad(float x, float y, float xl, float yl, Color c){
		HvlQuadPainter2D.hvlDrawQuad(x, y, xl, yl, white512, c);//TODO support more sizes
	}
	
	public static void hvlDrawQuadc(float x, float y, float xl, float yl, Texture t){
		HvlQuadPainter2D.hvlDrawQuadc(x, y, xl, yl, t);
	}

	public static void hvlDrawQuadc(float x, float y, float xl, float yl, Texture t, Color c){
		HvlQuadPainter2D.hvlDrawQuadc(x, y, xl, yl, t, c);
	}

	public static void hvlDrawQuadc(float x, float y, float xl, float yl, float uvx1, float uvy1, float uvx2, float uvy2, Texture t){
		HvlQuadPainter2D.hvlDrawQuadc(x, y, xl, yl, uvx1, uvy1, uvx2, uvy2, t);
	}

	public static void hvlDrawQuadc(float x, float y, float xl, float yl, float uvx1, float uvy1, float uvx2, float uvy2, Texture t, Color c){
		HvlQuadPainter2D.hvlDrawQuadc(x, y, xl, yl, uvx1, uvy1, uvx2, uvy2, t, c);
	}

	public static void hvlDrawQuadc(float x, float y, float xl, float yl, HvlRenderFrame renderFrame){
		HvlQuadPainter2D.hvlDrawQuadc(x, y, xl, yl, renderFrame);//TODO test this
	}

	public static void hvlDrawQuadc(float x, float y, float xl, float yl, HvlAnimatedTextureArray texture){
		HvlQuadPainter2D.hvlDrawQuadc(x, y, xl, yl, texture);
	}

	public static void hvlDrawQuadc(float x, float y, float xl, float yl, HvlAnimatedTextureUV texture){
		HvlQuadPainter2D.hvlDrawQuadc(x, y, xl, yl, texture);
	}
	
	public static void hvlDrawQuadc(float x, float y, float xl, float yl, HvlAnimatedTextureUV texture, Color c){
		HvlQuadPainter2D.hvlDrawQuadc(x, y, xl, yl, texture, c);
	}

	public static void hvlDrawQuadc(float x, float y, float xl, float yl, Color c){
		HvlQuadPainter2D.hvlDrawQuadc(x, y, xl, yl, white512, c);//TODO support more sizes
	}

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
