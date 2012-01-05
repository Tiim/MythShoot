package ch.mythshoot.client.util.recycler;

/**
 *
 * @author Tim
 */
public interface Recycler <K>
{
    public K get();
    public void recycle(K obj);
    public K copy(K obj);
}
