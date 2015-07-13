package com.osreboot.ridhvl.menu.component;

import java.util.LinkedList;
import java.util.List;

import com.osreboot.ridhvl.menu.HvlComponent;

public class HvlPanel extends HvlComponent {

	private List<HvlComponent> children;

	protected HvlPanel(float wArg, float hArg) {
		super(wArg, hArg);
		children = new LinkedList<>();
	}

	protected HvlPanel(float xArg, float yArg, float wArg, float hArg) {
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
	
	public void remove(int i) {
		children.remove(i);
	}
	
	public void removeAll() {
		children.clear();
	}

	public HvlComponent get(int i) {
		return children.get(i);
	}

	public int getChildCount() {
		return children.size();
	}

	public static class Builder {
		private HvlPanel tr;
		
		public Builder() {
			// TODO: Set defaults here
			tr = new HvlPanel(0, 0, 0, 0);
		}

		public Builder add(HvlComponent toAdd) {
			tr.add(toAdd);
			return this;
		}

		public HvlComponent get(int i) {
			return tr.get(i);
		}

		public int getChildCount() {
			return tr.getChildCount();
		}

		public float getX() {
			return tr.getX();
		}

		public Builder setX(float x) {
			tr.setX(x);
			return this;
		}

		public float getY() {
			return tr.getY();
		}

		public Builder setY(float y) {
			tr.setY(y);
			return this;
		}

		public float getWidth() {
			return tr.getWidth();
		}

		public Builder setWidth(float width) {
			tr.setWidth(width);
			return this;
		}

		public float getHeight() {
			return tr.getHeight();
		}

		public Builder setHeight(float height) {
			tr.setHeight(height);
			return this;
		}

		public boolean isEnabled() {
			return tr.isEnabled();
		}

		public void setEnabled(boolean enabled) {
			tr.setEnabled(enabled);
		}

		public boolean isVisible() {
			return tr.isVisible();
		}

		public Builder setVisible(boolean visible) {
			tr.setVisible(visible);
			return this;
		}
		
		public HvlPanel build() {
			return tr;
		}
	}
}
