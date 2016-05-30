package BuffsCharacter;

import Characters.A_Character;

/**
 * Created by Michael on 5/27/2016.
 */
public class DefendOther extends CharacterBuffManage
{
    public DefendOther(A_Character affected)
    {
        super(affected);
    }

    public boolean isInEffect()
    {
        return getBuff() != null;
    }

    public double getAmount()
    {
        return getBuff().getAmount();
    }

    public void addBuff(A_Character contributor, double amount, int rounds, String source)
    {
        if(getBuff() != null)
        {
            getBuff().getContributor().getConditions().stopDefendOther();
        }
        contributor.getConditions().defendOther();
        super.addBuff(contributor, amount, rounds, source);
    }

    public boolean apply()
    {
        return false;
    }

    public String toString()
    {
        return "Defended";
    }
}
