package com.osreboot.ridhvl.menu;

import java.util.ArrayList;
import java.util.HashMap;

import org.newdawn.slick.openal.Audio;

import com.osreboot.ridhvl.HvlMath;

public class HvlSongPlayer {

	private static HashMap<Audio, HvlMenu> songs = new HashMap<Audio, HvlMenu>();

	private static float fadeTime, currentFade = 0;//TODO integrate this
	private static Audio currentSong;

	public static void update(float delta){
		if(songs.size() > 0){
			if(currentSong == null || songs.get(currentSong) != HvlMenu.getCurrent() || !currentSong.isPlaying()){
				if(songs.entrySet().contains(HvlMenu.getCurrent())){
					ArrayList<Audio> newSongOptions = new ArrayList<Audio>();
					for(Audio a : songs.keySet()) if(songs.get(a) == HvlMenu.getCurrent()) newSongOptions.add(a);
					if(currentSong != null) if(newSongOptions.contains(currentSong) && newSongOptions.size() > 1) newSongOptions.remove(currentSong);
					currentSong = newSongOptions.get(HvlMath.getRand().nextInt(newSongOptions.size()));
					currentSong.playAsSoundEffect(1, 1, false);
				}
			}

		}
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

	public static Audio getCurrentSong(){
		return currentSong;
	}

}
