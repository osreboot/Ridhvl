package com.osreboot.ridhvl.menu.component;

import com.osreboot.ridhvl.action.HvlAction2;
import com.osreboot.ridhvl.menu.HvlComponent;
import com.osreboot.ridhvl.menu.HvlComponentDefault;

public class HvlButton extends HvlComponent {

	public static abstract class OnClickedCommand {
		public abstract void run(HvlButton callingButton);
	}
	
	public static abstract class OnPressingCommand {
		public abstract void run(HvlButton callingButton);
	}
	
	private boolean previousHover, currentHover, previousClick, currentClick;
	private HvlComponentDrawable offDrawable, hoverDrawable, onDrawable;
	private OnClickedCommand clickedCommand;
	private OnPressingCommand pressingCommand;

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

	public boolean isTriggered() {
		return previousClick && !currentClick;
	}

	public void draw(float delta) {
		if (isBeingPressed(0))
		{
			if (onDrawable != null)
				onDrawable.draw(delta, getX(), getY(), getWidth(), getHeight());
		}
		else if (isHovering())
		{
			if (hoverDrawable != null)
				hoverDrawable.draw(delta, getX(), getY(), getWidth(), getHeight());
		}
		else if (offDrawable != null)
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
			if (clickedCommand != null)
				clickedCommand.run(this);
		} else if (currentClick) {
			if (pressingCommand != null)
				pressingCommand.run(this);
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

	public OnClickedCommand getClickedCommand() {
		return clickedCommand;
	}

	public void setClickedCommand(OnClickedCommand clickedCommand) {
		this.clickedCommand = clickedCommand;
	}

	public OnPressingCommand getPressingCommand() {
		return pressingCommand;
	}

	public void setPressingCommand(OnPressingCommand pressingCommand) {
		this.pressingCommand = pressingCommand;
	}

	public static class Builder {
		private HvlButton tr;

		public Builder() {
			if (HvlComponentDefault.hasDefault(HvlButton.class))
				tr = HvlComponentDefault.getDefault(HvlButton.class).clone();
			else
				tr = new HvlButton(0, 0, null, null);
		}

		public Builder setX(float x) {
			tr.setX(x);
			return this;
		}

		public Builder setY(float y) {
			tr.setY(y);
			return this;
		}

		public Builder setWidth(float width) {
			tr.setWidth(width);
			return this;
		}

		public Builder setHeight(float height) {
			tr.setHeight(height);
			return this;
		}

		public Builder setEnabled(boolean enabled) {
			tr.setEnabled(enabled);
			return this;
		}

		public Builder setVisible(boolean visible) {
			tr.setVisible(visible);
			return this;
		}

		public Builder setOffDrawable(HvlComponentDrawable offDrawable) {
			tr.setOffDrawable(offDrawable);
			return this;
		}

		public Builder setHoverDrawable(HvlComponentDrawable hoverDrawable) {
			tr.setHoverDrawable(hoverDrawable);
			return this;
		}

		public Builder setOnDrawable(HvlComponentDrawable onDrawable) {
			tr.setOnDrawable(onDrawable);
			return this;
		}

		public Builder setClickedCommand(OnClickedCommand clickedCommand) {
			tr.setClickedCommand(clickedCommand);
			return this;
		}

		public Builder setPressingCommand(OnPressingCommand pressingCommand) {
			tr.setPressingCommand(pressingCommand);
			return this;
		}

		public Builder setUpdateOverride(HvlAction2<HvlComponent, Float> updateOverride) {
			tr.setUpdateOverride(updateOverride);
			return this;
		}

		public Builder setDrawOverride(HvlAction2<HvlComponent, Float> drawOverride) {
			tr.setDrawOverride(drawOverride);
			return this;
		}

		public HvlButton build() {
			return tr;
		}
	}

	public HvlButton clone() {
		HvlButton tr = new HvlButton(0, 0, null, null);
		// HvlComponent
		tr.setX(getX());
		tr.setY(getY());
		tr.setWidth(getWidth());
		tr.setHeight(getHeight());
		tr.setEnabled(isEnabled());
		tr.setVisible(isVisible());
		tr.setUpdateOverride(getUpdateOverride());
		tr.setDrawOverride(getDrawOverride());
		// HvlButton
		tr.offDrawable = offDrawable;
		tr.hoverDrawable = hoverDrawable;
		tr.onDrawable = onDrawable;
		return tr;
	}
}
