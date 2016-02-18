package com.osreboot.ridhvl.test;

import com.osreboot.ridhvl.config.HvlConfigUtil;
import com.osreboot.ridhvl.test.TestConfigType.TCT4;
import com.osreboot.ridhvl.test.TestConfigType.TCT5;

public class ConfigUtilTest {	
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
		System.out.println();
		for (TCT4 obj : loaded.aClassArray) {
			System.out.println(obj.prop);
			System.out.println(obj.value);
			System.out.println();
			for (TCT5 nest : obj.nestedClassArray) {
				System.out.println(nest.val);
			}
			System.out.println();
		}
		
		System.out.println(TestConfigType.testingStatics);
		
		HvlConfigUtil.save(loaded, "res/TestSaveConfig.txt", true, true);
	}
}
