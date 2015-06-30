package com.osreboot.ridhvl;

import java.util.Random;


public class HvlMath {
	@Deprecated
	public static class HvlCoord
	{
		public float x, y;
		
		public HvlCoord() {
			
		}
		
		public HvlCoord(float xArg, float yArg) {
			this.x = xArg;
			this.y = yArg;
		}
	}
	
	public static float lerp(float arg1, float arg2, float alpha){
		return arg1 + alpha * (arg2 - arg1);
	}
	
	/**
	 * Returns arg1 progressed towards goalArg by modifierArg amount (ignores negative 
	 * modifierArg). Returns goalArg if arg1 plus or minus a fraction of modifierArg equals 
	 * goalArg.
	 * <p>
	 * E.g.<br>
	 * stepTowards(0, 5, 10) returns 5 because 0 + 5 = 5<br>
	 * stepTowards(0, 5, -10) returns -5 because 0 - 5 = -5<br>
	 * stepTowards(0, 20, 10) returns 10 because 0 + 20 > 10<br>
	 * stepTowards(0, 20, -10) returns -10 because 0 - 20 < -10<br>
	 * <p>
	 * This method can be used, for example, to move an object towards a wall at modifierArg
	 * speed where upon arrival the object will stop.
	 * 
	 * @param arg1 			original value to be modified
	 * @param modifierArg 	value by which arg1 will be modified (ignores negatives)
	 * @param goalArg 		maximum or minimum value to be returned
	 * @return 				final value
	 */
	public static float stepTowards(float arg1, float modifierArg, float goalArg){
		if(goalArg > arg1){
			if(arg1 + modifierArg < goalArg) return arg1 + modifierArg; else return goalArg;
		}else{
			if(arg1 - modifierArg > goalArg) return arg1 - modifierArg; else return goalArg;
		}
	}
	
	/**
	 * 
	 * Returns the distance between point one (xArg1, yArg1) and point two (xArg2, yArg2)
	 * 
	 * @param xArg1			x value for first point
	 * @param yArg1			y value for first point
	 * @param xArg2			x value for second point
	 * @param yArg2			y value for second point
	 * @return				distance between (xArg1, yArg1) and (xArg2, yArg2)
	 */
	public static float distance(float xArg1, float yArg1, float xArg2, float yArg2){
		return (float)Math.sqrt(Math.pow(xArg1 - xArg2, 2) + Math.pow(yArg1 - yArg2, 2));
	}
	
	public static int randomIntBetween(int min, int max)	{
		Random rand = new Random();
		return min + rand.nextInt(max - min);
	}
	
	public static float randomFloatBetween(float min, float max)	{
		return min + ((float) Math.random() * (max - min));
	}

	public static HvlCoord randomPointInCircle(float radius) {
		float theta = randomFloatBetween(0, 360);
		float d = (float) Math.random();
		return new HvlCoord((float) Math.cos(theta) * radius * d,
				(float) Math.sin(theta) * radius * d);
	}
}
