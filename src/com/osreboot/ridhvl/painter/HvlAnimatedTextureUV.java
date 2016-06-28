package com.osreboot.ridhvl.painter;

import org.newdawn.slick.opengl.Texture;

public class HvlAnimatedTextureUV extends HvlAnimatedTexture{

	private Texture texture;
	private int animationLength, frameSize;
	
	public HvlAnimatedTextureUV(Texture textureArg, int frameSizeArg, int animationLengthArg, float frameDelayArg){
		super(frameDelayArg);
		texture = textureArg;
		frameSize = frameSizeArg;
		animationLength = animationLengthArg;
	}
	
	public HvlAnimatedTextureUV(Texture textureArg, int frameSizeArg, int animationLengthArg, float frameDelayArg, boolean runningArg){
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
		int frameIndex = getCurrentFrameIndex();
		return ((float)frameIndex%((float)texture.getImageWidth()/(float)frameSize))*((float)frameSize/(float)texture.getImageWidth()) * texture.getWidth();
	}

	public float getCurrentUVY(){
		int frameIndex = getCurrentFrameIndex();
		return ((int)frameIndex/(int)((float)texture.getImageWidth()/(float)frameSize))*((float)frameSize/(float)texture.getImageHeight()) * texture.getHeight();
	}
	
	public float getFrameWidth(){
		return (float)frameSize/(float)texture.getImageWidth();
	}
	
	public float getFrameHeight(){
		return (float)frameSize/(float)texture.getImageHeight();
	}

	@Override
	public int getAnimationLength(){
		return animationLength;
	}

}
