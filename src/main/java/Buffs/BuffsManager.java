package Buffs;

import BuffsBoolean.*;
import BuffsCharacter.CharacterBuffManage;
import BuffsCharacter.DefendOther;
import BuffsPercentage.*;
import Characters.A_Character;

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
    private BuffList regenStatic;
    private BuffList burn;
    private BuffList damageReduction;
    private BuffList charge;
    private BooleanBuffList exhausted;
    private BooleanBuffList stunned;
    private BooleanBuffList feared;
    private BooleanBuffList confused;
    private CharacterBuffManage defended;
    private String name;

    public BuffsManager(A_Character name)
    {
        attack = new AttackBuffs(name.getName());
        attackDebuff = new AttackDebuffs(name.getName());
        damage = new DamageBuffs(name.getName());
        damageDebuff = new DamageDebuff(name.getName());
        regen = new RegenBuffs(name.getName());
        poison = new PoisonDebuffs(name.getName());
        bleed = new BleedDebuff(name.getName());
        regenStatic = new RegenStaticBuff(name.getName());
        burn = new BurnDebuff(name.getName());
        exhausted = new ExhaustedDebuffs(name.getName());
        stunned = new StunnedDebuff(name.getName());
        feared = new FearDebuff(name.getName());
        confused = new ConfusionDebuff(name.getName());
        damageReduction = new DamageReductionBuff(name.getName());
        charge = new ChargeBuff(name.getName());
        defended = new DefendOther(name);
        this.name = name.getName();
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

    public void addBleedDebuff(double buff, int rounds, String source)
    {
        bleed.addBuff(buff, rounds, source);
    }

    public void addBurnDebuff(double buff, int rounds, String source)
    {
        burn.addBuff(buff, rounds, source);
    }

    public void addDamageReductionBuff(double buff, int rounds, String source)
    {
        damageReduction.addBuff(buff, rounds, source);
    }

    public void addChargeBuff(double buff, int rounds, String source)
    {
        charge.addBuff(buff, rounds, source);
    }

    public void addExhaustedDebuff(int rounds, String source)
    {
        exhausted.addBuff(rounds, source);
    }

    public void addStunnedDebuff(int rounds, String source)
    {
        stunned.addBuff(rounds, source);
    }

    public void addFearedDebuff(int rounds, String source)
    {
        feared.addBuff(rounds, source);
    }

    public void addConfusedDebuff(int rounds, String source)
    {
        confused.addBuff(rounds, source);
    }

    public void addRegenStaticBuff(double buff, int rounds, String source)
    {
        regenStatic.addBuff(buff, rounds, source);
    }

    public void addDefendedBuff(A_Character contributor, double amount, int rounds, String source)
    {
        defended.addBuff(contributor, amount, rounds, source);
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

        feared.decrementList();

        confused.decrementList();

        regenStatic.decrementList();

        burn.decrementList();

        damageReduction.decrementList();

        defended.decrementList();

        charge.decrementList();
    }

    public void decrementBad()
    {
        //All negative buffs except Exhaustion.
        poison.decrementList();
        stunned.decrementList();
        bleed.decrementList();
        feared.decrementList();
        confused.decrementList();
        burn.decrementList();
    }

    //One for every BuffList
    public double getDamageBuffAmount()
    {
        return getTotal(damage, damageDebuff);
    }

    public double getAttackBuffAmount()
    {
        return getTotal(attack, attackDebuff);
    }

    private double getTotal(BuffList buffList1, BuffList buffList2)
    {
        double total = 1.0;
        total += buffList1.getAmount();
        total += buffList2.getAmount();

        if(total < .50)
        {
            total = .50;
        }
        if(total > 2.0)
        {
            total = 2.0;
        }

        return total;
    }

    public double getRegenAmount()
    {
        return regen.getAmount();
    }

    public double getPoisonAmount()
    {
        return poison.getAmount();
    }

    public double getBleedAmount()
    {
        return bleed.getAmount();
    }

    public double getRegenStaticAmount()
    {
        return regenStatic.getAmount();
    }

    public double getBurnAmount()
    {
        return burn.getAmount();
    }

    public double getDamageReductionAmount()
    {
        return damageReduction.getAmount();
    }

    public double getChargeAmount()
    {
        return charge.getAmount();
    }

    public boolean isExhausted()
    {
        return exhausted.isInEffect();
    }

    public boolean isStunned()
    {
        return stunned.isInEffect();
    }

    public boolean isFeared()
    {
        return feared.isInEffect();
    }

    public boolean isConfused()
    {
        return confused.isInEffect();
    }

    public boolean isDefended()
    {
        return defended.isInEffect();
    }

    public double getDefendAmount()
    {
        if(isDefended())
        {
            return defended.getAmount();
        }
        else
        {
            return 0;
        }
    }
    //

    //For Character Buffs
    public A_Character getDefendedContributor()
    {
        return defended.getContributor();
    }
    //

    public boolean badCondition()
    {
        boolean badCondition = false;

        badCondition = poison.size() > 0 || badCondition;
        badCondition = bleed.size() > 0 || badCondition;
        badCondition = exhausted.isInEffect() || badCondition;
        badCondition = stunned.isInEffect() || badCondition;
        badCondition = feared.isInEffect() || badCondition;
        badCondition = confused.isInEffect() || badCondition;
        badCondition = burn.size() > 0 || badCondition;

        return badCondition;
    }

    public void clearBad()
    {
        while(poison.size() > 0  || stunned.size() > 0 || bleed.size() > 0 ||
                feared.size() > 0 || confused.size() > 0 || burn.size() > 0)
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
        feared.clear();
        confused.clear();
        regenStatic.clear();
        burn.clear();
        damageReduction.clear();
        defended.clear();
        charge.clear();
    }
}
