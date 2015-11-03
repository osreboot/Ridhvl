package com.osreboot.ridhvl.particle.correlation.collection;

import com.osreboot.ridhvl.particle.HvlParticle;
import com.osreboot.ridhvl.particle.collection.HvlSimpleParticle;
import com.osreboot.ridhvl.particle.correlation.HvlParticleCorrelator;
import com.osreboot.ridhvl.tile.HvlLayeredTileMap;

@Deprecated
public class HvlParticlePointCollider extends HvlParticleCorrelator {

	private HvlLayeredTileMap map;
	private int layer;
	private float bounce;

	public HvlParticlePointCollider(HvlLayeredTileMap map, int layer, float bounce) {
		setContinuous(true);
		this.map = map;
		this.layer = layer;
		this.bounce = bounce;
	}

	@Override
	public void correlate(HvlParticle in, float delta) {
		HvlSimpleParticle p = (HvlSimpleParticle) in;
		if (map.isTileInLocation(p.getX() + (p.getxVel() * delta), p.getY(), layer))
			p.setxVel(p.getxVel() * -bounce);
		if (map.isTileInLocation(p.getX(), p.getY() + (p.getyVel() * delta), layer))
			p.setyVel(p.getyVel() * -bounce);
	}

}
