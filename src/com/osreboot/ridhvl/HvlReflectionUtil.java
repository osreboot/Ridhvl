package com.osreboot.ridhvl;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class HvlReflectionUtil {
	public static Object genericParse(Class<?> c, String in) {
		if (c.equals(byte.class) || c.equals(Byte.class))
			return Byte.parseByte(in);
		if (c.equals(short.class) || c.equals(Short.class))
			return Short.parseShort(in);
		if (c.equals(int.class) || c.equals(Integer.class))
			return Integer.parseInt(in);
		if (c.equals(long.class) || c.equals(Long.class))
			return Long.parseLong(in);
		if (c.equals(float.class) || c.equals(Float.class))
			return Float.parseFloat(in);
		if (c.equals(double.class) || c.equals(Double.class))
			return Double.parseDouble(in);
		if (c.equals(boolean.class) || c.equals(Boolean.class))
			return Boolean.parseBoolean(in);
		if (c.equals(char.class) || c.equals(Character.class))
			return in.charAt(0); // if this is a character it should only be a 1 character string anyways

		return in;
	}
	
	public static Class<?> convertAwayFromPrimitive(Class<?> c) {
		if (c.equals(byte.class)) return Byte.class;
		if (c.equals(short.class)) return Short.class;
		if (c.equals(int.class)) return Integer.class;
		if (c.equals(long.class)) return Long.class;
		if (c.equals(float.class)) return Float.class;
		if (c.equals(double.class)) return Double.class;
		if (c.equals(boolean.class)) return Boolean.class;
		if (c.equals(char.class)) return Character.class;
		
		return c;
	}

	public static boolean isPrimitive(Class<?> type) {
		return type.equals(byte.class) || type.equals(Byte.class) || type.equals(short.class) || type.equals(Short.class) || type.equals(int.class) || type.equals(Integer.class) || type.equals(long.class) || type.equals(Long.class) || type.equals(float.class) || type.equals(Float.class) || type.equals(double.class) || type.equals(Double.class) || type.equals(boolean.class) || type.equals(Boolean.class) || type.equals(char.class) || type.equals(Character.class);
	}
	
	public static boolean isSimple(Class<?> type) {
		return isPrimitive(type) || type.equals(String.class); // Strings are "simple" but not technically "primitive"
	}
	
	public static Object getDefault(Class<?> type) {
		if (type.equals(byte.class) || type.equals(Byte.class)) return 0;
		if (type.equals(short.class) || type.equals(Short.class)) return 0;
		if (type.equals(int.class) || type.equals(Integer.class)) return 0;
		if (type.equals(long.class) || type.equals(Long.class)) return 0l;
		if (type.equals(float.class) || type.equals(Float.class)) return 0f;
		if (type.equals(double.class) || type.equals(Double.class)) return 0d;
		if (type.equals(boolean.class) || type.equals(Boolean.class)) return false;
		if (type.equals(char.class) || type.equals(Character.class)) return '\u0000';
		if (type.equals(String.class)) return "";
		
		return null;
	}
	
	public static Class<?>[] getGenericTypes(Field f) {
		Type type = f.getGenericType();
		
		if (type instanceof ParameterizedType) {
			ParameterizedType pType = (ParameterizedType) type;
			Class<?>[] tr = new Class<?>[pType.getActualTypeArguments().length];
			for (int i = 0; i < pType.getActualTypeArguments().length; i++) {
				tr[i] = (Class<?>) pType.getActualTypeArguments()[i];
			}
			return tr;
		} else {
			return new Class<?>[] {};
		}
	}
}
