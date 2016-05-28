package Buffs;

import Characters.A_Character;
import Utilities.Display;

/**
 * Created by Michael on 5/23/2016.
 */
public class UndeadConditions extends Conditions
{
    private String name;

    public UndeadConditions(A_Character character)
    {
        super(character);
        this.name = character.getName();
    }

    public void giveStunnedDebuff(int rounds, String source)
    {
        Display.displayMessage(name + " is immune to stun!");
    }

    public void giveBleedDebuff(double percentage, int rounds, String source)
    {
        Display.displayMessage(name + " is immune to bleed!");
    }

    public void givePoisonDebuff(double percentage, int rounds, String source)
    {
        Display.displayMessage(name + " is immune to poison!");
    }
}
