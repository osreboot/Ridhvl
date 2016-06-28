package com.osreboot.rlhidhvl.action;

import java.util.ArrayList;
import java.util.Arrays;

public final class HvlEvent2<A, B> {

	private ArrayList<HvlAction2<A, B>> actions;

	public HvlEvent2(){
		actions = new ArrayList<>();
	}

	public HvlEvent2(HvlAction2<A, B>[] actionArgs){
		actions = new ArrayList<>(Arrays.asList(actionArgs));
	}
	
	public void trigger(A a, B b){
		for(HvlAction2<A, B> action : actions) action.run(a, b);
	}

	public void addAction(HvlAction2<A, B> actionArg){
		actions.add(actionArg);
	}

}
