package com.osreboot.ridhvl.input;

import java.util.LinkedList;

import org.lwjgl.input.Controllers;


public class HvlInput {
	
	private static LinkedList<HvlInput> inputs = new LinkedList<HvlInput>();
	
	public static abstract class HvlInputFilter{
		public abstract float getCurrentOutput();
	}
	
	public static abstract class HvlInputAction{
		public abstract void run(HvlInput inputArg);
	}
	
	public static void initialize(){
		try{
			Controllers.create();
			joystickEnabled = true;
		}catch(Exception e){
			System.out.println("Joystick control disabled: jinput.jar not available.");
			joystickEnabled = false;
		}
	}
	
	public static void update(){
		Controllers.poll();
		for(HvlInput input : inputs) input.updateEvents();
	}
	
	private static int controllerIndex = 0;
	private static boolean joystickEnabled;
	
	private HvlInputFilter[] filter;
	
	private float previousOutput;
	private HvlInputAction pressedAction, releasedAction;
	
	public HvlInput(HvlInputFilter... filterArg){
		filter = filterArg;
		previousOutput = 0;
		inputs.add(this);
	}
	
	public float getCurrentOutput(){
		float total = 0;
		for(int i = 0; i < filter.length; i++) total += filter[i].getCurrentOutput();
		return Math.min(1, total);
	}
	
	public void updateEvents(){
		if(previousOutput == 0 && getCurrentOutput() != 0) if(pressedAction != null) pressedAction.run(this);
		if(previousOutput != 0 && getCurrentOutput() == 0) if(releasedAction != null) releasedAction.run(this);
		previousOutput = getCurrentOutput();
	}
	
	public HvlInputAction getPressedAction(){
		return pressedAction;
	}

	public void setPressedAction(HvlInputAction pressedActionArg){
		pressedAction = pressedActionArg;
	}

	public HvlInputAction getReleasedAction(){
		return releasedAction;
	}

	public void setReleasedAction(HvlInputAction releasedActionArg){
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

	public static boolean isJoystickEnabled(){
		return joystickEnabled;
	}

	public static void setControllerIndex(int controllerIndexArg){
		controllerIndex = controllerIndexArg;
	}
	
}
