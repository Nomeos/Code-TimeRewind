package model.databaseManager;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.security.NoSuchAlgorithmException;
import java.sql.Statement;

import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import lombok.Getter;
import lombok.Setter;
import model.account.Account;

@Getter
@Setter
public class DatabaseAccountManagerTest {

	private DatabaseAccountManager jm;
	private Account account;
	private Session session;
	private String sqlQuery;

	File file;

	@Before
	public void Init() {
		this.jm = new DatabaseAccountManager();
		this.account = new Account();
		this.jm.DatabaseCreation();
		this.setSession(jm.OpenDatabaseConnection());
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
		this.setSession(this.jm.OpenDatabaseConnection());
		this.jm.DeleteDatabase();
		this.jm = null;

		this.setSession(null);

	}
}
