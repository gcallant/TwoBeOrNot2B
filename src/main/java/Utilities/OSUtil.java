package Utilities;

import org.jetbrains.annotations.Contract;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;

/**
 * Created by Grant Callant on 5/14/2016. A collection of static utilities that simplify working with the operating
 * system.
 *
 * @author Grant Callant
 */
public class OSUtil
{

	private static final Logger logger             = LoggerFactory.getLogger("OSUtil");
	/**
	 * Method to get the parent directory of where the program is currently being run from.
	 *
	 * @return parent directory as a File
	 * @throws OSException if parent directory could not be opened
	 */

	private static       String SEPARATOR          = setSeparator();
	private static       File   EXTERNAL_DIRECTORY = null;

	private static String setSeparator()
	{
		String separator = FileSystems.getDefault().getSeparator();

		if(separator.compareTo("\\") == 0)
		{
			separator = "\\\\";
		}
		return separator;
	}
//
//	@Nullable
//	private static LoggingManager waitForLoggerConfiguration()
//	{
//		if(logger.isConfigured())
//		{
//			return logger = LoggingManager.getInstance();
//		}
//		return null;
//	}

	public static File getParentDirectory() throws OSException
	{
		File cwd = new File(System.getProperty("user.dir"));


			logger.info("Got Current directory {}", cwd);

		File parent = new File(cwd.getParent());
		if(parent == null || parent.isFile())
		{
			throw new OSException("Couldn't get parent directory");
		}


			logger.info("Got Parent directory {}", parent);

		return parent;
	}

	public static File getCurrentDirectory()
	{
		File cwd = new File(System.getProperty("user.dir"));
		return cwd;
	}

	public static File getExternalDirectory()
	{
		return EXTERNAL_DIRECTORY;
	}

	@Contract("null -> fail")
	public static void setExternalDirectory(File externalDirectory) throws OSException
	{
		if(externalDirectory != null)
		{
			//Can only be set to a directory in the parent directory- but can't be the parent or the cwd
			if(! externalDirectory.equals(getCurrentDirectory()) && ! externalDirectory.equals(getParentDirectory()) &&
					     externalDirectory.toString().startsWith(getParentDirectory().toString()))
			{
				EXTERNAL_DIRECTORY = externalDirectory;
			}

			else
			{
				throw new OSException("External Directory cannot be parent or current directory");
			}
		}
		else
		{
			throw new OSException("External Directory cannot be null");
		}
	}

	public static boolean pathExists(String path)
	{
		File pathToCheck = new File(path);
		return pathToCheck.exists();
	}

	public static File createNewDirectory(File parentDirectory, String newDirectoryName) throws OSException
	{
		verifyDirectory(parentDirectory, newDirectoryName);

			logger.info("Attempting to create new directory {} in {}",
			                        newDirectoryName, parentDirectory);

		File newDirectory = new File(parentDirectory.getAbsolutePath() + SEPARATOR + newDirectoryName);
		newDirectory.mkdir();
		if(! newDirectory.exists())
		{
			throw new OSException(new Throwable("Could not create new directory"));
		}

			logger.info("Successfully created new directory {} in {}", newDirectory, parentDirectory);

		return newDirectory;
	}

	public static File createNewFile(File parentDirectory, String newFileName) throws OSException, IOException
	{
		verifyFile(parentDirectory, newFileName);
		logger.info("Attempting to create new file {} in {}",
		            newFileName, parentDirectory);

		File newFile = new File(parentDirectory.getAbsolutePath() + SEPARATOR + newFileName);
		newFile.createNewFile();
		logger.info("Successfully created new file {} in {}", newFile, parentDirectory);
		return newFile;
	}

	@Contract("null, _ -> fail; !null, null -> fail")
	private static void verifyFile(File parentDirectory, String newFileName) throws OSException
	{
		if(parentDirectory == null || newFileName == null || newFileName.isEmpty())
		{
			throw new OSException("Directory specified is null, or the provided file name is empty");
		}
	}

	public static String getSeparator()
	{
		return SEPARATOR;
	}

	@Contract("null, _ -> fail; !null, null -> fail")
	private static void verifyDirectory(File parentDirectory, String newDirectoryName) throws OSException
	{
		if(parentDirectory == null || newDirectoryName == null || newDirectoryName.isEmpty())
		{
			throw new OSException(new Throwable("Parent directory or new directory is null."));
		}

		if(! parentDirectory.exists() || parentDirectory.isFile())
		{
			throw new OSException(new Throwable("Parent directory does not exist or is not a valid directory."));
		}

		File newDirectory = new File(parentDirectory.getAbsolutePath() + SEPARATOR + newDirectoryName);
				if(newDirectory.exists())
				{
					logger.info("Tried to create directory {}, but it already exists", newDirectory.getAbsolutePath());
					return;
				}

		if(! parentDirectory.canWrite())
		{
			throw new OSException(new Throwable("Access Denied: Cannot write to this directory!")).writeException();
		}
	}
}
