package com.osreboot.ridhvl.menu.component.template;

import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

import com.osreboot.ridhvl.menu.component.HvlButton;
import com.osreboot.ridhvl.painter.painter2d.HvlPainter2D;

public class HvlColorButton extends HvlButton {

	private Color colorOff, colorHover, colorOn;
	private Texture texture;
	
	public HvlColorButton(float xArg, float yArg, float xlArg, float ylArg,
			float inversionHeightArg, Color colorOffArg, Color colorOnArg, Texture tArg) {
		super(xArg, yArg, xlArg, ylArg, inversionHeightArg);
		colorOff = colorOffArg;
		colorHover = colorOffArg;
		colorOn = colorOnArg;
		texture = tArg;
	}
	
	public HvlColorButton(float xArg, float yArg, float xlArg, float ylArg,
			float inversionHeightArg, Color colorOffArg, Color colorHoverArg, Color colorOnArg, Texture tArg) {
		super(xArg, yArg, xlArg, ylArg, inversionHeightArg);
		colorOff = colorOffArg;
		colorHover = colorHoverArg;
		colorOn = colorOnArg;
		texture = tArg;
	}
	
	@Override
	public void draw(float delta)
	{
		if (isBeingPressed(0))
			HvlPainter2D.hvlDrawQuad(getX(), getY(), getWidth(), getHeight(), texture, colorOn);
		else if (isHovering())
			HvlPainter2D.hvlDrawQuad(getX(), getY(), getWidth(), getHeight(), texture, colorHover);
		else
			HvlPainter2D.hvlDrawQuad(getX(), getY(), getWidth(), getHeight(), texture, colorOff);
	}

	
	public Color getColorOff() {
		return colorOff;
	}

	public void setColorOff(Color colorOff) {
		this.colorOff = colorOff;
	}

	public Color getColorHover() {
		return colorHover;
	}

	public void setColorHover(Color colorHover) {
		this.colorHover = colorHover;
	}

	public Color getColorOn() {
		return colorOn;
	}

	public void setColorOn(Color colorOn) {
		this.colorOn = colorOn;
	}

	public Texture getTexture() {
		return texture;
	}

	public void setTexture(Texture texture) {
		this.texture = texture;
	}

	
}
