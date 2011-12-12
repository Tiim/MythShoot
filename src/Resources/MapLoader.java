package Resources;

import Core.Log;
import Entities.Map;
import it.marteEngine.World;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import org.newdawn.slick.util.ResourceLoader;

/**
 *
 * @author Tiim
 * @since 11.12.2011
 */
public class MapLoader
{

    private final static String PATH = "Assets/Maps/res.dat";
    private static HashMap<String, Map> maps;

    public static void initLoader(World gameWorld)
    {
        maps = new HashMap<String, Map>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(ResourceLoader.getResourceAsStream(PATH)));
        try
        {
            while (reader.ready())
            {
                String line = reader.readLine().trim();

                Log.Debug(PictureLoader.class.getName(), line);

                if (line == null || line.isEmpty() || line.startsWith("#"))
                {
                    continue;
                }
                String[] args = line.split(";");
                String key = args[0].trim();
                String path = args[1].trim();
                Map map = new Map(0,0,new BufferedReader(new InputStreamReader(ResourceLoader.getResourceAsStream(path))),key,gameWorld);
                gameWorld.add(map);
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
