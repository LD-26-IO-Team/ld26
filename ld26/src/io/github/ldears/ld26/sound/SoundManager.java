package io.github.ldears.ld26.sound;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.ArrayMap;

/**
 * @author dector
 */
public class SoundManager {

	public static final String SOUND_DIR = "data/sounds/";
	public static final String MUSIC_FILE = "Eglyn_-_Hard_rock_80_s.mp3";

	public static final SoundManager instance = new SoundManager();

	private boolean muted;

	private Music music;
	private ArrayMap<String, Sound> sounds;

	private int musicVolume;

	private SoundManager() {
		musicVolume = 50;

		music = Gdx.audio.newMusic(Gdx.files.internal(SOUND_DIR + MUSIC_FILE));
		setMusicVolume(musicVolume);

		sounds = new ArrayMap<String, Sound>();
		for (Sounds sound : Sounds.values()) {
			sounds.put(sound.file, Gdx.audio.newSound(Gdx.files.internal(SOUND_DIR + sound.file)));
		}
	}

	public void setMuted(boolean muted) {
		if (muted) {
			music.pause();
		} else {
			music.play();
		}

		this.muted = muted;
	}

	public void play(Sounds sound) {
		if (muted) return;

		sounds.get(sound.file).play();
	}

	public void decMusicVolume() {
		if (musicVolume - 10 >= 0) {
			setMusicVolume(musicVolume - 10);
		}
	}

	public void incMusicVolume() {
		if (musicVolume + 10 <= 100) {
			setMusicVolume(musicVolume + 10);
		}
	}

	public boolean isMuted() {
		return muted;
	}

	public int getMusicVolume() {
		return musicVolume;
	}

	private void setMusicVolume(int volPercent) {
		musicVolume = volPercent;
		music.setVolume(1f * volPercent / 100);

		System.out.println("Music volume: " + volPercent + "%");
	}

	public void toggleMuted() {
		setMuted(! isMuted());
	}
}
