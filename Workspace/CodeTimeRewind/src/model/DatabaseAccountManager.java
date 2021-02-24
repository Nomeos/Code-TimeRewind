package model;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import main.Game;

public class DatabaseAccountManager {

	private String connectionStatement;
	private String sqlQuery;
	private boolean isRegister = false;
	private Connection connection;
	private Statement statement;

	// Constructor of the JsonManager class
	public DatabaseAccountManager() {
	}

	public void DatabaseCreation() {
		try {
			this.connectionStatement = "jdbc:derby:codetimerewinddb;create=true";
			this.connection = DriverManager.getConnection(this.connectionStatement);
			this.statement = this.connection.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		CreateAllTables();
	}

	public void CreateAllTables() {
		try {
			this.sqlQuery = "Create Table Accounts (Account_Id int not null generated always as identity,"
					+ "Username varchar(12), Password_Hash varchar(128),Account_Level int,"
					+ "PRIMARY KEY (Account_Id))";
			this.statement.executeUpdate(this.sqlQuery);
		} catch (SQLException e) {
			if (e.getSQLState().equals("42Y55") || e.getSQLState().equals("42X05") || e.getSQLState().equals("X0Y32")) {

			} else {
				e.printStackTrace();
			}
		}
		try {
			this.sqlQuery = "Create Table Characters (Character_Id int not null generated always as identity,"
					+ "Name varchar(12), Health int,Defense Int,Attack int,Speed int," + "PRIMARY KEY (Character_Id))";
			this.statement.executeUpdate(this.sqlQuery);
		} catch (SQLException e) {
			if (e.getSQLState().equals("42Y55") || e.getSQLState().equals("42X05") || e.getSQLState().equals("X0Y32")) {
			} else {
				e.printStackTrace();
			}
		}
		try {
			this.sqlQuery = "Create Table Account_Own_Characters (Account_Own_Character_Id int not null generated always as identity,"
					+ "Character_Id int, Account_Id int, Level int," + "PRIMARY KEY (Account_Own_Character_Id),"
					+ "FOREIGN KEY (Character_Id) REFERENCES Characters(Character_Id),"
					+ "FOREIGN KEY (Account_Id) REFERENCES Accounts(Account_Id))";
			this.statement.executeUpdate(this.sqlQuery);
		} catch (SQLException e) {
			if (e.getSQLState().equals("42Y55") || e.getSQLState().equals("42X05") || e.getSQLState().equals("X0Y32")) {
			} else {
				e.printStackTrace();
			}
		}
	}

	public Statement OpenDatabaseConnection() {

		try {

			this.connectionStatement = "jdbc:derby:C:/Projet TPI/Code-TimeRewind/Workspace/CodeTimeRewind/codetimerewinddb";
			this.connection = DriverManager.getConnection(this.connectionStatement);
			this.statement = this.connection.createStatement();
			return this.statement;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

	public void CloseDatabaseConnection() {
		try {
			this.connection.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	// Principal method for check if the user can register
	public boolean RegisterAccount(Account userAccount) {
		this.isRegister = true;
		if (IsTheUserAlreadyExist(userAccount)) {
			CloseDatabaseConnection();
			return false;
		} else {
			InsertAccountInDatabase(userAccount);
			CloseDatabaseConnection();
			return true;
		}
	}

	// Principal method for check if the user can login
	public boolean LoginAccount(Account userAccount) {
		this.isRegister = false;
		if (IsTheUserAlreadyExist(userAccount)) {
			CloseDatabaseConnection();
			return true;
		} else {
			CloseDatabaseConnection();
			return false;
		}
	}

	public void InsertAccountInDatabase(Account userAccount) {
		this.sqlQuery = "INSERT INTO Accounts (Username,Password_Hash,Account_Level) values('"
				+ userAccount.getUsername() + "','" + userAccount.getPasswordHash() + "',1)";
		Statement statement = OpenDatabaseConnection();
		try {
			statement.executeUpdate(sqlQuery);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	// Check if the user already exist in the json, it returns a boolean (works with
	// login and register).
	public boolean IsTheUserAlreadyExist(Account userAccount) {
		boolean result = false;
		if (isRegister) {
			this.sqlQuery = "SELECT * FROM Accounts WHERE Username = '" + userAccount.getUsername() + "'";
			Statement statement = OpenDatabaseConnection();
			ResultSet resultQuery;
			try {
				resultQuery = statement.executeQuery(sqlQuery);
				while (resultQuery.next()) {
					result = true;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {

			this.sqlQuery = "SELECT * FROM Accounts WHERE Username = '" + userAccount.getUsername() + "' ";
			Statement statement = OpenDatabaseConnection();
			ResultSet resultQuery;
			try {
				resultQuery = statement.executeQuery(sqlQuery);
				while (resultQuery.next()) {
					if(userAccount.areThePasswordEquals(resultQuery.getString("Password_hash"))){
						resultQuery.getString("Username");
						userAccount.setAccount_Level(resultQuery.getInt("Account_Level"));
						
						result = true;
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if (Game.getInstance() != null) {
				Game.getInstance().setPlayerAccount(userAccount);
			}

		}
		return result;

	}

	/*
	 * public void DropTables() { try { String sqlQuery =
	 * "Drop table Account_Own_Characters"; statement.executeUpdate(sqlQuery); }
	 * catch (SQLException e) { if (e.getSQLState().equals("42Y55") ||
	 * e.getSQLState().equals("42X05")) {
	 * 
	 * } else { e.printStackTrace(); } } try { String sqlQuery =
	 * "Drop table Accounts"; statement.executeUpdate(sqlQuery); } catch
	 * (SQLException e) { if (e.getSQLState().equals("42Y55") ||
	 * e.getSQLState().equals("42X05")) {
	 * 
	 * } else { e.printStackTrace(); } } try { String sqlQuery =
	 * "Drop table Characters"; statement.executeUpdate(sqlQuery); } catch
	 * (SQLException e) { if (e.getSQLState().equals("42Y55") ||
	 * e.getSQLState().equals("42X05")) {
	 * 
	 * } else { e.printStackTrace(); } } }
	 * 
	 * /* // Update the user account with the new information. public String
	 * UpdateUserAccount(Account oldAccount) {
	 * 
	 * for (Account currentAccount : listOfAccount) { if (oldAccount.getUsername()
	 * == currentAccount.getUsername()) {
	 * currentAccount.setAccount_Level(oldAccount.getAccount_Level()); } } return
	 * null; }
	 */

}
