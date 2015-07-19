package com.osreboot.ridhvl;

import org.lwjgl.input.Keyboard;

public abstract class HvlInput {
	
	public HvlInput(){}
	
	public abstract float getCurrentOutput();
	
	public static final HvlInput UP = new HvlInput(){
		@Override
		public float getCurrentOutput(){
			return Keyboard.isKeyDown(Keyboard.KEY_W) || Keyboard.isKeyDown(Keyboard.KEY_UP) ? 1 : 0;
		}
	};
	
	public static final HvlInput DOWN = new HvlInput(){
		@Override
		public float getCurrentOutput(){
			return Keyboard.isKeyDown(Keyboard.KEY_S) || Keyboard.isKeyDown(Keyboard.KEY_DOWN) ? 1 : 0;
		}
	};
	
	public static final HvlInput LEFT = new HvlInput(){
		@Override
		public float getCurrentOutput(){
			return Keyboard.isKeyDown(Keyboard.KEY_A) || Keyboard.isKeyDown(Keyboard.KEY_LEFT) ? 1 : 0;
		}
	};
	
	public static final HvlInput RIGHT = new HvlInput(){
		@Override
		public float getCurrentOutput(){
			return Keyboard.isKeyDown(Keyboard.KEY_D) || Keyboard.isKeyDown(Keyboard.KEY_RIGHT) ? 1 : 0;
		}
	};
	
}
