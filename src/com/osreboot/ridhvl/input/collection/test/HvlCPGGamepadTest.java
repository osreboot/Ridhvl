package com.osreboot.ridhvl.input.collection.test;

import com.osreboot.ridhvl.input.HvlCPGroup;
import com.osreboot.ridhvl.input.HvlControllerProfile;
import com.osreboot.ridhvl.input.HvlPollValueGroup;

public class HvlCPGGamepadTest extends HvlCPGroup{

	public HvlCPGGamepadTest(){
		super(HvlCPGGamepadTest.class);
	}

	@Override
	public HvlControllerProfile[] instantiateSubProfiles(){
		return new HvlControllerProfile[]{
				new HvlCPXBoxTest(),
		};
	}
	
	@HvlPollValueGroup({HvlCPXBoxTest.JOY1X})
	public static final String JOYINPUT = "JOY_INPUT_TEST";

}
