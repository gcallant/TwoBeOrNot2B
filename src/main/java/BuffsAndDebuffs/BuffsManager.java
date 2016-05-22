package BuffsAndDebuffs;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michael on 5/21/2016.
 */
public class BuffsManager
{
    private BuffList attack;
    private BuffList damage;
    private BuffList regen;
    private BuffList poison;
    private BooleanBuffList exhausted;
    private BooleanBuffList stunned;
    private String name;

    public BuffsManager(String name)
    {
        attack = new AttackBuffs(name);
        damage = new DamageBuffs(name);
        regen = new RegenBuffs(name);
        poison = new PoisonBuffs(name);
        exhausted = new ExhaustedBuffs(name);
        stunned = new StunnedDebuff(name);
        this.name = name;
    }

    //One for every BuffList
    public void addAttackBuff(double buff, int rounds, String source)
    {
        attack.addBuff(buff, rounds, source);
    }

    public void addDamageBuff(double buff, int rounds, String source)
    {
        damage.addBuff(buff, rounds, source);
    }

    public void addRegenBuff(double buff, int rounds, String source)
    {
        regen.addBuff(buff, rounds, source);
    }

    public void addPoisonDebuff(double debuff, int rounds, String source)
    {
        poison.addBuff(debuff, rounds, source);
    }

    public void addExhaustedDebuff(int rounds, String source)
    {
        exhausted.addBuff(rounds, source);
    }

    public void addStunnedDebuff(int rounds, String source)
    {
        stunned.addBuff(rounds, source);
    }
    //

    public void decrement()
    {
        //One for every BuffList
        attack.decrementList();

        damage.decrementList();

        regen.decrementList();

        poison.decrementList();

        exhausted.decrementList();

        stunned.decrementList();
    }

    public void decrementBad()
    {
        //All negative buffs except Exhaustion.
        poison.decrementList();
        stunned.decrementList();
    }

    //One for every BuffList
    public double getDamageBuffAmount()
    {
        return damage.getAmount();
    }

    public double getAttackBuffAmount()
    {
        return attack.getAmount();
    }

    public double getRegenAmount()
    {
        return regen.getAmount();
    }

    public double getPoisonAmount()
    {
        return poison.getAmount();
    }

    public boolean isExhausted()
    {
        return exhausted.isInEffect();
    }

    public boolean isStunned()
    {
        return stunned.isInEffect();
    }
    //

    public boolean badCondition()
    {
        boolean badCondition = false;

        badCondition = poison.size() > 0 || badCondition;
        badCondition = exhausted.isInEffect() || badCondition;
        badCondition = stunned.isInEffect() || badCondition;

        return badCondition;
    }

    public void clearBad()
    {
        while(poison.size() > 0 || stunned.size() > 0)
        {
            decrementBad();
        }
    }

    public void cleanBuffs()
    {
        //One for each buff
        attack.clear();
        damage.clear();
        regen.clear();
        poison.clear();
        exhausted.clear();
        stunned.clear();
    }
}
