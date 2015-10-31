package com.osreboot.ridhvl.test;

import com.osreboot.ridhvl.display.collection.HvlDisplayModeDefault;
import com.osreboot.ridhvl.map.HvlMap;
import com.osreboot.ridhvl.template.HvlTemplateInteg2D;

public class TileTest extends HvlTemplateInteg2D {

	public HvlMap map;
	
	public TileTest(){
		super(60, 1280, 720, "Ridhvl Tilemap Test", new HvlDisplayModeDefault());
	}

	@Override
	public void initialize() {
		getTextureLoader().loadResource("Tilemap");
		map = new HvlMap(0, 0, 64, 64, 8, 8, 2, 32, 16, getTexture(0));
		map.setTile(0, 1, 2, 3);
	}

	@Override
	public void update(float delta) {
		map.update(delta);
		map.draw(delta);
	}
}
