package com.osreboot.ridhvl.menu.component.template;

import org.newdawn.slick.opengl.Texture;

import com.osreboot.ridhvl.menu.component.HvlCheckbox;
import com.osreboot.ridhvl.painter.painter2d.HvlPainter2D;

public class HvlTextureCheckbox extends HvlCheckbox {

	private Texture textureOff, textureOffHover, textureOn, textureOnHover;
	
	public HvlTextureCheckbox(float xArg, float yArg, float xlArg, float ylArg,
			float inversionHeightArg, Texture offArg, Texture hoverArg, Texture onArg) {
		super(xArg, yArg, xlArg, ylArg, inversionHeightArg);
		textureOff = offArg;
		textureOffHover = hoverArg;
		textureOn = onArg;
		textureOnHover = hoverArg;
	}

	public HvlTextureCheckbox(float xArg, float yArg, float xlArg, float ylArg,
			float inversionHeightArg, Texture offArg, Texture hoverArg, Texture onArg, boolean checkedArg) {
		super(xArg, yArg, xlArg, ylArg, inversionHeightArg, checkedArg);
		textureOff = offArg;
		textureOffHover = hoverArg;
		textureOn = onArg;
		textureOnHover = hoverArg;
	}
	
	public HvlTextureCheckbox(float xArg, float yArg, float xlArg, float ylArg,
			float inversionHeightArg, Texture offArg, Texture offHoverArg, Texture onArg,
			Texture onHoverArg) {
		super(xArg, yArg, xlArg, ylArg, inversionHeightArg);
		textureOff = offArg;
		textureOffHover = offHoverArg;
		textureOn = onArg;
		textureOnHover = onHoverArg;
	}
	
	public HvlTextureCheckbox(float xArg, float yArg, float xlArg, float ylArg,
			float inversionHeightArg, Texture offArg, Texture offHoverArg, Texture onArg,
			Texture onHoverArg, boolean checkedArg) {
		super(xArg, yArg, xlArg, ylArg, inversionHeightArg, checkedArg);
		textureOff = offArg;
		textureOffHover = offHoverArg;
		textureOn = onArg;
		textureOnHover = onHoverArg;
	}
	
	@Override
	public void draw(float delta)
	{
		if (getChecked())
		{
			if (isHovering())
				HvlPainter2D.hvlDrawQuad(getX(), getY(), getWidth(), getHeight(), textureOnHover);
			else
				HvlPainter2D.hvlDrawQuad(getX(), getY(), getWidth(), getHeight(), textureOn);
		}
		else
		{
			if (isHovering())
				HvlPainter2D.hvlDrawQuad(getX(), getY(), getWidth(), getHeight(), textureOffHover);
			else
				HvlPainter2D.hvlDrawQuad(getX(), getY(), getWidth(), getHeight(), textureOff);
		}
	}
}
