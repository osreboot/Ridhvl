package com.osreboot.ridhvl.particle.collection;

import com.osreboot.ridhvl.HvlCoord2D;
import com.osreboot.ridhvl.HvlMath;

public class HvlLinearPositionProvider implements HvlParticlePositionProvider {

	private float startX, startY, endX, endY;

	public HvlLinearPositionProvider(float startX, float startY, float endX, float endY) {
		this.startX = startX;
		this.startY = startY;
		this.endX = endX;
		this.endY = endY;
	}

	// TODO: this is relative to the original spawner, is this what we want?
	@Override
	public HvlCoord2D getParticlePosition(HvlSimpleParticleSystem spawnerArg) {
		float lerpValue = HvlMath.randomFloatBetween(0.0f, 1.0f);
		
		return new HvlCoord2D(spawnerArg.getX() + HvlMath.lerp(startX, endX, lerpValue), spawnerArg.getY() + HvlMath.lerp(startY, endY, lerpValue));
	}

	public float getStartX() {
		return startX;
	}

	public void setStartX(float startX) {
		this.startX = startX;
	}

	public float getStartY() {
		return startY;
	}

	public void setStartY(float startY) {
		this.startY = startY;
	}

	public float getEndX() {
		return endX;
	}

	public void setEndX(float endX) {
		this.endX = endX;
	}

	public float getEndY() {
		return endY;
	}

	public void setEndY(float endY) {
		this.endY = endY;
	}

}
