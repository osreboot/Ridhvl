package com.osreboot.ridhvl.input;

import java.util.LinkedList;

import com.osreboot.ridhvl.action.HvlAction0;
import com.osreboot.ridhvl.action.HvlAction1;
import com.osreboot.ridhvl.external.HvlVerifier;
import com.osreboot.ridhvl.template.HvlChronology;
import com.osreboot.ridhvl.template.HvlChronologyInitialize;
import com.osreboot.ridhvl.template.HvlChronologyUpdate;


public class HvlInput {

	public static final int INIT_CHRONOLOGY = HvlChronology.INIT_CHRONOLOGY_EARLY + HvlChronology.INIT_CHRONOLOGY_DFLTINTVL;

	@HvlChronologyInitialize(chronology = INIT_CHRONOLOGY)
	public static final HvlAction0 INIT_ACTION = new HvlAction0(){
		@Override
		public void run(){
			initialize();
		}
	};

	public static final int UPDATE_CHRONOLOGY = HvlChronology.UPDATE_CHRONOLOGY_PRE_EARLY;

	@HvlChronologyUpdate(chronology = UPDATE_CHRONOLOGY)
	public static final HvlAction1<Float> UPDATE_ACTION = new HvlAction1<Float>(){
		@Override
		public void run(Float delta){
			update();
		}
	};

	private static LinkedList<HvlInput> inputs = new LinkedList<HvlInput>();

	public static abstract class InputFilter{
		public abstract float getCurrentOutput();
	}

	public static void initialize(){
		if(!HvlVerifier.VFR_JINPUT.isValid()) System.out.println("Joystick control disabled: jinput.jar not available.");
	}

	public static void update(){
		for(HvlInput input : inputs) input.updateEvents();
	}

	private InputFilter[] filter;

	private float previousOutput;
	private HvlAction1<HvlInput> pressedAction, releasedAction;

	public HvlInput(InputFilter... filterArg){
		filter = filterArg;
		previousOutput = 0;
		inputs.add(this);
	}

	public float getCurrentOutput(){
		float total = 0;
		for(int i = 0; i < filter.length; i++) total += filter[i].getCurrentOutput();
		return Math.min(1, Math.max(-1, total));
	}

	public void updateEvents(){
		if(previousOutput == 0 && getCurrentOutput() != 0) if(pressedAction != null) pressedAction.run(this);
		if(previousOutput != 0 && getCurrentOutput() == 0) if(releasedAction != null) releasedAction.run(this);
		previousOutput = getCurrentOutput();
	}

	public HvlAction1<HvlInput> getPressedAction(){
		return pressedAction;
	}

	public void setPressedAction(HvlAction1<HvlInput> pressedActionArg){
		pressedAction = pressedActionArg;
	}

	public HvlAction1<HvlInput> getReleasedAction(){
		return releasedAction;
	}

	public void setReleasedAction(HvlAction1<HvlInput> releasedActionArg){
		releasedAction = releasedActionArg;
	}

	public InputFilter[] getFilters(){
		return filter;
	}

	public void setFilter(InputFilter... filterArg){
		filter = filterArg;
	}

}
