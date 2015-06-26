package com.osreboot.ridhvl.menu.component;

import com.osreboot.ridhvl.menu.HvlComponent;
import com.osreboot.ridhvl.painter.painter2d.HvlPainter2D;

public class HvlButton extends HvlComponent {

	private boolean previousHover, currentHover, previousClick, currentClick;
	private HvlComponentDrawable off, hover, on;
	
	public HvlButton(float xArg, float yArg, float xlArg, float ylArg, HvlComponentDrawable offArg, HvlComponentDrawable onArg) {
		super(xArg, yArg, xlArg, ylArg);
		off = offArg;
		hover = offArg;
		on = onArg;
	}
	
	public HvlButton(float xArg, float yArg, float xlArg, float ylArg, HvlComponentDrawable offArg, HvlComponentDrawable hoverArg, HvlComponentDrawable onArg) {
		super(xArg, yArg, xlArg, ylArg);
		off = offArg;
		hover = hoverArg;
		on = onArg;
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
			on.draw(delta, getX(), getY(), getWidth(), getHeight());
		else if (isHovering())
			hover.draw(delta, getX(), getY(), getWidth(), getHeight());
		else
			off.draw(delta, getX(), getY(), getWidth(), getHeight());
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
}
