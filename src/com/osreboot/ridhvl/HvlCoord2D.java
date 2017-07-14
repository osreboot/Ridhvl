package com.osreboot.ridhvl;

public class HvlCoord2D
{
	public float x, y;
	
	public HvlCoord2D() {
		
	}
	
	public HvlCoord2D(HvlCoord2D cArg) {
		this.x = cArg.x;
		this.y = cArg.y;
	}
	
	public HvlCoord2D(float xArg, float yArg) {
		this.x = xArg;
		this.y = yArg;
	}
	
	public float length() {
		return (float) Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
	}
	
	public float angle() {
		return (float) Math.atan2(y, x);
	}
	
	public HvlCoord2D normalize() {
		float len = length();
		x /= len;
		y /= len;
		return this;
	}
	
	public HvlCoord2D normalizeNew() {
		float len = length();
		return new HvlCoord2D(x / len, y / len);
	}
	
	public HvlCoord2D fixNaN() {
		return fixNaN(0, 0);
	}
	
	public HvlCoord2D fixNaN(float nanX, float nanY) {
		x = Float.isNaN(x) ? nanX : x;
		y = Float.isNaN(y) ? nanY : y;
		
		return this;
	}
	
	public HvlCoord2D fixNaNNew() {
		return fixNaNNew(0, 0);
	}
	
	public HvlCoord2D fixNaNNew(float nanX, float nanY) {
		return new HvlCoord2D(Float.isNaN(x) ? nanX : x, Float.isNaN(y) ? nanY : y);
	}
	
	public HvlCoord2D negate() {
		x *= -1;
		y *= -1;
		return this;
	}
	
	public HvlCoord2D negateNew() {
		return new HvlCoord2D(-x, -y);
	}
	
	public HvlCoord2D add(float x, float y) {
		this.x += x;
		this.y += y;
		return this;
	}
	
	public HvlCoord2D add(HvlCoord2D toAdd) {
		add(toAdd.x, toAdd.y);
		return this;
	}
	
	public HvlCoord2D addNew(float x, float y) {
		return new HvlCoord2D(this.x + x, this.y + y);
	}
	
	public HvlCoord2D addNew(HvlCoord2D toAdd) {
		return new HvlCoord2D(this.x + toAdd.x, this.y + toAdd.y);
	}
	
	public HvlCoord2D subtract(float x, float y) {
		this.x -= x;
		this.y -= y;
		return this;
	}
	
	public HvlCoord2D subtract(HvlCoord2D toSubtract) {
		subtract(toSubtract.x, toSubtract.y);
		return this;
	}
	
	public HvlCoord2D subtractNew(float x, float y) {
		return new HvlCoord2D(this.x - x, this.y - y);
	}
	
	public HvlCoord2D subtractNew(HvlCoord2D toSubtract) {
		return new HvlCoord2D(this.x - toSubtract.x, this.y - toSubtract.y);
	}
	
	public HvlCoord2D mult(float val) {
		x *= val;
		y *= val;
		return this;
	}
	
	public HvlCoord2D mult(float xVal, float yVal) {
		x *= xVal;
		y *= yVal;
		return this;
	}
	
	public HvlCoord2D mult(HvlCoord2D val) {
		mult(val.x, val.y);
		return this;
	}
	
	public HvlCoord2D multNew(float val) {
		return new HvlCoord2D(this.x * val, this.y * val);
	}
	
	public HvlCoord2D multNew(float xVal, float yVal) {
		return new HvlCoord2D(this.x * xVal, this.y * yVal);
	}
	
	public HvlCoord2D multNew(HvlCoord2D val) {
		return new HvlCoord2D(this.x * val.x, this.y * val.y);
	}
	
	public HvlCoord2D div(float val) {
		x /= val;
		y /= val;
		return this;
	}
	
	public HvlCoord2D div(float xVal, float yVal) {
		x /= xVal;
		y /= yVal;
		return this;
	}
	
	public HvlCoord2D div(HvlCoord2D val) {
		div(val.x, val.y);
		return this;
	}
	
	public HvlCoord2D divNew(float val) {
		return new HvlCoord2D(this.x / val, this.y / val);
	}
	
	public HvlCoord2D divNew(float xVal, float yVal) {
		return new HvlCoord2D(this.x / xVal, this.y / yVal);
	}
	
	public HvlCoord2D divNew(HvlCoord2D val) {
		return new HvlCoord2D(this.x / val.x, this.y / val.y);
	}

	public float dot(HvlCoord2D dotter) {
		return (x * dotter.x) + (y * dotter.y);
	}
	
	public HvlCoord2D clone() {
		return new HvlCoord2D(x, y);
	}
	
	@Override
	public boolean equals(Object obj){
		if(obj != null && obj instanceof HvlCoord2D){
			return ((HvlCoord2D)obj).x == x && ((HvlCoord2D)obj).y == y;
		}else return false;
	}
}