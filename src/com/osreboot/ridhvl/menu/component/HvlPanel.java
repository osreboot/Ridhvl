package com.osreboot.ridhvl.menu.component;

import java.util.LinkedList;
import java.util.List;

import com.osreboot.ridhvl.action.HvlAction2;
import com.osreboot.ridhvl.menu.HvlComponent;
import com.osreboot.ridhvl.menu.HvlComponentContainer;
import com.osreboot.ridhvl.menu.HvlComponentDefault;
import com.osreboot.ridhvl.menu.reflect.HvlDoNotClone;

public class HvlPanel extends HvlComponent implements HvlComponentContainer {

	@HvlDoNotClone
	protected List<HvlComponent> children;
	
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
			comp.setContainer(this);
			comp.metaUpdate(delta);
		}
	}

	@Override
	public void draw(float delta) {
		for (HvlComponent comp : children) {
			if (comp == null)
				continue;
			if (comp.isVisible())
				comp.metaDraw(delta);
		}
	}

	@Override
	public void add(HvlComponent toAdd) {
		children.add(toAdd);
	}
	
	@Override
	public void add(HvlComponent toAdd, int indexArg) {
		children.add(indexArg, toAdd);
	}

	@Override
	public void remove(HvlComponent toRemove) {
		children.remove(toRemove);
	}

	@Override
	public void remove(int i) {
		children.remove(i);
	}

	@Override
	public void clear() {
		children.clear();
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends HvlComponent> T get(int i) {
		return (T) children.get(i);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends HvlComponent> T getFirstOfType(Class<? extends T> type) {
		for (HvlComponent c : children) {
			if (c.getClass().isAssignableFrom(type)) return (T) c;
		}
		
		return null;
	}

	public List<HvlComponent> getChildren(){
		return children;
	}

	public int getChildCount() {
		return children.size();
	}

	public static class Builder {
		private HvlPanel tr;

		public Builder() {
			tr = new HvlPanel(0, 0, 0, 0);
			if (HvlComponentDefault.hasDefault(HvlPanel.class))
				tr = HvlComponentDefault.getDefault(HvlPanel.class).cloneComponent(tr);
		}

		public Builder add(HvlComponent toAdd) {
			tr.add(toAdd);
			return this;
		}

		public Builder setX(float x) {
			tr.setX(x);
			return this;
		}

		public Builder setY(float y) {
			tr.setY(y);
			return this;
		}

		public Builder setWidth(float width) {
			tr.setWidth(width);
			return this;
		}

		public Builder setHeight(float height) {
			tr.setHeight(height);
			return this;
		}

		public void setEnabled(boolean enabled) {
			tr.setEnabled(enabled);
		}

		public Builder setVisible(boolean visible) {
			tr.setVisible(visible);
			return this;
		}

		public Builder setUpdateOverride(HvlAction2<HvlComponent, Float> updateOverride) {
			tr.setUpdateOverride(updateOverride);
			return this;
		}

		public Builder setDrawOverride(HvlAction2<HvlComponent, Float> drawOverride) {
			tr.setDrawOverride(drawOverride);
			return this;
		}

		public HvlPanel build() {
			return tr;
		}
	}
}
