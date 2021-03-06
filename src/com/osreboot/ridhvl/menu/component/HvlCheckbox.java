package com.osreboot.ridhvl.menu.component;

import com.osreboot.ridhvl.action.HvlAction2;
import com.osreboot.ridhvl.action.HvlEvent2;
import com.osreboot.ridhvl.menu.HvlComponent;
import com.osreboot.ridhvl.menu.HvlComponentDefault;
import com.osreboot.ridhvl.menu.reflect.HvlDoNotClone;

public class HvlCheckbox extends HvlComponent {
	
	public static final HvlEvent2<HvlCheckbox, Boolean> EVENT_CHECKBOX_CLICKED = new HvlEvent2<>();
	
	@HvlDoNotClone
	protected boolean previousPressed, currentPressed, previousHover, currentHover;
	private boolean checked;
	protected HvlComponentDrawable offDrawable, offHoverDrawable, onDrawable, onHoverDrawable;
	@HvlDoNotClone
	protected HvlAction2<HvlCheckbox, Boolean> changedCommand;

	public HvlCheckbox(float xlArg, float ylArg, HvlComponentDrawable offArg, HvlComponentDrawable onArg) {
		super(xlArg, ylArg);
		offDrawable = offArg;
		offHoverDrawable = offArg;
		onDrawable = onArg;
		onHoverDrawable = onArg;
	}

	public HvlCheckbox(float xlArg, float ylArg, boolean checkedArg, HvlComponentDrawable offArg, HvlComponentDrawable onArg) {
		super(xlArg, ylArg);
		checked = checkedArg;
		offDrawable = offArg;
		offHoverDrawable = offArg;
		onDrawable = onArg;
		onHoverDrawable = onArg;
	}

	public HvlCheckbox(float xlArg, float ylArg, HvlComponentDrawable offArg, HvlComponentDrawable hoverArg, HvlComponentDrawable onArg) {
		super(xlArg, ylArg);
		offDrawable = offArg;
		offHoverDrawable = hoverArg;
		onDrawable = onArg;
		onHoverDrawable = hoverArg;
	}

	public HvlCheckbox(float xlArg, float ylArg, boolean checkedArg, HvlComponentDrawable offArg, HvlComponentDrawable hoverArg, HvlComponentDrawable onArg) {
		super(xlArg, ylArg);
		checked = checkedArg;
		offDrawable = offArg;
		offHoverDrawable = hoverArg;
		onDrawable = onArg;
		onHoverDrawable = hoverArg;
	}

	public HvlCheckbox(float xlArg, float ylArg, HvlComponentDrawable offArg, HvlComponentDrawable offHoverArg, HvlComponentDrawable onArg,
			HvlComponentDrawable onHoverArg) {
		super(xlArg, ylArg);
		offDrawable = offArg;
		offHoverDrawable = offHoverArg;
		onDrawable = onArg;
		onHoverDrawable = onHoverArg;
	}

	public HvlCheckbox(float xlArg, float ylArg, boolean checkedArg, HvlComponentDrawable offArg, HvlComponentDrawable offHoverArg,
			HvlComponentDrawable onArg, HvlComponentDrawable onHoverArg) {
		super(xlArg, ylArg);
		checked = checkedArg;
		offDrawable = offArg;
		offHoverDrawable = offHoverArg;
		onDrawable = onArg;
		onHoverDrawable = onHoverArg;
	}

	public HvlCheckbox(float xArg, float yArg, float xlArg, float ylArg, HvlComponentDrawable offArg, HvlComponentDrawable onArg) {
		super(xArg, yArg, xlArg, ylArg);
		offDrawable = offArg;
		offHoverDrawable = offArg;
		onDrawable = onArg;
		onHoverDrawable = onArg;
	}

	public HvlCheckbox(float xArg, float yArg, float xlArg, float ylArg, boolean checkedArg, HvlComponentDrawable offArg, HvlComponentDrawable onArg) {
		super(xArg, yArg, xlArg, ylArg);
		checked = checkedArg;
		offDrawable = offArg;
		offHoverDrawable = offArg;
		onDrawable = onArg;
		onHoverDrawable = onArg;
	}

	public HvlCheckbox(float xArg, float yArg, float xlArg, float ylArg, HvlComponentDrawable offArg, HvlComponentDrawable hoverArg,
			HvlComponentDrawable onArg) {
		super(xArg, yArg, xlArg, ylArg);
		offDrawable = offArg;
		offHoverDrawable = hoverArg;
		onDrawable = onArg;
		onHoverDrawable = hoverArg;
	}

	public HvlCheckbox(float xArg, float yArg, float xlArg, float ylArg, boolean checkedArg, HvlComponentDrawable offArg, HvlComponentDrawable hoverArg,
			HvlComponentDrawable onArg) {
		super(xArg, yArg, xlArg, ylArg);
		checked = checkedArg;
		offDrawable = offArg;
		offHoverDrawable = hoverArg;
		onDrawable = onArg;
		onHoverDrawable = hoverArg;
	}

	public HvlCheckbox(float xArg, float yArg, float xlArg, float ylArg, HvlComponentDrawable offArg, HvlComponentDrawable offHoverArg,
			HvlComponentDrawable onArg, HvlComponentDrawable onHoverArg) {
		super(xArg, yArg, xlArg, ylArg);
		offDrawable = offArg;
		offHoverDrawable = offHoverArg;
		onDrawable = onArg;
		onHoverDrawable = onHoverArg;
	}

	public HvlCheckbox(float xArg, float yArg, float xlArg, float ylArg, boolean checkedArg, HvlComponentDrawable offArg, HvlComponentDrawable offHoverArg,
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
			if (changedCommand != null)
				changedCommand.run(this, checked);
			EVENT_CHECKBOX_CLICKED.trigger(this, checked);
		}
	}

	@Override
	public void draw(float delta) {
		if(isBeingPressed(0)){//TODO this should be done more cleanly in a future refactor
			if(getChecked()){
				if (onDrawable != null)
					onDrawable.draw(delta, getX(), getY(), getWidth(), getHeight());
			}else{
				if (offDrawable != null)
					offDrawable.draw(delta, getX(), getY(), getWidth(), getHeight());
			}
		}else if (getChecked()) {
			if (isHovering()) {
				if (onHoverDrawable != null)
					onHoverDrawable.draw(delta, getX(), getY(), getWidth(), getHeight());
			} else {
				if (onDrawable != null)
					onDrawable.draw(delta, getX(), getY(), getWidth(), getHeight());
			}
		} else {
			if (isHovering()) {
				if (offHoverDrawable != null)
					offHoverDrawable.draw(delta, getX(), getY(), getWidth(), getHeight());
			} else {
				if (offDrawable != null)
					offDrawable.draw(delta, getX(), getY(), getWidth(), getHeight());
			}
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

	public HvlAction2<HvlCheckbox, Boolean> getChangedCommand() {
		return changedCommand;
	}

	public void setChangedCommand(HvlAction2<HvlCheckbox, Boolean> changedEvent) {
		this.changedCommand = changedEvent;
	}

	public static class Builder {
		private HvlCheckbox tr;

		public Builder() {
			tr = new HvlCheckbox(0, 0, null, null);
			if (HvlComponentDefault.hasDefault(HvlCheckbox.class))
				tr = HvlComponentDefault.getDefault(HvlCheckbox.class).cloneComponent(tr);
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

		public Builder setChecked(boolean checkedArg) {
			tr.setChecked(checkedArg);
			return this;
		}

		public Builder setOffDrawable(HvlComponentDrawable offDrawable) {
			tr.setOffDrawable(offDrawable);
			return this;
		}

		public Builder setOffHoverDrawable(HvlComponentDrawable offHoverDrawable) {
			tr.setOffHoverDrawable(offHoverDrawable);
			return this;
		}

		public Builder setOnDrawable(HvlComponentDrawable onDrawable) {
			tr.setOnDrawable(onDrawable);
			return this;
		}

		public Builder setOnHoverDrawable(HvlComponentDrawable onHoverDrawable) {
			tr.setOnHoverDrawable(onHoverDrawable);
			return this;
		}

		public Builder setChangedEventCommand(HvlAction2<HvlCheckbox, Boolean> changedEventCommand) {
			tr.setChangedCommand(changedEventCommand);
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

		public HvlCheckbox build() {
			return tr;
		}
	}
}
