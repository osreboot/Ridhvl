package com.osreboot.ridhvl.painter.painter3d;

import org.lwjgl.opengl.GL11;

public class HvlPainter3D {

	public static enum HvlPainter3DTemplate{
		DEFAULT
	}

	public static void hvlGL11Init(HvlPainter3DTemplate type){
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

	public static void hvlGL11Ortho(int displayWidth, int displayHeight, int farPlane, int nearPlane){
		GL11.glMatrixMode(GL11.GL_MATRIX_MODE);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, displayWidth, displayHeight, 0, nearPlane, farPlane);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
	}
	
}
