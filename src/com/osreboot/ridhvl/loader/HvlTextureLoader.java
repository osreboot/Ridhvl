package com.osreboot.ridhvl.loader;

import java.io.FileInputStream;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

public class HvlTextureLoader extends HvlContentLoader<Texture>{

	public HvlTextureLoader(String defaultPathArg, int arrayLength){
		super(Texture.class, defaultPathArg, arrayLength);
	}
	
	public HvlTextureLoader(int arrayLength){
		super(Texture.class, "res/", arrayLength);
	}

	@Override
	public boolean loadResource(String nameArg){
		Texture t;
		try{
			t = TextureLoader.getTexture("PNG", new FileInputStream(super.getDefaultPath() + nameArg + ".png"));
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		super.addResource(t);
		return true;
	}

}
