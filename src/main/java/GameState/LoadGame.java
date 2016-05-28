package GameState;

/**
 * Created by Grant Callant on 5/25/16.
 */
public class LoadGame implements I_State
{
	private Mediator mediator = null;

	public LoadGame(Mediator mediator)
	{
		if(mediator != null)
		{
			this.mediator = mediator;
		}
	}

	@Override
	public String display()
	{
		return "\nPlease wait while we retrieve your saved game.\n";
	}

	@Override
	public I_State execute()
	{
		return null;
	}

	@Override
	public boolean isEndOfGame()
	{
		return false;
	}
}
