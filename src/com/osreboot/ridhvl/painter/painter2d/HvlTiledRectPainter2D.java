package com.osreboot.ridhvl.painter.painter2d;

import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

class HvlTiledRectPainter2D {
	// format:
	// left,right,top,bottom
	// OR
	// left and right (1.0 - left), top and bottom (1.0 - top)

	protected static void hvlDrawTiledQuad(float x, float y, float w, float h, float lrWidth, float udHeight, String format, Texture t) {
		hvlDrawTiledQuad(x, y, w, h, lrWidth, lrWidth, udHeight, udHeight, format, t);
	}
	
	protected static void hvlDrawTiledQuad(float x, float y, float w, float h, float lWidth, float rWidth, float uHeight, float dHeight, String format, Texture t) {
		hvlDrawTiledQuad(x, y, w, h, lWidth, rWidth, uHeight, dHeight, format, t, Color.white);
	}

	protected static void hvlDrawTiledQuad(float x, float y, float w, float h, float lWidth, float rWidth, float uHeight, float dHeight, String format, Texture t, Color c) {
		float[] uvs = parseFormatString(format);
		
		HvlPainter2D.hvlDrawQuad(x, y, w, h, Color.black);
		
		// Upper left
		HvlPainter2D.hvlDrawQuad(x, y, lWidth * Math.signum(w), uHeight * Math.signum(h), 0f, 0f, uvs[0], uvs[2], t, c);
		
		// Upper right
		HvlPainter2D.hvlDrawQuad(x + w - (rWidth * Math.signum(w)), y, rWidth * Math.signum(w), uHeight * Math.signum(h), uvs[1], 0f, 1f, uvs[2], t, c);
		
		// Lower left
		HvlPainter2D.hvlDrawQuad(x, y + h - (dHeight * Math.signum(h)), lWidth * Math.signum(w), dHeight * Math.signum(h), 0f, uvs[3], uvs[0], 1f, t, c);
		
		// Lower right
		HvlPainter2D.hvlDrawQuad(x + w - (rWidth * Math.signum(w)), y + h - (dHeight * Math.signum(h)), rWidth * Math.signum(w), dHeight * Math.signum(h), uvs[1], uvs[3], 1f, 1f, t, c);
		
		// Top
		HvlPainter2D.hvlDrawQuad(x + (lWidth * Math.signum(w)), y, w - ((lWidth + rWidth) * Math.signum(w)), uHeight * Math.signum(h), uvs[0], 0f, uvs[1], uvs[2], t, c);
		
		// Bottom
		HvlPainter2D.hvlDrawQuad(x + (lWidth * Math.signum(w)), y + h - (dHeight * Math.signum(h)), w - ((lWidth + rWidth) * Math.signum(w)), dHeight * Math.signum(h), uvs[0], uvs[3], uvs[1], 1f, t, c);
		
		// Left
		HvlPainter2D.hvlDrawQuad(x, y + (uHeight * Math.signum(h)), lWidth * Math.signum(w), h - ((uHeight + dHeight) * Math.signum(h)), 0, uvs[2], uvs[0], uvs[3], t, c);
		
		// Right
		HvlPainter2D.hvlDrawQuad(x + w - (rWidth * Math.signum(w)), y + (uHeight * Math.signum(h)), rWidth * Math.signum(w), h - ((uHeight + dHeight) * Math.signum(h)), uvs[1], uvs[2], 1f, uvs[3], t, c);
		
		// Center
		HvlPainter2D.hvlDrawQuad(x + (lWidth * Math.signum(w)), y + (uHeight * Math.signum(h)), w - ((lWidth + rWidth) * Math.signum(w)), h - ((uHeight + dHeight) * Math.signum(h)), uvs[0], uvs[2], uvs[1], uvs[3], t, c);
	}
	
	protected static void hvlDrawTiledQuad(float x, float y, float w, float h, float lrWidth, float udHeight, String format, Texture t, Color c) {
		hvlDrawTiledQuad(x, y, w, h, lrWidth, lrWidth, udHeight, udHeight, format, t, c);
	}

	protected static void hvlDrawTiledQuadc(float x, float y, float w, float h, float lrWidth, float udHeight, String format, Texture t) {
		hvlDrawTiledQuadc(x, y, w, h, lrWidth, lrWidth, udHeight, udHeight, format, t);
	}
	
	protected static void hvlDrawTiledQuadc(float x, float y, float w, float h, float lWidth, float rWidth, float uHeight, float dHeight, String format, Texture t) {
		hvlDrawTiledQuadc(x, y, w, h, lWidth, rWidth, uHeight, dHeight, format, t, Color.white);
	}

	protected static void hvlDrawTiledQuadc(float x, float y, float w, float h, float lWidth, float rWidth, float uHeight, float dHeight, String format, Texture t, Color c) {
		hvlDrawTiledQuad(x - (w / 2), y - (h / 2), w, h, lWidth, rWidth, uHeight, dHeight, format, t, c);
	}
	
	protected static void hvlDrawTiledQuadc(float x, float y, float w, float h, float lrWidth, float udHeight, String format, Texture t, Color c) {
		hvlDrawTiledQuadc(x, y, w, h, lrWidth, lrWidth, udHeight, udHeight, format, t, c);
	}

	public static float[] parseFormatString(String format) {
		// returns in the order left, right, top, bottom
		String[] parts = format.split(",");

		switch (parts.length) {
		case 4: {
			return new float[] { Float.parseFloat(parts[0]), Float.parseFloat(parts[1]), Float.parseFloat(parts[2]), Float.parseFloat(parts[3]) };
		}
		case 2: {
			return new float[] { Float.parseFloat(parts[0]), 1.0f - Float.parseFloat(parts[0]), Float.parseFloat(parts[1]), 1.0f - Float.parseFloat(parts[1]) };
		}
		default:
			throw new IllegalArgumentException("Format must have either two or four numerical values separated by commas.");
		}
	}
}
