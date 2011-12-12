package Entities;

import it.marteEngine.entity.Entity;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

/**
 *
 * @author Tiim
 * @since 11.12.2011
 */
public class InfoText extends Entity
{
    public InfoText()
    {
        super(10,10);
    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException
    {

    }
    
    @Override
    public void render(GameContainer container, Graphics g) throws SlickException
    {
        String s = "FPS: " + container.getFPS()
                + "\nEntities: " + world.getNrOfEntities("ENTITY");                     
        g.drawString(s, 10, 10);
    }    
}
