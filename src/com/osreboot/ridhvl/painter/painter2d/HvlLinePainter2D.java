package com.osreboot.ridhvl.painter.painter2d;

import static org.lwjgl.opengl.GL11.*;

import org.newdawn.slick.Color;

class HvlLinePainter2D {

	protected static void hvlDrawLine(float x1, float y1, float x2, float y2, Color c){
		glColor4f(c.r, c.g, c.b, c.a);
		glBindTexture(GL_TEXTURE_2D, 0);
		glBegin(GL_LINES);
		glVertex2f(x1, y1);
		glVertex2f(x2, y2);
		glEnd();
	}
	
	//TODO hvlDrawLine(float x1, float y1, float x2, float y2, float thickness, Color c)
	
}
