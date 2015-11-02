package com.osreboot.ridhvl.map.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.newdawn.slick.Color;

import com.osreboot.ridhvl.HvlCoord;
import com.osreboot.ridhvl.HvlMath;
import com.osreboot.ridhvl.map.HvlMap;
import com.osreboot.ridhvl.map.HvlTileCollisionProfile;
import com.osreboot.ridhvl.painter.painter2d.HvlPainter2D;

public class HvlSimpleCollisionProfiles {
	public static class Square extends HvlTileCollisionProfile {
		@Override
		public HvlCoord raytrace(final HvlCoord start, HvlCoord end, HvlMap map, int layer, int tileX, int tileY) {
			List<HvlCoord> collisions = new ArrayList<HvlCoord>();

			{
				HvlCoord segStart = new HvlCoord(map.getX() + (map.getTileWidth() * tileX),
						map.getY() + (map.getTileHeight() * tileY));

				HvlCoord segEnd = new HvlCoord(map.getX() + (map.getTileWidth() * tileX),
						map.getY() + (map.getTileHeight() * (tileY + 1)));

				HvlCoord found = HvlMath.raytrace(start, end, segStart, segEnd);
				if (found != null)
					collisions.add(found);
			}

			{
				HvlCoord segStart = new HvlCoord(map.getX() + (map.getTileWidth() * tileX),
						map.getY() + (map.getTileHeight() * tileY));

				HvlCoord segEnd = new HvlCoord(map.getX() + (map.getTileWidth() * (tileX + 1)),
						map.getY() + (map.getTileHeight() * tileY));

				HvlCoord found = HvlMath.raytrace(start, end, segStart, segEnd);
				if (found != null)
					collisions.add(found);
			}

			{
				HvlCoord segStart = new HvlCoord(map.getX() + (map.getTileWidth() * (tileX + 1)),
						map.getY() + (map.getTileHeight() * tileY));

				HvlCoord segEnd = new HvlCoord(map.getX() + (map.getTileWidth() * (tileX + 1)),
						map.getY() + (map.getTileHeight() * (tileY + 1)));

				HvlCoord found = HvlMath.raytrace(start, end, segStart, segEnd);
				if (found != null)
					collisions.add(found);
			}

			{
				HvlCoord segStart = new HvlCoord(map.getX() + (map.getTileWidth() * tileX),
						map.getY() + (map.getTileHeight() * (tileY + 1)));

				HvlCoord segEnd = new HvlCoord(map.getX() + (map.getTileWidth() * (tileX + 1)),
						map.getY() + (map.getTileHeight() * (tileY + 1)));

				HvlCoord found = HvlMath.raytrace(start, end, segStart, segEnd);
				if (found != null)
					collisions.add(found);
			}

			if (collisions.isEmpty())
				return null;

			Collections.sort(collisions, new Comparator<HvlCoord>() {

				@Override
				public int compare(HvlCoord o1, HvlCoord o2) {
					float d1 = HvlMath.distance(start.x, start.y, o1.x, o1.y);
					float d2 = HvlMath.distance(start.x, start.y, o2.x, o2.y);
					if (d1 < d2)
						return -1;
					if (d1 > d2)
						return 1;
					return 0;
				}
			});

			return collisions.get(0);
		}

		@Override
		public void debugDraw(float delta, HvlMap map, int layer, int tileX, int tileY) {
			HvlPainter2D.hvlDrawLine(map.getX() + (map.getTileWidth() * tileX),
					map.getY() + (map.getTileHeight() * tileY), map.getX() + (map.getTileWidth() * tileX),
					map.getY() + (map.getTileHeight() * (tileY + 1)), Color.red, 3.0f);

			HvlPainter2D.hvlDrawLine(map.getX() + (map.getTileWidth() * tileX),
					map.getY() + (map.getTileHeight() * tileY), map.getX() + (map.getTileWidth() * (tileX + 1)),
					map.getY() + (map.getTileHeight() * tileY), Color.red, 3.0f);

			HvlPainter2D.hvlDrawLine(map.getX() + (map.getTileWidth() * (tileX + 1)),
					map.getY() + (map.getTileHeight() * tileY), map.getX() + (map.getTileWidth() * (tileX + 1)),
					map.getY() + (map.getTileHeight() * (tileY + 1)), Color.red, 3.0f);

			HvlPainter2D.hvlDrawLine(map.getX() + (map.getTileWidth() * tileX),
					map.getY() + (map.getTileHeight() * (tileY + 1)), map.getX() + (map.getTileWidth() * (tileX + 1)),
					map.getY() + (map.getTileHeight() * (tileY + 1)), Color.red, 3.0f);
		}
	}

	public static class CustomMiddle extends HvlTileCollisionProfile {
		private boolean left, right, up, down;

		public CustomMiddle(boolean l, boolean r, boolean u, boolean d) {
			left = l;
			right = r;
			up = u;
			down = d;
		}

		@Override
		public HvlCoord raytrace(final HvlCoord start, HvlCoord end, HvlMap map, int layer, int tileX, int tileY) {
			List<HvlCoord> collisions = new ArrayList<HvlCoord>();

			if (left) {
				HvlCoord segStart = new HvlCoord(map.getX() + (tileX * map.getTileWidth()),
						map.getY() + (tileY * map.getTileHeight()) + (map.getTileHeight() / 2));

				HvlCoord segEnd = new HvlCoord(map.getX() + (tileX * map.getTileWidth()) + (map.getTileWidth() / 2),
						map.getY() + (tileY * map.getTileHeight()) + (map.getTileHeight() / 2));

				HvlCoord found = HvlMath.raytrace(start, end, segStart, segEnd);
				if (found != null)
					collisions.add(found);
			}

			if (right) {
				HvlCoord segStart = new HvlCoord(map.getX() + (tileX * map.getTileWidth()) + (map.getTileWidth() / 2),
						map.getY() + (tileY * map.getTileHeight()) + (map.getTileHeight() / 2));

				HvlCoord segEnd = new HvlCoord(map.getX() + ((tileX + 1) * map.getTileWidth()),
						map.getY() + (tileY * map.getTileHeight()) + (map.getTileHeight() / 2));

				HvlCoord found = HvlMath.raytrace(start, end, segStart, segEnd);
				if (found != null)
					collisions.add(found);
			}

			if (up) {
				HvlCoord segStart = new HvlCoord(map.getX() + (tileX * map.getTileWidth()) + (map.getTileWidth() / 2),
						map.getY() + (tileY * map.getTileHeight()));

				HvlCoord segEnd = new HvlCoord(map.getX() + (tileX * map.getTileWidth() + (map.getTileWidth() / 2)),
						map.getY() + (tileY * map.getTileHeight()) + (map.getTileHeight() / 2));

				HvlCoord found = HvlMath.raytrace(start, end, segStart, segEnd);
				if (found != null)
					collisions.add(found);
			}

			if (down) {
				HvlCoord segStart = new HvlCoord(map.getX() + (tileX * map.getTileWidth()) + (map.getTileWidth() / 2),
						map.getY() + (tileY * map.getTileHeight()) + (map.getTileHeight() / 2));

				HvlCoord segEnd = new HvlCoord(map.getX() + (tileX * map.getTileWidth() + (map.getTileWidth() / 2)),
						map.getY() + ((tileY + 1) * map.getTileHeight()));

				HvlCoord found = HvlMath.raytrace(start, end, segStart, segEnd);
				if (found != null)
					collisions.add(found);
			}

			if (collisions.isEmpty())
				return null;

			Collections.sort(collisions, new Comparator<HvlCoord>() {

				@Override
				public int compare(HvlCoord o1, HvlCoord o2) {
					float d1 = HvlMath.distance(start.x, start.y, o1.x, o1.y);
					float d2 = HvlMath.distance(start.x, start.y, o2.x, o2.y);
					if (d1 < d2)
						return -1;
					if (d1 > d2)
						return 1;
					return 0;
				}
			});

			return collisions.get(0);
		}

		@Override
		public void debugDraw(float delta, HvlMap map, int layer, int tileX, int tileY) {
			if (left)
				HvlPainter2D.hvlDrawLine(map.getX() + (tileX * map.getTileWidth()),
						map.getY() + (tileY * map.getTileHeight()) + (map.getTileHeight() / 2),
						map.getX() + (tileX * map.getTileWidth()) + (map.getTileWidth() / 2),
						map.getY() + (tileY * map.getTileHeight()) + (map.getTileHeight() / 2), Color.red, 4.0f);
			if (right)
				HvlPainter2D.hvlDrawLine(map.getX() + (tileX * map.getTileWidth()) + (map.getTileWidth() / 2),
						map.getY() + (tileY * map.getTileHeight()) + (map.getTileHeight() / 2),
						map.getX() + ((tileX + 1) * map.getTileWidth()),
						map.getY() + (tileY * map.getTileHeight()) + (map.getTileHeight() / 2), Color.red, 4.0f);
			if (up)
				HvlPainter2D.hvlDrawLine(map.getX() + (tileX * map.getTileWidth()) + (map.getTileWidth() / 2),
						map.getY() + (tileY * map.getTileHeight()),
						map.getX() + (tileX * map.getTileWidth() + (map.getTileWidth() / 2)),
						map.getY() + (tileY * map.getTileHeight()) + (map.getTileHeight() / 2), Color.red, 4.0f);
			if (down)
				HvlPainter2D.hvlDrawLine(map.getX() + (tileX * map.getTileWidth()) + (map.getTileWidth() / 2),
						map.getY() + (tileY * map.getTileHeight()) + (map.getTileHeight() / 2),
						map.getX() + (tileX * map.getTileWidth() + (map.getTileWidth() / 2)),
						map.getY() + ((tileY + 1) * map.getTileHeight()), Color.red, 4.0f);
		}
	}

	public static class Vertical extends HvlTileCollisionProfile {

		@Override
		public HvlCoord raytrace(HvlCoord start, HvlCoord end, HvlMap map, int layer, int tileX, int tileY) {
			HvlCoord segStart = new HvlCoord(map.getX() + (map.getTileWidth() * tileX) + (map.getTileWidth() / 2),
					map.getY() + (map.getTileHeight() * tileY));

			HvlCoord segEnd = new HvlCoord(map.getX() + (map.getTileWidth() * tileX) + (map.getTileWidth() / 2),
					map.getY() + (map.getTileHeight() * (tileY + 1)));

			return HvlMath.raytrace(start, end, segStart, segEnd);
		}

		@Override
		public void debugDraw(float delta, HvlMap map, int layer, int tileX, int tileY) {
			HvlPainter2D.hvlDrawLine(map.getX() + (map.getTileWidth() * tileX) + (map.getTileWidth() / 2),
					map.getY() + (map.getTileHeight() * tileY),
					map.getX() + (map.getTileWidth() * tileX) + (map.getTileWidth() / 2),
					map.getY() + (map.getTileHeight() * (tileY + 1)), Color.red, 3.0f);
		}
	}

	public static class Horizontal extends HvlTileCollisionProfile {

		@Override
		public void debugDraw(float delta, HvlMap map, int layer, int tileX, int tileY) {
			HvlPainter2D.hvlDrawLine(map.getX() + (map.getTileWidth() * tileX),
					map.getY() + (map.getTileHeight() * tileY) + (map.getTileHeight() / 2),
					map.getX() + (map.getTileWidth() * (tileX + 1)),
					map.getY() + (map.getTileHeight() * tileY) + (map.getTileHeight() / 2), Color.red, 3.0f);
		}

		@Override
		public HvlCoord raytrace(HvlCoord start, HvlCoord end, HvlMap map, int layer, int tileX, int tileY) {
			HvlCoord segStart = new HvlCoord(map.getX() + (map.getTileWidth() * tileX),
					map.getY() + (map.getTileHeight() * tileY) + (map.getTileHeight() / 2));

			HvlCoord segEnd = new HvlCoord(map.getX() + (map.getTileWidth() * (tileX + 1)),
					map.getY() + (map.getTileHeight() * tileY) + (map.getTileHeight() / 2));

			return HvlMath.raytrace(start, end, segStart, segEnd);
		}
	}
}
