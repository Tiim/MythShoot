package Entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author Tiim
 */
public class World implements GameState
{
    private int ID;
    
    protected boolean acceptsInput = true;
    
    protected List<Entity> entities;
    private Input input;
    
    protected GameContainer container;
    
    
    public World(int ID)
    {
        this.ID = ID;
        entities = new ArrayList<Entity>();
    }
    
    @Override
    public final int getID()
    {
        return ID;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException
    {
        this.container = container;
        input = new Input(container.getScreenHeight());
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException
    {
        for (Entity e : entities)
        {
            e.render(g, container);
        }
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException
    {
        for (Entity e : entities)
        {
            e.update(container, game, delta);
        }
    }

    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException
    {
        
    }

    @Override
    public void leave(GameContainer container, StateBasedGame game) throws SlickException
    {
        
    }
    //<editor-fold defaultstate="collapsed" desc="GameState implements">
    @Override
    public void mouseWheelMoved(int change)
    {
        
    }
    
    @Override
    public void mouseClicked(int button, int x, int y, int clickCount)
    {
        
    }
    
    @Override
    public void mousePressed(int button, int x, int y)
    {
        
    }
    
    @Override
    public void mouseReleased(int button, int x, int y)
    {
        
    }
    
    @Override
    public void mouseMoved(int oldx, int oldy, int newx, int newy)
    {
        
    }
    
    @Override
    public void mouseDragged(int oldx, int oldy, int newx, int newy)
    {
        
    }
    
    @Override
    public void setInput(Input input)
    {
        
    }
    
    @Override
    public boolean isAcceptingInput()
    {
        return acceptsInput;
    }
    
    @Override
    public void inputEnded()
    {
        
    }
    
    @Override
    public void inputStarted()
    {
        
    }
    
    @Override
    public void keyPressed(int key, char c)
    {
        
    }
    
    @Override
    public void keyReleased(int key, char c)
    {
        
    }
    
    @Override
    public void controllerLeftPressed(int controller)
    {
        
    }
    
    @Override
    public void controllerLeftReleased(int controller)
    {
        
    }
    
    @Override
    public void controllerRightPressed(int controller)
    {
        
    }
    
    @Override
    public void controllerRightReleased(int controller)
    {
        
    }
    
    @Override
    public void controllerUpPressed(int controller)
    {
        
    }
    
    @Override
    public void controllerUpReleased(int controller)
    {
        
    }
    
    @Override
    public void controllerDownPressed(int controller)
    {
        
    }
    
    @Override
    public void controllerDownReleased(int controller)
    {
        
    }
    
    @Override
    public void controllerButtonPressed(int controller, int button)
    {
        
    }
    
    @Override
    public void controllerButtonReleased(int controller, int button)
    {
        
    }
    //</editor-fold>
    
    public void addEntities(Entity... entity)
    {
        entities.addAll(Arrays.asList(entity));
    }
          
    public final List<Entity> getEntities(String... types)
    {
        List<Entity> ents = new ArrayList<Entity>();
        for (Entity e : entities)
        {
            if (e.isType(types))
                ents.add(e);
        }
        return ents;
    }
    
    public final List<Entity> collidesWithEntities(Entity entity, String... types)   
    {
        List<Entity> ents = new ArrayList<Entity>();
        for (Entity e : entities)
        {
            if (e.isType(types) && e.collides(entity))
                ents.add(e);
        }
        return (ents.isEmpty())? null:ents;
    }

    public Input getInput()
    {
        return input;
    }

    public int getNrOfEntities(String string)
    {
        return entities.size();
    }
}
