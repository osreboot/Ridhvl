package com.osreboot.ridhvl.loader;

import java.io.FileInputStream;
import java.util.ArrayList;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

public class HvlTextureSeriesLoader extends HvlContentSeriesLoader<Texture>{

	public HvlTextureSeriesLoader(String defaultPathArg, int arrayLength){
		super(Texture.class, defaultPathArg, arrayLength);
	}
	
	public HvlTextureSeriesLoader(int arrayLength){
		super(Texture.class, "res/", arrayLength);
	}

	@Override
	public boolean loadResource(String nameArg, int lengthArg) {
		ArrayList<Texture> resources = new ArrayList<Texture>();//TODO
		Texture t;
		try{
			t = TextureLoader.getTexture("PNG", new FileInputStream(getPath() + nameArg + ".png"));
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		super.addResource(resources);
		return true;
	}

}
