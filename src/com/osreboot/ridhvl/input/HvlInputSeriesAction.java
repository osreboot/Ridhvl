package com.osreboot.ridhvl.input;

import org.lwjgl.input.Keyboard;

import com.osreboot.ridhvl.input.HvlInput.HvlInputFilter;

public class HvlInputSeriesAction {

	public static final HvlInputFilter FILTER_UP = new HvlInputFilter(){
		public float getCurrentOutput(){
			return Keyboard.isKeyDown(Keyboard.KEY_W) || Keyboard.isKeyDown(Keyboard.KEY_UP) ? 1 : 0;
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
	
	public static final HvlInputFilter FILTER_RIGHT = new HvlInputFilter(){
		public float getCurrentOutput(){
			return Keyboard.isKeyDown(Keyboard.KEY_D) || Keyboard.isKeyDown(Keyboard.KEY_RIGHT) ? 1 : 0;
		}
	};
	
	public static final HvlInput UP = new HvlInput(FILTER_UP);
	public static final HvlInput DOWN = new HvlInput(FILTER_DOWN);
	public static final HvlInput LEFT = new HvlInput(FILTER_LEFT);
	public static final HvlInput RIGHT = new HvlInput(FILTER_RIGHT);
	
}
