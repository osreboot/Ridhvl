package com.osreboot.rlhidhvl.action;

import java.util.ArrayList;
import java.util.Arrays;

public final class HvlEvent5<A, B, C, D, E> {

	private ArrayList<HvlAction5<A, B, C, D, E>> actions;

	public HvlEvent5(){
		actions = new ArrayList<>();
	}

	public HvlEvent5(HvlAction5<A, B, C, D, E>[] actionArgs){
		actions = new ArrayList<>(Arrays.asList(actionArgs));
	}

	public void trigger(A a, B b, C c, D d, E e){
		for(HvlAction5<A, B, C, D, E> action : actions) action.run(a, b, c, d, e);
	}

	public void addAction(HvlAction5<A, B, C, D, E> actionArg){
		actions.add(actionArg);
	}
	
}
