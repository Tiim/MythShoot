package ch.mythshoot.client.entities;

import ch.mythshoot.client.resources.PictureLoader;
import ch.mythshoot.client.resources.Props;
import ch.mythshoot.client.util.recycler.VectorRecycler;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
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
    private static final Logger LOGGER = Logger.getLogger(Player.class.getName());
    
    private Image flipped, unflipped;
    private static final String ENTITY = "ENTITY";
    public static final String TYPE = "CHARACTER";
    private static final String NAME = "PLAYER";
    private boolean onGround;
    private boolean viewDirectionRight;
    private Weapon weapon;
    private boolean lastUpdateMouseDown = false;
    int id;
    boolean red;

    public Player(float x, float y, int id, World world) throws SlickException
    {
        super(x, y, world);
        viewDirectionRight = true;
        unflipped = PictureLoader.getInstance().getImage("Character " + id + " " + red);
        flipped = unflipped.getFlippedCopy(true, false);
        setImage(unflipped);
        int hBX = Props.getPropInt("Player." + id + ".Hitbox.X");
        int hBY = Props.getPropInt("Player." + id + ".Hitbox.Y");
        int hBW = Props.getPropInt("Player." + id + ".Hitbox.Width");
        int hBH = Props.getPropInt("Player." + id + ".Hitbox.Height");
        setHitbox(hBX, hBY, hBW, hBH);
        addType(TYPE, ENTITY, NAME);
        weapon = new Weapon(getPosition().x, getPosition().y, id, this);
        this.id = id;
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta)
    {
        super.update(gc, sbg, delta);
        weapon.update(gc, sbg, delta);
        
        handleInput(gc, sbg, delta);

        speed.x = Math.min(Math.max(speed.x, -0.5f), 0.5f);
        speed.y = Math.min(Math.max(speed.y, -0.5f), 0.5f);

        onGround = false;

        Vector2f prevPosition = VectorRecycler.getInstance().copy(getPosition());
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
                speed.x = 0;
            refreshPosition();
        }
        VectorRecycler.getInstance().recycle(prevPosition);
    }
    
    private void handleInput(GameContainer gc, StateBasedGame sbg, int delta)
    {
        if (!onGround)
            speed.y += 0.001 * delta;
        if (getInput().isKeyDown(Input.KEY_LEFT))
            speed.x -= 0.01;
        if (getInput().isKeyDown(Input.KEY_RIGHT))
            speed.x += 0.01;
        if (getInput().isKeyDown(Input.KEY_UP) && onGround)
            speed.y -= 0.6;
        if (getInput().isKeyDown(Input.KEY_DOWN))
            speed.y += 0.01;

        if (!getInput().isKeyDown(Input.KEY_RIGHT) && !getInput().isKeyDown(Input.KEY_LEFT))
            speed.x /= 1.7;

        if (getInput().getMouseX() < gc.getWidth() / 2 && viewDirectionRight)
        {
            setImage(flipped);
            viewDirectionRight = false;
        }
        else if (getInput().getMouseX() > gc.getWidth() / 2 && !viewDirectionRight)
        {
            setImage(unflipped);
            viewDirectionRight = true;
        }
        
        if (getInput().isMouseButtonDown(0) && !lastUpdateMouseDown)
        {
            try
            {
                world.addEntities(new Shot(getPosition().x + getImage().getWidth() / 2, getPosition().y + getImage().getHeight() / 2,weapon.getRotation(), id, this));
            }
            catch (SlickException ex)
            {
                LOGGER.log(Level.WARNING,"Unable to make a bullet", ex);
            }
        }
        lastUpdateMouseDown = getInput().isMouseButtonDown(0);
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

    @Override
    protected boolean useCamera()
    {
        return true;
    }

    @Override
    public void render(Graphics g, GameContainer gc)
    {
        super.render(g, gc);
        weapon.render(g, gc);
    }
}
