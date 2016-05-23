package PartyManagement;

import Characters.A_Character;

import java.util.Comparator;

/**
 * Created by Michael on 5/16/2016.
 */
public class DefeatedSort implements Comparator<A_Character>
{
    public int compare(A_Character o1, A_Character o2)
    {
        if(o1.getDefeated() && o2.getDefeated())
        {
            return 0;
        }
        else if(o1.getDefeated())
        {
            return 1;
        }
        else if(o2.getDefeated())
        {
            return -1;
        }
        return 0;
    }
}
