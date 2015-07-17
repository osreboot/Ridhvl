package com.osreboot.ridhvl.loader;

import java.io.FileInputStream;
import java.util.ArrayList;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

public class HvlTextureSeriesLoader extends HvlContentSeriesLoader<Texture>{

	public HvlTextureSeriesLoader(String defaultPathArg){
		super(defaultPathArg);
	}

	public HvlTextureSeriesLoader(){
		super("res/");
	}

	@Override
	public boolean loadResource(String nameArg, int lengthArg) {
		ArrayList<Texture> resources = new ArrayList<Texture>();
		for(int i = 0; i < lengthArg; i++){
			try{
				resources.add(TextureLoader.getTexture("PNG", new FileInputStream(getPath() + String.format(nameArg, i) + ".png")));
			}catch(Exception e){
				e.printStackTrace();
				return false;
			}
		}
		super.addResource(resources);
		return true;
	}
	
	@Override
	public boolean loadResource(String nameArg, int lengthArg, int intervalArg) {
		ArrayList<Texture> resources = new ArrayList<Texture>();
		for(int i = 0; i < lengthArg; i += intervalArg){
			try{
				resources.add(TextureLoader.getTexture("PNG", new FileInputStream(getPath() + String.format(nameArg, i) + ".png")));
			}catch(Exception e){
				e.printStackTrace();
				return false;
			}
		}
		super.addResource(resources);
		return true;
	}

}
