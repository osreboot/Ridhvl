package com.osreboot.ridhvl.test;

import java.util.LinkedList;
import java.util.List;

import org.newdawn.slick.opengl.Texture;

import com.osreboot.ridhvl.HvlCoord;
import com.osreboot.ridhvl.input.HvlInputSeriesAction;
import com.osreboot.ridhvl.map.HvlEntity;
import com.osreboot.ridhvl.map.HvlMap;
import com.osreboot.ridhvl.painter.painter2d.HvlPainter2D;
import com.osreboot.ridhvl.template.HvlTemplateInteg2D;

public class TestTilemapEntity extends HvlEntity {

	private Texture t;
	
	private float someValue;
	private String magicalString;

	public TestTilemapEntity(float xArg, float yArg, HvlMap mapArg) {
		super(xArg, yArg, mapArg);
		TileTest.ent = this;
		t = HvlTemplateInteg2D.getTexture(1);
		someValue = 64f;
	}
	
	public TestTilemapEntity(float xArg, float yArg, HvlMap mapArg, float sv) {
		super(xArg, yArg, mapArg);
		TileTest.ent = this;
		t = HvlTemplateInteg2D.getTexture(1);
		someValue = sv;
	}
	
	public TestTilemapEntity(float xArg, float yArg, HvlMap mapArg, float sv, String magicalString) {
		super(xArg, yArg, mapArg);
		TileTest.ent = this;
		t = HvlTemplateInteg2D.getTexture(1);
		someValue = sv;
		this.magicalString = magicalString;
	}

	@Override
	public void update(float delta) {
		System.out.println(someValue);
		System.out.println(magicalString);
		float speed = 256.0f;
		float xMotion = HvlInputSeriesAction.HORIZONTAL.getCurrentOutput();
		float yMotion = HvlInputSeriesAction.VERTICAL.getCurrentOutput();
		
		HvlCoord entPos = new HvlCoord(getX(), getY());
		
		HvlCoord motionInput = new HvlCoord(xMotion, yMotion).normalize().fixNaN().mult(delta).mult(speed);
		
		HvlCoord motionActual = new HvlCoord(0, 0);
		
		if (getMap().raytrace(entPos, entPos.addNew(motionInput.x, 0)).isEmpty()) motionActual.x = motionInput.x;
		if (getMap().raytrace(entPos, entPos.addNew(0, motionInput.y)).isEmpty()) motionActual.y = motionInput.y;
		
		motionActual.normalize().fixNaN().mult(delta).mult(speed);
		
		// If (somehow) this still would put us through a wall, just make us not move (to be safe).
		if (!getMap().raytrace(entPos, entPos.addNew(motionActual)).isEmpty()) motionActual.mult(0);
		
		setRelX(getRelX() + motionActual.x);
		setRelY(getRelY() + motionActual.y);
	}

	@Override
	public void draw(float delta) {
		HvlPainter2D.hvlDrawQuadc(getX(), getY(), getMap().getTileDrawWidth(), getMap().getTileDrawHeight(), t);
	}

	@Override
	public List<Object> getSaveParameters() {
		List<Object> tr = new LinkedList<>();
		tr.add(someValue);
		return tr;
	}
}