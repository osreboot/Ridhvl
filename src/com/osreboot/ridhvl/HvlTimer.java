package com.osreboot.ridhvl;

import org.lwjgl.Sys;


public abstract class HvlTimer {

	private float dilation = 1f, total = 0;
	
	private long delta, time, last;
	private boolean running = true;
	
	public HvlTimer(){}

	public final void start(){
		while(running){
			time = (Sys.getTime()*1000)/Sys.getTimerResolution();
			delta = (time - last);//TODO if user is not dragging window?
			last = time;
			if(delta > 0 && delta < time){
				total += ((float)delta / 1000)*dilation;
				update(((float)delta / 1000)*dilation);
			}
		}
	}
	
	public abstract void update(float delta);

	public boolean isRunning(){
		return running;
	}

	public void setRunning(boolean runningArg){
		running = runningArg;
	}
	
	public float getDilation(){
		return dilation;
	}
	
	public void setDilation(float dilationArg){
		dilation = dilationArg;
	}
	
	public float getTotalTime(){
		return total;
	}

}
