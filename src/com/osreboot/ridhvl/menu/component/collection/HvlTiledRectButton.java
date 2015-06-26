package com.osreboot.ridhvl.menu.component.collection;

import com.osreboot.ridhvl.menu.component.HvlButton;
import com.osreboot.ridhvl.painter.painter2d.HvlTiledRect;

public class HvlTiledRectButton extends HvlButton {

	private HvlTiledRect rectOff, rectHover, rectOn;

	public HvlTiledRectButton(float xArg, float yArg, float xlArg, float ylArg,
			HvlTiledRect rectOffArg, HvlTiledRect rectOnArg) {
		super(xArg, yArg, xlArg, ylArg);
		rectOff = rectOffArg;
		rectHover = rectOffArg;
		rectOn = rectOnArg;
	}

	public HvlTiledRectButton(float xArg, float yArg, float xlArg, float ylArg,
			HvlTiledRect rectOffArg, HvlTiledRect rectHoverArg,
			HvlTiledRect rectOnArg) {
		super(xArg, yArg, xlArg, ylArg);
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

	public HvlTiledRect getRectOff() {
		return rectOff;
	}

	public void setRectOff(HvlTiledRect rectOff) {
		this.rectOff = rectOff;
	}

	public HvlTiledRect getRectHover() {
		return rectHover;
	}

	public void setRectHover(HvlTiledRect rectHover) {
		this.rectHover = rectHover;
	}

	public HvlTiledRect getRectOn() {
		return rectOn;
	}

	public void setRectOn(HvlTiledRect rectOn) {
		this.rectOn = rectOn;
	}
}
