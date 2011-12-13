package Entities;

import Resources.Props;
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
        super(0,0);
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
        g.drawString(s,Props.getPropFloat("InfoText.X"),Props.getPropFloat("InfoText.Y"));
    }    
}
