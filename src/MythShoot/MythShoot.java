package MythShoot;

import GameStates.GameWorld;
import Core.Log;
import Resources.MapLoader;
import Resources.PictureLoader;
import Resources.Props;
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
        Props.initLoader();
        try
        {
            int width = Props.getPropInt("Graphic.DisplayMode.Width");
            int height = Props.getPropInt("Graphic.DisplayMode.Height");
            int fps = Props.getPropInt("Graphic.FrameRate");
            boolean fullscreen = Props.getPropBool("Graphic.DisplayMode.Fullscreen");
            
            AppGameContainer gc = new AppGameContainer(new MythShoot(Props.getPropStr("Graphic.Title")));
            gc.setDisplayMode(width,height,fullscreen);
            gc.setMouseGrabbed(false);
            gc.setShowFPS(true);
            gc.setTargetFrameRate(fps);
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

        World gs = new GameWorld(GAME_WORLD_ID, container);
        addState(gs);

        MapLoader.initLoader(gs);
    }
}
