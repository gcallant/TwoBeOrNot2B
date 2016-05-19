package Item;

import java.util.Comparator;

/**
 * Created by Michael on 5/18/2016.
 */
public class ConsumableSort implements Comparator<Consumable>
{
    public int compare(Consumable s1, Consumable s2)
    {
        return s1.toString().compareTo(s2.toString());
    }
}
