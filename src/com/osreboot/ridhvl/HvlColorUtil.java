package com.osreboot.ridhvl;

import org.newdawn.slick.Color;

public class HvlColorUtil {
	public static Color lerpColor(Color min, Color max, float lerp)
	{
		return new Color(min.r + ((max.r - min.r) * lerp),
				min.g + ((max.g - min.g) * lerp),
				min.b + ((max.b - min.b) * lerp),
				min.a + ((max.a - min.a) * lerp));
	}
	
	public static Color lerpColor(Color min, Color max, float lerpR, float lerpG, float lerpB, float lerpA)
	{
		return new Color(min.r + ((max.r - min.r) * lerpR),
				min.g + ((max.g - min.g) * lerpG),
				min.b + ((max.b - min.b) * lerpB),
				min.a + ((max.a - min.a) * lerpA));
	}
	
	public static Color hsvToColor(float h, float s, float v) {
		float c = v * s;
		
		float hmod = h / 60f;
		
		float x = c * (1f - Math.abs((hmod % 2) - 1));
		
		float r = 0f, g = 0f, b = 0f;
		
		if (0 <= hmod && hmod < 1) {
			r = c;
			g = x;
		}
		if (1 <= hmod && hmod < 2) {
			r = x;
			g = c;
		}
		if (2 <= hmod && hmod < 3) {
			g = c;
			b = x;
		}
		if (3 <= hmod && hmod < 4) {
			g = x;
			b = c;
		}
		if (4 <= hmod && hmod < 5) {
			r = x;
			b = c;
		}
		if (5 <= hmod && hmod < 6) {
			r = c;
			b = x;
		}
		
		float m = v - c;
		
		return new Color(r + m, g + m, b + m);
	}
}
