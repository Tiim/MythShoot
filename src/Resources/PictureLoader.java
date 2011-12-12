package Resources;

import Core.Log;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.util.ResourceLoader;

/**
 *
 * @author Tiim
 * @since 11.12.2011
 */
public class PictureLoader
{  
    private static HashMap<String,Image> images;
    
    
    public static void initLoader()
    {
        images = new HashMap<String, Image>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(ResourceLoader.getResourceAsStream(Props.getPropStr("Resource.Picture.Path"))));
        try
        {
            String line; 
            while ((line = reader.readLine()) != null )
            {
                line = line.trim();
                Log.Debug(PictureLoader.class.getName(),line);
                
                if (line == null || line.isEmpty() || line.startsWith("#"))
                {
                    continue;
                }
                String[] args = line.split(";");
                String key = args[0].trim();
                String path = args[1].trim();
                try
                {
                    images.put(key, new Image(ResourceLoader.getResourceAsStream(path),path,false));
                }
                catch (SlickException ex)
                {
                    Log.Exception(ex);
                }
            }
        }
        catch (IOException ex)
        {
            Log.Exception(ex);
        }
    }
    
    public static Image getImage(String key)
    {
        return images.get(key.trim());
    }
}
