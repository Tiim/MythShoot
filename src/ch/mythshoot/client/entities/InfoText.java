package ch.mythshoot.client.entities;

import ch.mythshoot.client.resources.Props;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

    
/**
 * An Infotext who displays the framerate, the entitycount and the memory usage
 * @author Tiim
 * @since 11.12.2011
 */
public class InfoText extends Entity
{
    String s = "";
    long mem;
    public InfoText(float x, float y, World world) throws SlickException
    {
        super(x,y, world);
    }

    /**
     * Updates the text
     * @param gc
     * @param sbg
     * @param delta 
     */
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta)
    {
        mem = (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) /  1000000 ;
        s = "FPS: " + gc.getFPS()
                + "\nEntities: " + world.getNrOfEntities("ENTITY")
                + "\nMemory usage: " + mem + "MB";        
    }
    
    /**
     * Renders the Text
     * @param g
     * @param container 
     */
    @Override
    public void render(Graphics g, GameContainer container)
    {               
        g.drawString(s,Props.getPropFloat("InfoText.X"),Props.getPropFloat("InfoText.Y"));
    } 
    
    /** It will move with the Plyer */
    @Override
    protected boolean useCamera()
    {
        return false;
    }
}
