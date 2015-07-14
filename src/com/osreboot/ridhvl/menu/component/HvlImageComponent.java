package com.osreboot.ridhvl.menu.component;

import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

import com.osreboot.ridhvl.menu.HvlComponent;
import com.osreboot.ridhvl.menu.HvlComponentDefault;
import com.osreboot.ridhvl.painter.painter2d.HvlPainter2D;

public class HvlImageComponent extends HvlComponent {

	private Texture texture;
	private Color color;

	protected HvlImageComponent(float wArg, float hArg, Texture tArg) {
		super(wArg, hArg);
		texture = tArg;
		color = Color.white;
	}

	protected HvlImageComponent(float xArg, float yArg, float wArg, float hArg, Texture tArg) {
		super(xArg, yArg, wArg, hArg);
		texture = tArg;
		color = Color.white;
	}

	@Override
	public void draw(float delta) {
		HvlPainter2D.hvlDrawQuad(getX(), getY(), getWidth(), getHeight(), texture, color);
	}

	public Texture getTexture() {
		return texture;
	}

	public void setTexture(Texture texture) {
		this.texture = texture;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
	public static class Builder {
		private HvlImageComponent tr;
		
		public Builder() {
			if (HvlComponentDefault.hasDefault(HvlImageComponent.class))
				tr = HvlComponentDefault.getDefault(HvlImageComponent.class).clone();
			else
				tr = new HvlImageComponent(0, 0, null);
		}

		public Builder setTexture(Texture texture) {
			tr.setTexture(texture);
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
		
		public HvlImageComponent build() {
			return tr;
		}
	}
	
	public HvlImageComponent clone() {
		HvlImageComponent tr = new HvlImageComponent(0, 0, null);
		// HvlComponent
		tr.setX(getX());
		tr.setY(getY());
		tr.setWidth(getWidth());
		tr.setHeight(getHeight());
		tr.setEnabled(isEnabled());
		tr.setVisible(isVisible());
		// HvlImageComponent
		tr.color = color;
		tr.texture = texture;
		return tr;
	}
}
