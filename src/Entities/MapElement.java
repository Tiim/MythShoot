package Entities;

import Resources.Props;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 *
 * @author Tiim
 * @since 11.12.2011
 */
public class MapElement extends Entity
{
    public static final String TYPE = "WALL";
    public MapElement(float x, float y, Image image, World world) throws SlickException
    {
        super(x, y, image, world);
        addType(TYPE);
        setHitbox(0,0,Props.getPropInt("Map.Element.Width"),Props.getPropInt("Map.Element.Height"));
    }
}
