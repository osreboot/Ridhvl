package com.osreboot.ridhvl.test.particle;

import org.newdawn.slick.opengl.Texture;

import com.osreboot.ridhvl.particle.HvlParticle;
import com.osreboot.ridhvl.particle.HvlParticleSystem;

public class TestSmokeParticleSystem extends HvlParticleSystem {

	private Texture t;
	
	public TestSmokeParticleSystem(float x, float y, Texture tArg) {
		super(x, y);
		this.setMaxParticles(-1);
		this.setMinTimeToSpawn(0.1f);
		this.setMaxTimeToSpawn(0.1f);
		this.setParticlesPerSpawn(5);
		this.setSpawnOnTimer(true);
		this.t = tArg;
	}

	@Override
	public HvlParticle generateParticle() {
		return new TestSmokeParticle(getX(), getY(), this, t);
	}

}
