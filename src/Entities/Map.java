package Entities;

import Core.Log;
import Resources.PictureLoader;
import it.marteEngine.World;
import it.marteEngine.entity.Entity;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

/**
 *
 * @author Tiim
 * @since 11.12.2011
 */
public class Map extends Entity
{

    private ArrayList<String> lines;

    public Map(float x, float y, BufferedReader br, String name,World world)
    {
        super(x, y);
        this.name = name;
        this.world = world;
        lines = new ArrayList<String>();
        try
        {
            int count = 0;
            while (br.ready())
            {
                try
                {
                    String line = br.readLine();
                    if (line == null || line.isEmpty())
                    {
                        continue;
                    }
                    lines.add(line);
                }
                catch (IOException ex)
                {
                    Log.Exception(ex);
                }
            }
        }
        catch (IOException ex)
        {
            Log.Exception(ex);
        }
        for (int tileY = 0; tileY < lines.size(); tileY++)
        {
            char[] charry = lines.get(tileY).toCharArray();
            for (int tileX = 0; tileX < charry.length; tileX++)
            {
                try
                {
                    switch (charry[tileX])
                    {
                        case 's':
                            world.add(new MapElement(tileX * 32, tileY * 32, PictureLoader.getImage("Wall Stone"), name));
                            break;
                        case 'w':
                            world.add(new MapElement(tileX * 32, tileY * 32, PictureLoader.getImage("Wall Wood"), name));
                            break;
                    }
                }
                catch (Exception ex)
                {
                    Log.Exception(ex);
                }
            }
        }
    }

    public void removeWalls()
    {
        for (Entity e : world.getEntities(name))
        {
            e.destroy();
        }
    }

    @Override
    public void render(GameContainer container, Graphics g) throws SlickException
    {
    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException
    {
    }
}
