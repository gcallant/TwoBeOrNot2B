package Database;

import Characters.A_Character;
import Inventory.Inventory;
import Item.Equipable;
import Mediator.Mediator;
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
	private static final String     DATABASE          = "jdbc:sqlite:storedInformation";
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

		String statement = "CREATE TABLE CHARACTERS(" +
				                     "NAME TEXT PRIMARY KEY NOT NULL," +
				                     " HEALTH INT NOT NULL," +
				                     " STRENGTH INT NOT NULL," +
				                     " DEXTERITY INT NOT NULL," +
				                     " SPEED INT NOT NULL," +
				                     " ARMOR INT NOT NULL" +
				                     ");";

		try
		{
			sqlStatement.executeUpdate(statement);
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}

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

	public A_Character loadParty(Mediator mediator)
	{
		return null;
	}

	public Inventory loadInventory(Mediator mediator)
	{
		return null;
	}

	public Equipable loadEquipment(Mediator mediator)
	{
		return null;
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
