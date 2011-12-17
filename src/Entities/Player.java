package Entities;

import Resources.PictureLoader;
import Resources.Props;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

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
    
    
    public Player(float x, float y,World world) throws SlickException
    {
        super(x, y,world);
        setImage(PictureLoader.getImage("Soldier Red"));
        int hBX = Props.getPropInt("Player.Soldier.Hitbox.X");
        int hBY = Props.getPropInt("Player.Soldier.Hitbox.Y");
        int hBW = Props.getPropInt("Player.Soldier.Hitbox.Width");
        int hBH = Props.getPropInt("Player.Soldier.Hitbox.Height");
        setHitbox(hBX, hBY, hBW,hBH);
        addType(TYPE,ENTITY,NAME);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta)
    {
        super.update(gc,sbg, delta);
        
        float yOld = position.y;
        
        if(!onGround)
        {
            //speed.y += 0.01 * delta;
        }
        if (getInput().isKeyDown(Input.KEY_LEFT))
        {
            speed.x -= 0.4 * delta;
        }
        if (getInput().isKeyDown(Input.KEY_RIGHT))
        {
            speed.x += 0.4 * delta;
        }
        if (getInput().isKeyDown(Input.KEY_UP) && onGround)
        {
            speed.y -= 3 * delta;
        }          
        if (getInput().isKeyDown(Input.KEY_UP))
        {
            speed.y += 0.4 * delta;
        }
        
        onGround = false;
        if(world.collidesWithEntities(this,"wall") != null)
        {           
            position.x -= speed.x;
            if (world.collidesWithEntities(this,"wall") != null)
            {
                position.x += speed.x;           
                position.y -= speed.y;
                if (world.collidesWithEntities(this,"wall") != null)
                    position.x -= speed.x;
                else if (speed.y > 0)
                    onGround = true;
            }
        }
        
    } 
}
