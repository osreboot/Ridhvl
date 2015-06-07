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
	
	public static void updateMenus(float delta){
		if(current != null) current.update(delta);
		current.draw(delta);
	}
	
	public static ArrayList<HvlMenu> menus = new ArrayList<HvlMenu>();
	
	private ArrayList<HvlButton> buttons;
	
	public HvlMenu(){
		buttons = new ArrayList<HvlButton>();
	}
	
	public void addButton(HvlButton buttonArg){
		buttons.add(buttonArg);
	}
	
	public final void update(float delta){
		draw(delta);
		for(HvlButton b : buttons) b.update(delta);
	}
	
	public void draw(float delta){
		for(HvlButton b : buttons) b.draw(delta);
	}
	
}
