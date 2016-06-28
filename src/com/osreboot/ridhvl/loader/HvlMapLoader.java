package com.osreboot.ridhvl.loader;

import org.newdawn.slick.opengl.Texture;

import com.osreboot.ridhvl.map.HvlMap;

public class HvlMapLoader extends HvlContentLoader<HvlMap>{

	private float x, y, drawWidth, drawHeight;
	private Texture tilemap;
	
	public HvlMapLoader(String pathArg, float xArg, float yArg, float drawWidthArg, float drawHeightArg, Texture tilemapArg){
		super(pathArg);
		x = xArg;
		y = yArg;
		drawWidth = drawWidthArg;
		drawHeight = drawHeightArg;
		tilemap = tilemapArg;
	}
	
	public HvlMapLoader(float xArg, float yArg, float drawWidthArg, float drawHeightArg, Texture tilemapArg){
		super("/res");
		x = xArg;
		y = yArg;
		drawWidth = drawWidthArg;
		drawHeight = drawHeightArg;
		tilemap = tilemapArg;
	}

	public void setConfiguration(float xArg, float yArg, float drawWidthArg, float drawHeightArg, Texture tilemapArg){
		x = xArg;
		y = yArg;
		drawWidth = drawWidthArg;
		drawHeight = drawHeightArg;
		tilemap = tilemapArg;
	}
	
	public float getX(){
		return x;
	}

	public void setX(float xArg){
		x = xArg;
	}

	public float getY(){
		return y;
	}

	public void setY(float yArg){
		y = yArg;
	}

	public float getDrawWidth(){
		return drawWidth;
	}

	public void setDrawWidth(float drawWidthArg){
		drawWidth = drawWidthArg;
	}

	public float getDrawHeight(){
		return drawHeight;
	}

	public void setDrawHeight(float drawHeightArg){
		drawHeight = drawHeightArg;
	}

	public Texture getTilemap(){
		return tilemap;
	}

	public void setTilemap(Texture tilemapArg){
		tilemap = tilemapArg;
	}

	@Override
	public HvlMap loadResourceMeta(String nameArg){
		return HvlMap.load(getPath(), x, y, drawWidth, drawHeight, tilemap);
	}

}
