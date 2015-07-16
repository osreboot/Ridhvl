package com.osreboot.ridhvl.loader;

import java.io.FileInputStream;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

@Deprecated //TODO
public class HvlTextureSeriesLoader extends HvlContentSeriesLoader<Texture>{

	public HvlTextureSeriesLoader(String defaultPathArg, int arrayLength){
		super(Texture.class, defaultPathArg, arrayLength);
	}
	
	public HvlTextureSeriesLoader(int arrayLength){
		super(Texture.class, "res/", arrayLength);
	}

	@Override
	public boolean loadResource(String nameArg, int lengthArg) {
		Texture t;
		try{
			t = TextureLoader.getTexture("PNG", new FileInputStream(getDefaultPath() + nameArg + ".png"));
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		super.addResource(t);
		return true;
	}

}
