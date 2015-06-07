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
	
	private ArrayList<HvlControl> controls;
	
	public HvlMenu(){
		controls = new ArrayList<HvlControl>();
	}
	
	public void add(HvlControl control) {
		controls.add(control);
	}
	
	public final void update(float delta){
		for (HvlControl c : controls) c.update(delta);
		draw(delta);
	}
	
	public void draw(float delta){
		for(HvlControl c : controls) c.draw(delta);
	}
	
}
