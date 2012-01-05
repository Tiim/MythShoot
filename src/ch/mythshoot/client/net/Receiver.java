package ch.mythshoot.client.net;

import java.io.InputStream;

/**
 *
 * @author Tim
 */
public class Receiver extends Thread
{
    private static final Receiver INSTANCE = new Receiver();

    private boolean running = true;
    
    private InputStream input;

    public void setInputSteam(InputStream input)
    {
        this.input = input;
    }

    public void setRunning(boolean running)
    {
        this.running = running;
    }
    
    public boolean getRunning()
    {
        return running;
    }
    
    private Receiver()
    {
        
    }

    public static Receiver getInstance()
    {
        return INSTANCE;
    }
    
    @Override
    public void run()
    {
        while (running)
        {
            
        }
    }
}
