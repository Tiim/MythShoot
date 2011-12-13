package Entities;

import Core.Log;
import Resources.PictureLoader;
import Resources.Props;
import it.marteEngine.entity.Entity;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

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
    
    private boolean onGround;
    private int jumpVel;
    
    public Player(float x, float y)
    {
        super(x, y);
        jumpVel = 0;
        setGraphic(PictureLoader.getImage("Soldier Red"));
        int hBX = Props.getPropInt("Player.Soldier.Hitbox.X");
        int hBY = Props.getPropInt("Player.Soldier.Hitbox.Y");
        int hBW = Props.getPropInt("Player.Soldier.Hitbox.Width");
        int hBH = Props.getPropInt("Player.Soldier.Hitbox.Height");
        setHitBox(hBX, hBY, hBW,hBH);
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
        
        float yOld = y;
        
        if(!onGround)
        {
            y += 0.3 * delta;
        }
        if (check("left"))
        {
            x -= 0.4 * delta;
        }
        if (check("right"))
        {
            x += 0.4 * delta;
        }
        if (check("up") && onGround)
        {
            jumpVel = 4;
        }
        else if (jumpVel > 0)
        {
            jumpVel -= 0.00001;
        }            
        if (check("down"))
        {
            y += 0.4 * delta;
        }
        
        y -= jumpVel * delta;
        onGround = false;
        if(collide("WALL", x, y) != null)
        {
            float dX = x - previousx;
            float dY = y - previousy;               
            x -= dX;
            if (collide("WALL",x,y) != null)
            {
                x += dX;           
                y -= dY;
                if (collide("WALL",x,y) != null)
                    x -= dX;
                else if (dY > 0)
                    onGround = true;
            }
        }
        
    } 
}
