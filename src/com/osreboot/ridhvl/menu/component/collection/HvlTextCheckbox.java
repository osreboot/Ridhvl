package com.osreboot.ridhvl.menu.component.collection;

import org.newdawn.slick.Color;

import com.osreboot.ridhvl.menu.HvlComponent;
import com.osreboot.ridhvl.menu.component.HvlComponentDrawable;
import com.osreboot.ridhvl.painter.painter2d.HvlFontPainter2D;

public class HvlTextCheckbox extends HvlComponent {

	private boolean previousPressed, currentPressed, previousHover, currentHover;
	private boolean checked;
	private HvlComponentDrawable offDrawable, offHoverDrawable, onDrawable, onHoverDrawable;

	private HvlFontPainter2D font;
	private String text;
	private Color color;
	private float scale;

	private float spacing;

	protected HvlTextCheckbox(float wArg, float hArg, boolean checked, HvlComponentDrawable offDrawable, HvlComponentDrawable offHoverDrawable,
			HvlComponentDrawable onDrawable, HvlComponentDrawable onHoverDrawable, HvlFontPainter2D font, String text, Color color) {
		super(wArg, hArg);
		this.checked = checked;
		this.offDrawable = offDrawable;
		this.offHoverDrawable = offHoverDrawable;
		this.onDrawable = onDrawable;
		this.onHoverDrawable = onHoverDrawable;
		this.font = font;
		this.text = text;
		this.color = color;
		scale = 1.0f;
	}

	protected HvlTextCheckbox(float wArg, float hArg, boolean checked, HvlComponentDrawable offDrawable, HvlComponentDrawable hoverDrawable,
			HvlComponentDrawable onDrawable, HvlFontPainter2D font, String text, Color color) {
		super(wArg, hArg);
		this.checked = checked;
		this.offDrawable = offDrawable;
		this.offHoverDrawable = hoverDrawable;
		this.onDrawable = onDrawable;
		this.onHoverDrawable = hoverDrawable;
		this.font = font;
		this.text = text;
		this.color = color;
		scale = 1.0f;
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
			onChanged(checked);
		}

		draw(delta);
	}

	@Override
	public void draw(float delta) {
		if (isChecked()) {
			if (isHovering())
				onHoverDrawable.draw(delta, getX(), getY(), getWidth(), getHeight());
			else
				onDrawable.draw(delta, getX(), getY(), getWidth(), getHeight());
		} else {
			if (isHovering())
				offHoverDrawable.draw(delta, getX(), getY(), getWidth(), getHeight());
			else
				offDrawable.draw(delta, getX(), getY(), getWidth(), getHeight());
		}
		
		font.drawWord(text, getX() + getWidth() + spacing, getY() + (getHeight() / 2) - (font.getFontHeight() / 2), scale, color);
	}

	public void onChanged(boolean state) {

	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
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
	
	public static class Builder {
		private HvlTextCheckbox tr;
		
		public Builder() {
			tr = new HvlTextCheckbox(0, 0, false, null, null, null, null, null, "", Color.white);
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

		public Builder setChecked(boolean checked) {
			tr.setChecked(checked);
			return this;
		}

		public HvlComponentDrawable getOffDrawable() {
			return tr.getOffDrawable();
		}

		public Builder setOffDrawable(HvlComponentDrawable offDrawable) {
			tr.setOffDrawable(offDrawable);
			return this;
		}

		public HvlComponentDrawable getOffHoverDrawable() {
			return tr.getOffHoverDrawable();
		}

		public Builder setOffHoverDrawable(HvlComponentDrawable offHoverDrawable) {
			tr.setOffHoverDrawable(offHoverDrawable);
			return this;
		}

		public HvlComponentDrawable getOnDrawable() {
			return tr.getOnDrawable();
		}

		public Builder setOnDrawable(HvlComponentDrawable onDrawable) {
			tr.setOnDrawable(onDrawable);
			return this;
		}

		public HvlComponentDrawable getOnHoverDrawable() {
			return tr.getOnHoverDrawable();
		}

		public Builder setOnHoverDrawable(HvlComponentDrawable onHoverDrawable) {
			tr.setOnHoverDrawable(onHoverDrawable);
			return this;
		}

		public HvlFontPainter2D getFont() {
			return tr.getFont();
		}

		public Builder setFont(HvlFontPainter2D font) {
			tr.setFont(font);
			return this;
		}

		public String getText() {
			return tr.getText();
		}

		public Builder setText(String text) {
			tr.setText(text);
			return this;
		}

		public Color getColor() {
			return tr.getColor();
		}

		public Builder setColor(Color color) {
			tr.setColor(color);
			return this;
		}

		public float getScale() {
			return tr.getScale();
		}

		public Builder setScale(float scale) {
			tr.setScale(scale);
			return this;
		}

		public float getSpacing() {
			return tr.getSpacing();
		}

		public Builder setSpacing(float spacing) {
			tr.setSpacing(spacing);
			return this;
		}
		
		public HvlTextCheckbox build() {
			return tr;
		}
	}
}
