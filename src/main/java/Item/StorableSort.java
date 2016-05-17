package Item;

import java.util.Comparator;

/**
 * Created by Michael on 5/17/2016.
 */
public class StorableSort implements Comparator<Storable>
{
    public int compare(Storable s1, Storable s2)
    {
        return s1.toString().compareTo(s2.toString());
    }
}
