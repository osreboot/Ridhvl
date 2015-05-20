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

import com.osreboot.ridhvl.HvlDisplay;
import com.osreboot.ridhvl.HvlDisplay.HvlDisplayMode;
import com.osreboot.ridhvl.painter.painter2d.HvlPainter2D.HvlPainter2DTemplate;

public abstract class HvlTemplate2DBasic extends HvlTemplate{

	private int frameRate, displayWidth, displayHeight;

	public HvlTemplate2DBasic(int frameRateArg, int width, int height, String title, HvlDisplayMode displayModeArg){
		frameRate = frameRateArg;
		
		displayWidth = width;
		displayHeight = height;

		try{
			Display.setDisplayMode(new DisplayMode(width, height));
			Display.setTitle(title);
			HvlDisplay.configureDisplay(displayModeArg, (float)width/(float)height);
			Display.create();
		}catch(Exception e){
			e.printStackTrace();
		}

		hvlGL11Init(HvlPainter2DTemplate.DEFAULT);
		hvlGL11Ortho(width, height);
		
		start();
	}
	
	public HvlTemplate2DBasic(int frameRateArg, int width, int height, String title, String iconName, HvlDisplayMode displayModeArg){
		frameRate = frameRateArg;

		displayWidth = width;
		displayHeight = height;
		
		try{
			Display.setDisplayMode(new DisplayMode(width, height));
			Display.setIcon(new ByteBuffer[]{new ImageIOImageData().imageToByteBuffer(ImageIO.read(new FileInputStream("res/" + iconName + ".png")), false, false, null)});
			Display.setTitle(title);
			HvlDisplay.configureDisplay(displayModeArg, (float)width/(float)height);
			Display.create();
		}catch(Exception e){
			e.printStackTrace();
		}

		hvlGL11Init(HvlPainter2DTemplate.DEFAULT);
		hvlGL11Ortho(width, height);
		
		start();
	}

	@Override
	public void preUpdate(long delta){
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	}

	@Override
	public void postUpdate(long delta){
		Display.update();
		Display.sync(frameRate);

		getTimer().setRunning(!Display.isCloseRequested());
		if(!getTimer().isRunning()){
			Display.destroy();
			System.exit(1);
		}
	}
	
	public int getWidth(){
		return displayWidth;
	}
	
	public int getHeight(){
		return displayHeight;
	}

}
