package com.osreboot.ridhvl.tile;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.newdawn.slick.opengl.Texture;

import com.osreboot.ridhvl.painter.painter2d.HvlPainter2D;
import com.osreboot.ridhvl.tile.entity.HvlEntity;

public class HvlLayeredTileMap {
	private List<HvlTileMap> layers;
	private float x, y;
	private float tileWidth, tileHeight;

	private boolean cutOff;
	private float xLeft, xRight, yTop, yBottom;
	
	private List<HvlEntity> entities;

	public HvlLayeredTileMap(float xArg, float yArg, float tileWidthArg, float tileHeightArg, HvlTileMap... layersArg) {
		x = xArg;
		y = yArg;
		tileWidth = tileWidthArg;
		tileHeight = tileHeightArg;

		layers = new LinkedList<>();

		for (HvlTileMap layer : layersArg) {
			layers.add(layer);
		}
		
		entities = new LinkedList<>();
	}

	public void draw(float delta) {
		HvlPainter2D.TEXMAGBLUR.disable();

		for (HvlTileMap map : layers) {
			map.setX(x);
			map.setY(y);
			map.setTileWidth(tileWidth);
			map.setTileHeight(tileHeight);
			map.setCutOff(cutOff);
			map.setxLeft(xLeft);
			map.setxRight(xRight);
			map.setyTop(yTop);
			map.setyBottom(yBottom);
			map.draw(delta);
		}
		
		List<HvlEntity> toRemove = new LinkedList<>();
		
		for (HvlEntity entity : entities) {
			entity.update(delta);
			if (entity.isMarkedForDeletion())
				toRemove.add(entity);
		}
		
		for (HvlEntity entity : entities) {
			entity.draw(delta);
		}
		
		for (HvlEntity tr : toRemove) {
			entities.remove(tr);
		}

		HvlPainter2D.TEXMAGBLUR.enable();
	}

	public HvlTileMap getLayer(int layerArg) {
		return layers.get(layerArg);
	}

	public static HvlLayeredTileMap load(String inArg, boolean isPath, Texture tArg, float xArg, float yArg, float tileWidthArg, float tileHeightArg) {
		HvlLayeredTileMap tr = new HvlLayeredTileMap(xArg, yArg, tileWidthArg, tileHeightArg, HvlTileMap.load(inArg, isPath, tArg, xArg, yArg, tileWidthArg,
				tileHeightArg));

		
		
		return tr;
	}

	public static String save(HvlLayeredTileMap map) {
		StringBuilder sb = new StringBuilder();
		
		sb.append("<entities>");
		sb.append(System.lineSeparator());
		for (HvlEntity ent : map.entities)
		{
			sb.append("<" + ent.getClass().getName() + " " + ent.getX() + ", " + ent.getY() + ">");
			sb.append(System.lineSeparator());
			sb.append(ent.save());
			sb.append(System.lineSeparator());
			sb.append("</" + ent.getClass().getName() + ">");
			sb.append(System.lineSeparator());
		}
		sb.append("</entities>");
		sb.append(System.lineSeparator());

		for (HvlTileMap layer : map.layers) {
			sb.append(HvlTileMap.save(layer));
			sb.append(System.lineSeparator());
		}

		return sb.toString();
	}

	public int getLayerCount() {
		return layers.size();
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getTileWidth() {
		return tileWidth;
	}

	public void setTileWidth(float tileWidth) {
		this.tileWidth = tileWidth;
	}

	public float getTileHeight() {
		return tileHeight;
	}

	public void setTileHeight(float tileHeight) {
		this.tileHeight = tileHeight;
	}

	public boolean isCutOff() {
		return cutOff;
	}

	public void setCutOff(boolean cutOff) {
		this.cutOff = cutOff;
	}

	public float getxLeft() {
		return xLeft;
	}

	public void setxLeft(float xLeft) {
		this.xLeft = xLeft;
	}

	public float getxRight() {
		return xRight;
	}

	public void setxRight(float xRight) {
		this.xRight = xRight;
	}

	public float getyTop() {
		return yTop;
	}

	public void setyTop(float yTop) {
		this.yTop = yTop;
	}

	public float getyBottom() {
		return yBottom;
	}

	public void setyBottom(float yBottom) {
		this.yBottom = yBottom;
	}
	
	public void addEntity(HvlEntity ent) {
		entities.add(ent);
	}
	
	public List<HvlEntity> getEntities() {
		return Collections.unmodifiableList(entities);
	}
}
