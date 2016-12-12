package uk.co.rossbinden.footprints.screen;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import uk.co.rossbinden.footprints.component.*;
import uk.co.rossbinden.footprints.system.*;

import java.util.Random;

import static uk.co.rossbinden.footprints.component.ComponentMappers.POSITION;
import static uk.co.rossbinden.footprints.component.ComponentMappers.TEXTURE;
import static uk.co.rossbinden.footprints.music.Tracks.*;
import static uk.co.rossbinden.footprints.texture.Textures.*;

public class MainScreen extends ScreenAdapter {

    private final Engine engine;
    private final SpriteBatch spriteBatch;
    private final Family textures = Family.all(TextureComponent.class, PositionComponent.class).get();
    private Random random = new Random();
    private float stateTime = 0F;

    public MainScreen() {
        engine = new Engine();
        engine.addSystem(new MovementSystem());
        engine.addSystem(new ControlSystem());
        engine.addSystem(new FootprintsSystem());
        engine.addSystem(new AIControlSystem());
        engine.addSystem(new SoundSystem());
        Entity player = new Entity();
        player.add(new ControlsComponent());
        player.add(new PositionComponent(64, 64));
        player.add(new VelocityComponent(0, 0));
        player.add(new FootprintsComponent());
        engine.addEntity(player);
        spriteBatch = new SpriteBatch();
        T1.setLooping(true);
        T1.setVolume(0.2F);
        T1.play();
        T2.setLooping(true);
        T2.setVolume(0.2F);
        T3.setLooping(true);
        T3.setVolume(0.2F);
        T4.setLooping(true);
        T4.setVolume(0.2F);
        T5.setLooping(true);
        T5.setVolume(0.2F);
    }

    @Override
    public void render(float delta) {
        stateTime += delta;
        engine.update(delta);
        Gdx.gl.glClearColor(1F, 1F, 1F, 1F);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        spriteBatch.begin();
        for (Entity entity : engine.getEntitiesFor(textures)) {
            spriteBatch.draw(TEXTURE.get(entity).texture, POSITION.get(entity).x, POSITION.get(entity).y);
        }
        spriteBatch.end();
        if (stateTime > 5 && !T2.isPlaying()) {
            Entity ai = new Entity();
            ai.add(new AIControlComponent());
            ai.add(new VelocityComponent(0, 0));
            ai.add(new PositionComponent(random.nextInt(25) * 32, random.nextInt(18) * 32));
            ai.add(new FootprintsComponent());
            ai.add(new SoundComponent(T2));
            engine.addEntity(ai);
            T2.play();
        } else if (stateTime > 10 && !T3.isPlaying()) {
            Entity ai = new Entity();
            ai.add(new AIControlComponent());
            ai.add(new VelocityComponent(0, 0));
            ai.add(new PositionComponent(random.nextInt(25) * 32, random.nextInt(18) * 32));
            ai.add(new FootprintsComponent());
            ai.add(new SoundComponent(T3));
            engine.addEntity(ai);
            T3.play();
        } else if (stateTime > 15 && !T4.isPlaying()) {
            Entity ai = new Entity();
            ai.add(new AIControlComponent());
            ai.add(new VelocityComponent(0, 0));
            ai.add(new PositionComponent(random.nextInt(25) * 32, random.nextInt(18) * 32));
            ai.add(new FootprintsComponent());
            ai.add(new SoundComponent(T4));
            engine.addEntity(ai);
            T4.play();
        } else if (stateTime > 20 && !T5.isPlaying()) {
            Entity ai = new Entity();
            ai.add(new AIControlComponent());
            ai.add(new VelocityComponent(0, 0));
            ai.add(new PositionComponent(random.nextInt(25) * 32, random.nextInt(18) * 32));
            ai.add(new FootprintsComponent());
            ai.add(new SoundComponent(T5));
            engine.addEntity(ai);
            T5.play();
        }
    }

    @Override
    public void dispose() {
        spriteBatch.dispose();
        FOOTPRINTS_UP_1.dispose();
        FOOTPRINTS_UP_2.dispose();
        FOOTPRINTS_DOWN_1.dispose();
        FOOTPRINTS_DOWN_2.dispose();
        FOOTPRINTS_LEFT_1.dispose();
        FOOTPRINTS_LEFT_2.dispose();
        FOOTPRINTS_RIGHT_1.dispose();
        FOOTPRINTS_RIGHT_2.dispose();
        T1.dispose();
        T2.dispose();
        T3.dispose();
        T4.dispose();
        T5.dispose();
    }

}
