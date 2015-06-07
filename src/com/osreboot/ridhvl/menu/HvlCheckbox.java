package com.osreboot.ridhvl.menu;

import java.util.ArrayList;

import org.lwjgl.input.Mouse;

public abstract class HvlCheckbox {
	private static float globalInversionHeight;

	public static void setInversionHeight(float inversionHeightArg){
		globalInversionHeight = inversionHeightArg;
	}

	public static float getInversionHeight(){
		return globalInversionHeight;
	}

	private static ArrayList<HvlCheckbox> checkboxes = new ArrayList<HvlCheckbox>();

	private float xLocation, yLocation, xLength, yLength, inversionHeight;
	private boolean previousPressed, currentPressed, previousHover, currentHover;
	private boolean checked;
	
	public void onChanged(boolean state){}
	
	public HvlCheckbox(float xArg, float yArg, float xlArg, float ylArg, float inversionHeightArg) {
		xLocation = xArg;
		yLocation = yArg;
		xLength = xlArg;
		yLength = ylArg;
		inversionHeight = inversionHeightArg;
		checkboxes.add(this);
	}
	
	public HvlCheckbox(float xArg, float yArg, float xlArg, float ylArg, float inversionHeightArg, boolean checkedArg) {
		xLocation = xArg;
		yLocation = yArg;
		xLength = xlArg;
		yLength = ylArg;
		inversionHeight = inversionHeightArg;
		checked = checkedArg;
		checkboxes.add(this);
	}
	
	public final boolean isBeingPressed(int buttonArg){//TODO account for HvlDisplayMode
		return Mouse.isInsideWindow() && Mouse.isButtonDown(buttonArg) && Mouse.getX() > xLocation && inversionHeight - Mouse.getY() > yLocation && Mouse.getX() < xLocation + xLength && inversionHeight - Mouse.getY() < yLocation + yLength;
	}

	public final boolean isHovering(){//TODO account for HvlDisplayMode
		return Mouse.isInsideWindow() && Mouse.getX() > xLocation && inversionHeight - Mouse.getY() > yLocation && Mouse.getX() < xLocation + xLength && inversionHeight - Mouse.getY() < yLocation + yLength;
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
	
	public float getX(){
		return xLocation;
	}

	public float getY(){
		return yLocation;
	}

	public float getXLength(){
		return xLength;
	}

	public float getYLength(){
		return yLength;
	}
	
	public boolean getChecked(){
		return checked;
	}
	
	public void setChecked(boolean checkedArg){
		checked = checkedArg;
	}
}
