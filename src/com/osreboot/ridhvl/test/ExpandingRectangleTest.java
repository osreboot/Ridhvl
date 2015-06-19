package com.osreboot.ridhvl.test;

import org.lwjgl.input.Keyboard;

import com.osreboot.ridhvl.display.collection.HvlDisplayModeDefault;
import com.osreboot.ridhvl.loader.HvlTextureLoader;
import com.osreboot.ridhvl.painter.painter2d.HvlExpandingRectangle;
import com.osreboot.ridhvl.template.HvlTemplate2DBasic;

public class ExpandingRectangleTest extends HvlTemplate2DBasic {

	public ExpandingRectangleTest() {
		super(60, 1280, 720, "Unnamed", new HvlDisplayModeDefault());
	}
	
	static HvlTextureLoader textureLoader = new HvlTextureLoader(5);
	HvlExpandingRectangle testRect;

	@Override
	public void initialize() {
		textureLoader.loadResource("White");
		textureLoader.loadResource("ButtonDown");
		testRect = new HvlExpandingRectangle(textureLoader.getResource(1), 0.09375f, 0.90625f, 0.09375f, 0.90625f, 0, 0, 512, 256, 16, 16);
	}

	@Override
	public void update(float delta) {
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
