package Entities;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
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
    
    
    protected Image image;
    protected Animation animation;
    protected Shape shape;
    
    Entity(int X,int Y)
    {
        position = new Vector2f(X, Y);

    }
    
    Entity(int X,int Y, Image image)
    {
        position = new Vector2f(X, Y);
        
        this.image = image;

    }
    
    Entity(int X,int Y, Animation animation)
    {
        position = new Vector2f(X, Y);
        
        this.animation = animation;

    }
    
    Entity(int X,int Y ,Shape shape)
    {
        position = new Vector2f(X, Y);
        
        this.shape = shape;

    }
    
    Entity(int X,int Y, Image image ,Shape shape)
    {
        position = new Vector2f(X, Y);
        
        this.image = image;
        this.shape = shape;

    }
    
    Entity(int X,int Y, Animation animation ,Shape shape)
    {
        position = new Vector2f(X, Y);
        
        this.animation = animation;
        this.shape = shape;
        
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
        
        if (animation != null)
            animation.update(delta);
    }
    
    protected abstract void initEntity();
    
}
