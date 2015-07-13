package com.osreboot.ridhvl;

public class HvlCoord
{
	public float x, y;
	
	public HvlCoord() {
		
	}
	
	public HvlCoord(float xArg, float yArg) {
		this.x = xArg;
		this.y = yArg;
	}
	
	public float length() {
		return (float) Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
	}
	
	public float angle() {
		return (float) Math.atan2(y, x);
	}
	
	public void normalize() {
		float len = length();
		x /= len;
		y /= len;
	}
	
	public void negate() {
		x *= -1;
		y *= -1;
	}
	
	public void add(float x, float y) {
		this.x += x;
		this.y += y;
	}
	
	public void add(HvlCoord toAdd) {
		add(toAdd.x, toAdd.y);
	}
	
	public void subtract(float x, float y) {
		this.x -= x;
		this.y -= y;
	}
	
	public void subtract(HvlCoord toSubtract) {
		subtract(toSubtract.x, toSubtract.y);
	}
	
	public void mult(float val) {
		x *= val;
		y *= val;
	}
	
	public void mult(float xVal, float yVal) {
		x *= xVal;
		y *= yVal;
	}
	
	public void mult(HvlCoord val) {
		mult(val.x, val.y);
	}
	
	public void div(float val) {
		x /= val;
		y /= val;
	}
	
	public void div(float xVal, float yVal) {
		x /= xVal;
		y /= yVal;
	}
	
	public void div(HvlCoord val) {
		div(val.x, val.y);
	}

	public float dot(HvlCoord dotter) {
		return (x * dotter.x) + (y * dotter.y);
	}
}