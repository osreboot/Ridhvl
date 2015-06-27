package com.osreboot.ridhvl.menu.component.collection;

import org.newdawn.slick.Color;

import com.osreboot.ridhvl.menu.component.HvlButton;
import com.osreboot.ridhvl.menu.component.HvlComponentDrawable;
import com.osreboot.ridhvl.painter.painter2d.HvlFontPainter2D;

public class HvlTextButton extends HvlButton {
	private HvlFontPainter2D font;
	private String text;
	private float textScale;
	private Color textColor;
	private float xAlign, yAlign;

	public HvlTextButton(float xArg, float yArg, float xlArg, float ylArg,
			HvlComponentDrawable offArg, HvlComponentDrawable onArg,
			HvlFontPainter2D fontArg, String textArg) {
		super(xArg, yArg, xlArg, ylArg, offArg, onArg);
		font = fontArg;
		text = textArg;
		textScale = 1.0f;
		textColor = Color.white;
		xAlign = 0.5f;
		yAlign = 0.5f;
	}

	public HvlTextButton(float xArg, float yArg, float xlArg, float ylArg,
			HvlComponentDrawable offArg, HvlComponentDrawable hoverArg,
			HvlComponentDrawable onArg, HvlFontPainter2D fontArg, String textArg) {
		super(xArg, yArg, xlArg, ylArg, offArg, hoverArg, onArg);
		font = fontArg;
		text = textArg;
		textScale = 1.0f;
		textColor = Color.white;
		xAlign = 0.5f;
		yAlign = 0.5f;
	}
	
	@Override
	public void draw(float delta)
	{
		super.draw(delta);
		float xOffset = (getWidth() - (font.getLineWidth(text) * textScale)) * xAlign;
		float yOffset = (getHeight() - (font.getFontHeight() * textScale)) * yAlign;
		font.hvlDrawWord(text, getX() + xOffset, getY() + yOffset, textScale, textColor);
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

	public float getTextScale() {
		return textScale;
	}

	public void setTextScale(float textScale) {
		this.textScale = textScale;
	}

	public Color getTextColor() {
		return textColor;
	}

	public void setTextColor(Color textColor) {
		this.textColor = textColor;
	}

	public float getxAlign() {
		return xAlign;
	}

	public void setxAlign(float xAlign) {
		this.xAlign = xAlign;
	}

	public float getyAlign() {
		return yAlign;
	}

	public void setyAlign(float yAlign) {
		this.yAlign = yAlign;
	}
}
