package Entities;

import org.newdawn.slick.geom.Vector2f;

/**
 *
 * @author Tim
 */
public final class Camera
{
    private Vector2f cameraPosition;
    private Vector2f entityOffset;
    private Entity toFollow;
    private final World world;

    public Camera(Entity toFollow ,World world)
    {
        this.toFollow = toFollow;
        update();
        this.world = world;
    }
    
    public void update()
    {
        cameraPosition = this.toFollow.getPosition();
        entityOffset = cameraPosition.negate();
    }
    
    public float getOffsetX()
    {
        return entityOffset.x + world.container.getScreenWidth() / 5 /*- world.container.getScreenWidth() / 2*/;
    }
    
    public float getOffsetY()
    {
        return entityOffset.y + world.container.getScreenHeight() / 5 /*- world.container.getScreenHeight() / 2*/;
    }
}
