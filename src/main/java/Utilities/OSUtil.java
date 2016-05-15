package Utilities;

import Logging.LoggingManager;

import java.io.File;

/**
 * Created by Grant Callant on 5/14/2016. A collection of static utilities that simplify working with the operating
 * system.
 *
 * @author Grant Callant
 */
public class OSUtil
{
	private static LoggingManager logger = new LoggingManager("OSUtil");

	public static File getParentDirectory() throws OSException
	{
		logger.getLogger().info("Attempting to get parent Directory");
		File cwd = new File(System.getProperty("user.dir"));
		logger.getLogger().info("Got Current directory {}", cwd);
		File parent = new File(cwd.getParent());
		if(parent == null)
		{
			throw new OSException("Couldn't get parent directory");
		}
		logger.getLogger().info("Got Parent directory {}", parent);
		return parent;
	}
}
