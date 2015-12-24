package com.osreboot.ridhvl.config;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.reflect.InvocationTargetException;

public class HvlConfigUtil {
//	public static <TConfigType> TConfigType load(Class<? extends TConfigType> type, String path) {
//		try {
//			BufferedReader read = new BufferedReader(new FileReader(new File(path)));
//
//			String line;
//
//			TConfigType tr = (TConfigType) type.getConstructor().newInstance();
//
//			int layer = 0; // how many classes deep this is right now
//			
//			while ((line = read.readLine()) != null) {
//				
//			}
//			
//			read.close();
//		} catch (Exception e) {
//			return null;
//		}
//	}
//
//	public static <TConfigType> void loadIntoExisting(TConfigType inst, String path) {
//		
//	}

	private static <TConfigType> void loadVar(Class<? extends TConfigType> type, String text) {
		try {
			TConfigType tr = (TConfigType) type.getConstructor().newInstance();
			
			
		} catch (Exception e) {
			
		}
	}
}
