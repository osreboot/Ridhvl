package com.osreboot.ridhvl.template;

import com.osreboot.ridhvl.action.HvlEvent1;
import com.osreboot.ridhvl.external.HvlEnvironmentRegistry;

public abstract class HvlTemplateL extends HvlTemplate{

	public static final HvlEvent1<HvlTemplateL> EVENT_EXIT = new HvlEvent1<>();
	
	public HvlTemplateL(){
		super();
		
		HvlEnvironmentRegistry.registerVariables();
		HvlChronologyRegistry.registerChronologies();
		HvlChronology.initialize();
		
		start();
	}

	@Override
	public void preUpdate(float delta){
		HvlChronology.preUpdate(delta);
	}

	@Override
	public void postUpdate(float delta){
		HvlChronology.postUpdate(delta);
	}
	
	public void exit(){
		getTimer().setRunning(false);
		System.exit(0);
	}

}
