package ch.mythshoot.client.util.recycler;

import java.util.ArrayDeque;
import java.util.Queue;
import org.newdawn.slick.geom.Vector2f;

/**
 *
 * @author Tim
 */
public class VectorRecycler implements Recycler<Vector2f>
{

    private static final VectorRecycler INSTANCE = new VectorRecycler();
    private Queue<Vector2f> vectors;

    private VectorRecycler()
    {
        vectors = new ArrayDeque<Vector2f>();
    }

    public static VectorRecycler getInstance()
    {
        return INSTANCE;
    }

    @Override
    public Vector2f get()
    {
        if (vectors.isEmpty())
            return new Vector2f();

        Vector2f vec = vectors.poll();
        return vec;
    }

    @Override
    public void recycle(Vector2f newvec)
    {
        if (newvec != null)
        {
            vectors.add(newvec);
            newvec = null;
        }
    }

    @Override
    public Vector2f copy(Vector2f obj)
    {
        Vector2f vec = get();
        vec.x = obj.x;
        vec.y = obj.y;
        return vec;
    }
}
