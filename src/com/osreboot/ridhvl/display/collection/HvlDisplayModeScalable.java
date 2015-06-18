package com.osreboot.ridhvl.display.collection;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;

import org.lwjgl.opengl.Display;

import com.osreboot.ridhvl.display.HvlDisplayMode;
import com.osreboot.ridhvl.painter.HvlRenderFrame;
import com.osreboot.ridhvl.painter.HvlRenderFrame.HvlRenderFrameProfile;
import com.osreboot.ridhvl.painter.painter2d.HvlPainter2D;

public class HvlDisplayModeScalable extends HvlDisplayMode{
	
	private int initialHeight, initialWidth;
	
	private HvlRenderFrame renderFrame;
	
	@Override
	public void configureDisplay(){
		Display.setResizable(true);
	}
	
	@Override
	public void initialize(){
		initialHeight = Display.getHeight();
		initialWidth = Display.getWidth();
		renderFrame = new HvlRenderFrame(HvlRenderFrameProfile.DEFAULT, initialWidth, initialHeight);
	}
	
	@Override
	public void preUpdate(float delta){
		HvlPainter2D.hvlGL11FitToChanged(initialWidth, initialHeight, Display.getWidth(), Display.getHeight());
		
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		
		HvlRenderFrame.setCurrentRenderFrame(renderFrame.getID());
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void postUpdate(float delta){
		HvlRenderFrame.setCurrentRenderFrame(0);
		
		HvlPainter2D.hvlDrawQuad(0, Display.getHeight(), Display.getWidth(), -Display.getHeight(), renderFrame.getTextureID());
	}

}
