package com.osreboot.ridhvl.template;

import com.osreboot.ridhvl.HvlMath;
import com.osreboot.ridhvl.external.HvlEnvironment;

public class HvlChronologyRegistry {

	public static void registerChronologies(){
		HvlChronology.registerChronology(HvlMath.Stepper.class);
		HvlChronology.registerChronology(HvlEnvironment.class);
	}
	
}
