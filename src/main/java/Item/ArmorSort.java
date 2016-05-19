package Item;

import java.util.Comparator;

/**
 * Created by Michael on 5/17/2016.
 */
public class ArmorSort implements Comparator<Armor>
{
    public int compare(Armor s1, Armor s2)
    {
        return s1.toString().compareTo(s2.toString());
    }
}
