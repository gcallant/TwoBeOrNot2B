package Logging;

import org.jetbrains.annotations.Contract;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Grant Callant on 5/14/2016. GitHub
 *
 * @author Grant Callant
 */
public class LoggingManager
{
	final static Logger logger = LoggerFactory.getLogger(LoggingManager.class);

	private LoggingManager() //Prevents Instantiation
	{

	}

	@Contract(pure = true)
	public static LoggingManager getInstance()
	{
		return LoggerSingleton.INSTANCE;
	}

	private static class LoggerSingleton
	{
		private static final LoggingManager INSTANCE = new LoggingManager();
	}
}
