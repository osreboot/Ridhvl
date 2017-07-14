package com.osreboot.ridhvl.particle.collection;

import com.osreboot.ridhvl.HvlCoord2D;

public interface HvlParticlePositionProvider {
	HvlCoord2D getParticlePosition(HvlSimpleParticleSystem spawnerArg);
}
