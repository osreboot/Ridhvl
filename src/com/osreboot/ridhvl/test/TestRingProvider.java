package com.osreboot.ridhvl.test;

import com.osreboot.ridhvl.HvlCoord2D;
import com.osreboot.ridhvl.HvlMath;
import com.osreboot.ridhvl.particle.collection.HvlParticlePositionProvider;
import com.osreboot.ridhvl.particle.collection.HvlSimpleParticleSystem;

public class TestRingProvider implements HvlParticlePositionProvider {

	private float radius;
	
	public TestRingProvider(float radius) {
		this.radius = radius;
	}

	@Override
	public HvlCoord2D getParticlePosition(HvlSimpleParticleSystem spawnerArg) {
		float angle = HvlMath.randomFloatBetween(0, 2.0f * (float)Math.PI);
		return new HvlCoord2D(spawnerArg.getX() + ((float) Math.cos(angle) * radius), spawnerArg.getY() + ((float) Math.sin(angle) * radius));
	}

	public float getRadius() {
		return radius;
	}

	public void setRadius(float radius) {
		this.radius = radius;
	}

	
}
