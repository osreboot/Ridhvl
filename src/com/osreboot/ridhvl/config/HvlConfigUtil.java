package com.osreboot.ridhvl.config;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import com.osreboot.ridhvl.HvlReflectionUtil;
import com.osreboot.ridhvl.config.HvlConfigIgnore.IgnoreType;

public class HvlConfigUtil {
	public static <TConfigType> TConfigType load(Class<TConfigType> type, String path, boolean loadInstance, boolean loadStatic) {

		try {
			BufferedReader reader = new BufferedReader(new FileReader(path));

			StringBuilder sb = new StringBuilder();
			String line;

			while ((line = reader.readLine()) != null) {
				sb.append(line + System.lineSeparator());
			}

			reader.close();

			return loadFromText(type, sb.toString(), loadInstance, loadStatic);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	private static <TConfigType> TConfigType loadFromText(Class<TConfigType> type, String text, boolean loadInstance, boolean loadStatic) {
		// split by line separator
		String[] split = text.split(System.lineSeparator());

		// define int for mode, define string for searching name, define string
		// builder for buildup of lines
		int mode = -1; // 0 = custom class, 1 = primitive array, 2 = custom
						// class array
		String searchingName = null;
		StringBuilder lineBuildup = null;

		// create return instance
		try {
			TConfigType tr = type.getConstructor().newInstance();

			// loop through each line
			for (String line : split) {
				if (line.replaceAll(" ", "").isEmpty()) continue;
				
				// if searching name is null (searching for variable)
				if (searchingName == null) {
					// get name of variable
					String varName = line.split(":")[0];

					// get field
					try {
						Field field = type.getField(varName);

						// if we shouldn't load continue (based on boolean args
						// and annotations)
						if (shouldLoadField(field, loadInstance, loadStatic)) {
							// get type of field from variable name
							Class<?> fieldType = field.getType();
							if (HvlReflectionUtil.isSimple(fieldType)) {
								// load with primitive loader
								field.set(tr, HvlReflectionUtil.genericParse(fieldType, line.split(":")[1]));
							} else if (fieldType.isArray()) {
								if (HvlReflectionUtil.isSimple(fieldType.getComponentType())) {
									searchingName = varName;
									mode = 1;
									lineBuildup = new StringBuilder();
								} else {
									searchingName = varName;
									mode = 2;
									lineBuildup = new StringBuilder();
								}
							} else {
								searchingName = varName;
								mode = 0;
								lineBuildup = new StringBuilder();
							}
						}
					} catch (NoSuchFieldException | SecurityException e) {
						e.printStackTrace();
					}
				} else {
					if (line.equals("/" + searchingName))
					{
						switch (mode) {
						case 0:
							// set variable with recursion
							try {
								Field f = type.getField(searchingName);
								f.set(tr, loadFromText(f.getType(), lineBuildup.toString(), loadInstance, loadStatic));
							} catch (NoSuchFieldException e1) {
								e1.printStackTrace();
							}
							lineBuildup = null;
							searchingName = null;
							mode = -1;
							break;
						case 1:
						{
							// set variable with primitive array loader (just here, no
							// recursion)
							try {
								Field arrayField = type.getField(searchingName);
								String[] arrayParts = lineBuildup.toString().split(System.lineSeparator());
								Object arr = Array.newInstance(arrayField.getType().getComponentType(), arrayParts.length);
								for (int i = 0; i < arrayParts.length; i++) {
									Array.set(arr, i, HvlReflectionUtil.genericParse(HvlReflectionUtil.convertAwayFromPrimitive(arrayField.getType().getComponentType()), arrayParts[i]));
								}
								arrayField.set(tr, arr);
							} catch (NoSuchFieldException e) {
								e.printStackTrace();
							}
							lineBuildup = null;
							searchingName = null;
							mode = -1;
							break;
						}
						case 2:
							// set variable with custom array loader (requires
							// recursion)
							try {
								String[] customArraySplit = lineBuildup.toString().split(System.lineSeparator());
								Field arrayField = type.getField(searchingName);
								StringBuilder classBuildup = new StringBuilder();
								int classDepth = 0;
								List<Object> loaded = new ArrayList<>();
								for (String customLine : customArraySplit) {
									if (customLine.equals("class:")) {
										if (classDepth++ != 0)
											classBuildup.append(customLine + System.lineSeparator());
									}
									else if (customLine.equals("/class")) {
										classDepth--;
										if (classDepth == 0) {
											Object toAdd = loadFromText(arrayField.getType().getComponentType(), classBuildup.toString(), loadInstance, loadStatic);
											loaded.add(toAdd);
											classBuildup = new StringBuilder();
										} else {
											classBuildup.append(customLine + System.lineSeparator());
										}
									}
									else {
										classBuildup.append(customLine + System.lineSeparator());
									}
								}
								
								Object arr = Array.newInstance(arrayField.getType().getComponentType(), loaded.size());
								for (int i = 0; i < loaded.size(); i++) {
									Array.set(arr, i, loaded.get(i));
								}
								arrayField.set(tr, arr);
								
							} catch (NoSuchFieldException e) {
								e.printStackTrace();
							}
							lineBuildup = null;
							searchingName = null;
							mode = -1;
							break;
						}
					}
					else {
						lineBuildup.append(line + System.lineSeparator());
					}
				}
			}
			
			return tr;
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
			return null;
		}
	}

	private static boolean shouldLoadField(Field field, boolean loadInstance, boolean loadStatic) {
		if (Modifier.isStatic(field.getModifiers()) && !loadStatic)
			return false;
		if (!Modifier.isStatic(field.getModifiers()) && !loadInstance)
			return false;

		if (field.isAnnotationPresent(HvlConfigIgnore.class)) {
			HvlConfigIgnore.IgnoreType t = field.getAnnotation(HvlConfigIgnore.class).value();
			if (t == IgnoreType.BOTH || t == IgnoreType.LOAD)
				return false;
		}

		return true;
	}
}
