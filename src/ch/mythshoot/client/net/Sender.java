package ch.mythshoot.client.net;

/**
 *
 * @author Tim
 */
public class Sender extends Thread
{
    private static final Sender INSTANCE = new Sender();
    
    private boolean running = true;

    public void setRunning(boolean running)
    {
        this.running = running;
    }
    
    public boolean getRunning()
    {
        return running;
    }
    
    private Sender()
    {
        
    }

    public static Sender getInstance()
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
