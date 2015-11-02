package com.osreboot.ridhvl.test;

import java.util.List;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

import com.osreboot.ridhvl.HvlCoord;
import com.osreboot.ridhvl.display.collection.HvlDisplayModeDefault;
import com.osreboot.ridhvl.input.HvlInputSeriesAction;
import com.osreboot.ridhvl.map.HvlEntity;
import com.osreboot.ridhvl.map.HvlMap;
import com.osreboot.ridhvl.map.collection.HvlSimpleCollisionProfiles;
import com.osreboot.ridhvl.painter.HvlCamera;
import com.osreboot.ridhvl.painter.HvlCamera.HvlCameraAlignment;
import com.osreboot.ridhvl.painter.HvlCursor;
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
			float speed = 256.0f;
			float xMotion = HvlInputSeriesAction.HORIZONTAL.getCurrentOutput();
			float yMotion = HvlInputSeriesAction.VERTICAL.getCurrentOutput();
			
			HvlCoord entPos = new HvlCoord(getX(), getY());
			
			HvlCoord motionInput = new HvlCoord(xMotion, yMotion).normalize().fixNaN().mult(delta).mult(speed);
			
			HvlCoord motionActual = new HvlCoord(0, 0);
			
			if (map.raytrace(entPos, entPos.addNew(motionInput.x, 0)).isEmpty()) motionActual.x = motionInput.x;
			if (map.raytrace(entPos, entPos.addNew(0, motionInput.y)).isEmpty()) motionActual.y = motionInput.y;
			
			motionActual.normalize().fixNaN().mult(delta).mult(speed);
			
			setRelX(getRelX() + motionActual.x);
			setRelY(getRelY() + motionActual.y);
		}

		@Override
		public void draw(float delta) {
			HvlPainter2D.hvlDrawQuadc(getX(), getY(), getMap().getTileWidth(), getMap().getTileHeight(), t);
		}
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
		map.setCollisionDebugDraw(true);
		map.mapTileToCollision(9, new HvlSimpleCollisionProfiles.Vertical(4));
		map.mapTileToCollision(11, new HvlSimpleCollisionProfiles.Vertical(4));
		map.mapTileToCollision(2, new HvlSimpleCollisionProfiles.Horizontal(4));
		map.mapTileToCollision(18, new HvlSimpleCollisionProfiles.Horizontal(4));
		map.mapTileToCollision(10, new HvlSimpleCollisionProfiles.Square(0));
		map.mapTileToCollision(1, new HvlSimpleCollisionProfiles.CustomMiddle(false, true, false, true, 2));
		map.mapTileToCollision(3, new HvlSimpleCollisionProfiles.CustomMiddle(true, false, false, true, 2));
		map.mapTileToCollision(17, new HvlSimpleCollisionProfiles.CustomMiddle(false, true, true, false, 2));
		map.mapTileToCollision(19, new HvlSimpleCollisionProfiles.CustomMiddle(true, false, true, false, 2));

		map.fill(0, 0);
		map.fill(0, 0, 6, 31, 6, 2);
		map.setTile(0, 2, 2, 10);
		map.setTile(0, 4, 2, 3);
		map.setTile(0, 3, 2, 1);
		map.setTile(0, 3, 3, 17);
		map.setTile(0, 4, 3, 19);

		map.addEntity(new TestEntity(0, 0, map));

		HvlCamera.setAlignment(HvlCameraAlignment.CENTER);
	}

	@Override
	public void update(float delta) {
		map.update(delta);
		HvlCamera.setPosition(ent.getX(), ent.getY());
		map.draw(delta);
		List<HvlCoord> colls = map.raytrace(new HvlCoord(ent.getX(), ent.getY()),
				new HvlCoord(HvlCursor.getCursorX() + HvlCamera.getX() - (Display.getWidth() / 2),
						HvlCursor.getCursorY() + HvlCamera.getY() - (Display.getHeight() / 2)));

		if (colls.isEmpty())
			return;
		HvlCoord coll = colls.get(0);
		
		HvlPainter2D.hvlDrawLine(coll.x - 8, coll.y - 8, coll.x + 8, coll.y + 8, Color.green, 4.0f);
		HvlPainter2D.hvlDrawLine(coll.x - 8, coll.y + 8, coll.x + 8, coll.y - 8, Color.green, 4.0f);
	}
}
