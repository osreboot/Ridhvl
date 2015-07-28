package com.osreboot.ridhvl.input;

import org.lwjgl.input.Controllers;


public class HvlInput {
	
	public static abstract class HvlInputFilter{
		public abstract float getCurrentOutput();
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
	}
	
	private static int controllerIndex = 0;
	private static boolean joystickEnabled;
	
	private HvlInputFilter filter;
	
	public HvlInput(HvlInputFilter filterArg){
		filter = filterArg;
	}
	
	public float getCurrentOutput(){
		return filter.getCurrentOutput();
	}
	
	public HvlInputFilter getFilter(){
		return filter;
	}

	public void setFilter(HvlInputFilter filterArg){
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
