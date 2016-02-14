package com.osreboot.ridhvl.test;

import com.osreboot.ridhvl.config.HvlConfigUtil;
import com.osreboot.ridhvl.test.TestConfigType.TCT4;
import com.osreboot.ridhvl.test.TestConfigType.TCT5;

public class ConfigUtilTest {

	public static void main(String[] args) {
		new ConfigUtilTest();
	}	
	
	public ConfigUtilTest() {
		start();
	}

	public void start() {
		TestConfigType loaded = HvlConfigUtil.load(TestConfigType.class, "res/TestLoadConfig.txt", true, true);
		
		System.out.println(loaded.aString);
		System.out.println(loaded.someBool);
		for (int i : loaded.anIntArray) {
			System.out.println(i);
		}
		System.out.println(loaded.aClass.aString);
		for (int i : loaded.anotherClass.anIntArray) {
			System.out.println(i);
		}
		System.out.println(loaded.anotherClass.nestedClass.property);
		for (TCT4 obj : loaded.aClassArray) {
			System.out.println(obj.prop);
			System.out.println(obj.value);
			for (TCT5 nest : obj.nestedClassArray) {
				System.out.println(nest.val);
			}
			System.out.println();
		}
		
//		TestConfigType loaded = HvlConfigUtil.load(TestConfigType.class, "res/TestLoadConfig.txt", true, true);
//		// HvlConfigUtil.loadStaticConfig(TestConfigType.class,
//		// "res/TestSaveConfig.txt");
//
//		System.out.println(loaded.someInt);
//		System.out.println(loaded.someNumber);
//		System.out.println(loaded.test);
//		System.out.println(loaded.magi.pi);
//		System.out.println(loaded.magi.wazzup);
//
////		for (String i : TestConfigType.arr) {
////			System.out.println(" - " + i);
////		}
////		System.out.println(TestConfigType.wtf);
//
////		System.out.println(HvlConfigUtil.saveVar(TestConfigType.class, true, true));
//		HvlConfigUtil.save(loaded, "res/TestSaveConfig.txt", true, true);
//
//		// TestConfigType.arr = new String[] {"HEY!", "BAI!", "MAGIC!" };
//
//		// HvlConfigUtil.saveConfig(loaded, "res/TestSaveConfig.txt", true);
//		// HvlConfigUtil.saveStaticConfig(TestConfigType.class,
//		// "res/TestStaticSaveConfig.txt");
	}
}
