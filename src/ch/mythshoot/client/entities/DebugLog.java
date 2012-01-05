package ch.mythshoot.client.entities;

import ch.mythshoot.client.crash.LogDisplayManager;
import java.util.logging.Logger;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author Tim
 */
public class DebugLog extends Entity
{
    
    private static final int MAX_LINES = 10;    
    
    private static final Logger LOGGER = Logger.getLogger(Entity.class.getName());
    
    private static final int DISP_TIME = 10000;
    
    private String[] lines = new String[MAX_LINES];
    private int[] disapearTime = new int[MAX_LINES];
    
    public DebugLog(World world) throws SlickException
    {
        super(0, world.container.getHeight()-200,world);
    }

    @Override
    public void render(Graphics g, GameContainer gc)
    {
        String tmp = "";
        int i = 0;
        for (String s : lines)
        {
            if (s != null && disapearTime[i++] < DISP_TIME)
                tmp += s + "\n";
        }
        if (tmp != null && !tmp.isEmpty())
            g.drawString(tmp,getPosition().x,getPosition().y);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta)
    {       
        for (int i = 0; i < MAX_LINES; ++i)
        {
            disapearTime[i] += delta;
        }
        while(!LogDisplayManager.getInstance().getLines().isEmpty())
        {
            addLine(LogDisplayManager.getInstance().getLines().pop());
        }
    } 
    
    private void addLine(String line)
    {
        
        for (int i = MAX_LINES-1; i > 0; --i)
        {
            lines[i] = lines[i-1];
            disapearTime[i] = disapearTime[i-1];
        }
        lines[0] = line;
        disapearTime[0] = 0;
    }

    @Override
    protected boolean useCamera()
    {
        return false;
    }
    
}
