package MythShoot;

import GameStates.GameWorld;
import Core.Log;
import Resources.MapLoader;
import Resources.PictureLoader;
import it.marteEngine.World;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author Tiim
 * @since 11.12.2011
 */
public class MythShoot extends StateBasedGame
{
    public static final int GAME_WORLD_ID = 1;
    
    public MythShoot(String name)
    {
        super(name);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        try
        {
            AppGameContainer gc = new AppGameContainer(new MythShoot("MythShoot"));
            gc.setDisplayMode(800,600,false);
            gc.setMouseGrabbed(false);
            gc.setShowFPS(true);
            gc.setTargetFrameRate(60);
            gc.start();
        }
        catch (SlickException ex)
        {
            Log.Exception(ex);
        }
    }

    @Override
    public void initStatesList(GameContainer container) throws SlickException
    {
        PictureLoader.initLoader();
        Log.Debug(this.getClass().getName(), "Pictures loaded");
        
        World gs = new GameWorld(GAME_WORLD_ID, container);
        addState(gs);
        
        MapLoader.initLoader(gs);
        Log.Debug(this.getClass().getName(), "Maps loaded");
    }
}
