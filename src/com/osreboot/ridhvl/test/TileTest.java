package com.osreboot.ridhvl.test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import com.osreboot.ridhvl.display.collection.HvlDisplayModeDefault;
import com.osreboot.ridhvl.input.HvlInputSeriesAction;
import com.osreboot.ridhvl.loader.HvlTextureLoader;
import com.osreboot.ridhvl.painter.HvlCamera;
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
		textureLoader.loadResource("Tilemap");
		textureLoader.loadResource("Tilemap2");

		tilemaps = HvlLayeredTileMap.load("SavedMap", true, 0, 0, 64, 64, textureLoader.getResource(1), textureLoader.getResource(2));
//		tilemaps.addEntity(new TestEntity("", 32, 32, tilemaps));
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("res/SavedMap.hvlmap"));
			String toWrite = HvlLayeredTileMap.save(tilemaps);
			writer.write(toWrite);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(float delta) {
		HvlCamera.setX(HvlCamera.getX() + (HvlInputSeriesAction.HORIZONTAL.getCurrentOutput() * delta * 256.0f));
		HvlCamera.setY(HvlCamera.getY() + (HvlInputSeriesAction.VERTICAL.getCurrentOutput() * delta * 256.0f));
		
		if (tilemaps != null) {
			tilemaps.update(delta);
			tilemaps.draw(delta);
		}
	}

}
