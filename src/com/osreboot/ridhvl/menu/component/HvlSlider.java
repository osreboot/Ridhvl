package com.osreboot.ridhvl.menu.component;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

import com.osreboot.ridhvl.menu.HvlComponent;
import com.osreboot.ridhvl.painter.HvlCursor;
import com.osreboot.ridhvl.painter.painter2d.HvlPainter2D;

public abstract class HvlSlider extends HvlComponent {
	public enum SliderDirection {
		VERTICAL, HORIZONTAL
	}

	private Texture handleUpTexture, handleDownTexture;
	private SliderDirection direction;
	private float value;
	private float handleHeight, handleWidth;
	private float handleStartOffset, handleEndOffset;
	private float snapInterval;

	private boolean isBeingHeld;

	public HvlSlider(float xArg, float yArg, float wArg, float hArg,
			float heightInversionArg, SliderDirection dirArg,
			float handleWidthArg, float handleHeightArg, float value,
			Texture handleArg) {
		super(xArg, yArg, wArg, hArg, heightInversionArg);
		direction = dirArg;
		handleWidth = handleWidthArg;
		handleHeight = handleHeightArg;
		handleUpTexture = handleArg;
		handleDownTexture = handleArg;
	}

	public HvlSlider(float xArg, float yArg, float wArg, float hArg,
			float heightInversionArg, SliderDirection dirArg,
			float handleWidthArg, float handleHeightArg, float value,
			Texture handleUpArg, Texture handleDownArg) {
		super(xArg, yArg, wArg, hArg, heightInversionArg);
		direction = dirArg;
		handleWidth = handleWidthArg;
		handleHeight = handleHeightArg;
		handleUpTexture = handleUpArg;
		handleDownTexture = handleDownArg;
	}

	@Override
	public void update(float delta) {
		if (!isEnabled()) return;
		
		switch (direction) {
		case HORIZONTAL: {
			float minX = getX() + handleStartOffset;
			float maxX = getX() + getWidth() - handleWidth - handleEndOffset;
			float handleMinX = minX + (value * (maxX - minX))
					- (handleWidth / 2);
			float handleMaxX = handleMinX + (2 * handleWidth);

			float handleMinY = getY() + (getHeight() / 2) - (handleHeight / 2);
			float handleMaxY = getY() + (getHeight() / 2) + (handleHeight / 2);

			if (Mouse.isButtonDown(0) && HvlCursor.getCursorX() > handleMinX
					&& HvlCursor.getCursorX() < handleMaxX
					&& HvlCursor.getCursorY() > handleMinY
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
			float handleMinY = minY + (value * (maxY - minY))
					- (handleHeight / 2);
			float handleMaxY = handleMinY + (2 * handleHeight);

			float handleMinX = getX() + (getWidth() / 2) - (handleWidth / 2);
			float handleMaxX = getX() + (getWidth() / 2) + (handleWidth / 2);

			if (Mouse.isButtonDown(0) && HvlCursor.getCursorX() > handleMinX
					&& HvlCursor.getCursorX() < handleMaxX
					&& HvlCursor.getCursorY() > handleMinY
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

//		if (!Mouse.isButtonDown(0)) {
			value = snapInterval * Math.round(value / snapInterval);
//		}

		value = Math.max(0.0f, Math.min(value, 1.0f));
	}

	@Override
	public void draw(float delta) {
		switch (direction) {
		case HORIZONTAL: {
			float min = getX() + handleStartOffset;
			float max = getX() + getWidth() - handleEndOffset;
			float lerpedX = min + (value * (max - min));

			HvlPainter2D.hvlDrawQuad(lerpedX - (handleWidth / 2), getY()
					+ (getHeight() / 2) - (handleHeight / 2), handleWidth,
					handleHeight, isBeingHeld ? handleDownTexture
							: handleUpTexture, Color.white);
		}
			break;
		case VERTICAL: {
			float min = getY() + handleStartOffset;
			float max = getY() + getHeight() - handleEndOffset;
			float lerpedY = min + (value * (max - min));
			HvlPainter2D.hvlDrawQuad(getX() + (getWidth() / 2)
					- (handleWidth / 2), lerpedY - (handleHeight / 2),
					handleWidth, handleHeight, isBeingHeld ? handleDownTexture
							: handleUpTexture, Color.white);
		}
			break;
		}
	}

	
	public final Texture getHandleUpTexture() {
		return handleUpTexture;
	}

	public final void setHandleUpTexture(Texture handleUpTexture) {
		this.handleUpTexture = handleUpTexture;
	}

	public final Texture getHandleDownTexture() {
		return handleDownTexture;
	}

	public final void setHandleDownTexture(Texture handleDownTexture) {
		this.handleDownTexture = handleDownTexture;
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
}
