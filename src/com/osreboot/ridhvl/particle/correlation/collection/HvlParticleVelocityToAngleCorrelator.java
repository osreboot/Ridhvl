package com.osreboot.ridhvl.particle.correlation.collection;

import com.osreboot.ridhvl.particle.HvlParticle;
import com.osreboot.ridhvl.particle.collection.HvlSimpleParticle;
import com.osreboot.ridhvl.particle.correlation.HvlParticleCorrelator;

/**
 * This is a correlator for an HvlSimpleParticle that relates the velocity of
 * the particle and the direction of it.
 */
public class HvlParticleVelocityToAngleCorrelator extends HvlParticleCorrelator {

	public HvlParticleVelocityToAngleCorrelator() {
		setContinuous(true);
	}

	@Override
	public void correlate(HvlParticle in) {
		if (!(in instanceof HvlSimpleParticle))
			return;

		HvlSimpleParticle p = (HvlSimpleParticle) in;

		p.setRot((float) Math.toDegrees(Math.atan(p.getxVel() / p.getyVel())));
	}
}
