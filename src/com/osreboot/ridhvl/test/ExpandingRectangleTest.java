package com.osreboot.ridhvl.test;

import static com.osreboot.ridhvl.painter.painter2d.HvlPainter2D.getWhite512;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;

import com.osreboot.ridhvl.display.collection.HvlDisplayModeDefault;
import com.osreboot.ridhvl.input.HvlController;
import com.osreboot.ridhvl.input.HvlInputSeriesAction;
import com.osreboot.ridhvl.input.collection.HvlCPG_Gamepad;
import com.osreboot.ridhvl.loader.HvlTextureLoader;
import com.osreboot.ridhvl.painter.painter2d.HvlPainter2D;
import com.osreboot.ridhvl.template.HvlTemplate2D;

public class ExpandingRectangleTest extends HvlTemplate2D {
	
	float width, height;
	
	public ExpandingRectangleTest() {
		super(60, 1280, 720, "Ridhvl Expanding Rectangle Test", new HvlDisplayModeDefault());
	}

	static HvlTextureLoader textureLoader = new HvlTextureLoader();
	
	Color color;

	HvlCPG_Gamepad profile;
	
	@Override
	public void initialize() {
		profile = new HvlCPG_Gamepad();
		textureLoader.loadResource("Icon");
		color = Color.white;
		
		width = 512;
		height = 512;
	}
	
	public static float counter = 0;

	@Override
	public void update(float delta) {
		counter += delta;
		if(counter > 5){
			counter = 0;
			HvlController.rescanControllers();
		}
		
		HvlPainter2D.hvlDrawQuad(0, 0, 1280, 720, getWhite512(), color);

//		width += profile.getValue(HvlCPG_Gamepad.TRIGGER_LEFT) * 256 * delta;
//		height += profile.getValue(HvlCPG_Gamepad.TRIGGER_RIGHT) * 256 * delta;
		width += HvlInputSeriesAction.HORIZONTAL.getCurrentOutput() * 256 * delta;
		height += HvlInputSeriesAction.VERTICAL.getCurrentOutput() * 256 * delta;
		
		HvlPainter2D.hvlDrawTiledQuadc(Display.getWidth() / 2, Display.getHeight() / 2, width, height, 32, 32, 32, 32, "0.125,0.125", textureLoader.getResource(0));
	}

}