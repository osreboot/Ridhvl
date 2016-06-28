package com.osreboot.ridhvl.menu.component.collection;

import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

import com.osreboot.ridhvl.menu.component.HvlComponentDrawable;
import com.osreboot.ridhvl.painter.painter2d.HvlPainter2D;

public class HvlTiledRectDrawable extends HvlComponentDrawable {

	private Texture t;
	private String format;
	private float lWidth, rWidth, uHeight, dHeight;
	private Color c;

	public HvlTiledRectDrawable(Texture t, String format, float lrWidth, float udHeight) {
		super();
		this.t = t;
		this.format = format;
		this.lWidth = lrWidth;
		this.rWidth = lrWidth;
		this.uHeight = udHeight;
		this.dHeight = udHeight;
		this.c = Color.white;
	}
	
	public HvlTiledRectDrawable(Texture t, String format, float lrWidth, float udHeight, Color c) {
		super();
		this.t = t;
		this.format = format;
		this.lWidth = lrWidth;
		this.rWidth = lrWidth;
		this.uHeight = udHeight;
		this.dHeight = udHeight;
		this.c = c;
	}

	public HvlTiledRectDrawable(Texture t, String format, float lWidth, float rWidth, float uHeight, float dHeight) {
		super();
		this.t = t;
		this.format = format;
		this.lWidth = lWidth;
		this.rWidth = rWidth;
		this.uHeight = uHeight;
		this.dHeight = dHeight;
		this.c = Color.white;
	}

	public HvlTiledRectDrawable(Texture t, String format, float lWidth, float rWidth, float uHeight, float dHeight, Color c) {
		super();
		this.t = t;
		this.format = format;
		this.lWidth = lWidth;
		this.rWidth = rWidth;
		this.uHeight = uHeight;
		this.dHeight = dHeight;
		this.c = c;
	}

	@Override
	public void draw(float delta, float x, float y, float width, float height) {
		HvlPainter2D.hvlDrawTiledQuad(x, y, width, height, lWidth, rWidth, uHeight, dHeight, format, t, c);
	}
	
	
}
