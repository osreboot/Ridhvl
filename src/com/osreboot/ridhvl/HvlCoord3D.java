package com.osreboot.ridhvl;

import java.io.Serializable;

public class HvlCoord3D implements Serializable{
	private static final long serialVersionUID = 6215292722178154882L;
	
	public float x, y, z;

	public HvlCoord3D() {

	}

	public HvlCoord3D(HvlCoord3D cArg) {
		this.x = cArg.x;
		this.y = cArg.y;
		this.z = cArg.z;
	}

	public HvlCoord3D(float xArg, float yArg, float zArg) {
		this.x = xArg;
		this.y = yArg;
		this.z = zArg;
	}

	public HvlCoord3D clone() {
		return new HvlCoord3D(x, y, z);
	}

	@Override
	public boolean equals(Object obj){
		if(obj != null && obj instanceof HvlCoord3D){
			return ((HvlCoord3D)obj).x == x && ((HvlCoord3D)obj).y == y && ((HvlCoord3D)obj).z == z;
		}else return false;
	}
	
	@Override
	public String toString(){
		return "[" + x + "," + y + "," + z + "]";
	}

	@Override
	public int hashCode(){
		return toString().hashCode();
	}
	
}
