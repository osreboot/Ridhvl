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
		};
	}
	
	@HvlPollValueGroup({HvlCP_LogitechF510.JOY1X})
	public static final String JOY1X = "JOY1X";
	
	@HvlPollValueGroup({HvlCP_LogitechF510.JOY1Y})
	public static final String JOY1Y = "JOY1Y";
	
	@HvlPollValueGroup({HvlCP_LogitechF510.JOY2X})
	public static final String JOY2X = "JOY2X";
	
	@HvlPollValueGroup({HvlCP_LogitechF510.JOY2Y})
	public static final String JOY2Y = "JOY2Y";
	
	@HvlPollValueGroup({HvlCP_LogitechF510.JOY1PRESS})
	public static final String JOY1PRESS = "JOY1PRESS";
	
	@HvlPollValueGroup({HvlCP_LogitechF510.JOY2PRESS})
	public static final String JOY2PRESS = "JOY2PRESS";
	
	@HvlPollValueGroup({HvlCP_LogitechF510.BUTTON_A})
	public static final String BUTTON_A = "BUTTON_A";
	
	@HvlPollValueGroup({HvlCP_LogitechF510.BUTTON_B})
	public static final String BUTTON_B = "BUTTON_B";
	
	@HvlPollValueGroup({HvlCP_LogitechF510.BUTTON_X})
	public static final String BUTTON_X = "BUTTON_X";
	
	@HvlPollValueGroup({HvlCP_LogitechF510.BUTTON_Y})
	public static final String BUTTON_Y = "BUTTON_Y";
	
	@HvlPollValueGroup({HvlCP_LogitechF510.BUMPER_LEFT})
	public static final String BUMPER_LEFT = "BUMPER_LEFT";
	
	@HvlPollValueGroup({HvlCP_LogitechF510.BUMPER_RIGHT})
	public static final String BUMPER_RIGHT = "BUMPER_RIGHT";
	
	@HvlPollValueGroup({HvlCP_LogitechF510.BUTTON_BACK})
	public static final String BUTTON_BACK = "BUTTON_BACK";
	
	@HvlPollValueGroup({HvlCP_LogitechF510.BUTTON_START})
	public static final String BUTTON_START = "BUTTON_START";
	
	@HvlPollValueGroup({HvlCP_LogitechF510.TRIGGER_LEFT})
	public static final String TRIGGER_LEFT = "TRIGGER_LEFT";
	
	@HvlPollValueGroup({HvlCP_LogitechF510.TRIGGER_RIGHT})
	public static final String TRIGGER_RIGHT = "TRIGGER_RIGHT";
	
	@HvlPollValueGroup({HvlCP_LogitechF510.DIRECTION_UP})
	public static final String DIRECTION_UP = "DIRECTION_UP";
	
	@HvlPollValueGroup({HvlCP_LogitechF510.DIRECTION_DOWN})
	public static final String DIRECTION_DOWN = "DIRECTION_DOWN";
	
	@HvlPollValueGroup({HvlCP_LogitechF510.DIRECTION_LEFT})
	public static final String DIRECTION_LEFT = "DIRECTION_LEFT";
	
	@HvlPollValueGroup({HvlCP_LogitechF510.DIRECTION_RIGHT})
	public static final String DIRECTION_RIGHT = "DIRECTION_RIGHT";
	
}
