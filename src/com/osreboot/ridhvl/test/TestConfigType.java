package com.osreboot.ridhvl.test;

import com.osreboot.ridhvl.config.HvlConfigIgnore;
import com.osreboot.ridhvl.config.HvlConfigIgnore.IgnoreType;

public class TestConfigType {
	
	@HvlConfigIgnore(IgnoreType.SAVE)
	public String test;
	
	public double someNumber;
	public int someInt;
	public static String wtf;
}
