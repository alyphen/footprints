package uk.co.rossbinden.footprints.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import uk.co.rossbinden.footprints.component.PositionComponent;
import uk.co.rossbinden.footprints.component.VelocityComponent;

import static uk.co.rossbinden.footprints.component.ComponentMappers.POSITION;
import static uk.co.rossbinden.footprints.component.ComponentMappers.VELOCITY;

public class MovementSystem extends IteratingSystem {

    public MovementSystem() {
        super(Family.all(PositionComponent.class, VelocityComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        POSITION.get(entity).x += VELOCITY.get(entity).x * deltaTime;
        POSITION.get(entity).y += VELOCITY.get(entity).y * deltaTime;
    }
}
