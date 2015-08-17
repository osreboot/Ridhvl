package com.osreboot.ridhvl.test;

import org.newdawn.slick.Color;

import com.osreboot.ridhvl.HvlMath;
import com.osreboot.ridhvl.HvlTextureUtil;
import com.osreboot.ridhvl.painter.painter2d.HvlPainter2D;
import com.osreboot.ridhvl.tile.HvlLayeredTileMap;
import com.osreboot.ridhvl.tile.entity.HvlEntity;

public class TestEntity extends HvlEntity {

	public TestEntity(float xArg, float yArg, HvlLayeredTileMap parentArg) {
		super(xArg, yArg, parentArg);
	}

	@Override
	public void update(float delta) {
		System.out.println("ENTITY!");
	}

	@Override
	public void draw(float delta) {
		HvlPainter2D.hvlDrawQuad(getWorldX(), getWorldY(), getParent().getTileWidth(), getParent().getTileWidth(), HvlTextureUtil.getColoredRect(64, 64,
				Color.white), Color.red);
	}

}
