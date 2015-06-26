package com.osreboot.ridhvl.painter;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

import com.osreboot.ridhvl.painter.painter2d.HvlPainter2D;


public class HvlCursor {

	private static float xOffset, yOffset, width, height;
	private static Texture texture;
	private static Color color = Color.white;

	public static void setNativeHidden(boolean hiddenArg){
		Mouse.setGrabbed(hiddenArg);
	}

	public static boolean getNativeHidden(){
		return Mouse.isGrabbed();
	}

	public static void drawCursor(){
		if(texture != null && Mouse.isInsideWindow()){
			HvlPainter2D.hvlDrawQuad(getCursorX() + xOffset, getCursorY() + yOffset, width, height, texture, color);//TODO account for inversion
		}
	}

	public static float getXOffset(){
		return xOffset;
	}

	public static void setXOffset(float xOffsetArg){
		xOffset = xOffsetArg;
	}

	public static float getYOffset(){
		return yOffset;
	}

	public static void setYOffset(float yOffsetArg){
		yOffset = yOffsetArg;
	}

	public static Color getColor(){
		return color;
	}

	public static void setColor(Color colorArg){
		color = colorArg;
	}

	public static float getWidth(){
		return width;
	}

	public static void setWidth(float widthArg){
		width = widthArg;
	}

	public static float getHeight(){
		return height;
	}

	public static void setHeight(float heightArg){
		height = heightArg;
	}

	public static Texture getTexture(){
		return texture;
	}

	public static void setTexture(Texture textureArg){
		texture = textureArg;
		width = texture.getImageWidth();
		height = texture.getImageHeight();
	}

	public static int getCursorX(){
		return Mouse.getX();
	}

	public static int getCursorY(){
		return Display.getHeight() - Mouse.getY();
	}

	public static void setOffset(float offsetArg){
		xOffset = offsetArg;
		yOffset = offsetArg;
	}
	
	public static void applySizeScale(float scale){
		width *= scale;
		height *= scale;
	}
	
	public static void applyOffsetScale(float scale){
		xOffset *= scale;
		yOffset *= scale;
	}
	
	public static void applyScale(float scale){
		applySizeScale(scale);
		applyOffsetScale(scale);
	}

}