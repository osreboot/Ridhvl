package com.osreboot.ridhvl.menu.component;

import org.lwjgl.input.Mouse;

import com.osreboot.ridhvl.menu.HvlComponent;
import com.osreboot.ridhvl.menu.HvlComponentDefault;
import com.osreboot.ridhvl.painter.HvlCursor;

public class HvlSlider extends HvlComponent {
	public enum SliderDirection {
		VERTICAL, HORIZONTAL
	}

	private HvlComponentDrawable handleUpDrawable, handleDownDrawable;
	private HvlComponentDrawable background;
	private SliderDirection direction;
	private float value;
	private float pValue;
	private float handleHeight, handleWidth;
	private float handleStartOffset, handleEndOffset;
	private float snapInterval;

	private boolean liveSnap;

	private boolean isBeingHeld;

	protected HvlSlider(float wArg, float hArg, SliderDirection dirArg, float handleWidthArg, float handleHeightArg, float value,
			HvlComponentDrawable handleArg, HvlComponentDrawable backgroundArg) {
		super(wArg, hArg);
		direction = dirArg;
		handleWidth = handleWidthArg;
		handleHeight = handleHeightArg;
		handleUpDrawable = handleArg;
		handleDownDrawable = handleArg;
		background = backgroundArg;
		liveSnap = true;
	}

	protected HvlSlider(float wArg, float hArg, SliderDirection dirArg, float handleWidthArg, float handleHeightArg, float value,
			HvlComponentDrawable handleUpArg, HvlComponentDrawable handleDownArg, HvlComponentDrawable backgroundArg) {
		super(wArg, hArg);
		direction = dirArg;
		handleWidth = handleWidthArg;
		handleHeight = handleHeightArg;
		handleUpDrawable = handleUpArg;
		handleDownDrawable = handleDownArg;
		background = backgroundArg;
		liveSnap = true;
	}

	protected HvlSlider(float xArg, float yArg, float wArg, float hArg, SliderDirection dirArg, float handleWidthArg, float handleHeightArg, float value,
			HvlComponentDrawable handleArg, HvlComponentDrawable backgroundArg) {
		super(xArg, yArg, wArg, hArg);
		direction = dirArg;
		handleWidth = handleWidthArg;
		handleHeight = handleHeightArg;
		handleUpDrawable = handleArg;
		handleDownDrawable = handleArg;
		background = backgroundArg;
		liveSnap = true;
	}

	protected HvlSlider(float xArg, float yArg, float wArg, float hArg, SliderDirection dirArg, float handleWidthArg, float handleHeightArg, float value,
			HvlComponentDrawable handleUpArg, HvlComponentDrawable handleDownArg, HvlComponentDrawable backgroundArg) {
		super(xArg, yArg, wArg, hArg);
		direction = dirArg;
		handleWidth = handleWidthArg;
		handleHeight = handleHeightArg;
		handleUpDrawable = handleUpArg;
		handleDownDrawable = handleDownArg;
		background = backgroundArg;
		liveSnap = true;
	}

	public void onValueChanged(float value) {
	}

	@Override
	public void update(float delta) {
		if (!isEnabled())
			return;

		switch (direction) {
		case HORIZONTAL: {
			float minX = getX() + handleStartOffset;
			float maxX = getX() + getWidth() - handleWidth - handleEndOffset;
			float handleMinX = minX + (value * (maxX - minX)) - (handleWidth / 2);
			float handleMaxX = handleMinX + (2 * handleWidth);

			float handleMinY = getY() + (getHeight() / 2) - (handleHeight / 2);
			float handleMaxY = getY() + (getHeight() / 2) + (handleHeight / 2);

			if (Mouse.isButtonDown(0) && HvlCursor.getCursorX() > handleMinX && HvlCursor.getCursorX() < handleMaxX && HvlCursor.getCursorY() > handleMinY
					&& HvlCursor.getCursorY() < handleMaxY) {
				isBeingHeld = true;
			} else if (!Mouse.isButtonDown(0)) {
				isBeingHeld = false;
			}
		}
			break;
		case VERTICAL: {
			float minY = getY() + handleStartOffset;
			float maxY = getY() + getHeight() - handleHeight - handleEndOffset;
			float handleMinY = minY + (value * (maxY - minY)) - (handleHeight / 2);
			float handleMaxY = handleMinY + (2 * handleHeight);

			float handleMinX = getX() + (getWidth() / 2) - (handleWidth / 2);
			float handleMaxX = getX() + (getWidth() / 2) + (handleWidth / 2);

			if (Mouse.isButtonDown(0) && HvlCursor.getCursorX() > handleMinX && HvlCursor.getCursorX() < handleMaxX && HvlCursor.getCursorY() > handleMinY
					&& HvlCursor.getCursorY() < handleMaxY) {
				isBeingHeld = true;
			} else if (!Mouse.isButtonDown(0)) {
				isBeingHeld = false;
			}
		}
			break;
		}

		if (isBeingHeld) {
			switch (direction) {
			case HORIZONTAL: {
				float minX = getX() + handleStartOffset;
				float maxX = getX() + getWidth() - handleEndOffset;
				float adjusted = HvlCursor.getCursorX() - minX;
				value = adjusted / (maxX - minX);
				value = Math.max(0, Math.min(value, 1.0f));
			}
				break;
			case VERTICAL: {
				float minY = getY() + handleStartOffset;
				float maxY = getY() + getHeight() - handleEndOffset;
				float adjusted = HvlCursor.getCursorY() - minY;
				value = adjusted / (maxY - minY);
				value = Math.max(0, Math.min(value, 1.0f));
			}
				break;
			}
		}

		if (liveSnap || !Mouse.isButtonDown(0)) {
			value = snapInterval * Math.round(value / snapInterval);
		}

		value = Math.max(0.0f, Math.min(value, 1.0f));

		if (pValue != value)
			onValueChanged(value);

		pValue = value;

	}

	@Override
	public void draw(float delta) {
		if (background != null)
			background.draw(delta, getX(), getY(), getWidth(), getHeight());

		switch (direction) {
		case HORIZONTAL: {
			float min = getX() + handleStartOffset;
			float max = getX() + getWidth() - handleEndOffset;
			float lerpedX = min + (value * (max - min));

			if (isBeingHeld)
				handleDownDrawable.draw(delta, lerpedX - (handleWidth / 2), getY() + (getHeight() / 2) - (handleHeight / 2), handleWidth, handleHeight);
			else
				handleUpDrawable.draw(delta, lerpedX - (handleWidth / 2), getY() + (getHeight() / 2) - (handleHeight / 2), handleWidth, handleHeight);
		}
			break;
		case VERTICAL: {
			float min = getY() + handleStartOffset;
			float max = getY() + getHeight() - handleEndOffset;
			float lerpedY = min + (value * (max - min));
			if (isBeingHeld)
				handleDownDrawable.draw(delta, getX() + (getWidth() / 2) - (handleWidth / 2), lerpedY - (handleHeight / 2), handleWidth, handleHeight);
			else
				handleUpDrawable.draw(delta, getX() + (getWidth() / 2) - (handleWidth / 2), lerpedY - (handleHeight / 2), handleWidth, handleHeight);
		}
			break;
		}
	}

	public HvlComponentDrawable getHandleUpDrawable() {
		return handleUpDrawable;
	}

	public void setHandleUpDrawable(HvlComponentDrawable handleUpDrawable) {
		this.handleUpDrawable = handleUpDrawable;
	}

	public HvlComponentDrawable getHandleDownDrawable() {
		return handleDownDrawable;
	}

	public void setHandleDownDrawable(HvlComponentDrawable handleDownDrawable) {
		this.handleDownDrawable = handleDownDrawable;
	}

	public HvlComponentDrawable getBackground() {
		return background;
	}

	public void setBackground(HvlComponentDrawable background) {
		this.background = background;
	}

	public final SliderDirection getDirection() {
		return direction;
	}

	public final void setDirection(SliderDirection direction) {
		this.direction = direction;
	}

	public final float getValue() {
		return value;
	}

	public final void setValue(float value) {
		this.value = value;
	}

	public final float getHandleHeight() {
		return handleHeight;
	}

	public final void setHandleHeight(float handleHeight) {
		this.handleHeight = handleHeight;
	}

	public final float getHandleWidth() {
		return handleWidth;
	}

	public final void setHandleWidth(float handleWidth) {
		this.handleWidth = handleWidth;
	}

	public final float getHandleStartOffset() {
		return handleStartOffset;
	}

	public final void setHandleStartOffset(float handleStartOffset) {
		this.handleStartOffset = handleStartOffset;
	}

	public final float getHandleEndOffset() {
		return handleEndOffset;
	}

	public final void setHandleEndOffset(float handleEndOffset) {
		this.handleEndOffset = handleEndOffset;
	}

	public final float getSnapInterval() {
		return snapInterval;
	}

	public final void setSnapInterval(float snapInterval) {
		this.snapInterval = snapInterval;
	}

	public boolean isBeingHeld() {
		return isBeingHeld;
	}

	public boolean isLiveSnap() {
		return liveSnap;
	}

	public void setLiveSnap(boolean liveSnap) {
		this.liveSnap = liveSnap;
	}

	public static class Builder {
		private HvlSlider tr;

		public Builder() {
			if (HvlComponentDefault.hasDefault(HvlSlider.class))
				tr = HvlComponentDefault.getDefault(HvlSlider.class).clone();
			else
				tr = new HvlSlider(0, 0, SliderDirection.HORIZONTAL, 0, 0, 0, null, null);
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

		public HvlComponentDrawable getHandleUpDrawable() {
			return tr.getHandleUpDrawable();
		}

		public Builder setHandleUpDrawable(HvlComponentDrawable handleUpDrawable) {
			tr.setHandleUpDrawable(handleUpDrawable);
			return this;
		}

		public HvlComponentDrawable getHandleDownDrawable() {
			return tr.getHandleDownDrawable();
		}

		public Builder setHandleDownDrawable(HvlComponentDrawable handleDownDrawable) {
			tr.setHandleDownDrawable(handleDownDrawable);
			return this;
		}

		public HvlComponentDrawable getBackground() {
			return tr.getBackground();
		}

		public Builder setBackground(HvlComponentDrawable background) {
			tr.setBackground(background);
			return this;
		}

		public final SliderDirection getDirection() {
			return tr.getDirection();
		}

		public final Builder setDirection(SliderDirection direction) {
			tr.setDirection(direction);
			return this;
		}

		public final float getValue() {
			return tr.getValue();
		}

		public final Builder setValue(float value) {
			tr.setValue(value);
			return this;
		}

		public final float getHandleHeight() {
			return tr.getHandleHeight();
		}

		public final Builder setHandleHeight(float handleHeight) {
			tr.setHandleHeight(handleHeight);
			return this;
		}

		public final float getHandleWidth() {
			return tr.getHandleWidth();
		}

		public final Builder setHandleWidth(float handleWidth) {
			tr.setHandleWidth(handleWidth);
			return this;
		}

		public final float getHandleStartOffset() {
			return tr.getHandleStartOffset();
		}

		public final Builder setHandleStartOffset(float handleStartOffset) {
			tr.setHandleStartOffset(handleStartOffset);
			return this;
		}

		public final float getHandleEndOffset() {
			return tr.getHandleEndOffset();
		}

		public final Builder setHandleEndOffset(float handleEndOffset) {
			tr.setHandleEndOffset(handleEndOffset);
			return this;
		}

		public final float getSnapInterval() {
			return tr.getSnapInterval();
		}

		public final Builder setSnapInterval(float snapInterval) {
			tr.setSnapInterval(snapInterval);
			return this;
		}

		public boolean isLiveSnap() {
			return tr.isLiveSnap();
		}

		public void setLiveSnap(boolean liveSnap) {
			tr.setLiveSnap(liveSnap);
		}

		public HvlSlider build() {
			return tr;
		}
	}
	
	public HvlSlider clone() {
		HvlSlider tr = new HvlSlider(0, 0, SliderDirection.HORIZONTAL, 0, 0, 0, null, null);
		// HvlComponent
		tr.setX(getX());
		tr.setY(getY());
		tr.setWidth(getWidth());
		tr.setHeight(getHeight());
		tr.setEnabled(isEnabled());
		tr.setVisible(isVisible());
		// HvlSlider
		tr.handleUpDrawable = handleUpDrawable;
		tr.handleDownDrawable = handleDownDrawable;
		tr.background = background;
		tr.direction = direction;
		tr.value = value;
		tr.handleHeight = handleHeight;
		tr.handleWidth = handleWidth;
		tr.handleStartOffset = handleStartOffset;
		tr.handleEndOffset = handleEndOffset;
		tr.snapInterval = snapInterval;
		tr.liveSnap = liveSnap;
		
		return tr;
	}
}
