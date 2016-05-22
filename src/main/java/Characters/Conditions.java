package Characters;

import BuffsAndDebuffs.BuffsManager;

import java.util.Collections;

/**
 * Created by Michael on 5/20/2016.
 */
public class Conditions
{
    private String name;

    public Conditions(String name)
    {
        this.name = name;
        buffsManager = new BuffsManager(name);
    }

    private boolean defended;

    public void defend()
    {
        defended = true;
    }



    public int reduceDamage(int damage)
    {
        if(defended)
        {
            damage = damage/2;
        }
        return damage;
    }



    private int additionalDamage;
    private int additionalAttack;

    public int addDamage(int damage)
    {
        damage += additionalDamage;
        return Math.max(damage, 0);
    }

    public int addAttack(int attack)
    {
        attack += additionalAttack;
        return Math.max(attack, 0);
    }

    public void tempDamage(int tempDamage)
    {
        additionalDamage = tempDamage;
    }

    public void tempAttack(int tempAttack)
    {
        additionalAttack = tempAttack;
    }


    private BuffsManager buffsManager;

    public void giveDamageBuff(double percentage, int rounds, String source)
    {
        buffsManager.addDamageBuff(percentage, rounds, source);
    }

    public void giveAttackBuff(double percentage, int rounds, String source)
    {
        buffsManager.addAttackBuff(percentage,rounds,source);
    }

    public int calculateDamage(int damage)
    {
        damage = (int)((double)damage*buffsManager.getDamageBuffAmount());
        return damage;
    }

    public int calculateAttack(int attack)
    {
        attack = (int)((double)attack*buffsManager.getAttackBuffAmount());
        return attack;
    }



    private boolean stunned;
    private int stunnedCount;

    public void stunned(int count)
    {
        if(stunnedCount <= 0)
        {
            stunnedCount = count;
        }
        else
        {
            stunnedCount += count;
        }
        stunned = true;
    }

    public boolean cannotAttack()
    {
        return stunned;
    }



    private boolean exhausted;
    private int exhaustedCount;

    public void exhausted(int count)
    {
        if(exhaustedCount <= 0)
        {
            exhaustedCount = count;
        }
        else
        {
            exhaustedCount += count;
        }
        exhausted = true;
    }

    public boolean cannotUseSpecial()
    {
        return exhausted;
    }



    private boolean poisoned;
    private int poisonCount;

    public void poisoned(int count)
    {
        if(poisonCount <= 0)
        {
            poisonCount = count;
        }
        else
        {
            poisonCount += count;
        }
        poisoned = true;
    }

    public boolean isPoisoned()
    {
        return poisoned;
    }
    public boolean hasBadCondition()
    {
        return stunned || exhausted || poisoned;
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
        endBuffs();
    }

    public void startTurn()
    {
        endDefending();
        buffRound();
    }

    public void endTurn()
    {
        poisonRound();
        exhaustedRound();
        stunnedRound();
        additionalDamage = 0;
        additionalAttack = 0;
    }

    private void endExhaustion()
    {
        if(exhausted)
        {
            System.out.println(name + "'s exhaustion has ended");
        }
        exhausted = false;
    }

    private void endDefending()
    {
        defended = false;
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

    private void endBuffs()
    {
        buffsManager.cleanBuffs();
    }

    private void buffRound()
    {
        buffsManager.decrement();
    }

    private void poisonRound()
    {
        poisonCount -= 1;
        if(poisonCount == 0)
        {
            endPoisoned();
        }
    }

    private void stunnedRound()
    {
        stunnedCount -= 1;
        if(stunnedCount == 0)
        {
            endStunned();
        }
    }

    private void exhaustedRound()
    {
        exhaustedCount -= 1;
        if(exhaustedCount == 0)
        {
            endExhaustion();
        }
    }
}
