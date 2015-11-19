package com.osreboot.ridhvl.menu.component;

import org.lwjgl.input.Mouse;

import com.osreboot.ridhvl.action.HvlAction2;
import com.osreboot.ridhvl.menu.HvlComponent;
import com.osreboot.ridhvl.menu.HvlComponentDefault;
import com.osreboot.ridhvl.menu.reflect.HvlDoNotClone;
import com.osreboot.ridhvl.painter.HvlCursor;
import com.osreboot.ridhvl.painter.painter2d.HvlPainter2D;

public class HvlSlider extends HvlComponent {
	public enum Direction {
		VERTICAL, HORIZONTAL
	}

	private HvlComponentDrawable handleUpDrawable, handleDownDrawable;
	private HvlComponentDrawable background;
	private Direction direction;
	private Direction textureDirection;
	private Direction handleDirection;
	private float value;
	@HvlDoNotClone
	private float pValue;
	private float handleHeight, handleWidth;
	private float handleStartOffset, handleEndOffset;
	private float snapInterval;
	private boolean liveSnap;
	@HvlDoNotClone
	private boolean isBeingHeld;

	@HvlDoNotClone
	private HvlAction2<HvlSlider, Float> valueChangedCommand;

	public HvlSlider(float wArg, float hArg, Direction dirArg, float handleWidthArg, float handleHeightArg, float value, HvlComponentDrawable handleArg,
			HvlComponentDrawable backgroundArg) {
		super(wArg, hArg);
		direction = dirArg;
		textureDirection = dirArg;
		handleDirection = dirArg;
		handleWidth = handleWidthArg;
		handleHeight = handleHeightArg;
		handleUpDrawable = handleArg;
		handleDownDrawable = handleArg;
		background = backgroundArg;
		liveSnap = true;
	}

	public HvlSlider(float wArg, float hArg, Direction dirArg, float handleWidthArg, float handleHeightArg, float value,
			HvlComponentDrawable handleUpArg, HvlComponentDrawable handleDownArg, HvlComponentDrawable backgroundArg) {
		super(wArg, hArg);
		direction = dirArg;
		textureDirection = dirArg;
		handleDirection = dirArg;
		handleWidth = handleWidthArg;
		handleHeight = handleHeightArg;
		handleUpDrawable = handleUpArg;
		handleDownDrawable = handleDownArg;
		background = backgroundArg;
		liveSnap = true;
	}

	public HvlSlider(float xArg, float yArg, float wArg, float hArg, Direction dirArg, float handleWidthArg, float handleHeightArg, float value,
			HvlComponentDrawable handleArg, HvlComponentDrawable backgroundArg) {
		super(xArg, yArg, wArg, hArg);
		direction = dirArg;
		textureDirection = dirArg;
		handleDirection = dirArg;
		handleWidth = handleWidthArg;
		handleHeight = handleHeightArg;
		handleUpDrawable = handleArg;
		handleDownDrawable = handleArg;
		background = backgroundArg;
		liveSnap = true;
	}

	public HvlSlider(float xArg, float yArg, float wArg, float hArg, Direction dirArg, float handleWidthArg, float handleHeightArg, float value,
			HvlComponentDrawable handleUpArg, HvlComponentDrawable handleDownArg, HvlComponentDrawable backgroundArg) {
		super(xArg, yArg, wArg, hArg);
		direction = dirArg;
		textureDirection = dirArg;
		handleDirection = dirArg;
		handleWidth = handleWidthArg;
		handleHeight = handleHeightArg;
		handleUpDrawable = handleUpArg;
		handleDownDrawable = handleDownArg;
		background = backgroundArg;
		liveSnap = true;
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

		if (pValue != value) {
			if (valueChangedCommand != null)
				valueChangedCommand.run(this, value);
		}

		pValue = value;

	}

	@Override
	public void draw(float delta) {
		if (background != null) {
			if (textureDirection == direction) // If the texture is facing the
												// right direction draw it
												// normally
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

			float x = lerpedX - (handleWidth / 2);
			float y = getY() + (getHeight() / 2) - (handleHeight / 2);

			if (handleDirection != direction) {
				switch (handleDirection) {
				case HORIZONTAL: {
					HvlPainter2D.hvlRotate(x + handleWidth, y, 90);

					if (isBeingHeld) {
						if (handleDownDrawable != null)
							handleDownDrawable.draw(delta, x + handleWidth, y, handleWidth, handleHeight);
					} else {
						if (handleUpDrawable != null)
							handleUpDrawable.draw(delta, x + handleWidth, y, handleWidth, handleHeight);
					}
				}
					break;
				case VERTICAL: {
					HvlPainter2D.hvlRotate(x, y + handleHeight, -90);

					if (isBeingHeld) {
						if (handleDownDrawable != null)
							handleDownDrawable.draw(delta, x, y + handleHeight, handleWidth, handleHeight);
					} else {
						if (handleUpDrawable != null)
							handleUpDrawable.draw(delta, x, y + handleHeight, handleWidth, handleHeight);
					}
				}
					break;
				}
			} else {
				if (isBeingHeld) {
					if (handleDownDrawable != null)
						handleDownDrawable.draw(delta, x, y, handleWidth, handleHeight);
				} else {
					if (handleUpDrawable != null)
						handleUpDrawable.draw(delta, x, y, handleWidth, handleHeight);
				}
			}

			if (handleDirection != direction)
				HvlPainter2D.hvlResetRotation();
		}
			break;
		case VERTICAL: {
			float min = getY() + handleStartOffset;
			float max = getY() + getHeight() - handleEndOffset;
			float lerpedY = min + (value * (max - min));

			float x = getX() + (getWidth() / 2) - (handleWidth / 2);
			float y = lerpedY - (handleHeight / 2);

			if (handleDirection != direction) {
				switch (handleDirection) {
				case HORIZONTAL: {
					HvlPainter2D.hvlRotate(x + handleWidth, y, 90);

					if (isBeingHeld) {
						if (handleDownDrawable != null)
							handleDownDrawable.draw(delta, x + handleWidth, y, handleWidth, handleHeight);
					} else {
						if (handleUpDrawable != null)
							handleUpDrawable.draw(delta, x + handleWidth, y, handleWidth, handleHeight);
					}
				}
					break;
				case VERTICAL: {
					HvlPainter2D.hvlRotate(x, y + handleHeight, -90);

					if (isBeingHeld) {
						if (handleDownDrawable != null)
							handleDownDrawable.draw(delta, x, y + handleHeight, handleWidth, handleHeight);
					} else {
						if (handleUpDrawable != null)
							handleUpDrawable.draw(delta, x, y + handleHeight, handleWidth, handleHeight);
					}
				}
					break;
				}
			} else {
				if (isBeingHeld) {
					if (handleDownDrawable != null)
						handleDownDrawable.draw(delta, x, y, handleWidth, handleHeight);
				} else {
					if (handleUpDrawable != null)
						handleUpDrawable.draw(delta, x, y, handleWidth, handleHeight);
				}
			}

			if (handleDirection != direction)
				HvlPainter2D.hvlResetRotation();
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

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
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

	public Direction getTextureDirection() {
		return textureDirection;
	}

	public void setTextureDirection(Direction textureDirection) {
		this.textureDirection = textureDirection;
	}

	public Direction getHandleDirection() {
		return handleDirection;
	}

	public void setHandleDirection(Direction handleDirection) {
		this.handleDirection = handleDirection;
	}

	public HvlAction2<HvlSlider, Float> getValueChangedCommand() {
		return valueChangedCommand;
	}

	public void setValueChangedCommand(HvlAction2<HvlSlider, Float> valueChangedCommand) {
		this.valueChangedCommand = valueChangedCommand;
	}

	public static class Builder {
		private HvlSlider tr;

		public Builder() {
			tr = new HvlSlider(0, 0, Direction.HORIZONTAL, 0, 0, 0, null, null);
			if (HvlComponentDefault.hasDefault(HvlSlider.class))
				tr = HvlComponentDefault.getDefault(HvlSlider.class).cloneComponent(tr);
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

		public Builder setDirection(Direction direction) {
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

		public Builder setTextureDirection(Direction textureDirection) {
			tr.setTextureDirection(textureDirection);
			return this;
		}

		public Builder setHandleDirection(Direction handleDirection) {
			tr.setHandleDirection(handleDirection);
			return this;
		}

		public Builder setValueChangedCommand(HvlAction2<HvlSlider, Float> valueChangedCommand) {
			tr.setValueChangedCommand(valueChangedCommand);
			return this;
		}

		public HvlSlider build() {
			return tr;
		}
	}
}
