package com.osreboot.ridhvl.menu.component;

import java.util.LinkedList;
import java.util.List;

import com.osreboot.ridhvl.menu.HvlComponent;

public class HvlPanel extends HvlComponent {

	private List<HvlComponent> children;

	public HvlPanel(float xArg, float yArg, float wArg, float hArg) {
		super(xArg, yArg, wArg, hArg);
		children = new LinkedList<>();
	}

	@Override
	public void update(float delta) {
		for (HvlComponent comp : children) {
			comp.update(delta);
		}
	}

	@Override
	public void draw(float delta) {
		for (HvlComponent comp : children) {
			comp.draw(delta);
		}
	}

	public void addChild(HvlComponent toAdd) {
		children.add(toAdd);
	}

	public void removeChild(HvlComponent toRemove) {
		children.remove(toRemove);
	}

	public HvlComponent get(int i) {
		return children.get(i);
	}

	public int getChildCount() {
		return children.size();
	}
}
