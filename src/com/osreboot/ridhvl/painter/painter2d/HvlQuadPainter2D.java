package com.osreboot.ridhvl.painter.painter2d;


import static com.osreboot.ridhvl.painter.painter2d.HvlPainter2D.hvlForceRefresh;
import static org.lwjgl.opengl.GL11.GL_LINEAR;
import static org.lwjgl.opengl.GL11.GL_NEAREST;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_MAG_FILTER;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_MIN_FILTER;
import static org.lwjgl.opengl.GL11.GL_TRIANGLE_FAN;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL11.glColor4f;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glTexParameteri;
import static org.lwjgl.opengl.GL11.glVertex2f;

import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

import com.osreboot.ridhvl.HvlCoord2D;
import com.osreboot.ridhvl.painter.HvlAnimatedTextureArray;
import com.osreboot.ridhvl.painter.HvlAnimatedTextureUV;
import com.osreboot.ridhvl.painter.HvlRenderFrame;

class HvlQuadPainter2D {
	
	protected static void hvlDrawQuad(float x, float y, float xl, float yl, Texture t){
		glColor4f(1, 1, 1, 1);
		t.bind();
		constructTexturedQuad(x, y, xl, yl, 0, 0, t.getWidth(), t.getHeight());
	}

	protected static void hvlDrawQuad(float x, float y, float xl, float yl, Texture t, Color c){
		glColor4f(c.r, c.g, c.b, c.a);
		t.bind();
		constructTexturedQuad(x, y, xl, yl, 0, 0, t.getWidth(), t.getHeight());
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
		constructTexturedQuad(x, y + yl, xl, -yl, 0, 0, 1, 1);
	}

	protected static void hvlDrawQuad(float x, float y, float xl, float yl, HvlAnimatedTextureArray texture){
		glColor4f(1, 1, 1, 1);
		texture.getCurrentTexture().bind();
		constructTexturedQuad(x, y, xl, yl, 0, 0, texture.getCurrentTexture().getWidth(), texture.getCurrentTexture().getHeight());
	}
	
	protected static void hvlDrawQuad(float x, float y, float xl, float yl, HvlAnimatedTextureArray texture, Color c){
		glColor4f(c.r, c.g, c.b, c.a);
		texture.getCurrentTexture().bind();
		constructTexturedQuad(x, y, xl, yl, 0, 0, texture.getCurrentTexture().getWidth(), texture.getCurrentTexture().getHeight());
	}

	protected static void hvlDrawQuad(float x, float y, float xl, float yl, HvlAnimatedTextureUV texture){
		glColor4f(1, 1, 1, 1);
		texture.getCurrentTexture().bind();
		constructTexturedQuad(x, y, xl, yl, texture.getCurrentUVX(), texture.getCurrentUVY(), texture.getCurrentUVX() + texture.getFrameWidth(), texture.getCurrentUVY() + texture.getFrameHeight());
	}
	
	protected static void hvlDrawQuad(float x, float y, float xl, float yl, HvlAnimatedTextureUV texture, Color c){
		glColor4f(c.r, c.g, c.b, c.a);
		texture.getCurrentTexture().bind();
		constructTexturedQuad(x, y, xl, yl, texture.getCurrentUVX(), texture.getCurrentUVY(), texture.getCurrentUVX() + texture.getFrameWidth(), texture.getCurrentUVY() + texture.getFrameHeight());
	}
	
	protected static void hvlDrawQuadc(float x, float y, float xl, float yl, Texture t){
		glColor4f(1, 1, 1, 1);
		t.bind();
		constructTexturedQuad(x - (xl/2), y - (yl/2), xl, yl, 0, 0, t.getWidth(), t.getHeight());
	}

	protected static void hvlDrawQuadc(float x, float y, float xl, float yl, Texture t, Color c){
		glColor4f(c.r, c.g, c.b, c.a);
		t.bind();
		constructTexturedQuad(x - (xl/2), y - (yl/2), xl, yl, 0, 0, t.getWidth(), t.getHeight());
	}

	protected static void hvlDrawQuadc(float x, float y, float xl, float yl, float uvx1, float uvy1, float uvx2, float uvy2, Texture t){
		glColor4f(1, 1, 1, 1);
		t.bind();
		constructTexturedQuad(x - (xl/2), y - (yl/2), xl, yl, uvx1, uvy1, uvx2, uvy2);
	}

	protected static void hvlDrawQuadc(float x, float y, float xl, float yl, float uvx1, float uvy1, float uvx2, float uvy2, Texture t, Color c){
		glColor4f(c.r, c.g, c.b, c.a);
		t.bind();
		constructTexturedQuad(x - (xl/2), y - (yl/2), xl, yl, uvx1, uvy1, uvx2, uvy2);
	}

	protected static void hvlDrawQuadc(float x, float y, float xl, float yl, HvlRenderFrame renderFrame){
		glColor4f(1, 1, 1, 1);
		glBindTexture(GL_TEXTURE_2D, renderFrame.getTextureID());
		constructTexturedQuad(x - (xl/2), y + yl - (yl/2), xl, -yl, 0, 0, 1, 1);
	}

	protected static void hvlDrawQuadc(float x, float y, float xl, float yl, HvlAnimatedTextureArray texture){
		glColor4f(1, 1, 1, 1);
		texture.getCurrentTexture().bind();
		constructTexturedQuad(x - (xl/2), y - (yl/2), xl, yl, 0, 0, texture.getCurrentTexture().getWidth(), texture.getCurrentTexture().getHeight());
	}
	
	protected static void hvlDrawQuadc(float x, float y, float xl, float yl, HvlAnimatedTextureArray texture, Color c){
		glColor4f(c.r, c.g, c.b, c.a);
		texture.getCurrentTexture().bind();
		constructTexturedQuad(x - (xl/2), y - (yl/2), xl, yl, 0, 0, texture.getCurrentTexture().getWidth(), texture.getCurrentTexture().getHeight());
	}

	protected static void hvlDrawQuadc(float x, float y, float xl, float yl, HvlAnimatedTextureUV texture){
		glColor4f(1, 1, 1, 1);
		texture.getCurrentTexture().bind();
		constructTexturedQuad(x - (xl/2), y - (yl/2), xl, yl, texture.getCurrentUVX(), texture.getCurrentUVY(), texture.getCurrentUVX() + texture.getFrameWidth(), texture.getCurrentUVY() + texture.getFrameHeight());
	}
	
	protected static void hvlDrawQuadc(float x, float y, float xl, float yl, HvlAnimatedTextureUV texture, Color c){
		glColor4f(c.r, c.g, c.b, c.a);
		texture.getCurrentTexture().bind();
		constructTexturedQuad(x - (xl/2), y - (yl/2), xl, yl, texture.getCurrentUVX(), texture.getCurrentUVY(), texture.getCurrentUVX() + texture.getFrameWidth(), texture.getCurrentUVY() + texture.getFrameHeight());
	}
	
	protected static void hvlDrawQuad(HvlCoord2D v1, HvlCoord2D v2, HvlCoord2D v3, HvlCoord2D v4, Texture t){
		glColor4f(1, 1, 1, 1);
		t.bind();
		constructTexturedQuad(v1, v2, v3, v4, 0, 0, t.getWidth(), t.getHeight());
	}

	protected static void hvlDrawQuad(HvlCoord2D v1, HvlCoord2D v2, HvlCoord2D v3, HvlCoord2D v4, Texture t, Color c){
		glColor4f(c.r, c.g, c.b, c.a);
		t.bind();
		constructTexturedQuad(v1, v2, v3, v4, 0, 0, t.getWidth(), t.getHeight());
	}

	protected static void hvlDrawQuad(HvlCoord2D v1, HvlCoord2D v2, HvlCoord2D v3, HvlCoord2D v4, float uvx1, float uvy1, float uvx2, float uvy2, Texture t){
		glColor4f(1, 1, 1, 1);
		t.bind();
		constructTexturedQuad(v1, v2, v3, v4, uvx1, uvy1, uvx2, uvy2);
	}

	protected static void hvlDrawQuad(HvlCoord2D v1, HvlCoord2D v2, HvlCoord2D v3, HvlCoord2D v4, float uvx1, float uvy1, float uvx2, float uvy2, Texture t, Color c){
		glColor4f(c.r, c.g, c.b, c.a);
		t.bind();
		constructTexturedQuad(v1, v2, v3, v4, uvx1, uvy1, uvx2, uvy2);
	}
	
	protected static void hvlDrawQuad(HvlCoord2D v1, HvlCoord2D v2, HvlCoord2D v3, HvlCoord2D v4, HvlCoord2D uv1, HvlCoord2D uv2, HvlCoord2D uv3, HvlCoord2D uv4, Texture t){
		glColor4f(1, 1, 1, 1);
		t.bind();
		constructTexturedQuad(v1, v2, v3, v4, uv1, uv2, uv3, uv4);
	}

	protected static void hvlDrawQuad(HvlCoord2D v1, HvlCoord2D v2, HvlCoord2D v3, HvlCoord2D v4, HvlCoord2D uv1, HvlCoord2D uv2, HvlCoord2D uv3, HvlCoord2D uv4, Texture t, Color c){
		glColor4f(c.r, c.g, c.b, c.a);
		t.bind();
		constructTexturedQuad(v1, v2, v3, v4, uv1, uv2, uv3, uv4);
	}

	//TODO
//	protected static void hvlDrawQuad(HvlCoord v1, HvlCoord v2, HvlCoord v3, HvlCoord v4, HvlRenderFrame renderFrame){
//		glColor4f(1, 1, 1, 1);
//		glBindTexture(GL_TEXTURE_2D, renderFrame.getTextureID());
//		constructTexturedQuad(x, y + yl, xl, -yl, 0, 0, 1, 1);
//	}

	protected static void hvlDrawQuad(HvlCoord2D v1, HvlCoord2D v2, HvlCoord2D v3, HvlCoord2D v4, HvlAnimatedTextureArray texture){
		glColor4f(1, 1, 1, 1);
		texture.getCurrentTexture().bind();
		constructTexturedQuad(v1, v2, v3, v4, 0, 0, texture.getCurrentTexture().getWidth(), texture.getCurrentTexture().getHeight());
	}
	
	protected static void hvlDrawQuad(HvlCoord2D v1, HvlCoord2D v2, HvlCoord2D v3, HvlCoord2D v4, HvlAnimatedTextureArray texture, Color c){
		glColor4f(c.r, c.g, c.b, c.a);
		texture.getCurrentTexture().bind();
		constructTexturedQuad(v1, v2, v3, v4, 0, 0, texture.getCurrentTexture().getWidth(), texture.getCurrentTexture().getHeight());
	}

	protected static void hvlDrawQuad(HvlCoord2D v1, HvlCoord2D v2, HvlCoord2D v3, HvlCoord2D v4, HvlAnimatedTextureUV texture){
		glColor4f(1, 1, 1, 1);
		texture.getCurrentTexture().bind();
		constructTexturedQuad(v1, v2, v3, v4, texture.getCurrentUVX(), texture.getCurrentUVY(), texture.getCurrentUVX() + texture.getFrameWidth(), texture.getCurrentUVY() + texture.getFrameHeight());
	}
	
	protected static void hvlDrawQuad(HvlCoord2D v1, HvlCoord2D v2, HvlCoord2D v3, HvlCoord2D v4, HvlAnimatedTextureUV texture, Color c){
		glColor4f(c.r, c.g, c.b, c.a);
		texture.getCurrentTexture().bind();
		constructTexturedQuad(v1, v2, v3, v4, texture.getCurrentUVX(), texture.getCurrentUVY(), texture.getCurrentUVX() + texture.getFrameWidth(), texture.getCurrentUVY() + texture.getFrameHeight());
	}
	
	public static void hvlDrawPolygon(float x, float y, HvlCoord2D[] vertices, Color c){
		hvlForceRefresh();
		glColor4f(c.r, c.g, c.b, c.a);
		glBegin(GL_TRIANGLE_FAN);
		for(int i = 0; i < vertices.length; i++) glVertex2f(vertices[i].x + x, vertices[i].y + y);
		glEnd();
	}
	
	public static void hvlDrawPolygon(float x, float y, HvlCoord2D[] vertices, HvlCoord2D[] uvs, Texture t){
		hvlForceRefresh();
		glColor4f(1, 1, 1, 1);
		t.bind();
		if(HvlPainter2D.FLAG_TEXMAGBLUR.isEnabled()){
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
		}else{
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
		}
		glBegin(GL_TRIANGLE_FAN);
		for(int i = 0; i < vertices.length; i++){
			glTexCoord2f(uvs[i].x, uvs[i].y);
			glVertex2f(vertices[i].x + x, vertices[i].y + y);
		}
		glEnd();
	}
	
	public static void hvlDrawPolygon(float x, float y, HvlCoord2D[] vertices, HvlCoord2D[] uvs, Texture t, Color c){
		hvlForceRefresh();
		glColor4f(c.r, c.g, c.b, c.a);
		t.bind();
		if(HvlPainter2D.FLAG_TEXMAGBLUR.isEnabled()){
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
		}else{
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
		}
		glBegin(GL_TRIANGLE_FAN);
		for(int i = 0; i < vertices.length; i++){
			glTexCoord2f(uvs[i].x, uvs[i].y);
			glVertex2f(vertices[i].x + x, vertices[i].y + y);
		}
		glEnd();
	}
	
	private static void constructTexturedQuad(float x, float y, float xl, float yl, float uvx1, float uvy1, float uvx2, float uvy2){
		if(HvlPainter2D.FLAG_TEXMAGBLUR.isEnabled()){
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
		}else{
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
		}
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
	
	private static void constructTexturedQuad(HvlCoord2D v1, HvlCoord2D v2, HvlCoord2D v3, HvlCoord2D v4, float uvx1, float uvy1, float uvx2, float uvy2){
		if(HvlPainter2D.FLAG_TEXMAGBLUR.isEnabled()){
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
		}else{
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
		}
		glBegin(GL_QUADS);
		glTexCoord2f(uvx1, uvy1);
		glVertex2f(v1.x, v1.y);
		glTexCoord2f(uvx2, uvy1);
		glVertex2f(v2.x, v2.y);
		glTexCoord2f(uvx2, uvy2);
		glVertex2f(v3.x, v3.y);
		glTexCoord2f(uvx1, uvy2);
		glVertex2f(v4.x, v4.y);
		glEnd();
	}
	
	private static void constructTexturedQuad(HvlCoord2D v1, HvlCoord2D v2, HvlCoord2D v3, HvlCoord2D v4, HvlCoord2D uv1, HvlCoord2D uv2, HvlCoord2D uv3, HvlCoord2D uv4){
		if(HvlPainter2D.FLAG_TEXMAGBLUR.isEnabled()){
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
		}else{
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
		}
		glBegin(GL_QUADS);
		glTexCoord2f(uv1.x, uv1.y);
		glVertex2f(v1.x, v1.y);
		glTexCoord2f(uv2.x, uv2.y);
		glVertex2f(v2.x, v2.y);
		glTexCoord2f(uv3.x, uv3.y);
		glVertex2f(v3.x, v3.y);
		glTexCoord2f(uv4.x, uv4.y);
		glVertex2f(v4.x, v4.y);
		glEnd();
	}

}
