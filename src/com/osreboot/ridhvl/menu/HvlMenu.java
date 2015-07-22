package com.osreboot.ridhvl.menu;

import java.util.ArrayList;

public class HvlMenu {

	private static HvlMenu current;
	
	public static HvlMenu getCurrent() {
		return current;
	}

	public static void setCurrent(HvlMenu currentArg) {
		current = currentArg;
		if(current.getComponents().size() > 0) current.setFocused(current.getComponents().get(0));//TODO implement boolean joystick using system
	}

	public static void updateMenus(float delta) {
		if (current != null)
			current.update(delta);
		current.draw(delta);
	}

	public static ArrayList<HvlMenu> menus = new ArrayList<HvlMenu>();

	private ArrayList<HvlComponent> components;

	private HvlComponent focused;
	
	public HvlMenu() {
		components = new ArrayList<HvlComponent>();
	}

	public ArrayList<HvlComponent> getComponents() {
		return components;
	}

	public void add(HvlComponent control) {
		components.add(control);
	}

	public final void update(float delta) {
		for (HvlComponent c : components)
			c.update(delta);
		draw(delta);
	}

	public void draw(float delta) {
		for (HvlComponent c : components) {
			if (c.isVisible())
				c.draw(delta);
		}
	}

	public HvlComponent getFocused(){
		return focused;
	}

	public void setFocused(HvlComponent focusedArg){
		focused = focusedArg;
	}

}
