package com.osreboot.ridhvl.particle;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public abstract class HvlParticleSystem {
	protected Queue<HvlParticle> particles;
	private int maxParticles;
	private int particlesPerSpawn;
	private float minTimeToSpawn, maxTimeToSpawn;
	private float timeToSpawn, spawnTimer;
	private boolean spawnOnTimer;
	private float x, y;
	
	public HvlParticleSystem(float xArg, float yArg)
	{
		maxParticles = -1; // Unlimited by default
		particlesPerSpawn = 1;
		particles = new LinkedList<HvlParticle>();
		spawnOnTimer = true;
		this.x = xArg;
		this.y = yArg;
	}
	
	public void draw(float delta)
	{
		if (spawnOnTimer)
		{
			spawnTimer += delta;
			
			if (spawnTimer >= timeToSpawn)
			{
				timeToSpawn = minTimeToSpawn + ((float)Math.random() * (maxTimeToSpawn - minTimeToSpawn));
				spawnTimer = 0;
				spawnParticles(particlesPerSpawn);
			}
		}
		
		// Keep the count down to the maximum amount of particles
		// If it's negative don't bother (unlimited)
		while (maxParticles >= 0 && particles.size() > maxParticles)
		{
			particles.poll();
		}
		
		List<HvlParticle> toRemove = new LinkedList<HvlParticle>();
		
		for (HvlParticle p : particles)
		{
			p.draw(delta);
			if (p.shouldBeDestroyed())
				toRemove.add(p);
		}
		
		for (HvlParticle p : toRemove)
			particles.remove(p);
	}
	
	public void spawnParticles(int count)
	{
		for (int i = 0; i < count; i++)
		{
			spawnIndividualParticle();
		}
	}
	
	public void spawnAllParticles()
	{
		spawnParticles(maxParticles);
	}

	public void spawnIndividualParticle()
	{
		particles.add(generateParticle());
	}

	public abstract HvlParticle generateParticle();

	
	public int getMaxParticles() {
		return maxParticles;
	}

	public void setMaxParticles(int maxParticles) {
		this.maxParticles = maxParticles;
	}

	public int getParticlesPerSpawn() {
		return particlesPerSpawn;
	}

	public void setParticlesPerSpawn(int particlesPerSpawn) {
		this.particlesPerSpawn = particlesPerSpawn;
	}

	public float getMinTimeToSpawn() {
		return minTimeToSpawn;
	}

	public void setMinTimeToSpawn(float minTimeToSpawn) {
		this.minTimeToSpawn = minTimeToSpawn;
	}

	public float getMaxTimeToSpawn() {
		return maxTimeToSpawn;
	}

	public void setMaxTimeToSpawn(float maxTimeToSpawn) {
		this.maxTimeToSpawn = maxTimeToSpawn;
	}

	public boolean isSpawnOnTimer() {
		return spawnOnTimer;
	}

	public void setSpawnOnTimer(boolean spawnOnTimer) {
		this.spawnOnTimer = spawnOnTimer;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}
}
