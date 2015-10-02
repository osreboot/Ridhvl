package com.osreboot.ridhvl.template;

import static com.osreboot.ridhvl.painter.painter2d.HvlPainter2D.*;
import static org.lwjgl.opengl.GL11.*;

import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.opengl.ImageIOImageData;
import org.newdawn.slick.opengl.Texture;

import com.osreboot.ridhvl.action.HvlAction1;
import com.osreboot.ridhvl.display.HvlDisplay;
import com.osreboot.ridhvl.display.HvlDisplayMode;
import com.osreboot.ridhvl.external.HvlVerifier;
import com.osreboot.ridhvl.input.HvlInput;
import com.osreboot.ridhvl.loader.HvlSoundLoader;
import com.osreboot.ridhvl.loader.HvlTextureLoader;
import com.osreboot.ridhvl.loader.HvlTextureSeriesLoader;
import com.osreboot.ridhvl.menu.HvlMenuDJ;
import com.osreboot.ridhvl.painter.HvlAnimatedTexture;
import com.osreboot.ridhvl.painter.HvlCamera;
import com.osreboot.ridhvl.painter.HvlCursor;
import com.osreboot.ridhvl.painter.painter2d.HvlPainter2D.HvlPainter2DProfile;

public abstract class HvlTemplateInteg2D extends HvlTemplate{

	public static Texture getTexture(int indexArg){
		return ((HvlTemplateInteg2D)HvlTemplate.getNewestInstance()).getTextureLoader().getResource(indexArg);
	}
	
	public static Audio getSound(int indexArg){
		return ((HvlTemplateInteg2D)HvlTemplate.getNewestInstance()).getSoundLoader().getResource(indexArg);
	}
	
	public static ArrayList<Texture> getTextureSeries(int indexArg){
		return ((HvlTemplateInteg2D)HvlTemplate.getNewestInstance()).getTextureSeriesLoader().getResource(indexArg);
	}
	
	private int frameRate, displayWidth, displayHeight;
	
	private HvlTextureLoader textureLoader;
	private HvlSoundLoader soundLoader;
	private HvlTextureSeriesLoader textureSeriesLoader;

	public HvlTemplateInteg2D(int frameRateArg, int width, int height, String title, HvlDisplayMode displayModeArg){
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
		
		HvlChronology.initialize();
		
		hvlGL11Init(HvlPainter2DProfile.DEFAULT);//TODO convert to HvlChronology.Initialize
		hvlGL11Ortho(width, height);//TODO convert to HvlChronology.Initialize
		HvlVerifier.globalVerify();//TODO convert to HvlChronology.Initialize
		HvlDisplay.initializeDisplayMode();//TODO convert to HvlChronology.Initialize
		HvlInput.initialize();//TODO convert to HvlChronology.Initialize
		
		textureLoader = new HvlTextureLoader();
		soundLoader = new HvlSoundLoader();
		textureSeriesLoader = new HvlTextureSeriesLoader();
		
		start();
	}
	
	public HvlTemplateInteg2D(int frameRateArg, int width, int height, String title, String iconName, HvlDisplayMode displayModeArg){
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

		HvlChronology.initialize();
		
		hvlGL11Init(HvlPainter2DProfile.DEFAULT);//TODO convert to HvlChronology.Initialize
		hvlGL11Ortho(width, height);//TODO convert to HvlChronology.Initialize
		HvlVerifier.globalVerify();//TODO convert to HvlChronology.Initialize
		HvlDisplay.initializeDisplayMode();//TODO convert to HvlChronology.Initialize
		HvlInput.initialize();//TODO convert to HvlChronology.Initialize
		
		textureLoader = new HvlTextureLoader();
		soundLoader = new HvlSoundLoader();
		textureSeriesLoader = new HvlTextureSeriesLoader();
		
		start();
	}

	private HvlAction1<Float> preCameraTransform;
	
	@Override
	public void preUpdate(float delta){
		HvlChronology.preUpdate(delta);
		
		HvlInput.update();//TODO convert to HvlChronology.Update
		HvlDisplay.preUpdate(delta);//TODO convert to HvlChronology.Update
		HvlMenuDJ.update(delta);//TODO convert to HvlChronology.Update
		HvlAnimatedTexture.updateTextures(delta);//TODO convert to HvlChronology.Update
		
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		
		if(preCameraTransform != null) preCameraTransform.run(delta);
		
		HvlCamera.doTransform();//TODO convert to HvlChronology.Update
	}

	@Override
	public void postUpdate(float delta){
		HvlChronology.postUpdate(delta);
		
		HvlCursor.drawCursor();//TODO convert to HvlChronology.Update
		HvlCamera.undoTransform();//TODO convert to HvlChronology.Update
		HvlDisplay.postUpdate(delta);//TODO convert to HvlChronology.Update
		
		Display.update();
		Display.sync(frameRate);
		
		if(Display.isCloseRequested()) exit();
	}
	
	public void exit(){
		getTimer().setRunning(false);
		Display.destroy();
		System.exit(0);
	}
	
	public HvlAction1<Float> getPreCameraTransform(){
		return preCameraTransform;
	}

	public void setPreCameraTransform(HvlAction1<Float> preCameraTransformArg){
		preCameraTransform = preCameraTransformArg;
	}
	
	public HvlTextureLoader getTextureLoader(){
		return textureLoader;
	}
	
	public HvlSoundLoader getSoundLoader(){
		return soundLoader;
	}
	
	public HvlTextureSeriesLoader getTextureSeriesLoader(){
		return textureSeriesLoader;
	}

	public int getWidth(){
		return displayWidth;
	}
	
	public int getHeight(){
		return displayHeight;
	}
	
}
