package com.osreboot.ridhvl.menu.component.collection;

import org.newdawn.slick.opengl.Texture;

import com.osreboot.ridhvl.menu.component.HvlButton;
import com.osreboot.ridhvl.painter.painter2d.HvlPainter2D;

public class HvlTextureButton extends HvlButton {

	private Texture textureOff, textureHover, textureOn;

	public HvlTextureButton(float xArg, float yArg, float xlArg, float ylArg,
			float inversionHeightArg, Texture tOffArg, Texture tOnArg) {
		super(xArg, yArg, xlArg, ylArg, inversionHeightArg);
		textureOff = tOffArg;
		textureHover = tOffArg;
		textureOn = tOnArg;
	}
	
	public HvlTextureButton(float xArg, float yArg, float xlArg, float ylArg,
			float inversionHeightArg, Texture tOffArg, Texture tHoverArg, Texture tOnArg) {
		super(xArg, yArg, xlArg, ylArg, inversionHeightArg);
		textureOff = tOffArg;
		textureHover = tHoverArg;
		textureOn = tOnArg;
	}
	
	@Override
	public void draw(float delta)
	{
		if (isBeingPressed(0))
			HvlPainter2D.hvlDrawQuad(getX(), getY(), getWidth(), getHeight(), textureOn);
		else if (isHovering())
			HvlPainter2D.hvlDrawQuad(getX(), getY(), getWidth(), getHeight(), textureHover);
		else
			HvlPainter2D.hvlDrawQuad(getX(), getY(), getWidth(), getHeight(), textureOff);
	}

	public Texture getTextureOff() {
		return textureOff;
	}

	public void setTextureOff(Texture textureOff) {
		this.textureOff = textureOff;
	}

	public Texture getTextureHover() {
		return textureHover;
	}

	public void setTextureHover(Texture textureHover) {
		this.textureHover = textureHover;
	}

	public Texture getTextureOn() {
		return textureOn;
	}

	public void setTextureOn(Texture textureOn) {
		this.textureOn = textureOn;
	}
}
