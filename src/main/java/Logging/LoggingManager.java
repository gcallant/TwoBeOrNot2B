package Logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Grant Callant on 5/14/2016.
 *
 * @author Grant Callant
 */
public class LoggingManager
{
	private static Logger logger = null;

	public LoggingManager(Class callingClass)
	{
		logger = LoggerFactory.getLogger(callingClass);
	}

	public LoggingManager(String callingClass)
	{
		logger = LoggerFactory.getLogger(callingClass);
	}

	public static Logger getLogger()
	{
		return logger;
	}
}
