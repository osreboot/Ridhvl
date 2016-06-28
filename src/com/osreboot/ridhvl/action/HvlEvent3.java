package com.osreboot.ridhvl.action;

import java.util.ArrayList;
import java.util.Arrays;

public final class HvlEvent3<A, B, C> {

	private ArrayList<HvlAction3<A, B, C>> actions;

	public HvlEvent3(){
		actions = new ArrayList<>();
	}

	public HvlEvent3(HvlAction3<A, B, C>[] actionArgs){
		actions = new ArrayList<>(Arrays.asList(actionArgs));
	}

	public void trigger(A a, B b, C c){
		for(HvlAction3<A, B, C> action : actions) action.run(a, b, c);
	}

	public void addAction(HvlAction3<A, B, C> actionArg){
		actions.add(actionArg);
	}
	
}
