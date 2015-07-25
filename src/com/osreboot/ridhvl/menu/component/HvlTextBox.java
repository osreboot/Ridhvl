package com.osreboot.ridhvl.menu.component;

import java.util.regex.Pattern;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;

import com.osreboot.ridhvl.menu.HvlComponent;
import com.osreboot.ridhvl.menu.HvlComponentDefault;
import com.osreboot.ridhvl.painter.painter2d.HvlFontPainter2D;

public class HvlTextBox extends HvlComponent {

	public static abstract class OnTextChangedCommand {
		public abstract void run(HvlTextBox callingTextBox, String text);
	}
	
	private HvlComponentDrawable focusedDrawable, unfocusedDrawable;
	private float offsetX, offsetY;
	private float textScale;
	private Color textColor;
	private HvlFontPainter2D font;
	private String text;
	private String pText;
	private boolean isFocused;
	private int maxCharacters;
	private boolean forceUppercase, forceLowercase;
	private boolean numbersOnly;
	private String blacklistCharacters;
	
	private OnTextChangedCommand textChangedCommand;

	public HvlTextBox(float wArg, float hArg, String textArg, HvlComponentDrawable focusedArg, HvlComponentDrawable unfocusedArg, HvlFontPainter2D fontArg) {
		super(wArg, hArg);
		text = textArg;
		isFocused = false;
		maxCharacters = -1;
		focusedDrawable = focusedArg;
		unfocusedDrawable = unfocusedArg;
		font = fontArg;
		textColor = Color.black;
		textScale = 1.0f;
		pText = textArg;
		blacklistCharacters = "";
	}

	public HvlTextBox(float xArg, float yArg, float wArg, float hArg, String textArg, HvlComponentDrawable focusedArg, HvlComponentDrawable unfocusedArg,
			HvlFontPainter2D fontArg) {
		super(xArg, yArg, wArg, hArg);
		text = textArg;
		isFocused = false;
		maxCharacters = -1;
		focusedDrawable = focusedArg;
		unfocusedDrawable = unfocusedArg;
		font = fontArg;
		textColor = Color.black;
		textScale = 1.0f;
		pText = textArg;
		blacklistCharacters = "";
	}

	public HvlTextBox(float wArg, float hArg, HvlComponentDrawable focusedDrawable, HvlComponentDrawable unfocusedDrawable, float textScale, Color textColor,
			HvlFontPainter2D font, String text) {
		super(wArg, hArg);
		this.focusedDrawable = focusedDrawable;
		this.unfocusedDrawable = unfocusedDrawable;
		this.textScale = textScale;
		this.textColor = textColor;
		this.font = font;
		this.text = text;
		isFocused = false;
		maxCharacters = -1;
		pText = text;
		blacklistCharacters = "";
	}

	public HvlTextBox(float xArg, float yArg, float wArg, float hArg, HvlComponentDrawable focusedDrawable, HvlComponentDrawable unfocusedDrawable,
			float textScale, Color textColor, HvlFontPainter2D font, String text) {
		super(xArg, yArg, wArg, hArg);
		this.focusedDrawable = focusedDrawable;
		this.unfocusedDrawable = unfocusedDrawable;
		this.textScale = textScale;
		this.textColor = textColor;
		this.font = font;
		this.text = text;
		isFocused = false;
		maxCharacters = -1;
		pText = text;
		blacklistCharacters = "";
	}

	@Override
	public void update(float delta) {
		if (!isEnabled()) {
			isFocused = false;
			return;
		}

		if (Mouse.isButtonDown(0)) {
			isFocused = isBeingPressed(0);
		}

		if (isFocused) {
			Keyboard.poll();
			if (Keyboard.getNumKeyboardEvents() > 0) {
				while (Keyboard.next()) {
					if (Keyboard.getEventKeyState()) {
						Character keyChar = Keyboard.getEventCharacter();
						int key = Keyboard.getEventKey();
						if (key == Keyboard.KEY_LSHIFT || key == Keyboard.KEY_RSHIFT || key == Keyboard.KEY_LCONTROL || key == Keyboard.KEY_RCONTROL
								|| key == Keyboard.KEY_LMENU || key == Keyboard.KEY_RMENU || key == Keyboard.KEY_LMETA || key == Keyboard.KEY_RMETA)
							continue;

						if (Keyboard.getEventKey() == Keyboard.KEY_BACK) {
							if (text.length() > 0)
								text = text.substring(0, Math.max(text.length() - 1, 0));
						} else {
							text = text.concat(keyChar.toString());
						}
					}
				}
			}
		}

		if (maxCharacters > 0) {
			if (text.length() > maxCharacters)
				text = text.substring(0, maxCharacters);
		}

		if (forceLowercase)
			text = text.toLowerCase();
		if (forceUppercase)
			text = text.toUpperCase();
		if (numbersOnly)
			text = text.replaceAll("[^\\d-]", "");
		if (blacklistCharacters != null && !blacklistCharacters.isEmpty())
			text = text.replaceAll(String.format("[%s]", Pattern.quote(blacklistCharacters)), "");

		if (!pText.equals(text))
		{
			if (textChangedCommand != null)
				textChangedCommand.run(this, text);
		}

		pText = text;
	}

	@Override
	public void draw(float delta) {
		if (isFocused) {
			if (focusedDrawable != null)
				focusedDrawable.draw(delta, getX(), getY(), getWidth(), getHeight());
		} else {
			if (unfocusedDrawable != null)
				unfocusedDrawable.draw(delta, getX(), getY(), getWidth(), getHeight());
		}
		if (font != null && text != null && textColor != null)
			font.drawWord(getText(), getX() + offsetX, getY() + offsetY, textScale, textColor);
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public boolean isFocused() {
		return isFocused;
	}

	public void setFocused(boolean isFocused) {
		this.isFocused = isFocused;
	}

	public int getMaxCharacters() {
		return maxCharacters;
	}

	public void setMaxCharacters(int maxCharacters) {
		this.maxCharacters = maxCharacters;
	}

	public boolean isForceUppercase() {
		return forceUppercase;
	}

	public void setForceUppercase(boolean forceUppercase) {
		this.forceUppercase = forceUppercase;
	}

	public boolean isForceLowercase() {
		return forceLowercase;
	}

	public void setForceLowercase(boolean forceLowercase) {
		this.forceLowercase = forceLowercase;
	}

	public boolean isNumbersOnly() {
		return numbersOnly;
	}

	public void setNumbersOnly(boolean numbersOnly) {
		this.numbersOnly = numbersOnly;
	}

	public String getBlacklistCharacters() {
		return blacklistCharacters;
	}

	public void setBlacklistCharacters(String blacklistCharacters) {
		this.blacklistCharacters = blacklistCharacters;
	}

	public HvlComponentDrawable getFocusedDrawable() {
		return focusedDrawable;
	}

	public void setFocusedDrawable(HvlComponentDrawable focusedDrawable) {
		this.focusedDrawable = focusedDrawable;
	}

	public HvlComponentDrawable getUnfocusedDrawable() {
		return unfocusedDrawable;
	}

	public void setUnfocusedDrawable(HvlComponentDrawable unfocusedDrawable) {
		this.unfocusedDrawable = unfocusedDrawable;
	}

	public float getOffsetX() {
		return offsetX;
	}

	public void setOffsetX(float offsetX) {
		this.offsetX = offsetX;
	}

	public float getOffsetY() {
		return offsetY;
	}

	public void setOffsetY(float offsetY) {
		this.offsetY = offsetY;
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

	public HvlFontPainter2D getFont() {
		return font;
	}

	public void setFont(HvlFontPainter2D font) {
		this.font = font;
	}

	public OnTextChangedCommand getTextChangedCommand() {
		return textChangedCommand;
	}

	public void setTextChangedCommand(OnTextChangedCommand textChangedCommand) {
		this.textChangedCommand = textChangedCommand;
	}

	public static class Builder {
		private HvlTextBox tr;

		public Builder() {
			if (HvlComponentDefault.hasDefault(HvlTextBox.class))
				tr =  HvlComponentDefault.getDefault(HvlTextBox.class).clone();
			else
				tr = new HvlTextBox(0, 0, "", null, null, null);
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

		public Builder setText(String text) {
			tr.setText(text);
			return this;
		}

		public Builder setMaxCharacters(int maxCharacters) {
			tr.setMaxCharacters(maxCharacters);
			return this;
		}

		public Builder setForceUppercase(boolean forceUppercase) {
			tr.setForceUppercase(forceUppercase);
			return this;
		}

		public Builder setForceLowercase(boolean forceLowercase) {
			tr.setForceLowercase(forceLowercase);
			return this;
		}

		public Builder setNumbersOnly(boolean numbersOnly) {
			tr.setNumbersOnly(numbersOnly);
			return this;
		}

		public Builder setBlacklistCharacters(String blacklistCharacters) {
			tr.setBlacklistCharacters(blacklistCharacters);
			return this;
		}

		public Builder setFocusedDrawable(HvlComponentDrawable focusedDrawable) {
			tr.setFocusedDrawable(focusedDrawable);
			return this;
		}

		public Builder setUnfocusedDrawable(HvlComponentDrawable unfocusedDrawable) {
			tr.setUnfocusedDrawable(unfocusedDrawable);
			return this;
		}

		public Builder setOffsetX(float offsetX) {
			tr.setOffsetX(offsetX);
			return this;
		}

		public Builder setOffsetY(float offsetY) {
			tr.setOffsetY(offsetY);
			return this;
		}

		public Builder setTextScale(float textScale) {
			tr.setTextScale(textScale);
			return this;
		}

		public Builder setTextColor(Color textColor) {
			tr.setTextColor(textColor);
			return this;
		}

		public Builder setFont(HvlFontPainter2D font) {
			tr.setFont(font);
			return this;
		}

		public Builder setTextChangedCommand(OnTextChangedCommand textChangedCommand) {
			tr.setTextChangedCommand(textChangedCommand);
			return this;
		}

		public HvlTextBox build() {
			return tr;
		}
	}

	public HvlTextBox clone() {
		HvlTextBox tr = new HvlTextBox(0, 0, "", null, null, null);
		// HvlComponent
		tr.setX(getX());
		tr.setY(getY());
		tr.setWidth(getWidth());
		tr.setHeight(getHeight());
		tr.setEnabled(isEnabled());
		tr.setVisible(isVisible());
		// HvlTextBox
		tr.focusedDrawable = focusedDrawable;
		tr.unfocusedDrawable = unfocusedDrawable;
		tr.offsetX = offsetX;
		tr.offsetY = offsetY;
		tr.textScale = textScale;
		tr.textColor = textColor;
		tr.font = font;
		tr.text = text;
		tr.isFocused = isFocused;
		tr.maxCharacters = maxCharacters;
		tr.forceUppercase = forceUppercase;
		tr.forceLowercase = forceLowercase;
		tr.numbersOnly = numbersOnly;
		tr.blacklistCharacters = blacklistCharacters;
		return tr;
	}
}
