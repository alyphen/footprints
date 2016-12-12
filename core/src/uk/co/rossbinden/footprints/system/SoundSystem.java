package uk.co.rossbinden.footprints.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import uk.co.rossbinden.footprints.component.ControlsComponent;
import uk.co.rossbinden.footprints.component.PositionComponent;
import uk.co.rossbinden.footprints.component.SoundComponent;

import static uk.co.rossbinden.footprints.component.ComponentMappers.POSITION;
import static uk.co.rossbinden.footprints.component.ComponentMappers.SOUND;

public class SoundSystem extends IteratingSystem {

    private Family playerG = Family.all(ControlsComponent.class, PositionComponent.class).get();

    public SoundSystem() {
        super(Family.all(SoundComponent.class, PositionComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        Entity player = getEngine().getEntitiesFor(playerG).first();
        if (POSITION.get(entity).x < POSITION.get(player).x) {
            SOUND.get(entity).music.setPan(
                    Math.min(Math.max((POSITION.get(entity).x - POSITION.get(player).x) / 800F, -1F), 1F),
                    (1F - (Math.abs(POSITION.get(entity).x - POSITION.get(player).x) / 800F)) * 0.2F
            );
        }
    }
}
