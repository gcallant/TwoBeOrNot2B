package AttackAndDefendBehavior;

/**
 * Created by SaraPage on 4/29/2016.
 */
public class AttackBehaviorA implements I_Attack
{
	public AttackBehaviorA()
	{
	}

	@Override
	public void performAttack()
	{
		System.out.println("The character performs Attack A");
	}
}
