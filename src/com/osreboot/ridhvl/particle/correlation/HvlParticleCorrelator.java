package com.osreboot.ridhvl.particle.correlation;

import com.osreboot.ridhvl.particle.HvlParticle;
import com.osreboot.ridhvl.particle.HvlParticleSystem;

public class HvlParticleCorrelator {

	private boolean isContinuous;
	
	public HvlParticleCorrelator() {
	}

	public void correlate(HvlParticle in, HvlParticleSystem spawner)
	{
		return; // Do nothing by default.
	}

	public final boolean isContinuous() {
		return isContinuous;
	}

	public final void setContinuous(boolean isContinuous) {
		this.isContinuous = isContinuous;
	}
}
