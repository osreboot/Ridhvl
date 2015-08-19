package com.osreboot.ridhvl.menu;

import java.util.ArrayList;
import java.util.HashMap;

import org.newdawn.slick.openal.Audio;

import com.osreboot.ridhvl.HvlMath;

public class HvlSongPlayer {

	private static HashMap<HvlMenu, ArrayList<Audio>> songs = new HashMap<>();

	private static float fadeTime = 0, fadeGoal = 0, fadeLocation = 0, volume = 1f;//TODO integrate this
	private static Audio currentSong;

	public static void update(float delta){
		if(songs.size() > 0){//if this system is being used

			if(fadeTime > 0 && volume > 0){
				float previousLocation = fadeLocation;//TODO changing volume like this makes a strange clicking noise
				fadeLocation = HvlMath.stepTowards(fadeLocation, delta * (1/fadeTime) * volume, fadeGoal * volume);
				if(currentSong != null && previousLocation != fadeLocation){
					float position = currentSong.getPosition();
					currentSong.stop();
					currentSong.playAsSoundEffect(1, fadeLocation, false);
					currentSong.setPosition(position);
				}
			}

			if(songs.containsKey(HvlMenu.getCurrent())){//if the menu has playable songs
				if(!songs.get(HvlMenu.getCurrent()).contains(currentSong)) endCurrentSong();//if the current song is not in menu's list

				if(currentSong == null || !currentSong.isPlaying()){//if nothing is playing
					playRandomSong(songs.get(HvlMenu.getCurrent()));//play a tune
				}
			}else endCurrentSong();//end the song if the menu does not have any songs
		}
	}

	private static void endCurrentSong(){
		if(currentSong != null){
			currentSong.stop();
			currentSong = null;

			fadeGoal = 0;
			fadeLocation = 0;
		}
	}

	private static void playRandomSong(ArrayList<Audio> songArgs){
		ArrayList<Audio> newSongOptions = new ArrayList<>();
		for(Audio a : songArgs) newSongOptions.add(a);

		if(currentSong != null) if(newSongOptions.contains(currentSong) && newSongOptions.size() > 1) newSongOptions.remove(currentSong);

		currentSong = newSongOptions.get(HvlMath.getRand().nextInt(newSongOptions.size()));
		currentSong.playAsSoundEffect(1, fadeTime > 0 ? 0 : volume, false);

		fadeGoal = 1;
		fadeLocation = 0;
	}

	public static void fadeOut(){
		fadeGoal = 0;
		fadeLocation = 1;
	}

	public static void addSong(HvlMenu menu, Audio audio){
		if(!songs.containsKey(menu)) songs.put(menu, new ArrayList<Audio>());
		songs.get(menu).add(audio);
	}

	public static void addAllSongs(HvlMenu parent, HvlMenu child){
		if(songs.containsKey(parent)){
			songs.put(child, songs.get(parent));
		}else System.err.print("Parent menu doesn't have any songs!");//TODO else throw exception
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

	public static float getVolume(){
		return volume;
	}

	public static void setVolume(float volumeArg){
		volume = volumeArg;
	}

}
