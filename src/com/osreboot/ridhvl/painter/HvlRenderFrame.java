package com.osreboot.ridhvl.painter;

import java.nio.ByteBuffer;

import org.lwjgl.opengl.EXTFramebufferObject;
import org.lwjgl.opengl.GL11;

public class HvlRenderFrame {

	public static enum HvlRenderFrameProfile{
		DEFAULT
	}
	
	public static void setCurrentRenderFrame(int id){
		EXTFramebufferObject.glBindFramebufferEXT(EXTFramebufferObject.GL_FRAMEBUFFER_EXT, id);
	}
	
	private int frameID, textureID;
	
	public HvlRenderFrame(HvlRenderFrameProfile profile, int width, int height){
		frameID = EXTFramebufferObject.glGenFramebuffersEXT();
		textureID = GL11.glGenTextures();
		
		setCurrentRenderFrame(frameID);
		
		switch(profile){
		default:
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureID);
			GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
			GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA8, width, height, 0, GL11.GL_RGBA, GL11.GL_INT, (ByteBuffer)null);
			EXTFramebufferObject.glFramebufferTexture2DEXT(EXTFramebufferObject.GL_FRAMEBUFFER_EXT, EXTFramebufferObject.GL_COLOR_ATTACHMENT0_EXT, GL11.GL_TEXTURE_2D, textureID, 0);
			break;
		}
		
		setCurrentRenderFrame(0);
	}
	
	public int getID(){
		return frameID;
	}
	
	@Deprecated
	public int getTextureID(){
		return textureID;
	}
	
}
