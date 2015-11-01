package com.osreboot.ridhvl.map.collection;

import org.newdawn.slick.Color;

import com.osreboot.ridhvl.map.HvlMap;
import com.osreboot.ridhvl.map.HvlTileCollisionProfile;
import com.osreboot.ridhvl.painter.painter2d.HvlPainter2D;

public class HvlMiddleCollisionProfile {
	public static class Square extends HvlTileCollisionProfile {

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
	}
}
