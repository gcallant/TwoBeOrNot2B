package BuffsCharacter;

import Characters.A_Character;
import Utilities.Display;

/**
 * Created by Michael on 5/27/2016.
 */
public abstract class CharacterBuffManage
{
    private CharacterBuffs buff;
    private A_Character affected;

    public CharacterBuffManage(A_Character name)
    {
        this.affected = name;
    }

    public void addBuff(A_Character contributor, double amount, int rounds, String source)
    {
        searchList(contributor, amount, rounds, source);
    }

    private void searchList(A_Character contributor, double amount, int rounds, String source)
    {
        if(buff != null)
        {
            Display.displayMessage(affected.getName() + " is no longer " + toString() + " by " + buff.getContributor().getName());
        }
        buff = new CharacterBuffs(contributor, amount, rounds, source);
        Display.displayMessage(affected.getName() + " is now " + toString() + " by " + contributor.getName());
    }

    public void decrementList()
    {
        if(buff != null && buff.decrement())
        {
            if(!apply())
            {
                Display.displayMessage(affected.getName() + "'s " + toString() + " has ended from " + buff.getSource());
            }
        }
    }

    public A_Character getAffected()
    {
        return affected;
    }

    public A_Character getContributor()
    {
        return buff.getContributor();
    }

    public CharacterBuffs getBuff()
    {
        return buff;
    }

    public void clear()
    {
        buff = null;
    }

    public abstract double getAmount();
    public abstract boolean apply();
    public abstract boolean isInEffect();
}