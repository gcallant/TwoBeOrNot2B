package Characters;

import java.util.Collections;

/**
 * Created by Michael on 5/20/2016.
 */
public class Conditions
{
    private boolean exhausted;
    private int exhaustedCount;
    private boolean defended;
    private boolean isProtected;
    private boolean isProtecting;
    private boolean poisoned;
    private int poisonCount;
    private boolean stunned;
    private int stunnedCount;
    private Conditions protecting;
    int additionalDamage;
    int additionalAttack;
    private String name;

    public Conditions(String name)
    {
        this.name = name;
    }

    public boolean canAttack()
    {
        return !stunned;
    }

    public int reduceDamage(int damage)
    {
        if(isProtected)
        {
            damage = damage/2;
        }
        if(defended)
        {
            damage = damage/2;
        }
        return damage;
    }

    public int addDamage(int damage)
    {
        damage += additionalDamage;
        return Math.max(damage, 0);
    }

    public void tempDamage(int tempDamage)
    {
        additionalDamage += tempDamage;
    }

    public void tempAttack(int tempAttack)
    {
        additionalAttack += tempAttack;
    }

    public boolean takeDamage()
    {
        return poisoned;
    }

    public boolean useSpecial()
    {
        return !exhausted;
    }

    public boolean hasBadCondition()
    {
        return stunned || exhausted || poisoned;
    }

    public void stunned(int count)
    {
        stunnedCount += count;
        stunned = true;
    }

    public void exhausted(int count)
    {
        exhaustedCount += count;
        exhausted = true;
    }

    public void poisoned(int count)
    {
        poisoned = true;
        poisonCount += count;
    }

    public void setProtected()
    {
        isProtected = true;
    }

    public void defend()
    {
        defended = true;
    }

    public void setProtector(Conditions conditions)
    {
        protecting = conditions;
        conditions.setProtected();
        isProtecting = true;
    }

    public void recoverConditions()
    {
        endExhaustion();
        endStunned();
        endPoisoned();
    }

    public void resetConditions()
    {
        recoverConditions();
        endProtecting();
    }

    public void endTurn()
    {
        poisonRound();
        exhaustedRound();
        stunnedRound();
        endDefending();
        endProtecting();
    }

    private void endExhaustion()
    {
        if(exhausted)
        {
            System.out.println(name + " exhaustion has ended");
        }
        exhausted = false;
    }

    private void endDefending()
    {
        defended = false;
    }

    private void endProtecting()
    {
        if(protecting != null)
        {
            protecting.endProtection();
            protecting = null;
            isProtecting = false;
        }
    }

    private void endProtection()
    {
        if(isProtected)
        {
            System.out.println(name + " is no longer being protected");
        }
        isProtected = false;
    }

    private void endPoisoned()
    {
        if(poisoned)
        {
            System.out.println(name + " is no longer poisoned");
        }
        poisoned = false;
        poisonCount = 0;
    }

    private void endStunned()
    {
        if(stunned)
        {
            System.out.println(name + " is no longer stunned");
        }
        stunned = false;
    }

    private void poisonRound()
    {
        poisonCount--;
        if(poisonCount == 0)
        {
            endPoisoned();
        }
    }

    private void stunnedRound()
    {
        stunnedCount--;
        if(stunnedCount == 0)
        {
            endStunned();
        }
    }

    private void exhaustedRound()
    {
        exhaustedCount--;
        if(exhaustedCount == 0)
        {
            endExhaustion();
        }
    }

}
