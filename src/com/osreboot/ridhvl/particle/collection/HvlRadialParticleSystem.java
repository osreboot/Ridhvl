package com.osreboot.ridhvl.particle.collection;

import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

import com.osreboot.ridhvl.HvlColorUtil;
import com.osreboot.ridhvl.HvlMath;
import com.osreboot.ridhvl.HvlMath.HvlCoord;
import com.osreboot.ridhvl.particle.HvlParticle;
import com.osreboot.ridhvl.particle.HvlParticleSystem;

public class HvlRadialParticleSystem extends HvlParticleSystem {

	private float spawnRadius;
	private Color startColorOne, startColorTwo;
	private Color endColorOne, endColorTwo;
	private Texture particleTexture;
	private float minXVel, maxXVel;
	private float minYVel, maxYVel;
	private float xVelDecay, yVelDecay;
	private float minRot, maxRot;
	private float minRotVel, maxRotVel;
	private float rotVelDecay;
	private float baseWidth, baseHeight;
	private float minScale, maxScale;
	private float scaleDecay;
	private float minLifetime, maxLifetime;
	
	public HvlRadialParticleSystem(float xArg, float yArg, Texture tArg,
			float pWidthArg, float pHeightArg) {
		super(xArg, yArg);
		spawnRadius = 0;
		startColorOne = startColorTwo = Color.white;
		endColorOne = endColorTwo = Color.white;
		particleTexture = tArg;
		minXVel = maxXVel = 0;
		minYVel = maxYVel = 0;
		xVelDecay = yVelDecay = 1.0f;
		minRot = 0;
		maxRot = 360;
		minRotVel = maxRotVel = 0;
		rotVelDecay = 1.0f;
		baseWidth = pWidthArg;
		baseHeight = pHeightArg;
		minScale = maxScale = 1.0f;
		scaleDecay = 1.0f;
		minLifetime = maxLifetime = 5.0f;
	}

	@Override
	public HvlParticle generateParticle() {
		System.out.println("Gen");
		HvlCoord spawnPos = HvlMath.randomPointInCircle(spawnRadius);
		float xVel = HvlMath.randomBetween(minXVel, maxXVel);
		float yVel = HvlMath.randomBetween(minYVel, maxYVel);
		float rot = HvlMath.randomBetween(minRot, maxRot);
		float rotVel = HvlMath.randomBetween(minRotVel, maxRotVel);
		float scale = HvlMath.randomBetween(minScale, maxScale);
		float lifetime = HvlMath.randomBetween(minLifetime, maxLifetime);
		Color startColor = HvlColorUtil.lerpColor(startColorOne, startColorTwo, (float) Math.random());
		Color endColor = HvlColorUtil.lerpColor(endColorOne, endColorTwo, (float) Math.random());
		
		return createParticleFromSpecs(getX() + spawnPos.x, getY() + spawnPos.y,
				xVel, yVel,
				xVelDecay, yVelDecay,
				rot, rotVel, rotVelDecay,
				baseWidth, baseHeight, scale,
				scaleDecay, lifetime,
				startColor, endColor, particleTexture);
	}

	public HvlParticle createParticleFromSpecs(float xArg, float yArg,
			float xVelArg, float yVelArg,
			float xVelDecayArg, float yVelDecayArg,
			float rotArg, float rotRateArg, float rotVelDecayArg,
			float baseWidthArg, float baseHeightArg, float scaleArg,
			float scaleDecayArg, float lifetimeArg,
			Color startColorArg, Color endColorArg, Texture tArg)
	{
		return new HvlSimpleParticle(xArg, yArg, this, startColorArg, endColorArg,
				tArg, xVelArg, yVelArg, xVelDecayArg, yVelDecayArg, rotArg, rotRateArg,
				rotVelDecayArg, baseWidthArg, baseHeightArg, scaleArg, scaleDecayArg, lifetimeArg);
	}
	
	public float getSpawnRadius() {
		return spawnRadius;
	}

	public void setSpawnRadius(float spawnRadius) {
		this.spawnRadius = spawnRadius;
	}

	public Color getStartColorOne() {
		return startColorOne;
	}

	public void setStartColorOne(Color startColorOne) {
		this.startColorOne = startColorOne;
	}

	public Color getStartColorTwo() {
		return startColorTwo;
	}

	public void setStartColorTwo(Color startColorTwo) {
		this.startColorTwo = startColorTwo;
	}

	public Color getEndColorOne() {
		return endColorOne;
	}

	public void setEndColorOne(Color endColorOne) {
		this.endColorOne = endColorOne;
	}

	public Color getEndColorTwo() {
		return endColorTwo;
	}

	public void setEndColorTwo(Color endColorTwo) {
		this.endColorTwo = endColorTwo;
	}

	public Texture getParticleTexture() {
		return particleTexture;
	}

	public void setParticleTexture(Texture particleTexture) {
		this.particleTexture = particleTexture;
	}

	public float getMinXVel() {
		return minXVel;
	}

	public void setMinXVel(float minXVel) {
		this.minXVel = minXVel;
	}

	public float getMaxXVel() {
		return maxXVel;
	}

	public void setMaxXVel(float maxXVel) {
		this.maxXVel = maxXVel;
	}

	public float getMinYVel() {
		return minYVel;
	}

	public void setMinYVel(float minYVel) {
		this.minYVel = minYVel;
	}

	public float getMaxYVel() {
		return maxYVel;
	}

	public void setMaxYVel(float maxYVel) {
		this.maxYVel = maxYVel;
	}

	public float getxVelDecay() {
		return xVelDecay;
	}

	public void setxVelDecay(float xVelDecay) {
		this.xVelDecay = xVelDecay;
	}

	public float getyVelDecay() {
		return yVelDecay;
	}

	public void setyVelDecay(float yVelDecay) {
		this.yVelDecay = yVelDecay;
	}

	public float getMinRot() {
		return minRot;
	}

	public void setMinRot(float minRot) {
		this.minRot = minRot;
	}

	public float getMaxRot() {
		return maxRot;
	}

	public void setMaxRot(float maxRot) {
		this.maxRot = maxRot;
	}

	public float getMinRotVel() {
		return minRotVel;
	}

	public void setMinRotVel(float minRotVel) {
		this.minRotVel = minRotVel;
	}

	public float getMaxRotVel() {
		return maxRotVel;
	}

	public void setMaxRotVel(float maxRotVel) {
		this.maxRotVel = maxRotVel;
	}

	public float getRotVelDecay() {
		return rotVelDecay;
	}

	public void setRotVelDecay(float rotVelDecay) {
		this.rotVelDecay = rotVelDecay;
	}

	public float getBaseWidth() {
		return baseWidth;
	}

	public void setBaseWidth(float baseWidth) {
		this.baseWidth = baseWidth;
	}

	public float getBaseHeight() {
		return baseHeight;
	}

	public void setBaseHeight(float baseHeight) {
		this.baseHeight = baseHeight;
	}

	public float getMinScale() {
		return minScale;
	}

	public void setMinScale(float minScale) {
		this.minScale = minScale;
	}

	public float getMaxScale() {
		return maxScale;
	}

	public void setMaxScale(float maxScale) {
		this.maxScale = maxScale;
	}

	public float getScaleDecay() {
		return scaleDecay;
	}

	public void setScaleDecay(float scaleDecay) {
		this.scaleDecay = scaleDecay;
	}

	public float getMinLifetime() {
		return minLifetime;
	}

	public void setMinLifetime(float minLifetime) {
		this.minLifetime = minLifetime;
	}

	public float getMaxLifetime() {
		return maxLifetime;
	}

	public void setMaxLifetime(float maxLifetime) {
		this.maxLifetime = maxLifetime;
	}
}
