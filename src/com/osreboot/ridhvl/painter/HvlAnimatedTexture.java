package com.osreboot.ridhvl.painter;

import java.util.ArrayList;
import java.util.Arrays;

import org.newdawn.slick.opengl.Texture;

public class HvlAnimatedTexture {

	private static ArrayList<HvlAnimatedTexture> animatedTextures = new ArrayList<HvlAnimatedTexture>();
	
	public static void updateTextures(float delta){
		for(HvlAnimatedTexture texture : animatedTextures) texture.update(delta);
	}
	
	private ArrayList<Texture> textures;
	private float frameDelay, totalTime;
	private boolean running;
	
	public HvlAnimatedTexture(ArrayList<Texture> textureArgs, float frameDelayArg){
		textures = textureArgs;
		frameDelay = frameDelayArg;
		running = true;
		totalTime = 0;
		animatedTextures.add(this);
	}
	
	public HvlAnimatedTexture(ArrayList<Texture> textureArgs, float frameDelayArg, boolean runningArg){
		textures = textureArgs;
		frameDelay = frameDelayArg;
		running = runningArg;
		totalTime = 0;
		animatedTextures.add(this);
	}
	
	public HvlAnimatedTexture(Texture[] textureArgs, float frameDelayArg){
		textures = new ArrayList<Texture>(Arrays.asList(textureArgs));
		frameDelay = frameDelayArg;
		running = true;
		totalTime = 0;
		animatedTextures.add(this);
	}
	
	public HvlAnimatedTexture(Texture[] textureArgs, float frameDelayArg, boolean runningArg){
		textures = new ArrayList<Texture>(Arrays.asList(textureArgs));
		frameDelay = frameDelayArg;
		running = runningArg;
		totalTime = 0;
		animatedTextures.add(this);
	}
	
	protected void update(float delta){
		if(running) totalTime += delta;
	}

	public boolean isRunning(){
		return running;
	}

	public void setRunning(boolean runningArg){
		running = runningArg;
	}
	
	public Texture getCurrentTexture(){
		return textures.get((int)(totalTime/frameDelay)%textures.size());
	}
	
}
