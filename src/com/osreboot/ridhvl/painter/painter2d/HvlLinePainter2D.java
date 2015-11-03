package com.osreboot.ridhvl.painter.painter2d;

import static org.lwjgl.opengl.GL11.GL_LINES;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL11.glColor4f;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glLineWidth;
import static org.lwjgl.opengl.GL11.glVertex2f;

import org.newdawn.slick.Color;

class HvlLinePainter2D {

	protected static void hvlDrawLine(float x1, float y1, float x2, float y2, Color c){
		hvlDrawLine(x1, y1, x2, y2, c, 1.0f);
	}
	
	protected static void hvlDrawLine(float x1, float y1, float x2, float y2, Color c, float width){
		glLineWidth(width);
		glColor4f(c.r, c.g, c.b, c.a);
		glBindTexture(GL_TEXTURE_2D, 0);
		glBegin(GL_LINES);
		glVertex2f(x1, y1);
		glVertex2f(x2, y2);
		glEnd();
	}
	
	//TODO hvlDrawLine(float x1, float y1, float x2, float y2, float thickness, Color c)
	
}
