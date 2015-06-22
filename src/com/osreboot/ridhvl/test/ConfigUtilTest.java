package com.osreboot.ridhvl.test;

import com.osreboot.ridhvl.config.HvlConfigUtil;

public class ConfigUtilTest {

	public ConfigUtilTest() {
		
	}
	
	public void start()
	{
		TestConfigType loaded = HvlConfigUtil.loadConfig(TestConfigType.class, "res/TestConfig.txt");
		
		HvlConfigUtil.loadStaticConfig(TestConfigType.class, "res/TestConfig.txt");
		
		HvlConfigUtil.saveConfig(loaded, "res/TestSaveConfig.txt", false);
		HvlConfigUtil.saveStaticConfig(TestConfigType.class, "res/TestStaticSaveConfig.txt");
	}
}
