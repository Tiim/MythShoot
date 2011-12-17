package Entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author Tim
 */
public abstract class Entity
{

    protected Vector2f position;
    protected Vector2f speed;
    protected Vector2f acceleration;
    private Vector2f hitboxOffset;
    protected World world;
    private Image image;
    private Animation animation;
    private Shape shape;
    private List<String> types;

    Entity(int X, int Y, World world) throws SlickException
    {
        initEntity();
        position = new Vector2f(X, Y);

        if (isNull(world))
            throw new SlickException("Nullpointer");

        this.world = world;
    }

    Entity(int X, int Y, Image image, World world) throws SlickException
    {
        initEntity();
        position = new Vector2f(X, Y);

        if (isNull(world, image))
            throw new SlickException("Nullpointer");

        this.image = image;
        this.world = world;
    }

    Entity(int X, int Y, Animation animation, World world) throws SlickException
    {
        initEntity();
        position = new Vector2f(X, Y);

        if (isNull(world, animation))
            throw new SlickException("Nullpointer");

        this.animation = animation;
        this.world = world;
    }

    Entity(int X, int Y, Shape shape, World world) throws SlickException
    {
        initEntity();
        position = new Vector2f(X, Y);

        if (isNull(world, shape))
            throw new SlickException("Nullpointer");

        this.shape = shape;
        this.world = world;
    }

    Entity(int X, int Y, Image image, Shape shape, World world) throws SlickException
    {
        initEntity();
        position = new Vector2f(X, Y);

        if (isNull(world, shape, image))
            throw new SlickException("Nullpointer");

        this.image = image;
        this.shape = shape;
        this.world = world;
    }

    Entity(int X, int Y, Animation animation, Shape shape, World world) throws SlickException
    {
        initEntity();
        position = new Vector2f(X, Y);

        if (isNull(world, shape, animation))
            throw new SlickException("Nullpointer");

        this.animation = animation;
        this.shape = shape;
        this.world = world;

    }

    public Animation getAnimation()
    {
        return animation;
    }

    public void setAnimation(Animation animation)
    {
        this.animation = animation;
    }

    public Image getImage()
    {
        return image;
    }

    public void setImage(Image image)
    {
        this.image = image;
    }

    public World getWorld()
    {
        return world;
    }

    public void setWorld(World world)
    {
        this.world = world;
    }
    
    protected void addType(String... types)
    {
        this.types.addAll(Arrays.asList(types));
    }
    
    public Shape getShape()
    {
        return shape;
    }    
    
    public void render(Graphics g, GameContainer gc)
    {
        if (image != null)
            image.draw(position.x, position.y);
        else if (animation != null)
            animation.draw(position.x, position.y);
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta)
    {
        speed.x += acceleration.x;
        speed.y += acceleration.y;

        position.x += speed.x * delta;
        position.y += speed.y * delta;

        if (shape != null && hitboxOffset != null)
        {
            shape.setX(position.x + hitboxOffset.x);
            shape.setY(position.y + hitboxOffset.y);
        }
        
        if (animation != null)
            animation.update(delta);
    }

    private void initEntity() throws SlickException
    {
        types = new ArrayList<String>();
        types.add("ALL");
    }

    public boolean isType(String... types)
    {
        boolean isType = true;

        for (String type : types)
            isType = isType && this.types.contains(type);

        return isType;
    }

    private boolean isNull(Object... objects)
    {
        boolean isNull = false;

        for (Object o : objects)
            isNull = isNull || o == null;

        return isNull;
    }

    protected final void setHitbox(int xOffset, int yOffset, int width, int height)
    {
        shape = new Rectangle(xOffset + position.x, yOffset + position.y, width, height);
        hitboxOffset = new Vector2f(xOffset, yOffset);
    }

    public final boolean collides(Entity e)
    {
        return shape.intersects(e.getShape());
    }
}
