package ch.mythshoot.Core;

import ch.mythshoot.Resources.Props;
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
    /**
     * Displays debug messages if "Debug" int the Poprietyfile is {@code true}
     * @param c The caller's class
     * @param message The message (normally {@code String})
     */
    public synchronized static void Debug(Class c, Object message)
    {
        if (!Props.getPropBool("Debug"))
        {
            return;
        }
        String messageStr = (message == null)? "null" : message.toString();
        String out = "DEBUG:  [" + c.getName() + "]  " + messageStr;
        System.out.println(out);
    }
    
    
    /**
     * Displays and saves warning at "log.txt"
     * @param c The caller's class
     * @param message The message (normally {@code String})
     */
    public synchronized static void Warning(Class c, Object message)
    {
        String out = "WARNING:  [" + c.getName() + "]  " + message.toString();
        System.out.println(out);
        save(out);
    }
    
    /**
     * Displays and saves Exceptions
     * @param ex the Exception
     */
    public synchronized static void Exception(Exception ex)
    {
            String out = "EXCEPTION:\n"; 
            out += "\t" + ex.getMessage() + "\n\n";
            out += "\t" + ex.toString() + "\n";
            for (StackTraceElement element : ex.getStackTrace())
                out += "\t" + element.getClassName() + "." + element.getMethodName() + " : ( " + element.getLineNumber() + " )" + "\n";
            System.err.println(out);
            save(out);
    }
    
    /**
     * @param message The string to save 
     */
    private synchronized static void save(String message)
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
