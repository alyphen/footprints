package uk.co.rossbinden.footprints.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.Texture;
import uk.co.rossbinden.footprints.component.FootprintsComponent;
import uk.co.rossbinden.footprints.component.PositionComponent;
import uk.co.rossbinden.footprints.component.TextureComponent;
import uk.co.rossbinden.footprints.component.VelocityComponent;

import static uk.co.rossbinden.footprints.component.ComponentMappers.*;
import static uk.co.rossbinden.footprints.texture.Textures.*;

public class FootprintsSystem extends IteratingSystem {

    private final Family textured = Family.all(PositionComponent.class, TextureComponent.class).get();

    public FootprintsSystem() {
        super(Family.all(FootprintsComponent.class, PositionComponent.class, VelocityComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        boolean alreadyFootprints = false;
        for (Entity t : getEngine().getEntitiesFor(textured)) {
            if ((int) POSITION.get(t).x / 32 == (int) POSITION.get(entity).x / 32 && (int) POSITION.get(t).y / 32 == (int) POSITION.get(entity).y / 32) {
                alreadyFootprints = true;
                if (VELOCITY.get(entity).x > 0 || VELOCITY.get(entity).y > 0) {
                    if ((int) POSITION.get(t).x / 16 != (int) POSITION.get(entity).x / 16 || (int) POSITION.get(t).y / 16 != (int) POSITION.get(entity).y / 16) {
                        if (TEXTURE.get(t).texture == FOOTPRINTS_UP_1) {
                            TEXTURE.get(t).texture = FOOTPRINTS_UP_2;
                        } else if (TEXTURE.get(t).texture == FOOTPRINTS_DOWN_1) {
                            TEXTURE.get(t).texture = FOOTPRINTS_DOWN_2;
                        } else if (TEXTURE.get(t).texture == FOOTPRINTS_LEFT_1) {
                            TEXTURE.get(t).texture = FOOTPRINTS_LEFT_2;
                        } else if (TEXTURE.get(t).texture == FOOTPRINTS_RIGHT_1) {
                            TEXTURE.get(t).texture = FOOTPRINTS_RIGHT_2;
                        }
                    }
                } else {
                    if ((int) POSITION.get(t).x / 16 == (int) POSITION.get(entity).x / 16 && (int) POSITION.get(t).y / 16 == (int) POSITION.get(entity).y / 16) {
                        if (TEXTURE.get(t).texture == FOOTPRINTS_UP_1) {
                            TEXTURE.get(t).texture = FOOTPRINTS_UP_2;
                        } else if (TEXTURE.get(t).texture == FOOTPRINTS_DOWN_1) {
                            TEXTURE.get(t).texture = FOOTPRINTS_DOWN_2;
                        } else if (TEXTURE.get(t).texture == FOOTPRINTS_LEFT_1) {
                            TEXTURE.get(t).texture = FOOTPRINTS_LEFT_2;
                        } else if (TEXTURE.get(t).texture == FOOTPRINTS_RIGHT_1) {
                            TEXTURE.get(t).texture = FOOTPRINTS_RIGHT_2;
                        }
                    }
                }
            }
        }
        if (!alreadyFootprints) {
            Entity footprints = new Entity();
            Texture texture = null;
            if (VELOCITY.get(entity).y > 0) {
                texture = FOOTPRINTS_UP_1;
            } else if (VELOCITY.get(entity).y < 0) {
                texture = FOOTPRINTS_DOWN_1;
            } else if (VELOCITY.get(entity).x > 0) {
                texture = FOOTPRINTS_RIGHT_1;
            } else if (VELOCITY.get(entity).x < 0) {
                texture = FOOTPRINTS_LEFT_1;
            }
            if (texture != null) {
                footprints.add(new TextureComponent(texture));
                footprints.add(new PositionComponent(((int) POSITION.get(entity).x / 32) * 32, ((int) POSITION.get(entity).y / 32) * 32));
                getEngine().addEntity(footprints);
            }
        }
    }
}
