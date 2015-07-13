package com.osreboot.ridhvl.menu.component;

import com.osreboot.ridhvl.menu.HvlComponent;
import com.osreboot.ridhvl.menu.HvlComponentDefault;

public class HvlCheckbox extends HvlComponent {

	private boolean previousPressed, currentPressed, previousHover, currentHover;
	private boolean checked;
	private HvlComponentDrawable offDrawable, offHoverDrawable, onDrawable, onHoverDrawable;

	public void onChanged(boolean state) {
	}

	protected HvlCheckbox(float xlArg, float ylArg, HvlComponentDrawable offArg, HvlComponentDrawable onArg) {
		super(xlArg, ylArg);
		offDrawable = offArg;
		offHoverDrawable = offArg;
		onDrawable = onArg;
		onHoverDrawable = onArg;
	}

	protected HvlCheckbox(float xlArg, float ylArg, boolean checkedArg, HvlComponentDrawable offArg, HvlComponentDrawable onArg) {
		super(xlArg, ylArg);
		checked = checkedArg;
		offDrawable = offArg;
		offHoverDrawable = offArg;
		onDrawable = onArg;
		onHoverDrawable = onArg;
	}

	protected HvlCheckbox(float xlArg, float ylArg, HvlComponentDrawable offArg, HvlComponentDrawable hoverArg, HvlComponentDrawable onArg) {
		super(xlArg, ylArg);
		offDrawable = offArg;
		offHoverDrawable = hoverArg;
		onDrawable = onArg;
		onHoverDrawable = hoverArg;
	}

	protected HvlCheckbox(float xlArg, float ylArg, boolean checkedArg, HvlComponentDrawable offArg, HvlComponentDrawable hoverArg, HvlComponentDrawable onArg) {
		super(xlArg, ylArg);
		checked = checkedArg;
		offDrawable = offArg;
		offHoverDrawable = hoverArg;
		onDrawable = onArg;
		onHoverDrawable = hoverArg;
	}

	protected HvlCheckbox(float xlArg, float ylArg, HvlComponentDrawable offArg, HvlComponentDrawable offHoverArg, HvlComponentDrawable onArg,
			HvlComponentDrawable onHoverArg) {
		super(xlArg, ylArg);
		offDrawable = offArg;
		offHoverDrawable = offHoverArg;
		onDrawable = onArg;
		onHoverDrawable = onHoverArg;
	}

	protected HvlCheckbox(float xlArg, float ylArg, boolean checkedArg, HvlComponentDrawable offArg, HvlComponentDrawable offHoverArg,
			HvlComponentDrawable onArg, HvlComponentDrawable onHoverArg) {
		super(xlArg, ylArg);
		checked = checkedArg;
		offDrawable = offArg;
		offHoverDrawable = offHoverArg;
		onDrawable = onArg;
		onHoverDrawable = onHoverArg;
	}

	protected HvlCheckbox(float xArg, float yArg, float xlArg, float ylArg, HvlComponentDrawable offArg, HvlComponentDrawable onArg) {
		super(xArg, yArg, xlArg, ylArg);
		offDrawable = offArg;
		offHoverDrawable = offArg;
		onDrawable = onArg;
		onHoverDrawable = onArg;
	}

	protected HvlCheckbox(float xArg, float yArg, float xlArg, float ylArg, boolean checkedArg, HvlComponentDrawable offArg, HvlComponentDrawable onArg) {
		super(xArg, yArg, xlArg, ylArg);
		checked = checkedArg;
		offDrawable = offArg;
		offHoverDrawable = offArg;
		onDrawable = onArg;
		onHoverDrawable = onArg;
	}

	protected HvlCheckbox(float xArg, float yArg, float xlArg, float ylArg, HvlComponentDrawable offArg, HvlComponentDrawable hoverArg,
			HvlComponentDrawable onArg) {
		super(xArg, yArg, xlArg, ylArg);
		offDrawable = offArg;
		offHoverDrawable = hoverArg;
		onDrawable = onArg;
		onHoverDrawable = hoverArg;
	}

	protected HvlCheckbox(float xArg, float yArg, float xlArg, float ylArg, boolean checkedArg, HvlComponentDrawable offArg, HvlComponentDrawable hoverArg,
			HvlComponentDrawable onArg) {
		super(xArg, yArg, xlArg, ylArg);
		checked = checkedArg;
		offDrawable = offArg;
		offHoverDrawable = hoverArg;
		onDrawable = onArg;
		onHoverDrawable = hoverArg;
	}

	protected HvlCheckbox(float xArg, float yArg, float xlArg, float ylArg, HvlComponentDrawable offArg, HvlComponentDrawable offHoverArg,
			HvlComponentDrawable onArg, HvlComponentDrawable onHoverArg) {
		super(xArg, yArg, xlArg, ylArg);
		offDrawable = offArg;
		offHoverDrawable = offHoverArg;
		onDrawable = onArg;
		onHoverDrawable = onHoverArg;
	}

	protected HvlCheckbox(float xArg, float yArg, float xlArg, float ylArg, boolean checkedArg, HvlComponentDrawable offArg, HvlComponentDrawable offHoverArg,
			HvlComponentDrawable onArg, HvlComponentDrawable onHoverArg) {
		super(xArg, yArg, xlArg, ylArg);
		checked = checkedArg;
		offDrawable = offArg;
		offHoverDrawable = offHoverArg;
		onDrawable = onArg;
		onHoverDrawable = onHoverArg;
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
				onHoverDrawable.draw(delta, getX(), getY(), getWidth(), getHeight());
			else
				onDrawable.draw(delta, getX(), getY(), getWidth(), getHeight());
		} else {
			if (isHovering())
				offHoverDrawable.draw(delta, getX(), getY(), getWidth(), getHeight());
			else
				offDrawable.draw(delta, getX(), getY(), getWidth(), getHeight());
		}
	}

	public boolean getChecked() {
		return checked;
	}

	public void setChecked(boolean checkedArg) {
		checked = checkedArg;
	}

	public HvlComponentDrawable getOffDrawable() {
		return offDrawable;
	}

	public void setOffDrawable(HvlComponentDrawable offDrawable) {
		this.offDrawable = offDrawable;
	}

	public HvlComponentDrawable getOffHoverDrawable() {
		return offHoverDrawable;
	}

	public void setOffHoverDrawable(HvlComponentDrawable offHoverDrawable) {
		this.offHoverDrawable = offHoverDrawable;
	}

	public HvlComponentDrawable getOnDrawable() {
		return onDrawable;
	}

	public void setOnDrawable(HvlComponentDrawable onDrawable) {
		this.onDrawable = onDrawable;
	}

	public HvlComponentDrawable getOnHoverDrawable() {
		return onHoverDrawable;
	}

	public void setOnHoverDrawable(HvlComponentDrawable onHoverDrawable) {
		this.onHoverDrawable = onHoverDrawable;
	}

	public static class Builder {
		private HvlCheckbox tr;

		public Builder() {
			if (HvlComponentDefault.hasDefault(HvlCheckbox.class))
				tr = HvlComponentDefault.getDefault(HvlCheckbox.class).clone();
			else
				tr = new HvlCheckbox(0, 0, null, null);
		}

		public float getX() {
			return tr.getX();
		}

		public Builder setX(float x) {
			tr.setX(x);
			return this;
		}

		public float getY() {
			return tr.getY();
		}

		public Builder setY(float y) {
			tr.setY(y);
			return this;
		}

		public float getWidth() {
			return tr.getWidth();
		}

		public Builder setWidth(float width) {
			tr.setWidth(width);
			return this;
		}

		public float getHeight() {
			return tr.getHeight();
		}

		public Builder setHeight(float height) {
			tr.setHeight(height);
			return this;
		}

		public boolean isEnabled() {
			return tr.isEnabled();
		}

		public Builder setEnabled(boolean enabled) {
			tr.setEnabled(enabled);
			return this;
		}

		public boolean isVisible() {
			return tr.isVisible();
		}

		public Builder setVisible(boolean visible) {
			tr.setVisible(visible);
			return this;
		}

		public boolean getChecked() {
			return tr.getChecked();
		}

		public Builder setChecked(boolean checkedArg) {
			tr.setChecked(checkedArg);
			return this;
		}

		public HvlComponentDrawable getOffDrawable() {
			return tr.getOffDrawable();
		}

		public Builder setOffDrawable(HvlComponentDrawable offDrawable) {
			tr.setOffDrawable(offDrawable);
			return this;
		}

		public HvlComponentDrawable getOffHoverDrawable() {
			return tr.getOffHoverDrawable();
		}

		public Builder setOffHoverDrawable(HvlComponentDrawable offHoverDrawable) {
			tr.setOffHoverDrawable(offHoverDrawable);
			return this;
		}

		public HvlComponentDrawable getOnDrawable() {
			return tr.getOnDrawable();
		}

		public Builder setOnDrawable(HvlComponentDrawable onDrawable) {
			tr.setOnDrawable(onDrawable);
			return this;
		}

		public HvlComponentDrawable getOnHoverDrawable() {
			return tr.getOnHoverDrawable();
		}

		public Builder setOnHoverDrawable(HvlComponentDrawable onHoverDrawable) {
			tr.setOnHoverDrawable(onHoverDrawable);
			return this;
		}

		public HvlCheckbox build() {
			return tr;
		}
	}

	public HvlCheckbox clone() {
		HvlCheckbox tr = new HvlCheckbox(0, 0, null, null);
		// HvlComponent
		tr.setX(getX());
		tr.setY(getY());
		tr.setWidth(getWidth());
		tr.setHeight(getHeight());
		tr.setEnabled(isEnabled());
		tr.setVisible(isVisible());
		// HvlCheckbox
		tr.checked = checked;
		tr.offDrawable = offDrawable;
		tr.offHoverDrawable = offHoverDrawable;
		tr.onDrawable = onDrawable;
		tr.onHoverDrawable = onHoverDrawable;
		return tr;
	}
}
