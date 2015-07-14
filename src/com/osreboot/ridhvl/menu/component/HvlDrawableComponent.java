package com.osreboot.ridhvl.menu.component;

import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

import com.osreboot.ridhvl.menu.HvlComponent;
import com.osreboot.ridhvl.menu.HvlComponentDefault;
import com.osreboot.ridhvl.painter.painter2d.HvlPainter2D;

public class HvlDrawableComponent extends HvlComponent {

	private HvlComponentDrawable drawable;
	private Color color;

	protected HvlDrawableComponent(float wArg, float hArg, HvlComponentDrawable dArg) {
		super(wArg, hArg);
		drawable = dArg;
		color = Color.white;
	}

	protected HvlDrawableComponent(float xArg, float yArg, float wArg, float hArg, HvlComponentDrawable dArg) {
		super(xArg, yArg, wArg, hArg);
		drawable = dArg;
		color = Color.white;
	}

	@Override
	public void draw(float delta) {
		drawable.draw(delta, getX(), getY(), getWidth(), getHeight());
	}

	public HvlComponentDrawable getDrawable() {
		return drawable;
	}

	public void setDrawable(HvlComponentDrawable drawable) {
		this.drawable = drawable;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
	public static class Builder {
		private HvlDrawableComponent tr;
		
		public Builder() {
			if (HvlComponentDefault.hasDefault(HvlDrawableComponent.class))
				tr = HvlComponentDefault.getDefault(HvlDrawableComponent.class).clone();
			else
				tr = new HvlDrawableComponent(0, 0, null);
		}

		public Builder setTexture(HvlComponentDrawable drawable) {
			tr.setDrawable(drawable);
			return this;
		}

		public Builder setColor(Color color) {
			tr.setColor(color);
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
		
		public HvlDrawableComponent build() {
			return tr;
		}
	}
	
	public HvlDrawableComponent clone() {
		HvlDrawableComponent tr = new HvlDrawableComponent(0, 0, null);
		// HvlComponent
		tr.setX(getX());
		tr.setY(getY());
		tr.setWidth(getWidth());
		tr.setHeight(getHeight());
		tr.setEnabled(isEnabled());
		tr.setVisible(isVisible());
		// HvlImageComponent
		tr.color = color;
		tr.drawable = drawable;
		return tr;
	}
}
