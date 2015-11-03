package com.osreboot.ridhvl.test;

import org.newdawn.slick.opengl.Texture;

import com.osreboot.ridhvl.HvlCoord;
import com.osreboot.ridhvl.input.HvlInputSeriesAction;
import com.osreboot.ridhvl.map.HvlEntity;
import com.osreboot.ridhvl.map.HvlMap;
import com.osreboot.ridhvl.painter.painter2d.HvlPainter2D;
import com.osreboot.ridhvl.template.HvlTemplateInteg2D;

public class TestTilemapEntity extends HvlEntity {

	private Texture t;

	public TestTilemapEntity(float xArg, float yArg, HvlMap mapArg) {
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
		
		if (getMap().raytrace(entPos, entPos.addNew(motionInput.x, 0)).isEmpty()) motionActual.x = motionInput.x;
		if (getMap().raytrace(entPos, entPos.addNew(0, motionInput.y)).isEmpty()) motionActual.y = motionInput.y;
		
		motionActual.normalize().fixNaN().mult(delta).mult(speed);
		
		setRelX(getRelX() + motionActual.x);
		setRelY(getRelY() + motionActual.y);
	}

	@Override
	public void draw(float delta) {
		HvlPainter2D.hvlDrawQuadc(getX(), getY(), getMap().getTileWidth(), getMap().getTileHeight(), t);
	}
}