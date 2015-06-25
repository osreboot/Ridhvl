package com.osreboot.ridhvl.particle.collection;

import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

import com.osreboot.ridhvl.HvlColorUtil;
import com.osreboot.ridhvl.painter.painter2d.HvlPainter2D;
import com.osreboot.ridhvl.particle.HvlParticle;
import com.osreboot.ridhvl.particle.HvlParticleSystem;

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
		
	public HvlSimpleParticle(float xArg, float yArg,
			HvlParticleSystem parentArg, Color startColor, Color endColor,
			Texture texture, float xVel, float yVel, float xVelDecay,
			float yVelDecay, float rot, float rotVel, float rotVelDecay,
			float baseWidth, float baseHeight, float scale, float scaleDecay,
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

	@Override
	public void draw(float delta)
	{
		super.draw(delta);
		setX(getX() + (xVel * delta));
		setY(getY() + (yVel * delta));
		
		xVel *= Math.pow(Math.E, delta * xVelDecay);
		yVel *= Math.pow(Math.E, delta * yVelDecay);
		
		rot += rotVel * delta;
		rotVel *= Math.pow(Math.E, delta * rotVelDecay);
		
		scale *= Math.pow(Math.E, delta * scaleDecay);
		
		HvlPainter2D.hvlRotate(getX(), getY(), rot);
		
		HvlPainter2D.hvlDrawQuad(getX() - (baseWidth * scale * 0.5f),
				getY() - (baseHeight * scale * 0.5f),
				baseWidth * scale, baseHeight * scale, texture, HvlColorUtil.lerpColor(startColor, endColor, timeAlive / lifetime));
		
		HvlPainter2D.hvlResetRotation();
	}

	@Override
	public boolean shouldBeDestroyed() {
		return timeAlive > lifetime;
	}

}
