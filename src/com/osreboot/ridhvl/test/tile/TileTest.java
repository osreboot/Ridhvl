package com.osreboot.ridhvl.test.tile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.osreboot.ridhvl.display.collection.HvlDisplayModeDefault;
import com.osreboot.ridhvl.loader.HvlTextureLoader;
import com.osreboot.ridhvl.template.HvlTemplate2DBasic;
import com.osreboot.ridhvl.tile.HvlLayeredTileMap;

public class TileTest extends HvlTemplate2DBasic {

	public TileTest() {
		super(60, 1280, 720, "Unnamed", new HvlDisplayModeDefault());
	}

	static HvlTextureLoader textureLoader = new HvlTextureLoader(5);

	private HvlLayeredTileMap tilemaps;

	public static void main(String[] args)
	{
		new TileTest();
	}
	
	@Override
	public void initialize() {
		textureLoader.loadResource("White");
		textureLoader.loadResource("TestTilemap");

//		tilemaps = new HvlTileMap[] {new HvlTileMap(textureLoader.getResource(1), 16, 16, 16, 16, 0,
//				0, 64, 64), new HvlTileMap(textureLoader.getResource(1), 16, 16, 16, 16, 0,
//						0, 64, 64)};
//		tilemaps[0].fill(new HvlSimpleTile(0));
//		tilemaps[0].setTile(0, 0, new HvlSimpleTile(96));
//		tilemaps[1].setTile(1, 0, new HvlSimpleTile(1));
//		tilemaps[1].setTile(2, 0, new HvlAnimatedTile(1.0f, 1, 2, 18));
//		try {
//			BufferedWriter writer = new BufferedWriter(new FileWriter(
//					"res/SavedMap.map"));
//			writer.write(HvlTileMap.save(tilemaps));
//			writer.close();
//		} catch (IOException e1) {
//			
//		}

		try {
			BufferedReader reader = new BufferedReader(new FileReader(
					"res/SavedMap.map"));
			StringBuilder sb = new StringBuilder();
			String current;
			while ((current = reader.readLine()) != null) {
				sb.append(current);
				sb.append(System.lineSeparator());
			}
			tilemaps = HvlLayeredTileMap.load(sb.toString(),  textureLoader.getResource(1),  0, 0, 64, 64);
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(float delta) {
		if (tilemaps != null)
		{
			tilemaps.draw(delta);
		}
	}

}