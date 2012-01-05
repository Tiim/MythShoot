package ch.mythshoot.client.net.server;

import java.nio.ByteBuffer;

/**
 *
 * @author Tim
 */
public abstract class AbstractReply
{
    
    public abstract int getId();  
    public abstract void deserialize(ByteBuffer buffer); 
}
