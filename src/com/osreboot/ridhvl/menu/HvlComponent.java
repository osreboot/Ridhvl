package com.osreboot.ridhvl.menu;

import org.lwjgl.input.Mouse;

import com.osreboot.ridhvl.painter.HvlCursor;

public abstract class HvlComponent {
	
	private float x, y, width, height, heightInversion; 
	
	private boolean enabled, visible;
	
	public HvlComponent(float xArg, float yArg, float wArg, float hArg, float heightInversionArg)
	{
		x = xArg;
		y = yArg;
		width = wArg;
		height = hArg;
		heightInversion = heightInversionArg;
		visible = true;
		enabled = true;
	}
	
	public void update(float delta) {}
	public void draw(float delta) {}

	public final boolean isBeingPressed(int buttonArg){//TODO account for HvlDisplayMode
		if (!enabled) return false;
		
		return Mouse.isInsideWindow() && Mouse.isButtonDown(buttonArg) && HvlCursor.getCursorX() > getX() && HvlCursor.getCursorY() > getY() && HvlCursor.getCursorX() < getX() + getWidth() && HvlCursor.getCursorY() < getY() + getHeight();
	}

	public final boolean isHovering(){//TODO account for HvlDisplayMode
		if (!enabled) return false;
		
		return Mouse.isInsideWindow() && HvlCursor.getCursorX() > getX() && HvlCursor.getCursorY() > getY() && HvlCursor.getCursorX() < getX() + getWidth() && HvlCursor.getCursorY() < getY() + getHeight();
	}
	
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public float getHeightInversion() {
		return heightInversion;
	}

	public void setHeightInversion(float heightInversion) {
		this.heightInversion = heightInversion;
	}

	
	public boolean isEnabled() {
		return enabled;
	}

	
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	
	public boolean isVisible() {
		return visible;
	}

	
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
}
