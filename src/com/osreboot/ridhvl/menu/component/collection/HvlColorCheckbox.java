package com.osreboot.ridhvl.menu.component.collection;

import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

import com.osreboot.ridhvl.menu.component.HvlCheckbox;
import com.osreboot.ridhvl.painter.painter2d.HvlPainter2D;

public class HvlColorCheckbox extends HvlCheckbox {

	private Texture texture;
	private Color colorOff, colorHoverOff, colorOn, colorHoverOn;

	public HvlColorCheckbox(float xArg, float yArg, float xlArg, float ylArg,
			float inversionHeightArg, Color colorOffArg, Color colorOnArg, Texture tArg) {
		super(xArg, yArg, xlArg, ylArg, inversionHeightArg);
		colorOff = colorOffArg;
		colorHoverOff = colorOffArg;
		colorOn = colorOnArg;
		colorHoverOn = colorOnArg;
		texture = tArg;
	}
	
	public HvlColorCheckbox(float xArg, float yArg, float xlArg, float ylArg,
			float inversionHeightArg, Color colorOffArg, Color colorOnArg,
			Texture tArg, boolean checkedArg) {
		super(xArg, yArg, xlArg, ylArg, inversionHeightArg, checkedArg);
		colorOff = colorOffArg;
		colorHoverOff = colorOffArg;
		colorOn = colorOnArg;
		colorHoverOn = colorOnArg;
		texture = tArg;
	}
	
	public HvlColorCheckbox(float xArg, float yArg, float xlArg, float ylArg,
			float inversionHeightArg, Color colorOffArg, Color colorHoverArg,
			Color colorOnArg, Texture tArg) {
		super(xArg, yArg, xlArg, ylArg, inversionHeightArg);
		colorOff = colorOffArg;
		colorHoverOff = colorHoverArg;
		colorOn = colorOnArg;
		colorHoverOn = colorHoverArg;
		texture = tArg;
	}

	public HvlColorCheckbox(float xArg, float yArg, float xlArg, float ylArg,
			float inversionHeightArg, Color colorOffArg, Color colorHoverArg,
			Color colorOnArg, Texture tArg, boolean checkedArg) {
		super(xArg, yArg, xlArg, ylArg, inversionHeightArg, checkedArg);
		colorOff = colorOffArg;
		colorHoverOff = colorHoverArg;
		colorOn = colorOnArg;
		colorHoverOn = colorHoverArg;
		texture = tArg;
	}

	public HvlColorCheckbox(float xArg, float yArg, float xlArg, float ylArg,
			float inversionHeightArg, Color colorOffArg, Color colorHoverOffArg,
			Color colorOnArg, Color colorHoverOnArg, Texture tArg) {
		super(xArg, yArg, xlArg, ylArg, inversionHeightArg);
		colorOff = colorOffArg;
		colorHoverOff = colorHoverOffArg;
		colorOn = colorOnArg;
		colorHoverOn = colorHoverOnArg;
		texture = tArg;
	}

	public HvlColorCheckbox(float xArg, float yArg, float xlArg, float ylArg,
			float inversionHeightArg, Color colorOffArg, Color colorHoverOffArg,
			Color colorOnArg, Color colorHoverOnArg, Texture tArg, boolean checkedArg) {
		super(xArg, yArg, xlArg, ylArg, inversionHeightArg, checkedArg);
		colorOff = colorOffArg;
		colorHoverOff = colorHoverOffArg;
		colorOn = colorOnArg;
		colorHoverOn = colorHoverOnArg;
		texture = tArg;
	}

	@Override
	public void draw(float delta)
	{
		if (getChecked())
		{
			if (isHovering())
				HvlPainter2D.hvlDrawQuad(getX(), getY(), getWidth(), getHeight(), texture, colorHoverOn);
			else
				HvlPainter2D.hvlDrawQuad(getX(), getY(), getWidth(), getHeight(), texture, colorOn);
		}
		else
		{
			if (isHovering())
				HvlPainter2D.hvlDrawQuad(getX(), getY(), getWidth(), getHeight(), texture, colorHoverOff);
			else
				HvlPainter2D.hvlDrawQuad(getX(), getY(), getWidth(), getHeight(), texture, colorOff);
		}
	}

	
	public Texture getTexture() {
		return texture;
	}

	public void setTexture(Texture texture) {
		this.texture = texture;
	}

	public Color getColorOff() {
		return colorOff;
	}

	public void setColorOff(Color colorOff) {
		this.colorOff = colorOff;
	}

	public Color getColorHoverOff() {
		return colorHoverOff;
	}

	public void setColorHoverOff(Color colorHoverOff) {
		this.colorHoverOff = colorHoverOff;
	}

	public Color getColorOn() {
		return colorOn;
	}

	public void setColorOn(Color colorOn) {
		this.colorOn = colorOn;
	}

	public Color getColorHoverOn() {
		return colorHoverOn;
	}

	public void setColorHoverOn(Color colorHoverOn) {
		this.colorHoverOn = colorHoverOn;
	}


}
