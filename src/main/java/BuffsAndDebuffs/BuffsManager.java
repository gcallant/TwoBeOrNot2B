package BuffsAndDebuffs;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michael on 5/21/2016.
 */
public class BuffsManager
{
    private BuffList attack;
    private BuffList attackDebuff;
    private BuffList damage;
    private BuffList damageDebuff;
    private BuffList regen;
    private BuffList poison;
    private BuffList bleed;
    private BooleanBuffList exhausted;
    private BooleanBuffList stunned;
    private String name;

    public BuffsManager(String name)
    {
        attack = new AttackBuffs(name);
        attackDebuff = new AttackDebuffs(name);
        damage = new DamageBuffs(name);
        damageDebuff = new DamageDebuff(name);
        regen = new RegenBuffs(name);
        poison = new PoisonBuffs(name);
        exhausted = new ExhaustedBuffs(name);
        stunned = new StunnedDebuff(name);
        bleed = new BleedDebuff(name);
        this.name = name;
    }

    //One for every BuffList
    public void addAttackBuff(double buff, int rounds, String source)
    {
        attack.addBuff(buff, rounds, source);
    }

    public void addAttackDebuff(double buff, int rounds, String source)
    {
        attackDebuff.addBuff(buff, rounds, source);
    }

    public void addDamageBuff(double buff, int rounds, String source)
    {
        damage.addBuff(buff, rounds, source);
    }

    public void addDamageDebuff(double buff, int rounds, String source)
    {
        damageDebuff.addBuff(buff, rounds, source);
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

    public void addBleedDebuff(double buff, int rounds, String source)
    {
        bleed.addBuff(buff, rounds, source);
    }
    //

    public void decrement()
    {
        //One for every BuffList
        attack.decrementList();

        attackDebuff.decrementList();

        damage.decrementList();

        damageDebuff.decrementList();

        regen.decrementList();

        poison.decrementList();

        exhausted.decrementList();

        stunned.decrementList();

        bleed.decrementList();
    }

    public void decrementBad()
    {
        //All negative buffs except Exhaustion.
        poison.decrementList();
        stunned.decrementList();
        attackDebuff.decrementList();
        damageDebuff.decrementList();
        bleed.decrementList();
    }

    //One for every BuffList
    public double getDamageBuffAmount()
    {
        return 1.0 + damage.getAmount() + damageDebuff.getAmount();
    }

    public double getAttackBuffAmount()
    {
        return 1.0 + attack.getAmount() + attackDebuff.getAmount();
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

    public double getBleedAmount()
    {
        return bleed.getAmount();
    }
    //

    public boolean badCondition()
    {
        boolean badCondition = false;

        badCondition = poison.size() > 0 || badCondition;
        badCondition = exhausted.isInEffect() || badCondition;
        badCondition = stunned.isInEffect() || badCondition;
        badCondition = bleed.size() > 0 || badCondition;

        return badCondition;
    }

    public void clearBad()
    {
        while(poison.size() > 0  || stunned.size() > 0)
        {
            decrementBad();
        }
    }

    public void cleanBuffs()
    {
        //One for each buff
        attack.clear();
        attackDebuff.clear();
        damage.clear();
        damageDebuff.clear();
        regen.clear();
        poison.clear();
        exhausted.clear();
        stunned.clear();
        bleed.clear();
    }
}
