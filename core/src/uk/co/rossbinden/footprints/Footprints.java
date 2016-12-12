package uk.co.rossbinden.footprints;

import com.badlogic.gdx.Game;
import uk.co.rossbinden.footprints.screen.MainScreen;

public class Footprints extends Game {

    @Override
    public void create() {
        screen = new MainScreen();
    }

    @Override
    public void dispose() {
        screen.dispose();
    }

}
