package com.osreboot.ridhvl.input;

import java.util.ArrayList;

import com.osreboot.ridhvl.action.HvlAction0;
import com.osreboot.ridhvl.template.HvlChronology;
import com.osreboot.ridhvl.template.HvlChronologyInitialize;

import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;

public class HvlController {

	public static final int INIT_CHRONOLOGY = HvlChronology.INIT_CHRONOLOGY_LATE - (HvlChronology.INIT_CHRONOLOGY_DFLTINTVL);

	@HvlChronologyInitialize(chronology = INIT_CHRONOLOGY)
	public static final HvlAction0 INIT_ACTION = new HvlAction0(){
		@Override
		public void run(){
			rescanControllers();
		}
	};

	private static ArrayList<Controller> controllers = new ArrayList<>();

	public static ArrayList<Controller> getControllers(){
		return controllers;
	}

	public static void rescanControllers(){
		Controller[] newControllers = ControllerEnvironment.getDefaultEnvironment().getControllers();
		for(Controller c : newControllers) controllers.add(c);
		for(HvlControllerProfile p : HvlControllerProfile.getProfiles()) p.syncControllers();
	}

}
