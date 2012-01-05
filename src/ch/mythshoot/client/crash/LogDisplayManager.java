package ch.mythshoot.client.crash;

import java.util.LinkedList;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

/**
 *
 * @author Tim
 */
public class LogDisplayManager extends Handler
{
    private static final LogDisplayManager INSTANCE = new LogDisplayManager();
    
    private LinkedList<String> lines;

    public static LogDisplayManager getInstance()
    {
        return INSTANCE;
    }

    public LinkedList<String> getLines()
    {
        return lines;
    }
    
    private LogDisplayManager()
    {
        lines = new LinkedList<String>();
    }
    
    @Override
    public void publish(LogRecord record)
    {
        String msg = "[" + record.getLoggerName()+"]" + record.getMessage();
        String[] line = msg.split("\n");
        
        for (int i = line.length -1; i >= 0; --i)
        {
            lines.add(line[i]);
        }
    }

    @Override
    public void flush()
    {
        
    }

    @Override
    public void close() throws SecurityException
    {
        
    }
    
}
