package com.osreboot.ridhvl.menu.component;

import org.newdawn.slick.Color;

import com.osreboot.ridhvl.action.HvlAction2;
import com.osreboot.ridhvl.menu.HvlComponent;
import com.osreboot.ridhvl.menu.HvlComponentDefault;

public class HvlDrawableComponent extends HvlComponent {

	private HvlComponentDrawable drawable;

	public HvlDrawableComponent(float wArg, float hArg, HvlComponentDrawable dArg) {
		super(wArg, hArg);
		drawable = dArg;
	}

	public HvlDrawableComponent(float xArg, float yArg, float wArg, float hArg, HvlComponentDrawable dArg) {
		super(xArg, yArg, wArg, hArg);
		drawable = dArg;
	}

	@Override
	public void draw(float delta) {
		if (drawable != null)
			drawable.draw(delta, getX(), getY(), getWidth(), getHeight());
	}

	public HvlComponentDrawable getDrawable() {
		return drawable;
	}

	public void setDrawable(HvlComponentDrawable drawable) {
		this.drawable = drawable;
	}

	public static class Builder {
		private HvlDrawableComponent tr;

		public Builder() {
			tr = new HvlDrawableComponent(0, 0, null);
			if (HvlComponentDefault.hasDefault(HvlDrawableComponent.class))
				tr = HvlComponentDefault.getDefault(HvlDrawableComponent.class).cloneComponent(tr);
		}

		public Builder setTexture(HvlComponentDrawable drawable) {
			tr.setDrawable(drawable);
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

		public Builder setEnabled(boolean enabled) {
			tr.setEnabled(enabled);
			return this;
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

		public HvlDrawableComponent build() {
			return tr;
		}
	}
}
