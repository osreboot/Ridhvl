package com.osreboot.ridhvl.menu.component.collection;

import org.newdawn.slick.Color;

import com.osreboot.ridhvl.menu.component.HvlButton;
import com.osreboot.ridhvl.menu.component.HvlComponentDrawable;
import com.osreboot.ridhvl.painter.painter2d.HvlFontPainter2D;

public class HvlTextButton extends HvlButton {
	private HvlFontPainter2D font;
	private String text;
	private float textScale;
	private Color textColor;
	private float xAlign, yAlign;

	protected HvlTextButton(float xArg, float yArg, float xlArg, float ylArg, HvlComponentDrawable offArg, HvlComponentDrawable onArg,
			HvlFontPainter2D fontArg, String textArg) {
		super(xArg, yArg, xlArg, ylArg, offArg, onArg);
		font = fontArg;
		text = textArg;
		textScale = 1.0f;
		textColor = Color.white;
		xAlign = 0.5f;
		yAlign = 0.5f;
	}

	protected HvlTextButton(float xArg, float yArg, float xlArg, float ylArg, HvlComponentDrawable offArg, HvlComponentDrawable hoverArg,
			HvlComponentDrawable onArg, HvlFontPainter2D fontArg, String textArg) {
		super(xArg, yArg, xlArg, ylArg, offArg, hoverArg, onArg);
		font = fontArg;
		text = textArg;
		textScale = 1.0f;
		textColor = Color.white;
		xAlign = 0.5f;
		yAlign = 0.5f;
	}

	@Override
	public void draw(float delta) {
		super.draw(delta);
		float xOffset = (getWidth() - (font.getLineWidth(text) * textScale)) * xAlign;
		float yOffset = (getHeight() - (font.getFontHeight() * textScale)) * yAlign;
		font.drawWord(text, getX() + xOffset, getY() + yOffset, textScale, textColor);
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

	public float getTextScale() {
		return textScale;
	}

	public void setTextScale(float textScale) {
		this.textScale = textScale;
	}

	public Color getTextColor() {
		return textColor;
	}

	public void setTextColor(Color textColor) {
		this.textColor = textColor;
	}

	public float getxAlign() {
		return xAlign;
	}

	public void setxAlign(float xAlign) {
		this.xAlign = xAlign;
	}

	public float getyAlign() {
		return yAlign;
	}

	public void setyAlign(float yAlign) {
		this.yAlign = yAlign;
	}

	public static class Builder {
		private HvlTextButton tr;

		public Builder() {
			tr = new HvlTextButton(0, 0, 0, 0, null, null, null, "");
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

		public HvlFontPainter2D getFont() {
			return tr.getFont();
		}

		public float getHeight() {
			return tr.getHeight();
		}

		public Builder setFont(HvlFontPainter2D font) {
			tr.setFont(font);
			return this;
		}

		public Builder setHeight(float height) {
			tr.setHeight(height);
			return this;
		}

		public String getText() {
			return tr.getText();
		}

		public boolean isEnabled() {
			return tr.isEnabled();
		}

		public Builder setText(String text) {
			tr.setText(text);
			return this;
		}

		public Builder setEnabled(boolean enabled) {
			tr.setEnabled(enabled);
			return this;
		}

		public float getTextScale() {
			return tr.getTextScale();
		}

		public Builder setTextScale(float textScale) {
			tr.setTextScale(textScale);
			return this;
		}

		public boolean isVisible() {
			return tr.isVisible();
		}

		public Builder setVisible(boolean visible) {
			tr.setVisible(visible);
			return this;
		}

		public Color getTextColor() {
			return tr.getTextColor();
		}

		public Builder setTextColor(Color textColor) {
			tr.setTextColor(textColor);
			return this;
		}

		public float getxAlign() {
			return tr.getxAlign();
		}

		public Builder setxAlign(float xAlign) {
			tr.setxAlign(xAlign);
			return this;
		}

		public float getyAlign() {
			return tr.getyAlign();
		}

		public HvlComponentDrawable getOffDrawable() {
			return tr.getOffDrawable();
		}

		public Builder setyAlign(float yAlign) {
			tr.setyAlign(yAlign);
			return this;
		}

		public Builder setOffDrawable(HvlComponentDrawable offDrawable) {
			tr.setOffDrawable(offDrawable);
			return this;
		}

		public HvlComponentDrawable getHoverDrawable() {
			return tr.getHoverDrawable();
		}

		public Builder setHoverDrawable(HvlComponentDrawable hoverDrawable) {
			tr.setHoverDrawable(hoverDrawable);
			return this;
		}

		public HvlComponentDrawable getOnDrawable() {
			return tr.getOnDrawable();
		}

		public Builder setOnDrawable(HvlComponentDrawable onDrawable) {
			tr.setOnDrawable(onDrawable);
			return this;
		}
		
		public HvlTextButton build() {
			return tr;
		}
	}
}
