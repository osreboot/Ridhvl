package com.osreboot.ridhvl.input;

import java.util.LinkedList;

import org.lwjgl.input.Controllers;

import com.osreboot.ridhvl.action.HvlAction0;
import com.osreboot.ridhvl.action.HvlAction1;
import com.osreboot.ridhvl.external.HvlVerifier;
import com.osreboot.ridhvl.template.HvlChronology;


public class HvlInput {
	
	public static final int CHRONOLOGY = HvlChronology.INIT_CHRONOLOGY_EARLY + HvlChronology.INIT_CHRONOLOGY_DFLTINTVL;
	{
		new HvlChronology.Initialize(CHRONOLOGY, new HvlAction0(){
			@Override
			public void run(){
				initialize();
			}
		});
	}
	
	private static LinkedList<HvlInput> inputs = new LinkedList<HvlInput>();
	
	public static abstract class HvlInputFilter{
		public abstract float getCurrentOutput();
	}
	
	public static void initialize(){
		if(!HvlVerifier.VFR_JINPUT.isValid()) System.out.println("Joystick control disabled: jinput.jar not available.");
	}
	
	public static void update(){
		Controllers.poll();
		for(HvlInput input : inputs) input.updateEvents();
	}
	
	private static int controllerIndex = 0;
	
	private HvlInputFilter[] filter;
	
	private float previousOutput;
	private HvlAction1<HvlInput> pressedAction, releasedAction;
	
	public HvlInput(HvlInputFilter... filterArg){
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

	public HvlInputFilter[] getFilters(){
		return filter;
	}

	public void setFilter(HvlInputFilter... filterArg){
		filter = filterArg;
	}

	public static int getControllerIndex(){
		return controllerIndex;
	}

	public static void setControllerIndex(int controllerIndexArg){
		controllerIndex = controllerIndexArg;
	}
	
}
