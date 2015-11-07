package com.osreboot.ridhvl.config;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.osreboot.ridhvl.HvlReflectionUtil;
import com.osreboot.ridhvl.config.HvlConfigIgnore.IgnoreType;

/**
 * This is used to load properties into any class using reflection.
 */
public class HvlConfigUtil {

	@HvlConfigIgnore
	/**
	 * The delimiter between property name and value in a saved file.
	 */
	public static final String delimiter = ":";
	
	@HvlConfigIgnore
	/**
	 * The regex-quoted version of <code>delimiter</code> (Pattern.quote(delimiter))
	 * @see HvlConfigUtil.delimiter
	 */
	public static final String regexDelim = Pattern.quote(delimiter);

	/**
	 * Loads a config file from the given path into a newly created object.
	 * @param type The type to load the data into.
	 * @param path The path to the config file.
	 * @return The loaded config class with the variables set from the config file.
	 */
	public static <TConfigType> TConfigType loadConfig(
			Class<? extends TConfigType> type, String path) {
		Scanner scan = null;

		Field[] fields = type.getFields();

		try {
			TConfigType toReturn = type.newInstance();

			scan = new Scanner(new FileInputStream(path));

			while (scan.hasNextLine()) {
				String ln = scan.nextLine();

				String[] split = ln.split(regexDelim);
				String propName = split[0];

				for (int i = 0; i < fields.length; i++) {
					if (fields[i].getName().equals(propName)) {
						boolean shouldBeIgnored = false;
						for (Annotation a : fields[i].getDeclaredAnnotations()) {
							if (a.annotationType()
									.equals(HvlConfigIgnore.class)) {
								HvlConfigIgnore ign = (HvlConfigIgnore) a;
								if (ign.value() == IgnoreType.BOTH
										|| ign.value() == IgnoreType.LOAD)
									shouldBeIgnored = true;
								break;
							}
						}
						if (shouldBeIgnored)
							continue;

						if (fields[i].getType().isArray()) {
							Pattern pattern = Pattern.compile("'(.*?)'");
							Matcher match = pattern.matcher(split[1]);
							Class<?> arrayType = getArrayType(fields[i]
									.getType());
							List<String> matches = new LinkedList<>();
							while (match.find())
							{
								matches.add(match.group(1));
							}
							
							Object arr = Array.newInstance(arrayType, matches.size());
							for (int j = 0; j < matches.size(); j++)
							{
								Array.set(arr, j, HvlReflectionUtil.genericParse(arrayType, matches.get(j)));
							}
							
							fields[i].set(toReturn, arr);
						} else {
							try {
								toReturn.getClass()
										.getField(propName)
										.set(toReturn,
												HvlReflectionUtil.genericParse(toReturn.getClass()
														.getField(propName)
														.getType(), split[1]));
							} catch (IllegalArgumentException
									| NoSuchFieldException | SecurityException e1) {
								e1.printStackTrace();
							}
						}
					}
				}
			}
			return toReturn;
		} catch (FileNotFoundException | InstantiationException
				| IllegalAccessException e) {
			return null;
		} finally {
			if (scan != null)
				scan.close();
		}
	}

	/**
	 * Loads a config file from the given path into an existing object.
	 * @param obj The object to load the data into.
	 * @param path The path to the config file.
	 * @return The object sent in from obj, but with the properties set from the config file.
	 */
	public static <TConfigType> TConfigType loadConfig(TConfigType obj,
			String path) {
		Scanner scan = null;

		Field[] fields = obj.getClass().getFields();

		TConfigType toReturn = obj;

		try {
			scan = new Scanner(new FileInputStream(path));

			while (scan.hasNextLine()) {
				String ln = scan.nextLine();

				String[] split = ln.split(regexDelim);
				String propName = split[0];

				for (int i = 0; i < fields.length; i++) {
					if (fields[i].getName().equals(propName)) {
						boolean shouldBeIgnored = false;
						for (Annotation a : fields[i].getDeclaredAnnotations()) {
							if (a.annotationType()
									.equals(HvlConfigIgnore.class)) {
								HvlConfigIgnore ign = (HvlConfigIgnore) a;
								if (ign.value() == IgnoreType.BOTH
										|| ign.value() == IgnoreType.LOAD)
									shouldBeIgnored = true;
								break;
							}
						}
						if (shouldBeIgnored)
							continue;

						if (fields[i].getType().isArray()) {
							Pattern pattern = Pattern.compile("'(.*?)'");
							Matcher match = pattern.matcher(split[1]);
							Class<?> arrayType = getArrayType(fields[i]
									.getType());
							List<String> matches = new LinkedList<>();
							while (match.find())
							{
								matches.add(match.group(1));
							}
							
							Object arr = Array.newInstance(arrayType, matches.size());
							for (int j = 0; j < matches.size(); j++)
							{
								Array.set(arr, j, HvlReflectionUtil.genericParse(arrayType, matches.get(j)));
							}
							
							fields[i].set(toReturn, arr);
						} else {
							try {
								toReturn.getClass()
										.getField(propName)
										.set(toReturn,
												HvlReflectionUtil.genericParse(toReturn.getClass()
														.getField(propName)
														.getType(), split[1]));
							} catch (IllegalArgumentException
									| NoSuchFieldException | SecurityException e1) {
								e1.printStackTrace();
							}
						}
						
//						if (fields[i].getType().equals(int.class)
//								|| fields[i].getType().equals(Integer.class)) {
//							try {
//								toReturn.getClass()
//										.getField(propName)
//										.setInt(toReturn,
//												Integer.parseInt(split[1]));
//							} catch (IllegalArgumentException
//									| NoSuchFieldException | SecurityException e) {
//							}
//						}
//
//						if (fields[i].getType().equals(float.class)
//								|| fields[i].getType().equals(Float.class)) {
//							try {
//								toReturn.getClass()
//										.getField(propName)
//										.setFloat(toReturn,
//												Float.parseFloat(split[1]));
//							} catch (IllegalArgumentException
//									| NoSuchFieldException | SecurityException e) {
//							}
//						}
//
//						if (fields[i].getType().equals(double.class)
//								|| fields[i].getType().equals(Double.class)) {
//							try {
//								toReturn.getClass()
//										.getField(propName)
//										.setDouble(toReturn,
//												Double.parseDouble(split[1]));
//							} catch (IllegalArgumentException
//									| NoSuchFieldException | SecurityException e) {
//							}
//						}
//
//						if (fields[i].getType().equals(boolean.class)
//								|| fields[i].getType().equals(Boolean.class)) {
//							try {
//								toReturn.getClass()
//										.getField(propName)
//										.setBoolean(toReturn,
//												Boolean.parseBoolean(split[1]));
//							} catch (IllegalArgumentException
//									| NoSuchFieldException | SecurityException e) {
//							}
//						}
//
//						if (fields[i].getType().equals(String.class)) {
//							try {
//								toReturn.getClass().getField(propName)
//										.set(toReturn, split[1]);
//							} catch (IllegalArgumentException
//									| NoSuchFieldException | SecurityException e) {
//							}
//						}
					}
				}
			}
			return toReturn;
		} catch (FileNotFoundException | IllegalAccessException e) {
			return toReturn;
		} finally {
			if (scan != null)
				scan.close();
		}
	}

	/**
	 * Loads a config file from the given path into the static variables of a class.
	 * @param type The type to load the data into.
	 * @param path The path to the config file.
	 */
	public static void loadStaticConfig(Class<? extends Object> type,
			String path) {
		Scanner scan = null;

		Field[] allFields = type.getFields();
		List<Field> fields = new LinkedList<Field>();
		for (Field f : allFields) {
			if (Modifier.isStatic(f.getModifiers()))
				fields.add(f);
		}

		try {
			scan = new Scanner(new FileInputStream(path));

			while (scan.hasNextLine()) {
				String ln = scan.nextLine();

				String[] split = ln.split(regexDelim);
				String propName = split[0];

				for (Field f : fields) {
					if (f.getName().equals(propName)) {
						boolean shouldBeIgnored = false;
						for (Annotation a : f.getDeclaredAnnotations()) {
							if (a.annotationType()
									.equals(HvlConfigIgnore.class)) {
								HvlConfigIgnore ign = (HvlConfigIgnore) a;
								if (ign.value() == IgnoreType.BOTH
										|| ign.value() == IgnoreType.LOAD)
									shouldBeIgnored = true;
								break;
							}
						}
						if (shouldBeIgnored)
							continue;

						if (f.getType().isArray()) {
							Pattern pattern = Pattern.compile("'(.*?)'");
							Matcher match = pattern.matcher(split[1]);
							Class<?> arrayType = getArrayType(f
									.getType());
							List<String> matches = new LinkedList<>();
							while (match.find())
							{
								matches.add(match.group(1));
							}
							
							Object arr = Array.newInstance(arrayType, matches.size());
							for (int j = 0; j < matches.size(); j++)
							{
								Array.set(arr, j, HvlReflectionUtil.genericParse(arrayType, matches.get(j)));
							}
							
							f.set(null, arr);
						} else {
							try {
								type
										.getField(propName)
										.set(null,
												HvlReflectionUtil.genericParse(type
														.getField(propName)
														.getType(), split[1]));
							} catch (IllegalArgumentException
									| NoSuchFieldException | SecurityException e1) {
								e1.printStackTrace();
							}
						}
					}
				}
			}
			return;
		} catch (FileNotFoundException | IllegalAccessException e) {
			return;
		} finally {
			if (scan != null)
				scan.close();
		}
	}

	/**
	 * Saves the properties of a class to a config file.
	 * <ul>
	 * Note regarding the <code>includeStatic</code> parameter:
	 * To load files saved like this, you need to use both loadConfig and loadStaticConfig.
	 * @param in The object to save the properties of.
	 * @param path The path to save the config file at.
	 * @param includeStatic Should this also save the static properties of the class?
	 */
	public static <TConfigType> void saveConfig(TConfigType in, String path,
			boolean includeStatic) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(path));

			for (Field f : in.getClass().getFields()) {
				boolean shouldBeIgnored = false;
				for (Annotation a : f.getDeclaredAnnotations()) {
					if (a.annotationType().equals(HvlConfigIgnore.class)) {
						HvlConfigIgnore ign = (HvlConfigIgnore) a;
						if (ign.value() == IgnoreType.BOTH
								|| ign.value() == IgnoreType.SAVE)
							shouldBeIgnored = true;
						break;
					}
				}
				if (Modifier.isStatic(f.getModifiers()) && !includeStatic)
					shouldBeIgnored = true;

				if (shouldBeIgnored)
					continue;

				
				if (f.getType().isArray())
				{
					Class<?> arrType = getArrayType(f.getType());
					if (arrType.equals(String.class)
							|| arrType.equals(Integer.class)
							|| arrType.equals(int.class)
							|| arrType.equals(Float.class)
							|| arrType.equals(float.class)
							|| arrType.equals(Double.class)
							|| arrType.equals(double.class)
							|| arrType.equals(Boolean.class)
							|| arrType.equals(boolean.class)) {
						int l = Array.getLength(f.get(in));
						
						StringBuilder sb = new StringBuilder();
						sb.append(f.getName() + delimiter);
						sb.append("{");
						if (l > 0)
						{
							for (int j = 0; j < l - 1; j++)
							{

								sb.append("'" + Array.get(f.get(in), j) + "', ");
							}
							
							sb.append("'" + Array.get(f.get(in), l - 1) + "'");
						}
						
						sb.append("}");
						
						writer.write(sb.toString());
						writer.write(System.lineSeparator());
					}
				}
				else
				{
					// If we support the type
					if (f.getType().equals(String.class)
							|| f.getType().equals(Integer.class)
							|| f.getType().equals(int.class)
							|| f.getType().equals(Float.class)
							|| f.getType().equals(float.class)
							|| f.getType().equals(Double.class)
							|| f.getType().equals(double.class)
							|| f.getType().equals(Boolean.class)
							|| f.getType().equals(boolean.class)) {
						try {
							writer.write(f.getName() + delimiter + f.get(in));
							writer.write(System.lineSeparator());
						} catch (IllegalArgumentException | IllegalAccessException e) {
							e.printStackTrace();
						}
					}
				}
			}

			writer.close();
		} catch (IOException | IllegalArgumentException | IllegalAccessException e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * Saves the static properties of a class to a config file.
	 * @param type The type to save the properties of.
	 * @param path The path to save the config file at.
	 */
	public static void saveStaticConfig(Class<? extends Object> type,
			String path) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(path));

			for (Field f : type.getFields()) {
				boolean shouldBeIgnored = false;
				for (Annotation a : f.getDeclaredAnnotations()) {
					if (a.annotationType().equals(HvlConfigIgnore.class)) {
						HvlConfigIgnore ign = (HvlConfigIgnore) a;
						if (ign.value() == IgnoreType.BOTH
								|| ign.value() == IgnoreType.SAVE)
							shouldBeIgnored = true;
						break;
					}
				}
				if (!Modifier.isStatic(f.getModifiers()))
					shouldBeIgnored = true;

				if (shouldBeIgnored)
					continue;

				if (f.getType().isArray())
				{
					Class<?> arrType = getArrayType(f.getType());
					if (arrType.equals(String.class)
							|| arrType.equals(Integer.class)
							|| arrType.equals(int.class)
							|| arrType.equals(Float.class)
							|| arrType.equals(float.class)
							|| arrType.equals(Double.class)
							|| arrType.equals(double.class)
							|| arrType.equals(Boolean.class)
							|| arrType.equals(boolean.class)) {
						int l = Array.getLength(f.get(null));
						
						StringBuilder sb = new StringBuilder();
						sb.append(f.getName() + delimiter);
						sb.append("{");
						if (l > 0)
						{
							for (int j = 0; j < l - 1; j++)
							{

								sb.append("'" + Array.get(f.get(null), j) + "', ");
							}
							
							sb.append("'" + Array.get(f.get(null), l - 1) + "'");
						}
						
						sb.append("}");
						
						writer.write(sb.toString());
						writer.write(System.lineSeparator());
					}
				}
				else
				{
					// If we support the type
					if (f.getType().equals(String.class)
							|| f.getType().equals(Integer.class)
							|| f.getType().equals(int.class)
							|| f.getType().equals(Float.class)
							|| f.getType().equals(float.class)
							|| f.getType().equals(Double.class)
							|| f.getType().equals(double.class)
							|| f.getType().equals(Boolean.class)
							|| f.getType().equals(boolean.class)) {
						try {
							writer.write(f.getName() + delimiter + f.get(null));
							writer.write(System.lineSeparator());
						} catch (IllegalArgumentException | IllegalAccessException e) {
							e.printStackTrace();
						}
					}
				}
			}

			writer.close();
		} catch (IOException | ArrayIndexOutOfBoundsException | IllegalArgumentException | IllegalAccessException e1) {
			e1.printStackTrace();
		}
	}

	private static Class<?> getArrayType(Class<?> in) {
		if (!in.isArray())
			return in;

		String thing = in.getName().replaceFirst("\\[", "");

		if (thing.endsWith(";"))
			thing = thing.substring(0, thing.length() - 1);
		
		if (thing.equals("I"))
			return int.class;
		if (thing.equals("F"))
			return float.class;
		if (thing.equals("D"))
			return double.class;
		if (thing.equals("L"))
			return long.class;
		if (thing.equals("S"))
			return short.class;
		if (thing.equals("Z"))
			return boolean.class;
		if (thing.equals("C"))
			return char.class;

		try {
			return Class.forName(thing.replaceFirst("L", ""));
		} catch (ClassNotFoundException e) {
		}
		return null;
	}
}
