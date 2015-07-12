package com.osreboot.ridhvl.menu.component;

import com.osreboot.ridhvl.menu.HvlComponent;

public class HvlButton extends HvlComponent {

	private boolean previousHover, currentHover, previousClick, currentClick;
	private HvlComponentDrawable offDrawable, hoverDrawable, onDrawable;

	public HvlButton(float xlArg, float ylArg, HvlComponentDrawable offArg, HvlComponentDrawable onArg) {
		super(xlArg, ylArg);
		offDrawable = offArg;
		hoverDrawable = offArg;
		onDrawable = onArg;
	}

	public HvlButton(float xlArg, float ylArg, HvlComponentDrawable offArg, HvlComponentDrawable hoverArg, HvlComponentDrawable onArg) {
		super(xlArg, ylArg);
		offDrawable = offArg;
		hoverDrawable = hoverArg;
		onDrawable = onArg;
	}

	public HvlButton(float xArg, float yArg, float xlArg, float ylArg, HvlComponentDrawable offArg, HvlComponentDrawable onArg) {
		super(xArg, yArg, xlArg, ylArg);
		offDrawable = offArg;
		hoverDrawable = offArg;
		onDrawable = onArg;
	}

	public HvlButton(float xArg, float yArg, float xlArg, float ylArg, HvlComponentDrawable offArg, HvlComponentDrawable hoverArg, HvlComponentDrawable onArg) {
		super(xArg, yArg, xlArg, ylArg);
		offDrawable = offArg;
		hoverDrawable = hoverArg;
		onDrawable = onArg;
	}

	public void onPressing(int buttonArg) {
	}

	public void onTriggered() {
	}

	public boolean isTriggered() {
		return previousClick && !currentClick;
	}

	public void draw(float delta) {
		if (isBeingPressed(0))
			onDrawable.draw(delta, getX(), getY(), getWidth(), getHeight());
		else if (isHovering())
			hoverDrawable.draw(delta, getX(), getY(), getWidth(), getHeight());
		else
			offDrawable.draw(delta, getX(), getY(), getWidth(), getHeight());
	}

	public final void update(float delta) {
		previousHover = currentHover;
		previousClick = currentClick;

		currentHover = isHovering();
		currentClick = isBeingPressed(0);

		// Allows "cancelling" by dragging off of the button
		if (previousHover && !currentHover) {
			previousClick = false;
			currentClick = false;
		}

		if (previousClick && !currentClick) {
			onTriggered();
		} else if (currentClick) {
			onPressing(0);
		}
	}

	public HvlComponentDrawable getOffDrawable() {
		return offDrawable;
	}

	public void setOffDrawable(HvlComponentDrawable offDrawable) {
		this.offDrawable = offDrawable;
	}

	public HvlComponentDrawable getHoverDrawable() {
		return hoverDrawable;
	}

	public void setHoverDrawable(HvlComponentDrawable hoverDrawable) {
		this.hoverDrawable = hoverDrawable;
	}

	public HvlComponentDrawable getOnDrawable() {
		return onDrawable;
	}

	public void setOnDrawable(HvlComponentDrawable onDrawable) {
		this.onDrawable = onDrawable;
	}
}
