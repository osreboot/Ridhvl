package com.osreboot.ridhvl.painter;

import java.util.ArrayList;

import org.newdawn.slick.opengl.Texture;

public abstract class HvlAnimatedTexture {

	private static ArrayList<HvlAnimatedTexture> animatedTextures = new ArrayList<HvlAnimatedTexture>();
	
	public static void updateTextures(float delta){
		for(HvlAnimatedTexture texture : animatedTextures) texture.update(delta);
	}
	
	private float frameDelay, speed, totalTime, stopTime;
	private boolean running, autoStop;
	
	public HvlAnimatedTexture(float frameDelayArg){
		frameDelay = frameDelayArg;
		running = true;
		totalTime = 0;
		stopTime = 0;
		speed = 1;
		animatedTextures.add(this);
		autoStop = false;
	}
	
	public HvlAnimatedTexture(float frameDelayArg, boolean runningArg){
		frameDelay = frameDelayArg;
		running = runningArg;
		totalTime = 0;
		stopTime = 0;
		speed = 1;
		animatedTextures.add(this);
		autoStop = false;
	}
	
	protected void update(float delta){
		if(running) totalTime += delta * speed;
		if(autoStop) stopTime += Math.abs(delta * speed);
		if(stopTime > (float)getAnimationLength()*frameDelay){
			running = false;
			stopTime = 0;
			totalTime = ((float)getAnimationLength() - 1)*frameDelay;
		}
	}

	public boolean isRunning(){
		return running;
	}

	public void setRunning(boolean runningArg){
		running = runningArg;
	}
	
	public abstract int getAnimationLength();
	
	public abstract Texture getCurrentTexture();
	
	public float getFrameDelay(){
		return frameDelay;
	}

	public float getTotalTime(){
		return totalTime;
	}

	public void reset(){
		totalTime = 0;
		stopTime = 0;
	}
	
	public void setCurrentFrame(int frame){
		totalTime = frame * frameDelay;
	}

	public boolean getAutoStop(){
		return autoStop;
	}

	public void setAutoStop(boolean autoStopArg){
		autoStop = autoStopArg;
		if(autoStop) stopTime = 0;
	}

	public float getSpeed(){
		return speed;
	}

	public void setSpeed(float speedArg){
		speed = speedArg;
	}
	
	public int getCurrentFrameIndex(){
		int raw = (int)(((float)getTotalTime()/(float)getFrameDelay())%(float)getAnimationLength());
		return raw < 0 ? getAnimationLength() + raw : raw;
	}
	
}
