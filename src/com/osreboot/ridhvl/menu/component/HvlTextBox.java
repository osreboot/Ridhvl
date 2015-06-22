package com.osreboot.ridhvl.menu.component;

import java.util.regex.Pattern;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import com.osreboot.ridhvl.menu.HvlComponent;

public abstract class HvlTextBox extends HvlComponent {

	private String text;
	private boolean isFocused;
	private int maxCharacters;
	private boolean forceUppercase, forceLowercase;
	private boolean numbersOnly;
	private String blacklistCharacters;

	public HvlTextBox(float xArg, float yArg, float wArg, float hArg,
			float heightInversionArg, String textArg) {
		super(xArg, yArg, wArg, hArg, heightInversionArg);
		text = textArg;
		isFocused = false;
		maxCharacters = -1;
	}

	@Override
	public void update(float delta) {
		if(!isEnabled())
		{
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
}
