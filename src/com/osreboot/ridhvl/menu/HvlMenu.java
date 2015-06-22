package com.osreboot.ridhvl.menu;

import java.util.ArrayList;

public class HvlMenu {

	private static HvlMenu current;

	public static HvlMenu getCurrent() {
		return current;
	}

	public static void setCurrent(HvlMenu currentArg) {
		current = currentArg;
	}

	public static void updateMenus(float delta) {
		if (current != null)
			current.update(delta);
		current.draw(delta);
	}

	public static ArrayList<HvlMenu> menus = new ArrayList<HvlMenu>();

	private ArrayList<HvlComponent> controls;

	public HvlMenu() {
		controls = new ArrayList<HvlComponent>();
	}

	public void add(HvlComponent control) {
		controls.add(control);
	}

	public final void update(float delta) {
		for (HvlComponent c : controls)
			c.update(delta);
		draw(delta);
	}

	public void draw(float delta) {
		for (HvlComponent c : controls) {
			if (c.isVisible())
				c.draw(delta);
		}
	}

}
