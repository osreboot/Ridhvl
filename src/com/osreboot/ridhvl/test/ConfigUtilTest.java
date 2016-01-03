package com.osreboot.ridhvl.test;

import com.osreboot.ridhvl.config.HvlConfigUtil;

public class ConfigUtilTest {

	public ConfigUtilTest() {
		start();
	}

	public void start() {
		TestConfigType loaded = HvlConfigUtil.load(TestConfigType.class, "res/TestLoadConfig.txt", true, true);
		// HvlConfigUtil.loadStaticConfig(TestConfigType.class,
		// "res/TestSaveConfig.txt");

		System.out.println(loaded.someInt);
		System.out.println(loaded.someNumber);
		System.out.println(loaded.test);
		System.out.println(loaded.magi.pi);
		System.out.println(loaded.magi.wazzup);

//		for (String i : TestConfigType.arr) {
//			System.out.println(" - " + i);
//		}
//		System.out.println(TestConfigType.wtf);

//		System.out.println(HvlConfigUtil.saveVar(TestConfigType.class, true, true));
		HvlConfigUtil.save(loaded, "res/TestSaveConfig.txt", true, true);

		// TestConfigType.arr = new String[] {"HEY!", "BAI!", "MAGIC!" };

		// HvlConfigUtil.saveConfig(loaded, "res/TestSaveConfig.txt", true);
		// HvlConfigUtil.saveStaticConfig(TestConfigType.class,
		// "res/TestStaticSaveConfig.txt");
	}
}
