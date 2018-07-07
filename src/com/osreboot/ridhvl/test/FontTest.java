package com.osreboot.ridhvl.test;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;

import com.osreboot.ridhvl.display.collection.HvlDisplayModeDefault;
import com.osreboot.ridhvl.painter.painter2d.HvlFontPainter2D;
import com.osreboot.ridhvl.template.HvlTemplateInteg2D;

public class FontTest extends HvlTemplateInteg2D{
	
	public static void main(String[] args) {
		new FontTest();
	}
	
	public FontTest() {
		super(60, 3000, 720, "Ridhvl - Font Test", new HvlDisplayModeDefault());
	}

	HvlFontPainter2D font;
	
	@Override
	public void initialize() {
		getTextureLoader().loadResource("INOF");
		font = new HvlFontPainter2D(getTexture(0), HvlFontPainter2D.Preset.FP_INOFFICIAL);
		font.setCharSpacing(192);
	}

	@Override
	public void update(float delta) {
		font.drawWordc("TOOLKIT", Display.getWidth()/2, Display.getHeight()/2, Color.white, 1.0f);
	}

}
