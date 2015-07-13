package com.osreboot.ridhvl.menu.component;

import com.osreboot.ridhvl.menu.HvlComponent;

public class HvlArrangerBox extends HvlPanel {

	public enum ArrangementStyle {
		VERTICAL, HORIZONTAL
	}

	private ArrangementStyle style;
	private float align;

	private float borderL, borderR, borderU, borderD;

	protected HvlArrangerBox(float wArg, float hArg, ArrangementStyle styleArg) {
		super(wArg, hArg);
		style = styleArg;
		align = 0.0f;
	}

	protected HvlArrangerBox(float xArg, float yArg, float wArg, float hArg, ArrangementStyle styleArg) {
		super(xArg, yArg, wArg, hArg);
		style = styleArg;
		align = 0.0f;
	}

	@Override
	public void update(float delta) {
		super.update(delta);

		switch (style) {
		case VERTICAL: {
			float previousY = getY();
			for (int i = 0; i < getChildCount(); i++) {
				HvlComponent comp = get(i);
				if (comp == null)
					continue;

				comp.setX(getX() + (getWidth() * align) - (comp.getWidth() * align));

				if (i == 0)
					comp.setY(previousY + borderU);
				else
					comp.setY(previousY + borderU + borderD);

				previousY += borderU + borderD + comp.getHeight();
			}
			break;
		}
		case HORIZONTAL: {
			float previousX = getX();
			for (int i = 0; i < getChildCount(); i++) {
				HvlComponent comp = get(i);
				if (comp == null)
					continue;

				if (i == 0)
					comp.setX(previousX + borderL);
				else
					comp.setX(previousX + borderL + borderR);
				previousX += borderL + borderR + comp.getWidth();

				comp.setY(getY() + (getHeight() * align) - (comp.getHeight() * align));
			}
			break;
		}
		}
	}

	public ArrangementStyle getStyle() {
		return style;
	}

	public void setStyle(ArrangementStyle style) {
		this.style = style;
	}

	public float getBorderL() {
		return borderL;
	}

	public void setBorderL(float borderL) {
		this.borderL = borderL;
	}

	public float getBorderR() {
		return borderR;
	}

	public void setBorderR(float borderR) {
		this.borderR = borderR;
	}

	public float getBorderU() {
		return borderU;
	}

	public void setBorderU(float borderU) {
		this.borderU = borderU;
	}

	public float getBorderD() {
		return borderD;
	}

	public float getAlign() {
		return align;
	}

	public void setAlign(float align) {
		this.align = align;
	}

	public void setBorderD(float borderD) {
		this.borderD = borderD;
	}

	public static class Builder {
		private HvlArrangerBox tr;

		public Builder() {
			// TODO: Set defaults here
			tr = new HvlArrangerBox(0, 0, 0, 0, ArrangementStyle.VERTICAL);
		}

		public Builder add(HvlComponent toAdd) {
			tr.add(toAdd);
			return this;
		}

		public HvlComponent get(int i) {
			return tr.get(i);
		}

		public final boolean isHovering() {
			return tr.isHovering();
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

		public ArrangementStyle getStyle() {
			return tr.getStyle();
		}

		public Builder setHeight(float height) {
			tr.setHeight(height);
			return this;
		}

		public Builder setStyle(ArrangementStyle style) {
			tr.setStyle(style);
			return this;
		}

		public boolean isEnabled() {
			return tr.isEnabled();
		}

		public float getBorderL() {
			return tr.getBorderL();
		}

		public Builder setEnabled(boolean enabled) {
			tr.setEnabled(enabled);
			return this;
		}

		public Builder setBorderL(float borderL) {
			tr.setBorderL(borderL);
			return this;
		}

		public boolean isVisible() {
			return tr.isVisible();
		}

		public float getBorderR() {
			return tr.getBorderR();
		}

		public Builder setVisible(boolean visible) {
			tr.setVisible(visible);
			return this;
		}

		public Builder setBorderR(float borderR) {
			tr.setBorderR(borderR);
			return this;
		}

		public float getBorderU() {
			return tr.getBorderU();
		}

		public Builder setBorderU(float borderU) {
			tr.setBorderU(borderU);
			return this;
		}

		public float getBorderD() {
			return tr.getBorderD();
		}

		public float getAlign() {
			return tr.getAlign();
		}

		public Builder setAlign(float align) {
			tr.setAlign(align);
			return this;
		}

		public Builder setBorderD(float borderD) {
			tr.setBorderD(borderD);
			return this;
		}
		
		public HvlArrangerBox build() {
			return tr;
		}
	}
}
