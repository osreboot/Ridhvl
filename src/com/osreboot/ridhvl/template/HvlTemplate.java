package com.osreboot.ridhvl.template;

import com.osreboot.ridhvl.HvlTimer;

public abstract class HvlTemplate{

	public static HvlTemplate newestInstance;
	
	public static HvlTemplate getNewestInstance(){
		return newestInstance;
	}
	
	private HvlTimer timer;

	public HvlTemplate(){
		newestInstance = this;
	}

	final void start(){
		initialize();

		timer = new HvlTimer(){
			@Override
			public void update(float delta) {
				tick(delta);
			}
		};
		timer.start();
	}

	public HvlTimer getTimer(){
		return timer;
	}
	
	public abstract void initialize();

	public final void tick(float delta){
		preUpdate(delta);
		update(delta);
		postUpdate(delta);
	}

	public void preUpdate(float delta){}
	public abstract void update(float delta);
	public void postUpdate(float delta){}

}
