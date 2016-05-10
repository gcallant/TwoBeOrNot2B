package AttackAndDefendBehavior;

/**
 * Created by SaraPage on 4/29/2016.
 */
public class RangedAttack implements I_Attack
{
	public RangedAttack()
	{
	}

	@Override
	public void performAttack()
	{
		System.out.println("The character performs Attack B");
	}
}
