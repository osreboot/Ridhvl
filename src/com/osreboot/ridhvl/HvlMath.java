package com.osreboot.ridhvl;

import java.util.Random;

import com.osreboot.ridhvl.action.HvlAction1;
import com.osreboot.ridhvl.template.HvlChronology;


public class HvlMath {
	
	private static Random rand;
	
	static
	{
		reseedRandom();
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
	
	public static HvlCoord raytrace(HvlCoord start, HvlCoord end, HvlCoord segStart, HvlCoord segEnd) {
		HvlCoord tr = new HvlCoord(0, 0);

		HvlCoord b = end.subtractNew(start);
		HvlCoord d = segEnd.subtractNew(segStart);
		float bDotDPerp = b.x * d.y - b.y * d.x;

		if (bDotDPerp == 0)
			return null;

		HvlCoord c = segStart.subtractNew(start);
		float t = (c.x * d.y - c.y * d.x) / bDotDPerp;
		if (t < 0 || t > 1)
			return null;

		float u = (c.x * b.y - c.y * b.x) / bDotDPerp;
		if (u < 0 || u > 1)
			return null;

		tr = start.addNew(b.multNew(t));

		if (HvlMath.distance(tr.x, tr.y, start.x, start.y) > HvlMath.distance(start.x, start.y, end.x, end.y))
			return null;

		return tr;
	}
	
	public static int randomInt(int max) {
		return rand.nextInt(max);
	}
	
	public static int randomIntBetween(int min, int max)	{
		if (max > min)
			return min + rand.nextInt(max - min);
		if (max < min)
			return max + rand.nextInt(min - max);
		
		return min;
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
	
	public static boolean chanceIn(int chance) {
		return randomInt(chance) == 0;
	}
	
	public static Random getRand() {
		return rand;
	}

	public static void reseedRandom() {
		rand = new Random();
	}
	
	public static class Stepper{
		
		{
			new HvlChronology.Update(new HvlAction1<Float>(){
				@Override
				public void run(Float a){
					value = stepTowards(value, rate, goal);
				}
			});
		}
		
		private float value, rate, goal;
		
		public Stepper(float valueArg, float rateArg, float goalArg){
			value = valueArg;
			rate = rateArg;
			goal = goalArg;
		}

		public float getValue(){
			return value;
		}

		public void setValue(float value){
			this.value = value;
		}

		public float getRate(){
			return rate;
		}

		public void setRate(float rate){
			this.rate = rate;
		}

		public float getGoal(){
			return goal;
		}

		public void setGoal(float goal){
			this.goal = goal;
		}
		
	}
	
}
