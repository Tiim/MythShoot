package ch.mythshoot.Resources;

import ch.mythshoot.Core.Log;
import ch.mythshoot.Entities.Map;
import ch.mythshoot.Entities.World;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.util.ResourceLoader;

/**
 *
 * @author Tiim
 * @since 11.12.2011
 */
public class MapLoader
{
    private static HashMap<String, Map> maps;

    public static void initLoader(World gameWorld) throws SlickException
    {
        String path = Props.getPropStr("Resource.Map.Path");
        maps = new HashMap<String, Map>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(ResourceLoader.getResourceAsStream(path)));
        try
        {
            while (reader.ready())
            {
                String line = reader.readLine().trim();

                Log.Debug(PictureLoader.class, line);

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
            Log.Exception(ex);
        }
    }

    public static Map getMap(String key)
    {
        
        if(maps.containsKey(key))
            return maps.get(key.trim());
        return null;
    }
}
