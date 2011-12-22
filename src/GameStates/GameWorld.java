package GameStates;

import Entities.Player;
import Core.Log;
import Entities.Camera;
import Entities.Entity;
import Entities.InfoText;
import Entities.World;
import Resources.MapLoader;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 * The Gamestate for the Game
 * @author Tiim
 * @since 11.12.2011
 */
public class GameWorld extends World
{
    /** Entity that shows FPS and entitycount */
    InfoText infoText;
    
    /** 
     * 
     * @param id The id of this GameState
     * @param container The GameContainer
     */
    public GameWorld(int id, GameContainer container)
    {
        super(id);
    }

    /**
     * Initialise the world
     * @param container The GameContainer
     * @param game The main class
     * @throws SlickException
     */
    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException
    {
        super.init(container, game);
        /** Add the entities */
        addEntities();

    }

    /** 
     * Called before enter the state
     * @param container The GameContainer
     * @param game The statebasedgame 
     * @throws SlickException
     */
    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException
    {
        super.enter(container, game);
    }
    
    /** 
     * Renders the scene
     * @param container The GameContainer
     * @param game The StatebasedGame
     * @param g Graphics is used to draws primitives and Text
     * @throws SlickException
     */
    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException
    {
        super.render(container, game, g);        
    }

    /** 
     * Here comes the Gamelogic
     * @param container The GameContainer
     * @param game The StatebasedGame
     * @param delta The time elapsed since last call 
     * @throws SlickException
     */
    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException
    {
        /** If the window is draged, the delta value will be very high */
        if (delta > 20) delta = 20;
        
        super.update(container, game, delta);
    }

    private void addEntities() throws SlickException
    {
        /** Add all wall elements */
        addEntities(MapLoader.getMap("Fort"));
        /** Add the Player */
        Entity player = new Player(container.getWidth() / 2, container.getHeight() / 2,this);
        addEntities(player);
        /** Initialise the camere */
        camera = new Camera(player,this);
        
        /** Add the Infotext */
        addEntities(infoText = new InfoText(10,10,this));
    }

}
