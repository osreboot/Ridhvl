package com.osreboot.ridhvl.particle.correlation.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.osreboot.ridhvl.HvlCoord2D;
import com.osreboot.ridhvl.HvlMath;
import com.osreboot.ridhvl.particle.HvlParticle;
import com.osreboot.ridhvl.particle.collection.HvlSimpleParticle;
import com.osreboot.ridhvl.particle.correlation.HvlParticleCorrelator;

public class HvlParticleAttractionCorrelator extends HvlParticleCorrelator {

	private HvlCoord2D[] points;
	private float strength;
	
	public HvlParticleAttractionCorrelator(float strength, HvlCoord2D... points)
	{
		setContinuous(true);
		this.strength = strength;
		this.points = points;
	}

	public HvlParticleAttractionCorrelator() {
		setContinuous(true);
	}

	@Override
	public void correlate(final HvlParticle in, float delta) {
		HvlSimpleParticle sp = (HvlSimpleParticle) in;
		
		List<HvlCoord2D> pList = new ArrayList<>();
		for (HvlCoord2D c : points) {
			pList.add(c);
		}

		Collections.sort(pList, new Comparator<HvlCoord2D>() {
			@Override
			public int compare(HvlCoord2D arg0, HvlCoord2D arg1) {
				return (int) Math.signum(HvlMath.distance(arg0.x, arg0.y, in.getX(), in.getY()) - HvlMath.distance(arg1.x, arg1.y, in.getX(), in.getY()));
			}
		});
		
		HvlCoord2D vel = new HvlCoord2D(pList.get(0).x - in.getX(), pList.get(0).y - in.getY()).normalize().mult(strength);
		
		sp.setxVel(vel.x);
		sp.setyVel(vel.y);
	}

	public HvlCoord2D[] getPoints() {
		return points;
	}

	public void setPoints(HvlCoord2D[] points) {
		this.points = points;
	}

	public float getStrength() {
		return strength;
	}

	public void setStrength(float strength) {
		this.strength = strength;
	}
}
