package com.osreboot.ridhvl.loader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

public class HvlTextureLoader extends HvlContentLoader<Texture>{

	public HvlTextureLoader(String defaultPathArg){
		super(defaultPathArg);
	}
	
	public HvlTextureLoader(){
		super("res/");
	}

	@Override
	public Texture loadResourceMeta(String nameArg) throws FileNotFoundException, IOException{
		return TextureLoader.getTexture("PNG", new FileInputStream(getPath() + nameArg + ".png"));
	}

}
