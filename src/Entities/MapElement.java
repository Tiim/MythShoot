package Entities;

import it.marteEngine.entity.Entity;
import org.newdawn.slick.Image;

/**
 *
 * @author Tiim
 * @since 11.12.2011
 */
public class MapElement extends Entity
{
    private static final String ENTITY = "ENTITY";
    public static final String TYPE = "WALL";
    public MapElement(float x, float y, Image image, String type)
    {
        super(x, y, image);
        addType(TYPE,ENTITY,type);
    }
}
