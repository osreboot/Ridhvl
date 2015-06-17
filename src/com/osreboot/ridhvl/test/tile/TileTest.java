package com.osreboot.ridhvl.test.tile;

import com.osreboot.ridhvl.display.collection.HvlDisplayModeDefault;
import com.osreboot.ridhvl.loader.HvlTextureLoader;
import com.osreboot.ridhvl.template.HvlTemplate2DBasic;
import com.osreboot.ridhvl.tile.TileMap;
import com.osreboot.ridhvl.tile.collection.AnimatedTile;
import com.osreboot.ridhvl.tile.collection.SimpleTile;

public class TileTest extends HvlTemplate2DBasic{

	public TileTest(){
		super(60, 1280, 720, "Unnamed", new HvlDisplayModeDefault());
	}

	static HvlTextureLoader textureLoader = new HvlTextureLoader(5);
	
	private TileMap tilemap;

	public static void main(String[] args)
	{
		new TileTest().start();
	}
	
	@Override
	public void initialize(){		
		textureLoader.loadResource("White");
		textureLoader.loadResource("TestTilesheet");
		
		tilemap = new TileMap(textureLoader.getResource(1), 8, 8, 8, 8, 0, 0, 64, 64);
		tilemap.fill(new SimpleTile(63));
		tilemap.setTile(0, 0, new SimpleTile(32));
		tilemap.setTile(1, 1, new SimpleTile(0));
		tilemap.setTile(1, 2, new SimpleTile(35));
		tilemap.setTile(2, 1, new AnimatedTile(1.0f, 44, 45));
	}

	@Override
	public void update(float delta){
		tilemap.draw(delta);
	}

}
