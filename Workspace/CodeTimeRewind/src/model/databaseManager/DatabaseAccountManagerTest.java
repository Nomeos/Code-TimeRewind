package model.databaseManager;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.security.NoSuchAlgorithmException;
import java.sql.Statement;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.newdawn.slick.SlickException;

import junit.framework.Assert;
import model.account.Account;

public class DatabaseAccountManagerTest {

	private DatabaseAccountManager jm;
	private Account account;
	private Statement statement;
	private String sqlQuery;

	File file;

	@Before
	public void Init() {
		this.jm = new DatabaseAccountManager();
		this.account = new Account();
		this.jm.DatabaseCreation();
		this.setStatement(jm.OpenDatabaseConnection());
		this.jm.CloseDatabaseConnection();

	}
	@Test 
	public void DatabaseConnection(){
		this.jm.OpenDatabaseConnection();
	}


	@Test
	public void IsTheUserAlreadyExistFalse() {
		try {
			this.jm.DeleteDatabase();
			this.jm.DatabaseCreation();
			Account account = new Account();
			account.setUsername("test");
			account.setPassword("test");
			account.setPasswordHash(account.hashPassword("test"));
			assertTrue(!this.jm.IsTheUserAlreadyExist(account));

		} catch (NoSuchAlgorithmException e) {

		}
	}

	@Test
	public void InsertTheFirstLevel() {
		this.jm.OpenDatabaseConnection();
		assertTrue(this.jm.InsertTheFirstLevel());
	}

	@Test
	public void InsertAllDifferentCharacters() {
		this.jm.OpenDatabaseConnection();
		assertTrue(this.jm.InsertAllDifferentCharacters());
	}

	@After
	public void Clean() {
		this.account = null;
		this.setStatement(this.jm.OpenDatabaseConnection());
		this.jm.DeleteDatabase();
		this.jm = null;

		this.setStatement(null);

	}

	public Statement getStatement() {
		return statement;
	}

	public void setStatement(Statement statement) {
		this.statement = statement;
	}

	public String getSqlQuery() {
		return sqlQuery;
	}

	public void setSqlQuery(String sqlQuery) {
		this.sqlQuery = sqlQuery;
	}
}
