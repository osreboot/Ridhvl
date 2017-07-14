package com.osreboot.ridhvl;

public class HvlCoord3D {

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

}
