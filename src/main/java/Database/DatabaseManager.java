package Database;

import Mediator.Mediator;
import Utilities.OSUtil;
import org.jetbrains.annotations.Contract;
import org.sqlite.SQLiteConfig;

import java.sql.*;

/**
 * Created by Grant Callant on 5/12/2016. Manages an SQLite database object called database- constructor will create
 * database if it does not exist, or connect to existing database.
 *
 * @author Grant Callant
 */
public class DatabaseManager
{
	private static final String     DRIVER            = "org.sqlite.JDBC";
	private static final String     DATABASE          =
			  "jdbc:sqlite:" + OSUtil.getExternalDirectory().toString() +
						 OSUtil.getSeparator() + "DungeonCrawler.db";
	private              Connection databaseConnector = null;
	private              Statement  sqlStatement      = null;

	private DatabaseManager()
	{
		try
		{
			SQLiteConfig sqLiteConfig = new SQLiteConfig();
			sqLiteConfig.enforceForeignKeys(true);
			Class.forName(DRIVER);
			databaseConnector = DriverManager.getConnection(DATABASE, sqLiteConfig.toProperties());

		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		createTables();
	}

	@Contract(pure = true)
	public static DatabaseManager getInstance()
	{
		return DatabaseSingle.INSTANCE;
	}

	private void createTables()
	{
		try
		{
			sqlStatement = databaseConnector.createStatement();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}

		String statement = "";

		if(! tableIsPresent("CHARACTERS"))
		{
			statement = "CREATE TABLE CHARACTERS(" +
					              "NAME TEXT PRIMARY KEY NOT NULL," +
					              " HEALTH INT NOT NULL," +
					              " STRENGTH INT NOT NULL," +
					              " DEXTERITY INT NOT NULL," +
					              " SPEED INT NOT NULL," +
					              " ARMOR BLOB NOT NULL," +
					              " WEAPON BLOB NOT NULL);";

			try
			{
				sqlStatement.executeUpdate(statement);
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		}

		if(! tableIsPresent("INVENTORY"))
		{
			statement = "CREATE TABLE INVENTORY(" +
					              "ITEMID INT PRIMARY KEY NOT NULL," +
					              " ITEMTYPE TEXT NOT NULL," +
					              " OWNER TEXT NOT NULL," +
					              " FOREIGN KEY(OWNER) REFERENCES CHARACTERS(NAME)" +
					              ");";

			try
			{
				sqlStatement.close();
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
	}

	private boolean tableIsPresent(String tableName)
	{
		try
		{
			DatabaseMetaData metaData = databaseConnector.getMetaData();
			ResultSet resultSet = metaData.getTables(null, null, tableName, null);
			if(resultSet != null)
			{ return true; }
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return false;
	}

	public void loadParty(Mediator mediator)
	{

	}

	public void loadInventory(Mediator mediator)
	{

	}

	public void saveParty(Mediator mediator)
	{

	}

	public void saveInventory(Mediator mediator)
	{

	}

	public void closeConnection()
	{
		try
		{
			databaseConnector.close();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}

	private static class DatabaseSingle
	{
		private static final DatabaseManager INSTANCE = new DatabaseManager();
	}
}
