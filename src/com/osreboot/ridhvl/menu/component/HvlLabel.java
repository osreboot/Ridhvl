package com.osreboot.ridhvl.menu.component;

import org.newdawn.slick.Color;

import com.osreboot.ridhvl.menu.HvlComponent;
import com.osreboot.ridhvl.painter.painter2d.HvlFontPainter2D;

public class HvlLabel extends HvlComponent {

	private HvlFontPainter2D font;
	private String text;
	private Color color;
	private float scale;

	public HvlLabel(float xArg, float yArg, HvlFontPainter2D fontArg,
			String textArg, Color colorArg) {
		// The dimensions are 0 because
		// they get immediately reset anyways.
		super(xArg, yArg, 0, 0);
		font = fontArg;
		text = textArg;
		color = colorArg;
		scale = 1.0f;
		updateDimensions();
	}

	public HvlLabel(float xArg, float yArg, float heightInversionArg,
			HvlFontPainter2D fontArg, String textArg, Color colorArg,
			float scaleArg) {
		// The dimensions are 0 because
		// they get immediately reset anyways.
		super(xArg, yArg, 0, 0);
		font = fontArg;
		text = textArg;
		color = colorArg;
		scale = scaleArg;
		updateDimensions();
	}

	@Override
	public void draw(float delta) {
		updateDimensions(); // Force the width and height.
		font.hvlDrawWord(text, getX(), getY(), scale, color);
	}

	public HvlFontPainter2D getFont() {
		return font;
	}

	public void setFont(HvlFontPainter2D font) {
		this.font = font;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public float getScale() {
		return scale;
	}

	public void setScale(float scale) {
		this.scale = scale;
	}

	private void updateDimensions() {
		this.setWidth(font.getLineWidth(text) * scale);
		this.setHeight(font.getFontHeight() * scale);
	}

}
