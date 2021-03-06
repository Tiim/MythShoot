package ch.mythshoot.client.entities;

import ch.mythshoot.client.resources.PictureLoader;
import ch.mythshoot.client.resources.Props;
import java.util.logging.Logger;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author Tiim
 */
public class Shot extends Entity
{
    private static final Logger LOGGER = Logger.getLogger(Shot.class.getName());
    
    Entity owner;
    float maxSpeed;
    
    
    public Shot(float X, float Y,float angle,int id, Entity owner) throws SlickException
    {
        super(X, Y, owner.world);
        maxSpeed = Props.getPropFloat("Shot." + id +".Speed");
        setImage(PictureLoader.getInstance().getImage("Shot " + id).copy());
        getImage().setRotation(angle);
        speed = new Vector2f((float)Math.cos(Math.toRadians(angle))* maxSpeed,(float)Math.sin(Math.toRadians(angle))* maxSpeed);
        addType("shot");
        setShape(new Circle(X, Y, 2));
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta)
    {
        super.update(gc, sbg, delta);
        
        setPositionX(getPosition().x + speed.x * delta);
        setPositionY(getPosition().y + speed.y * delta);
        
        if (world.collidesWithEntities(this,"wall") != null)
        {
            world.removeEntities(this);
        }
    }

    @Override
    public void render(Graphics g, GameContainer gc)
    {
        super.render(g, gc);
    }
    
    @Override
    protected boolean useCamera()
    {
        return true;
    }

}


