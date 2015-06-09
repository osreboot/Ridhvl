package com.osreboot.ridhvl.menu.component;

import java.util.LinkedList;
import java.util.List;

import com.osreboot.ridhvl.menu.HvlComponent;

public class HvlArrangerBox extends HvlComponent {

	public enum Alignment {
		LEFT_TOP, RIGHT_BOTTOM, CENTER
	}
	
	public enum ArrangementStyle {
		VERTICAL, HORIZONTAL
	}

	private ArrangementStyle style;
	private Alignment alignment;

	private List<HvlComponent> children;

	private float borderL, borderR, borderU, borderD;

	public HvlArrangerBox(float xArg, float yArg, float wArg, float hArg,
			float heightInversionArg, ArrangementStyle styleArg) {
		super(xArg, yArg, wArg, hArg, heightInversionArg);
		children = new LinkedList<HvlComponent>();
		style = styleArg;
		alignment = Alignment.LEFT_TOP;
	}

	@Override
	public void update(float delta) {
		for (HvlComponent comp : children) {
			comp.update(delta);
		}

		switch (style) {
		case VERTICAL: {
			float previousY = 0;
			for (int i = 0; i < children.size(); i++) {
				HvlComponent comp = children.get(i);
				switch (alignment)
				{
				case LEFT_TOP:
					comp.setX(getX() + borderL);
					break;
				case RIGHT_BOTTOM:
					comp.setX(getX() + getWidth() - borderR - comp.getWidth());
					break;
				case CENTER:
					comp.setX(getX() + (getWidth() / 2) - (comp.getWidth() / 2));
					break;
				}
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
			for (int i = 0; i < children.size(); i++)
			{
				HvlComponent comp = children.get(i);
				
				if (i == 0)
					comp.setX(previousX + borderL);
				else
					comp.setX(previousX + borderL + borderR);
				previousX += borderL + borderR + comp.getWidth();
				
				switch(alignment)
				{
				case LEFT_TOP:
					comp.setY(getY() + borderU);
					break;
				case RIGHT_BOTTOM:
					comp.setY(getY() + getHeight() - borderD - comp.getHeight());
					break;
				case CENTER:
					comp.setY(getY() + (getHeight() / 2) - (comp.getHeight() / 2));
					break;
				}
			}
			break;
		}
		}
	}

	@Override
	public void draw(float delta) {
		for (HvlComponent comp : children) {
			comp.draw(delta);
		}
	}
	
	public void add(HvlComponent comp) {
		children.add(comp);
	}

	public Alignment getAlignment() {
		return alignment;
	}

	public void setAlignment(Alignment alignment) {
		this.alignment = alignment;
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
}
