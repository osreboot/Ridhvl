package com.osreboot.ridhvl.config;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class HvlConfig {

	public static boolean saveToFile(Object o, String path){
		try{
			FileOutputStream fos = new FileOutputStream(path);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(o);
			oos.close();
			fos.close();
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	public static <T> T loadFromFile(String path){
		try{
			FileInputStream fis = new FileInputStream(path);
			ObjectInputStream ois = new ObjectInputStream(fis);
			T output = (T)ois.readObject();
			ois.close();
			fis.close();
			return output;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
}
