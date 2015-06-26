package com.osreboot.ridhvl.menu.component.collection;

import org.newdawn.slick.Color;

import com.osreboot.ridhvl.menu.component.HvlTextBox;
import com.osreboot.ridhvl.painter.painter2d.HvlTiledRect;
import com.osreboot.ridhvl.painter.painter2d.HvlFontPainter2D;

public class HvlTiledRectTextBox extends HvlTextBox {

	private HvlTiledRect focusedRect, unfocusedRect;
	private HvlFontPainter2D fontPainter;
	private Color textColor;
	private float textScale;
	private float offsetX, offsetY;

	public HvlTiledRectTextBox(float xArg, float yArg, float wArg,
			float hArg, float heightInversionArg, String textArg,
			HvlTiledRect focusedArg,
			HvlTiledRect unfocusedArg, HvlFontPainter2D fontArg) {
		super(xArg, yArg, wArg, hArg, heightInversionArg, textArg);
		focusedRect = focusedArg;
		unfocusedRect = unfocusedArg;
		fontPainter = fontArg;
		textColor = Color.black;
		textScale = 1.0f;
	}

	@Override
	public void draw(float delta) {
		focusedRect.setX(getX());
		focusedRect.setY(getY());
		focusedRect.setTotalWidth(getWidth());
		focusedRect.setTotalHeight(getHeight());
		unfocusedRect.setX(getX());
		unfocusedRect.setY(getY());
		unfocusedRect.setTotalWidth(getWidth());
		unfocusedRect.setTotalHeight(getHeight());
		
		if (isFocused())
			focusedRect.draw();
		else
			unfocusedRect.draw();
		fontPainter.hvlDrawWord(getText(), getX() + offsetX, getY() + offsetY,
				textScale, textColor);
	}

	public HvlTiledRect getFocusedRect() {
		return focusedRect;
	}

	public void setFocusedRect(HvlTiledRect focusedRect) {
		this.focusedRect = focusedRect;
	}

	public HvlTiledRect getUnfocusedRect() {
		return unfocusedRect;
	}

	public void setUnfocusedRect(HvlTiledRect unfocusedRect) {
		this.unfocusedRect = unfocusedRect;
	}

	public HvlFontPainter2D getFontPainter() {
		return fontPainter;
	}

	public void setFontPainter(HvlFontPainter2D fontPainter) {
		this.fontPainter = fontPainter;
	}

	public Color getTextColor() {
		return textColor;
	}

	public void setTextColor(Color textColor) {
		this.textColor = textColor;
	}

	public float getTextScale() {
		return textScale;
	}

	public void setTextScale(float textScale) {
		this.textScale = textScale;
	}

	public float getOffsetX() {
		return offsetX;
	}

	public void setOffsetX(float offsetX) {
		this.offsetX = offsetX;
	}

	public float getOffsetY() {
		return offsetY;
	}

	public void setOffsetY(float offsetY) {
		this.offsetY = offsetY;
	}
}
