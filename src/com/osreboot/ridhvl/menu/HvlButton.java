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
	
	/*
	public static boolean isBeingClicked(int buttonArg, float xArg, float yArg, float xlArg, float ylArg, float inversionHeightArg){
		return Mouse.isInsideWindow() && Mouse.isButtonDown(buttonArg) && Mouse.getX() > xArg && inversionHeightArg - Mouse.getY() > yArg && Mouse.getX() < xArg + xlArg && inversionHeightArg - Mouse.getY() < yArg + ylArg;
	}
	
	public static boolean isBeingClicked(int buttonArg, float xArg, float yArg, float xlArg, float ylArg){
		return Mouse.isInsideWindow() && Mouse.isButtonDown(buttonArg) && Mouse.getX() > xArg && globalInversionHeight - Mouse.getY() > yArg && Mouse.getX() < xArg + xlArg && globalInversionHeight - Mouse.getY() < yArg + ylArg;
	}*///TODO clear this up

	public static void updateButtons(){
		//for(HvlButton b : buttons) b.update();
		triggered = !Mouse.isButtonDown(0);
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
	public void draw(){}
	
	public boolean isBeingPressed(int buttonArg){
		return Mouse.isInsideWindow() && Mouse.isButtonDown(buttonArg) && Mouse.getX() > xLocation && inversionHeight - Mouse.getY() > yLocation && Mouse.getX() < xLocation + xLength && inversionHeight - Mouse.getY() < yLocation + yLength;
	}
	
	public void update(){
		draw();
		if(isBeingPressed(0)){
			onPressing(0);
			if(!triggered){
				onTriggered();
				triggered = true;
			}
		}else if(isBeingPressed(1)){
			onPressing(1);
		}
	}
	
}
