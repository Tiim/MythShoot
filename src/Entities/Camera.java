package Entities;

import org.newdawn.slick.geom.Vector2f;

/**
 * The camera can return a updated Offset on top of an entyty which shoult be folowed
 * @author Tiim
 */
public final class Camera
{
    /** The actual camera position */
    private Vector2f cameraPosition;
    /** The offset of the entities */
    private Vector2f entityOffset;
    /** The entity, wich shoult be in the middle of the screen */
    private Entity toFollow;
    /** The world in wich the Entities are */
    private final World world;

    /**
     * @param toFollow the entity wich should be in the middle
     * @param world  The World
     */
    public Camera(Entity toFollow ,World world)
    {
        this.toFollow = toFollow;
        update();
        this.world = world;
    }
    
    /** Updates the offsetvariables */
    public void update()
    {
        cameraPosition = this.toFollow.getPosition();
        entityOffset = cameraPosition.negate();
        entityOffset.x -= toFollow.getImage().getWidth() / 2;
        entityOffset.y -= toFollow.getImage().getHeight() / 2;
    }
    
    /** @return The X offset */
    public float getOffsetX()
    {
        return entityOffset.x + world.container.getWidth() / 2f /*- world.container.getScreenWidth() / 2*/;
    }
    
    /** @return The Y offset */
    public float getOffsetY()
    {
        return entityOffset.y + world.container.getHeight() / 2f /*- world.container.getScreenHeight() / 2*/;
    }
}
