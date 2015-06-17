package com.osreboot.ridhvl.painter;

import org.lwjgl.opengl.EXTFramebufferObject;

public class HvlRenderFrame {

	public static enum HvlRenderFrameProfile{
		DEFAULT
	}
	
	public static void setCurrentRenderFrame(int id){
		EXTFramebufferObject.glBindFramebufferEXT(EXTFramebufferObject.GL_FRAMEBUFFER_EXT, id);
	}
	
	public HvlRenderFrame(){
		//TODO
	}
	
}
