package com.osreboot.ridhvl.test;

import static com.osreboot.ridhvl.painter.painter2d.HvlPainter2D.*;

import org.newdawn.slick.Color;

import com.osreboot.ridhvl.HvlFontUtil.HvlFontLayout;
import com.osreboot.ridhvl.painter.painter2d.HvlFontPainter2D;
import com.osreboot.ridhvl.template.HvlTemplateInteg2DBasic;

public class TemplateInteg2DBasicTest extends HvlTemplateInteg2DBasic{

	public TemplateInteg2DBasicTest(){
		super(60, 1280, 720, "Unnamed", 5);
	}

	static HvlFontPainter2D fontPainter;
	long gradient = 0;
	
	@Override
	public void initialize(){
		getTextureLoader().loadResource("White");
		getTextureLoader().loadResource("Font");
		
		fontPainter = new HvlFontPainter2D(getTextureLoader().getResource(1), HvlFontLayout.SIMPLISTIC, 2048, 2048, 40, 50, 51);
	}

	@Override
	public void update(long delta){
		gradient = gradient < 1280 ? gradient + delta : 0;
		
		for(int i = 0; i < 360; i++){
			hvlDrawQuad(0, i*2, gradient - (i*2), 2, getTextureLoader().getResource(0), new Color(1f, ((float)i - 180)/180, (float)i/360));
		}
		
		hvlRotate(300f, 300f, gradient/1280f*360f);
		hvlDrawQuad(250, 250, 100, 100, getTextureLoader().getResource(0));
		hvlResetRotation();
		
		fontPainter.hvlDrawWord("test of the most basic template!", 10, 10, new Color(gradient/1280f, gradient/1280f, gradient/1280f, 1f));
		fontPainter.hvlDrawWord("and rotation! yay?", 10, 100, new Color(1 - (gradient/1280f), 1 - (gradient/1280f), 1 - (gradient/1280f), 1f));

	}

}
