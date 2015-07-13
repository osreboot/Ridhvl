package com.osreboot.ridhvl.menu.component;

import com.osreboot.ridhvl.menu.HvlComponent;

public class HvlButton extends HvlComponent {

	private boolean previousHover, currentHover, previousClick, currentClick;
	private HvlComponentDrawable offDrawable, hoverDrawable, onDrawable;

	protected HvlButton(float xlArg, float ylArg, HvlComponentDrawable offArg, HvlComponentDrawable onArg) {
		super(xlArg, ylArg);
		offDrawable = offArg;
		hoverDrawable = offArg;
		onDrawable = onArg;
	}

	protected HvlButton(float xlArg, float ylArg, HvlComponentDrawable offArg, HvlComponentDrawable hoverArg, HvlComponentDrawable onArg) {
		super(xlArg, ylArg);
		offDrawable = offArg;
		hoverDrawable = hoverArg;
		onDrawable = onArg;
	}

	protected HvlButton(float xArg, float yArg, float xlArg, float ylArg, HvlComponentDrawable offArg, HvlComponentDrawable onArg) {
		super(xArg, yArg, xlArg, ylArg);
		offDrawable = offArg;
		hoverDrawable = offArg;
		onDrawable = onArg;
	}

	protected HvlButton(float xArg, float yArg, float xlArg, float ylArg, HvlComponentDrawable offArg, HvlComponentDrawable hoverArg, HvlComponentDrawable onArg) {
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

	public static class Builder {
		private HvlButton tr;

		public Builder() {
			tr = new HvlButton(0, 0, null, null);
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

		public HvlComponentDrawable getOffDrawable() {
			return tr.getOffDrawable();
		}

		public Builder setOffDrawable(HvlComponentDrawable offDrawable) {
			tr.setOffDrawable(offDrawable);
			return this;
		}

		public HvlComponentDrawable getHoverDrawable() {
			return tr.getHoverDrawable();
		}

		public Builder setHoverDrawable(HvlComponentDrawable hoverDrawable) {
			tr.setHoverDrawable(hoverDrawable);
			return this;
		}

		public HvlComponentDrawable getOnDrawable() {
			return tr.getOnDrawable();
		}

		public Builder setOnDrawable(HvlComponentDrawable onDrawable) {
			tr.setOnDrawable(onDrawable);
			return this;
		}

		public HvlButton build() {
			return tr;
		}
	}
}
