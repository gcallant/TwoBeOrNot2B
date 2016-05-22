package PartyManagement;

import Characters.A_Character;

import java.util.Comparator;

/**
 * Created by Michael on 5/16/2016.
 */
public class InitiativeSort implements Comparator<A_Character>
{
    public int compare(A_Character c1, A_Character c2)
    {
        return c1.getInitiative() - c2.getInitiative();
    }
}
