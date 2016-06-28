package com.osreboot.ridhvl.particle;

import com.osreboot.ridhvl.HvlCoord;

/**
 * This represents the base of a particle, and is designed to be expanded upon.
 * 
 * @see com.osreboot.ridhvl.particle.collection
 * @see com.osreboot.ridhvl.particle.collection.HvlSimpleParticle
 */
public abstract class HvlParticle {
	/**
	 * How long this particle has been alive.
	 */
	private float timeAlive;

	private HvlCoord pos;

	/**
	 * The particle system that contains this particle.
	 */
	private final HvlParticleSystem parent;

	/**
	 * A basic constructor with the minimal information required to function.
	 * 
	 * @param xArg
	 *            The X location of this particle.
	 * @param yArg
	 *            The Y location of this particle.
	 * @param parentArg
	 *            The particle system that contains this particle (the one that
	 *            spawned it).
	 */
	public HvlParticle(float xArg, float yArg, HvlParticleSystem parentArg) {
		this.parent = parentArg;
		this.pos = new HvlCoord(xArg, yArg);
	}

	/**
	 * A basic constructor with the minimal information required to function.
	 * 
	 * @param posArg
	 *            The position of this particle.
	 * @param parentArg
	 *            The particle system that contains this particle (the one that
	 *            spawned it).
	 */
	public HvlParticle(HvlCoord posArg, HvlParticleSystem parentArg) {
		this.pos = posArg;
		this.parent = parentArg;
	}

	/**
	 * This gets called every update loop and is used for non-drawing-related
	 * updates.
	 * 
	 * @param delta
	 *            The time (in seconds) since the last update loop.
	 */
	public void update(float delta) {
		timeAlive += delta;
	}

	/**
	 * This gets called every update loop and is used to draw the particle.
	 * 
	 * @param delta
	 *            The time (in seconds) since the last update loop.
	 */
	public void draw(float delta) {
	}

	/**
	 * This returns whether the particle should be removed by the particle
	 * system in the next update loop.
	 * 
	 * @return Whether this particle should be removed.
	 */
	public abstract boolean shouldBeDestroyed();

	/**
	 * Gets the X position of this particle.
	 * 
	 * @return The X position of this particle.
	 */
	public float getX() {
		return pos.x;
	}

	/**
	 * Sets the X position of this particle.
	 * 
	 * @param x
	 *            The new X position of this particle.
	 */
	public void setX(float x) {
		pos.x = x;
	}

	/**
	 * Gets the Y position of this particle.
	 * 
	 * @return The Y position of this particle.
	 */
	public float getY() {
		return pos.y;
	}

	/**
	 * Sets the Y position of this particle.
	 * 
	 * @param y
	 *            The new Y position of this particle.
	 */
	public void setY(float y) {
		pos.y = y;
	}

	/**
	 * Gets the position of this particle.
	 * 
	 * @return The position of this particle.
	 */
	public HvlCoord getPosition() {
		return pos;
	}

	/**
	 * Sets the position of this particle.
	 * 
	 * @param x
	 *            The new X position of this particle.
	 * @param y
	 *            The new Y position of this particle.
	 */
	public void setPosition(float x, float y) {
		pos.x = x;
		pos.y = y;
	}

	/**
	 * Sets the position of this particle.
	 * 
	 * @param position
	 *            The new position of this particle.
	 */
	public void setPosition(HvlCoord position) {
		pos = position;
	}

	/**
	 * Gets how long this particle has been alive (how long it's been since it
	 * was spawned).
	 * 
	 * @return How long this particle has been alive.
	 */
	public float getTimeAlive() {
		return timeAlive;
	}

	/**
	 * Gets the HvlParticleSystem that contains this particle (the one that
	 * spawned it).
	 * 
	 * @return The HvlParticleSystem that contains this particle.
	 */
	public HvlParticleSystem getParent() {
		return parent;
	}
}
