package com.osreboot.ridhvl.painter.painter2d;

import org.lwjgl.opengl.GL11;

public class hvlPainter2D {

	public static enum TemplateType{
		DEFAULT
	}

	public static void GL11_Init(TemplateType type){
		switch(type){
		default:
			GL11.glEnable(GL11.GL_TEXTURE_2D);
			GL11.glEnable(GL11.GL_COLOR_MATERIAL);
			GL11.glClearColor(0, 0, 0, 0);
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			GL11.glMatrixMode(GL11.GL_MODELVIEW);
			break;
		}
	}

	public static void GL11_Ortho(int displayWidth, int displayHeight){
		GL11.glMatrixMode(GL11.GL_MATRIX_MODE);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, displayWidth, displayHeight, 0, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
	}

}
