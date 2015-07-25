package com.osreboot.ridhvl.menu.component.collection;

import org.newdawn.slick.Color;

import com.osreboot.ridhvl.menu.HvlComponentDefault;
import com.osreboot.ridhvl.menu.component.HvlButton;
import com.osreboot.ridhvl.menu.component.HvlComponentDrawable;
import com.osreboot.ridhvl.painter.painter2d.HvlFontPainter2D;

public class HvlTextButton extends HvlButton {
	private HvlFontPainter2D font;
	private String text;
	private float textScale;
	private Color textColor;
	private float xAlign, yAlign;

	public HvlTextButton(float xlArg, float ylArg, HvlComponentDrawable offArg, HvlComponentDrawable onArg, HvlFontPainter2D font, String text, Color textColor) {
		super(xlArg, ylArg, offArg, onArg);
		this.font = font;
		this.text = text;
		this.textColor = textColor;
	}
	
	public HvlTextButton(float xlArg, float ylArg, HvlComponentDrawable offArg, HvlComponentDrawable hoverArg, HvlComponentDrawable onArg,
			HvlFontPainter2D font, String text, Color textColor) {
		super(xlArg, ylArg, offArg, hoverArg, onArg);
		this.font = font;
		this.text = text;
		this.textColor = textColor;
	}

	public HvlTextButton(float xArg, float yArg, float xlArg, float ylArg, HvlComponentDrawable offArg, HvlComponentDrawable onArg,
			HvlFontPainter2D fontArg, String textArg, Color colorArg) {
		super(xArg, yArg, xlArg, ylArg, offArg, onArg);
		font = fontArg;
		text = textArg;
		textScale = 1.0f;
		textColor = colorArg;
		xAlign = 0.5f;
		yAlign = 0.5f;
	}

	public HvlTextButton(float xArg, float yArg, float xlArg, float ylArg, HvlComponentDrawable offArg, HvlComponentDrawable hoverArg,
			HvlComponentDrawable onArg, HvlFontPainter2D fontArg, String textArg, Color colorArg) {
		super(xArg, yArg, xlArg, ylArg, offArg, hoverArg, onArg);
		font = fontArg;
		text = textArg;
		textScale = 1.0f;
		textColor = colorArg;
		xAlign = 0.5f;
		yAlign = 0.5f;
	}

	public HvlTextButton(float xlArg, float ylArg, HvlComponentDrawable offArg, HvlComponentDrawable onArg, HvlFontPainter2D font, String text,
			float textScale, Color textColor) {
		super(xlArg, ylArg, offArg, onArg);
		this.font = font;
		this.text = text;
		this.textScale = textScale;
		this.textColor = textColor;
	}

	public HvlTextButton(float xlArg, float ylArg, HvlComponentDrawable offArg, HvlComponentDrawable hoverArg, HvlComponentDrawable onArg,
			HvlFontPainter2D font, String text, float textScale, Color textColor) {
		super(xlArg, ylArg, offArg, hoverArg, onArg);
		this.font = font;
		this.text = text;
		this.textScale = textScale;
		this.textColor = textColor;
	}

	public HvlTextButton(float xArg, float yArg, float xlArg, float ylArg, HvlComponentDrawable offArg, HvlComponentDrawable onArg, HvlFontPainter2D font,
			String text, float textScale, Color textColor) {
		super(xArg, yArg, xlArg, ylArg, offArg, onArg);
		this.font = font;
		this.text = text;
		this.textScale = textScale;
		this.textColor = textColor;
	}

	public HvlTextButton(float xArg, float yArg, float xlArg, float ylArg, HvlComponentDrawable offArg, HvlComponentDrawable hoverArg,
			HvlComponentDrawable onArg, HvlFontPainter2D font, String text, float textScale, Color textColor) {
		super(xArg, yArg, xlArg, ylArg, offArg, hoverArg, onArg);
		this.font = font;
		this.text = text;
		this.textScale = textScale;
		this.textColor = textColor;
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
			if (HvlComponentDefault.hasDefault(HvlTextButton.class))
				tr = HvlComponentDefault.getDefault(HvlTextButton.class).clone();
			else
				tr = new HvlTextButton(0, 0, null, null, null, "", Color.white);
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

		public Builder setFont(HvlFontPainter2D font) {
			tr.setFont(font);
			return this;
		}

		public Builder setHeight(float height) {
			tr.setHeight(height);
			return this;
		}

		public Builder setText(String text) {
			tr.setText(text);
			return this;
		}

		public Builder setEnabled(boolean enabled) {
			tr.setEnabled(enabled);
			return this;
		}

		public Builder setTextScale(float textScale) {
			tr.setTextScale(textScale);
			return this;
		}

		public Builder setVisible(boolean visible) {
			tr.setVisible(visible);
			return this;
		}

		public Builder setTextColor(Color textColor) {
			tr.setTextColor(textColor);
			return this;
		}

		public Builder setxAlign(float xAlign) {
			tr.setxAlign(xAlign);
			return this;
		}

		public Builder setyAlign(float yAlign) {
			tr.setyAlign(yAlign);
			return this;
		}

		public Builder setOffDrawable(HvlComponentDrawable offDrawable) {
			tr.setOffDrawable(offDrawable);
			return this;
		}

		public Builder setHoverDrawable(HvlComponentDrawable hoverDrawable) {
			tr.setHoverDrawable(hoverDrawable);
			return this;
		}

		public Builder setOnDrawable(HvlComponentDrawable onDrawable) {
			tr.setOnDrawable(onDrawable);
			return this;
		}
		
		public Builder setClickedCommand(OnClickedCommand clickedCommand) {
			tr.setClickedCommand(clickedCommand);
			return this;
		}

		public Builder setPressingCommand(OnPressingCommand pressingCommand) {
			tr.setPressingCommand(pressingCommand);
			return this;
		}

		public HvlTextButton build() {
			return tr;
		}
	}

	public HvlTextButton clone() {
		HvlTextButton tr = new HvlTextButton(0, 0, null, null, null, "", Color.white);
		// HvlComponent
		tr.setX(getX());
		tr.setY(getY());
		tr.setWidth(getWidth());
		tr.setHeight(getHeight());
		tr.setEnabled(isEnabled());
		tr.setVisible(isVisible());
		// HvlButton
		tr.setOffDrawable(getOffDrawable());
		tr.setHoverDrawable(getHoverDrawable());
		tr.setOnDrawable(getOnDrawable());
		// HvlTextButton
		tr.font = font;
		tr.text = text;
		tr.textScale = textScale;
		tr.textColor = textColor;
		tr.xAlign = xAlign;
		tr.yAlign = yAlign;
		return tr;
	}
}
