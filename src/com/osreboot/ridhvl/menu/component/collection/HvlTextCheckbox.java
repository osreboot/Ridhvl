package com.osreboot.ridhvl.menu.component.collection;

import org.newdawn.slick.Color;

import com.osreboot.ridhvl.action.HvlAction2;
import com.osreboot.ridhvl.menu.HvlComponent;
import com.osreboot.ridhvl.menu.HvlComponentDefault;
import com.osreboot.ridhvl.menu.component.HvlCheckbox;
import com.osreboot.ridhvl.menu.component.HvlComponentDrawable;
import com.osreboot.ridhvl.painter.painter2d.HvlFontPainter2D;

public class HvlTextCheckbox extends HvlCheckbox {

	private HvlFontPainter2D font;
	private String text;
	private Color color;
	private float scale;

	private float spacing;

	public HvlTextCheckbox(float xlArg, float ylArg, boolean checkedArg, HvlComponentDrawable offArg, HvlComponentDrawable onArg, HvlFontPainter2D font,
			String text, Color color, float scale) {
		super(xlArg, ylArg, checkedArg, offArg, onArg);
		this.font = font;
		this.text = text;
		this.color = color;
		this.scale = scale;
	}

	public HvlTextCheckbox(float xlArg, float ylArg, boolean checkedArg, HvlComponentDrawable offArg, HvlComponentDrawable hoverArg,
			HvlComponentDrawable onArg, HvlFontPainter2D font, String text, Color color, float scale) {
		super(xlArg, ylArg, checkedArg, offArg, hoverArg, onArg);
		this.font = font;
		this.text = text;
		this.color = color;
		this.scale = scale;
	}

	public HvlTextCheckbox(float xlArg, float ylArg, boolean checkedArg, HvlComponentDrawable offArg, HvlComponentDrawable offHoverArg,
			HvlComponentDrawable onArg, HvlComponentDrawable onHoverArg, HvlFontPainter2D font, String text, Color color, float scale) {
		super(xlArg, ylArg, checkedArg, offArg, offHoverArg, onArg, onHoverArg);
		this.font = font;
		this.text = text;
		this.color = color;
		this.scale = scale;
	}

	public HvlTextCheckbox(float xArg, float yArg, float xlArg, float ylArg, boolean checkedArg, HvlComponentDrawable offArg, HvlComponentDrawable onArg,
			HvlFontPainter2D font, String text, Color color, float scale) {
		super(xArg, yArg, xlArg, ylArg, checkedArg, offArg, onArg);
		this.font = font;
		this.text = text;
		this.color = color;
		this.scale = scale;
	}

	public HvlTextCheckbox(float xArg, float yArg, float xlArg, float ylArg, boolean checkedArg, HvlComponentDrawable offArg, HvlComponentDrawable hoverArg,
			HvlComponentDrawable onArg, HvlFontPainter2D font, String text, Color color, float scale) {
		super(xArg, yArg, xlArg, ylArg, checkedArg, offArg, hoverArg, onArg);
		this.font = font;
		this.text = text;
		this.color = color;
		this.scale = scale;
	}

	public HvlTextCheckbox(float xArg, float yArg, float xlArg, float ylArg, boolean checkedArg, HvlComponentDrawable offArg, HvlComponentDrawable offHoverArg,
			HvlComponentDrawable onArg, HvlComponentDrawable onHoverArg, HvlFontPainter2D font, String text, Color color, float scale) {
		super(xArg, yArg, xlArg, ylArg, checkedArg, offArg, offHoverArg, onArg, onHoverArg);
		this.font = font;
		this.text = text;
		this.color = color;
		this.scale = scale;
	}

	@Override
	public void draw(float delta) {
		super.draw(delta);

		if (font != null && text != null && color != null)
			font.drawWord(text, getX() + getWidth() + spacing, getY() + (getHeight() / 2) - (font.getFontHeight() * scale / 2), scale, color);
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

	public static class Builder {
		private HvlTextCheckbox tr;

		public Builder() {
			if (HvlComponentDefault.hasDefault(HvlTextCheckbox.class))
				tr = HvlComponentDefault.getDefault(HvlTextCheckbox.class).clone();
			else
				tr = new HvlTextCheckbox(0, 0, false, null, null, null, null, "", Color.white, 1.0f);
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

		public Builder setChangedEvent(OnChangedCommand changedEvent) {
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

		public HvlTextCheckbox build() {
			return tr;
		}
	}

	public HvlTextCheckbox clone() {
		HvlTextCheckbox tr = new HvlTextCheckbox(0, 0, false, null, null, null, null, "", Color.white, 1.0f);;
		// HvlComponent
		tr.setX(getX());
		tr.setY(getY());
		tr.setWidth(getWidth());
		tr.setHeight(getHeight());
		tr.setEnabled(isEnabled());
		tr.setVisible(isVisible());
		tr.setUpdateOverride(getUpdateOverride());
		tr.setDrawOverride(getDrawOverride());
		// HvlCheckbox
		tr.setChecked(getChecked());
		tr.setOffDrawable(getOffDrawable());
		tr.setOffHoverDrawable(getOffHoverDrawable());
		tr.setOnDrawable(getOnDrawable());
		tr.setOnHoverDrawable(getOnHoverDrawable());
		// HvlTextCheckbox
		tr.font = font;
		tr.text = text;
		tr.color = color;
		tr.scale = scale;
		tr.spacing = spacing;
		return tr;
	}
}
