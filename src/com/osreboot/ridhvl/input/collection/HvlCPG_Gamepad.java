package com.osreboot.ridhvl.input.collection;

import com.osreboot.ridhvl.input.HvlCPGroup;
import com.osreboot.ridhvl.input.HvlControllerProfile;
import com.osreboot.ridhvl.input.HvlPollValueGroup;

public class HvlCPG_Gamepad extends HvlCPGroup{

	public HvlCPG_Gamepad(){
		super(HvlCPG_Gamepad.class);
	}

	@Override
	public HvlControllerProfile[] instantiateSubProfiles(){
		return new HvlControllerProfile[]{
			new HvlCP_LogitechF510(),
			new HvlCP_XBox360WirelessReceiver(),
			new HvlCP_XBoxOne(),
			new HvlCP_XBox360()
		};
	}
	
	@HvlPollValueGroup({HvlCP_LogitechF510.JOY1X, HvlCP_XBox360WirelessReceiver.JOY1X, HvlCP_XBoxOne.JOY1X, HvlCP_XBox360.JOY1X})
	public static final String JOY1X = "JOY1X";
	
	@HvlPollValueGroup({HvlCP_LogitechF510.JOY1Y, HvlCP_XBox360WirelessReceiver.JOY1Y, HvlCP_XBoxOne.JOY1Y, HvlCP_XBox360.JOY1Y})
	public static final String JOY1Y = "JOY1Y";
	
	@HvlPollValueGroup({HvlCP_LogitechF510.JOY2X, HvlCP_XBox360WirelessReceiver.JOY2X, HvlCP_XBoxOne.JOY2X, HvlCP_XBox360.JOY2X})
	public static final String JOY2X = "JOY2X";
	
	@HvlPollValueGroup({HvlCP_LogitechF510.JOY2Y, HvlCP_XBox360WirelessReceiver.JOY2Y, HvlCP_XBoxOne.JOY1Y, HvlCP_XBox360.JOY1Y})
	public static final String JOY2Y = "JOY2Y";
	
	@HvlPollValueGroup({HvlCP_LogitechF510.JOY1PRESS, HvlCP_XBox360WirelessReceiver.JOY1PRESS, HvlCP_XBoxOne.JOY1PRESS, HvlCP_XBox360.JOY1PRESS})
	public static final String JOY1PRESS = "JOY1PRESS";
	
	@HvlPollValueGroup({HvlCP_LogitechF510.JOY2PRESS, HvlCP_XBox360WirelessReceiver.JOY2PRESS, HvlCP_XBoxOne.JOY2PRESS, HvlCP_XBox360.JOY2PRESS})
	public static final String JOY2PRESS = "JOY2PRESS";
	
	@HvlPollValueGroup({HvlCP_LogitechF510.BUTTON_A, HvlCP_XBox360WirelessReceiver.BUTTON_A, HvlCP_XBoxOne.BUTTON_A, HvlCP_XBox360.BUTTON_A})
	public static final String BUTTON_A = "BUTTON_A";
	
	@HvlPollValueGroup({HvlCP_LogitechF510.BUTTON_B, HvlCP_XBox360WirelessReceiver.BUTTON_B, HvlCP_XBoxOne.BUTTON_B, HvlCP_XBox360.BUTTON_B})
	public static final String BUTTON_B = "BUTTON_B";
	
	@HvlPollValueGroup({HvlCP_LogitechF510.BUTTON_X, HvlCP_XBox360WirelessReceiver.BUTTON_X, HvlCP_XBoxOne.BUTTON_X, HvlCP_XBox360.BUTTON_X})
	public static final String BUTTON_X = "BUTTON_X";
	
	@HvlPollValueGroup({HvlCP_LogitechF510.BUTTON_Y, HvlCP_XBox360WirelessReceiver.BUTTON_Y, HvlCP_XBoxOne.BUTTON_Y, HvlCP_XBox360.BUTTON_Y})
	public static final String BUTTON_Y = "BUTTON_Y";
	
	@HvlPollValueGroup({HvlCP_LogitechF510.DIRECTION_UP, HvlCP_XBox360WirelessReceiver.DIRECTION_UP, HvlCP_XBoxOne.DIRECTION_UP, HvlCP_XBox360.DIRECTION_UP})
	public static final String DIRECTION_UP = "DIRECTION_UP";
	
	@HvlPollValueGroup({HvlCP_LogitechF510.DIRECTION_DOWN, HvlCP_XBox360WirelessReceiver.DIRECTION_DOWN, HvlCP_XBoxOne.DIRECTION_DOWN, HvlCP_XBox360.DIRECTION_DOWN})
	public static final String DIRECTION_DOWN = "DIRECTION_DOWN";
	
	@HvlPollValueGroup({HvlCP_LogitechF510.DIRECTION_LEFT, HvlCP_XBox360WirelessReceiver.DIRECTION_LEFT, HvlCP_XBoxOne.DIRECTION_LEFT, HvlCP_XBox360.DIRECTION_LEFT})
	public static final String DIRECTION_LEFT = "DIRECTION_LEFT";
	
	@HvlPollValueGroup({HvlCP_LogitechF510.DIRECTION_RIGHT, HvlCP_XBox360WirelessReceiver.DIRECTION_RIGHT, HvlCP_XBoxOne.DIRECTION_RIGHT, HvlCP_XBox360.DIRECTION_RIGHT})
	public static final String DIRECTION_RIGHT = "DIRECTION_RIGHT";
	
	@HvlPollValueGroup({HvlCP_LogitechF510.BUTTON_BACK, HvlCP_XBox360WirelessReceiver.BUTTON_BACK, HvlCP_XBoxOne.BUTTON_BACK, HvlCP_XBox360.BUTTON_BACK})
	public static final String BUTTON_BACK = "BUTTON_BACK";
	
	@HvlPollValueGroup({HvlCP_LogitechF510.BUTTON_START, HvlCP_XBox360WirelessReceiver.BUTTON_START, HvlCP_XBoxOne.BUTTON_START, HvlCP_XBox360.BUTTON_START})
	public static final String BUTTON_START = "BUTTON_START";
	
	@HvlPollValueGroup({HvlCP_LogitechF510.TRIGGER_LEFT, HvlCP_XBox360WirelessReceiver.TRIGGER_LEFT, HvlCP_XBoxOne.TRIGGER_LEFT, HvlCP_XBox360.TRIGGER_LEFT})
	public static final String TRIGGER_LEFT = "TRIGGER_LEFT";
	
	@HvlPollValueGroup({HvlCP_LogitechF510.TRIGGER_RIGHT, HvlCP_XBox360WirelessReceiver.TRIGGER_RIGHT, HvlCP_XBoxOne.TRIGGER_RIGHT, HvlCP_XBox360.TRIGGER_RIGHT})
	public static final String TRIGGER_RIGHT = "TRIGGER_RIGHT";
	
	@HvlPollValueGroup({HvlCP_LogitechF510.BUMPER_LEFT, HvlCP_XBox360WirelessReceiver.BUMPER_LEFT, HvlCP_XBoxOne.BUMPER_LEFT, HvlCP_XBox360.BUMPER_LEFT})
	public static final String BUMPER_LEFT = "BUMPER_LEFT";
	
	@HvlPollValueGroup({HvlCP_LogitechF510.BUMPER_RIGHT, HvlCP_XBox360WirelessReceiver.BUMPER_RIGHT, HvlCP_XBoxOne.BUMPER_RIGHT, HvlCP_XBox360.BUMPER_RIGHT})
	public static final String BUMPER_RIGHT = "BUMPER_RIGHT";
	
	public static final String[] RANGE_ALL = {JOY1X, JOY1Y, JOY2X, JOY2Y, JOY1PRESS, JOY2PRESS, BUTTON_A, BUTTON_B, BUTTON_X, BUTTON_Y, DIRECTION_UP, DIRECTION_DOWN,
		DIRECTION_LEFT, DIRECTION_RIGHT, BUTTON_BACK, BUTTON_START, TRIGGER_LEFT, TRIGGER_RIGHT, BUMPER_LEFT, BUMPER_RIGHT};
	
}
