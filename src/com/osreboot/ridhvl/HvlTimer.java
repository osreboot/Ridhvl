package com.osreboot.ridhvl;

import org.lwjgl.Sys;


public abstract class HvlTimer {

	private long delta, time, last;
	private boolean running = true;
	
	public HvlTimer(){}

	public final void start(){
		while(running){
			time = (Sys.getTime()*1000)/Sys.getTimerResolution();
			delta = (time - last);//TODO if user is not dragging window?
			last = time;
			if(delta > 0) update((float)delta / 1000);
		}
	}
	
	public abstract void update(float delta);

	public long getDelta(){
		return delta;
	}

	public boolean isRunning(){
		return running;
	}

	public void setRunning(boolean runningArg){
		running = runningArg;
	}

}
