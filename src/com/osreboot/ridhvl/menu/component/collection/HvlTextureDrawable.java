package com.osreboot.ridhvl.menu.component.collection;

import org.newdawn.slick.opengl.Texture;

import com.osreboot.ridhvl.menu.component.HvlComponentDrawable;
import com.osreboot.ridhvl.painter.painter2d.HvlPainter2D;

public class HvlTextureDrawable extends HvlComponentDrawable {

	private Texture texture;
	
	public HvlTextureDrawable(Texture textureArg) {
		texture = textureArg;
	}

	@Override
	public void draw(float delta, float x, float y, float width, float height) {
		HvlPainter2D.hvlDrawQuad(x, y, width, height, texture);
	}

}
