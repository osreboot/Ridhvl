package com.osreboot.ridhvl.loader;

import java.io.BufferedInputStream;
import java.io.FileInputStream;

import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;

public class HvlSoundLoader extends HvlContentLoader<Audio>{

	public enum HvlSoundType{
		WAV, MP3
	}

	public HvlSoundLoader(String defaultPathArg){
		super(defaultPathArg);
	}

	public HvlSoundLoader(){
		super("res/");
	}

	@Override
	public boolean loadResource(String nameArg){
		Audio t;
		try{
			t = AudioLoader.getAudio("WAV", new BufferedInputStream(new FileInputStream(getPath() + nameArg + ".wav")));
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		super.addResource(t);
		return true;
	}

	public boolean loadResource(String nameArg, HvlSoundType typeArg){
		Audio t;
		try{
			switch(typeArg){
			case MP3: t = AudioLoader.getAudio("MP3", new BufferedInputStream(new FileInputStream(getPath() + nameArg + ".mp3"))); break; //TODO test this
			default: t = AudioLoader.getAudio("WAV", new BufferedInputStream(new FileInputStream(getPath() + nameArg + ".wav"))); break;
			}
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		super.addResource(t);
		return true;
	}

}
