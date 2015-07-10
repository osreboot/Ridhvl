package com.osreboot.ridhvl.template;

import static com.osreboot.ridhvl.painter.painter2d.HvlPainter2D.hvlGL11Init;
import static com.osreboot.ridhvl.painter.painter2d.HvlPainter2D.hvlGL11Ortho;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;

import java.io.FileInputStream;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.opengl.ImageIOImageData;

import com.osreboot.ridhvl.display.HvlDisplay;
import com.osreboot.ridhvl.display.HvlDisplayMode;
import com.osreboot.ridhvl.painter.HvlAnimatedTexture;
import com.osreboot.ridhvl.painter.HvlCursor;
import com.osreboot.ridhvl.painter.painter2d.HvlPainter2D.HvlPainter2DProfile;

public abstract class HvlTemplate2D extends HvlTemplate{

	private int frameRate, displayWidth, displayHeight;

	public HvlTemplate2D(int frameRateArg, int width, int height, String title, HvlDisplayMode displayModeArg){
		super();
		
		frameRate = frameRateArg;
		
		displayWidth = width;
		displayHeight = height;

		try{
			Display.setDisplayMode(new DisplayMode(width, height));
			Display.setTitle(title);
			HvlDisplay.setDisplayMode(displayModeArg);
			Display.create();
		}catch(Exception e){
			e.printStackTrace();
		}

		hvlGL11Init(HvlPainter2DProfile.DEFAULT);
		hvlGL11Ortho(width, height);
		
		HvlDisplay.initializeDisplayMode();
		
		start();
	}
	
	public HvlTemplate2D(int frameRateArg, int width, int height, String title, String iconName, HvlDisplayMode displayModeArg){
		super();
		
		frameRate = frameRateArg;

		displayWidth = width;
		displayHeight = height;
		
		try{
			Display.setDisplayMode(new DisplayMode(width, height));
			Display.setIcon(new ByteBuffer[]{new ImageIOImageData().imageToByteBuffer(ImageIO.read(new FileInputStream("res/" + iconName + ".png")), false, false, null)});
			Display.setTitle(title);
			HvlDisplay.setDisplayMode(displayModeArg);
			Display.create();
		}catch(Exception e){
			e.printStackTrace();
		}

		hvlGL11Init(HvlPainter2DProfile.DEFAULT);
		hvlGL11Ortho(width, height);
		
		HvlDisplay.initializeDisplayMode();
		
		start();
	}

	@Override
	public void preUpdate(float delta){
		HvlDisplay.preUpdate(delta);
		
		HvlAnimatedTexture.updateTextures(delta);
		
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	}

	@Override
	public void postUpdate(float delta){
		HvlCursor.drawCursor();
		
		HvlDisplay.postUpdate(delta);
		
		Display.update();
		Display.sync(frameRate);

		if(Display.isCloseRequested()) exit();
	}
	
	public void exit(){
		getTimer().setRunning(false);
		Display.destroy();
		System.exit(0);
	}
	
	public int getWidth(){
		return displayWidth;
	}
	
	public int getHeight(){
		return displayHeight;
	}

}
