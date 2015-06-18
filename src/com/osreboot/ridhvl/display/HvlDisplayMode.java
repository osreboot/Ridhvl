package com.osreboot.ridhvl.display;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

public abstract class HvlDisplayMode {

	private int coordinateWidth, coordinateHeight;
	
	public HvlDisplayMode(){}
	
	public abstract void configureDisplay();
	
	public void initialize(){
		coordinateWidth = Display.getWidth();
		coordinateHeight = Display.getHeight();
	}
	
	public void preUpdate(float delta){}
	public void postUpdate(float delta){}
	
	protected void resizePerspective(int oldWidth, int oldHeight, int newWidth, int newHeight){
		GL11.glViewport(0, 0, newWidth < oldWidth ? oldWidth : newWidth, newHeight < oldHeight ? oldHeight : newHeight);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, newWidth < oldWidth ? oldWidth : newWidth, 0, newHeight < oldHeight ? oldHeight : newHeight, 1, -1);
	}
	
	public int getCoordinateWidth(){
		return coordinateWidth;
	}
	
	public int getCoordinateHeight(){
		return coordinateHeight;
	}
	
}
