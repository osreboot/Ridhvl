package com.osreboot.ridhvl.painter.shader;

import org.lwjgl.opengl.ARBShaderObjects;

public class HvlShader {

	public static void setCurrentShader(int id){
		ARBShaderObjects.glUseProgramObjectARB(id);
	}
	
}
