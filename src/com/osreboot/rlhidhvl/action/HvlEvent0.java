package com.osreboot.rlhidhvl.action;

import java.util.ArrayList;
import java.util.Arrays;

public final class HvlEvent0 {

	private ArrayList<HvlAction0> actions;
	
	public HvlEvent0(){
		actions = new ArrayList<>();
	}
	
	public HvlEvent0(HvlAction0[] actionArgs){
		actions = new ArrayList<>(Arrays.asList(actionArgs));
	}

	public void trigger(){
		for(HvlAction0 action : actions) action.run();
	}

	public void addAction(HvlAction0 actionArg){
		actions.add(actionArg);
	}
	
}
