package com.osreboot.ridhvl.particle.correlation.collection;

import com.osreboot.ridhvl.particle.HvlParticle;
import com.osreboot.ridhvl.particle.HvlParticleSystem;
import com.osreboot.ridhvl.particle.collection.HvlSimpleParticle;
import com.osreboot.ridhvl.particle.correlation.HvlParticleCorrelator;

public class HvlParticleVelocityToAngleCorrelator extends HvlParticleCorrelator {

	public HvlParticleVelocityToAngleCorrelator() {
		setContinuous(true);
	}
	
	@Override
	public void correlate(HvlParticle in, HvlParticleSystem spawner)
	{
		if (!(in instanceof HvlSimpleParticle)) return;
		
		HvlSimpleParticle p = (HvlSimpleParticle) in;
		
		p.setRot((float) Math.toDegrees(Math.atan(p.getxVel() / p.getyVel())));
	}
}
