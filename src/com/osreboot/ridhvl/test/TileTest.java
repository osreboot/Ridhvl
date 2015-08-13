package com.osreboot.ridhvl.test;

import com.osreboot.ridhvl.display.collection.HvlDisplayModeDefault;
import com.osreboot.ridhvl.loader.HvlTextureLoader;
import com.osreboot.ridhvl.template.HvlTemplate2D;
import com.osreboot.ridhvl.tile.HvlLayeredTileMap;

public class TileTest extends HvlTemplate2D {

	public TileTest() {
		super(60, 1280, 720, "Ridhvl TileMap Test", new HvlDisplayModeDefault());
	}

	static HvlTextureLoader textureLoader = new HvlTextureLoader();

	private HvlLayeredTileMap tilemaps;

	@Override
	public void initialize() {
		textureLoader.loadResource("Icon");

		// tilemaps = new HvlTileMap[] {new
		// HvlTileMap(textureLoader.getResource(0), 16, 16, 16, 16, 0,
		// 0, 64, 64), new HvlTileMap(textureLoader.getResource(0), 16, 16, 16,
		// 16, 0,
		// 0, 64, 64)};
		// tilemaps[0].fill(new HvlSimpleTile(0));
		// tilemaps[0].setTile(0, 0, new HvlSimpleTile(96));
		// tilemaps[1].setTile(1, 0, new HvlSimpleTile(1));
		// tilemaps[1].setTile(2, 0, new HvlAnimatedTile(1.0f, 1, 2, 18));
		// try {
		// BufferedWriter writer = new BufferedWriter(new FileWriter(
		// "res/SavedMap.map"));
		// writer.write(HvlTileMap.save(tilemaps));
		// writer.close();
		// } catch (IOException e1) {
		//
		// }

		tilemaps = HvlLayeredTileMap.load("SavedMap", true, textureLoader.getResource(0), 0, 0, 64, 64);
		tilemaps.setCutOff(true);
		tilemaps.setxLeft(0);
		tilemaps.setxRight(512);
		tilemaps.setyTop(0);
		tilemaps.setyBottom(256);
	}

	@Override
	public void update(float delta) {
		if (tilemaps != null) {
			tilemaps.draw(delta);
		}
	}

}
