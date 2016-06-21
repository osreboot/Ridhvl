package com.osreboot.ridhvl.test;

import java.util.ArrayList;
import java.util.LinkedList;

import com.osreboot.ridhvl.config.HvlConfigUtil;
import com.osreboot.ridhvl.test.TestConfigType.TCT4;
import com.osreboot.ridhvl.test.TestConfigType.TCT5;

public class ConfigUtilTest {	
	public ConfigUtilTest() {
		start();
	}
	
	public void start() {
//		TestConfigType loaded = HvlConfigUtil.load(TestConfigType.class, "res/TestLoadConfig.txt", true, true);
//		
//		System.out.println(loaded.aString);
//		System.out.println(loaded.someBool);
//		for (int i : loaded.anIntArray) {
//			System.out.println(i);
//		}
//		System.out.println(loaded.aClass.aString);
//		for (int i : loaded.anotherClass.anIntArray) {
//			System.out.println(i);
//		}
//		System.out.println(loaded.anotherClass.nestedClass.property);
//		System.out.println();
//		for (TCT4 obj : loaded.aClassArray) {
//			System.out.println(obj.prop);
//			System.out.println(obj.value);
//			System.out.println();
//			for (TCT5 nest : obj.nestedClassArray) {
//				System.out.println(nest.val);
//			}
//			System.out.println();
//		}
//		
//		System.out.println(TestConfigType.testingStatics);

		TestConfigType toSave = new TestConfigType();
		toSave.anIntArray = new LinkedList<Integer>();
		toSave.anIntArray.add(3);
		toSave.anIntArray.add(37);
		toSave.anIntArray.add(95);
		toSave.anIntArray.add(31415);
		toSave.anIntArray.add(-47);
		
		toSave.aClassArray = new ArrayList<TCT4>();
		toSave.aClassArray.add(new TCT4(true, 7, new TCT5("yes"), new TCT5("no"), new TCT5("maybe"), new TCT5("so")));
		toSave.aClassArray.add(new TCT4(false, 314, new TCT5("er"), new TCT5("mah"), new TCT5("gerd"), new TCT5("yusss"), new TCT5("it's"), new TCT5("so"), new TCT5("great!")));
		toSave.aClassArray.add(new TCT4(false, 314, new TCT5("how"), new TCT5("do?")));
		
		HvlConfigUtil.save(toSave, "res/TestSaveConfig.txt", true, true);
	}
}
