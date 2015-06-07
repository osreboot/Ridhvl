package com.osreboot.ridhvl.menu;

public abstract class HvlControl {
	
	private float x, y, width, height, heightInversion; 
	
	public HvlControl(float xArg, float yArg, float wArg, float hArg, float heightInversionArg)
	{
		x = xArg;
		y = yArg;
		width = wArg;
		height = hArg;
		heightInversion = heightInversionArg;
	}
	
	public void update(float delta) {}
	public void draw(float delta) {}

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
	
	
}
