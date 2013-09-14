package com.pacman.view;

import java.util.HashMap;

import android.content.Context;
import android.media.MediaPlayer;

import com.pacman.R;

public class Sound {

    private MediaPlayer mediaPlayer;
    private Context context;
    private HashMap<Wave, Integer> sounds = new HashMap<Wave, Integer>();

    public Sound(Context context) {
        this.context = context;
        sounds.put(Wave.eatFruit, R.raw.eatfruit);
        sounds.put(Wave.menuSound, R.raw.pacman_song2);
        sounds.put(Wave.chomp, R.raw.pacman_coinin);
        sounds.put(Wave.beginning, R.raw.beginning);
        sounds.put(Wave.death, R.raw.death);
        sounds.put(Wave.eatSpirit, R.raw.eatspirit);
        sounds.put(Wave.sirenSound, R.raw.sirensound);
    }

    @SuppressWarnings("static-access")
    public void play(Wave wave, boolean looping, boolean isSound) {
        if (isSound) {
            mediaPlayer = mediaPlayer.create(context, sounds.get(wave));
            mediaPlayer.setLooping(looping);
            mediaPlayer.start();
        }
    }

    public void stop() {
        if (mediaPlayer != null) {
            mediaPlayer.setLooping(false);
            mediaPlayer.stop();
        }
    }

}
