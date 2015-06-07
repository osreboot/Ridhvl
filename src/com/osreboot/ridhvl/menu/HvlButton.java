package com.osreboot.ridhvl.menu;

import java.util.ArrayList;

import org.lwjgl.input.Mouse;

public abstract class HvlButton {

	private static float globalInversionHeight;

	private static boolean triggered = false;

	public static void setInversionHeight(float inversionHeightArg){
		globalInversionHeight = inversionHeightArg;
	}

	public static float getInversionHeight(){
		return globalInversionHeight;
	}

	public static void updateButtons(){
		if(!Mouse.isButtonDown(0)) triggered = false;
	}

	private static ArrayList<HvlButton> buttons = new ArrayList<HvlButton>();

	private float xLocation, yLocation, xLength, yLength, inversionHeight;

	public HvlButton(float xArg, float yArg, float xlArg, float ylArg, float inversionHeightArg){
		xLocation = xArg;
		yLocation = yArg;
		xLength = xlArg;
		yLength = ylArg;
		inversionHeight = inversionHeightArg;
		buttons.add(this);
	}

	public void onPressing(int buttonArg){}
	public void onTriggered(){}
	public void draw(float delta){}

	public final boolean isBeingPressed(int buttonArg){//TODO account for HvlDisplayMode
		return Mouse.isInsideWindow() && Mouse.isButtonDown(buttonArg) && Mouse.getX() > xLocation && inversionHeight - Mouse.getY() > yLocation && Mouse.getX() < xLocation + xLength && inversionHeight - Mouse.getY() < yLocation + yLength;
	}

	public final boolean isHovering(){//TODO account for HvlDisplayMode
		return Mouse.isInsideWindow() && Mouse.getX() > xLocation && inversionHeight - Mouse.getY() > yLocation && Mouse.getX() < xLocation + xLength && inversionHeight - Mouse.getY() < yLocation + yLength;
	}

	public final void update(float delta){
		draw(delta);
		if(isBeingPressed(0)){
			onPressing(0);
			if(!triggered){
				onTriggered();
				triggered = true;
			}
		}else{
			if(isBeingPressed(1)){
				onPressing(1);
			}
			//triggered = false;
		}
	}

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

}
