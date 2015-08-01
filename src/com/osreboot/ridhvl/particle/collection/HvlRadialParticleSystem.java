package com.osreboot.ridhvl.particle.collection;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

import com.osreboot.ridhvl.HvlColorUtil;
import com.osreboot.ridhvl.HvlCoord;
import com.osreboot.ridhvl.HvlMath;
import com.osreboot.ridhvl.particle.HvlParticle;
import com.osreboot.ridhvl.particle.HvlParticleSystem;
import com.osreboot.ridhvl.particle.correlation.HvlParticleCorrelator;

public class HvlRadialParticleSystem extends HvlParticleSystem {

	private boolean isColorCoordinated;
	private float spawnRadius;
	private Color startColorOne, startColorTwo;
	private Color endColorOne, endColorTwo;
	private List<Texture> particleTextures;
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

	public HvlRadialParticleSystem(float xArg, float yArg, float pWidthArg,
			float pHeightArg, Texture... tArg) {
		super(xArg, yArg);
		spawnRadius = 0;
		startColorOne = startColorTwo = Color.white;
		endColorOne = endColorTwo = Color.white;
		particleTextures = new ArrayList<>();
		for (Texture t : tArg)
		{
			particleTextures.add(t);
		}
		minXVel = maxXVel = 0;
		minYVel = maxYVel = 0;
		xVelDecay = yVelDecay = 0;
		minRot = 0;
		maxRot = 360;
		minRotVel = maxRotVel = 0;
		rotVelDecay = 0f;
		baseWidth = pWidthArg;
		baseHeight = pHeightArg;
		minScale = maxScale = 1.0f;
		scaleDecay = 0f;
		minLifetime = maxLifetime = 5.0f;
	}

	@Override
	public HvlParticle generateParticle() {
		HvlCoord spawnPos = HvlMath.randomPointInCircle(spawnRadius);
		float xVel = HvlMath.randomFloatBetween(minXVel, maxXVel);
		float yVel = HvlMath.randomFloatBetween(minYVel, maxYVel);
		float rot = HvlMath.randomFloatBetween(minRot, maxRot);
		float rotVel = HvlMath.randomFloatBetween(minRotVel, maxRotVel);
		float scale = HvlMath.randomFloatBetween(minScale, maxScale);
		float lifetime = HvlMath.randomFloatBetween(minLifetime, maxLifetime);
		Color startColor = HvlColorUtil.lerpColor(startColorOne, startColorTwo,
				(float) Math.random());
		Color endColor = HvlColorUtil.lerpColor(endColorOne, endColorTwo,
				(float) Math.random());

		if (isColorCoordinated) {
			startColor = HvlColorUtil.lerpColor(startColorOne, startColorTwo,
					(float) Math.random());
			endColor = HvlColorUtil.lerpColor(endColorOne, endColorTwo,
					(float) Math.random());
		} else {
			startColor = HvlColorUtil.lerpColor(startColorOne, startColorTwo,
					(float) Math.random(), (float) Math.random(),
					(float) Math.random(), (float) Math.random());
			endColor = HvlColorUtil.lerpColor(endColorOne, endColorTwo,
					(float) Math.random(), (float) Math.random(),
					(float) Math.random(), (float) Math.random());
		}
		
		Texture particleTexture = particleTextures.get(HvlMath
				.randomIntBetween(0, particleTextures.size()));
		
		HvlParticle p = createParticleFromSpecs(getX() + spawnPos.x,
				getY() + spawnPos.y, xVel, yVel, xVelDecay, yVelDecay, rot,
				rotVel, rotVelDecay, baseWidth, baseHeight, scale, scaleDecay,
				lifetime, startColor, endColor, particleTexture);
		for (HvlParticleCorrelator corr : correlators)
		{
			corr.correlate(p);
		}
		return p;
	}

	public HvlParticle createParticleFromSpecs(float xArg, float yArg,
			float xVelArg, float yVelArg, float xVelDecayArg,
			float yVelDecayArg, float rotArg, float rotRateArg,
			float rotVelDecayArg, float baseWidthArg, float baseHeightArg,
			float scaleArg, float scaleDecayArg, float lifetimeArg,
			Color startColorArg, Color endColorArg, Texture tArg) {
		return new HvlSimpleParticle(xArg, yArg, this, startColorArg,
				endColorArg, tArg, xVelArg, yVelArg, xVelDecayArg,
				yVelDecayArg, rotArg, rotRateArg, rotVelDecayArg, baseWidthArg,
				baseHeightArg, scaleArg, scaleDecayArg, lifetimeArg);
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

	public List<Texture> getParticleTexture() {
		return particleTextures;
	}

	public void setParticleTexture(List<Texture> particleTextures) {
		this.particleTextures = particleTextures;
	}
	
	public void addParticleTexture(Texture particleTexture) {
		particleTextures.add(particleTexture);
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

	public boolean isColorCoordinated() {
		return isColorCoordinated;
	}

	public void setColorCoordinated(boolean isColorCoordinated) {
		this.isColorCoordinated = isColorCoordinated;
	}

	public void setStartColor(Color color) {
		setStartColorOne(color);
		setStartColorTwo(color);
	}

	public void setEndColor(Color color) {
		setEndColorOne(color);
		setEndColorTwo(color);
	}
	
	public void setColor(Color color) {
		setStartColor(color);
		setEndColor(color);
	}

	public void addTexture(Texture texture)	{
		particleTextures.add(texture);
	}
	
	public void setXVel(float xVel) {
		setMinXVel(xVel);
		setMaxXVel(xVel);
	}

	public void setYVel(float yVel) {
		setMinYVel(yVel);
		setMaxYVel(yVel);
	}

	public void setRot(float rot) {
		setMinRot(rot);
		setMaxRot(rot);
	}
	
	public void setRotVel(float rotVel) {
		setMinRotVel(rotVel);
		setMaxRotVel(rotVel);
	}
	
	public void setScale(float scale) {
		setMinScale(scale);
		setMaxScale(scale);
	}
	
	public void setLifetime(float lifetime) {
		setMinLifetime(lifetime);
		setMaxLifetime(lifetime);
	}
}
