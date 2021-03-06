package com.osreboot.ridhvl.painter;

import static com.osreboot.ridhvl.painter.painter2d.HvlPainter2D.hvlResetRotation;
import static com.osreboot.ridhvl.painter.painter2d.HvlPainter2D.hvlRotate;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

import com.osreboot.ridhvl.HvlCoord2D;
import com.osreboot.ridhvl.action.HvlAction0;

public class HvlCamera2D {
	
	public static final HvlCoord2D ALIGNMENT_TOPLEFT = new HvlCoord2D(0, 0), 
			ALIGNMENT_CENTER = new HvlCoord2D(-Display.getWidth()/2, -Display.getHeight()/2);
	
	private float x, y, xOffset, yOffset, rotation, zoom = 1f;
	
	public HvlCamera2D(float xArg, float yArg, float rotationArg, float zoomArg, HvlCoord2D alignmentArg){
		x = xArg;
		y = yArg;
		rotation = rotationArg;
		zoom = zoomArg;
		setAlignment(alignmentArg);
	}
	
	public void doTransform(HvlAction0 actionArg){
		doTransform();
		actionArg.run();
		undoTransform();
	}

	private void doTransform(){
		GL11.glPushMatrix();
		GL11.glTranslatef(-x - xOffset, -y - yOffset, 0);
		GL11.glScalef(zoom, zoom, 0);
		hvlRotate(x, y, rotation);
	}
	
	private void undoTransform(){
		hvlResetRotation();
		GL11.glPopMatrix();
	}
	
	public void setAlignment(HvlCoord2D alignmentArg){
		xOffset = alignmentArg.x;
		yOffset = alignmentArg.y;
	}
	
	public HvlCoord2D getAlignment(){
		return new HvlCoord2D(xOffset, yOffset);
	}
	
	public void setPosition(float xArg, float yArg){
		x = xArg;
		y = yArg;
	}
	
	public float getX(){
		return x;
	}

	public void setX(float xArg){
		x = xArg;
	}

	public float getY(){
		return y;
	}

	public void setY(float yArg){
		y = yArg;
	}

	public float getRotation(){
		return rotation;
	}

	public void setRotation(float rotationArg){
		rotation = rotationArg;
	}

	public float getZoom(){
		return zoom;
	}

	public void setZoom(float zoomArg){
		zoom = zoomArg;
	}
	
	public float getRawX(){
		return x + xOffset;
	}
	
	public float getRawY(){
		return y + yOffset;
	}
	
}
