package com.osreboot.ridhvl.particle.correlation;

import com.osreboot.ridhvl.particle.HvlParticle;

/**
 * This represents a class for relating various properties inside of an
 * HvlParticle. This could, for example, make particles point towards their
 * direction of motion, or make them
 */
public abstract class HvlParticleCorrelator {

	private boolean isContinuous;

	/**
	 * This performs the correlation on an individual particle. If this is a
	 * continuous correlator, this is called every update loop. If not, this is
	 * called only when the particle is spawned.
	 * 
	 * @param in
	 *            The particle to perform the correlation on.
	 */
	public abstract void correlate(HvlParticle in);

	/**
	 * Gets whether this correlator is used only when a particle is spawned, or
	 * if it is used every update loop ("continuous").
	 * 
	 * @return Whether this correlator is continuous or not.
	 */
	public final boolean isContinuous() {
		return isContinuous;
	}

	/**
	 * Sets whether this correlator is used only when a particle is spawned, or
	 * if it is used every update loop ("continuous").
	 * 
	 * @param isContinuous Whether this corellator is continuous or not.
	 */
	public final void setContinuous(boolean isContinuous) {
		this.isContinuous = isContinuous;
	}
}
