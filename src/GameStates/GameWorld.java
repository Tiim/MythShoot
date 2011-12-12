package GameStates;

import Entities.Player;
import Core.Log;
import Entities.InfoText;
import Resources.MapLoader;
import it.marteEngine.Camera;
import it.marteEngine.World;
import it.marteEngine.entity.Entity;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author Tiim
 * @since 11.12.2011
 */
public class GameWorld extends World
{
    InfoText infoText;
    
    
    public GameWorld(int id, GameContainer container)
    {
        super(id, container);
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException
    {
        super.init(container, game);
        Log.Debug(this.getClass().getName(), "init");

        addEntities();

    }

    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException
    {
        super.enter(container, game);
        Log.Debug(this.getClass().getName(), "enter");
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException
    {
        super.render(container, game, g);
        infoText.render(container, g);        
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException
    {
        super.update(container, game, delta);
        
        float x = camera.getFollow().previousx - camera.getFollow().x;
        float y = camera.getFollow().previousy - camera.getFollow().y;
        
        for(Entity e : getEntities())
        {
            e.x += x;
            e.y += y;
        }
        //camera.getFollow().x -= x;
        //camera.getFollow().y -= y;
    }

    private void addEntities()
    {
        add(MapLoader.getMap("Fort"));
        Entity player = new Player(getWidth() / 2, getHeight() / 2);
        add(player);
        camera = new Camera(this, player, width, height);
    
        add(infoText = new InfoText());
        infoText.visible = false;
    }
}
