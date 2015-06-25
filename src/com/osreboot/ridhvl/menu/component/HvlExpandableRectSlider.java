package com.osreboot.ridhvl.menu.component;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

import com.osreboot.ridhvl.menu.HvlComponent;
import com.osreboot.ridhvl.painter.HvlCursor;
import com.osreboot.ridhvl.painter.painter2d.HvlExpandingRectangle;
import com.osreboot.ridhvl.painter.painter2d.HvlPainter2D;

public class HvlExpandableRectSlider extends HvlComponent {
	public enum SliderDirection {
		VERTICAL, HORIZONTAL
	}

	private HvlExpandingRectangle backgroundRect;
	private Texture handleUpTexture, handleDownTexture;
	private SliderDirection direction;
	private float value;
	private float handleHeight, handleWidth;
	private float handleStartOffset, handleEndOffset;
	private float snapInterval;

	private boolean isBeingHeld;

	public HvlExpandableRectSlider(float xArg, float yArg, float wArg, float hArg,
			float heightInversionArg, HvlExpandingRectangle backgroundArg, Texture handleArg,
			SliderDirection dirArg, float handleHeightArg,
			float handleWidthArg, float valueArg) {
		super(xArg, yArg, wArg, hArg, heightInversionArg);

		backgroundRect = backgroundArg;
		handleUpTexture = handleArg;
		handleDownTexture = handleArg;
		direction = dirArg;
		handleHeight = handleHeightArg;
		handleWidth = handleWidthArg;
		value = valueArg;
	}

	public HvlExpandableRectSlider(float xArg, float yArg, float wArg, float hArg,
			float heightInversionArg, HvlExpandingRectangle backgroundArg,
			Texture handleUpArg, Texture handleDownArg, SliderDirection dirArg,
			float handleHeightArg, float handleWidthArg, float valueArg) {
		super(xArg, yArg, wArg, hArg, heightInversionArg);

		backgroundRect = backgroundArg;
		handleUpTexture = handleUpArg;
		handleDownTexture = handleDownArg;
		direction = dirArg;
		handleHeight = handleHeightArg;
		handleWidth = handleWidthArg;
		value = valueArg;
	}

	@Override
	public void update(float delta) {
		switch (direction) {
		case HORIZONTAL: {
			float minX = getX() + handleStartOffset;
			float maxX = getX() + getWidth() - handleWidth - handleEndOffset;
			float handleMinX = minX + (value * (maxX - minX))
					- (handleWidth / 2);
			float handleMaxX = handleMinX + (2 * handleWidth);

			float handleMinY = getY() + (getHeight() / 2) - (handleHeight / 2);
			float handleMaxY = getY() + (getHeight() / 2) + (handleHeight / 2);

			if (Mouse.isButtonDown(0) &&  HvlCursor.getCursorX() > handleMinX
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

		if (!Mouse.isButtonDown(0)) {
			value = snapInterval * Math.round(value / snapInterval);
		}

		value = Math.max(0.0f, Math.min(value, 1.0f));
	}

	@Override
	public void draw(float delta) {
		backgroundRect.setX(getX());
		backgroundRect.setY(getY());
		backgroundRect.setTotalWidth(getWidth());
		backgroundRect.setTotalHeight(getHeight());
		backgroundRect.draw();
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

	public HvlExpandingRectangle getBackgroundTexture() {
		return backgroundRect;
	}

	public void setBackgroundTexture(HvlExpandingRectangle backgroundTexture) {
		this.backgroundRect = backgroundTexture;
	}

	public Texture getHandleUpTexture() {
		return handleUpTexture;
	}

	public void setHandleUpTexture(Texture handleUpTexture) {
		this.handleUpTexture = handleUpTexture;
	}

	public Texture getHandleDownTexture() {
		return handleDownTexture;
	}

	public void setHandleDownTexture(Texture handleDownTexture) {
		this.handleDownTexture = handleDownTexture;
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

	public float getSnapInterval() {
		return snapInterval;
	}

	public void setSnapInterval(float snapInterval) {
		this.snapInterval = snapInterval;
	}
}
