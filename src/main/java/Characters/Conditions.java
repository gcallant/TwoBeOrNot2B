package Characters;

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


    private double damageBuff = 1;
    private int damageBuffCount;
    private double attackBuff = 1;
    private int attackBuffCount;

    public void giveDamageBuff(double percentage, int rounds)
    {
        damageBuff = percentage;
        damageBuffCount = rounds;
    }

    public void giveAttackBuff(double percentage, int rounds)
    {
        attackBuff = percentage;
        attackBuffCount = rounds;
    }

    public int calculateDamage(int damage)
    {
        damage = (int)((double)damage*damageBuff);
        return damage;
    }

    public int calculateAttack(int attack)
    {
        attack = (int)((double)attack*attackBuff);
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
        endDamageBuff();
        endAttackBuff();
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

    private void endDamageBuff()
    {
        damageBuff = 1;
        System.out.println(name + "'s damage returned to normal");
    }

    private void endAttackBuff()
    {
        attackBuff = 1;
        System.out.println(name + "'s attack returned to normal");
    }

    private void buffRound()
    {
        attackBuffCount -= 1;
        if(attackBuffCount == 0)
        {
            endAttackBuff();
        }
        damageBuffCount -= 1;
        if(damageBuffCount == 0)
        {
            endDamageBuff();
        }
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
