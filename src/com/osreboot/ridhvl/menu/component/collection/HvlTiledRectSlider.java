package com.osreboot.ridhvl.menu.component.collection;

import org.newdawn.slick.opengl.Texture;

import com.osreboot.ridhvl.menu.component.HvlSlider;
import com.osreboot.ridhvl.painter.painter2d.HvlTiledRect;

public class HvlTiledRectSlider extends HvlSlider {

	private HvlTiledRect backgroundRect;

	public HvlTiledRectSlider(float xArg, float yArg, float wArg, float hArg,
			SliderDirection dirArg, float handleWidthArg,
			float handleHeightArg, float valueArg, Texture handleArg,
			HvlTiledRect backgroundArg) {
		super(xArg, yArg, wArg, hArg, dirArg, handleWidthArg, handleHeightArg,
				valueArg, handleArg);

		backgroundRect = backgroundArg;
	}

	public HvlTiledRectSlider(float xArg, float yArg, float wArg, float hArg,
			SliderDirection dirArg, float handleWidthArg,
			float handleHeightArg, float valueArg, Texture handleUpArg,
			Texture handleDownArg, HvlTiledRect backgroundArg) {
		super(xArg, yArg, wArg, hArg, dirArg, handleWidthArg, handleHeightArg,
				valueArg, handleUpArg, handleDownArg);

		backgroundRect = backgroundArg;
	}

	@Override
	public void draw(float delta) {
		backgroundRect.setX(getX());
		backgroundRect.setY(getY());
		backgroundRect.setTotalWidth(getWidth());
		backgroundRect.setTotalHeight(getHeight());
		backgroundRect.draw();
		super.draw(delta);
	}

	public HvlTiledRect getBackgroundRect() {
		return backgroundRect;
	}

	public void setBackgroundRect(HvlTiledRect backgroundTexture) {
		this.backgroundRect = backgroundTexture;
	}
}
