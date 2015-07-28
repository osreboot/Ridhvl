package com.osreboot.ridhvl.test;

import static com.osreboot.ridhvl.painter.painter2d.HvlPainter2D.*;

import com.osreboot.ridhvl.display.collection.HvlDisplayModeDefault;
import com.osreboot.ridhvl.input.HvlInputSeriesAction;
import com.osreboot.ridhvl.loader.HvlTextureLoader;
import com.osreboot.ridhvl.painter.painter2d.HvlPainter2D;
import com.osreboot.ridhvl.painter.painter2d.HvlTiledRect;
import com.osreboot.ridhvl.template.HvlTemplate2D;

public class ExpandingRectangleTest extends HvlTemplate2D {
	
	public ExpandingRectangleTest() {
		super(60, 1280, 720, "Ridhvl Expanding Rectangle Test", new HvlDisplayModeDefault());
	}
	
	static HvlTextureLoader textureLoader = new HvlTextureLoader();
	HvlTiledRect testRect;
	
	@Override
	public void initialize() {
		textureLoader.loadResource("Icon");
		testRect = new HvlTiledRect(textureLoader.getResource(0), 0.125f, 0.875f, 0.125f, 0.875f, 0, 0, 512, 256, 16, 16);
	}

	@Override
	public void update(float delta) {
		HvlPainter2D.hvlDrawQuad(0, 0, 1280, 720, getWhite512());
	
		testRect.setTotalWidth(testRect.getTotalWidth() + HvlInputSeriesAction.RIGHT.getCurrentOutput() * 256 * delta);
		testRect.setTotalWidth(testRect.getTotalWidth() - HvlInputSeriesAction.LEFT.getCurrentOutput() * 256 * delta);
		testRect.setTotalHeight(testRect.getTotalHeight() - HvlInputSeriesAction.DOWN.getCurrentOutput() * 256 * delta);
		testRect.setTotalHeight(testRect.getTotalHeight() + HvlInputSeriesAction.UP.getCurrentOutput() * 256 * delta);
		
		testRect.setX(640 - (testRect.getTotalWidth()/2));
		testRect.setY(360 - (testRect.getTotalHeight()/2));
		
		testRect.draw();
	}

}