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

import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

import com.osreboot.ridhvl.HvlCoord;
import com.osreboot.ridhvl.HvlTextureUtil;
import com.osreboot.ridhvl.action.HvlAction0;
import com.osreboot.ridhvl.painter.HvlAnimatedTextureArray;
import com.osreboot.ridhvl.painter.HvlAnimatedTextureUV;
import com.osreboot.ridhvl.painter.HvlRenderFrame;

public class HvlPainter2D {

	@Deprecated
	private static Texture refreshTexture;
	private static Texture white512;//TODO support more sizes

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

	private static HvlAction0 actionInitialize = HvlPainter2D.ACTION_INITIALIZE_DEFAULT;

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
	
	private static float xGrid, yGrid;

	public static float getSnappedX(float xArg){
		if(FLAG_GRIDSNAP.isEnabled()){
			if(xArg % xGrid < (xGrid / 2)){
				return xArg - (xArg % xGrid);
			}else{
				return xArg + xGrid - (xArg % xGrid);
			}
		}
		return xArg;
	}

	public static float getSnappedY(float yArg){
		if(FLAG_GRIDSNAP.isEnabled()){
			if(yArg % yGrid < (yGrid / 2)){
				return yArg - (yArg % yGrid);
			}else{
				return yArg + yGrid - (yArg % yGrid);
			}
		}
		return yArg;
	}
	
	public static void setGrid(float gridArg){
		xGrid = gridArg;
		yGrid = gridArg;
	}
	
	public static float getXGrid(){
		return xGrid;
	}

	public static void setXGrid(float xGridArg){
		xGrid = xGridArg;
	}

	public static float getYGrid(){
		return yGrid;
	}

	public static void setYGrid(float yGridArg){
		yGrid = yGridArg;
	}

	public static HvlFlag2D FLAG_TEXMAGBLUR = new HvlFlag2D(false){
		@Override
		public void onEnable(){}
		@Override
		public void onDisable(){}
	};

	public static HvlFlag2D FLAG_GRIDSNAP = new HvlFlag2D(false){
		@Override
		public void onEnable(){}
		@Override
		public void onDisable(){}
	};

	public static void hvlDrawLine(float x1, float y1, float x2, float y2, Color c){
		HvlLinePainter2D.hvlDrawLine(getSnappedX(x1), getSnappedY(y1), getSnappedX(x2), getSnappedY(y2), c);
	}

	public static void hvlDrawLine(float x1, float y1, float x2, float y2, Color c, float width){
		HvlLinePainter2D.hvlDrawLine(getSnappedX(x1), getSnappedY(y1), getSnappedX(x2), getSnappedY(y2), c, width);
	}
	
	public static void hvlDrawLinec(float x, float y, float xl, float yl, Color c){
		HvlLinePainter2D.hvlDrawLinec(getSnappedX(x), getSnappedY(y), getSnappedX(xl), getSnappedY(yl), c);
	}
	
	public static void hvlDrawLinec(float x, float y, float xl, float yl, Color c, float width){
		HvlLinePainter2D.hvlDrawLinec(getSnappedX(x), getSnappedY(y), getSnappedX(xl), getSnappedY(yl), c, width);
	}

	public static void hvlDrawQuad(float x, float y, float xl, float yl, Texture t){
		HvlQuadPainter2D.hvlDrawQuad(getSnappedX(x), getSnappedY(y), getSnappedX(xl), getSnappedY(yl), t);
	}

	public static void hvlDrawQuad(float x, float y, float xl, float yl, Texture t, Color c){
		HvlQuadPainter2D.hvlDrawQuad(getSnappedX(x), getSnappedY(y), getSnappedX(xl), getSnappedY(yl), t, c);
	}

	public static void hvlDrawQuad(float x, float y, float xl, float yl, float uvx1, float uvy1, float uvx2, float uvy2, Texture t){
		HvlQuadPainter2D.hvlDrawQuad(getSnappedX(x), getSnappedY(y), getSnappedX(xl), getSnappedY(yl), uvx1, uvy1, uvx2, uvy2, t);
	}

	public static void hvlDrawQuad(float x, float y, float xl, float yl, float uvx1, float uvy1, float uvx2, float uvy2, Texture t, Color c){
		HvlQuadPainter2D.hvlDrawQuad(getSnappedX(x), getSnappedY(y), getSnappedX(xl), getSnappedY(yl), uvx1, uvy1, uvx2, uvy2, t, c);
	}

	public static void hvlDrawQuad(float x, float y, float xl, float yl, HvlRenderFrame renderFrame){
		HvlQuadPainter2D.hvlDrawQuad(getSnappedX(x), getSnappedY(y), getSnappedX(xl), getSnappedY(yl), renderFrame);
	}

	public static void hvlDrawQuad(float x, float y, float xl, float yl, HvlAnimatedTextureArray texture){
		HvlQuadPainter2D.hvlDrawQuad(getSnappedX(x), getSnappedY(y), getSnappedX(xl), getSnappedY(yl), texture);
	}
	
	public static void hvlDrawQuad(float x, float y, float xl, float yl, HvlAnimatedTextureArray texture, Color c){
		HvlQuadPainter2D.hvlDrawQuad(getSnappedX(x), getSnappedY(y), getSnappedX(xl), getSnappedY(yl), texture, c);
	}

	public static void hvlDrawQuad(float x, float y, float xl, float yl, HvlAnimatedTextureUV texture){
		HvlQuadPainter2D.hvlDrawQuad(getSnappedX(x), getSnappedY(y), getSnappedX(xl), getSnappedY(yl), texture);
	}

	public static void hvlDrawQuad(float x, float y, float xl, float yl, HvlAnimatedTextureUV texture, Color c){
		HvlQuadPainter2D.hvlDrawQuad(getSnappedX(x), getSnappedY(y), getSnappedX(xl), getSnappedY(yl), texture, c);
	}

	public static void hvlDrawQuad(float x, float y, float xl, float yl, Color c){
		HvlQuadPainter2D.hvlDrawQuad(getSnappedX(x), getSnappedY(y), getSnappedX(xl), getSnappedY(yl), white512, c);//TODO support more sizes
	}

	public static void hvlDrawQuadc(float x, float y, float xl, float yl, Texture t){
		HvlQuadPainter2D.hvlDrawQuadc(getSnappedX(x), getSnappedY(y), getSnappedX(xl), getSnappedY(yl), t);
	}

	public static void hvlDrawQuadc(float x, float y, float xl, float yl, Texture t, Color c){
		HvlQuadPainter2D.hvlDrawQuadc(getSnappedX(x), getSnappedY(y), getSnappedX(xl), getSnappedY(yl), t, c);
	}

	public static void hvlDrawQuadc(float x, float y, float xl, float yl, float uvx1, float uvy1, float uvx2, float uvy2, Texture t){
		HvlQuadPainter2D.hvlDrawQuadc(getSnappedX(x), getSnappedY(y), getSnappedX(xl), getSnappedY(yl), uvx1, uvy1, uvx2, uvy2, t);
	}

	public static void hvlDrawQuadc(float x, float y, float xl, float yl, float uvx1, float uvy1, float uvx2, float uvy2, Texture t, Color c){
		HvlQuadPainter2D.hvlDrawQuadc(getSnappedX(x), getSnappedY(y), getSnappedX(xl), getSnappedY(yl), uvx1, uvy1, uvx2, uvy2, t, c);
	}

	public static void hvlDrawQuadc(float x, float y, float xl, float yl, HvlRenderFrame renderFrame){
		HvlQuadPainter2D.hvlDrawQuadc(getSnappedX(x), getSnappedY(y), getSnappedX(xl), getSnappedY(yl), renderFrame);
	}

	public static void hvlDrawQuadc(float x, float y, float xl, float yl, HvlAnimatedTextureArray texture){
		HvlQuadPainter2D.hvlDrawQuadc(getSnappedX(x), getSnappedY(y), getSnappedX(xl), getSnappedY(yl), texture);
	}
	
	public static void hvlDrawQuadc(float x, float y, float xl, float yl, HvlAnimatedTextureArray texture, Color c){
		HvlQuadPainter2D.hvlDrawQuadc(getSnappedX(x), getSnappedY(y), getSnappedX(xl), getSnappedY(yl), texture, c);
	}

	public static void hvlDrawQuadc(float x, float y, float xl, float yl, HvlAnimatedTextureUV texture){
		HvlQuadPainter2D.hvlDrawQuadc(getSnappedX(x), getSnappedY(y), getSnappedX(xl), getSnappedY(yl), texture);
	}

	public static void hvlDrawQuadc(float x, float y, float xl, float yl, HvlAnimatedTextureUV texture, Color c){
		HvlQuadPainter2D.hvlDrawQuadc(getSnappedX(x), getSnappedY(y), getSnappedX(xl), getSnappedY(yl), texture, c);
	}

	public static void hvlDrawQuadc(float x, float y, float xl, float yl, Color c){
		HvlQuadPainter2D.hvlDrawQuadc(getSnappedX(x), getSnappedY(y), getSnappedX(xl), getSnappedY(yl), white512, c);//TODO support more sizes
	}
	
	public static void hvlDrawTiledQuad(float x, float y, float w, float h, float lrWidth, float udHeight, String format, Texture t) {
		hvlDrawTiledQuad(x, y, w, h, lrWidth, lrWidth, udHeight, udHeight, format, t);
	}
	
	public static void hvlDrawTiledQuad(float x, float y, float w, float h, float lWidth, float rWidth, float uHeight, float dHeight, String format, Texture t) {
		hvlDrawTiledQuad(x, y, w, h, lWidth, rWidth, uHeight, dHeight, format, t, Color.white);
	}

	public static void hvlDrawTiledQuad(float x, float y, float w, float h, float lWidth, float rWidth, float uHeight, float dHeight, String format, Texture t, Color c) {
		HvlTiledRectPainter2D.hvlDrawTiledQuad(x, y, w, h, lWidth, rWidth, uHeight, dHeight, format, t, c);
	}
	
	public static void hvlDrawTiledQuad(float x, float y, float w, float h, float lrWidth, float udHeight, String format, Texture t, Color c) {
		hvlDrawTiledQuad(x, y, w, h, lrWidth, lrWidth, udHeight, udHeight, format, t, c);
	}

	public static void hvlDrawTiledQuadc(float x, float y, float w, float h, float lrWidth, float udHeight, String format, Texture t) {
		hvlDrawTiledQuadc(x, y, w, h, lrWidth, lrWidth, udHeight, udHeight, format, t);
	}
	
	public static void hvlDrawTiledQuadc(float x, float y, float w, float h, float lWidth, float rWidth, float uHeight, float dHeight, String format, Texture t) {
		hvlDrawTiledQuadc(x, y, w, h, lWidth, rWidth, uHeight, dHeight, format, t, Color.white);
	}

	public static void hvlDrawTiledQuadc(float x, float y, float w, float h, float lWidth, float rWidth, float uHeight, float dHeight, String format, Texture t, Color c) {
		hvlDrawTiledQuad(x - (w / 2), y - (h / 2), w, h, lWidth, rWidth, uHeight, dHeight, format, t, c);
	}
	
	public static void hvlDrawTiledQuadc(float x, float y, float w, float h, float lrWidth, float udHeight, String format, Texture t, Color c) {
		hvlDrawTiledQuadc(x, y, w, h, lrWidth, lrWidth, udHeight, udHeight, format, t, c);
	}

	public static void hvlRotate(float x, float y, float degrees){
		HvlSwivel2D.hvlRotate(x, y, degrees);
	}
	
	public static void hvlDrawLinea(float x1, float y1, float x2, float y2, Color c){
		HvlLinePainter2D.hvlDrawLine(getSnappedX(x1 + (Display.getWidth()/2)), getSnappedY(y1 + (Display.getHeight()/2)), getSnappedX(x2), getSnappedY(y2), c);
	}

	public static void hvlDrawLinea(float x1, float y1, float x2, float y2, Color c, float width){
		HvlLinePainter2D.hvlDrawLine(getSnappedX(x1 + (Display.getWidth()/2)), getSnappedY(y1 + (Display.getHeight()/2)), getSnappedX(x2), getSnappedY(y2), c, width);
	}
	
	public static void hvlDrawLineac(float x, float y, float xl, float yl, Color c){
		HvlLinePainter2D.hvlDrawLinec(getSnappedX(x + (Display.getWidth()/2)), getSnappedY(y + (Display.getHeight()/2)), getSnappedX(xl), getSnappedY(yl), c);
	}
	
	public static void hvlDrawLineac(float x, float y, float xl, float yl, Color c, float width){
		HvlLinePainter2D.hvlDrawLinec(getSnappedX(x + (Display.getWidth()/2)), getSnappedY(y + (Display.getHeight()/2)), getSnappedX(xl), getSnappedY(yl), c, width);
	}

	public static void hvlDrawQuada(float x, float y, float xl, float yl, Texture t){
		HvlQuadPainter2D.hvlDrawQuad(getSnappedX(x + (Display.getWidth()/2)), getSnappedY(y + (Display.getHeight()/2)), getSnappedX(xl), getSnappedY(yl), t);
	}

	public static void hvlDrawQuada(float x, float y, float xl, float yl, Texture t, Color c){
		HvlQuadPainter2D.hvlDrawQuad(getSnappedX(x + (Display.getWidth()/2)), getSnappedY(y + (Display.getHeight()/2)), getSnappedX(xl), getSnappedY(yl), t, c);
	}

	public static void hvlDrawQuada(float x, float y, float xl, float yl, float uvx1, float uvy1, float uvx2, float uvy2, Texture t){
		HvlQuadPainter2D.hvlDrawQuad(getSnappedX(x + (Display.getWidth()/2)), getSnappedY(y + (Display.getHeight()/2)), getSnappedX(xl), getSnappedY(yl), uvx1, uvy1, uvx2, uvy2, t);
	}

	public static void hvlDrawQuada(float x, float y, float xl, float yl, float uvx1, float uvy1, float uvx2, float uvy2, Texture t, Color c){
		HvlQuadPainter2D.hvlDrawQuad(getSnappedX(x + (Display.getWidth()/2)), getSnappedY(y + (Display.getHeight()/2)), getSnappedX(xl), getSnappedY(yl), uvx1, uvy1, uvx2, uvy2, t, c);
	}

	public static void hvlDrawQuada(float x, float y, float xl, float yl, HvlRenderFrame renderFrame){
		HvlQuadPainter2D.hvlDrawQuad(getSnappedX(x + (Display.getWidth()/2)), getSnappedY(y + (Display.getHeight()/2)), getSnappedX(xl), getSnappedY(yl), renderFrame);
	}

	public static void hvlDrawQuada(float x, float y, float xl, float yl, HvlAnimatedTextureArray texture){
		HvlQuadPainter2D.hvlDrawQuad(getSnappedX(x + (Display.getWidth()/2)), getSnappedY(y + (Display.getHeight()/2)), getSnappedX(xl), getSnappedY(yl), texture);
	}
	
	public static void hvlDrawQuada(float x, float y, float xl, float yl, HvlAnimatedTextureArray texture, Color c){
		HvlQuadPainter2D.hvlDrawQuad(getSnappedX(x + (Display.getWidth()/2)), getSnappedY(y + (Display.getHeight()/2)), getSnappedX(xl), getSnappedY(yl), texture, c);
	}

	public static void hvlDrawQuada(float x, float y, float xl, float yl, HvlAnimatedTextureUV texture){
		HvlQuadPainter2D.hvlDrawQuad(getSnappedX(x + (Display.getWidth()/2)), getSnappedY(y + (Display.getHeight()/2)), getSnappedX(xl), getSnappedY(yl), texture);
	}

	public static void hvlDrawQuada(float x, float y, float xl, float yl, HvlAnimatedTextureUV texture, Color c){
		HvlQuadPainter2D.hvlDrawQuad(getSnappedX(x + (Display.getWidth()/2)), getSnappedY(y + (Display.getHeight()/2)), getSnappedX(xl), getSnappedY(yl), texture, c);
	}

	public static void hvlDrawQuada(float x, float y, float xl, float yl, Color c){
		HvlQuadPainter2D.hvlDrawQuad(getSnappedX(x + (Display.getWidth()/2)), getSnappedY(y + (Display.getHeight()/2)), getSnappedX(xl), getSnappedY(yl), white512, c);//TODO support more sizes
	}

	public static void hvlDrawQuadac(float x, float y, float xl, float yl, Texture t){
		HvlQuadPainter2D.hvlDrawQuadc(getSnappedX(x + (Display.getWidth()/2)), getSnappedY(y + (Display.getHeight()/2)), getSnappedX(xl), getSnappedY(yl), t);
	}

	public static void hvlDrawQuadac(float x, float y, float xl, float yl, Texture t, Color c){
		HvlQuadPainter2D.hvlDrawQuadc(getSnappedX(x + (Display.getWidth()/2)), getSnappedY(y + (Display.getHeight()/2)), getSnappedX(xl), getSnappedY(yl), t, c);
	}

	public static void hvlDrawQuadac(float x, float y, float xl, float yl, float uvx1, float uvy1, float uvx2, float uvy2, Texture t){
		HvlQuadPainter2D.hvlDrawQuadc(getSnappedX(x + (Display.getWidth()/2)), getSnappedY(y + (Display.getHeight()/2)), getSnappedX(xl), getSnappedY(yl), uvx1, uvy1, uvx2, uvy2, t);
	}

	public static void hvlDrawQuadac(float x, float y, float xl, float yl, float uvx1, float uvy1, float uvx2, float uvy2, Texture t, Color c){
		HvlQuadPainter2D.hvlDrawQuadc(getSnappedX(x + (Display.getWidth()/2)), getSnappedY(y + (Display.getHeight()/2)), getSnappedX(xl), getSnappedY(yl), uvx1, uvy1, uvx2, uvy2, t, c);
	}

	public static void hvlDrawQuadac(float x, float y, float xl, float yl, HvlRenderFrame renderFrame){
		HvlQuadPainter2D.hvlDrawQuadc(getSnappedX(x + (Display.getWidth()/2)), getSnappedY(y + (Display.getHeight()/2)), getSnappedX(xl), getSnappedY(yl), renderFrame);
	}

	public static void hvlDrawQuadac(float x, float y, float xl, float yl, HvlAnimatedTextureArray texture){
		HvlQuadPainter2D.hvlDrawQuadc(getSnappedX(x + (Display.getWidth()/2)), getSnappedY(y + (Display.getHeight()/2)), getSnappedX(xl), getSnappedY(yl), texture);
	}
	
	public static void hvlDrawQuadac(float x, float y, float xl, float yl, HvlAnimatedTextureArray texture, Color c){
		HvlQuadPainter2D.hvlDrawQuadc(getSnappedX(x + (Display.getWidth()/2)), getSnappedY(y + (Display.getHeight()/2)), getSnappedX(xl), getSnappedY(yl), texture, c);
	}

	public static void hvlDrawQuadac(float x, float y, float xl, float yl, HvlAnimatedTextureUV texture){
		HvlQuadPainter2D.hvlDrawQuadc(getSnappedX(x + (Display.getWidth()/2)), getSnappedY(y + (Display.getHeight()/2)), getSnappedX(xl), getSnappedY(yl), texture);
	}

	public static void hvlDrawQuadac(float x, float y, float xl, float yl, HvlAnimatedTextureUV texture, Color c){
		HvlQuadPainter2D.hvlDrawQuadc(getSnappedX(x + (Display.getWidth()/2)), getSnappedY(y + (Display.getHeight()/2)), getSnappedX(xl), getSnappedY(yl), texture, c);
	}

	public static void hvlDrawQuadac(float x, float y, float xl, float yl, Color c){
		HvlQuadPainter2D.hvlDrawQuadc(getSnappedX(x + (Display.getWidth()/2)), getSnappedY(y + (Display.getHeight()/2)), getSnappedX(xl), getSnappedY(yl), white512, c);//TODO support more sizes
	}
	
	public static void hvlDrawTiledQuada(float x, float y, float w, float h, float lrWidth, float udHeight, String format, Texture t) {
		hvlDrawTiledQuad(x + (Display.getWidth()/2), y + (Display.getHeight()/2), w, h, lrWidth, lrWidth, udHeight, udHeight, format, t);
	}
	
	public static void hvlDrawTiledQuada(float x, float y, float w, float h, float lWidth, float rWidth, float uHeight, float dHeight, String format, Texture t) {
		hvlDrawTiledQuad(x + (Display.getWidth()/2), y + (Display.getHeight()/2), w, h, lWidth, rWidth, uHeight, dHeight, format, t, Color.white);
	}

	public static void hvlDrawTiledQuada(float x, float y, float w, float h, float lWidth, float rWidth, float uHeight, float dHeight, String format, Texture t, Color c) {
		HvlTiledRectPainter2D.hvlDrawTiledQuad(x + (Display.getWidth()/2), y + (Display.getHeight()/2), w, h, lWidth, rWidth, uHeight, dHeight, format, t, c);
	}
	
	public static void hvlDrawTiledQuada(float x, float y, float w, float h, float lrWidth, float udHeight, String format, Texture t, Color c) {
		hvlDrawTiledQuad(x + (Display.getWidth()/2), y + (Display.getHeight()/2), w, h, lrWidth, lrWidth, udHeight, udHeight, format, t, c);
	}

	public static void hvlDrawTiledQuadac(float x, float y, float w, float h, float lrWidth, float udHeight, String format, Texture t) {
		hvlDrawTiledQuadc(x + (Display.getWidth()/2), y + (Display.getHeight()/2), w, h, lrWidth, lrWidth, udHeight, udHeight, format, t);
	}
	
	public static void hvlDrawTiledQuadac(float x, float y, float w, float h, float lWidth, float rWidth, float uHeight, float dHeight, String format, Texture t) {
		hvlDrawTiledQuadc(x + (Display.getWidth()/2), y + (Display.getHeight()/2), w, h, lWidth, rWidth, uHeight, dHeight, format, t, Color.white);
	}

	public static void hvlDrawTiledQuadac(float x, float y, float w, float h, float lWidth, float rWidth, float uHeight, float dHeight, String format, Texture t, Color c) {
		hvlDrawTiledQuad(x - (w / 2) + (Display.getWidth()/2), y - (h / 2) + (Display.getHeight()/2), w, h, lWidth, rWidth, uHeight, dHeight, format, t, c);
	}
	
	public static void hvlDrawTiledQuadac(float x, float y, float w, float h, float lrWidth, float udHeight, String format, Texture t, Color c) {
		hvlDrawTiledQuadc(x + (Display.getWidth()/2), y + (Display.getHeight()/2), w, h, lrWidth, lrWidth, udHeight, udHeight, format, t, c);
	}

	public static void hvlDrawQuad(HvlCoord v1, HvlCoord v2, HvlCoord v3, HvlCoord v4, Texture t){
		HvlQuadPainter2D.hvlDrawQuad(v1, v2, v3, v4, t);
	}

	public static void hvlDrawQuad(HvlCoord v1, HvlCoord v2, HvlCoord v3, HvlCoord v4, Texture t, Color c){
		HvlQuadPainter2D.hvlDrawQuad(v1, v2, v3, v4, t, c);
	}

	public static void hvlDrawQuad(HvlCoord v1, HvlCoord v2, HvlCoord v3, HvlCoord v4, float uvx1, float uvy1, float uvx2, float uvy2, Texture t){
		HvlQuadPainter2D.hvlDrawQuad(v1, v2, v3, v4, uvx1, uvy1, uvx2, uvy2, t);
	}

	public static void hvlDrawQuad(HvlCoord v1, HvlCoord v2, HvlCoord v3, HvlCoord v4, float uvx1, float uvy1, float uvx2, float uvy2, Texture t, Color c){
		HvlQuadPainter2D.hvlDrawQuad(v1, v2, v3, v4, uvx1, uvy1, uvx2, uvy2, t, c);
	}
	
	public static void hvlDrawQuad(HvlCoord v1, HvlCoord v2, HvlCoord v3, HvlCoord v4, HvlCoord uv1, HvlCoord uv2, HvlCoord uv3, HvlCoord uv4, Texture t){
		HvlQuadPainter2D.hvlDrawQuad(v1, v2, v3, v4, uv1, uv2, uv3, uv4, t);
	}

	public static void hvlDrawQuad(HvlCoord v1, HvlCoord v2, HvlCoord v3, HvlCoord v4, HvlCoord uv1, HvlCoord uv2, HvlCoord uv3, HvlCoord uv4, Texture t, Color c){
		HvlQuadPainter2D.hvlDrawQuad(v1, v2, v3, v4, uv1, uv2, uv3, uv4, t, c);
	}

	//TODO
//	public static void hvlDrawQuad(HvlCoord v1, HvlCoord v2, HvlCoord v3, HvlCoord v4, HvlRenderFrame renderFrame){
//		HvlQuadPainter2D.hvlDrawQuad(v1, v2, v3, v4, renderFrame);
//	}

	public static void hvlDrawQuad(HvlCoord v1, HvlCoord v2, HvlCoord v3, HvlCoord v4, HvlAnimatedTextureArray texture){
		HvlQuadPainter2D.hvlDrawQuad(v1, v2, v3, v4, texture);
	}
	
	public static void hvlDrawQuad(HvlCoord v1, HvlCoord v2, HvlCoord v3, HvlCoord v4, HvlAnimatedTextureArray texture, Color c){
		HvlQuadPainter2D.hvlDrawQuad(v1, v2, v3, v4, texture, c);
	}

	public static void hvlDrawQuad(HvlCoord v1, HvlCoord v2, HvlCoord v3, HvlCoord v4, HvlAnimatedTextureUV texture){
		HvlQuadPainter2D.hvlDrawQuad(v1, v2, v3, v4, texture);
	}

	public static void hvlDrawQuad(HvlCoord v1, HvlCoord v2, HvlCoord v3, HvlCoord v4, HvlAnimatedTextureUV texture, Color c){
		HvlQuadPainter2D.hvlDrawQuad(v1, v2, v3, v4, texture, c);
	}

	public static void hvlDrawQuad(HvlCoord v1, HvlCoord v2, HvlCoord v3, HvlCoord v4, Color c){
		HvlQuadPainter2D.hvlDrawQuad(v1, v2, v3, v4, white512, c);//TODO support more sizes
	}
	
	public static void hvlRotatea(float x, float y, float degrees){
		HvlSwivel2D.hvlRotate(x + (Display.getWidth()/2), y + (Display.getHeight()/2), degrees);
	}

	public static void hvlResetRotation(){
		HvlSwivel2D.hvlResetRotation();
	}

	@Deprecated
	public static void hvlForceRefresh(){
		hvlDrawQuad(0f, 0f, 0f, 0f, refreshTexture);
	}

}
