package com.osreboot.ridhvl.particle;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.osreboot.ridhvl.HvlCoord;
import com.osreboot.ridhvl.HvlMath;
import com.osreboot.ridhvl.particle.correlation.HvlParticleCorrelator;

/**
 * This represents the base of a particle system, and is designed to be extended
 * upon.
 * 
 * @see com.osreboot.ridhvl.particle.collection
 */
public abstract class HvlParticleSystem {
	/**
	 * The queue of all the particles. This is a queue to allow for easy removal
	 * of particles once we go over the limit on the number of particles.
	 */
	protected Queue<HvlParticle> particles;

	/**
	 * The list of all of the particle correlators.
	 * 
	 * @see com.osreboot.ridhvl.particle.correlation
	 * @see correlation.HvlParticleCorrelator
	 */
	protected List<HvlParticleCorrelator> correlators;
	private int maxParticles;
	private int minParticlesPerSpawn, maxParticlesPerSpawn;
	private float minTimeToSpawn, maxTimeToSpawn;
	private float timeToSpawn, spawnTimer;
	private boolean spawnOnTimer;
	private HvlCoord pos;

	/**
	 * A basic constructor with the minimal information to function.
	 * 
	 * @param xArg
	 *            The X position of the particle system.
	 * @param yArg
	 *            The Y position of the particle system.
	 */
	public HvlParticleSystem(float xArg, float yArg) {
		maxParticles = -1; // Unlimited by default
		minParticlesPerSpawn = 1;
		maxParticlesPerSpawn = 1;
		particles = new LinkedList<HvlParticle>();
		spawnOnTimer = true;
		pos = new HvlCoord(xArg, yArg);
		correlators = new LinkedList<>();
	}

	/**
	 * A basic constructor with the minimal information to function.
	 * 
	 * @param posArg
	 *            The position of the particle system.
	 */
	public HvlParticleSystem(HvlCoord posArg) {
		maxParticles = -1; // Unlimited by default
		minParticlesPerSpawn = 1;
		maxParticlesPerSpawn = 1;
		particles = new LinkedList<HvlParticle>();
		spawnOnTimer = true;
		pos = posArg;
		correlators = new LinkedList<>();
	}

	/**
	 * This should get called every update loop. This updates all of the
	 * particles and correlators, as well as draws them.
	 * 
	 * @param delta
	 *            The time (in seconds) since the last update loop.
	 */
	public void draw(float delta) {
		if (spawnOnTimer) {
			spawnTimer += delta;

			if (spawnTimer >= timeToSpawn) {
				timeToSpawn = minTimeToSpawn + ((float) Math.random() * (maxTimeToSpawn - minTimeToSpawn));
				spawnTimer = 0;
				spawnParticles(HvlMath.randomIntBetween(minParticlesPerSpawn, maxParticlesPerSpawn));
			}
		}

		// Keep the count down to the maximum amount of particles
		// If it's negative don't bother (unlimited)
		while (maxParticles >= 0 && particles.size() > maxParticles) {
			particles.poll();
		}

		List<HvlParticle> toRemove = new LinkedList<HvlParticle>();

		for (HvlParticle p : particles) {
			p.update(delta);
			for (HvlParticleCorrelator corr : correlators)
				if (corr.isContinuous())
					corr.correlate(p);
			p.draw(delta);
			if (p.shouldBeDestroyed())
				toRemove.add(p);
		}

		for (HvlParticle p : toRemove)
			particles.remove(p);
	}

	/**
	 * Spawns the given number of particles.
	 * 
	 * @param count
	 *            The number of particles to spawn.
	 */
	public void spawnParticles(int count) {
		for (int i = 0; i < count; i++) {
			spawnIndividualParticle();
		}
	}

	/**
	 * Spawns the maximum number of particles. Useful for something like an
	 * explosion.
	 */
	public void spawnAllParticles() {
		spawnParticles(maxParticles);
	}

	/**
	 * Spawns a single particle.
	 */
	public void spawnIndividualParticle() {
		particles.add(generateParticle());
	}

	/**
	 * Generates a single particle.
	 * 
	 * @return The generated particle.
	 */
	public abstract HvlParticle generateParticle();

	/**
	 * Gets the maximum number of particles at any time in this particle system.
	 * 
	 * @return The maximum number of particles.
	 */
	public int getMaxParticles() {
		return maxParticles;
	}

	/**
	 * Sets the maximum number of particles at any time in this particle system.
	 * 
	 * @param maxParticles
	 *            The new maximum number of particles.
	 */
	public void setMaxParticles(int maxParticles) {
		this.maxParticles = maxParticles;
	}

	/**
	 * Gets the minimum number of particles to spawn automatically on a timer.
	 * 
	 * @return The minimum number of particles to spawn automatically.
	 */
	public int getMinParticlesPerSpawn() {
		return minParticlesPerSpawn;
	}

	/**
	 * Sets the minimum number of particles to spawn automatically on a timer.
	 * 
	 * @param minParticlesPerSpawn
	 *            The new minimum number of particles to spawn automatically.
	 */
	public void setMinParticlesPerSpawn(int minParticlesPerSpawn) {
		this.minParticlesPerSpawn = minParticlesPerSpawn;
	}

	/**
	 * Gets the maximum number of particles to spawn automatically on a timer.
	 * 
	 * @return The maximum number of particles to spawn automatically.
	 */
	public int getMaxParticlesPerSpawn() {
		return maxParticlesPerSpawn;
	}

	/**
	 * Sets the maximum number of particles to spawn automatically on a timer.
	 * 
	 * @param maxParticlesPerSpawn
	 *            The new maximum number of particles to spawn automatically.
	 */
	public void setMaxParticlesPerSpawn(int maxParticlesPerSpawn) {
		this.maxParticlesPerSpawn = maxParticlesPerSpawn;
	}

	/**
	 * Sets the exact number of particles to spawn automatically on a timer.
	 * 
	 * @param particlesPerSpawn
	 *            The new number of particles to spawn automatically.
	 */
	public void setParticlesPerSpawn(int particlesPerSpawn) {
		this.minParticlesPerSpawn = particlesPerSpawn;
		this.maxParticlesPerSpawn = particlesPerSpawn;
	}

	/**
	 * Gets the minimum time between automatic particle spawns.
	 * 
	 * @return The minimum time between automatic particle spawns.
	 */
	public float getMinTimeToSpawn() {
		return minTimeToSpawn;
	}

	/**
	 * Sets the minimum time between automatic particle spawns.
	 * 
	 * @param minTimeToSpawn
	 *            The new minimum time between automatic particle spawns.
	 */
	public void setMinTimeToSpawn(float minTimeToSpawn) {
		this.minTimeToSpawn = minTimeToSpawn;
	}

	/**
	 * Gets the maximum time between automatic particle spawns.
	 * 
	 * @return The maximum time between automatic particle spawns.
	 */
	public float getMaxTimeToSpawn() {
		return maxTimeToSpawn;
	}

	/**
	 * Sets the maximum time between automatic particle spawns.
	 * 
	 * @param maxTimeToSpawn
	 *            The new maximum time between automatic particle spawns.
	 */
	public void setMaxTimeToSpawn(float maxTimeToSpawn) {
		this.maxTimeToSpawn = maxTimeToSpawn;
	}

	/**
	 * Sets the time between automatic particle spawns.
	 * 
	 * @param timeToSpawn
	 *            The new time between automatic particle spawns.
	 */
	public void setTimeToSpawn(float timeToSpawn) {
		setMinTimeToSpawn(timeToSpawn);
		setMaxTimeToSpawn(timeToSpawn);
	}

	/**
	 * Gets whether or not the particle system automatically spawns particles.
	 * 
	 * @return Whether or not this particles system automatically spawns
	 *         particles.
	 */
	public boolean isSpawnOnTimer() {
		return spawnOnTimer;
	}

	/**
	 * Sets whether or not the particle system automatically spawns particles.
	 * 
	 * @param spawnOnTimer
	 *            Whether or not the particle system automatically spawns
	 *            particles.
	 */
	public void setSpawnOnTimer(boolean spawnOnTimer) {
		this.spawnOnTimer = spawnOnTimer;
	}

	/**
	 * Gets the X position of this particle system.
	 * 
	 * @return The X position of this particle system.
	 */
	public float getX() {
		return pos.x;
	}

	/**
	 * Sets the X position of this particle system.
	 * 
	 * @param x
	 *            The new X position of this particle system.
	 */
	public void setX(float x) {
		pos.x = x;
	}

	/**
	 * Gets the Y position of this particle system.
	 * 
	 * @return The Y position of this particle system.
	 */
	public float getY() {
		return pos.y;
	}

	/**
	 * Sets the Y position of this particle system.
	 * 
	 * @param y
	 *            The new Y position of this particle system.
	 */
	public void setY(float y) {
		pos.y = y;
	}

	/**
	 * Gets the position of this particle system.
	 * 
	 * @return The position of this particle system.
	 */
	public HvlCoord getPosition() {
		return pos;
	}

	/**
	 * Sets the position of this particle system.
	 * 
	 * @param x
	 *            The new X position of this particle system.
	 * @param y
	 *            The new Y position of this particle system.
	 */
	public void setPosition(float x, float y) {
		pos.x = x;
		pos.y = y;
	}

	/**
	 * Sets the position of this particle system.
	 * 
	 * @param pos
	 *            The new position of this particle system.
	 */
	public void setPosition(HvlCoord pos) {
		this.pos = pos;
	}

	/**
	 * Adds a correlator to this particle system.
	 * 
	 * @param corr
	 *            The correlator to add.
	 */
	public void addCorrelator(HvlParticleCorrelator corr) {
		correlators.add(corr);
	}

	/**
	 * Gets the particles in this particle system in an unmodifiable collection
	 * form.
	 * 
	 * @return An unmodifiable collection holding the particles in this particle
	 *         system.
	 */
	public Collection<HvlParticle> getParticles() {
		return Collections.unmodifiableCollection(particles);
	}
}
