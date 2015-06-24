package com.osreboot.ridhvl.display.collection;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.opengl.Display;

import com.osreboot.ridhvl.display.HvlDisplayMode;
import com.osreboot.ridhvl.painter.HvlRenderFrame;
import com.osreboot.ridhvl.painter.HvlRenderFrame.HvlRenderFrameProfile;
import com.osreboot.ridhvl.painter.painter2d.HvlPainter2D;

@Deprecated
public class HvlDisplayModeARLock extends HvlDisplayMode{

	private HvlRenderFrame renderFrame;

	@Override
	public void configureDisplay(){
		Display.setResizable(true);
	}

	@Override
	public void initialize(){
		super.initialize();
		renderFrame = new HvlRenderFrame(HvlRenderFrameProfile.DEFAULT, getCoordinateWidth(), getCoordinateHeight());
	}

	@Override
	public void preUpdate(float delta){
		if(hasBeenResized()) resizePerspective(getCoordinateWidth(), getCoordinateHeight(), Display.getWidth(), Display.getHeight());

		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

		HvlRenderFrame.setCurrentRenderFrame(renderFrame.getID());
	}

	@Override
	public void postUpdate(float delta){
		HvlRenderFrame.setCurrentRenderFrame(0);

		int oldAR = getCoordinateWidth()/getCoordinateHeight();
		int newAR = Display.getWidth()/Display.getHeight();
		
		HvlPainter2D.hvlDrawQuad(oldAR >= newAR ? 0 : (Display.getWidth()/2) - ((oldAR * Display.getHeight())/2), Display.getHeight(), oldAR >= newAR ? Display.getWidth() : (Display.getWidth()/2) - (oldAR * Display.getHeight()), -Display.getHeight(), renderFrame.getTextureID());
	}

}
