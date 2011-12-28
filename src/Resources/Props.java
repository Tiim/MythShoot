package Resources;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.util.ResourceLoader;

/**
 *
 * @author Tiim
 * @since 12.12.2011
 */
public class Props
{
    private static final String PATH = "Assets/Settings/settings.properties";
    private static Properties properties;
    
    private Props()
    {
        
    }
    
    public static void initLoader()
    {
        properties = new Properties();
        try
        {
            properties.load(ResourceLoader.getResourceAsStream(PATH));
        }
        catch (IOException ex)
        {
            Logger.getLogger(Props.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static String getPropStr(String key)
    {
        return properties.getProperty(key);
    }
    
    public static int getPropInt(String key)
    {
        return Integer.parseInt(getPropStr(key));
    }
    
    public static float getPropFloat(String key)
    {
        return Float.parseFloat(getPropStr(key));
    }
    
    public static boolean getPropBool(String key)
    {
        return Boolean.parseBoolean(getPropStr(key));
    }
    
    public static long getPropLong(String key)
    {
        return Long.parseLong(getPropStr(key));
    }

    public static double getPropDouble(String key)
    {
        return Double.parseDouble(getPropStr(key));
    }
    
    public static void setProp(String key, String value)
    {
        properties.setProperty(key, value);
    }
}
