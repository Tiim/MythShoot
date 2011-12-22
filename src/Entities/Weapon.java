package Entities;

import java.awt.Graphics;
import org.newdawn.slick.GameContainer;
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
    Player owner;
    private float X,Y,rotation;
    private Image image;
    
    public Weapon(float X, float Y, Image image,Player owner) throws SlickException
    {
        this.X = X;
        this.Y = Y;
        this.image = image;
        this.owner = owner;
        
    }
    
    public void update(GameContainer gc, StateBasedGame sbg, int delta)
    {
        Input input = gc.getInput();
        
        float mouseX = input.getMouseX();
        float mouseY = input.getMouseY();
        
        this.X = owner.getPosition().x + owner.world.camera.getOffsetX();
        this.Y = owner.getPosition().y + owner.world.camera.getOffsetY();
        
        rotation = (float) Math.atan(mouseX - X / ((mouseY - Y == 0)?  0.0001f : mouseY - Y));
    }
    
    public void render(Graphics g, GameContainer gc)
    {
        image.setRotation(rotation);
        image.draw(X,Y);
    }
}
