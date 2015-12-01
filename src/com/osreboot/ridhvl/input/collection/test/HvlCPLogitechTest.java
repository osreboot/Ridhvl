package com.osreboot.ridhvl.input.collection.test;

import com.osreboot.ridhvl.action.HvlAction2r;
import com.osreboot.ridhvl.input.HvlControllerProfile;
import com.osreboot.ridhvl.input.HvlPollValue;

import net.java.games.input.Controller;

public class HvlCPLogitechTest extends HvlControllerProfile{

	public HvlCPLogitechTest(){
		super(HvlCPLogitechTest.class, "Rumble Gamepad F510 (Controller)");
	}

	public static final HvlAction2r<Float, Controller, HvlControllerProfile> UP_ARROW_CUSTOM = new HvlAction2r<Float, Controller, HvlControllerProfile>() {
		@Override
		public Float run(Controller c, HvlControllerProfile p) {
			if(p.getRawValue(c, HAT_SWITCH) == 0.25) return 1f; else return 0f;
		}
	};
	
	@HvlPollValue(component = "Hat Switch")
	private static final String HAT_SWITCH = "HAT_SWITCH";
	
	@HvlPollValue(component = "", custom = "UP_ARROW_CUSTOM")
	public static final String UP_ARROW = "UP_ARROW";
	
}
