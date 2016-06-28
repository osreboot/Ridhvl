package com.osreboot.rlhidhvl.test;

import com.osreboot.rlhidhvl.template.HvlTemplateL;

public class TemplateLBasicTest extends HvlTemplateL{
	
	@Override
	public void initialize(){}

	@Override
	public void update(float delta){
		System.out.println(getTimer().getTotalTime()/1000f);
	}

}
