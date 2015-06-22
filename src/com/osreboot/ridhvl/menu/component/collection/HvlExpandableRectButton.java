package com.osreboot.ridhvl.menu.component.collection;

import com.osreboot.ridhvl.menu.component.HvlButton;
import com.osreboot.ridhvl.painter.painter2d.HvlExpandingRectangle;

public class HvlExpandableRectButton extends HvlButton {

	private HvlExpandingRectangle rectOff, rectHover, rectOn;

	public HvlExpandableRectButton(float xArg, float yArg, float xlArg,
			float ylArg, float inversionHeightArg,
			HvlExpandingRectangle rectOffArg, HvlExpandingRectangle rectOnArg) {
		super(xArg, yArg, xlArg, ylArg, inversionHeightArg);
		rectOff = rectOffArg;
		rectHover = rectOffArg;
		rectOn = rectOnArg;
	}

	public HvlExpandableRectButton(float xArg, float yArg, float xlArg,
			float ylArg, float inversionHeightArg,
			HvlExpandingRectangle rectOffArg, HvlExpandingRectangle rectHoverArg,
			HvlExpandingRectangle rectOnArg) {
		super(xArg, yArg, xlArg, ylArg, inversionHeightArg);
		rectOff = rectOffArg;
		rectHover = rectHoverArg;
		rectOn = rectOnArg;
	}

	@Override
	public void draw(float delta) {
		rectOff.setTotalWidth(getWidth());
		rectOff.setTotalHeight(getHeight());
		rectOff.setX(getX());
		rectOff.setY(getY());
		rectHover.setTotalWidth(getWidth());
		rectHover.setTotalHeight(getHeight());
		rectHover.setX(getX());
		rectHover.setY(getY());
		rectOn.setTotalWidth(getWidth());
		rectOn.setTotalHeight(getHeight());
		rectOn.setX(getX());
		rectOn.setY(getY());
		
		if (isBeingPressed(0))
			rectOn.draw();
		else if (isHovering())
			rectHover.draw();
		else
			rectOff.draw();
	}

	public HvlExpandingRectangle getRectOff() {
		return rectOff;
	}

	public void setRectOff(HvlExpandingRectangle rectOff) {
		this.rectOff = rectOff;
	}

	public HvlExpandingRectangle getRectHover() {
		return rectHover;
	}

	public void setRectHover(HvlExpandingRectangle rectHover) {
		this.rectHover = rectHover;
	}

	public HvlExpandingRectangle getRectOn() {
		return rectOn;
	}

	public void setRectOn(HvlExpandingRectangle rectOn) {
		this.rectOn = rectOn;
	}
}
