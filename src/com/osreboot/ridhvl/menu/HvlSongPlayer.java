package com.osreboot.ridhvl.menu;

import java.util.ArrayList;
import java.util.HashMap;

import org.newdawn.slick.openal.Audio;

import com.osreboot.ridhvl.HvlMath;

public class HvlSongPlayer {

	private static HashMap<HvlMenu, ArrayList<Audio>> songs = new HashMap<>();
	private static HashMap<HvlMenu, ArrayList<HvlMenu>> children = new HashMap<>();

	private static float fadeTime, currentFade = 0;//TODO integrate this
	private static Audio currentSong;

	public static void update(float delta){//TODO exception for nonexistent parent
		if(songs.size() > 0){
			if(currentSong != null && !currentSong.isPlaying()) currentSong = null;

			HvlMenu ultimate = null;
			if(songs.keySet().contains(HvlMenu.getCurrent())) ultimate = HvlMenu.getCurrent(); else{
				for(HvlMenu menu : children.keySet()) if(children.get(menu).contains(HvlMenu.getCurrent())) ultimate = menu;
			}

			if(ultimate != null){
				if(currentSong == null || !songs.get(ultimate).contains(currentSong)){
					ArrayList<Audio> newSongOptions = new ArrayList<>();
					for(Audio a : songs.get(ultimate)) newSongOptions.add(a);

					if(currentSong != null) currentSong.stop();
						
					if(currentSong != null) if(newSongOptions.contains(currentSong) && newSongOptions.size() > 1) newSongOptions.remove(currentSong);

					currentSong = newSongOptions.get(HvlMath.getRand().nextInt(newSongOptions.size()));
					currentSong.playAsSoundEffect(1, 1, false);
				}
			}else{
				if(currentSong != null){
					currentSong.stop();
					currentSong = null;
				}
			}
		}
	}

	public static void addSong(HvlMenu menu, Audio audio){
		if(!songs.containsKey(menu)) songs.put(menu, new ArrayList<Audio>());
		songs.get(menu).add(audio);
	}

	public static void addMenuChild(HvlMenu parent, HvlMenu child){
		if(!songs.containsKey(child)){
			if(!children.containsKey(parent)) children.put(parent, new ArrayList<HvlMenu>());
			children.get(parent).add(child);
		}else System.err.println("Child menu already has bound songs!");//TODO throw exception
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
