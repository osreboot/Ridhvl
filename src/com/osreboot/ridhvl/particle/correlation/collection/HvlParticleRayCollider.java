package com.osreboot.ridhvl.particle.correlation.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.osreboot.ridhvl.HvlCoord;
import com.osreboot.ridhvl.HvlMath;
import com.osreboot.ridhvl.particle.HvlParticle;
import com.osreboot.ridhvl.particle.collection.HvlSimpleParticle;
import com.osreboot.ridhvl.particle.correlation.HvlParticleCorrelator;
import com.osreboot.ridhvl.tile.HvlLayeredTileMap;
import com.osreboot.ridhvl.tile.HvlTilemapCollisionUtil;
import com.osreboot.ridhvl.tile.HvlTilemapCollisionUtil.LineSegment;

public class HvlParticleRayCollider extends HvlParticleCorrelator {

	private HvlLayeredTileMap map;
	private int layer;
	private float bounce;
	
	public HvlParticleRayCollider(HvlLayeredTileMap map, int layer, float bounce) {
		setContinuous(true);
		this.map = map;
		this.layer = layer;
		this.bounce = bounce;
	}

	@Override
	public void correlate(HvlParticle in, float delta) {
		HvlSimpleParticle smp = (HvlSimpleParticle) in;
		try {
			HvlCoord vel = new HvlCoord(smp.getxVel(), smp.getyVel());
			HvlCoord pos = new HvlCoord(smp.getX(), smp.getY());
			applyCollision(delta, pos, vel, bounce);
			smp.setX(pos.x);
			smp.setY(pos.y);
			smp.setxVel(vel.x);
			smp.setyVel(vel.y);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void applyCollision(float delta, HvlCoord pos, HvlCoord vel, float bounce) throws Exception {
		for (int i = 0; i < 100; i++) {
			List<LineSegment> segs = HvlTilemapCollisionUtil.getAllNearbySides(map, pos.x, pos.y, 1, layer);

			Map<HvlCoord, LineSegment> colls = new HashMap<>();

			for (LineSegment seg : segs) {
				HvlCoord coll = HvlMath.raytrace(pos, new HvlCoord(pos.x + (vel.x * delta), pos.y + (vel.y * delta)), seg.start, seg.end);

				if (coll != null) {
					colls.put(coll, seg);
				}
			}
			if (colls.isEmpty())
				return;

			final HvlCoord tempPos = pos.clone();

			List<HvlCoord> keys = new ArrayList<>();
			for (HvlCoord key : colls.keySet()) {
				if (key == null)
					continue;

				keys.add(key);
			}

			Collections.sort(keys, new Comparator<HvlCoord>() {
				@Override
				public int compare(HvlCoord arg0, HvlCoord arg1) {
					return (int) Math.signum(HvlMath.distance(arg0.x, arg0.y, tempPos.x, tempPos.y) - HvlMath.distance(arg1.x, arg1.y, tempPos.x, tempPos.y));
				}
			});

			HvlCoord coll = keys.get(0);
			LineSegment seg = colls.get(coll);

			float angle = (float) Math.atan2(pos.y - coll.y, pos.x - coll.x);

			float normal = (float) ((Math.PI / 2) + Math.atan2(seg.end.y - seg.start.y, seg.end.x - seg.start.x) % Math.PI);

			float angleOfReflection = normal - angle;

			float oldVel = new HvlCoord(vel.x, vel.y).length();

			float newAngle = angle + 2 * angleOfReflection;

			HvlCoord newDir = new HvlCoord((float) Math.cos(newAngle), (float) Math.sin(newAngle)).normalize().mult(oldVel);
			vel.x = newDir.x * bounce;
			vel.y = newDir.y * bounce;
			HvlCoord mod = vel.normalizeNew();

			pos.x = coll.x + (mod.x * 0.001f);
			pos.y = coll.y + (mod.y * 0.001f);
		}
		throw new Exception("Looped too many times.");
	}
}
