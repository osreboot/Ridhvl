package com.osreboot.ridhvl.menu.component;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

import com.osreboot.ridhvl.menu.HvlComponent;
import com.osreboot.ridhvl.painter.painter2d.HvlPainter2D;

public class HvlTextureSlider extends HvlComponent {
	public enum SliderDirection {
		VERTICAL, HORIZONTAL
	}

	private Texture backgroundTexture;
	private Texture handleUpTexture, handleDownTexture;
	private SliderDirection direction;
	private float value;
	private float handleHeight, handleWidth;
	private float handleStartOffset, handleEndOffset;

	private boolean isBeingHeld;

	public HvlTextureSlider(float xArg, float yArg, float wArg, float hArg,
			float heightInversionArg, Texture backgroundArg, Texture handleArg,
			SliderDirection dirArg, float handleHeightArg,
			float handleWidthArg, float valueArg) {
		super(xArg, yArg, wArg, hArg, heightInversionArg);

		backgroundTexture = backgroundArg;
		handleUpTexture = handleArg;
		handleDownTexture = handleArg;
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
			float handleMinX = minX + (value * (maxX - minX));
			float handleMaxX = handleMinX + handleWidth;

			float handleMinY = getY() + (getHeight() / 2) - (handleHeight / 2);
			float handleMaxY = getY() + (getHeight() / 2) + (handleHeight / 2);

			if (Mouse.isButtonDown(0) && Mouse.getX() > handleMinX
					&& Mouse.getX() < handleMaxX
					&& (getHeightInversion() - Mouse.getY()) > handleMinY
					&& (getHeightInversion() - Mouse.getY()) < handleMaxY) {
				isBeingHeld = true;
			} else if (!Mouse.isButtonDown(0)) {
				isBeingHeld = false;
			}
		}
			break;
		case VERTICAL: {
			float minY = getY() + handleStartOffset;
			float maxY = getY() + getHeight() - handleHeight - handleEndOffset;
			float handleMinY = minY + (value * (maxY - minY));
			float handleMaxY = handleMinY + handleHeight;

			float handleMinX = getX() + (getWidth() / 2) - (handleWidth / 2);
			float handleMaxX = getX() + (getWidth() / 2) + (handleWidth / 2);
			
			if (Mouse.isButtonDown(0) && Mouse.getX() > handleMinX
					&& Mouse.getX() < handleMaxX
					&& (getHeightInversion() - Mouse.getY()) > handleMinY
					&& (getHeightInversion() - Mouse.getY()) < handleMaxY) {
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
				float maxX = getX() + getWidth() - handleWidth
						- handleEndOffset;
				float adjusted = Mouse.getX() - minX;
				value = adjusted / (maxX - minX);
				value = Math.max(0, Math.min(value, 1.0f));
			}
				break;
			case VERTICAL: {
				float minY = getY() + handleStartOffset;
				float maxY = getY() + getHeight() - handleHeight
						- handleEndOffset;
				float adjusted = (getHeightInversion() - Mouse.getY()) - minY;
				value = adjusted / (maxY - minY);
				value = Math.max(0, Math.min(value, 1.0f));
			}
				break;
			}
		}
	}

	@Override
	public void draw(float delta) {
		HvlPainter2D.hvlDrawQuad(getX(), getY(), getWidth(), getHeight(),
				backgroundTexture, Color.white);
		switch (direction) {
		case HORIZONTAL: {
			float min = getX() + handleStartOffset;
			float max = getX() + getWidth() - handleWidth - handleEndOffset;
			float lerpedX = min + (value * (max - min));

			HvlPainter2D.hvlDrawQuad(lerpedX, getY() + (getHeight() / 2)
					- (handleHeight / 2), handleWidth, handleHeight,
					handleUpTexture, Color.white);
		}
			break;
		case VERTICAL: {
			float min = getY() + handleStartOffset;
			float max = getY() + getHeight() - handleHeight - handleEndOffset;
			float lerpedY = min + (value * (max - min));
			HvlPainter2D.hvlDrawQuad(getX() + (getWidth() / 2)
					- (handleWidth / 2), lerpedY, handleWidth, handleHeight,
					handleUpTexture, Color.white);
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
}
