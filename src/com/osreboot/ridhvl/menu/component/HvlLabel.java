package com.osreboot.ridhvl.menu.component;

import org.newdawn.slick.Color;

import com.osreboot.ridhvl.menu.HvlComponent;
import com.osreboot.ridhvl.painter.painter2d.HvlFontPainter2D;

public class HvlLabel extends HvlComponent {

	private HvlFontPainter2D font;
	private String text;
	private Color color;
	
	public HvlLabel(float xArg, float yArg,
			float heightInversionArg, HvlFontPainter2D fontArg,
			String textArg, Color colorArg) {
		// The dimensions are 0 because
		// they get immediately reset anyways.
		super(xArg, yArg, 0, 0, heightInversionArg); 	
		font = fontArg;
		text = textArg;
		color = colorArg;
		updateDimensions();
	}
	
	@Override
	public void draw(float delta)
	{
		font.hvlDrawWord(text, getX(), getY(), color);
	}

	public HvlFontPainter2D getFont() {
		return font;
	}

	public void setFont(HvlFontPainter2D font) {
		this.font = font;
		updateDimensions();
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
		updateDimensions();
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
	private void updateDimensions()
	{
		this.setWidth(font.getLineWidth(text));
		this.setHeight(font.getFontHeight());
	}
}
