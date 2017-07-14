package com.osreboot.ridhvl.particle.collection;

import com.osreboot.ridhvl.HvlCoord2D;
import com.osreboot.ridhvl.HvlMath;

public class HvlRadialPositionProvider implements HvlParticlePositionProvider {

	private float radius;
	
	public HvlRadialPositionProvider(float radius) {
		super();
		this.radius = radius;
	}

	@Override
	public HvlCoord2D getParticlePosition(HvlSimpleParticleSystem spawnerArg) {
		return HvlMath.randomPointInCircle(radius).add(spawnerArg.getX(), spawnerArg.getY());
	}

	public float getRadius() {
		return radius;
	}

	public void setRadius(float radius) {
		this.radius = radius;
	}

}
