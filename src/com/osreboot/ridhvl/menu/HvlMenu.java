package com.osreboot.ridhvl.menu;

import java.util.ArrayList;

public class HvlMenu {

	private static HvlMenu current;
	
	public static HvlMenu getCurrent(){
		return current;
	}
	
	public static void setCurrent(HvlMenu currentArg){
		current = currentArg;
	}
	
	public static void updateMenus(){
		current.update();
	}
	
	public static ArrayList<HvlMenu> menus = new ArrayList<HvlMenu>();
	
	private ArrayList<HvlButton> buttons;
	
	public HvlMenu(){
		buttons = new ArrayList<HvlButton>();
	}
	
	public void addButton(HvlButton buttonArg){
		buttons.add(buttonArg);
	}
	
	public void update(){
		draw();
		for(HvlButton b : buttons) b.update();
	}
	
	public void draw(){}
	
}
