package Buffs;

import Characters.A_Character;
import PartyManagement.Party;
import Utilities.Display;

import java.util.Random;

/*
 * Created by Michael on 5/20/2016.
 */
public class Conditions
{
    private String name;
    private boolean summoningSickness;
    private int maxSummoning;
    private int currentSummoning;
    private boolean isDefendingOther;

    public Conditions(A_Character character)
    {
        this.name = character.getName();
        buffsManager = new BuffsManager(character);
        summoningSickness = false;
        maxSummoning = 1;
        currentSummoning = 0;
    }

    public void summon()
    {
        currentSummoning++;
    }

    public void unsummon()
    {
        currentSummoning--;
    }

    public boolean summoningSickness()
    {
        return currentSummoning >= maxSummoning;
    }

    private boolean defended;

    public void defend()
    {
        defended = true;
    }

    public void defendOther()
    {
        isDefendingOther = true;
    }

    public void stopDefendOther()
    {
        isDefendingOther = false;
    }

    public boolean isDefendingOther()
    {
        return isDefendingOther;
    }

    public int reduceDamage(int damage, boolean test)
    {
        damage = (int)((double)damage/buffsManager.getDamageReductionAmount());
        if(defended)
        {
            damage = damage/2;
        }
        if(!test)
        {
            if (buffsManager.isDefended() && damage > 0)
            {
                int total = damage - (int) buffsManager.getDefendAmount();

                if (total > 0)
                {
                    Display.displayMessage(buffsManager.getDefendedContributor().getName() + " defended " + name + " for " + (int)buffsManager.getDefendAmount());
                    buffsManager.getDefendedContributor().takeDamage((int) buffsManager.getDefendAmount());
                }
                else
                {
                    Display.displayMessage(buffsManager.getDefendedContributor().getName() + " defended " + name + " for " + (int)(buffsManager.getDefendAmount() + total));
                    buffsManager.getDefendedContributor().takeDamage((int) buffsManager.getDefendAmount() + total);
                }
                damage = Math.max(0, total);
            }
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
        buffsManager.addAttackDebuff(percentage, rounds, source);
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

    public void giveBurnDebuff(double value, int rounds, String source)
    {
        buffsManager.addBurnDebuff(value, rounds, source);
    }

    public void giveDamageReductionBuff(double percentage, int rounds, String source)
    {
        buffsManager.addDamageReductionBuff(percentage, rounds, source);
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

    public void giveDefendedStatus(A_Character contributor, double amount, int rounds, String source)
    {
        buffsManager.addDefendedBuff(contributor, amount, rounds, source);
    }

    public void giveChargeBuff(double multiplier, int rounds, String source)
    {
        buffsManager.addChargeBuff(multiplier, rounds, source);
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

    private int calculateBurnDamage()
    {
        return (int)buffsManager.getBurnAmount();
    }

    private boolean isConfused()
    {
        return buffsManager.isConfused();
    }

    public boolean isBleeding()
    {
        return buffsManager.getBleedAmount() > 0;
    }

    public int calculateDamage(int damage)
    {
        damage = (int)(((double)damage*buffsManager.getDamageBuffAmount()));
        damage = (int)(((double)damage*buffsManager.getChargeAmount()));
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
            Display.displayMessage(name + " is stunned and cannot attack!");
            return true;
        }

        if(buffsManager.isFeared())
        {
            if(new Random().nextBoolean())
            {
                Display.displayMessage(name + " is feared and cannot attack!");
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

    public int takeTurnDamage(int maxHealth, int health)
    {
        int poison, bleed, burn;

        poison = Math.min(50,calculatePoisonDamage(maxHealth));
        bleed = Math.min(50,calculateBleedDamage(health));
        burn = calculateBurnDamage();
        if(poison > 0)
        {
            Display.displayMessage(name + " is poisoned and takes " + poison + " damage!");
        }
        if(bleed > 0)
        {
            Display.displayMessage(name + " is bleeding and takes " + bleed + " damage!");
        }
        if(burn > 0)
        {
            Display.displayMessage(name + " is burning and takes " + burn + " damage!");
        }

        return poison + bleed + burn;
    }

    public int takeTurnHealing(int health)
    {
        int regen;

        regen = calculateRegen(health) + calculateRegenStatic();
        if(regen > 0)
        {
            Display.displayMessage(name + " has regen and recovers " + regen + " health!");
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
        str += (reduceDamage(100, true) != 100) ? " Damage Reduction " + reduceDamage(100, true) + "%": "";
        str += (buffsManager.isExhausted()) ? " Exhausted": "";
        str += (buffsManager.isStunned()) ? " Stunned": "";
        str += (buffsManager.getPoisonAmount() != 0) ? " Poisoned" : "";
        str += (buffsManager.isFeared()) ? " Feared": "";
        str += (buffsManager.getBurnAmount() != 0) ? " Burning" : "";
        str += (buffsManager.getRegenAmount() != 0) ? " Regen" : "";
        str += (buffsManager.isConfused()) ? " Confused" : "";
        str += (buffsManager.getBleedAmount() != 0) ? " Bleeding" : "";
        str += (summoningSickness()) ? " Summoning Sickness" : "";
        return str;
    }
}
