package ch.mythshoot.client.entities;

import ch.mythshoot.client.util.recycler.VectorRecycler;
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
    public Camera(Entity toFollow, World world)
    {
        this.toFollow = toFollow;
        this.world = world;
        update();
    }

    /** Updates the offsetvariables */
    public void update()
    {
        VectorRecycler.getInstance().recycle(cameraPosition);
        VectorRecycler.getInstance().recycle(entityOffset);
        cameraPosition = VectorRecycler.getInstance().copy(this.toFollow.getPosition());
        entityOffset = VectorRecycler.getInstance().copy(cameraPosition);
        entityOffset.x = - entityOffset.x;
        entityOffset.y = - entityOffset.y;
        entityOffset.x -= toFollow.getImage().getWidth() / 2;
        entityOffset.y -= toFollow.getImage().getHeight() / 2;
    }

    /** @return The X offset */
    public float getOffsetX()
    {
        return entityOffset.x + world.container.getWidth() / 2f;
    }

    /** @return The Y offset */
    public float getOffsetY()
    {
        return entityOffset.y + world.container.getHeight() / 2f;
    }
}
