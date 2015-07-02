package com.osreboot.ridhvl.menu.component;

import java.util.regex.Pattern;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;

import com.osreboot.ridhvl.menu.HvlComponent;
import com.osreboot.ridhvl.painter.painter2d.HvlFontPainter2D;

public class HvlTextBox extends HvlComponent {

	private HvlComponentDrawable focusedDrawable, unfocusedDrawable;
	private float offsetX, offsetY;
	private float textScale;
	private Color textColor;
	private HvlFontPainter2D fontPainter;
	private String text;
	private String pText;
	private boolean isFocused;
	private int maxCharacters;
	private boolean forceUppercase, forceLowercase;
	private boolean numbersOnly;
	private String blacklistCharacters;

	public HvlTextBox(float xArg, float yArg, float wArg, float hArg,
			String textArg, HvlComponentDrawable focusedArg,
			HvlComponentDrawable unfocusedArg, HvlFontPainter2D fontArg) {
		super(xArg, yArg, wArg, hArg);
		text = textArg;
		isFocused = false;
		maxCharacters = -1;
		focusedDrawable = focusedArg;
		unfocusedDrawable = unfocusedArg;
		fontPainter = fontArg;
		textColor = Color.black;
		textScale = 1.0f;
		pText = textArg;
	}

	public void onTextChanged(String text) {
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
						if (key == Keyboard.KEY_LSHIFT
								|| key == Keyboard.KEY_RSHIFT
								|| key == Keyboard.KEY_LCONTROL
								|| key == Keyboard.KEY_RCONTROL
								|| key == Keyboard.KEY_LMENU
								|| key == Keyboard.KEY_RMENU
								|| key == Keyboard.KEY_LMETA
								|| key == Keyboard.KEY_RMETA)
							continue;

						if (Keyboard.getEventKey() == Keyboard.KEY_BACK) {
							if (text.length() > 0)
								text = text.substring(0,
										Math.max(text.length() - 1, 0));
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
			text = text.replaceAll(
					String.format("[%s]", Pattern.quote(blacklistCharacters)),
					"");
		
		if (!pText.equals(text))
			onTextChanged(text);
		
		pText = text;
	}
	
	@Override
	public void draw(float delta)
	{		
		if (isFocused)
		{
			focusedDrawable.draw(delta, getX(), getY(), getWidth(), getHeight());
		}
		else
		{
			unfocusedDrawable.draw(delta, getX(), getY(), getWidth(), getHeight());
		}
		fontPainter.drawWord(getText(), getX() + offsetX, getY() + offsetY,
				textScale, textColor);
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

	public HvlFontPainter2D getFontPainter() {
		return fontPainter;
	}

	public void setFontPainter(HvlFontPainter2D fontPainter) {
		this.fontPainter = fontPainter;
	}
}
