package com.osreboot.ridhvl.input.collection;

import com.osreboot.ridhvl.input.HvlControllerProfile;
import com.osreboot.ridhvl.input.HvlPollValue;


public class HvlCPXBoxTest extends HvlControllerProfile{

	public HvlCPXBoxTest(){
		super(HvlCPXBoxTest.class, "Xbox 360");
	}

	@HvlPollValue(component = "X Axis", deadZone = 0.3f)
	public static final String JOY1X = "JOY1X";
	
}
