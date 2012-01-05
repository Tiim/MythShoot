package ch.mythshoot.client.net;

/**
 *
 * @author Tim
 */
public class Receiver extends Thread
{
    private static final Receiver INSTANCE = new Receiver();

    private boolean running = true;

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
