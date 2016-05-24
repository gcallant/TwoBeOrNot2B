package Logging;

import org.jetbrains.annotations.Contract;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Grant Callant on 5/14/2016.
 *
 * @author Grant Callant
 */
public class LoggingManager
{
	private static boolean isLoggerConfigured = false;
	private        Logger  logger             = null;

	private LoggingManager(Class callingClass)
	{
		logger = LoggerFactory.getLogger(callingClass);
	}

	private LoggingManager(String callingClass)
	{
		logger = LoggerFactory.getLogger(callingClass);
	}

	@Contract(pure = true)
	public static LoggingManager getInstance()
	{
		return LoggerHolder.INSTANCE;
	}

	public static boolean isConfigured()
	{
		return isLoggerConfigured;
	}

	static void setConfigured(final boolean loggerIsConfigured)
	{
		isLoggerConfigured = loggerIsConfigured;
	}

	public Logger getLogger()
	{
		return logger;
	}

	private static class LoggerHolder
	{
		private static final LoggingManager INSTANCE = new LoggingManager("LoggingManager");
	}
}
