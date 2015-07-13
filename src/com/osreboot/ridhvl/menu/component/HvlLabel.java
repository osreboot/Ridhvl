package com.osreboot.ridhvl.menu.component;

import org.newdawn.slick.Color;

import com.osreboot.ridhvl.menu.HvlComponent;
import com.osreboot.ridhvl.menu.HvlComponentDefault;
import com.osreboot.ridhvl.painter.painter2d.HvlFontPainter2D;

public class HvlLabel extends HvlComponent {

	private HvlFontPainter2D font;
	private String text;
	private Color color;
	private float scale;

	protected HvlLabel(HvlFontPainter2D fontArg, String textArg, Color colorArg) {
		// The dimensions are 0 because
		// they get immediately reset anyways.
		super(0, 0);
		font = fontArg;
		text = textArg;
		color = colorArg;
		scale = 1.0f;
		updateDimensions();
	}

	protected HvlLabel(HvlFontPainter2D fontArg, String textArg, Color colorArg, float scaleArg) {
		// The dimensions are 0 because
		// they get immediately reset anyways.
		super(0, 0);
		font = fontArg;
		text = textArg;
		color = colorArg;
		scale = scaleArg;
		updateDimensions();
	}

	protected HvlLabel(float xArg, float yArg, HvlFontPainter2D fontArg, String textArg, Color colorArg) {
		// The dimensions are 0 because
		// they get immediately reset anyways.
		super(xArg, yArg, 0, 0);
		font = fontArg;
		text = textArg;
		color = colorArg;
		scale = 1.0f;
		updateDimensions();
	}

	protected HvlLabel(float xArg, float yArg, HvlFontPainter2D fontArg, String textArg, Color colorArg, float scaleArg) {
		// The dimensions are 0 because
		// they get immediately reset anyways.
		super(xArg, yArg, 0, 0);
		font = fontArg;
		text = textArg;
		color = colorArg;
		scale = scaleArg;
		updateDimensions();
	}

	@Override
	public void draw(float delta) {
		updateDimensions(); // Force the width and height.
		font.drawWord(text, getX(), getY(), scale, color);
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

	private void updateDimensions() {
		if (font == null) return;
		this.setWidth(font.getLineWidth(text) * scale);
		this.setHeight(font.getFontHeight() * scale);
	}
	
	// NOTE: There aren't any width and height methods because
	// they're set by the component
	public static class Builder
	{
		private HvlLabel tr;

		public Builder() {
			if (HvlComponentDefault.hasDefault(HvlLabel.class))
				tr = HvlComponentDefault.getDefault(HvlLabel.class).clone();
			else
				tr = new HvlLabel(null, "", Color.white);
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

		public HvlFontPainter2D getFont() {
			return tr.getFont();
		}

		public boolean isEnabled() {
			return tr.isEnabled();
		}

		public Builder setEnabled(boolean enabled) {
			tr.setEnabled(enabled);
			return this;
		}

		public Builder setFont(HvlFontPainter2D font) {
			tr.setFont(font);
			return this;
		}

		public String getText() {
			return tr.getText();
		}

		public boolean isVisible() {
			return tr.isVisible();
		}

		public Builder setText(String text) {
			tr.setText(text);
			return this;
		}

		public Builder setVisible(boolean visible) {
			tr.setVisible(visible);
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
		
		public HvlLabel build() {
			return tr;
		}
	}
	
	public HvlLabel clone() {
		HvlLabel tr = new HvlLabel(null, "", Color.white);
		// HvlComponent
		tr.setX(getX());
		tr.setY(getY());
		tr.setWidth(getWidth());
		tr.setHeight(getHeight());
		tr.setEnabled(isEnabled());
		tr.setVisible(isVisible());
		// HvlLabel
		tr.font = font;
		tr.text = text;
		tr.color = color;
		tr.scale = scale;
		return tr;
	}
}
