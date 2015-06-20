package com.osreboot.ridhvl.menu.component;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import com.osreboot.ridhvl.menu.HvlComponent;

public abstract class HvlTextBox extends HvlComponent {

	private String text;
	private boolean isFocused;

	public HvlTextBox(float xArg, float yArg, float wArg, float hArg,
			float heightInversionArg, String textArg) {
		super(xArg, yArg, wArg, hArg, heightInversionArg);
		text = textArg;
		isFocused = false;
	}

	@Override
	public void update(float delta) {
		if (Mouse.isButtonDown(0)) {
			isFocused = isBeingPressed(0);
		}

		if (isFocused) {
			Keyboard.poll();
			if (Keyboard.getNumKeyboardEvents() > 0) {
				while (Keyboard.next()) {
					if (Keyboard.getEventKeyState()) {
						Character key = Keyboard.getEventCharacter();
						text = text.concat(key.toString());
						if (Keyboard.getEventKey() == Keyboard.KEY_BACK) {
							if (text.length() > 0)
								text = text.substring(0, Math.max(text.length() - 2, 0));
						}
					}
				}
			}
		}
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
}
