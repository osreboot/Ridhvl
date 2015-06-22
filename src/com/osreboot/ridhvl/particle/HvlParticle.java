package com.osreboot.ridhvl.particle;

public abstract class HvlParticle {
	protected float timeAlive;
	
	private float x, y;
	protected HvlParticleSystem parent;
	
	public HvlParticle(float xArg, float yArg, HvlParticleSystem parentArg)
	{
		this.parent = parentArg;
		this.x = xArg;
		this.y = yArg;
	}
	
	public void draw(float delta)
	{
		timeAlive += delta;
	}
	
	public abstract boolean shouldBeDestroyed();

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
