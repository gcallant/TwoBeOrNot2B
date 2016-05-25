package BuffsAndDebuffs;

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
    private BooleanBuffList exhausted;
    private BooleanBuffList stunned;
    private BooleanBuffList feared;
    private BooleanBuffList confused;
    private String name;

    public BuffsManager(String name)
    {
        attack = new AttackBuffs(name);
        attackDebuff = new AttackDebuffs(name);
        damage = new DamageBuffs(name);
        damageDebuff = new DamageDebuff(name);
        regen = new RegenBuffs(name);
        poison = new PoisonDebuffs(name);
        bleed = new BleedDebuff(name);
        regenStatic = new RegenStaticBuff(name);
        burn = new BurnDebuff(name);
        exhausted = new ExhaustedDebuffs(name);
        stunned = new StunnedDebuff(name);
        feared = new FearDebuff(name);
        confused = new ConfusionDebuff(name);
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

    public void addBleedDebuff(double buff, int rounds, String source)
    {
        bleed.addBuff(buff, rounds, source);
    }

    public void addBurnDebuff(double buff, int rounds, String source)
    {
        burn.addBuff(buff, rounds, source);
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
        while(poison.size() > 0  || stunned.size() > 0 || bleed.size() > 0 || feared.size() > 0 || confused.size() > 0 || burn.size() > 0)
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
    }
}
