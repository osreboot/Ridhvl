package com.osreboot.ridhvl.test;

import com.osreboot.ridhvl.config.HvlConfigUtil;

public class ConfigUtilTest {

	public ConfigUtilTest() {
		
	}
	
	public void start()
	{
		TestConfigType loaded = HvlConfigUtil.loadConfig(TestConfigType.class, "res/TestConfig.txt");
		System.out.println(loaded.test);
		System.out.println(loaded.someNumber);
		System.out.println(loaded.someInt);
		
		HvlConfigUtil.loadStaticConfig(TestConfigType.class, "res/TestConfig.txt");
		System.out.println(TestConfigType.wtf);
	}
}
