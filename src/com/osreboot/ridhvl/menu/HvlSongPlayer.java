package com.osreboot.ridhvl.menu;

import java.util.HashMap;

import org.newdawn.slick.openal.Audio;

public class HvlSongPlayer {

	private static HashMap<Audio, HvlMenu> songs = new HashMap<Audio, HvlMenu>();
	
	public static void update(float delta){
		
	}
	
	public static void addSong(Audio audio, HvlMenu menu){
		songs.put(audio, menu);
	}
	
}
