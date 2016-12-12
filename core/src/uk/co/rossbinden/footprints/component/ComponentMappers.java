package uk.co.rossbinden.footprints.component;

import com.badlogic.ashley.core.ComponentMapper;

public class ComponentMappers {

    private ComponentMappers() {}

    public static final ComponentMapper<PositionComponent> POSITION = ComponentMapper.getFor(PositionComponent.class);
    public static final ComponentMapper<VelocityComponent> VELOCITY = ComponentMapper.getFor(VelocityComponent.class);
    public static final ComponentMapper<ControlsComponent> CONTROLS = ComponentMapper.getFor(ControlsComponent.class);
    public static final ComponentMapper<TextureComponent> TEXTURE = ComponentMapper.getFor(TextureComponent.class);
    public static final ComponentMapper<SoundComponent> SOUND = ComponentMapper.getFor(SoundComponent.class);

}
