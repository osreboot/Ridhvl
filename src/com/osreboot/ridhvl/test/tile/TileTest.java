package com.osreboot.ridhvl.test.tile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.osreboot.ridhvl.display.collection.HvlDisplayModeDefault;
import com.osreboot.ridhvl.loader.HvlTextureLoader;
import com.osreboot.ridhvl.template.HvlTemplate2DBasic;
import com.osreboot.ridhvl.tile.HvlTileMap;

public class TileTest extends HvlTemplate2DBasic{

	public TileTest(){
		super(60, 1280, 720, "Unnamed", new HvlDisplayModeDefault());
	}

	static HvlTextureLoader textureLoader = new HvlTextureLoader(5);
	
	private HvlTileMap tilemap;

	public static void main(String[] args)
	{
		new TileTest().start();
	}
	
	@Override
	public void initialize(){		
		textureLoader.loadResource("White");
		textureLoader.loadResource("TestTilesheet");
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader("res/TestLevelSave.txt"));
			StringBuilder sb = new StringBuilder();
			String current;
			while ((current = reader.readLine()) != null)
			{
				sb.append(current);
				sb.append(System.lineSeparator());
			}
			tilemap = HvlTileMap.load(sb.toString(), textureLoader.getResource(1), 0, 0, 64, 64);
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(float delta){
		tilemap.draw(delta);
	}

}
