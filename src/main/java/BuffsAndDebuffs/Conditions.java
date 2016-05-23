package BuffsAndDebuffs;

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

    /*
    * BUFFS MANAGER
     */

    private BuffsManager buffsManager;

    /*
    * Give Buffs
     */

    public void giveDamageBuff(double percentage, int rounds, String source)
    {
        buffsManager.addDamageBuff(percentage, rounds, source);
    }

    public void giveAttackBuff(double percentage, int rounds, String source)
    {
        buffsManager.addAttackBuff(percentage, rounds, source);
    }

    public void giveRegenBuff(double percentage, int rounds, String source)
    {
        buffsManager.addRegenBuff(percentage, rounds, source);
    }

    public void givePoisonDebuff(double percentage, int rounds, String source)
    {
        buffsManager.addPoisonDebuff(percentage, rounds, source);
    }

    public void giveExhaustedDebuff(int rounds, String source)
    {
        buffsManager.addExhaustedDebuff(rounds, source);
    }

    public void giveStunnedDebuff(int rounds, String source)
    {
        buffsManager.addStunnedDebuff(rounds, source);
    }

    public void giveAttackDebuff(double percentage, int rounds, String source)
    {
        buffsManager.addAttackBuff(percentage, rounds, source);
    }

    public void giveDamageDebuff(double percentage, int rounds, String source)
    {
        buffsManager.addDamageDebuff(percentage, rounds, source);
    }

    /*
    * Retrieve Buffs
     */

    public int calculateRegen(int health)
    {
         return (int)((double)health*buffsManager.getRegenAmount());
    }

    public int calculatePoisonDamage(int health)
    {
        return (int)((double)health*buffsManager.getPoisonAmount());
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

    public boolean cannotAttack()
    {
        return buffsManager.isStunned();
    }

    public boolean cannotUseSpecial()
    {
        return buffsManager.isExhausted();
    }

    public boolean hasBadCondition()
    {
        return buffsManager.badCondition();
    }

    public void recoverConditions()
    {
        buffsManager.clearBad();
    }

    public void resetConditions()
    {
        buffsManager.cleanBuffs();
    }

    public void startTurn()
    {

    }

    public void endTurn()
    {
        buffsManager.decrement();
        additionalDamage = 0;
        additionalAttack = 0;
    }

    public void decrementBadConditions()
    {
        buffsManager.decrementBad();
    }

    private void endDefending()
    {
        defended = false;
    }

    public String displayStats()
    {
        String str = "";
        str += (calculateAttack(100) != 100) ? " Attack " + calculateAttack(100) + "%": "";
        str += (calculateDamage(100) != 100) ? " Damage " + calculateDamage(100) + "%": "";
        str += (buffsManager.isExhausted()) ? " Exhausted": "";
        str += (buffsManager.isStunned()) ? " Stunned": "";
        str += (buffsManager.getPoisonAmount() != 0) ? " Poisoned" : "";
        str += (buffsManager.getRegenAmount() != 0) ? " Regen" : "";
        return str;
    }
}
