package com.osreboot.ridhvl.particle.collection;

import com.osreboot.ridhvl.HvlCoord;
import com.osreboot.ridhvl.HvlMath;

public class HvlTorusPositionProvider implements HvlParticlePositionProvider {

	float innerRadius, outerRadius;

	public HvlTorusPositionProvider(float innerRadius, float outerRadius) {
		this.innerRadius = innerRadius;
		this.outerRadius = outerRadius;
	}

	@Override
	public HvlCoord getParticlePosition(HvlSimpleParticleSystem spawnerArg) {
		float angle = HvlMath.randomFloatBetween(0, (float) Math.PI * 2);
		float length = HvlMath.randomFloatBetween(innerRadius, outerRadius);

		return new HvlCoord((float)(spawnerArg.getX() + (Math.cos(angle) * length)), (float)(spawnerArg.getY() + (Math.sin(angle) * length)));
	}

	public float getInnerRadius() {
		return innerRadius;
	}

	public void setInnerRadius(float innerRadius) {
		this.innerRadius = innerRadius;
	}

	public float getOuterRadius() {
		return outerRadius;
	}

	public void setOuterRadius(float outerRadius) {
		this.outerRadius = outerRadius;
	}

}
