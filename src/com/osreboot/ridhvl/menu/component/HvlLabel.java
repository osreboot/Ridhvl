package com.osreboot.ridhvl.menu.component;

import org.newdawn.slick.Color;

import com.osreboot.ridhvl.action.HvlAction2;
import com.osreboot.ridhvl.menu.HvlComponent;
import com.osreboot.ridhvl.menu.HvlComponentDefault;
import com.osreboot.ridhvl.painter.painter2d.HvlFontPainter2D;

public class HvlLabel extends HvlComponent {

	private HvlFontPainter2D font;
	private String text;
	private Color color;
	private float scale;

	public HvlLabel(HvlFontPainter2D fontArg, String textArg, Color colorArg) {
		// The dimensions are 0 because
		// they get immediately reset anyways.
		super(0, 0);
		font = fontArg;
		text = textArg;
		color = colorArg;
		scale = 1.0f;
		updateDimensions();
	}

	public HvlLabel(HvlFontPainter2D fontArg, String textArg, float scaleArg, Color colorArg) {
		// The dimensions are 0 because
		// they get immediately reset anyways.
		super(0, 0);
		font = fontArg;
		text = textArg;
		color = colorArg;
		scale = scaleArg;
		updateDimensions();
	}

	public HvlLabel(float xArg, float yArg, HvlFontPainter2D fontArg, String textArg, Color colorArg) {
		// The dimensions are 0 because
		// they get immediately reset anyways.
		super(xArg, yArg, 0, 0);
		font = fontArg;
		text = textArg;
		color = colorArg;
		scale = 1.0f;
		updateDimensions();
	}

	public HvlLabel(float xArg, float yArg, HvlFontPainter2D fontArg, String textArg, float scaleArg, Color colorArg) {
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
		if (font != null && color != null)
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
			tr = new HvlLabel(null, "", Color.white);
			if (HvlComponentDefault.hasDefault(HvlLabel.class))
				tr = HvlComponentDefault.getDefault(HvlLabel.class).cloneComponent(tr);
		}
		
		public Builder setX(float x) {
			tr.setX(x);
			return this;
		}

		public Builder setY(float y) {
			tr.setY(y);
			return this;
		}

		public Builder setEnabled(boolean enabled) {
			tr.setEnabled(enabled);
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

		public Builder setVisible(boolean visible) {
			tr.setVisible(visible);
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

		public Builder setUpdateOverride(HvlAction2<HvlComponent, Float> updateOverride) {
			tr.setUpdateOverride(updateOverride);
			return this;
		}

		public Builder setDrawOverride(HvlAction2<HvlComponent, Float> drawOverride) {
			tr.setDrawOverride(drawOverride);
			return this;
		}
		
		public HvlLabel build() {
			return tr;
		}
	}
}
