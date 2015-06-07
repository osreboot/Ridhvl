package com.osreboot.ridhvl.menu.component.template;

import org.newdawn.slick.opengl.Texture;

import com.osreboot.ridhvl.menu.component.HvlButton;
import com.osreboot.ridhvl.painter.painter2d.HvlPainter2D;

public class HvlTextureButton extends HvlButton {

	private Texture tOff, tHover, tOn;

	public HvlTextureButton(float xArg, float yArg, float xlArg, float ylArg,
			float inversionHeightArg, Texture tOffArg, Texture tHoverArg, Texture tOnArg) {
		super(xArg, yArg, xlArg, ylArg, inversionHeightArg);
		tOff = tOffArg;
		tHover = tHoverArg;
		tOn = tOnArg;
	}
	
	@Override
	public void draw(float delta)
	{
		if (isBeingPressed(0))
			HvlPainter2D.hvlDrawQuad(getX(), getY(), getWidth(), getHeight(), tOn);
		else if (isHovering())
			HvlPainter2D.hvlDrawQuad(getX(), getY(), getWidth(), getHeight(), tHover);
		else
			HvlPainter2D.hvlDrawQuad(getX(), getY(), getWidth(), getHeight(), tOff);
	}
}
