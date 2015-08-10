package com.osreboot.ridhvl.input;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import com.osreboot.ridhvl.input.HvlInput.HvlInputFilter;

public class HvlInputSeriesAction {

	//TODO boolean mode for using joystick

	public static final HvlInputFilter FILTER_UP = new HvlInputFilter(){
		public float getCurrentOutput(){
			return Keyboard.isKeyDown(Keyboard.KEY_W) || Keyboard.isKeyDown(Keyboard.KEY_UP) ? 1 : 0;
		}
	};
	
	public static final HvlInputFilter FILTER_UP_NEGATIVE = new HvlInputFilter(){
		public float getCurrentOutput(){
			return Keyboard.isKeyDown(Keyboard.KEY_W) || Keyboard.isKeyDown(Keyboard.KEY_UP) ? -1 : 0;
		}
	};
	
	public static final HvlInputFilter FILTER_DOWN = new HvlInputFilter(){
		public float getCurrentOutput(){
			return Keyboard.isKeyDown(Keyboard.KEY_S) || Keyboard.isKeyDown(Keyboard.KEY_DOWN) ? 1 : 0;
		}
	};

	public static final HvlInputFilter FILTER_LEFT = new HvlInputFilter(){
		public float getCurrentOutput(){
			return Keyboard.isKeyDown(Keyboard.KEY_A) || Keyboard.isKeyDown(Keyboard.KEY_LEFT) ? 1 : 0;
		}
	};
	
	public static final HvlInputFilter FILTER_LEFT_NEGATIVE = new HvlInputFilter(){
		public float getCurrentOutput(){
			return Keyboard.isKeyDown(Keyboard.KEY_A) || Keyboard.isKeyDown(Keyboard.KEY_LEFT) ? -1 : 0;
		}
	};

	public static final HvlInputFilter FILTER_RIGHT = new HvlInputFilter(){
		public float getCurrentOutput(){
			return Keyboard.isKeyDown(Keyboard.KEY_D) || Keyboard.isKeyDown(Keyboard.KEY_RIGHT) ? 1 : 0;
		}
	};

	public static final HvlInputFilter FILTER_PAUSE = new HvlInputFilter(){
		public float getCurrentOutput(){
			return Keyboard.isKeyDown(Keyboard.KEY_ESCAPE) ? 1 : 0;
		}
	};

	public static final HvlInputFilter FILTER_PRIMARY = new HvlInputFilter(){
		public float getCurrentOutput(){
			return Mouse.isButtonDown(0) ? 1 : 0;
		}
	};

	public static final HvlInputFilter FILTER_SECONDARY = new HvlInputFilter(){
		public float getCurrentOutput(){
			return Mouse.isButtonDown(1) ? 1 : 0;
		}
	};

	public static final HvlInput UP = new HvlInput(FILTER_UP);
	public static final HvlInput DOWN = new HvlInput(FILTER_DOWN);
	public static final HvlInput LEFT = new HvlInput(FILTER_LEFT);
	public static final HvlInput RIGHT = new HvlInput(FILTER_RIGHT);
	public static final HvlInput PAUSE = new HvlInput(FILTER_PAUSE);
	public static final HvlInput PRIMARY = new HvlInput(FILTER_PRIMARY);
	public static final HvlInput SECONDARY = new HvlInput(FILTER_SECONDARY);
	public static final HvlInput INTERACTION = new HvlInput(FILTER_PRIMARY, FILTER_SECONDARY);
	public static final HvlInput VERTICAL = new HvlInput(FILTER_DOWN, FILTER_UP_NEGATIVE);
	public static final HvlInput HORIZONTAL = new HvlInput(FILTER_RIGHT, FILTER_LEFT_NEGATIVE);

}
