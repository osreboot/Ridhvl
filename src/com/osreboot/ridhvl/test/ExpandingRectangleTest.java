package com.osreboot.ridhvl.test;

import org.lwjgl.input.Keyboard;

import com.osreboot.ridhvl.display.collection.HvlDisplayModeDefault;
import com.osreboot.ridhvl.loader.HvlTextureLoader;
import com.osreboot.ridhvl.painter.painter2d.HvlPainter2D;
import com.osreboot.ridhvl.painter.painter2d.HvlTiledRect;
import com.osreboot.ridhvl.template.HvlTemplate2D;

public class ExpandingRectangleTest extends HvlTemplate2D {

	public ExpandingRectangleTest() {
		super(60, 1280, 720, "Unnamed", new HvlDisplayModeDefault());
	}
	
	static HvlTextureLoader textureLoader = new HvlTextureLoader();
	HvlTiledRect testRect;
	
	@Override
	public void initialize() {
		textureLoader.loadResource("White");
		textureLoader.loadResource("ButtonDown");
		testRect = new HvlTiledRect(textureLoader.getResource(1), 0.125f, 0.875f, 0.125f, 0.875f, 0, 0, 512, 256, 16, 16);
	}

	@Override
	public void update(float delta) {
		HvlPainter2D.hvlDrawQuad(0, 0, 1280, 720, textureLoader.getResource(0));
		testRect.draw();
		
		if (Keyboard.isKeyDown(Keyboard.KEY_D))
			testRect.setTotalWidth(testRect.getTotalWidth() + 256.0f * delta);
		if (Keyboard.isKeyDown(Keyboard.KEY_A))
			testRect.setTotalWidth(testRect.getTotalWidth() - 256.0f * delta);
		if (Keyboard.isKeyDown(Keyboard.KEY_W))
			testRect.setTotalHeight(testRect.getTotalHeight() - 256.0f * delta);
		if (Keyboard.isKeyDown(Keyboard.KEY_S))
			testRect.setTotalHeight(testRect.getTotalHeight() + 256.0f * delta);
	}

}