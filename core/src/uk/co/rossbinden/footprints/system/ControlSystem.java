package uk.co.rossbinden.footprints.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import uk.co.rossbinden.footprints.component.ControlsComponent;
import uk.co.rossbinden.footprints.component.PositionComponent;
import uk.co.rossbinden.footprints.component.VelocityComponent;

import static uk.co.rossbinden.footprints.component.ComponentMappers.CONTROLS;
import static uk.co.rossbinden.footprints.component.ComponentMappers.POSITION;
import static uk.co.rossbinden.footprints.component.ComponentMappers.VELOCITY;

public class ControlSystem extends IteratingSystem {
    public ControlSystem() {
        super(Family.all(ControlsComponent.class, PositionComponent.class, VelocityComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        if ((int) POSITION.get(entity).x % 32 == 0 && (int) POSITION.get(entity).y % 32 == 0) {
            VELOCITY.get(entity).x = 0;
            VELOCITY.get(entity).y = 0;
        }
        if (Gdx.input.isKeyPressed(CONTROLS.get(entity).upKey)) {
            if (Gdx.input.isKeyPressed(CONTROLS.get(entity).downKey)) {
                VELOCITY.get(entity).y = 0;
            } else if (VELOCITY.get(entity).x == 0) {
                VELOCITY.get(entity).y = 50;
            }
        } else if (Gdx.input.isKeyPressed(CONTROLS.get(entity).downKey)) {
            if (Gdx.input.isKeyPressed(CONTROLS.get(entity).upKey)) {
                VELOCITY.get(entity).y = 0;
            } else if (VELOCITY.get(entity).x == 0) {
                VELOCITY.get(entity).y = -50;
            }
        } else if (Gdx.input.isKeyPressed(CONTROLS.get(entity).leftKey)) {
            if (Gdx.input.isKeyPressed(CONTROLS.get(entity).rightKey)) {
                VELOCITY.get(entity).x = 0;
            } else if (VELOCITY.get(entity).y == 0) {
                VELOCITY.get(entity).x = -50;
            }
        } else if (Gdx.input.isKeyPressed(CONTROLS.get(entity).rightKey)) {
            if (Gdx.input.isKeyPressed(CONTROLS.get(entity).leftKey)) {
                VELOCITY.get(entity).x = 0;
            } else if (VELOCITY.get(entity).y == 0) {
                VELOCITY.get(entity).x = 50;
            }
        }
    }
}
