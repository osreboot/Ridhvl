package com.osreboot.ridhvl.template;

import com.osreboot.ridhvl.HvlTimer;

public abstract class HvlTemplate{

	private HvlTimer timer;

	public HvlTemplate(){}

	public final void start(){
		initialize();

		timer = new HvlTimer(){
			@Override
			public void update(long delta) {
				tick(delta);
			}
		};
		timer.start();
	}

	public HvlTimer getTimer(){
		return timer;
	}
	
	public abstract void initialize();

	public final void tick(long delta){
		preUpdate(delta);
		update(delta);
		postUpdate(delta);
	}

	public void preUpdate(long delta){}
	public abstract void update(long delta);
	public void postUpdate(long delta){}

}
