package Entities;

import Resources.PictureLoader;
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
    Player owner;
    private float X,Y,rotation;
    private Image image;
    private int id;
    
    public Weapon(float X, float Y, Player owner) throws SlickException
    {
        this.X = X;
        this.Y = Y;
        this.owner = owner;
        this.image = PictureLoader.getImage("Weapon");
    }
    
    public void update(GameContainer gc, StateBasedGame sbg, int delta)
    {
        Input input = gc.getInput();
        
        float mouseX = input.getMouseX();
        float mouseY = input.getMouseY();
        
        this.X = owner.getPosition().x + owner.world.camera.getOffsetX();
        this.Y = owner.getPosition().y + owner.world.camera.getOffsetY();
        
        rotation = (float) Math.toDegrees(Math.atan2(Y - mouseY , ((X - mouseX == 0)?  0.0001f : X - mouseX)) + 180f);
    }
    
    public void render(Graphics g, GameContainer gc)
    {
        image.setRotation(rotation);
        image.draw(X,Y);
    }
}
