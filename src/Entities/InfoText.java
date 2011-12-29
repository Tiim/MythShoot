package Entities;

import Resources.Props;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

    
/**
 *
 * @author Tiim
 * @since 11.12.2011
 */
public class InfoText extends Entity
{
    public InfoText(float x, float y, World world) throws SlickException
    {
        super(x,y, world);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta)
    {
        
    }

    @Override
    public void render(Graphics g, GameContainer container)
    {
        long mem = (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) /  1000000 ;
        
        String s = "FPS: " + container.getFPS()
                + "\nEntities: " + world.getNrOfEntities("ENTITY")
                + "\nMemory usage: " + mem + "MB";                     
        g.drawString(s,Props.getPropFloat("InfoText.X"),Props.getPropFloat("InfoText.Y"));
    } 
    
    
    @Override
    protected boolean useCamera()
    {
        return false;
    }
}
