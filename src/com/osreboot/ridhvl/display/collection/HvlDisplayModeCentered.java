package com.osreboot.ridhvl.display.collection;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.opengl.Display;

import com.osreboot.ridhvl.display.HvlDisplayMode;
import com.osreboot.ridhvl.painter.HvlRenderFrame;
import com.osreboot.ridhvl.painter.painter2d.HvlPainter2D;

public class HvlDisplayModeCentered extends HvlDisplayMode{
	
	private HvlRenderFrame renderFrame;
	
	@Override
	public void configureDisplay(){
		Display.setResizable(true);
	}
	
	@Override
	public void initialize(){
		super.initialize();
		renderFrame = new HvlRenderFrame(getCoordinateWidth(), getCoordinateHeight());
	}
	
	@Override
	public void preUpdate(float delta){
		if(hasBeenResized()) resizePerspective(getCoordinateWidth(), getCoordinateHeight(), Display.getWidth(), Display.getHeight());
		
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		
		HvlRenderFrame.setCurrentRenderFrame(renderFrame);
	}
	
	@Override
	public void postUpdate(float delta){
		HvlRenderFrame.setCurrentRenderFrame(null);
		
		HvlPainter2D.hvlDrawQuad((Display.getWidth()/2) - (getCoordinateWidth()/2), (Display.getHeight()/2) + (getCoordinateHeight()/2), getCoordinateWidth(), -getCoordinateHeight(), renderFrame);
	}
	
}
