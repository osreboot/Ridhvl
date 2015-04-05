package com.osreboot.ridhvl.test;

import java.io.FileInputStream;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import static org.lwjgl.opengl.GL11.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import com.osreboot.ridhvl.hvlUpdater;
import static com.osreboot.ridhvl.painter.painter2d.hvlPainter2D.*;


public class Painter2DTest extends hvlUpdater{

	public static void main(String[] args){
		try{
			Display.setDisplayMode(new DisplayMode(1280, 720));
			//Display.setIcon(new ByteBuffer[]{new ImageIOImageData().imageToByteBuffer(ImageIO.read(new FileInputStream("res/Icon32.png")), false, false, null)}); //TODO
			Display.setTitle("Unnamed");
			Display.create();
			
			GL11_Init(hvlPainter2DTemplate.DEFAULT);
			GL11_Ortho(1280, 720);

			white = TextureLoader.getTexture("PNG", new FileInputStream("res/White.png"));
			
		}catch(Exception e){e.printStackTrace();}
		
		new Painter2DTest();
	}
	
	static Texture white;
	long gradient = 0;
	
	public Painter2DTest() {
		
	}

	@Override
	public void update(long delta) {
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

		gradient = gradient < 1280 ? gradient + delta/2 : 0;
		
		for(int i = 0; i < 360; i++){
			drawQuad(0, i*2, gradient - (i*2), 2, white, new Color(1f, ((float)i - 180)/180, (float)i/360));
		}

		Display.update();
		Display.sync(60);
		
		super.setRunning(!Display.isCloseRequested());
		if(!super.isRunning()){
			Display.destroy();
			System.exit(1);
		}
	}

}
