package com.osreboot.ridhvl.painter.painter2d;


import static org.lwjgl.opengl.GL11.*;

import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

import com.osreboot.ridhvl.painter.HvlRenderFrame;

class HvlQuadPainter2D {

	protected static void hvlDrawQuad(float x, float y, float xl, float yl, Texture t){
		glColor4f(1, 1, 1, 1);
		t.bind();
		constructTexturedQuad(x, y, xl, yl);
	}

	protected static void hvlDrawQuad(float x, float y, float xl, float yl, Texture t, Color c){
		glColor4f(c.r, c.g, c.b, c.a);
		t.bind();
		constructTexturedQuad(x, y, xl, yl);
	}

	protected static void hvlDrawQuad(float x, float y, float xl, float yl, float uvx1, float uvy1, float uvx2, float uvy2, Texture t){
		glColor4f(1, 1, 1, 1);
		t.bind();
		constructTexturedQuad(x, y, xl, yl, uvx1, uvy1, uvx2, uvy2);
	}

	protected static void hvlDrawQuad(float x, float y, float xl, float yl, float uvx1, float uvy1, float uvx2, float uvy2, Texture t, Color c){
		glColor4f(c.r, c.g, c.b, c.a);
		t.bind();
		constructTexturedQuad(x, y, xl, yl, uvx1, uvy1, uvx2, uvy2);
	}

	protected static void hvlDrawQuad(float x, float y, float xl, float yl, HvlRenderFrame renderFrame){
		glColor4f(1, 1, 1, 1);
		glBindTexture(GL_TEXTURE_2D, renderFrame.getTextureID());
		constructTexturedQuad(x, y + yl, xl, -yl);
	}

	//TODO hvlDrawQuad(float x, float y, float xl, float yl, Color c)
	
	private static void constructTexturedQuad(float x, float y, float xl, float yl){
		glBegin(GL_QUADS);
		glTexCoord2f(0, 0);
		glVertex2f(x, y);
		glTexCoord2f(1, 0);
		glVertex2f(x + xl, y);
		glTexCoord2f(1, 1);
		glVertex2f(x + xl, y + yl);
		glTexCoord2f(0, 1);
		glVertex2f(x, y + yl);
		glEnd();
	}

	private static void constructTexturedQuad(float x, float y, float xl, float yl, float uvx1, float uvy1, float uvx2, float uvy2){
		glBegin(GL_QUADS);
		glTexCoord2f(uvx1, uvy1);
		glVertex2f(x, y);
		glTexCoord2f(uvx2, uvy1);
		glVertex2f(x + xl, y);
		glTexCoord2f(uvx2, uvy2);
		glVertex2f(x + xl, y + yl);
		glTexCoord2f(uvx1, uvy2);
		glVertex2f(x, y + yl);
		glEnd();
	}

}
