package com.osreboot.ridhvl.test;

import com.osreboot.ridhvl.template.HvlTemplateL;

public class TemplateLBasicTest extends HvlTemplateL{
	
	@Override
	public void initialize(){}

	@Override
	public void update(float delta){
		System.out.println(getTimer().getTotalTime()/1000f);
	}

}
