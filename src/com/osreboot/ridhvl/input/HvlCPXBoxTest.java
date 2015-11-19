package com.osreboot.ridhvl.input;


public class HvlCPXBoxTest extends HvlControllerProfile{

	public HvlCPXBoxTest(){
		super(HvlCPXBoxTest.class, "Xbox 360");
	}

	@HvlPollValue(component = "X Axis", deadZone = 0.3f)
	public static final String JOY1X = "JOY1X";
	
	@HvlPollValue(component = "Y Axis", deadZone = 0.3f)
	public static final String JOY1Y = "JOY1Y";
	
}
