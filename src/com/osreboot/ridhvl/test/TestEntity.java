package com.osreboot.ridhvl.test;

import org.newdawn.slick.Color;

import com.osreboot.ridhvl.HvlTextureUtil;
import com.osreboot.ridhvl.painter.painter2d.HvlPainter2D;
import com.osreboot.ridhvl.tile.HvlLayeredTileMap;
import com.osreboot.ridhvl.tile.entity.HvlEntity;

@Deprecated
public class TestEntity extends HvlEntity {

	public TestEntity(String inArg, float xArg, float yArg, HvlLayeredTileMap parentArg) {
		super(inArg, xArg, yArg, parentArg);
	}
	
	// Example of handling loading yourself (pass null in for inArg)
	public TestEntity(float xArg, float yArg, HvlLayeredTileMap parentArg) {
		super(null, xArg, yArg, parentArg);
	}

	@Override
	public void update(float delta) {
		
	}

	@Override
	public void draw(float delta) {
		HvlPainter2D.hvlDrawQuad(getWorldX(), getWorldY(), getParent().getTileWidth(), getParent().getTileWidth(), HvlTextureUtil.getColoredRect(64, 64,
				Color.white), Color.red);
	}

	@Override
	public String save()
	{
		return "";
	}
	
	@Override
	protected void load(String inArg)
	{
		
	}
}
