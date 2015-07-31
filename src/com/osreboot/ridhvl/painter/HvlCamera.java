package com.osreboot.ridhvl.painter;

import static com.osreboot.ridhvl.painter.painter2d.HvlPainter2D.*;
import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.opengl.Display;


public class HvlCamera {

	private static float x, y, xOffset, yOffset, rotation;

	public enum HvlCameraAlignment{
		TOPLEFT, CENTER
	}

	public static void setAlignment(HvlCameraAlignment alignmentArg){
		switch(alignmentArg){
		case TOPLEFT: 
			xOffset = 0;
			yOffset = 0;
			break;
		case CENTER: 
			xOffset = -Display.getWidth()/2;
			yOffset = -Display.getHeight()/2;
			break;
		}
	}

	public static void preTransform(){
		glPushMatrix();
		glTranslatef(-x - xOffset, -y - yOffset, 0);
		hvlRotate(x, y, rotation);
	}

	public static void postTransform(){
		hvlResetRotation();
		glPopMatrix();
	}

	public static float getX() {
		return x;
	}

	public static void setX(float xArg){
		x = xArg;
	}

	public static float getY(){
		return y;
	}

	public static void setY(float yArg){
		y = yArg;
	}

	public static void setPosition(float xArg, float yArg){
		x = xArg;
		y = yArg;
	}

	public static float getRotation(){
		return rotation;
	}

	public static void setRotation(float rotationArg){
		rotation = rotationArg;
	}

	public static float getxOffset(){
		return xOffset;
	}

	public static void setxOffset(float xOffsetArg){
		xOffset = xOffsetArg;
	}

	public static float getyOffset(){
		return yOffset;
	}

	public static void setyOffset(float yOffsetArg){
		yOffset = yOffsetArg;
	}

}
