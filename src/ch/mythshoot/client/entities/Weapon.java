package ch.mythshoot.client.entities;

import ch.mythshoot.client.resources.PictureLoader;
import ch.mythshoot.client.resources.Props;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author Tim
 */
public class Weapon
{
    boolean debug;
    Player owner;
    private float X,Y,rotation;
    private Image image;
    private int id;
    
    public Weapon(float X, float Y, int id, Player owner) throws SlickException
    {
        this.X = X;
        this.Y = Y;
        this.owner = owner;
        this.id = id;
        this.image = PictureLoader.getInstance().getImage("Weapon " + id);
        debug = Props.getPropBool("Debug");
    }
    
    public void update(GameContainer gc, StateBasedGame sbg, int delta)
    {
        Input input = gc.getInput();
        
        float mouseX = input.getMouseX();
        float mouseY = input.getMouseY();
        
        this.X = owner.getPosition().x + owner.world.camera.getOffsetX() + owner.getImage().getWidth() / 2 - image.getWidth() / 2;
        this.Y = owner.getPosition().y + owner.world.camera.getOffsetY() + owner.getImage().getHeight() /1.5f - image.getHeight() /2;
        
        rotation = (float) Math.toDegrees(Math.atan2(Y + image.getHeight() /2 - mouseY , ((X + image.getWidth() / 2 - mouseX == 0)?  0.0001f : X + image.getWidth() / 2 - mouseX))) + 180;
    }
    
    public void render(Graphics g, GameContainer gc)
    {
        image.setRotation(rotation);
        image.draw(X,Y);
    }

    public float getRotation()
    {
        return rotation;
    }
    
    
}
