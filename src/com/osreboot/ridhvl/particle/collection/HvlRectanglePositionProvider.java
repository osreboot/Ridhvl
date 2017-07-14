package com.osreboot.ridhvl.particle.collection;

import com.osreboot.ridhvl.HvlCoord2D;
import com.osreboot.ridhvl.HvlMath;

public class HvlRectanglePositionProvider implements HvlParticlePositionProvider {

	private float left, right, top, bottom;
	
	public HvlRectanglePositionProvider(float left, float right, float top, float bottom) {
		this.left = left;
		this.right = right;
		this.top = top;
		this.bottom = bottom;
	}

	@Override
	public HvlCoord2D getParticlePosition(HvlSimpleParticleSystem spawnerArg) {
		return new HvlCoord2D(HvlMath.randomFloatBetween(left, right), HvlMath.randomFloatBetween(top, bottom)).add(spawnerArg.getX(), spawnerArg.getY());
	}

	public float getLeft() {
		return left;
	}

	public void setLeft(float left) {
		this.left = left;
	}

	public float getRight() {
		return right;
	}

	public void setRight(float right) {
		this.right = right;
	}

	public float getTop() {
		return top;
	}

	public void setTop(float top) {
		this.top = top;
	}

	public float getBottom() {
		return bottom;
	}

	public void setBottom(float bottom) {
		this.bottom = bottom;
	}

}
