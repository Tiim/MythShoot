package Entities;

import Resources.Props;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Abstract base class of all entities
 * @author Tim
 */
public abstract class Entity
{
    /** Intern variable for some debug things (Like drawing the colision shape) */
    private final boolean debug = Props.getPropBool("Debug");
    /** The Position of the entity */
    private Vector2f position;
    /** The actual Speed of the entity */
    protected Vector2f speed;
    /** The actual acceleration of the entity */
    protected Vector2f acceleration;
    /** Shape placing */
    private Vector2f hitboxOffset;
    /** The world that contains this entity */
    protected World world;
    /** The Image of that entity */
    private Image image;
    /** The Animation of the entity */
    private Animation animation;
    /** The shape of the entity */
    private Shape shape;
    /** 
     * Each Type is a group of one or more entities,
     * that makes collisiondetecting between as example between Walls and Bullets easy 
     */
    private List<String> types;
    
    

    /** 
     * Konstructor
     * @param X The X position of the entity
     * @param Y The Y position of the entity
     * @param world The world
     * @throws SlickException
     */
    public Entity(float X, float Y, World world) throws SlickException
    {
        /** Initialise the entity */
        initEntity();
        /** Initialise the position */
        position = new Vector2f(X, Y);
        
        /** Throw exeption when world is null */
        if (isNull(world))
            throw new SlickException("Nullpointer");

        this.world = world;
    }

    public Entity(float X, float Y, Image image, World world) throws SlickException
    {
        initEntity();
        position = new Vector2f(X, Y);

        if (isNull(world, image))
            throw new SlickException("Nullpointer");

        this.image = image;
        this.world = world;
    }

    public Entity(float X, float Y, Animation animation, World world) throws SlickException
    {
        initEntity();
        position = new Vector2f(X, Y);

        if (isNull(world, animation))
            throw new SlickException("Nullpointer");

        this.animation = animation;
        this.world = world;
    }

    public Entity(float X, float Y, Shape shape, World world) throws SlickException
    {
        initEntity();
        position = new Vector2f(X, Y);

        if (isNull(world, shape))
            throw new SlickException("Nullpointer");

        this.shape = shape;
        this.world = world;
    }

    public Entity(float X, float Y, Image image, Shape shape, World world) throws SlickException
    {
        initEntity();
        position = new Vector2f(X, Y);

        if (isNull(world, shape, image))
            throw new SlickException("Nullpointer");

        this.image = image;
        this.shape = shape;
        this.world = world;
    }

    public Entity(float X, float Y, Animation animation, Shape shape, World world) throws SlickException
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

    public Vector2f getPosition()
    {
        return position;
    }

    public void setPosition(Vector2f position)
    {
        this.position = position;
        refreshPosition();
    }
    
    public void setPositionX(float position)
    {
        this.position.x = position;
        refreshPosition();
    }
    
    public void setPositionY(float position)
    {
        this.position.y = position;
        refreshPosition();
    }

    public void render(Graphics g, GameContainer gc)
    {
        float xOffset = (world.camera != null && useCamera())? world.camera.getOffsetX(): 0; 
        float yOffset = (world.camera != null && useCamera())? world.camera.getOffsetY(): 0; 
        
        if (image != null)
            image.draw(position.x + xOffset, position.y + yOffset);
        else if (animation != null)
            animation.draw(position.x + xOffset, position.y + yOffset);
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta)
    {

        refreshPosition();

        if (animation != null)
            animation.update(delta);
    }
    
    public void refreshPosition()
    {
        float xOffset = (world.camera != null && useCamera())? world.camera.getOffsetX(): 0 /*+ image.getWidth() / 2*/; 
        float yOffset = (world.camera != null && useCamera())? world.camera.getOffsetY(): 0 /*+ image.getHeight() / 2*/; 
        if (shape != null && hitboxOffset != null)
        {
            shape.setX(position.x + hitboxOffset.x + xOffset);
            shape.setY(position.y + hitboxOffset.y + yOffset);
        }
    }
    private void initEntity() throws SlickException
    {
        types = new ArrayList<String>();
        types.add("ALL");
        acceleration = new Vector2f();
        speed = new Vector2f();

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

    public final Input getInput()
    {
        return world.getInput();
    }

    public void destroy()
    {
        world = null;
        world.getEntities().remove(this);
    }
    
    protected abstract boolean useCamera();
}
