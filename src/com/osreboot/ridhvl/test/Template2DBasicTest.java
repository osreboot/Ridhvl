package com.osreboot.ridhvl.test;

import static com.osreboot.ridhvl.painter.painter2d.HvlPainter2D.hvlDrawQuad;
import static com.osreboot.ridhvl.painter.painter2d.HvlPainter2D.hvlResetRotation;
import static com.osreboot.ridhvl.painter.painter2d.HvlPainter2D.hvlRotate;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;

import com.osreboot.ridhvl.HvlCoord;
import com.osreboot.ridhvl.display.collection.HvlDisplayModeDefault;
import com.osreboot.ridhvl.loader.HvlTextureLoader;
import com.osreboot.ridhvl.painter.painter2d.HvlFontPainter2D;
import com.osreboot.ridhvl.template.HvlTemplate2D;

public class Template2DBasicTest extends HvlTemplate2D{
	
	public Template2DBasicTest(){
		super(60, 1280, 720, "Ridhvl Template2D Test", new HvlDisplayModeDefault());
	}

	static HvlTextureLoader textureLoader = new HvlTextureLoader();
	static HvlFontPainter2D font;

	@Override
	public void initialize(){
		textureLoader.loadResource("Font");

//		fontPainter = new HvlFontPainter2D(textureLoader.getResource(0), HvlFontUtil.DEFAULT, 192, 256);
		font = new HvlFontPainter2D(textureLoader.getResource(0), "abcdefghijklmnopqrstuvwxyz0123456789!?.()[]".toCharArray(), 192, 256, 0.25f);
		font.setCharWidth('a', 0f, 1f);
		font.setCharWidth('b', 0.2f, 0.8f);
		font.setCharWidth('c', 0.1f, 0.8f);
		font.setCharWidth('d', 0.2f, 0.8f);
		font.setCharWidth('e', 0.2f, 0.8f);
		font.setCharWidth('f', 0.2f, 0.9f);
		font.setCharWidth('g', 0.2f, 0.8f);
		font.setCharWidth('h', 0.2f, 0.8f);
		font.setCharWidth('i', 0.0f, 0.8f);
		font.setCharWidth('j', 0.0f, 0.8f);
		font.setCharWidth('k', 0.1f, 0.8f);
		font.setCharWidth('l', 0.2f, 0.9f);
		font.setCharWidth('m', 0f, 1f);
		font.setCharWidth('n', 0f, 0.9f);
		font.setCharWidth('o', 0f, 0.9f);
		font.setCharWidth('p', 0.25f, 0.75f);
		font.setCharWidth('q', 0f, 1f);
		font.setCharWidth('r', 0.2f, 0.75f);
		font.setCharWidth('s', 0.1f, 0.9f);
		font.setCharWidth('t', 0.1f, 0.9f);
		font.setCharWidth('u', 0f, 0.9f);
		font.setCharWidth('v', 0f, 0.9f);
		font.setCharWidth('w', 0f, 0.8f);
		font.setCharWidth('x', 0.1f, 0.8f);
		font.setCharWidth('y', 0f, 0.9f);
		font.setCharWidth('z', 0f, 1f);
		font.setCharWidth('0', 0f, 0.9f);
		font.setCharWidth('1', 0.2f, 0.8f);
		font.setCharWidth('2', 0.2f, 0.8f);
		font.setCharWidth('3', 0.2f, 0.75f);
		font.setCharWidth('4', 0.1f, 0.8f);
		font.setCharWidth('5', 0.2f, 0.8f);
		font.setCharWidth('6', 0.2f, 0.8f);
		font.setCharWidth('7', 0.2f, 0.8f);
		font.setCharWidth('8', 0.1f, 0.8f);
		font.setCharWidth('9', 0.2f, 0.8f);
		font.setCharWidth('!', 0.3f, 0.7f);
		font.setCharWidth('?', 0.2f, 0.8f);
		font.setCharWidth('.', 0.0f, 0.25f);
		font.setCharWidth('(', 0.0f, 0.33f);
		font.setCharWidth(')', 0.0f, 0.33f);
		font.setCharWidth('[', 0.0f, 0.5f);
		font.setCharWidth(']', 0.0f, 0.5f);
		font.setSpaceSize(128);
		font.setCharSpacing(32f);
		font.setRowSpacing(0.5f);
	}

	@Override
	public void update(float delta){
		float gradient = getNewestInstance().getTimer().getTotalTime()*1000 % 1280f;

		for(int i = 0; i < 360; i++){
			hvlDrawQuad(0, i*2, gradient - (i*2), 2, new Color(1f, ((float)i - 180)/180, (float)i/360));
		}

		hvlRotate(300f, 300f, gradient/1280f*360f);
		hvlDrawQuad(250, 250, 100, 100, Color.white);
		hvlResetRotation();
		
		hvlDrawQuad(new HvlCoord(100, 100), new HvlCoord(200, 100), new HvlCoord(250, 300), new HvlCoord(150, 300), Color.yellow);

		String text = "hello hi\nhows it going?\nits going good.\nniiiice!";
		font.drawWordc(text, Display.getWidth() / 2, Display.getHeight() / 2, Color.white, 1f, 1f, 0.0f);
//		fontPainter.drawWord("test of the most basic template!", 10, 10, 1260, 100, new Color(gradient/1280f, gradient/1280f, gradient/1280f));
//		fontPainter.drawWord("and rotation! yay?", 10, 100, 0.5f, new Color(1 - (gradient/1280f), 1 - (gradient/1280f), 1 - (gradient/1280f)));
	}

}
