package com.osreboot.ridhvl.menu;

import java.util.HashMap;

import org.newdawn.slick.openal.Audio;

public class HvlSongPlayer {

	private static HashMap<Audio, HvlMenu> songs = new HashMap<Audio, HvlMenu>();
	
	private static float fadeTime;
	
	public static void update(float delta){
		
	}
	
	public static void addSong(Audio audio, HvlMenu menu){
		songs.put(audio, menu);
	}

	public static float getFadeTime(){
		return fadeTime;
	}

	public static void setFadeTime(float fadeTimeArg){
		fadeTime = fadeTimeArg;
	}
	
}
