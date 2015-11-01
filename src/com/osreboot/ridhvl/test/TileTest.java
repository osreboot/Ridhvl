package com.osreboot.ridhvl.test;

import org.newdawn.slick.opengl.Texture;

import com.osreboot.ridhvl.display.collection.HvlDisplayModeDefault;
import com.osreboot.ridhvl.input.HvlInputSeriesAction;
import com.osreboot.ridhvl.map.HvlEntity;
import com.osreboot.ridhvl.map.HvlMap;
import com.osreboot.ridhvl.painter.painter2d.HvlPainter2D;
import com.osreboot.ridhvl.template.HvlTemplateInteg2D;

public class TileTest extends HvlTemplateInteg2D {
	
	public class TestEntity extends HvlEntity {

		private Texture t;
		
		public TestEntity(float xArg, float yArg, HvlMap mapArg) {
			super(xArg, yArg, mapArg);
			t = HvlTemplateInteg2D.getTexture(1);
		}

		@Override
		public void update(float delta) {
			setRelX(getRelX() + (HvlInputSeriesAction.HORIZONTAL.getCurrentOutput() * delta * getMap().getTileWidth() * 2));
			setRelY(getRelY() + (HvlInputSeriesAction.VERTICAL.getCurrentOutput() * delta * getMap().getTileHeight() * 2));
		}

		@Override
		public void draw(float delta) {
			HvlPainter2D.hvlDrawQuadc(getX(), getY(), getMap().getTileWidth(), getMap().getTileHeight(), t);
		}
	}
	
	public HvlMap map;
	
	public TileTest(){
		super(60, 1280, 720, "Ridhvl Tilemap Test", new HvlDisplayModeDefault());
	}

	@Override
	public void initialize() {
		getTextureLoader().loadResource("Tilemap");
		getTextureLoader().loadResource("Icon");
		map = new HvlMap(0, 0, 64, 64, 8, 8, 2, 32, 16, getTexture(0));
		map.fill(0, 0);
		map.fill(0, 0, 0, 31, 0, 9);
		map.setTile(0, 1, 2, 3);
		map.setTile(0, 31, 15, 3);
		map.addEntity(new TestEntity(0, 0, map));
	}

	@Override
	public void update(float delta) {
		map.update(delta);
		map.draw(delta);
	}
}
