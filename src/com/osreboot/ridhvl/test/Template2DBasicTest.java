package com.osreboot.ridhvl.test;

import static com.osreboot.ridhvl.painter.painter2d.HvlPainter2D.hvlDrawQuad;
import static com.osreboot.ridhvl.painter.painter2d.HvlPainter2D.hvlResetRotation;
import static com.osreboot.ridhvl.painter.painter2d.HvlPainter2D.hvlRotate;

import org.newdawn.slick.Color;

import com.osreboot.ridhvl.HvlFontUtil;
import com.osreboot.ridhvl.display.collection.HvlDisplayModeDefault;
import com.osreboot.ridhvl.loader.HvlTextureLoader;
import com.osreboot.ridhvl.painter.painter2d.HvlFontPainter2D;
import com.osreboot.ridhvl.template.HvlTemplate2D;

public class Template2DBasicTest extends HvlTemplate2D{
	
	public Template2DBasicTest(){
		super(60, 1280, 720, "Ridhvl Template2D Test", new HvlDisplayModeDefault());
	}

	static HvlTextureLoader textureLoader = new HvlTextureLoader();
	static HvlFontPainter2D fontPainter;

	@Override
	public void initialize(){
		textureLoader.loadResource("Font");

		fontPainter = new HvlFontPainter2D(textureLoader.getResource(0), HvlFontUtil.DEFAULT, 192, 256);
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

		fontPainter.drawWord("test of the most basic template!", 10, 10, 1260, 100, new Color(gradient/1280f, gradient/1280f, gradient/1280f));
		fontPainter.drawWord("and rotation! yay?", 10, 100, 0.5f, new Color(1 - (gradient/1280f), 1 - (gradient/1280f), 1 - (gradient/1280f)));
	}

}
