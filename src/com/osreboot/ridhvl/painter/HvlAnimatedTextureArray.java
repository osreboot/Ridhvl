package com.osreboot.ridhvl.painter;

import java.util.ArrayList;
import java.util.Arrays;

import org.newdawn.slick.opengl.Texture;

public class HvlAnimatedTextureArray extends HvlAnimatedTexture{

	private ArrayList<Texture> textures;
	
	public HvlAnimatedTextureArray(ArrayList<Texture> textureArgs, float frameDelayArg){
		super(frameDelayArg);
		textures = textureArgs;
	}
	
	public HvlAnimatedTextureArray(ArrayList<Texture> textureArgs, float frameDelayArg, boolean runningArg){
		super(frameDelayArg, runningArg);
		textures = textureArgs;
	}
	
	public HvlAnimatedTextureArray(Texture[] textureArgs, float frameDelayArg){
		super(frameDelayArg);
		textures = new ArrayList<Texture>(Arrays.asList(textureArgs));
	}
	
	public HvlAnimatedTextureArray(Texture[] textureArgs, float frameDelayArg, boolean runningArg){
		super(frameDelayArg, runningArg);
		textures = new ArrayList<Texture>(Arrays.asList(textureArgs));
	}
	
	@Override
	public Texture getCurrentTexture(){
		return textures.get((int)(getTotalTime()/getFrameDelay())%textures.size());
	}

	@Override
	public int getAnimationLength(){
		return textures.size();
	}
	
}
