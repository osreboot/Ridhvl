package com.osreboot.ridhvl.menu.component;

import com.osreboot.ridhvl.menu.HvlComponent;

public class HvlArrangerBox extends HvlPanel {
	
	public enum ArrangementStyle {
		VERTICAL, HORIZONTAL
	}

	private ArrangementStyle style;
	private float align;

	private float borderL, borderR, borderU, borderD;

	public HvlArrangerBox(float xArg, float yArg, float wArg, float hArg,
			float heightInversionArg, ArrangementStyle styleArg) {
		super(xArg, yArg, wArg, hArg, heightInversionArg);
		style = styleArg;
		align = 0.0f;
	}

	@Override
	public void update(float delta) {
		super.update(delta);

		switch (style) {
		case VERTICAL: {
			float previousY = 0;
			for (int i = 0; i < getChildCount(); i++) {
				HvlComponent comp = get(i);
				
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
			float previousX = 0;
			for (int i = 0; i < getChildCount(); i++)
			{
				HvlComponent comp = get(i);
				
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
}
