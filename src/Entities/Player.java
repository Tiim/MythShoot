package Entities;

import Resources.PictureLoader;
import it.marteEngine.entity.Entity;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/**
 *
 * @author Tiim
 * @since 11.12.2011
 */
public class Player extends Entity
{
    private static final String ENTITY = "ENTITY";
    public static final String TYPE = "CHARACTER";
    private static final String NAME = "PLAYER";
    
    public Player(float x, float y)
    {
        super(x, y);
        setGraphic(PictureLoader.getImage("Demoman Red"));
        setHitBox(24, 22, 36 -24, 64 -22);
        //@todo read hitbox from a file for each character (? settings ?)
        addType(TYPE,ENTITY,NAME);
        name = NAME;
        
        define("left",Input.KEY_LEFT,Input.KEY_A);
        define("right",Input.KEY_RIGHT,Input.KEY_D);
        define("up",Input.KEY_UP,Input.KEY_W);
        define("down",Input.KEY_DOWN,Input.KEY_S);
    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException
    {
        super.update(container, delta);
        
        if (check("left"))
        {
            x -= 0.4 * delta;
        }
        if (check("right"))
        {
            x += 0.4 * delta;
        }
        if (check("up"))
        {
            y -= 0.4 * delta;
        }
        if (check("down"))
        {
            y += 0.4 * delta;
        }
        if(collide("WALL", x, y) != null)
        {
            float dX = x - previousx;
            float dY = y - previousy;               
            x -= dX;
            y -= dY;
            
            //@fixme move up or down while running against a wall
        }
    } 
}
