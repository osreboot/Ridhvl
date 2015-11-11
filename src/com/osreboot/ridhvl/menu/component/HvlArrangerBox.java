package com.osreboot.ridhvl.menu.component;

import com.osreboot.ridhvl.action.HvlAction2;
import com.osreboot.ridhvl.menu.HvlComponent;
import com.osreboot.ridhvl.menu.HvlComponentDefault;

public class HvlArrangerBox extends HvlPanel {

	public enum ArrangementStyle {
		VERTICAL, HORIZONTAL
	}

	private ArrangementStyle style;
	private float xAlign, yAlign;

	private float borderL, borderR, borderU, borderD;

	public HvlArrangerBox(float wArg, float hArg, ArrangementStyle styleArg) {
		super(wArg, hArg);
		style = styleArg;
		xAlign = 0.5f;
		yAlign = 0.5f;
	}

	public HvlArrangerBox(float xArg, float yArg, float wArg, float hArg, ArrangementStyle styleArg) {
		super(xArg, yArg, wArg, hArg);
		style = styleArg;
		xAlign = 0.5f;
		yAlign = 0.5f;
	}

	@Override
	public void update(float delta) {
		super.update(delta);

		switch (style) {
		case VERTICAL: {
			float totalHeight = 0;

			for (int i = 0; i < getChildCount(); i++) {
				HvlComponent comp = get(i);
				if (comp == null)
					continue;

				totalHeight += borderU + comp.getHeight() + borderD;
			}

			float previousY = getY() + (getHeight() * yAlign) - (totalHeight * yAlign);
			for (int i = 0; i < getChildCount(); i++) {
				HvlComponent comp = get(i);
				if (comp == null)
					continue;

				comp.setX(getX() + (getWidth() * xAlign) - (comp.getWidth() * xAlign));

				if (i == 0)
					comp.setY(previousY + borderU);
				else
					comp.setY(previousY + borderU + borderD);

				previousY += borderU + borderD + comp.getHeight();
			}
			break;
		}
		case HORIZONTAL: {
			float totalWidth = 0;

			for (int i = 0; i < getChildCount(); i++) {
				HvlComponent comp = get(i);
				if (comp == null)
					continue;

				totalWidth += borderL + comp.getWidth() + borderR;
			}

			float previousX = getX() + (getWidth() * xAlign) - (totalWidth * xAlign);
			for (int i = 0; i < getChildCount(); i++) {
				HvlComponent comp = get(i);
				if (comp == null)
					continue;

				comp.setY(getY() + (getHeight() * yAlign) - (comp.getHeight() * yAlign));

				if (i == 0)
					comp.setX(previousX + borderL);
				else
					comp.setX(previousX + borderL + borderR);

				previousX += borderL + borderR + comp.getWidth();

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

	public void setBorderD(float borderD) {
		this.borderD = borderD;
	}

	public float getxAlign() {
		return xAlign;
	}

	public void setxAlign(float xAlign) {
		this.xAlign = xAlign;
	}

	public float getyAlign() {
		return yAlign;
	}

	public void setyAlign(float yAlign) {
		this.yAlign = yAlign;
	}

	public static class Builder {
		private HvlArrangerBox tr;

		public Builder() {
			tr = new HvlArrangerBox(0, 0, 0, 0, ArrangementStyle.VERTICAL);
			if (HvlComponentDefault.hasDefault(HvlArrangerBox.class))
				tr = HvlComponentDefault.getDefault(HvlArrangerBox.class).cloneComponent(tr);
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

		public Builder setStyle(ArrangementStyle style) {
			tr.setStyle(style);
			return this;
		}

		public Builder setEnabled(boolean enabled) {
			tr.setEnabled(enabled);
			return this;
		}

		public Builder setBorderL(float borderL) {
			tr.setBorderL(borderL);
			return this;
		}

		public Builder setVisible(boolean visible) {
			tr.setVisible(visible);
			return this;
		}

		public Builder setBorderR(float borderR) {
			tr.setBorderR(borderR);
			return this;
		}

		public Builder setBorderU(float borderU) {
			tr.setBorderU(borderU);
			return this;
		}

		public Builder setBorderD(float borderD) {
			tr.setBorderD(borderD);
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

		public Builder setxAlign(float xAlign) {
			tr.setxAlign(xAlign);
			return this;
		}

		public Builder setyAlign(float yAlign) {
			tr.setyAlign(yAlign);
			return this;
		}

		public HvlArrangerBox build() {
			return tr;
		}
	}
}
