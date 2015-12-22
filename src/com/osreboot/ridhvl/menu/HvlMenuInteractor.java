package com.osreboot.ridhvl.menu;

import com.osreboot.ridhvl.action.HvlAction1;
import com.osreboot.ridhvl.input.HvlInput;

public class HvlMenuInteractor {

	private static HvlComponent selected;
	
	private static HvlInput up, down;
	
	public static void setFilters(HvlInput.InputFilter upArg, HvlInput.InputFilter downArg){
		up = new HvlInput(upArg);
		up.setPressedAction(new HvlAction1<HvlInput>(){
			@Override
			public void run(HvlInput i){
				
			}
		});
		down = new HvlInput(downArg);
		down.setPressedAction(new HvlAction1<HvlInput>(){
			@Override
			public void run(HvlInput i){
				
			}
		});
	}

	public static HvlComponent getSelected(){
		return selected;
	}
	
}
