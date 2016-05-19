package Characters;

/**
 * Created by Michael on 5/16/2016.
 */
public enum ConstantValues
{
    RandomInitiative(10), ChanceToHit(5), RandomDamage(10);

    private int value;

    private ConstantValues(int value)
    {
        this.value = value;
    }

    public int getValue()
    {
        return this.value;
    }
}
