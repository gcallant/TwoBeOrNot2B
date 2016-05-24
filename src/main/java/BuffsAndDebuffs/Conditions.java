package BuffsAndDebuffs;

import BuffsAndDebuffs.BuffsManager;
import Characters.A_Character;
import PartyManagement.Party;

import java.util.Collections;
import java.util.Random;

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

    public void giveDamageDebuff(double percentage, int rounds, String source)
    {
        buffsManager.addDamageDebuff(percentage, rounds, source);
    }

    public void giveAttackBuff(double percentage, int rounds, String source)
    {
        buffsManager.addAttackBuff(percentage, rounds, source);
    }

    public void giveAttackDebuff(double percentage, int rounds, String source)
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

    public void giveBleedDebuff(double percentage, int rounds, String source)
    {
        buffsManager.addBleedDebuff(percentage, rounds, source);
    }

    public void giveRegenStaticBuff(double value, int rounds, String source)
    {
        buffsManager.addRegenStaticBuff(value, rounds, source);
    }

    public void giveExhaustedDebuff(int rounds, String source)
    {
        buffsManager.addExhaustedDebuff(rounds, source);
    }

    public void giveStunnedDebuff(int rounds, String source)
    {
        buffsManager.addStunnedDebuff(rounds, source);
    }

    public void giveFearedDebuff(int rounds, String source)
    {
        buffsManager.addFearedDebuff(rounds, source);
    }

    public void giveConfusedDebuff(int rounds, String source)
    {
        buffsManager.addConfusedDebuff(rounds, source);
    }

    /*
    * Retrieve Buffs
     */

    private int calculateRegen(int health)
    {
         return (int)((double)health*buffsManager.getRegenAmount());
    }

    private int calculateRegenStatic()
    {
        return (int)buffsManager.getRegenStaticAmount();
    }

    private int calculatePoisonDamage(int health)
    {
        return (int)((double)health*buffsManager.getPoisonAmount());
    }

    private int calculateBleedDamage(int health)
    {
        return (int)((double)health*buffsManager.getBleedAmount());
    }

    private boolean isConfused()
    {
        return buffsManager.isConfused();
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
        boolean cannotAttack = false;

        if( buffsManager.isStunned())
        {
            System.out.println(name + " is stunned and cannot attack!");
            return true;
        }

        if(buffsManager.isFeared())
        {
            if(new Random().nextBoolean())
            {
                System.out.println(name + " is feared and cannot attack!");
                return true;
            }
        }

        return false;
    }

    public boolean cannotUseSpecial()
    {
        return buffsManager.isExhausted();
    }

    public boolean confusedEffect(A_Character person, Party allies, Party enemies)
    {
        if(!isConfused())
        {
            return false;
        }
        Random rand = new Random();
        A_Character newTarget;
        Party toChooseFrom;

        if(rand.nextBoolean())
        {
            toChooseFrom = allies;
        }
        else
        {
            toChooseFrom = enemies;
        }

        newTarget = toChooseFrom.getCharacter(rand.nextInt(toChooseFrom.size()));

        person.attack(newTarget);

        return true;
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

    public int takeTurnDamage(int health)
    {
        int poison, bleed;

        poison = Math.min(50,calculatePoisonDamage(health));
        bleed = Math.min(50,calculateBleedDamage(health));
        if(poison > 0)
        {
            System.out.println(name + " is poisoned and takes " + poison + " damage!");
        }
        if(bleed > 0)
        {
            System.out.println(name + " is bleeding and takes " + bleed + " damage!");
        }

        return poison + bleed;
    }

    public int takeTurnHealing(int health)
    {
        int regen;

        regen = calculateRegen(health) + calculateRegenStatic();
        if(regen > 0)
        {
            System.out.println(name + " has regen and recovers " + regen + " health!");
        }

        return regen;
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
        str += (buffsManager.isConfused()) ? " Confused" : "";
        return str;
    }
}
