package com.osreboot.ridhvl.painter.painter2d;


import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

class HvlQuadPainter2D {
	
	protected static void hvlDrawQuad(float x, float y, float xl, float yl, Texture t){
		GL11.glColor4f(1, 1, 1, 1);
		t.bind();
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glTexCoord2f(0, 0);
		GL11.glVertex2f(x, y);
		GL11.glTexCoord2f(1, 0);
		GL11.glVertex2f(x + xl, y);
		GL11.glTexCoord2f(1, 1);
		GL11.glVertex2f(x + xl, y + yl);
		GL11.glTexCoord2f(0, 1);
		GL11.glVertex2f(x, y + yl);
		GL11.glEnd();
	}
	
	protected static void hvlDrawQuad(float x, float y, float xl, float yl, Texture t, Color c){
		GL11.glColor4f(c.r, c.g, c.b, c.a);
		t.bind();
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glTexCoord2f(0, 0);
		GL11.glVertex2f(x, y);
		GL11.glTexCoord2f(1, 0);
		GL11.glVertex2f(x + xl, y);
		GL11.glTexCoord2f(1, 1);
		GL11.glVertex2f(x + xl, y + yl);
		GL11.glTexCoord2f(0, 1);
		GL11.glVertex2f(x, y + yl);
		GL11.glEnd();
	}
	
	protected static void hvlDrawQuad(float x, float y, float xl, float yl, float uvx1, float uvy1, float uvx2, float uvy2, Texture t){
		GL11.glColor4f(1, 1, 1, 1);
		t.bind();
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glTexCoord2f(uvx1, uvy1);
		GL11.glVertex2f(x, y);
		GL11.glTexCoord2f(uvx2, uvy1);
		GL11.glVertex2f(x + xl, y);
		GL11.glTexCoord2f(uvx2, uvy2);
		GL11.glVertex2f(x + xl, y + yl);
		GL11.glTexCoord2f(uvx1, uvy2);
		GL11.glVertex2f(x, y + yl);
		GL11.glEnd();
	}
	
	protected static void hvlDrawQuad(float x, float y, float xl, float yl, float uvx1, float uvy1, float uvx2, float uvy2, Texture t, Color c){
		GL11.glColor4f(c.r, c.g, c.b, c.a);
		t.bind();
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glTexCoord2f(uvx1, uvy1);
		GL11.glVertex2f(x, y);
		GL11.glTexCoord2f(uvx2, uvy1);
		GL11.glVertex2f(x + xl, y);
		GL11.glTexCoord2f(uvx2, uvy2);
		GL11.glVertex2f(x + xl, y + yl);
		GL11.glTexCoord2f(uvx1, uvy2);
		GL11.glVertex2f(x, y + yl);
		GL11.glEnd();
	}
	
	protected static void hvlDrawQuad(float x, float y, float xl, float yl, int textureID){
		GL11.glColor4f(1, 1, 1, 1);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureID);
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glTexCoord2f(0, 0);
		GL11.glVertex2f(x, y);
		GL11.glTexCoord2f(1, 0);
		GL11.glVertex2f(x + xl, y);
		GL11.glTexCoord2f(1, 1);
		GL11.glVertex2f(x + xl, y + yl);
		GL11.glTexCoord2f(0, 1);
		GL11.glVertex2f(x, y + yl);
		GL11.glEnd();
	}
	
	//TODO hvlDrawQuad(float x, float y, float xl, float yl, Color c)
	
}
