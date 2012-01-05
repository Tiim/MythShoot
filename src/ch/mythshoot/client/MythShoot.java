package ch.mythshoot.client;

import ch.mythshoot.client.gamestates.GameWorld;
import ch.mythshoot.client.entities.World;
import ch.mythshoot.client.resources.MapLoader;
import ch.mythshoot.client.resources.PictureLoader;
import ch.mythshoot.client.resources.Props;
import ch.mythshoot.client.crash.LogDisplayManager;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Main class of the Game MythShoot
 * @author Tiim
 * @since 11.12.2011
 */
public class MythShoot extends StateBasedGame
{
    /** ID of the game world state */
    public static final int GAME_WORLD_ID = 1;
    
    private static final Logger LOGGER = Logger.getLogger(MythShoot.class.getName());

    /** 
     * @param name Windowtitle
     */
    public MythShoot(String name)
    {
        super(name);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        /** Initialise the settingsloader */
        Props.initLoader();
        Logger.getLogger("").addHandler(LogDisplayManager.getInstance());
        try
        {
            /** Get the windowheight */
            int width = Props.getPropInt("Graphic.DisplayMode.Width");
            /** Get the windowwidth */
            int height = Props.getPropInt("Graphic.DisplayMode.Height");
            /** Get the fulscreen boolean */
            boolean fullscreen = Props.getPropBool("Graphic.DisplayMode.Fullscreen");
            
            /** Add MythShoot class to the game container */
            AppGameContainer gc = new AppGameContainer(new MythShoot(Props.getPropStr("Graphic.Title")));
            /** Set display mode */
            gc.setDisplayMode(width,height,fullscreen);
            /** Mouse should be visible */
            gc.setMouseGrabbed(false);
            /** Show no FPS, we can do that ourselves */
            gc.setShowFPS(false);
            /** Start our game */
            gc.start();
        }
        catch (SlickException ex)
        {
            /** Log slick exceptions */
            LOGGER.log(Level.SEVERE, "Error in main method", ex);
        }
    }

    /** 
     * 
     *@param container the game container
     */
    @Override
    public void initStatesList(GameContainer container) throws SlickException
    {
        /** Initialise picture loader */
        PictureLoader.getInstance().initLoader();
        
        /** Initialise the game world */
        World gs = new GameWorld(GAME_WORLD_ID, container);
        /** add it */
        addState(gs);

        /** Load the maps */
        MapLoader.getInstance().initLoader(gs);
    }
}
