package com.osreboot.ridhvl;


public abstract class hvlUpdater {

	private float framerate, speed, last;
	private boolean running = true;
	
	public hvlUpdater(float framerateArg){
		framerate = framerateArg;
		while(running){
			speed = ((System.nanoTime() - last)/(10000000f/framerate));//TODO if user is not dragging window? //TODO this frame rate conversion is not accurate
			last = System.nanoTime();
			update(speed);
		}
	}
	
	public abstract void update(float speed);
	
	public float getSpeed(){
		return speed;
	}
	
	public boolean isRunning(){
		return running;
	}
	
	public void setRunning(boolean runningArg){
		running = runningArg;
	}
	
}
