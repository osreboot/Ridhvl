package com.osreboot.ridhvl.menu.component.collection;

import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

import com.osreboot.ridhvl.menu.component.HvlSlider;
import com.osreboot.ridhvl.menu.component.HvlSlider.SliderDirection;
import com.osreboot.ridhvl.painter.painter2d.HvlPainter2D;

public class HvlTextureSlider extends HvlSlider {

	private Texture backgroundTexture;

	public HvlTextureSlider(float xArg, float yArg, float wArg, float hArg,
			float heightInversionArg, SliderDirection dirArg,
			float handleWidthArg, float handleHeightArg, float valueArg,
			Texture handleArg, Texture backgroundArg) {
		super(xArg, yArg, wArg, hArg, heightInversionArg, dirArg,
				handleWidthArg, handleHeightArg, valueArg, handleArg);

		backgroundTexture = backgroundArg;
	}

	public HvlTextureSlider(float xArg, float yArg, float wArg, float hArg,
			float heightInversionArg, SliderDirection dirArg,
			float handleWidthArg, float handleHeightArg, float valueArg,
			Texture handleUpArg, Texture handleDownArg, Texture backgroundArg) {
		super(xArg, yArg, wArg, hArg, heightInversionArg, dirArg,
				handleWidthArg, handleHeightArg, valueArg, handleUpArg,
				handleDownArg);

		backgroundTexture = backgroundArg;
	}

	@Override
	public void draw(float delta) {
		HvlPainter2D.hvlDrawQuad(getX(), getY(), getWidth(), getHeight(),
				backgroundTexture, Color.white);
		super.draw(delta);
	}

	public Texture getBackgroundTexture() {
		return backgroundTexture;
	}

	public void setBackgroundTexture(Texture backgroundTexture) {
		this.backgroundTexture = backgroundTexture;
	}
}
