package com.osreboot.ridhvl.menu.component.collection;

import org.newdawn.slick.opengl.Texture;

import com.osreboot.ridhvl.menu.component.HvlCheckbox;
import com.osreboot.ridhvl.painter.painter2d.HvlPainter2D;

public class HvlTextureCheckbox extends HvlCheckbox {

	private Texture textureOff, textureOffHover, textureOn, textureOnHover;

	public HvlTextureCheckbox(float xArg, float yArg, float xlArg, float ylArg,
			Texture offArg, Texture onArg) {
		super(xArg, yArg, xlArg, ylArg);
		textureOff = offArg;
		textureOffHover = offArg;
		textureOn = onArg;
		textureOnHover = onArg;
	}

	public HvlTextureCheckbox(float xArg, float yArg, float xlArg, float ylArg,
			Texture offArg, Texture onArg, boolean checkedArg) {
		super(xArg, yArg, xlArg, ylArg, checkedArg);
		textureOff = offArg;
		textureOffHover = offArg;
		textureOn = onArg;
		textureOnHover = onArg;
	}

	public HvlTextureCheckbox(float xArg, float yArg, float xlArg, float ylArg,
			Texture offArg, Texture hoverArg, Texture onArg) {
		super(xArg, yArg, xlArg, ylArg);
		textureOff = offArg;
		textureOffHover = hoverArg;
		textureOn = onArg;
		textureOnHover = hoverArg;
	}

	public HvlTextureCheckbox(float xArg, float yArg, float xlArg, float ylArg,
			Texture offArg, Texture hoverArg, Texture onArg, boolean checkedArg) {
		super(xArg, yArg, xlArg, ylArg, checkedArg);
		textureOff = offArg;
		textureOffHover = hoverArg;
		textureOn = onArg;
		textureOnHover = hoverArg;
	}

	public HvlTextureCheckbox(float xArg, float yArg, float xlArg, float ylArg,
			Texture offArg, Texture offHoverArg, Texture onArg,
			Texture onHoverArg) {
		super(xArg, yArg, xlArg, ylArg);
		textureOff = offArg;
		textureOffHover = offHoverArg;
		textureOn = onArg;
		textureOnHover = onHoverArg;
	}

	public HvlTextureCheckbox(float xArg, float yArg, float xlArg, float ylArg,
			Texture offArg, Texture offHoverArg, Texture onArg,
			Texture onHoverArg, boolean checkedArg) {
		super(xArg, yArg, xlArg, ylArg, checkedArg);
		textureOff = offArg;
		textureOffHover = offHoverArg;
		textureOn = onArg;
		textureOnHover = onHoverArg;
	}

	@Override
	public void draw(float delta) {
		if (getChecked()) {
			if (isHovering())
				HvlPainter2D.hvlDrawQuad(getX(), getY(), getWidth(),
						getHeight(), textureOnHover);
			else
				HvlPainter2D.hvlDrawQuad(getX(), getY(), getWidth(),
						getHeight(), textureOn);
		} else {
			if (isHovering())
				HvlPainter2D.hvlDrawQuad(getX(), getY(), getWidth(),
						getHeight(), textureOffHover);
			else
				HvlPainter2D.hvlDrawQuad(getX(), getY(), getWidth(),
						getHeight(), textureOff);
		}
	}

	public Texture getTextureOff() {
		return textureOff;
	}

	public void setTextureOff(Texture textureOff) {
		this.textureOff = textureOff;
	}

	public Texture getTextureOffHover() {
		return textureOffHover;
	}

	public void setTextureOffHover(Texture textureOffHover) {
		this.textureOffHover = textureOffHover;
	}

	public Texture getTextureOn() {
		return textureOn;
	}

	public void setTextureOn(Texture textureOn) {
		this.textureOn = textureOn;
	}

	public Texture getTextureOnHover() {
		return textureOnHover;
	}

	public void setTextureOnHover(Texture textureOnHover) {
		this.textureOnHover = textureOnHover;
	}
}
