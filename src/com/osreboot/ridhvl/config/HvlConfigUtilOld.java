//package com.osreboot.ridhvl.config;
//
//import java.io.BufferedReader;
//import java.io.BufferedWriter;
//import java.io.File;
//import java.io.FileReader;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.lang.reflect.Array;
//import java.lang.reflect.Field;
//import java.lang.reflect.Modifier;
//
//import com.osreboot.ridhvl.HvlReflectionUtil;
//import com.osreboot.ridhvl.config.HvlConfigIgnore.IgnoreType;
//
//public class HvlConfigUtilOld {
//	public static <TConfigType> TConfigType load(Class<? extends TConfigType> type, String path, boolean loadInstance, boolean loadStatic) {
//		try {
//			BufferedReader reader = new BufferedReader(new FileReader(new File(path)));
//
//			StringBuilder sb = new StringBuilder();
//			String line;
//
//			while ((line = reader.readLine()) != null) {
//				sb.append(line + System.lineSeparator());
//			}
//
//			reader.close();
//
//			return loadVar(type, sb.toString(), loadInstance, loadStatic);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return null;
//		}
//	}
//
//	public static void loadStatic(Class<?> type, String path, boolean loadInstance, boolean loadStatic) {
//		try {
//			BufferedReader reader = new BufferedReader(new FileReader(new File(path)));
//
//			StringBuilder sb = new StringBuilder();
//			String line;
//
//			while ((line = reader.readLine()) != null) {
//				sb.append(line + System.lineSeparator());
//			}
//
//			reader.close();
//
//			loadStaticVar(type, sb.toString(), loadInstance, loadStatic);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	public static void save(Object toSave, String path, boolean saveInstance, boolean saveStatic) {
//		String out = saveVar(toSave, saveInstance, saveStatic);
//
//		try {
//			BufferedWriter write = new BufferedWriter(new FileWriter(new File(path)));
//			write.write(out);
//			write.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//	
//	public static void saveStatic(Class<?> type, String path, boolean saveInstance, boolean saveStatic) {
//		String out = saveStaticVar(type, saveInstance, saveStatic);
//
//		try {
//			BufferedWriter write = new BufferedWriter(new FileWriter(new File(path)));
//			write.write(out);
//			write.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//
//	public static String saveVar(Object toSave, boolean saveInstance, boolean saveStatic) {
//		try {
//			StringBuilder tr = new StringBuilder();
//
//			for (Field f : toSave.getClass().getFields()) {
//				if (f.isAnnotationPresent(HvlConfigIgnore.class)) {
//					HvlConfigIgnore ign = f.getAnnotation(HvlConfigIgnore.class);
//					if (ign.value() == IgnoreType.BOTH || ign.value() == IgnoreType.SAVE)
//						continue;
//				}
//
//				if (!saveStatic && Modifier.isStatic(f.getModifiers()) || !saveInstance && !Modifier.isStatic(f.getModifiers()))
//					continue;
//
//				if (f.getType().isArray() && HvlReflectionUtil.isSimple(f.getType().getComponentType())) {
//					Object arr = f.get(toSave);
//					tr.append(f.getName() + ":");
//					for (int i = 0; i < Array.getLength(arr); i++) {
//						tr.append(Array.get(arr, i) + (i == Array.getLength(arr) - 1 ? "" : ",")); // TODO:
//																									// Less
//																									// common
//																									// delimeter?
//					}
//				} else if (HvlReflectionUtil.isSimple(f.getType())) {
//					tr.append(f.getName() + ":" + f.get(toSave).toString() + System.lineSeparator());
//				} else {
//					tr.append(f.getName() + ":{" + System.lineSeparator());
//					tr.append(saveVar(f.get(toSave), saveInstance, saveStatic));
//					tr.append(System.lineSeparator() + "}" + System.lineSeparator());
//				}
//			}
//
//			return tr.toString();
//		} catch (Exception e) {
//			e.printStackTrace();
//			return null;
//		}
//	}
//
//	public static String saveStaticVar(Class<?> type, boolean saveInstance, boolean saveStatic) {
//		try {
//			StringBuilder tr = new StringBuilder();
//
//			for (Field f : type.getFields()) {
//				if (f.isAnnotationPresent(HvlConfigIgnore.class)) {
//					HvlConfigIgnore ign = f.getAnnotation(HvlConfigIgnore.class);
//					if (ign.value() == IgnoreType.BOTH || ign.value() == IgnoreType.SAVE)
//						continue;
//				}
//
//				if (!Modifier.isStatic(f.getModifiers()))
//					continue;
//
//				if (f.getType().isArray() && HvlReflectionUtil.isSimple(f.getType().getComponentType())) {
//					Object arr = f.get(null);
//					tr.append(f.getName() + ":");
//					for (int i = 0; i < Array.getLength(arr); i++) {
//						tr.append(Array.get(arr, i) + (i == Array.getLength(arr) - 1 ? "" : ",")); // TODO: Less common delimiter?
//					}
//				} else if (HvlReflectionUtil.isSimple(f.getType())) {
//
//					tr.append(f.getName() + ":" + f.get(null).toString() + System.lineSeparator());
//				} else {
//					tr.append(f.getName() + ":{" + System.lineSeparator());
//					tr.append(saveVar(f.get(null), saveInstance, saveStatic));
//					tr.append(System.lineSeparator() + "}" + System.lineSeparator());
//				}
//			}
//
//			return tr.toString();
//		} catch (Exception e) {
//			e.printStackTrace();
//			return null;
//		}
//	}
//
//	private static <TConfigType> TConfigType loadVar(Class<? extends TConfigType> type, String text, boolean loadInstance, boolean loadStatic) {
//		try {
//			TConfigType tr = (TConfigType) type.getConstructor().newInstance();
//
//			String[] split = text.split(System.lineSeparator());
//
//			String classField = null;
//			StringBuilder classInsides = null;
//
//			int classDepth = 0;
//
//			for (String line : split) {
//				if (classField != null) {
//					if (line.equals("}")) {
//						classDepth--;
//						if (classDepth == 0) {
//							boolean ignore = false;
//
//							if (type.getField(classField).isAnnotationPresent(HvlConfigIgnore.class)) {
//								HvlConfigIgnore ign = type.getField(classField).getAnnotation(HvlConfigIgnore.class);
//								if (ign.value() == IgnoreType.BOTH || ign.value() == IgnoreType.LOAD) {
//									ignore = true;
//								}
//							}
//
//							if ((!loadStatic && Modifier.isStatic(type.getField(classField).getModifiers())) || (!loadInstance && !Modifier.isStatic(type.getField(classField).getModifiers())))
//								ignore = true;
//
//							if (!ignore)
//								tr.getClass().getField(classField).set(tr, loadVar(type.getField(classField).getType(), classInsides.toString(), loadInstance, loadStatic));
//							classField = null;
//							classInsides = null;
//						}
//					} else {
//						classInsides.append(line + System.lineSeparator());
//					}
//				} else {
//					String[] parts = line.split(":");
//
//					if (parts.length != 2)
//						continue;
//
//					String name = parts[0];
//
//					if (parts[1].equals("{")) {
//						classField = name;
//						classDepth++;
//						classInsides = new StringBuilder();
//					} else {
//						Class<?> fieldType = type.getField(name).getType();
//
//						if (fieldType.isArray()) {
//							String[] arrayParts = parts[1].split(","); // TODO:
//																		// Non-common
//																		// string
//																		// delimiter
//							Object arr = Array.newInstance(fieldType.getComponentType(), new int[] { arrayParts.length });
//							for (int i = 0; i < arrayParts.length; i++) {
//								Array.set(arr, i, HvlReflectionUtil.genericParse(fieldType.getComponentType(), arrayParts[i]));
//							}
//
//							boolean ignore = false;
//
//							if (type.getField(name).isAnnotationPresent(HvlConfigIgnore.class)) {
//								HvlConfigIgnore ign = type.getField(name).getAnnotation(HvlConfigIgnore.class);
//								if (ign.value() == IgnoreType.BOTH || ign.value() == IgnoreType.LOAD) {
//									ignore = true;
//								}
//							}
//
//							if ((!loadStatic && Modifier.isStatic(type.getField(name).getModifiers())) || (!loadInstance && !Modifier.isStatic(type.getField(name).getModifiers())))
//								ignore = true;
//
//							if (!ignore)
//								type.getField(name).set(tr, arr);
//						} else {
//							boolean ignore = false;
//
//							if (type.getField(name).isAnnotationPresent(HvlConfigIgnore.class)) {
//								HvlConfigIgnore ign = type.getField(name).getAnnotation(HvlConfigIgnore.class);
//								if (ign.value() == IgnoreType.BOTH || ign.value() == IgnoreType.LOAD) {
//									ignore = true;
//								}
//							}
//
//							if ((!loadStatic && Modifier.isStatic(type.getField(name).getModifiers())) || (!loadInstance && !Modifier.isStatic(type.getField(name).getModifiers())))
//								ignore = true;
//
//							if (!ignore)
//								type.getField(name).set(tr, HvlReflectionUtil.genericParse(fieldType, parts[1]));
//						}
//					}
//				}
//			}
//
//			return tr;
//		} catch (Exception e) {
//			e.printStackTrace();
//			return null;
//		}
//	}
//
//	private static void loadStaticVar(Class<?> type, String text, boolean loadInstance, boolean loadStatic) {
//		try {
//			String[] split = text.split(System.lineSeparator());
//
//			String classField = null;
//			StringBuilder classInsides = null;
//
//			int classDepth = 0;
//
//			for (String line : split) {
//				if (classField != null) {
//					if (line.equals("}")) {
//						classDepth--;
//						if (classDepth == 0) {
//							boolean ignore = false;
//
//							if (type.getField(classField).isAnnotationPresent(HvlConfigIgnore.class)) {
//								HvlConfigIgnore ign = type.getField(classField).getAnnotation(HvlConfigIgnore.class);
//								if (ign.value() == IgnoreType.BOTH || ign.value() == IgnoreType.LOAD) {
//									ignore = true;
//								}
//							}
//
//							if (!Modifier.isStatic(type.getField(classField).getModifiers()))
//								ignore = true;
//
//							if (!ignore)
//								type.getField(classField).set(null, loadVar(type.getField(classField).getType(), classInsides.toString(), loadInstance, loadStatic));
//							classField = null;
//							classInsides = null;
//						}
//					} else {
//						classInsides.append(line + System.lineSeparator());
//					}
//				} else {
//					String[] parts = line.split(":");
//
//					if (parts.length != 2)
//						continue;
//
//					String name = parts[0];
//
//					if (parts[1].equals("{")) {
//						classField = name;
//						classDepth++;
//						classInsides = new StringBuilder();
//					} else {
//						Class<?> fieldType = type.getField(name).getType();
//
//						if (fieldType.isArray()) {
//							String[] arrayParts = parts[1].split(","); // TODO:
//																		// Non-common
//																		// string
//																		// delimiter
//							Object arr = Array.newInstance(fieldType.getComponentType(), new int[] { arrayParts.length });
//							for (int i = 0; i < arrayParts.length; i++) {
//								Array.set(arr, i, HvlReflectionUtil.genericParse(fieldType.getComponentType(), arrayParts[i]));
//							}
//
//							boolean ignore = false;
//
//							if (type.getField(name).isAnnotationPresent(HvlConfigIgnore.class)) {
//								HvlConfigIgnore ign = type.getField(name).getAnnotation(HvlConfigIgnore.class);
//								if (ign.value() == IgnoreType.BOTH || ign.value() == IgnoreType.LOAD) {
//									ignore = true;
//								}
//							}
//
//							if (!Modifier.isStatic(type.getField(classField).getModifiers()))
//								ignore = true;
//
//							if (!ignore)
//								type.getField(name).set(null, arr);
//						} else {
//							boolean ignore = false;
//
//							if (type.getField(name).isAnnotationPresent(HvlConfigIgnore.class)) {
//								HvlConfigIgnore ign = type.getField(name).getAnnotation(HvlConfigIgnore.class);
//								if (ign.value() == IgnoreType.BOTH || ign.value() == IgnoreType.LOAD) {
//									ignore = true;
//								}
//							}
//
//							if (!Modifier.isStatic(type.getField(classField).getModifiers()))
//								ignore = true;
//
//							if (!ignore)
//								type.getField(name).set(null, HvlReflectionUtil.genericParse(fieldType, parts[1]));
//						}
//					}
//				}
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//}
