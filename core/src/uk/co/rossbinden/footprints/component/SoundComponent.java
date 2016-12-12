package uk.co.rossbinden.footprints.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.audio.Music;

public class SoundComponent implements Component {

    public Music music;

    public SoundComponent(Music music) {
        this.music = music;
    }

}
