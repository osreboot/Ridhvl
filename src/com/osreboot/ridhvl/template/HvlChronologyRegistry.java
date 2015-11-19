package com.osreboot.ridhvl.template;

import com.osreboot.ridhvl.HvlMath;
import com.osreboot.ridhvl.display.HvlDisplay;
import com.osreboot.ridhvl.external.HvlVerifier;
import com.osreboot.ridhvl.input.HvlController;
import com.osreboot.ridhvl.input.HvlControllerProfile;
import com.osreboot.ridhvl.input.HvlInput;
import com.osreboot.ridhvl.menu.HvlMenuDJ;
import com.osreboot.ridhvl.painter.HvlAnimatedTexture;
import com.osreboot.ridhvl.painter.HvlCursor;

public class HvlChronologyRegistry {

	public static void registerChronologies(){
		HvlChronology.registerChronology(HvlMath.Stepper.class);
		HvlChronology.registerChronology(HvlCursor.class);
		HvlChronology.registerChronology(HvlAnimatedTexture.class);
		HvlChronology.registerChronology(HvlMenuDJ.class);
		HvlChronology.registerChronology(HvlDisplay.class);
		HvlChronology.registerChronology(HvlInput.class);
		HvlChronology.registerChronology(HvlVerifier.class);
		HvlChronology.registerChronology(HvlControllerProfile.class);
		HvlChronology.registerChronology(HvlController.class);
	}
	
}
