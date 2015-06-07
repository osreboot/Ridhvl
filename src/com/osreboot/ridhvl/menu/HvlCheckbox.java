package com.osreboot.ridhvl.menu;

import org.lwjgl.input.Mouse;

public abstract class HvlCheckbox extends HvlControl {

	private boolean previousPressed, currentPressed, previousHover, currentHover;
	private boolean checked;
	
	public void onChanged(boolean state){}
	
	public HvlCheckbox(float xArg, float yArg, float xlArg, float ylArg, float inversionHeightArg) {
		super(xArg, yArg, xlArg, ylArg, inversionHeightArg);
	}
	
	public HvlCheckbox(float xArg, float yArg, float xlArg, float ylArg, float inversionHeightArg, boolean checkedArg) {
		super(xArg, yArg, xlArg, ylArg, inversionHeightArg);
		checked = checkedArg;
	}
	
	public final boolean isBeingPressed(int buttonArg){//TODO account for HvlDisplayMode
		return Mouse.isInsideWindow() && Mouse.isButtonDown(buttonArg) && Mouse.getX() > getX() && getHeightInversion() - Mouse.getY() > getY() && Mouse.getX() < getX() + getWidth() && getHeightInversion() - Mouse.getY() < getY() + getHeight();
	}

	public final boolean isHovering(){//TODO account for HvlDisplayMode
		return Mouse.isInsideWindow() && Mouse.getX() > getX() && getHeightInversion() - Mouse.getY() > getY() && Mouse.getX() < getX() + getWidth() && getHeightInversion() - Mouse.getY() < getY() + getHeight();
	}
	
	public final void update(float delta) {
		previousHover = currentHover;
		previousPressed = currentPressed;
		currentHover = isHovering();
		currentPressed = isBeingPressed(0);
		
		// This allows you to drag off of the checkbox without triggering it.
		if (previousHover && !currentHover)
		{
			previousPressed = false;
			currentPressed = false;
		}
		
		if (previousPressed && !currentPressed)
		{
			checked = !checked;
			onChanged(checked);
		}
		
		draw(delta);
	}
	
	public void draw(float delta){}
	
	public boolean getChecked(){
		return checked;
	}
	
	public void setChecked(boolean checkedArg){
		checked = checkedArg;
	}
}
