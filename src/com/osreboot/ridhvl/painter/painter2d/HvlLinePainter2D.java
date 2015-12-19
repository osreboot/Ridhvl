package com.osreboot.ridhvl.painter.painter2d;

import static com.osreboot.ridhvl.painter.painter2d.HvlPainter2D.*;
import static org.lwjgl.opengl.GL11.*;

import org.newdawn.slick.Color;

class HvlLinePainter2D {

	protected static void hvlDrawLine(float x1, float y1, float x2, float y2, Color c){
		hvlDrawLine(x1, y1, x2, y2, c, 1.0f);
	}

	protected static void hvlDrawLinec(float x, float y, float xl, float yl, Color c){
		hvlDrawLine(x - (xl/2), y - (yl/2), x + (xl/2), y + (yl/2), c, 1.0f);
	}
	
	protected static void hvlDrawLinec(float x, float y, float xl, float yl, Color c, float width){
		hvlDrawLine(x - (xl/2), y - (yl/2), x + (xl/2), y + (yl/2), c, width);
	}
	
	@SuppressWarnings("deprecation")
	protected static void hvlDrawLine(float x1, float y1, float x2, float y2, Color c, float width){
		hvlForceRefresh();
		glLineWidth(width);
		glColor4f(c.r, c.g, c.b, c.a);
		glBindTexture(GL_TEXTURE_2D, 0);
		glBegin(GL_LINES);
		glVertex2f(x1, y1);
		glVertex2f(x2, y2);
		glEnd();
		hvlForceRefresh();
	}
	
	//TODO hvlDrawLine(float x1, float y1, float x2, float y2, float thickness, Color c)
	
}
