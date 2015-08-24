package com.osreboot.ridhvl.menu.component.collection;

import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

import com.osreboot.ridhvl.menu.component.HvlComponentDrawable;
import com.osreboot.ridhvl.painter.painter2d.HvlPainter2D;

public class HvlTextureDrawable extends HvlComponentDrawable {

	private Texture texture;
	private Color color = Color.white;

	public HvlTextureDrawable(Texture textureArg) {
		texture = textureArg;
		color = Color.white;
	}

	@Override
	public void draw(float delta, float x, float y, float width, float height) {
		if (texture == null)
			return;
		HvlPainter2D.hvlDrawQuad(x, y, width, height, texture, color);
	}

	public Texture getTexture() {
		return texture;
	}

	public void setTexture(Texture texture) {
		this.texture = texture;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

}
