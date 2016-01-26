package com.osreboot.ridhvl.menu.component.collection;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;

import com.osreboot.ridhvl.action.HvlAction2;
import com.osreboot.ridhvl.menu.HvlComponent;
import com.osreboot.ridhvl.menu.HvlComponentDefault;
import com.osreboot.ridhvl.menu.HvlMenu;
import com.osreboot.ridhvl.menu.component.HvlComponentDrawable;
import com.osreboot.ridhvl.menu.component.HvlRadioButton;
import com.osreboot.ridhvl.painter.HvlCursor;
import com.osreboot.ridhvl.painter.painter2d.HvlFontPainter2D;

public class HvlLabeledRadioButton extends HvlRadioButton {

	public static enum HvlTextSide {
		LEFT, RIGHT
	}

	private HvlFontPainter2D font;
	private String text;
	private Color color;
	private float scale;

	private HvlTextSide textSide;
	private float checkWidth, checkHeight;
	private float spacing;

	public HvlLabeledRadioButton(float xlArg, float ylArg, boolean checkedArg, HvlComponentDrawable offArg, HvlComponentDrawable onArg, HvlFontPainter2D font,
			String text, Color color, float scale) {
		super(0, 0, checkedArg, offArg, onArg);
		this.checkWidth = xlArg;
		this.checkHeight = ylArg;
		this.font = font;
		this.text = text;
		this.color = color;
		this.scale = scale;
		this.textSide = HvlTextSide.RIGHT;
	}

	public HvlLabeledRadioButton(float xlArg, float ylArg, boolean checkedArg, HvlComponentDrawable offArg, HvlComponentDrawable hoverArg,
			HvlComponentDrawable onArg, HvlFontPainter2D font, String text, Color color, float scale) {
		super(0, 0, checkedArg, offArg, hoverArg, onArg);
		this.checkWidth = xlArg;
		this.checkHeight = ylArg;
		this.font = font;
		this.text = text;
		this.color = color;
		this.scale = scale;
		this.textSide = HvlTextSide.RIGHT;
	}

	public HvlLabeledRadioButton(float xlArg, float ylArg, boolean checkedArg, HvlComponentDrawable offArg, HvlComponentDrawable offHoverArg,
			HvlComponentDrawable onArg, HvlComponentDrawable onHoverArg, HvlFontPainter2D font, String text, Color color, float scale) {
		super(0, 0, checkedArg, offArg, offHoverArg, onArg, onHoverArg);
		this.checkWidth = xlArg;
		this.checkHeight = ylArg;
		this.font = font;
		this.text = text;
		this.color = color;
		this.scale = scale;
		this.textSide = HvlTextSide.RIGHT;
	}

	public HvlLabeledRadioButton(float xArg, float yArg, float xlArg, float ylArg, boolean checkedArg, HvlComponentDrawable offArg, HvlComponentDrawable onArg,
			HvlFontPainter2D font, String text, Color color, float scale) {
		super(xArg, yArg, 0, 0, checkedArg, offArg, onArg);
		this.checkWidth = xlArg;
		this.checkHeight = ylArg;
		this.font = font;
		this.text = text;
		this.color = color;
		this.scale = scale;
		this.textSide = HvlTextSide.RIGHT;
	}

	public HvlLabeledRadioButton(float xArg, float yArg, float xlArg, float ylArg, boolean checkedArg, HvlComponentDrawable offArg, HvlComponentDrawable hoverArg,
			HvlComponentDrawable onArg, HvlFontPainter2D font, String text, Color color, float scale) {
		super(xArg, yArg, 0, 0, checkedArg, offArg, hoverArg, onArg);
		this.checkWidth = xlArg;
		this.checkHeight = ylArg;
		this.font = font;
		this.text = text;
		this.color = color;
		this.scale = scale;
		this.textSide = HvlTextSide.RIGHT;
	}

	public HvlLabeledRadioButton(float xArg, float yArg, float xlArg, float ylArg, boolean checkedArg, HvlComponentDrawable offArg,
			HvlComponentDrawable offHoverArg, HvlComponentDrawable onArg, HvlComponentDrawable onHoverArg, HvlFontPainter2D font, String text, Color color,
			float scale) {
		super(xArg, yArg, 0, 0, checkedArg, offArg, offHoverArg, onArg, onHoverArg);
		this.checkWidth = xlArg;
		this.checkHeight = ylArg;
		this.font = font;
		this.text = text;
		this.color = color;
		this.scale = scale;
		this.textSide = HvlTextSide.RIGHT;
	}

	@Override
	public boolean isHovering() {
		if (!isEnabled() || !HvlMenu.getCurrent().isInteractable())
			return false;

		if (textSide == HvlTextSide.RIGHT)
			return Mouse.isInsideWindow() && HvlCursor.getCursorX() > getX() && HvlCursor.getCursorY() > getY() && HvlCursor.getCursorX() < getX() + checkWidth
					&& HvlCursor.getCursorY() < getY() + getHeight();
		else
			return Mouse.isInsideWindow() && HvlCursor.getCursorX() > getX() + getWidth() - checkWidth && HvlCursor.getCursorX() < getX() + getWidth()
					&& HvlCursor.getCursorY() > getY() && HvlCursor.getCursorY() < getY() + checkHeight;
	}

	@Override
	public void update(float delta) {
		super.update(delta);

		// Dimension overrides to respond to rearranging properly.
		setWidth((font.getLineWidth(text) * scale) + checkWidth + spacing);
		setHeight(Math.max(font.getLineHeight(text) * scale, checkHeight));
	}

	@Override
	public void draw(float delta) {

		if (getChecked()) {
			if (isHovering()) {
				if (onHoverDrawable != null)
					onHoverDrawable.draw(delta, textSide == HvlTextSide.LEFT ? getX() + getWidth() - checkWidth : getX(), getY(), checkWidth, checkHeight);
			} else {
				if (onDrawable != null)
					onDrawable.draw(delta, textSide == HvlTextSide.LEFT ? getX() + getWidth() - checkWidth : getX(), getY(), checkWidth, checkHeight);
			}
		} else {
			if (isHovering()) {
				if (offHoverDrawable != null)
					offHoverDrawable.draw(delta, textSide == HvlTextSide.LEFT ? getX() + getWidth() - checkWidth : getX(), getY(), checkWidth, checkHeight);
			} else {
				if (offDrawable != null)
					offDrawable.draw(delta, textSide == HvlTextSide.LEFT ? getX() + getWidth() - checkWidth : getX(), getY(), checkWidth, checkHeight);
			}
		}

		if (font != null && text != null && color != null)
			font.drawWord(text, textSide == HvlTextSide.RIGHT ? getX() + checkWidth + spacing : getX(), getY() + (getHeight() / 2)
					- (font.getLineHeight(text) * scale / 2), color, scale);
	}

	public void onChanged(boolean state) {

	}

	public HvlFontPainter2D getFont() {
		return font;
	}

	public void setFont(HvlFontPainter2D font) {
		this.font = font;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public float getScale() {
		return scale;
	}

	public void setScale(float scale) {
		this.scale = scale;
	}

	public float getSpacing() {
		return spacing;
	}

	public void setSpacing(float spacing) {
		this.spacing = spacing;
	}

	public float getCheckWidth() {
		return checkWidth;
	}

	public void setCheckWidth(float checkWidth) {
		this.checkWidth = checkWidth;
	}

	public float getCheckHeight() {
		return checkHeight;
	}

	public void setCheckHeight(float checkHeight) {
		this.checkHeight = checkHeight;
	}

	public HvlTextSide getTextSide() {
		return textSide;
	}

	public void setTextSide(HvlTextSide textSide) {
		this.textSide = textSide;
	}

	public static class Builder {
		private HvlLabeledRadioButton tr;

		public Builder() {
			tr = new HvlLabeledRadioButton(0, 0, false, null, null, null, null, "", Color.white, 1.0f);
			if (HvlComponentDefault.hasDefault(HvlLabeledRadioButton.class))
				tr = HvlComponentDefault.getDefault(HvlLabeledRadioButton.class).cloneComponent(tr);
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
			tr.setCheckWidth(width);
			return this;
		}

		public Builder setHeight(float height) {
			tr.setCheckHeight(height);
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

		public Builder setChecked(boolean checked) {
			tr.setChecked(checked);
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

		public Builder setFont(HvlFontPainter2D font) {
			tr.setFont(font);
			return this;
		}

		public Builder setText(String text) {
			tr.setText(text);
			return this;
		}

		public Builder setColor(Color color) {
			tr.setColor(color);
			return this;
		}

		public Builder setScale(float scale) {
			tr.setScale(scale);
			return this;
		}

		public Builder setSpacing(float spacing) {
			tr.setSpacing(spacing);
			return this;
		}

		public Builder setChangedEvent(HvlAction2<HvlRadioButton, Boolean> changedEvent) {
			tr.setChangedCommand(changedEvent);
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

		public Builder setCheckWidth(float checkWidth) {
			tr.setCheckWidth(checkWidth);
			return this;
		}

		public Builder setCheckHeight(float checkHeight) {
			tr.setCheckHeight(checkHeight);
			return this;
		}

		public Builder setTextSide(HvlTextSide textSide) {
			tr.setTextSide(textSide);
			return this;
		}

		public HvlLabeledRadioButton build() {
			return tr;
		}
	}
}
