package ch.mythshoot.Entities;

import ch.mythshoot.Resources.PictureLoader;
import ch.mythshoot.Resources.Props;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author Tiim
 * @since 11.12.2011
 */
public class Map extends Entity
{

    private static final Logger LOGGER = Logger.getLogger(Map.class.getName());
    float XOffset;
    float YOffset;
    private ArrayList<String> lines;

    public Map(float x, float y, BufferedReader br, World world) throws SlickException
    {
        super(x, y, world);
        this.world = world;
        lines = new ArrayList<String>();
        try
        {
            XOffset = Integer.parseInt(br.readLine().trim());
            YOffset = Integer.parseInt(br.readLine().trim());
            while (br.ready())
            {
                try
                {
                    String line = br.readLine();
                    if (line == null || line.isEmpty())
                        continue;
                    lines.add(line);
                }
                catch (IOException ex)
                {
                    LOGGER.log(Level.SEVERE, "Unable to read next Line", ex);
                }
            }
        }
        catch (IOException ex)
        {
            LOGGER.log(Level.SEVERE, "Unable to read mapdata", ex);
        }
        int tileWidth = Props.getPropInt("Map.Element.Width");
        int tileHeight = Props.getPropInt("Map.Element.Height");
        for (int tileY = 0; tileY < lines.size(); tileY++)
        {
            char[] charry = lines.get(tileY).toCharArray();
            for (int tileX = 0; tileX < charry.length; tileX++)
            {
                switch (charry[tileX])
                {
                    case 's':
                        world.addEntities(new MapElement(tileX * tileHeight + XOffset, tileY * tileWidth + YOffset, PictureLoader.getInstance().getImage("Wall Stone"), world));
                        break;
                    case 'w':
                        world.addEntities(new MapElement(tileX * tileHeight + XOffset, tileY * tileWidth + YOffset, PictureLoader.getInstance().getImage("Wall Wood"), world));
                        break;
                    case 'g':
                        world.addEntities(new MapElement(tileX * tileHeight + XOffset, tileY * tileWidth + YOffset, PictureLoader.getInstance().getImage("Wall Gravel"), world));
                        break;
                }
            }
        }
    }

    public void removeWalls()
    {
        for (Entity e : world.getEntities("WALL"))
        {
            e.destroy();
        }
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta)
    {
    }

    @Override
    public void render(Graphics g, GameContainer gc)
    {
    }

    @Override
    protected boolean useCamera()
    {
        return false;
    }
}
