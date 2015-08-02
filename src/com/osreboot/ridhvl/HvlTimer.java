package com.osreboot.ridhvl;

import org.lwjgl.Sys;

/**
 * This manages the timing of a loop. Once the loop is started, the only escape is to set running to false.
 */
public abstract class HvlTimer {

	private float dilation = 1f, total = 0;
	
	private long delta, time, last;
	private boolean running = true;
	
	/**
	 * Empty constructor.
	 */
	public HvlTimer(){}
	
	/**
	 * Starts the loop running. To end the loop, set running to false inside the update method.
	 */
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
	
	/**
	 * Called every update.
	 * 
	 * @param delta The time (in seconds) since the last update.
	 */
	public abstract void update(float delta);

	/**
	 * Gets whether the loop is running.
	 * 
	 * @return Whether the loop is running.
	 */
	public boolean isRunning(){
		return running;
	}

	/**
	 * Sets whether the loop is running.
	 * 
	 * @param runningArg The new value for if the loop is running.
	 */
	public void setRunning(boolean runningArg){
		running = runningArg;
	}
	
	/**
	 * Returns the delta multiplier.
	 * 
	 * @return The current delta multiplier.
	 */
	public float getDilation(){
		return dilation;
	}
	
	/**
	 * Sets the delta multiplier.
	 * 
	 * @param dilationArg New delta multiplier.
	 */
	public void setDilation(float dilationArg){
		dilation = dilationArg;
	}
	
	/**
	 * Gets the total time since the loop started (affected by dilation and starts/stops) (in milliseconds).
	 * 
	 * @return The total time since the loop started (in milliseconds).
	 */
	public float getTotalTime(){
		return total;
	}

}
