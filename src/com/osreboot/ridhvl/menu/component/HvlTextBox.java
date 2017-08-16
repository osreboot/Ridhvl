package com.osreboot.ridhvl.menu.component;

import java.util.regex.Pattern;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;

import com.osreboot.ridhvl.action.HvlAction2;
import com.osreboot.ridhvl.action.HvlEvent2;
import com.osreboot.ridhvl.menu.HvlComponent;
import com.osreboot.ridhvl.menu.HvlComponentDefault;
import com.osreboot.ridhvl.menu.reflect.HvlDoNotClone;
import com.osreboot.ridhvl.painter.painter2d.HvlFontPainter2D;

public class HvlTextBox extends HvlComponent {

	public static final HvlEvent2<HvlTextBox, Boolean> EVENT_TEXTBOX_FOCUSCHANGED = new HvlEvent2<>();
	public static final HvlEvent2<HvlTextBox, String> EVENT_TEXTBOX_VALUECHANGED = new HvlEvent2<>();
	
	private HvlComponentDrawable focusedDrawable, unfocusedDrawable;
	private float offsetX, offsetY;
	private float textScale;
	private Color textColor;
	private HvlFontPainter2D font;
	private String text;
	@HvlDoNotClone
	private String pText;
	@HvlDoNotClone
	private boolean isFocused, pIsFocused;
	private int maxCharacters;
	private boolean forceUppercase, forceLowercase;
	private boolean numbersOnly;
	private boolean disguised;
	private String blacklistCharacters;
	private boolean ignoreFocus;

	@HvlDoNotClone
	private HvlAction2<HvlTextBox, String> textChangedCommand;

	public HvlTextBox(float wArg, float hArg, String textArg, HvlComponentDrawable focusedArg,
			HvlComponentDrawable unfocusedArg, HvlFontPainter2D fontArg) {
		super(wArg, hArg);
		text = textArg;
		isFocused = false;
		pIsFocused = false;
		maxCharacters = -1;
		focusedDrawable = focusedArg;
		unfocusedDrawable = unfocusedArg;
		font = fontArg;
		textColor = Color.black;
		textScale = 1.0f;
		pText = textArg;
		blacklistCharacters = "";
		ignoreFocus = false;
	}

	public HvlTextBox(float xArg, float yArg, float wArg, float hArg, String textArg, HvlComponentDrawable focusedArg,
			HvlComponentDrawable unfocusedArg, HvlFontPainter2D fontArg) {
		super(xArg, yArg, wArg, hArg);
		text = textArg;
		isFocused = false;
		pIsFocused = false;
		maxCharacters = -1;
		focusedDrawable = focusedArg;
		unfocusedDrawable = unfocusedArg;
		font = fontArg;
		textColor = Color.black;
		textScale = 1.0f;
		pText = textArg;
		blacklistCharacters = "";
	}

	public HvlTextBox(float wArg, float hArg, HvlComponentDrawable focusedDrawable,
			HvlComponentDrawable unfocusedDrawable, float textScale, Color textColor, HvlFontPainter2D font,
			String text) {
		super(wArg, hArg);
		this.focusedDrawable = focusedDrawable;
		this.unfocusedDrawable = unfocusedDrawable;
		this.textScale = textScale;
		this.textColor = textColor;
		this.font = font;
		this.text = text;
		isFocused = false;
		pIsFocused = false;
		maxCharacters = -1;
		pText = text;
		blacklistCharacters = "";
		ignoreFocus = false;
	}

	public HvlTextBox(float xArg, float yArg, float wArg, float hArg, HvlComponentDrawable focusedDrawable,
			HvlComponentDrawable unfocusedDrawable, float textScale, Color textColor, HvlFontPainter2D font,
			String text) {
		super(xArg, yArg, wArg, hArg);
		this.focusedDrawable = focusedDrawable;
		this.unfocusedDrawable = unfocusedDrawable;
		this.textScale = textScale;
		this.textColor = textColor;
		this.font = font;
		this.text = text;
		isFocused = false;
		pIsFocused = false;
		maxCharacters = -1;
		pText = text;
		blacklistCharacters = "";
		ignoreFocus = false;
	}

	@Override
	public void update(float delta) {
		if (!isEnabled()) {
			isFocused = false;
			Keyboard.poll();
			while (Keyboard.next())
				;
		}

		if (Mouse.isButtonDown(0)) {
			isFocused = isBeingPressed(0);
		}

		if (ignoreFocus || isFocused) {
			Keyboard.poll();
			if (Keyboard.getNumKeyboardEvents() > 0) {
				while (Keyboard.next()) {
					if (Keyboard.getEventKeyState()) {
						Character keyChar = Keyboard.getEventCharacter();
						int key = Keyboard.getEventKey();
						if (key == Keyboard.KEY_LSHIFT || key == Keyboard.KEY_RSHIFT || key == Keyboard.KEY_LCONTROL
								|| key == Keyboard.KEY_RCONTROL || key == Keyboard.KEY_LMENU
								|| key == Keyboard.KEY_RMENU || key == Keyboard.KEY_LMETA || key == Keyboard.KEY_RMETA)
							continue;

						if (Keyboard.getEventKey() == Keyboard.KEY_BACK) {
							if (text.length() > 0)
								text = text.substring(0, Math.max(text.length() - 1, 0));
						} else {
							if(keyChar != '\t' && keyChar != '\n') text = text.concat(keyChar.toString());
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

		if(!pIsFocused == isFocused){
			EVENT_TEXTBOX_FOCUSCHANGED.trigger(this, isFocused);
		}
		pIsFocused = isFocused;
		
		if (!pText.equals(text)) {
			if (textChangedCommand != null)
				textChangedCommand.run(this, text);
			EVENT_TEXTBOX_VALUECHANGED.trigger(this, text);
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
		if (font != null && text != null && textColor != null){
			if(!disguised){
				font.drawWord(getText(), getX() + offsetX, getY() + offsetY, textColor, textScale);
			}else{
				String output = "";
				for(int i = 0; i < getText().length(); i++) output += "*";
				font.drawWord(output, getX() + offsetX, getY() + offsetY, textColor, textScale);
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
	
	public boolean isDisguised(){
		return disguised;
	}

	public void setDisguised(boolean disguisedArg){
		disguised = disguisedArg;
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

	public boolean isIgnoreFocus() {
		return ignoreFocus;
	}

	public void setIgnoreFocus(boolean ignoreFocus) {
		this.ignoreFocus = ignoreFocus;
	}

	public HvlAction2<HvlTextBox, String> getTextChangedCommand() {
		return textChangedCommand;
	}

	public void setTextChangedCommand(HvlAction2<HvlTextBox, String> textChangedCommand) {
		this.textChangedCommand = textChangedCommand;
	}

	public static class Builder {
		private HvlTextBox tr;

		public Builder() {
			tr = new HvlTextBox(0, 0, "", null, null, null);
			if (HvlComponentDefault.hasDefault(HvlTextBox.class))
				tr = HvlComponentDefault.getDefault(HvlTextBox.class).cloneComponent(tr);
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
		
		public Builder setDisguised(boolean disguised){
			tr.setDisguised(disguised);
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

		public Builder setIgnoreFocus(boolean ignoreFocus) {
			tr.setIgnoreFocus(ignoreFocus);
			return this;
		}

		public Builder setTextChangedCommand(HvlAction2<HvlTextBox, String> textChangedCommand) {
			tr.setTextChangedCommand(textChangedCommand);
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

		public HvlTextBox build() {
			return tr;
		}
	}
}
