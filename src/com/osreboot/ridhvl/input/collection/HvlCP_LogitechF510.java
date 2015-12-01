package com.osreboot.ridhvl.input.collection;

import com.osreboot.ridhvl.input.HvlControllerProfile;
import com.osreboot.ridhvl.input.HvlPollValue;

public class HvlCP_LogitechF510 extends HvlControllerProfile{

	public HvlCP_LogitechF510(){
		super(HvlCP_LogitechF510.class, "Rumble Gamepad F510 (Controller)");
	}
	
	@HvlPollValue(component = "X Axis", deadZone = 0.05f)
	public static final String JOY1X = "JOY1X";
	
	@HvlPollValue(component = "Y Axis", deadZone = 0.05f)
	public static final String JOY1Y = "JOY1Y";
	
	@HvlPollValue(component = "X Rotation", deadZone = 0.05f)
	public static final String JOY2X = "JOY2X";
	
	@HvlPollValue(component = "Y Rotation", deadZone = 0.05f)
	public static final String JOY2Y = "JOY2Y";
	
	@HvlPollValue(component = "Button 0")
	public static final String BUTTON_A = "BUTTON_A";
	
	@HvlPollValue(component = "Button 1")
	public static final String BUTTON_B = "BUTTON_B";
	
	@HvlPollValue(component = "Button 2")
	public static final String BUTTON_X = "BUTTON_X";
	
	@HvlPollValue(component = "Button 3")
	public static final String BUTTON_Y = "BUTTON_Y";
	
	@HvlPollValue(component = "Button 4")
	public static final String BUMPER_LEFT = "BUMPER_LEFT";
	
	@HvlPollValue(component = "Button 5")
	public static final String BUMPER_RIGHT = "BUMPER_RIGHT";
	
	//TODO 'hat', back, start, joy3, trigger
	
}

