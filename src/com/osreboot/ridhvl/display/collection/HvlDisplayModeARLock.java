package com.osreboot.ridhvl.display.collection;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;

import org.lwjgl.opengl.Display;

import com.osreboot.ridhvl.display.HvlDisplayMode;
import com.osreboot.ridhvl.painter.HvlRenderFrame;
import com.osreboot.ridhvl.painter.HvlRenderFrame.FBOUnsupportedException;
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
		try{
			renderFrame = new HvlRenderFrame(getCoordinateWidth(), getCoordinateHeight());
		}catch(FBOUnsupportedException e){
			e.printStackTrace();
		}
	}

	@Override
	public void preUpdate(float delta){
		if(hasBeenResized()) resizePerspective(getCoordinateWidth(), getCoordinateHeight(), Display.getWidth(), Display.getHeight(), true);

		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

		HvlRenderFrame.setCurrentRenderFrame(renderFrame);
	}

	@Override
	public void postUpdate(float delta){
		HvlRenderFrame.setCurrentRenderFrame(null);

		float oldAR = (float)getCoordinateWidth()/(float)getCoordinateHeight();
		//float newAR = (float)Display.getWidth()/(float)Display.getHeight();
		
		//HvlPainter2D.hvlDrawQuad(oldAR >= newAR ? 0 : (Display.getWidth()/2) - ((oldAR * Display.getHeight())/2), Display.getHeight(), oldAR >= newAR ? Display.getWidth() : (Display.getWidth()/2) - (oldAR * Display.getHeight()), -Display.getHeight(), renderFrame);
		float x = 0;
		float y = 0;
		float xl = Display.getHeight() * oldAR;
		float yl = Display.getWidth() * (1f/oldAR);
		HvlPainter2D.hvlDrawQuad(x, y, xl, yl, renderFrame);
	}

}
