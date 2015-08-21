package com.osreboot.ridhvl.tile;

import java.util.ArrayList;
import java.util.List;

import com.osreboot.ridhvl.HvlCoord;
import com.osreboot.ridhvl.tile.collection.HvlSimpleTile;

public class HvlTilemapCollisionUtil {
	private static final List<Integer> upperLeftCorners, upperRightCorners, lowerRightCorners, lowerLeftCorners;

	static {
		upperLeftCorners = new ArrayList<>();
		upperRightCorners = new ArrayList<>();
		lowerRightCorners = new ArrayList<>();
		lowerLeftCorners = new ArrayList<>();
	}

	public static class LineSegment {
		public HvlCoord start, end;

		public LineSegment(HvlCoord start, HvlCoord end) {
			this.start = start;
			this.end = end;
		}

		public LineSegment(float startX, float startY, float endX, float endY) {
			this.start = new HvlCoord(startX, startY);
			this.end = new HvlCoord(endX, endY);
		}
	}

	// Note: Depends on HvlSimpleTiles for diagonals
	public static List<LineSegment> getAllNearbySides(HvlLayeredTileMap map, float xArg, float yArg, int radiusArg) {
		List<LineSegment> tr = new ArrayList<>();

		int tileX = map.toTileX(xArg);
		int tileY = map.toTileY(yArg);

		for (int x = -radiusArg; x < radiusArg + 1; x++) {
			for (int y = -radiusArg; y < radiusArg + 1; y++) {
				if (tileX + x < 0 || tileX + x >= map.getLayer(1).getMapWidth() || tileY + y < 0 || tileY + y >= map.getLayer(1).getMapHeight())
					continue;

				if (!map.isTileInLocation(tileX + x, tileY + y, 1))
					continue;

				HvlSimpleTile tile = (HvlSimpleTile) map.getLayer(1).getTile(tileX + x, tileY + y);

				if (tile == null)
					continue;

				if (upperLeftCorners.contains(tile.getTile())) {
					tr.add(new LineSegment(map.toWorldX(tileX + x + 1), map.toWorldY(tileY + y), map.toWorldX(tileX + x), map.toWorldY(tileY + y + 1)));
				} else if (upperRightCorners.contains(tile.getTile())) {
					tr.add(new LineSegment(map.toWorldX(tileX + x), map.toWorldY(tileY + y), map.toWorldX(tileX + x + 1), map.toWorldY(tileY + y + 1)));
				} else if (lowerRightCorners.contains(tile.getTile())) {
					tr.add(new LineSegment(map.toWorldX(tileX + x), map.toWorldY(tileY + y + 1), map.toWorldX(tileX + x + 1), map.toWorldY(tileY + y)));
				} else if (lowerLeftCorners.contains(tile.getTile())) {
					tr.add(new LineSegment(map.toWorldX(tileX + x), map.toWorldY(tileY + y), map.toWorldX(tileX + x + 1), map.toWorldY(tileY + y + 1)));
				} else // Flat collision
				{
					if (x != 0 || y != 0) {
						if (x < 0) {
							HvlCoord wallStart = new HvlCoord(map.toWorldX(tileX + x + 1), map.toWorldY(tileY + y));
							HvlCoord wallEnd = new HvlCoord(map.toWorldX(tileX + x + 1), map.toWorldY(tileY + y + 1));
							tr.add(new LineSegment(wallStart, wallEnd));
						}
						if (x > 0) {
							HvlCoord wallStart = new HvlCoord(map.toWorldX(tileX + x), map.toWorldY(tileY + y));
							HvlCoord wallEnd = new HvlCoord(map.toWorldX(tileX + x), map.toWorldY(tileY + y + 1));
							tr.add(new LineSegment(wallStart, wallEnd));
						}
						if (y < 0) {
							HvlCoord wallStart = new HvlCoord(map.toWorldX(tileX + x), map.toWorldY(tileY + y + 1));
							HvlCoord wallEnd = new HvlCoord(map.toWorldX(tileX + x + 1), map.toWorldY(tileY + y + 1));
							tr.add(new LineSegment(wallStart, wallEnd));
						}
						if (y > 0) {
							HvlCoord wallStart = new HvlCoord(map.toWorldX(tileX + x), map.toWorldY(tileY + y));
							HvlCoord wallEnd = new HvlCoord(map.toWorldX(tileX + x + 1), map.toWorldY(tileY + y));
							tr.add(new LineSegment(wallStart, wallEnd));
						}
					}
				}
			}
		}

		return tr;
	}

	public static void registerCornerSet(int ul, int ur, int ll, int lr) {
		upperLeftCorners.add(ul);
		upperRightCorners.add(ur);
		lowerLeftCorners.add(ll);
		lowerRightCorners.add(lr);
	}
}
