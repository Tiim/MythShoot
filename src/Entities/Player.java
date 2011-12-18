package Entities;

import Core.Log;
import Resources.PictureLoader;
import Resources.Props;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
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

    public Player(float x, float y, World world) throws SlickException
    {
        super(x, y, world);
        setImage(PictureLoader.getImage("Soldier Red"));
        int hBX = Props.getPropInt("Player.Soldier.Hitbox.X");
        int hBY = Props.getPropInt("Player.Soldier.Hitbox.Y");
        int hBW = Props.getPropInt("Player.Soldier.Hitbox.Width");
        int hBH = Props.getPropInt("Player.Soldier.Hitbox.Height");
        setHitbox(hBX, hBY, hBW, hBH);
        addType(TYPE, ENTITY, NAME);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta)
    {
        super.update(gc, sbg, delta);


        if (!onGround)
        {
            //speed.y += 0.01 * delta;
        }
        if (getInput().isKeyDown(Input.KEY_LEFT))
            speed.x -= 0.01;
        if (getInput().isKeyDown(Input.KEY_RIGHT))
            speed.x += 0.01;
        if (getInput().isKeyDown(Input.KEY_UP) /*&& onGround*/)
            speed.y -= 0.01;
        if (getInput().isKeyDown(Input.KEY_DOWN))
            speed.y += 0.01;

        speed.x = Math.min(Math.max(speed.x, -0.5f), 0.5f);
        speed.y = Math.min(Math.max(speed.y, -0.5f), 0.5f);
        
        onGround = false;
        
        Vector2f prevPosition = getPosition().copy();
        updateMovement(delta);

        if (world.collidesWithEntities(this, "wall") != null)
        {
            float dX = getPosition().x - prevPosition.x;
            float dY = getPosition().y - prevPosition.y;               
            setPositionX(getPosition().x - dX);
            if (world.collidesWithEntities(this, "wall") != null)
            {
                setPositionX(getPosition().x + dX);
                setPositionY(getPosition().y - dY);
                speed.y = 0;
                if (world.collidesWithEntities(this, "wall") != null)
                     setPositionX(getPosition().x - dX);
                else if (dY > 0)
                    onGround = true;
            }
            else
            {
                speed.x = 0;
            }
            refreshPosition();
        }

    }

    private void updateMovement(int delta)
    {
        speed.x += acceleration.x;
        speed.y += acceleration.y;

        acceleration.x = Math.min(acceleration.x, 1);
        acceleration.y = Math.min(acceleration.y, 1);


        getPosition().x += speed.x * delta;
        getPosition().y += speed.y * delta;
        
        refreshPosition();
    }
}
