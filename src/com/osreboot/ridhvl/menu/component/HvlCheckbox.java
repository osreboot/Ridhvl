package com.osreboot.ridhvl.menu.component;

import com.osreboot.ridhvl.menu.HvlComponent;

public class HvlCheckbox extends HvlComponent {

	private boolean previousPressed, currentPressed, previousHover,
			currentHover;
	private boolean checked;
	private HvlComponentDrawable off, offHover, on, onHover;

	public void onChanged(boolean state) {
	}

	public HvlCheckbox(float xArg, float yArg, float xlArg, float ylArg,
			HvlComponentDrawable offArg, HvlComponentDrawable onArg) {
		super(xArg, yArg, xlArg, ylArg);
		off = offArg;
		offHover = offArg;
		on = onArg;
		onHover = onArg;
	}

	public HvlCheckbox(float xArg, float yArg, float xlArg, float ylArg,
			boolean checkedArg, HvlComponentDrawable offArg,
			HvlComponentDrawable onArg) {
		super(xArg, yArg, xlArg, ylArg);
		checked = checkedArg;
		off = offArg;
		offHover = offArg;
		on = onArg;
		onHover = onArg;
	}

	public HvlCheckbox(float xArg, float yArg, float xlArg, float ylArg,
			HvlComponentDrawable offArg, HvlComponentDrawable hoverArg,
			HvlComponentDrawable onArg) {
		super(xArg, yArg, xlArg, ylArg);
		off = offArg;
		offHover = hoverArg;
		on = onArg;
		onHover = hoverArg;
	}

	public HvlCheckbox(float xArg, float yArg, float xlArg, float ylArg,
			boolean checkedArg, HvlComponentDrawable offArg,
			HvlComponentDrawable hoverArg, HvlComponentDrawable onArg) {
		super(xArg, yArg, xlArg, ylArg);
		checked = checkedArg;
		off = offArg;
		offHover = hoverArg;
		on = onArg;
		onHover = hoverArg;
	}

	public HvlCheckbox(float xArg, float yArg, float xlArg, float ylArg,
			HvlComponentDrawable offArg, HvlComponentDrawable offHoverArg,
			HvlComponentDrawable onArg, HvlComponentDrawable onHoverArg) {
		super(xArg, yArg, xlArg, ylArg);
		off = offArg;
		offHover = offHoverArg;
		on = onArg;
		onHover = onHoverArg;
	}

	public HvlCheckbox(float xArg, float yArg, float xlArg, float ylArg,
			boolean checkedArg, HvlComponentDrawable offArg,
			HvlComponentDrawable offHoverArg, HvlComponentDrawable onArg,
			HvlComponentDrawable onHoverArg) {
		super(xArg, yArg, xlArg, ylArg);
		checked = checkedArg;
		off = offArg;
		offHover = offHoverArg;
		on = onArg;
		onHover = onHoverArg;
	}

	@Override
	public void update(float delta) {
		previousHover = currentHover;
		previousPressed = currentPressed;
		currentHover = isHovering();
		currentPressed = isBeingPressed(0);

		// This allows you to drag off of the checkbox without triggering it.
		if (previousHover && !currentHover) {
			previousPressed = false;
			currentPressed = false;
		}

		if (previousPressed && !currentPressed) {
			checked = !checked;
			onChanged(checked);
		}

		draw(delta);
	}

	@Override
	public void draw(float delta) {		
		if (getChecked()) {
			if (isHovering())
				onHover.draw(delta, getX(), getY(), getWidth(), getHeight());
			else
				on.draw(delta, getX(), getY(), getWidth(), getHeight());
		} else {
			if (isHovering())
				offHover.draw(delta, getX(), getY(), getWidth(), getHeight());
			else
				off.draw(delta, getX(), getY(), getWidth(), getHeight());
		}
	}

	public boolean getChecked() {
		return checked;
	}

	public void setChecked(boolean checkedArg) {
		checked = checkedArg;
	}

	public HvlComponentDrawable getOff() {
		return off;
	}

	public void setOff(HvlComponentDrawable off) {
		this.off = off;
	}

	public HvlComponentDrawable getOffHover() {
		return offHover;
	}

	public void setOffHover(HvlComponentDrawable offHover) {
		this.offHover = offHover;
	}

	public HvlComponentDrawable getOn() {
		return on;
	}

	public void setOn(HvlComponentDrawable on) {
		this.on = on;
	}

	public HvlComponentDrawable getOnHover() {
		return onHover;
	}

	public void setOnHover(HvlComponentDrawable onHover) {
		this.onHover = onHover;
	}
}
