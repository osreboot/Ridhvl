package com.osreboot.ridhvl.painter.painter2d;

import org.lwjgl.opengl.GL11;

class HvlSwivel2D {

	protected static void hvlRotate(float x, float y, float degrees){
		GL11.glPushMatrix();
		GL11.glTranslatef(x, y, 0);
		GL11.glRotatef(degrees, 0, 0, 1);
		GL11.glTranslatef(-x, -y, 0);
		//draw
	}
	
	protected static void hvlResetRotation(){
		GL11.glPopMatrix();
	}
	
}
