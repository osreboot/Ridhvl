package com.osreboot.ridhvl.test;

import static com.osreboot.ridhvl.painter.painter2d.HvlPainter2D.*;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.Color;

import com.osreboot.ridhvl.HvlFontUtil.HvlFontLayout;
import com.osreboot.ridhvl.HvlTimer;
import com.osreboot.ridhvl.loader.HvlTextureLoader;
import com.osreboot.ridhvl.painter.painter2d.HvlFontPainter2D;
import com.osreboot.ridhvl.painter.painter2d.HvlPainter2D.HvlPainter2DTemplate;


public class Painter2DTest extends HvlTimer{

	public static void main(String args[]){
		run();
	}
	
	public static void run(){
		try{
			Display.setDisplayMode(new DisplayMode(1280, 720));
			//Display.setIcon(new ByteBuffer[]{new ImageIOImageData().imageToByteBuffer(ImageIO.read(new FileInputStream("res/Icon32.png")), false, false, null)}); //TODO
			Display.setTitle("Unnamed");
			Display.create();
			
			hvlGL11Init(HvlPainter2DTemplate.DEFAULT);
			hvlGL11Ortho(1280, 720);

			textureLoader.loadResource("White");
			textureLoader.loadResource("Font");
			
			fontPainter = new HvlFontPainter2D(textureLoader.getResource(1), HvlFontLayout.SIMPLISTIC, 2048, 2048, 40, 50, 51);
			
		}catch(Exception e){e.printStackTrace();}
		
		new Painter2DTest();
	}
	
	static HvlTextureLoader textureLoader = new HvlTextureLoader(5);
	static HvlFontPainter2D fontPainter;
	long gradient = 0;
	
	public Painter2DTest(){}

	@Override
	public void update(long delta) {
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

		gradient = gradient < 1280 ? gradient + delta : 0;
		
		for(int i = 0; i < 360; i++){
			hvlDrawQuad(0, i*2, gradient - (i*2), 2, textureLoader.getResource(0), new Color(1f, ((float)i - 180)/180, (float)i/360));
		}
		
		fontPainter.hvlDrawWord("test painter and font renderer!", 10, 10, new Color(gradient/1280f, gradient/1280f, gradient/1280f, 1f));

		Display.update();
		Display.sync(60);
		
		super.setRunning(!Display.isCloseRequested());
		if(!super.isRunning()){
			Display.destroy();
			System.exit(1);
		}
	}

}
