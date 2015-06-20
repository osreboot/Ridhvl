package com.osreboot.ridhvl.menu.component.collection;

import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

import com.osreboot.ridhvl.menu.component.HvlTextBox;
import com.osreboot.ridhvl.painter.painter2d.HvlFontPainter2D;
import com.osreboot.ridhvl.painter.painter2d.HvlPainter2D;

public class HvlTextureTextBox extends HvlTextBox {

	private Texture focusedTexture, unfocusedTexture;
	private HvlFontPainter2D fontPainter;
	private Color textColor;
	private float textScale;
	private float offsetX, offsetY;

	public HvlTextureTextBox(float xArg, float yArg, float wArg, float hArg,
			float heightInversionArg, String textArg, Texture focusedArg,
			Texture unfocusedArg, HvlFontPainter2D fontArg) {
		super(xArg, yArg, wArg, hArg, heightInversionArg, textArg);
		focusedTexture = focusedArg;
		unfocusedTexture = unfocusedArg;
		fontPainter = fontArg;
		textColor = Color.black;
		textScale = 1.0f;
	}

	@Override
	public void draw(float delta) {
		HvlPainter2D.hvlDrawQuad(getX(), getY(), getWidth(), getHeight(),
				isFocused() ? focusedTexture : unfocusedTexture, Color.white);
		fontPainter.hvlDrawWord(getText(), getX() + offsetX, getY() + offsetY,
				textScale, textColor);
	}

	public Texture getFocusedTexture() {
		return focusedTexture;
	}

	public void setFocusedTexture(Texture focusedTexture) {
		this.focusedTexture = focusedTexture;
	}

	public Texture getUnfocusedTexture() {
		return unfocusedTexture;
	}

	public void setUnfocusedTexture(Texture unfocusedTexture) {
		this.unfocusedTexture = unfocusedTexture;
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
