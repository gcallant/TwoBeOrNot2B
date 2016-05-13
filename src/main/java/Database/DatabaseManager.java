package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import Character.*;
import Inventory.Inventory;
import Item.Equipable;
import Mediator.Mediator;

import java.sql.Statement;

/**
 * Created by Grant Callant on 5/12/2016. Manages an SQLlite database object called database- constructor will create
 * database if it does not exist, or connect to existing database.
 *
 * @author Grant Callant
 */
public class DatabaseManager
{
	private static final String     DATABASE          = "jdbc:sqlite:storedInformation";
	private              Connection databaseConnector = null;
	private              Statement  sqlStatement      = null;

	public DatabaseManager()
	{
		try
		{
			Class.forName("org.sqlite.JDBC");
			databaseConnector = DriverManager.getConnection(DATABASE);
			createTables();
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}

	private void createTables() throws SQLException
	{
		sqlStatement = databaseConnector.createStatement();
		String statement = "CREATE TABLE CHARACTERS(" +
				                     "NAME TEXT PRIMARY KEY NOT NULL," +
				                     " HEALTH INT NOT NULL," +
				                     " STRENGTH INT NOT NULL," +
				                     " DEXTERITY INT NOT NULL," +
				                     " SPEED INT NOT NULL," +
				                     " ARMOR INT NOT NULL)";

		sqlStatement.executeUpdate(statement);

		//statement = "CREATE TABLE ";

		sqlStatement.close();
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
}
