package ch.mythshoot.client.resources;

import ch.mythshoot.client.entities.Map;
import ch.mythshoot.client.entities.World;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.util.ResourceLoader;

/**
 *
 * @author Tiim
 * @since 11.12.2011
 */
public class MapLoader
{
    private HashMap<String, Map> maps;

    private static final MapLoader INSTANCE = new MapLoader();
    
    private static final Logger LOGGER = Logger.getLogger(MapLoader.class.getName());
    
    public static MapLoader getInstance()
    {
        return INSTANCE;
    }
    
    private MapLoader()
    {
        maps = new HashMap<String, Map>();
    }
    
    public void initLoader(World gameWorld) throws SlickException
    {
        String path = Props.getPropStr("Resource.Map.Path");
        maps = new HashMap<String, Map>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(ResourceLoader.getResourceAsStream(path)));
        try
        {
            while (reader.ready())
            {
                String line = reader.readLine().trim();


                if (line == null || line.isEmpty() || line.startsWith("#"))
                {
                    continue;
                }
                String[] args = line.split(";");
                String key = args[0].trim();
                String resPath = args[1].trim();
                Map map = new Map(0,0,new BufferedReader(new InputStreamReader(ResourceLoader.getResourceAsStream(resPath))),gameWorld);
                gameWorld.addEntities(map);
                maps.put(key,map);
            }
        }
        catch (IOException ex)
        {
            LOGGER.log(Level.SEVERE,"Unable to initialise the maploader", ex);
        }
    }

    public Map getMap(String key)
    {
        
        if(maps.containsKey(key))
            return maps.get(key.trim());
        return null;
    }
}
