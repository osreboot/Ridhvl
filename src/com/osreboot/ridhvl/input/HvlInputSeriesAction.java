package com.osreboot.ridhvl.input;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import com.osreboot.ridhvl.input.HvlInput.InputFilter;

public class HvlInputSeriesAction {

	//TODO boolean mode for using joystick

	public static final InputFilter FILTER_UP = new InputFilter(){
		public float getCurrentOutput(){
			return Keyboard.isKeyDown(Keyboard.KEY_W) || Keyboard.isKeyDown(Keyboard.KEY_UP) ? 1 : 0;
		}
	};
	
	public static final InputFilter FILTER_UP_NEGATIVE = new InputFilter(){
		public float getCurrentOutput(){
			return Keyboard.isKeyDown(Keyboard.KEY_W) || Keyboard.isKeyDown(Keyboard.KEY_UP) ? -1 : 0;
		}
	};
	
	public static final InputFilter FILTER_DOWN = new InputFilter(){
		public float getCurrentOutput(){
			return Keyboard.isKeyDown(Keyboard.KEY_S) || Keyboard.isKeyDown(Keyboard.KEY_DOWN) ? 1 : 0;
		}
	};

	public static final InputFilter FILTER_LEFT = new InputFilter(){
		public float getCurrentOutput(){
			return Keyboard.isKeyDown(Keyboard.KEY_A) || Keyboard.isKeyDown(Keyboard.KEY_LEFT) ? 1 : 0;
		}
	};
	
	public static final InputFilter FILTER_LEFT_NEGATIVE = new InputFilter(){
		public float getCurrentOutput(){
			return Keyboard.isKeyDown(Keyboard.KEY_A) || Keyboard.isKeyDown(Keyboard.KEY_LEFT) ? -1 : 0;
		}
	};

	public static final InputFilter FILTER_RIGHT = new InputFilter(){
		public float getCurrentOutput(){
			return Keyboard.isKeyDown(Keyboard.KEY_D) || Keyboard.isKeyDown(Keyboard.KEY_RIGHT) ? 1 : 0;
		}
	};

	public static final InputFilter FILTER_PAUSE = new InputFilter(){
		public float getCurrentOutput(){
			return Keyboard.isKeyDown(Keyboard.KEY_ESCAPE) || Keyboard.isKeyDown(Keyboard.KEY_P) ? 1 : 0;
		}
	};

	public static final InputFilter FILTER_PRIMARY = new InputFilter(){
		public float getCurrentOutput(){
			return Mouse.isButtonDown(0) ? 1 : 0;
		}
	};

	public static final InputFilter FILTER_SECONDARY = new InputFilter(){
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
