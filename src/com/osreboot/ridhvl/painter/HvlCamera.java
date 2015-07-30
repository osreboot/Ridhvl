package com.osreboot.ridhvl.painter;

import static org.lwjgl.opengl.GL11.*;


public class HvlCamera {
	
	private static float x, y;

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
	
	public static void preTransform(){
		glPushMatrix();
		glTranslatef(x, y, 0);
	}
	
	public static void postTransform(){
		glPopMatrix();
	}
	
}
