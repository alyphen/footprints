package uk.co.rossbinden.footprints.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import uk.co.rossbinden.footprints.component.AIControlComponent;
import uk.co.rossbinden.footprints.component.PositionComponent;
import uk.co.rossbinden.footprints.component.VelocityComponent;

import java.util.Random;

import static uk.co.rossbinden.footprints.component.ComponentMappers.POSITION;
import static uk.co.rossbinden.footprints.component.ComponentMappers.VELOCITY;

public class AIControlSystem extends IteratingSystem {

    private Random random = new Random();

    public AIControlSystem() {
        super(Family.all(AIControlComponent.class, PositionComponent.class, VelocityComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        if ((int) POSITION.get(entity).x % 32 == 0 && (int) POSITION.get(entity).y % 32 == 0) {
            VELOCITY.get(entity).x = 0;
            VELOCITY.get(entity).y = 0;
        }
        boolean up = random.nextInt(4) == 0;
        boolean down = random.nextInt(4) == 0 && !up;
        boolean left = random.nextInt(4) == 0 && !up && !down;
        boolean right = random.nextInt(4) == 0 && !up && !down && !left;
        if (up) {
            if (down) {
                VELOCITY.get(entity).y = 0;
            } else if (VELOCITY.get(entity).x == 0) {
                VELOCITY.get(entity).y = 50;
            }
        } else if (down) {
            if (up) {
                VELOCITY.get(entity).y = 0;
            } else if (VELOCITY.get(entity).x == 0) {
                VELOCITY.get(entity).y = -50;
            }
        } else if (left) {
            if (right) {
                VELOCITY.get(entity).x = 0;
            } else if (VELOCITY.get(entity).y == 0) {
                VELOCITY.get(entity).x = -50;
            }
        } else if (right) {
            if (left) {
                VELOCITY.get(entity).x = 0;
            } else if (VELOCITY.get(entity).y == 0) {
                VELOCITY.get(entity).x = 50;
            }
        }
    }
}
