package com.osreboot.ridhvl.test;

import java.util.List;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;

import com.osreboot.ridhvl.HvlCoord;
import com.osreboot.ridhvl.display.collection.HvlDisplayModeDefault;
import com.osreboot.ridhvl.map.HvlMap;
import com.osreboot.ridhvl.map.collection.HvlSimpleCollisionProfiles;
import com.osreboot.ridhvl.painter.HvlCamera;
import com.osreboot.ridhvl.painter.HvlCamera.HvlCameraAlignment;
import com.osreboot.ridhvl.painter.HvlCursor;
import com.osreboot.ridhvl.painter.painter2d.HvlPainter2D;
import com.osreboot.ridhvl.template.HvlTemplateInteg2D;

public class TileTest extends HvlTemplateInteg2D {

	public static TestTilemapEntity ent;
	
	public HvlMap map;
	
	public TileTest() {
		super(60, 1280, 720, "Ridhvl Tilemap Test", new HvlDisplayModeDefault());
	}
	
	public static void main(String[] args) {
		new TileTest();
	}
	
	@Override
	public void initialize() {
		getTextureLoader().loadResource("Tilemap");
		getTextureLoader().loadResource("Icon");
		getTextureLoader().loadResource("Font");

		map = HvlMap.load("TestLoadMap", 0, 0, 64, 64, getTexture(0), 8, 8);
		map.setCollisionDebugDraw(true);
		map.setOverdrawAmount(1.0f);
		map.mapTileToCollision(9, new HvlSimpleCollisionProfiles.Vertical(4));
		map.mapTileToCollision(11, new HvlSimpleCollisionProfiles.Vertical(4));
		map.mapTileToCollision(2, new HvlSimpleCollisionProfiles.Horizontal(4));
		map.mapTileToCollision(18, new HvlSimpleCollisionProfiles.Horizontal(4));
		map.mapTileToCollision(10, new HvlSimpleCollisionProfiles.Square(0));
		map.mapTileToCollision(1, new HvlSimpleCollisionProfiles.CustomMiddle(false, true, false, true, false, false, false, false, 0));
		map.mapTileToCollision(3, new HvlSimpleCollisionProfiles.CustomMiddle(true, false, false, true, false, false, false, false, 0));
		map.mapTileToCollision(17, new HvlSimpleCollisionProfiles.CustomMiddle(false, true, true, false, false, false, false, false, 0));
		map.mapTileToCollision(19, new HvlSimpleCollisionProfiles.CustomMiddle(true, false, true, false, false, false, false, false, 0));

		map.save("TestSaveMap");

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
