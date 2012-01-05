package ch.mythshoot.Resources;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private HashMap<String,Image> images;
    
    private static final PictureLoader INSTANCE = new PictureLoader();
    
    private static final Logger LOGGER = Logger.getLogger(PictureLoader.class.getName());
    
    private PictureLoader()
    {
        images = new HashMap<String, Image>();
    }
    
    public static PictureLoader getInstance()
    {
        return INSTANCE;
    }
    
    public void initLoader()
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(ResourceLoader.getResourceAsStream(Props.getPropStr("Resource.Picture.Path"))));
        try
        {
            String line; 
            while ((line = reader.readLine()) != null )
            {
                line = line.trim();
                
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
                    LOGGER.log(Level.WARNING, "Unable to create image " + path, ex);
                }
            }
        }
        catch (IOException ex)
        {
            LOGGER.log(Level.SEVERE, "Unable to initialise the picture loader", ex);
        }
    }
    
    public Image getImage(String key) throws SlickException
    {
        Image img = images.get(key.trim());
        if (img == null)
            throw new SlickException("Image " + key + " not found!");
        return img;
    }
}
