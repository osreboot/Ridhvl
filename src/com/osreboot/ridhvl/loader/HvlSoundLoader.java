package com.osreboot.ridhvl.loader;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;

public class HvlSoundLoader extends HvlContentLoader<Audio>{

	public HvlSoundLoader(String defaultPathArg){
		super(defaultPathArg);
	}

	public HvlSoundLoader(){
		super("res/");
	}

	@Override
	public Audio loadResourceMeta(String nameArg) throws FileNotFoundException, IOException{
		return AudioLoader.getAudio("WAV", new BufferedInputStream(new FileInputStream(getPath() + nameArg + ".wav")));
	}

}
