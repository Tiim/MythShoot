package ch.mythshoot.client.net.client;

import java.nio.ByteBuffer;

/**
 *
 * @author Tim
 */
public abstract class AbstractCtl
{
    public abstract int getId();
    public abstract ByteBuffer serialize();

}
