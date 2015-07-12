package com.osreboot.ridhvl.menu.component;

import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

import com.osreboot.ridhvl.menu.HvlComponent;
import com.osreboot.ridhvl.painter.painter2d.HvlPainter2D;

public class HvlImageComponent extends HvlComponent {

	private Texture texture;
	private Color color;

	public HvlImageComponent(float wArg, float hArg, Texture tArg) {
		super(wArg, hArg);
		texture = tArg;
		color = Color.white;
	}

	public HvlImageComponent(float xArg, float yArg, float wArg, float hArg, Texture tArg) {
		super(xArg, yArg, wArg, hArg);
		texture = tArg;
		color = Color.white;
	}

	@Override
	public void draw(float delta) {
		HvlPainter2D.hvlDrawQuad(getX(), getY(), getWidth(), getHeight(), texture, color);
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
