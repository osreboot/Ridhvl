package com.osreboot.ridhvl.menu.component;

import org.lwjgl.input.Mouse;

import com.osreboot.ridhvl.menu.HvlComponent;
import com.osreboot.ridhvl.menu.HvlComponentDefault;
import com.osreboot.ridhvl.painter.HvlCursor;
import com.osreboot.ridhvl.painter.painter2d.HvlPainter2D;

public class HvlSlider extends HvlComponent {
	public enum SliderDirection {
		VERTICAL, HORIZONTAL
	}

	private HvlComponentDrawable handleUpDrawable, handleDownDrawable;
	private HvlComponentDrawable background;
	private SliderDirection direction;
	private SliderDirection textureDirection;
	private float value;
	private float pValue;
	private float handleHeight, handleWidth;
	private float handleStartOffset, handleEndOffset;
	private float snapInterval;

	private boolean liveSnap;

	private boolean isBeingHeld;

	public HvlSlider(float wArg, float hArg, SliderDirection dirArg, float handleWidthArg, float handleHeightArg, float value, HvlComponentDrawable handleArg,
			HvlComponentDrawable backgroundArg) {
		super(wArg, hArg);
		direction = dirArg;
		textureDirection = dirArg;
		handleWidth = handleWidthArg;
		handleHeight = handleHeightArg;
		handleUpDrawable = handleArg;
		handleDownDrawable = handleArg;
		background = backgroundArg;
		liveSnap = true;
	}

	public HvlSlider(float wArg, float hArg, SliderDirection dirArg, float handleWidthArg, float handleHeightArg, float value,
			HvlComponentDrawable handleUpArg, HvlComponentDrawable handleDownArg, HvlComponentDrawable backgroundArg) {
		super(wArg, hArg);
		direction = dirArg;
		textureDirection = dirArg;
		handleWidth = handleWidthArg;
		handleHeight = handleHeightArg;
		handleUpDrawable = handleUpArg;
		handleDownDrawable = handleDownArg;
		background = backgroundArg;
		liveSnap = true;
	}

	public HvlSlider(float xArg, float yArg, float wArg, float hArg, SliderDirection dirArg, float handleWidthArg, float handleHeightArg, float value,
			HvlComponentDrawable handleArg, HvlComponentDrawable backgroundArg) {
		super(xArg, yArg, wArg, hArg);
		direction = dirArg;
		textureDirection = dirArg;
		handleWidth = handleWidthArg;
		handleHeight = handleHeightArg;
		handleUpDrawable = handleArg;
		handleDownDrawable = handleArg;
		background = backgroundArg;
		liveSnap = true;
	}

	public HvlSlider(float xArg, float yArg, float wArg, float hArg, SliderDirection dirArg, float handleWidthArg, float handleHeightArg, float value,
			HvlComponentDrawable handleUpArg, HvlComponentDrawable handleDownArg, HvlComponentDrawable backgroundArg) {
		super(xArg, yArg, wArg, hArg);
		direction = dirArg;
		textureDirection = dirArg;
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
		if (background != null) {
			if (textureDirection == direction) // If the texture is facing the
												// right direction...
				background.draw(delta, getX(), getY(), getWidth(), getHeight());
			else // We need to rotate the background
			{
				switch (textureDirection) {
				case HORIZONTAL: {
					HvlPainter2D.hvlRotate(getX() + getWidth(), getY(), 90);
					background.draw(delta, getX() + getWidth(), getY(), getHeight(), getWidth());
					HvlPainter2D.hvlResetRotation();
				}
					break;
				case VERTICAL: {
					HvlPainter2D.hvlRotate(getX(), getY() + getHeight(), -90);
					background.draw(delta, getX(), getY() + getHeight(), getHeight(), getWidth());
					HvlPainter2D.hvlResetRotation();
				}
					break;
				}
			}
		}

		switch (direction) {
		case HORIZONTAL: {
			float min = getX() + handleStartOffset;
			float max = getX() + getWidth() - handleEndOffset;
			float lerpedX = min + (value * (max - min));

			if (isBeingHeld) {
				if (handleDownDrawable != null)
					handleDownDrawable.draw(delta, lerpedX - (handleWidth / 2), getY() + (getHeight() / 2) - (handleHeight / 2), handleWidth, handleHeight);
			} else {
				if (handleUpDrawable != null)
					handleUpDrawable.draw(delta, lerpedX - (handleWidth / 2), getY() + (getHeight() / 2) - (handleHeight / 2), handleWidth, handleHeight);
			}
		}
			break;
		case VERTICAL: {
			float min = getY() + handleStartOffset;
			float max = getY() + getHeight() - handleEndOffset;
			float lerpedY = min + (value * (max - min));
			if (isBeingHeld) {
				if (handleDownDrawable != null)
					handleDownDrawable.draw(delta, getX() + (getWidth() / 2) - (handleWidth / 2), lerpedY - (handleHeight / 2), handleWidth, handleHeight);
			} else {
				if (handleUpDrawable != null)
					handleUpDrawable.draw(delta, getX() + (getWidth() / 2) - (handleWidth / 2), lerpedY - (handleHeight / 2), handleWidth, handleHeight);
			}
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

	public SliderDirection getDirection() {
		return direction;
	}

	public void setDirection(SliderDirection direction) {
		this.direction = direction;
	}

	public float getValue() {
		return value;
	}

	public void setValue(float value) {
		this.value = value;
	}

	public float getHandleHeight() {
		return handleHeight;
	}

	public void setHandleHeight(float handleHeight) {
		this.handleHeight = handleHeight;
	}

	public float getHandleWidth() {
		return handleWidth;
	}

	public void setHandleWidth(float handleWidth) {
		this.handleWidth = handleWidth;
	}

	public float getHandleStartOffset() {
		return handleStartOffset;
	}

	public void setHandleStartOffset(float handleStartOffset) {
		this.handleStartOffset = handleStartOffset;
	}

	public float getHandleEndOffset() {
		return handleEndOffset;
	}

	public void setHandleEndOffset(float handleEndOffset) {
		this.handleEndOffset = handleEndOffset;
	}

	public float getSnapInterval() {
		return snapInterval;
	}

	public void setSnapInterval(float snapInterval) {
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

	public SliderDirection getTextureDirection() {
		return textureDirection;
	}

	public void setTextureDirection(SliderDirection textureDirection) {
		this.textureDirection = textureDirection;
	}

	public static class Builder {
		private HvlSlider tr;

		public Builder() {
			if (HvlComponentDefault.hasDefault(HvlSlider.class))
				tr = HvlComponentDefault.getDefault(HvlSlider.class).clone();
			else
				tr = new HvlSlider(0, 0, SliderDirection.HORIZONTAL, 0, 0, 0, null, null);
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

		public Builder setHandleUpDrawable(HvlComponentDrawable handleUpDrawable) {
			tr.setHandleUpDrawable(handleUpDrawable);
			return this;
		}

		public Builder setHandleDownDrawable(HvlComponentDrawable handleDownDrawable) {
			tr.setHandleDownDrawable(handleDownDrawable);
			return this;
		}

		public Builder setBackground(HvlComponentDrawable background) {
			tr.setBackground(background);
			return this;
		}

		public Builder setDirection(SliderDirection direction) {
			tr.setDirection(direction);
			return this;
		}

		public Builder setValue(float value) {
			tr.setValue(value);
			return this;
		}

		public Builder setHandleHeight(float handleHeight) {
			tr.setHandleHeight(handleHeight);
			return this;
		}

		public Builder setHandleWidth(float handleWidth) {
			tr.setHandleWidth(handleWidth);
			return this;
		}

		public Builder setHandleStartOffset(float handleStartOffset) {
			tr.setHandleStartOffset(handleStartOffset);
			return this;
		}

		public Builder setHandleEndOffset(float handleEndOffset) {
			tr.setHandleEndOffset(handleEndOffset);
			return this;
		}

		public Builder setSnapInterval(float snapInterval) {
			tr.setSnapInterval(snapInterval);
			return this;
		}

		public Builder setLiveSnap(boolean liveSnap) {
			tr.setLiveSnap(liveSnap);
			return this;
		}

		public Builder setTextureDirection(SliderDirection textureDirection) {
			tr.setTextureDirection(textureDirection);
			return this;
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
		tr.textureDirection = textureDirection;
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
