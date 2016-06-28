package com.osreboot.rlhidhvl.action;

import java.util.ArrayList;
import java.util.Arrays;

public final class HvlEvent4<A, B, C, D> {

	private ArrayList<HvlAction4<A, B, C, D>> actions;

	public HvlEvent4(){
		actions = new ArrayList<>();
	}

	public HvlEvent4(HvlAction4<A, B, C, D>[] actionArgs){
		actions = new ArrayList<>(Arrays.asList(actionArgs));
	}

	public void trigger(A a, B b, C c, D d){
		for(HvlAction4<A, B, C, D> action : actions) action.run(a, b, c, d);
	}

	public void addAction(HvlAction4<A, B, C, D> actionArg){
		actions.add(actionArg);
	}
	
}
