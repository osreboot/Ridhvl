package com.osreboot.ridhvl.test;

import org.newdawn.slick.opengl.Texture;

import com.osreboot.ridhvl.display.collection.HvlDisplayModeDefault;
import com.osreboot.ridhvl.input.HvlInputSeriesAction;
import com.osreboot.ridhvl.map.HvlEntity;
import com.osreboot.ridhvl.map.HvlMap;
import com.osreboot.ridhvl.map.collection.HvlMiddleCollisionProfile;
import com.osreboot.ridhvl.painter.HvlCamera;
import com.osreboot.ridhvl.painter.HvlCamera.HvlCameraAlignment;
import com.osreboot.ridhvl.painter.painter2d.HvlPainter2D;
import com.osreboot.ridhvl.template.HvlTemplateInteg2D;

public class TileTest extends HvlTemplateInteg2D {

	public static TestEntity ent;
	
	public class TestEntity extends HvlEntity {

		private Texture t;

		public TestEntity(float xArg, float yArg, HvlMap mapArg) {
			super(xArg, yArg, mapArg);
			TileTest.ent = this;
			t = HvlTemplateInteg2D.getTexture(1);
		}

		@Override
		public void update(float delta) {
			setRelX(getRelX()
					+ (HvlInputSeriesAction.HORIZONTAL.getCurrentOutput() * delta * getMap().getTileWidth() * 2));
			setRelY(getRelY()
					+ (HvlInputSeriesAction.VERTICAL.getCurrentOutput() * delta * getMap().getTileHeight() * 2));
		}

		@Override
		public void draw(float delta) {
			HvlPainter2D.hvlDrawQuadc(getX(), getY(), getMap().getTileWidth(), getMap().getTileHeight(), t);
		}
	}

	public static void main(String[] args) {
		new TileTest();
	}
	
	public HvlMap map;

	public TileTest() {
		super(60, 1280, 720, "Ridhvl Tilemap Test", new HvlDisplayModeDefault());
	}

	@Override
	public void initialize() {
		getTextureLoader().loadResource("Tilemap");
		getTextureLoader().loadResource("Icon");
		map = new HvlMap(0, 0, 64, 64, 8, 8, 2, 32, 16, getTexture(0));
		map.fill(0, 0);
		map.fill(0, 0, 6, 31, 6, 2);
		map.setTile(0, 2, 2, 10);
		map.setTile(0, 4, 2, 3);
		map.setTile(0, 3, 2, 1);
		map.setTile(0, 3, 3, 17);
		map.setTile(0, 4, 3, 19);
		map.setCollisionDebugDraw(true);
		map.mapTileToCollision(9, new HvlMiddleCollisionProfile.Vertical());
		map.mapTileToCollision(11, new HvlMiddleCollisionProfile.Vertical());
		map.mapTileToCollision(2, new HvlMiddleCollisionProfile.Horizontal());
		map.mapTileToCollision(18, new HvlMiddleCollisionProfile.Horizontal());
		map.mapTileToCollision(10, new HvlMiddleCollisionProfile.Square());
		map.mapTileToCollision(1, new HvlMiddleCollisionProfile.CustomMiddle(false, true, false, true));
		map.mapTileToCollision(3, new HvlMiddleCollisionProfile.CustomMiddle(true, false, false, true));
		map.mapTileToCollision(17, new HvlMiddleCollisionProfile.CustomMiddle(false, true, true, false));
		map.mapTileToCollision(19, new HvlMiddleCollisionProfile.CustomMiddle(true, false, true, false));
		map.addEntity(new TestEntity(0, 0, map));
		
		HvlCamera.setAlignment(HvlCameraAlignment.CENTER);
	}

	@Override
	public void update(float delta) {
		map.update(delta);
		HvlCamera.setPosition(ent.getX(), ent.getY());
		map.draw(delta);
	}
}
