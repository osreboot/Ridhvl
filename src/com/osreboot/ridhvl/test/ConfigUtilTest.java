package com.osreboot.ridhvl.test;

import com.osreboot.ridhvl.configold.HvlConfigUtil;

public class ConfigUtilTest {

	public ConfigUtilTest() {
		
	}
	
	public void start()
	{
		TestConfigType loaded = HvlConfigUtil.loadConfig(TestConfigType.class, "res/TestSaveConfig.txt");
		HvlConfigUtil.loadStaticConfig(TestConfigType.class, "res/TestSaveConfig.txt");
		
		System.out.println(loaded.someInt);
		System.out.println(loaded.someNumber);
		System.out.println(loaded.test);
		for (String i : TestConfigType.arr)
		{
			System.out.println(" - " + i);
		}
		System.out.println(TestConfigType.wtf);
		
		TestConfigType.arr = new String[] {"HEY!", "BAI!", "MAGIC!" };
		
		HvlConfigUtil.saveConfig(loaded, "res/TestSaveConfig.txt", true);
		HvlConfigUtil.saveStaticConfig(TestConfigType.class, "res/TestStaticSaveConfig.txt");
	}
}
