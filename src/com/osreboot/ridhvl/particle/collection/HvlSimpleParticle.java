package com.osreboot.ridhvl.particle.collection;

import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

import com.osreboot.ridhvl.HvlColorUtil;
import com.osreboot.ridhvl.HvlCoord;
import com.osreboot.ridhvl.painter.painter2d.HvlPainter2D;
import com.osreboot.ridhvl.particle.HvlParticle;
import com.osreboot.ridhvl.particle.HvlParticleSystem;

/**
 * This is an HvlParticle implementation that implements a very large number of
 * features. This is designed to work with the particle system implementations
 * in <code>com.osreboot.ridhvl.particle.collection</code>.
 */
public class HvlSimpleParticle extends HvlParticle {

	private Color startColor, endColor;
	private Texture texture;
	private float xVel, yVel;
	private float xVelDecay, yVelDecay;
	private float rot;
	private float rotVel;
	private float rotVelDecay;
	private float baseWidth, baseHeight;
	private float scale;
	private float scaleDecay;
	private float lifetime;

	/**
	 * A constructor for an HvlSimpleParticle that sets every property.
	 * 
	 * @param xArg
	 *            The X position of the particle.
	 * @param yArg
	 *            The Y position of the particle.
	 * @param parentArg
	 *            The HvlParticleSystem that spawned this particle.
	 * @param startColor
	 *            The color at the beginning of the particle's life.
	 * @param endColor
	 *            The color at the ending of the particle's life.
	 * @param texture
	 *            The texture of the particle.
	 * @param xVel
	 *            The X velocity of the particle.
	 * @param yVel
	 *            The Y velocity of the particle.
	 * @param xVelDecay
	 *            The X velocity decay of the particle (0 means constant
	 *            velocity)
	 * @param yVelDecay
	 *            The Y velocity decay of the particle (0 means constant
	 *            velocity)
	 * @param rot
	 *            The rotation (in degrees) of the particle.
	 * @param rotVel
	 *            The rotational velocity (in degrees/sec) of the particle.
	 * @param rotVelDecay
	 *            The rotational velocity decay of the particle (0 means
	 *            constant velocity).
	 * @param baseWidth
	 *            The base width to draw at (if scale is 1)
	 * @param baseHeight
	 *            The base height to draw at (if scale is 1)
	 * @param scale
	 *            The scale of the particle.
	 * @param scaleDecay
	 *            The scale decay of the particle (0 means constant scale)
	 * @param lifetime
	 *            How long this particle will live.
	 */
	public HvlSimpleParticle(float xArg, float yArg, HvlParticleSystem parentArg, Color startColor, Color endColor, Texture texture, float xVel, float yVel,
			float xVelDecay, float yVelDecay, float rot, float rotVel, float rotVelDecay, float baseWidth, float baseHeight, float scale, float scaleDecay,
			float lifetime) {
		super(xArg, yArg, parentArg);
		this.startColor = startColor;
		this.endColor = endColor;
		this.texture = texture;
		this.xVel = xVel;
		this.yVel = yVel;
		this.xVelDecay = xVelDecay;
		this.yVelDecay = yVelDecay;
		this.rot = rot;
		this.rotVel = rotVel;
		this.rotVelDecay = rotVelDecay;
		this.baseWidth = baseWidth;
		this.baseHeight = baseHeight;
		this.scale = scale;
		this.scaleDecay = scaleDecay;
		this.lifetime = lifetime;
	}

	/**
	 * A constructor for an HvlSimpleParticle that sets every property.
	 * 
	 * @param xArg
	 *            The X position of the particle.
	 * @param yArg
	 *            The Y position of the particle.
	 * @param parentArg
	 *            The HvlParticleSystem that spawned this particle.
	 * @param startColor
	 *            The color at the beginning of the particle's life.
	 * @param endColor
	 *            The color at the ending of the particle's life.
	 * @param texture
	 *            The texture of the particle.
	 * @param xVel
	 *            The X velocity of the particle.
	 * @param yVel
	 *            The Y velocity of the particle.
	 * @param xVelDecay
	 *            The X velocity decay of the particle (0 means constant
	 *            velocity)
	 * @param yVelDecay
	 *            The Y velocity decay of the particle (0 means constant
	 *            velocity)
	 * @param rot
	 *            The rotation (in degrees) of the particle.
	 * @param rotVel
	 *            The rotational velocity (in degrees/sec) of the particle.
	 * @param rotVelDecay
	 *            The rotational velocity decay of the particle (0 means
	 *            constant velocity).
	 * @param baseWidth
	 *            The base width to draw at (if scale is 1)
	 * @param baseHeight
	 *            The base height to draw at (if scale is 1)
	 * @param scale
	 *            The scale of the particle.
	 * @param scaleDecay
	 *            The scale decay of the particle (0 means constant scale)
	 * @param lifetime
	 *            How long this particle will live.
	 */
	public HvlSimpleParticle(HvlCoord pos, HvlParticleSystem parentArg, Color startColor, Color endColor, Texture texture, float xVel, float yVel,
			float xVelDecay, float yVelDecay, float rot, float rotVel, float rotVelDecay, float baseWidth, float baseHeight, float scale, float scaleDecay,
			float lifetime) {
		super(pos, parentArg);
		this.startColor = startColor;
		this.endColor = endColor;
		this.texture = texture;
		this.xVel = xVel;
		this.yVel = yVel;
		this.xVelDecay = xVelDecay;
		this.yVelDecay = yVelDecay;
		this.rot = rot;
		this.rotVel = rotVel;
		this.rotVelDecay = rotVelDecay;
		this.baseWidth = baseWidth;
		this.baseHeight = baseHeight;
		this.scale = scale;
		this.scaleDecay = scaleDecay;
		this.lifetime = lifetime;
	}

	@Override
	/**
	 * This gets called every update loop and is used for non-drawing-related
	 * updates.
	 * 
	 * @param delta
	 *            The time (in seconds) since the last update loop.
	 */
	public void update(float delta) {
		super.update(delta);

		setX(getX() + (xVel * delta));
		setY(getY() + (yVel * delta));

		xVel *= Math.pow(Math.E, delta * xVelDecay);
		yVel *= Math.pow(Math.E, delta * yVelDecay);

		rot += rotVel * delta;
		rotVel *= Math.pow(Math.E, delta * rotVelDecay);

		scale *= Math.pow(Math.E, delta * scaleDecay);
	}

	@Override
	/**
	 * This gets called every update loop and is used to draw the particle.
	 * 
	 * @param delta
	 *            The time (in seconds) since the last update loop.
	 */
	public void draw(float delta) {
		super.draw(delta);

		HvlPainter2D.hvlRotate(getX(), getY(), rot);

		HvlPainter2D.hvlDrawQuad(getX() - (baseWidth * scale * 0.5f), getY() - (baseHeight * scale * 0.5f), baseWidth * scale, baseHeight * scale, texture,
				HvlColorUtil.lerpColor(startColor, endColor, getTimeAlive() / lifetime));

		HvlPainter2D.hvlResetRotation();
	}

	@Override
	/**
	 * This returns whether the particle should be removed by the particle
	 * system in the next update loop.
	 * 
	 * @return Whether this particle should be removed.
	 */
	public boolean shouldBeDestroyed() {
		if (lifetime < 0) return false;
		return getTimeAlive() > lifetime;
	}

	/**
	 * Gets the color of the particle at the start of it's life.
	 * @return The start color of the particle.
	 */
	public Color getStartColor() {
		return startColor;
	}

	/**
	 * Sets the color of the particle at the start of it's life.
	 * @param startColor The new start color of the particle.
	 */
	public void setStartColor(Color startColor) {
		this.startColor = startColor;
	}

	/**
	 * Gets the color of the particle at the end of it's life.
	 * @return The color of the particle at the end of it's life.
	 */
	public Color getEndColor() {
		return endColor;
	}

	/**
	 * Sets the color of the particle at the end of it's life.
	 * @param endColor The new end color of the particle.
	 */
	public void setEndColor(Color endColor) {
		this.endColor = endColor;
	}
	
	/**
	 * Sets the color of the particle for it's whole life (start to end).
	 * @param color The new color of the particle.
	 */
	public void setColor(Color color) {
		setStartColor(color);
		setEndColor(color);
	}

	/**
	 * Gets the texture of the particle.
	 * @return The texture of the particle.
	 */
	public Texture getTexture() {
		return texture;
	}

	/**
	 * Sets the texture of the particle.
	 * @param texture The new texture of the particle.
	 */
	public void setTexture(Texture texture) {
		this.texture = texture;
	}

	/**
	 * Gets the X velocity of the particle.
	 * @return The X velocity of the particle.
	 */
	public float getxVel() {
		return xVel;
	}

	/**
	 * Sets the X velocity of the particle.
	 * @param xVel The new X velocity of the particle.
	 */
	public void setxVel(float xVel) {
		this.xVel = xVel;
	}

	/**
	 * Gets the Y velocity of the particle.
	 * @return The Y velocity of the particle.
	 */
	public float getyVel() {
		return yVel;
	}

	/**
	 * Sets the Y velocity of the particle.
	 * @param yVel The new Y velocity of the particle.
	 */
	public void setyVel(float yVel) {
		this.yVel = yVel;
	}

	/**
	 * Gets the X velocity decay of the particle (0 means constant velocity).
	 * @return The X velocity decay of the particle.
	 */
	public float getxVelDecay() {
		return xVelDecay;
	}

	/**
	 * Sets the X velocity decay of the particle (0 means constant velocity).
	 * @param xVelDecay The new X velocity decay of the particle.
	 */
	public void setxVelDecay(float xVelDecay) {
		this.xVelDecay = xVelDecay;
	}

	/**
	 * Gets the Y velocity decay of the particle (0 means constant velocity).
	 * @return The Y velocity decay of the particle.
	 */
	public float getyVelDecay() {
		return yVelDecay;
	}

	/**
	 * Sets the Y velocity decay of the particle (0 means constant velocity).
	 * @param yVelDecay The new Y velocity decay of the particle.
	 */
	public void setyVelDecay(float yVelDecay) {
		this.yVelDecay = yVelDecay;
	}
	
	/**
	 * Sets the velocity decay (X and Y) of the particle.
	 * @param velDecay The new velocity decay of the particle.
	 */
	public void setVelDecay(float velDecay) {
		setxVelDecay(velDecay);
		setyVelDecay(velDecay);
	}

	/**
	 * Gets the rotation of the particle (in degrees).
	 * @return The rotation of the particle.
	 */
	public float getRot() {
		return rot;
	}

	/**
	 * Sets the rotation of the particle (in degrees).
	 * @param rot The new rotation of the particle.
	 */
	public void setRot(float rot) {
		this.rot = rot;
	}

	/**
	 * Gets the rotational velocity of the particle (in degrees/second).
	 * @return The rotational velocity of the particle.
	 */
	public float getRotVel() {
		return rotVel;
	}

	/**
	 * Sets the rotational velocity of the particle (in degrees/second).
	 * @param rotVel The new rotation velocity of the particle.
	 */
	public void setRotVel(float rotVel) {
		this.rotVel = rotVel;
	}

	/**
	 * Gets the rotational velocity decay of the particle.
	 * @return The rotational velocity decay of the particle.
	 */
	public float getRotVelDecay() {
		return rotVelDecay;
	}

	/**
	 * Sets the rotational velocity decay of the particle.
	 * @param rotVelDecay The rotational velocity decay of the particle.
	 */
	public void setRotVelDecay(float rotVelDecay) {
		this.rotVelDecay = rotVelDecay;
	}

	/**
	 * Gets the base width of the particle (width when scale = 1)
	 * @return The base width of the particle.
	 */
	public float getBaseWidth() {
		return baseWidth;
	}

	/**
	 * Sets the base width of the particle (width when scale = 1)
	 * @param baseWidth The new base width of the particle.
	 */
	public void setBaseWidth(float baseWidth) {
		this.baseWidth = baseWidth;
	}

	/**
	 * Gets the base height of the particle (height when scale = 1)
	 * @return The base height of the particle.
	 */
	public float getBaseHeight() {
		return baseHeight;
	}

	/**
	 * Sets the base height of the particle (height when scale = 1)
	 * @param baseHeight The new base height of the particle.
	 */
	public void setBaseHeight(float baseHeight) {
		this.baseHeight = baseHeight;
	}

	/**
	 * Gets the scale of the particle.
	 * @return The scale of the particle.
	 */
	public float getScale() {
		return scale;
	}

	/**
	 * Sets the scale of the particle.
	 * @param scale The new scale of the particle.
	 */
	public void setScale(float scale) {
		this.scale = scale;
	}

	/**
	 * Gets the scale decay of the particle.
	 * @return The scale decay of the particle.
	 */
	public float getScaleDecay() {
		return scaleDecay;
	}

	/**
	 * Sets the scale decay of the particle.
	 * @param scaleDecay The new scale decay of the particle.
	 */
	public void setScaleDecay(float scaleDecay) {
		this.scaleDecay = scaleDecay;
	}

	/**
	 * Gets the lifetime of the particle.
	 * @return The lifetime of the particle.
	 */
	public float getLifetime() {
		return lifetime;
	}

	/**
	 * Sets the lifetime of the particle.
	 * @param lifetime The new lifetime of the particle.
	 */
	public void setLifetime(float lifetime) {
		this.lifetime = lifetime;
	}

}