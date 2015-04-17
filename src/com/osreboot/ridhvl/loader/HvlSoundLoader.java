package com.osreboot.ridhvl.loader;

import java.io.BufferedInputStream;
import java.io.FileInputStream;

import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;

public class HvlSoundLoader extends HvlContentLoader<Audio>{

	public HvlSoundLoader(String defaultPathArg, int arrayLength){
		super(Audio.class, defaultPathArg, arrayLength);
	}
	
	public HvlSoundLoader(int arrayLength){
		super(Audio.class, "res/", arrayLength);
	}

	@Override
	public boolean loadResource(String nameArg){
		Audio t;
		try{
			t = AudioLoader.getAudio("WAV", new BufferedInputStream(new FileInputStream(getDefaultPath() + nameArg + ".wav")));
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		super.addResource(t);
		return true;
	}

}
