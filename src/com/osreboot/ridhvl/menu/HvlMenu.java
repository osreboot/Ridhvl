package com.osreboot.ridhvl.menu;

import java.util.ArrayList;
import java.util.Stack;

public class HvlMenu {

	private static HvlMenu current;
	private static Stack<HvlMenu> popups;
	private static Stack<Boolean> blocks;

	static {
		popups = new Stack<>();
		blocks = new Stack<>();
	}

	public static HvlMenu getCurrent() {
		return current;
	}

	public static void setCurrent(HvlMenu currentArg) {
		current = currentArg;
		current.setTotalTime(0);
		if (current.getComponents().size() > 0)
			current.setFocused(current.getComponents().get(0));// TODO implement
																// boolean
																// joystick
																// using system
	}

	public static void updateMenus(float delta) {
		boolean blocked = false;

		// Go from the top down (highest popup to lowest).
		for (int i = popups.size() - 1; i >= 0; i--) {
			HvlMenu popup = popups.get(i);
			boolean block = blocks.get(i);

			// If nothing else has blocked updating, we can update this.
			if (!blocked) {
				popup.update(delta);
				popup.draw(delta);
			}

			// If this blocks lower popups, then say that we are blocked.
			if (block && !blocked)
				blocked = true;
		}

		if (!blocked) {
			current.update(delta);
		}

		// Draw in reverse order: draw the main menu...
		current.draw(delta);

		// ... and then the popups from the bottom up.
		for (int i = 0; i < popups.size(); i++) {
			popups.get(i).draw(delta);
		}
	}

	public static ArrayList<HvlMenu> menus = new ArrayList<HvlMenu>();

	private ArrayList<HvlComponent> components;

	private HvlComponent focused;
	private float totalTime;

	public HvlMenu() {
		components = new ArrayList<HvlComponent>();
	}

	public ArrayList<HvlComponent> getComponents() {
		return components;
	}

	public void add(HvlComponent control) {
		components.add(control);
	}

	public void update(float delta) {
		totalTime += delta;
		for (HvlComponent c : components)
			c.metaUpdate(delta);
		draw(delta);
	}

	public void draw(float delta) {
		for (HvlComponent c : components) {
			if (c.isVisible())
				c.metaDraw(delta);
		}
	}

	public HvlComponent getFocused() {
		return focused;
	}

	public void setFocused(HvlComponent focusedArg) {
		focused = focusedArg;
	}

	public float getTotalTime() {
		return totalTime;
	}

	public void setTotalTime(float totalTimeArg) {
		totalTime = totalTimeArg;
	}

	public static void addPopup(HvlMenu popup, boolean blocking) {
		popups.push(popup);
		blocks.push(blocking);
	}

	public static void removePopup() {
		if (popups.isEmpty())
			return;

		popups.pop();
		blocks.pop();
	}

	public static void removePopup(HvlMenu remove, boolean allAbove) {
		if (popups.isEmpty())
			return;

		if (!popups.contains(remove))
			return;

		int index = popups.indexOf(remove);

		if (allAbove) {
			while (popups.peek() != remove) {
				popups.pop();
				blocks.pop();
			}
			popups.pop();
			blocks.pop();
		} else {
			popups.remove(index);
			blocks.remove(index);
		}
	}

	public static boolean hasPopup() {
		return !popups.isEmpty();
	}
}
