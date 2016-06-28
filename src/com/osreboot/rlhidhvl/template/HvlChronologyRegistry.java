package com.osreboot.rlhidhvl.template;

import com.osreboot.rlhidhvl.HvlMath;
import com.osreboot.rlhidhvl.external.HvlEnvironment;

public class HvlChronologyRegistry {

	public static void registerChronologies(){
		HvlChronology.registerChronology(HvlMath.Stepper.class);
		HvlChronology.registerChronology(HvlEnvironment.class);
	}
	
}
