package com.osreboot.ridhvl.menu.component;

import java.util.LinkedList;
import java.util.List;

import com.osreboot.ridhvl.menu.HvlComponent;

public class HvlPanel extends HvlComponent {

	private List<HvlComponent> children;

	public HvlPanel(float wArg, float hArg) {
		super(wArg, hArg);
		children = new LinkedList<>();
	}

	public HvlPanel(float xArg, float yArg, float wArg, float hArg) {
		super(xArg, yArg, wArg, hArg);
		children = new LinkedList<>();
	}

	@Override
	public void update(float delta) {
		for (HvlComponent comp : children) {
			if (comp == null)
				continue;
			comp.update(delta);
		}
	}

	@Override
	public void draw(float delta) {
		for (HvlComponent comp : children) {
			if (comp == null)
				continue;
			if (comp.isVisible())
				comp.draw(delta);
		}
	}

	public void add(HvlComponent toAdd) {
		children.add(toAdd);
	}

	public void remove(HvlComponent toRemove) {
		children.remove(toRemove);
	}

	public HvlComponent get(int i) {
		return children.get(i);
	}

	public int getChildCount() {
		return children.size();
	}
}
