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

	protected static float xGridInterval, yGridInterval;
	protected static boolean gridSnapEnabled;
	
	public static boolean hvlGridSnapEnabled() {
		return gridSnapEnabled;
	}
	
	public static float hvlGetXGridInterval() {
		return xGridInterval;
	}
	
	public static float hvlGetYGridInterval() {
		return yGridInterval;
	}
	
	public static float hvlSnapToXGrid(float xIn) {		
		if (xIn % xGridInterval < (xGridInterval / 2)) {
			return xIn - (xIn % xGridInterval);
		}
		else
		{
			return xIn + xGridInterval - (xIn % xGridInterval);
		}
	}
	
	public static float hvlSnapToYGrid(float yIn) {
		if (yIn % yGridInterval < (yGridInterval / 2)) {
			return yIn - (yIn % yGridInterval);
		}
		else
		{
			return yIn + yGridInterval - (yIn % yGridInterval);
		}
	}
	
	protected static float conditionalXSnap(float xIn) {
		if (gridSnapEnabled)
			return hvlSnapToXGrid(xIn);
		return xIn;
	}
	
	protected static float conditionalYSnap(float yIn) {
		if (gridSnapEnabled)
			return hvlSnapToYGrid(yIn);
		return yIn;
	}
	
	public static void hvlEnableGridSnap(float xInterval, float yInterval) {
		gridSnapEnabled = true;
		xGridInterval = xInterval;
		yGridInterval = yInterval;
	}
	
	public static void hvlEnableGridSnap() {
		gridSnapEnabled = true;
	}
	
	public static void hvlDisableGridSnap() {
		gridSnapEnabled = false;
	}
	
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

	public static HvlFlag2D TEXMAGBLUR = new HvlFlag2D(false){
		@Override
		public void enable(){}
		@Override
		public void disable(){}
	};

	public static void hvlDrawLine(float x1, float y1, float x2, float y2, Color c){
		HvlLinePainter2D.hvlDrawLine(conditionalXSnap(x1), conditionalYSnap(y1), conditionalXSnap(x2), conditionalYSnap(y2), c);
	}

	public static void hvlDrawLine(float x1, float y1, float x2, float y2, Color c, float width){
		HvlLinePainter2D.hvlDrawLine(conditionalXSnap(x1), conditionalYSnap(y1), conditionalXSnap(x2), conditionalYSnap(y2), c, width);
	}

	public static void hvlDrawQuad(float x, float y, float xl, float yl, Texture t){
		HvlQuadPainter2D.hvlDrawQuad(conditionalXSnap(x), conditionalYSnap(y), conditionalXSnap(xl), conditionalYSnap(yl), t);
	}

	public static void hvlDrawQuad(float x, float y, float xl, float yl, Texture t, Color c){
		HvlQuadPainter2D.hvlDrawQuad(conditionalXSnap(x), conditionalYSnap(y), conditionalXSnap(xl), conditionalYSnap(yl), t, c);
	}

	public static void hvlDrawQuad(float x, float y, float xl, float yl, float uvx1, float uvy1, float uvx2, float uvy2, Texture t){
		HvlQuadPainter2D.hvlDrawQuad(conditionalXSnap(x), conditionalYSnap(y), conditionalXSnap(xl), conditionalYSnap(yl), uvx1, uvy1, uvx2, uvy2, t);
	}

	public static void hvlDrawQuad(float x, float y, float xl, float yl, float uvx1, float uvy1, float uvx2, float uvy2, Texture t, Color c){
		HvlQuadPainter2D.hvlDrawQuad(conditionalXSnap(x), conditionalYSnap(y), conditionalXSnap(xl), conditionalYSnap(yl), uvx1, uvy1, uvx2, uvy2, t, c);
	}

	public static void hvlDrawQuad(float x, float y, float xl, float yl, HvlRenderFrame renderFrame){
		HvlQuadPainter2D.hvlDrawQuad(conditionalXSnap(x), conditionalYSnap(y), conditionalXSnap(xl), conditionalYSnap(yl), renderFrame);
	}

	public static void hvlDrawQuad(float x, float y, float xl, float yl, HvlAnimatedTextureArray texture){
		HvlQuadPainter2D.hvlDrawQuad(conditionalXSnap(x), conditionalYSnap(y), conditionalXSnap(xl), conditionalYSnap(yl), texture);
	}

	public static void hvlDrawQuad(float x, float y, float xl, float yl, HvlAnimatedTextureUV texture){
		HvlQuadPainter2D.hvlDrawQuad(conditionalXSnap(x), conditionalYSnap(y), conditionalXSnap(xl), conditionalYSnap(yl), texture);
	}
	
	public static void hvlDrawQuad(float x, float y, float xl, float yl, HvlAnimatedTextureUV texture, Color c){
		HvlQuadPainter2D.hvlDrawQuad(conditionalXSnap(x), conditionalYSnap(y), conditionalXSnap(xl), conditionalYSnap(yl), texture, c);
	}

	public static void hvlDrawQuad(float x, float y, float xl, float yl, Color c){
		HvlQuadPainter2D.hvlDrawQuad(conditionalXSnap(x), conditionalYSnap(y), conditionalXSnap(xl), conditionalYSnap(yl), white512, c);//TODO support more sizes
	}
	
	public static void hvlDrawQuadc(float x, float y, float xl, float yl, Texture t){
		HvlQuadPainter2D.hvlDrawQuadc(conditionalXSnap(x), conditionalYSnap(y), conditionalXSnap(xl), conditionalYSnap(yl), t);
	}

	public static void hvlDrawQuadc(float x, float y, float xl, float yl, Texture t, Color c){
		HvlQuadPainter2D.hvlDrawQuadc(conditionalXSnap(x), conditionalYSnap(y), conditionalXSnap(xl), conditionalYSnap(yl), t, c);
	}

	public static void hvlDrawQuadc(float x, float y, float xl, float yl, float uvx1, float uvy1, float uvx2, float uvy2, Texture t){
		HvlQuadPainter2D.hvlDrawQuadc(conditionalXSnap(x), conditionalYSnap(y), conditionalXSnap(xl), conditionalYSnap(yl), uvx1, uvy1, uvx2, uvy2, t);
	}

	public static void hvlDrawQuadc(float x, float y, float xl, float yl, float uvx1, float uvy1, float uvx2, float uvy2, Texture t, Color c){
		HvlQuadPainter2D.hvlDrawQuadc(conditionalXSnap(x), conditionalYSnap(y), conditionalXSnap(xl), conditionalYSnap(yl), uvx1, uvy1, uvx2, uvy2, t, c);
	}

	public static void hvlDrawQuadc(float x, float y, float xl, float yl, HvlRenderFrame renderFrame){
		HvlQuadPainter2D.hvlDrawQuadc(conditionalXSnap(x), conditionalYSnap(y), conditionalXSnap(xl), conditionalYSnap(yl), renderFrame);//TODO test this
	}

	public static void hvlDrawQuadc(float x, float y, float xl, float yl, HvlAnimatedTextureArray texture){
		HvlQuadPainter2D.hvlDrawQuadc(conditionalXSnap(x), conditionalYSnap(y), conditionalXSnap(xl), conditionalYSnap(yl), texture);
	}

	public static void hvlDrawQuadc(float x, float y, float xl, float yl, HvlAnimatedTextureUV texture){
		HvlQuadPainter2D.hvlDrawQuadc(conditionalXSnap(x), conditionalYSnap(y), conditionalXSnap(xl), conditionalYSnap(yl), texture);
	}
	
	public static void hvlDrawQuadc(float x, float y, float xl, float yl, HvlAnimatedTextureUV texture, Color c){
		HvlQuadPainter2D.hvlDrawQuadc(conditionalXSnap(x), conditionalYSnap(y), conditionalXSnap(xl), conditionalYSnap(yl), texture, c);
	}

	public static void hvlDrawQuadc(float x, float y, float xl, float yl, Color c){
		HvlQuadPainter2D.hvlDrawQuadc(conditionalXSnap(x), conditionalYSnap(y), conditionalXSnap(xl), conditionalYSnap(yl), white512, c);//TODO support more sizes
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
