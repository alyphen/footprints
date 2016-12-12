package uk.co.rossbinden.footprints.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.Input;

public class ControlsComponent implements Component {

    public int upKey = Input.Keys.UP;
    public int leftKey = Input.Keys.LEFT;
    public int rightKey = Input.Keys.RIGHT;
    public int downKey = Input.Keys.DOWN;

}
