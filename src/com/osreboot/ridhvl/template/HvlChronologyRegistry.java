package com.osreboot.ridhvl.template;

import com.osreboot.ridhvl.HvlMath;
import com.osreboot.ridhvl.display.HvlDisplay;
import com.osreboot.ridhvl.external.HvlEnvironment;
import com.osreboot.ridhvl.input.HvlController;
import com.osreboot.ridhvl.input.HvlControllerProfile;
import com.osreboot.ridhvl.input.HvlInput;
import com.osreboot.ridhvl.menu.HvlMenuDJ;
import com.osreboot.ridhvl.menu.HvlMenuInteractor;
import com.osreboot.ridhvl.painter.HvlAnimatedTexture;
import com.osreboot.ridhvl.painter.HvlCursor;
import com.osreboot.ridhvl.painter.painter2d.HvlFontPainter2D;

public class HvlChronologyRegistry {

	public static void registerChronologies(){
		HvlChronology.registerChronology(HvlMath.Stepper.class);
		HvlChronology.registerChronology(HvlCursor.class);
		HvlChronology.registerChronology(HvlAnimatedTexture.class);
		HvlChronology.registerChronology(HvlMenuDJ.class);
		HvlChronology.registerChronology(HvlDisplay.class);
		HvlChronology.registerChronology(HvlInput.class);
		HvlChronology.registerChronology(HvlEnvironment.class);
		HvlChronology.registerChronology(HvlControllerProfile.class);
		HvlChronology.registerChronology(HvlController.class);
		HvlChronology.registerChronology(HvlMenuInteractor.class);
		HvlChronology.registerChronology(HvlFontPainter2D.Preset.class);
	}
	
}
