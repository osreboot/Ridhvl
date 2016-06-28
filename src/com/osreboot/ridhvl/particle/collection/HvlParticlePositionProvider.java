package com.osreboot.ridhvl.particle.collection;

import com.osreboot.ridhvl.HvlCoord;

public interface HvlParticlePositionProvider {
	HvlCoord getParticlePosition(HvlSimpleParticleSystem spawnerArg);
}
