package Core;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Tiim
 * @since 11.12.2011
 */
public class Log
{
    private static final boolean DEBUG = true;
    //@todo read Debug var from file
    
    public static void Debug(String className, Object message)
    {
        if (!DEBUG)
        {
            return;
        }
        String messageStr = (message == null)? "null" : message.toString();
        String out = "DEBUG:  [" + className + "]  " + messageStr;
        System.out.println(out);
    }
    
    public static void Warning(String className, Object message)
    {
        String out = "WARNING:  [" + className + "]  " + message.toString();
        System.out.println(out);
        save(out);
    }
    
    public static void Exception(Exception ex)
    {
            String out = "EXCEPTION:\n"; 
            out += "\t" + ex.getMessage() + "\n\n";
            out += "\t" + ex.toString() + "\n";
            for (StackTraceElement element : ex.getStackTrace())
                out += "\t" + element.getClassName() + "." + element.getMethodName() + " : ( " + element.getLineNumber() + " )" + "\n";
            System.out.println(out);
            save(out);
    }
    
    private static void save(String message)
    {
        try
        {
            FileWriter str = new FileWriter("log.txt", true);
            BufferedWriter writer = new BufferedWriter(str);
            writer.write(message);
            writer.close();
            str.close();
        }
        catch (IOException e)
        {System.out.println("CAN'T SAVE THE LOG FILE");}
    }
    
}
