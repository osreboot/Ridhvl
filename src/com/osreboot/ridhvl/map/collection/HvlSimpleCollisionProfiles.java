package com.osreboot.ridhvl.map.collection;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.newdawn.slick.Color;

import com.osreboot.ridhvl.HvlCoord;
import com.osreboot.ridhvl.HvlMath;
import com.osreboot.ridhvl.map.HvlMap;
import com.osreboot.ridhvl.map.HvlMapCollisionProfile;
import com.osreboot.ridhvl.map.HvlMapRaytraceResult;
import com.osreboot.ridhvl.painter.painter2d.HvlPainter2D;

/**
 * A collection of simple collision profiles for use with common map situations.
 */
public class HvlSimpleCollisionProfiles {
	/**
	 * A collision profile with four solid sides.
	 */
	public static class Square implements HvlMapCollisionProfile {
		float overshoot;

		/**
		 * A basic constructor.
		 * @param overshoot The amount of overshoot to have (makes the corner "stick out" a bit more).
		 */
		public Square(float overshoot) {
			this.overshoot = overshoot;
		}

		@Override
		public List<HvlMapRaytraceResult> raytrace(final HvlCoord start, HvlCoord end, HvlMap map, int layer, int tileX,
				int tileY) {
			List<HvlMapRaytraceResult> collisions = new ArrayList<HvlMapRaytraceResult>();

			{
				HvlCoord segStart = new HvlCoord(map.getX() + (map.getTileDrawWidth() * tileX),
						map.getY() + (map.getTileDrawHeight() * tileY) - overshoot);

				HvlCoord segEnd = new HvlCoord(map.getX() + (map.getTileDrawWidth() * tileX),
						map.getY() + (map.getTileDrawHeight() * (tileY + 1)) + overshoot);

				HvlCoord found = HvlMath.raytrace(start, end, segStart, segEnd);
				if (found != null) {
					collisions.add(new HvlMapRaytraceResult(new HvlMapRaytraceResult.MapTileTraceSource(layer, tileX,
							tileY, map.getTile(layer, tileX, tileY)), found, segStart, segEnd));
				}
			}

			{
				HvlCoord segStart = new HvlCoord(map.getX() + (map.getTileDrawWidth() * tileX) - overshoot,
						map.getY() + (map.getTileDrawHeight() * tileY));

				HvlCoord segEnd = new HvlCoord(map.getX() + (map.getTileDrawWidth() * (tileX + 1)) + overshoot,
						map.getY() + (map.getTileDrawHeight() * tileY));

				HvlCoord found = HvlMath.raytrace(start, end, segStart, segEnd);
				if (found != null) {
					collisions.add(new HvlMapRaytraceResult(new HvlMapRaytraceResult.MapTileTraceSource(layer, tileX,
							tileY, map.getTile(layer, tileX, tileY)), found, segStart, segEnd));
				}
			}

			{
				HvlCoord segStart = new HvlCoord(map.getX() + (map.getTileDrawWidth() * (tileX + 1)),
						map.getY() + (map.getTileDrawHeight() * tileY) - overshoot);

				HvlCoord segEnd = new HvlCoord(map.getX() + (map.getTileDrawWidth() * (tileX + 1)),
						map.getY() + (map.getTileDrawHeight() * (tileY + 1)) + overshoot);

				HvlCoord found = HvlMath.raytrace(start, end, segStart, segEnd);
				if (found != null) {
					collisions.add(new HvlMapRaytraceResult(new HvlMapRaytraceResult.MapTileTraceSource(layer, tileX,
							tileY, map.getTile(layer, tileX, tileY)), found, segStart, segEnd));
				}
			}

			{
				HvlCoord segStart = new HvlCoord(map.getX() + (map.getTileDrawWidth() * tileX) - overshoot,
						map.getY() + (map.getTileDrawHeight() * (tileY + 1)));

				HvlCoord segEnd = new HvlCoord(map.getX() + (map.getTileDrawWidth() * (tileX + 1)) + overshoot,
						map.getY() + (map.getTileDrawHeight() * (tileY + 1)));

				HvlCoord found = HvlMath.raytrace(start, end, segStart, segEnd);
				if (found != null) {
					collisions.add(new HvlMapRaytraceResult(new HvlMapRaytraceResult.MapTileTraceSource(layer, tileX,
							tileY, map.getTile(layer, tileX, tileY)), found, segStart, segEnd));
				}
			}

			return collisions;
		}

		@Override
		public void debugDraw(float delta, HvlMap map, int layer, int tileX, int tileY) {
			HvlPainter2D.hvlDrawLine(map.getX() + (map.getTileDrawWidth() * tileX),
					map.getY() + (map.getTileDrawHeight() * tileY) - overshoot, map.getX() + (map.getTileDrawWidth() * tileX),
					map.getY() + (map.getTileDrawHeight() * (tileY + 1)) + overshoot, Color.red, 3.0f);

			HvlPainter2D.hvlDrawLine(map.getX() + (map.getTileDrawWidth() * tileX) - overshoot,
					map.getY() + (map.getTileDrawHeight() * tileY),
					map.getX() + (map.getTileDrawWidth() * (tileX + 1)) + overshoot,
					map.getY() + (map.getTileDrawHeight() * tileY), Color.red, 3.0f);

			HvlPainter2D.hvlDrawLine(map.getX() + (map.getTileDrawWidth() * (tileX + 1)),
					map.getY() + (map.getTileDrawHeight() * tileY) - overshoot,
					map.getX() + (map.getTileDrawWidth() * (tileX + 1)),
					map.getY() + (map.getTileDrawHeight() * (tileY + 1)) + overshoot, Color.red, 3.0f);

			HvlPainter2D.hvlDrawLine(map.getX() + (map.getTileDrawWidth() * tileX) - overshoot,
					map.getY() + (map.getTileDrawHeight() * (tileY + 1)),
					map.getX() + (map.getTileDrawWidth() * (tileX + 1)) + overshoot,
					map.getY() + (map.getTileDrawHeight() * (tileY + 1)), Color.red, 3.0f);
		}
	}

	/**
	 * A collision profile that lets you specify which portions are solid.
	 */
	public static class CustomMiddle implements HvlMapCollisionProfile {
		private boolean left, right, up, down;
		private boolean ul, ur, ll, lr;

		private float overshoot;

		/**
		 * A basic constructor.
		 * @param l Whether the line of (-1, 0) is solid
		 * @param r Whether the line of (1, 0) is solid
		 * @param u Whether the line of (0, -1) is solid
		 * @param d Whether the line of (0, 1) is solid
		 * @param ul Whether the line of (-1, -1) is solid
		 * @param ur Whether the line of (1, -1) is solid
		 * @param ll Whether the line of (-1, 1) is solid
		 * @param lr Whether the line of (1, 1) is solid
		 * @param overshoot The amount of overshoot to have (makes the outside ends "stick out" a bit more).
		 */
		public CustomMiddle(boolean l, boolean r, boolean u, boolean d, boolean ul, boolean ur, boolean ll, boolean lr,
				float overshoot) {
			left = l;
			right = r;
			up = u;
			down = d;
			this.ul = ul;
			this.ur = ur;
			this.ll = ll;
			this.lr = lr;
			this.overshoot = overshoot;
		}

		@Override
		public List<HvlMapRaytraceResult> raytrace(final HvlCoord start, HvlCoord end, HvlMap map, int layer, int tileX,
				int tileY) {
			List<HvlMapRaytraceResult> collisions = new ArrayList<HvlMapRaytraceResult>();

			if (left) {
				HvlCoord segStart = new HvlCoord(map.getX() + (tileX * map.getTileDrawWidth()) - overshoot,
						map.getY() + (tileY * map.getTileDrawHeight()) + (map.getTileDrawHeight() / 2));

				HvlCoord segEnd = new HvlCoord(map.getX() + (tileX * map.getTileDrawWidth()) + (map.getTileDrawWidth() / 2),
						map.getY() + (tileY * map.getTileDrawHeight()) + (map.getTileDrawHeight() / 2));

				HvlCoord found = HvlMath.raytrace(start, end, segStart, segEnd);
				if (found != null)
					collisions.add(new HvlMapRaytraceResult(new HvlMapRaytraceResult.MapTileTraceSource(layer, tileX,
							tileY, map.getTile(layer, tileX, tileY)), found, segStart, segEnd));
			}

			if (right) {
				HvlCoord segStart = new HvlCoord(map.getX() + (tileX * map.getTileDrawWidth()) + (map.getTileDrawWidth() / 2),
						map.getY() + (tileY * map.getTileDrawHeight()) + (map.getTileDrawHeight() / 2));

				HvlCoord segEnd = new HvlCoord(map.getX() + ((tileX + 1) * map.getTileDrawWidth()) + overshoot,
						map.getY() + (tileY * map.getTileDrawHeight()) + (map.getTileDrawHeight() / 2));

				HvlCoord found = HvlMath.raytrace(start, end, segStart, segEnd);
				if (found != null)
					collisions.add(new HvlMapRaytraceResult(new HvlMapRaytraceResult.MapTileTraceSource(layer, tileX,
							tileY, map.getTile(layer, tileX, tileY)), found, segStart, segEnd));
			}

			if (up) {
				HvlCoord segStart = new HvlCoord(map.getX() + (tileX * map.getTileDrawWidth()) + (map.getTileDrawWidth() / 2),
						map.getY() + (tileY * map.getTileDrawHeight()) - overshoot);

				HvlCoord segEnd = new HvlCoord(map.getX() + (tileX * map.getTileDrawWidth() + (map.getTileDrawWidth() / 2)),
						map.getY() + (tileY * map.getTileDrawHeight()) + (map.getTileDrawHeight() / 2));

				HvlCoord found = HvlMath.raytrace(start, end, segStart, segEnd);
				if (found != null)
					collisions.add(new HvlMapRaytraceResult(new HvlMapRaytraceResult.MapTileTraceSource(layer, tileX,
							tileY, map.getTile(layer, tileX, tileY)), found, segStart, segEnd));
			}

			if (down) {
				HvlCoord segStart = new HvlCoord(map.getX() + (tileX * map.getTileDrawWidth()) + (map.getTileDrawWidth() / 2),
						map.getY() + (tileY * map.getTileDrawHeight()) + (map.getTileDrawHeight() / 2));

				HvlCoord segEnd = new HvlCoord(map.getX() + (tileX * map.getTileDrawWidth() + (map.getTileDrawWidth() / 2)),
						map.getY() + ((tileY + 1) * map.getTileDrawHeight()) + overshoot);

				HvlCoord found = HvlMath.raytrace(start, end, segStart, segEnd);
				if (found != null)
					collisions.add(new HvlMapRaytraceResult(new HvlMapRaytraceResult.MapTileTraceSource(layer, tileX,
							tileY, map.getTile(layer, tileX, tileY)), found, segStart, segEnd));
			}

			if (ul) {
				HvlCoord segStart = new HvlCoord(
						map.getX() + (tileX * map.getTileDrawWidth()) - (overshoot * (float) Math.sqrt(2)),
						map.getY() + (tileY * map.getTileDrawHeight()) - (overshoot * (float) Math.sqrt(2)));

				HvlCoord segEnd = new HvlCoord(map.getX() + (tileX * map.getTileDrawWidth() + (map.getTileDrawWidth() / 2)),
						map.getY() + (tileY * map.getTileDrawHeight()) + (map.getTileDrawHeight() / 2));

				HvlCoord found = HvlMath.raytrace(start, end, segStart, segEnd);
				if (found != null)
					collisions.add(new HvlMapRaytraceResult(new HvlMapRaytraceResult.MapTileTraceSource(layer, tileX,
							tileY, map.getTile(layer, tileX, tileY)), found, segStart, segEnd));
			}

			if (ur) {
				HvlCoord segStart = new HvlCoord(
						map.getX() + ((tileX + 1) * map.getTileDrawWidth()) + (overshoot * (float) Math.sqrt(2)),
						map.getY() + (tileY * map.getTileDrawHeight()) - (overshoot * (float) Math.sqrt(2)));

				HvlCoord segEnd = new HvlCoord(map.getX() + (tileX * map.getTileDrawWidth() + (map.getTileDrawWidth() / 2)),
						map.getY() + (tileY * map.getTileDrawHeight()) + (map.getTileDrawHeight() / 2));

				HvlCoord found = HvlMath.raytrace(start, end, segStart, segEnd);
				if (found != null)
					collisions.add(new HvlMapRaytraceResult(new HvlMapRaytraceResult.MapTileTraceSource(layer, tileX,
							tileY, map.getTile(layer, tileX, tileY)), found, segStart, segEnd));
			}

			if (ll) {
				HvlCoord segStart = new HvlCoord(map.getX() + (tileX * map.getTileDrawWidth()) + (map.getTileDrawWidth() / 2),
						map.getY() + (tileY * map.getTileDrawHeight()) + (map.getTileDrawHeight() / 2));

				HvlCoord segEnd = new HvlCoord(
						map.getX() + (tileX * map.getTileDrawWidth()) - (overshoot * (float) Math.sqrt(2)),
						map.getY() + ((tileY + 1) * map.getTileDrawHeight()) + (overshoot * (float) Math.sqrt(2)));

				HvlCoord found = HvlMath.raytrace(start, end, segStart, segEnd);
				if (found != null)
					collisions.add(new HvlMapRaytraceResult(new HvlMapRaytraceResult.MapTileTraceSource(layer, tileX,
							tileY, map.getTile(layer, tileX, tileY)), found, segStart, segEnd));
			}

			if (lr) {
				HvlCoord segStart = new HvlCoord(map.getX() + (tileX * map.getTileDrawWidth()) + (map.getTileDrawWidth() / 2),
						map.getY() + (tileY * map.getTileDrawHeight()) + (map.getTileDrawHeight() / 2));

				HvlCoord segEnd = new HvlCoord(
						map.getX() + ((tileX + 1) * map.getTileDrawWidth()) + (overshoot * (float) Math.sqrt(2)),
						map.getY() + ((tileY + 1) * map.getTileDrawHeight()) + (overshoot * (float) Math.sqrt(2)));

				HvlCoord found = HvlMath.raytrace(start, end, segStart, segEnd);
				if (found != null)
					collisions.add(new HvlMapRaytraceResult(new HvlMapRaytraceResult.MapTileTraceSource(layer, tileX,
							tileY, map.getTile(layer, tileX, tileY)), found, segStart, segEnd));
			}

			return collisions;
		}

		@Override
		public void debugDraw(float delta, HvlMap map, int layer, int tileX, int tileY) {
			if (left)
				HvlPainter2D.hvlDrawLine(map.getX() + (tileX * map.getTileDrawWidth()) - overshoot,
						map.getY() + (tileY * map.getTileDrawHeight()) + (map.getTileDrawHeight() / 2),
						map.getX() + (tileX * map.getTileDrawWidth()) + (map.getTileDrawWidth() / 2),
						map.getY() + (tileY * map.getTileDrawHeight()) + (map.getTileDrawHeight() / 2), Color.red, 4.0f);
			if (right)
				HvlPainter2D.hvlDrawLine(map.getX() + (tileX * map.getTileDrawWidth()) + (map.getTileDrawWidth() / 2),
						map.getY() + (tileY * map.getTileDrawHeight()) + (map.getTileDrawHeight() / 2),
						map.getX() + ((tileX + 1) * map.getTileDrawWidth()) + overshoot,
						map.getY() + (tileY * map.getTileDrawHeight()) + (map.getTileDrawHeight() / 2), Color.red, 4.0f);
			if (up)
				HvlPainter2D.hvlDrawLine(map.getX() + (tileX * map.getTileDrawWidth()) + (map.getTileDrawWidth() / 2),
						map.getY() + (tileY * map.getTileDrawHeight()) - overshoot,
						map.getX() + (tileX * map.getTileDrawWidth() + (map.getTileDrawWidth() / 2)),
						map.getY() + (tileY * map.getTileDrawHeight()) + (map.getTileDrawHeight() / 2), Color.red, 4.0f);
			if (down)
				HvlPainter2D.hvlDrawLine(map.getX() + (tileX * map.getTileDrawWidth()) + (map.getTileDrawWidth() / 2),
						map.getY() + (tileY * map.getTileDrawHeight()) + (map.getTileDrawHeight() / 2),
						map.getX() + (tileX * map.getTileDrawWidth() + (map.getTileDrawWidth() / 2)),
						map.getY() + ((tileY + 1) * map.getTileDrawHeight()) + overshoot, Color.red, 4.0f);
			if (ul)
				HvlPainter2D.hvlDrawLine(map.getX() + (tileX * map.getTileDrawWidth()) - (overshoot * (float) Math.sqrt(2)),
						map.getY() + (tileY * map.getTileDrawHeight()) - (overshoot * (float) Math.sqrt(2)),
						map.getX() + (tileX * map.getTileDrawWidth() + (map.getTileDrawWidth() / 2)),
						map.getY() + (tileY * map.getTileDrawHeight()) + (map.getTileDrawHeight() / 2), Color.red, 4.0f);

			if (ur)
				HvlPainter2D.hvlDrawLine(
						map.getX() + ((tileX + 1) * map.getTileDrawWidth()) + (overshoot * (float) Math.sqrt(2)),
						map.getY() + (tileY * map.getTileDrawHeight()) - (overshoot * (float) Math.sqrt(2)),
						map.getX() + (tileX * map.getTileDrawWidth() + (map.getTileDrawWidth() / 2)),
						map.getY() + (tileY * map.getTileDrawHeight()) + (map.getTileDrawHeight() / 2), Color.red, 4.0f);

			if (ll)
				HvlPainter2D.hvlDrawLine(map.getX() + (tileX * map.getTileDrawWidth()) + (map.getTileDrawWidth() / 2),
						map.getY() + (tileY * map.getTileDrawHeight()) + (map.getTileDrawHeight() / 2),
						map.getX() + (tileX * map.getTileDrawWidth()) - (overshoot * (float) Math.sqrt(2)),
						map.getY() + ((tileY + 1) * map.getTileDrawHeight()) + (overshoot * (float) Math.sqrt(2)),
						Color.red, 4.0f);

			if (lr)
				HvlPainter2D.hvlDrawLine(map.getX() + (tileX * map.getTileDrawWidth()) + (map.getTileDrawWidth() / 2),
						map.getY() + (tileY * map.getTileDrawHeight()) + (map.getTileDrawHeight() / 2),
						map.getX() + ((tileX + 1) * map.getTileDrawWidth()) + (overshoot * (float) Math.sqrt(2)),
						map.getY() + ((tileY + 1) * map.getTileDrawHeight()) + (overshoot * (float) Math.sqrt(2)),
						Color.red, 4.0f);
		}
	}

	/**
	 * A collision profile that is a vertical line centered in the tile.
	 */
	public static class Vertical implements HvlMapCollisionProfile {

		private float overshoot;

		/**
		 * A basic constructor.
		 * @param overshoot The amount of overshoot to have (makes the ends "stick out" a bit more).
		 */
		public Vertical(float overshoot) {
			this.overshoot = overshoot;
		}

		@Override
		public List<HvlMapRaytraceResult> raytrace(HvlCoord start, HvlCoord end, HvlMap map, int layer, int tileX, int tileY) {
			HvlCoord segStart = new HvlCoord(map.getX() + (map.getTileDrawWidth() * tileX) + (map.getTileDrawWidth() / 2),
					map.getY() + (map.getTileDrawHeight() * tileY) - overshoot);

			HvlCoord segEnd = new HvlCoord(map.getX() + (map.getTileDrawWidth() * tileX) + (map.getTileDrawWidth() / 2),
					map.getY() + (map.getTileDrawHeight() * (tileY + 1)) + overshoot);

			List<HvlMapRaytraceResult> tr = new LinkedList<HvlMapRaytraceResult>();
			
			HvlCoord found = HvlMath.raytrace(start, end, segStart, segEnd);
			if (found != null)
				tr.add(new HvlMapRaytraceResult(new HvlMapRaytraceResult.MapTileTraceSource(layer, tileX, tileY, map.getTile(layer, tileX, tileY)), found, start, end));
			
			return tr;
		}

		@Override
		public void debugDraw(float delta, HvlMap map, int layer, int tileX, int tileY) {
			HvlPainter2D.hvlDrawLine(map.getX() + (map.getTileDrawWidth() * tileX) + (map.getTileDrawWidth() / 2),
					map.getY() + (map.getTileDrawHeight() * tileY) - overshoot,
					map.getX() + (map.getTileDrawWidth() * tileX) + (map.getTileDrawWidth() / 2),
					map.getY() + (map.getTileDrawHeight() * (tileY + 1) + overshoot), Color.red, 3.0f);
		}
	}

	/**
	 * A collision profile that is a horizontal line centered in the tile.
	 */
	public static class Horizontal implements HvlMapCollisionProfile {

		private float overshoot;

		/**
		 * A basic constructor.
		 * @param overshoot The amount of overshoot to have (makes the ends "stick out" a bit more).
		 */
		public Horizontal(float overshoot) {
			this.overshoot = overshoot;
		}

		@Override
		public void debugDraw(float delta, HvlMap map, int layer, int tileX, int tileY) {
			HvlPainter2D.hvlDrawLine(map.getX() + (map.getTileDrawWidth() * tileX) - overshoot,
					map.getY() + (map.getTileDrawHeight() * tileY) + (map.getTileDrawHeight() / 2),
					map.getX() + (map.getTileDrawWidth() * (tileX + 1)) + overshoot,
					map.getY() + (map.getTileDrawHeight() * tileY) + (map.getTileDrawHeight() / 2), Color.red, 3.0f);
		}

		@Override
		public List<HvlMapRaytraceResult> raytrace(HvlCoord start, HvlCoord end, HvlMap map, int layer, int tileX, int tileY) {
			HvlCoord segStart = new HvlCoord(map.getX() + (map.getTileDrawWidth() * tileX) - overshoot,
					map.getY() + (map.getTileDrawHeight() * tileY) + (map.getTileDrawHeight() / 2));

			HvlCoord segEnd = new HvlCoord(map.getX() + (map.getTileDrawWidth() * (tileX + 1)) + overshoot,
					map.getY() + (map.getTileDrawHeight() * tileY) + (map.getTileDrawHeight() / 2));


			List<HvlMapRaytraceResult> tr = new LinkedList<HvlMapRaytraceResult>();
			
			HvlCoord found = HvlMath.raytrace(start, end, segStart, segEnd);
			if (found != null)
				tr.add(new HvlMapRaytraceResult(new HvlMapRaytraceResult.MapTileTraceSource(layer, tileX, tileY, map.getTile(layer, tileX, tileY)), found, start, end));
			
			return tr;
		}
	}
}
