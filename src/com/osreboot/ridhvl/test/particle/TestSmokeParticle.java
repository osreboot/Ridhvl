package com.osreboot.ridhvl.test.particle;

import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

import com.osreboot.ridhvl.HvlColorUtil;
import com.osreboot.ridhvl.painter.painter2d.HvlPainter2D;
import com.osreboot.ridhvl.particle.HvlParticle;
import com.osreboot.ridhvl.particle.HvlParticleSystem;

public class TestSmokeParticle extends HvlParticle {

	private final float minScale = 0.25f;
	
	private final float baseX = 16, baseY = 16;
	
	private float lifetime;
	private final float rotVel;
	private float theta;
	private final Texture t;
	private Color c;
	private float scale;
	
	private float yVel;
	private final float minYVel = -32.0f, maxYVel = -8.0f;
	private final float maxXVar = 16.0f, maxYVar = 16.0f;
	
	public TestSmokeParticle(float xArg, float yArg, HvlParticleSystem parentArg, Texture tArg) {
		super(xArg, yArg, parentArg);
		
		c = HvlColorUtil.lerpColor(Color.darkGray, Color.lightGray, (float) Math.random());
		this.rotVel = ((float)Math.random() * 720f) - 360f;
		this.t = tArg;
		scale = minScale + ((float) Math.random() * (1.0f - minScale));
		setX(parentArg.getX() + ((float) Math.random() * maxXVar * 2) - maxXVar);
		setY(parentArg.getY() + ((float) Math.random() * maxYVar * 2) - maxYVar);
		lifetime = 5.0f + ((float) Math.random() * (10.0f - 5.0f));
		yVel = minYVel + ((float) Math.random() * (maxYVel - minYVel));
	}
	
	@Override
	public void draw(float delta)
	{
		super.draw(delta);
		theta += rotVel * delta;
		
		setY(getY() + (yVel * delta));
		
		HvlPainter2D.hvlRotate(getX(), getY(), theta);
		
		HvlPainter2D.hvlDrawQuad(getX() - ((baseX * scale) / 2),
				getY() - ((baseY * scale) / 2),
				baseX * scale, baseY * scale, t, c);
		
		HvlPainter2D.hvlResetRotation();
	}

	@Override
	public boolean shouldBeDestroyed() {
		return timeAlive >= lifetime;
	}

}
