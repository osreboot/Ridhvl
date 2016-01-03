package com.osreboot.ridhvl;

public class HvlReflectionUtil {
	public static Object genericParse(Class<?> c, String in) {
		if (c.equals(int.class) || c.equals(Integer.class))
			return Integer.parseInt(in);
		if (c.equals(float.class) || c.equals(Float.class))
			return Float.parseFloat(in);
		if (c.equals(double.class) || c.equals(Double.class))
			return Double.parseDouble(in);
		if (c.equals(boolean.class) || c.equals(Boolean.class))
			return Boolean.parseBoolean(in);

		return in;
	}

	public static boolean isSimple(Class<?> c) {
		return c.equals(int.class) || c.equals(Integer.class) || c.equals(float.class) || c.equals(Float.class) || c.equals(double.class) || c.equals(Double.class) || c.equals(boolean.class) || c.equals(Boolean.class) || c.equals(String.class);
	}
}
