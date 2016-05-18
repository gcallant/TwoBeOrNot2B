package Item;

import java.util.Comparator;

/**
 * Created by Michael on 5/18/2016.
 */
public class WeaponSort implements Comparator<Weapon>
{
    public int compare(Weapon s1, Weapon s2)
    {
        return s1.toString().compareTo(s2.toString());
    }
}
