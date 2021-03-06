package com.osreboot.ridhvl;

public class HvlMatrix2D {

	public static HvlMatrix2D createTranslationMatrix(HvlCoord2D c){
		return new HvlMatrix2D(new HvlCoord2D(0, 0), new HvlCoord2D(1, 0), new HvlCoord2D(1, 1), new HvlCoord2D(0, 1),
				new HvlCoord2D(c.x, c.y), new HvlCoord2D(c.x + 1, c.y), new HvlCoord2D(c.x + 1, c.y + 1), new HvlCoord2D(c.x, c.y + 1));
	}
	
	public static HvlMatrix2D createRotationMatrix(float rotation){
		return new HvlMatrix2D(new HvlCoord2D(-0.5f, -0.5f), new HvlCoord2D(0.5f, -0.5f), new HvlCoord2D(0.5f, 0.5f), new HvlCoord2D(-0.5f, 0.5f),
				new HvlCoord2D((-0.5f*(float)Math.cos(rotation))-(-0.5f*(float)Math.sin(rotation)),
						(-0.5f*(float)Math.sin(rotation))+(-0.5f*(float)Math.cos(rotation))), 
				new HvlCoord2D((0.5f*(float)Math.cos(rotation))-(-0.5f*(float)Math.sin(rotation)),
						(0.5f*(float)Math.sin(rotation))+(-0.5f*(float)Math.cos(rotation))), 
				new HvlCoord2D((0.5f*(float)Math.cos(rotation))-(0.5f*(float)Math.sin(rotation)),
						(0.5f*(float)Math.sin(rotation))+(0.5f*(float)Math.cos(rotation))), 
				new HvlCoord2D((-0.5f*(float)Math.cos(rotation))-(0.5f*(float)Math.sin(rotation)),
						(-0.5f*(float)Math.sin(rotation))+(0.5f*(float)Math.cos(rotation))));
	}
	
	//TODO rotation matrix
	
	private HvlCoord2D fv1, fv2, fv3, fv4, tv1, tv2, tv3, tv4;
	private float verticalError;

	public HvlMatrix2D(HvlCoord2D fv1Arg, HvlCoord2D fv2Arg,HvlCoord2D fv3Arg, HvlCoord2D fv4Arg, 
			HvlCoord2D tv1Arg, HvlCoord2D tv2Arg,HvlCoord2D tv3Arg, HvlCoord2D tv4Arg, float verticalErrorArg){
		fv1 = fv1Arg;
		fv2 = fv2Arg;
		fv3 = fv3Arg;
		fv4 = fv4Arg;
		tv1 = tv1Arg;
		tv2 = tv2Arg;
		tv3 = tv3Arg;
		tv4 = tv4Arg;
		verticalError = verticalErrorArg;
	}
	
	public HvlMatrix2D(HvlCoord2D fv1Arg, HvlCoord2D fv2Arg,HvlCoord2D fv3Arg, HvlCoord2D fv4Arg, 
			HvlCoord2D tv1Arg, HvlCoord2D tv2Arg,HvlCoord2D tv3Arg, HvlCoord2D tv4Arg){
		fv1 = fv1Arg;
		fv2 = fv2Arg;
		fv3 = fv3Arg;
		fv4 = fv4Arg;
		tv1 = tv1Arg;
		tv2 = tv2Arg;
		tv3 = tv3Arg;
		tv4 = tv4Arg;
		verticalError = 0.01f;
	}

	public float getVerticalError(){
		return verticalError;
	}

	public void setVerticalError(float verticalErrorArg){
		verticalError = verticalErrorArg;
	}

	public HvlCoord2D map(HvlCoord2D arg){
		float fdeg12 = HvlMath.fullRadians(arg, fv1) - HvlMath.fullRadians(fv2, fv1);
		if(Float.isNaN(fdeg12)) fdeg12 = 0;
		float fdis12 = (float)Math.cos(fdeg12) * HvlMath.distance(fv1, arg);
		HvlCoord2D tint12 = HvlMath.lerp(tv1, tv2, fdis12/HvlMath.distance(fv1, fv2));

		float fdeg43 = HvlMath.fullRadians(arg, fv4) - HvlMath.fullRadians(fv3, fv4);
		if(Float.isNaN(fdeg43)) fdeg43 = 0;
		float fdis43 = (float)Math.cos(fdeg43) * HvlMath.distance(fv4, arg);
		HvlCoord2D tint43 = HvlMath.lerp(tv4, tv3, fdis43/HvlMath.distance(fv4, fv3));

		float fdeg14 = HvlMath.fullRadians(arg, fv1) - HvlMath.fullRadians(fv4, fv1);
		if(Float.isNaN(fdeg14)) fdeg14 = 0;
		float fdis14 = (float)Math.cos(fdeg14) * HvlMath.distance(fv1, arg);
		HvlCoord2D tint14 = HvlMath.lerp(tv1, tv4, fdis14/HvlMath.distance(fv1, fv4));

		float fdeg23 = HvlMath.fullRadians(arg, fv2) - HvlMath.fullRadians(fv3, fv2);
		if(Float.isNaN(fdeg23)) fdeg23 = 0;
		float fdis23 = (float)Math.cos(fdeg23) * HvlMath.distance(fv2, arg);
		HvlCoord2D tint23 = HvlMath.lerp(tv2, tv3, fdis23/HvlMath.distance(fv2, fv3));

		//hvlDrawQuadc(tint12.x, tint12.y, 10, 10, Color.blue);
		//hvlDrawQuadc(tint43.x, tint43.y, 10, 10, Color.red);
		//hvlDrawQuadc(tint14.x, tint14.y, 10, 10, Color.blue);
		//hvlDrawQuadc(tint23.x, tint23.y, 10, 10, Color.red);
		HvlCoord2D t = intersection(tint12, tint43, tint14, tint23);

		return new HvlCoord2D(t);
	}

	private HvlCoord2D intersection(HvlCoord2D s1, HvlCoord2D e1, HvlCoord2D s2, HvlCoord2D e2){
		float m1 = (s1.y - e1.y) / (s1.x - e1.x);//line 1 slope
		float b1 = -(m1 * s1.x) + s1.y;//line 1 y-intercept
		boolean m1vert = Math.abs(s1.x - e1.x) < verticalError;

		float m2 = (s2.y - e2.y) / (s2.x - e2.x);//line 2 slope
		float b2 = -(m2 * s2.x) + s2.y;//line 2 y-intercept
		boolean m2vert = Math.abs(s2.x - e2.x) < verticalError;

		if(m1 == m2) return null; else{
			
			float tx = (b2 - b1) / (m1 - m2);
			float ty = (m1 * tx) + b1;
			if(m1vert){
				tx = s1.x;
				ty = (m2 * s1.x) + b2;
			}
			if(m2vert){
				tx = s2.x;
				ty = (m1 * s2.x) + b1;
			}
			
			return new HvlCoord2D(tx, ty);
		}
	}

}
