package com.osreboot.ridhvl.painter;

import static com.osreboot.ridhvl.painter.painter2d.HvlPainter2D.hvlResetRotation;
import static com.osreboot.ridhvl.painter.painter2d.HvlPainter2D.hvlRotate;

import org.lwjgl.opengl.GL11;

import com.osreboot.ridhvl.HvlCoord3D;
import com.osreboot.ridhvl.action.HvlAction0;

public class HvlCamera3D {
	
	private float x, y, z, xOffset, yOffset, zOffset, rotation, zoom = 1f;
	
	public HvlCamera3D(float xArg, float yArg, float rotationArg, float zoomArg, HvlCoord3D alignmentArg){
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
		GL11.glTranslatef(-x - xOffset, -y - yOffset, -z - zOffset);
		GL11.glScalef(zoom, zoom, 0);
		hvlRotate(x, y, rotation);//TODO 3d rotation
	}
	
	private void undoTransform(){
		hvlResetRotation();
		GL11.glPopMatrix();
	}
	
	public void setAlignment(HvlCoord3D alignmentArg){
		xOffset = alignmentArg.x;
		yOffset = alignmentArg.y;
		zOffset = alignmentArg.z;
	}
	
	public HvlCoord3D getAlignment(){
		return new HvlCoord3D(xOffset, yOffset, zOffset);
	}
	
	public void setPosition(float xArg, float yArg, float zArg){
		x = xArg;
		y = yArg;
		z = zArg;
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
	
	public float getZ(){
		return z;
	}

	public void setZ(float zArg){
		z = zArg;
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
	
	public float getRawZ(){
		return z + zOffset;
	}
	
}
