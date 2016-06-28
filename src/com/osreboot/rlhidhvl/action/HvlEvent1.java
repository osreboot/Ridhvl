package com.osreboot.rlhidhvl.action;

import java.util.ArrayList;
import java.util.Arrays;

public final class HvlEvent1<A> {

	private ArrayList<HvlAction1<A>> actions;

	public HvlEvent1(){
		actions = new ArrayList<>();
	}

	public HvlEvent1(HvlAction1<A>[] actionArgs){
		actions = new ArrayList<>(Arrays.asList(actionArgs));
	}
	
	public void trigger(A a){
		for(HvlAction1<A> action : actions) action.run(a);
	}

	public void addAction(HvlAction1<A> actionArg){
		actions.add(actionArg);
	}

}
