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
	
	public HvlCoord normalize() {
		float len = length();
		x /= len;
		y /= len;
		return this;
	}
	
	public HvlCoord normalizeNew() {
		float len = length();
		return new HvlCoord(x / len, y / len);
	}
	
	public HvlCoord negate() {
		x *= -1;
		y *= -1;
		return this;
	}
	
	public HvlCoord negateNew() {
		return new HvlCoord(-x, -y);
	}
	
	public HvlCoord add(float x, float y) {
		this.x += x;
		this.y += y;
		return this;
	}
	
	public HvlCoord add(HvlCoord toAdd) {
		add(toAdd.x, toAdd.y);
		return this;
	}
	
	public HvlCoord addNew(float x, float y) {
		return new HvlCoord(this.x + x, this.y + y);
	}
	
	public HvlCoord addNew(HvlCoord toAdd) {
		return new HvlCoord(this.x + toAdd.x, this.y + toAdd.y);
	}
	
	public HvlCoord subtract(float x, float y) {
		this.x -= x;
		this.y -= y;
		return this;
	}
	
	public HvlCoord subtract(HvlCoord toSubtract) {
		subtract(toSubtract.x, toSubtract.y);
		return this;
	}
	
	public HvlCoord subtractNew(float x, float y) {
		return new HvlCoord(this.x - x, this.y - y);
	}
	
	public HvlCoord subtractNew(HvlCoord toSubtract) {
		return new HvlCoord(this.x - toSubtract.x, this.y - toSubtract.y);
	}
	
	public HvlCoord mult(float val) {
		x *= val;
		y *= val;
		return this;
	}
	
	public HvlCoord mult(float xVal, float yVal) {
		x *= xVal;
		y *= yVal;
		return this;
	}
	
	public HvlCoord mult(HvlCoord val) {
		mult(val.x, val.y);
		return this;
	}
	
	public HvlCoord multNew(float val) {
		return new HvlCoord(this.x * val, this.y * val);
	}
	
	public HvlCoord multNew(float xVal, float yVal) {
		return new HvlCoord(this.x * xVal, this.y * yVal);
	}
	
	public HvlCoord multNew(HvlCoord val) {
		return new HvlCoord(this.x * val.x, this.y * val.y);
	}
	
	public HvlCoord div(float val) {
		x /= val;
		y /= val;
		return this;
	}
	
	public HvlCoord div(float xVal, float yVal) {
		x /= xVal;
		y /= yVal;
		return this;
	}
	
	public HvlCoord div(HvlCoord val) {
		div(val.x, val.y);
		return this;
	}
	
	public HvlCoord divNew(float val) {
		return new HvlCoord(this.x / val, this.y / val);
	}
	
	public HvlCoord divNew(float xVal, float yVal) {
		return new HvlCoord(this.x / xVal, this.y / yVal);
	}
	
	public HvlCoord divNew(HvlCoord val) {
		return new HvlCoord(this.x / val.x, this.y / val.y);
	}

	public float dot(HvlCoord dotter) {
		return (x * dotter.x) + (y * dotter.y);
	}
}