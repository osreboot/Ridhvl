package com.osreboot.ridhvl.test;

import static com.osreboot.ridhvl.painter.painter2d.HvlPainter2D.*;

import org.newdawn.slick.Color;

import com.osreboot.ridhvl.HvlFontUtil;
import com.osreboot.ridhvl.display.collection.HvlDisplayModeDefault;
import com.osreboot.ridhvl.painter.HvlCursor;
import com.osreboot.ridhvl.painter.painter2d.HvlFontPainter2D;
import com.osreboot.ridhvl.template.HvlTemplateInteg2DBasic;

public class TemplateInteg2DBasicTest extends HvlTemplateInteg2DBasic{
	
	public TemplateInteg2DBasicTest(){
		super(60, 1280, 720, "Unnamed", 5, 5, new HvlDisplayModeDefault());
	}

	static HvlFontPainter2D fontPainter;
	float gradient = 0;
	
	@Override
	public void initialize(){
		getTextureLoader().loadResource("White");
		getTextureLoader().loadResource("Font");
		getTextureLoader().loadResource("Cursor");
		
		fontPainter = new HvlFontPainter2D(getTextureLoader().getResource(1), HvlFontUtil.DEFAULT, 2048, 2048, 112, 144, 18);
		
		HvlCursor.setNativeHidden(true);
		HvlCursor.setTexture(getTextureLoader().getResource(2));
		HvlCursor.setOffset(-6);
		HvlCursor.applyScale(0.5f);
	}

	@Override
	public void update(float delta){
		gradient = gradient < 1280 ? gradient + (delta*1000) : 0;
		
		for(int i = 0; i < 360; i++){
			hvlDrawQuad(0, i*2, gradient - (i*2), 2, getTextureLoader().getResource(0), new Color(1f, ((float)i - 180)/180, (float)i/360));
		}
		
		hvlRotate(300f, 300f, gradient/1280f*360f);
		hvlDrawQuad(250, 250, 100, 100, getTextureLoader().getResource(0));
		hvlResetRotation();
		
		fontPainter.hvlDrawWord("test of the most basic template!", 10, 10, 1260, 100, new Color(gradient/1280f, gradient/1280f, gradient/1280f));
		fontPainter.hvlDrawWord("and rotation! yay?", 10, 100, 0.5f, new Color(1 - (gradient/1280f), 1 - (gradient/1280f), 1 - (gradient/1280f)));
	}

}
