package com.osreboot.ridhvl.painter;

import org.newdawn.slick.opengl.Texture;

public class HvlAnimatedTextureUV extends HvlAnimatedTexture{

	private float frameSize;
	private Texture texture;
	private int animationLength;
	
	public HvlAnimatedTextureUV(Texture textureArg, float frameSizeArg, int animationLengthArg, float frameDelayArg){
		super(frameDelayArg);
		texture = textureArg;
		frameSize = frameSizeArg;
		animationLength = animationLengthArg;
	}
	
	public HvlAnimatedTextureUV(Texture textureArg, float frameSizeArg, int animationLengthArg, float frameDelayArg, boolean runningArg){
		super(frameDelayArg, runningArg);
		texture = textureArg;
		frameSize = frameSizeArg;
		animationLength = animationLengthArg;
	}

	@Override
	public Texture getCurrentTexture(){
		return texture;
	}

	public float getCurrentUVX(){
		return (float)((int)(getTotalTime()/getFrameDelay())%animationLength)%(frameSize/(float)texture.getImageWidth());
	}

	public float getCurrentUVY(){
		return (float)((int)(getTotalTime()/getFrameDelay())%animationLength)/(frameSize/(float)texture.getImageWidth());
	}

	public float getFrameSize(){
		return frameSize;
	}

}
