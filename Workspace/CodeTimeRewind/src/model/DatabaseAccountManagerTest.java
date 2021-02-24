package model;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
		this.statement = jm.OpenDatabaseConnection();
		try {
			this.sqlQuery = "Drop table Account_Own_Characters";
			this.statement.executeUpdate(this.sqlQuery);
		} catch (SQLException e) {
			if (e.getSQLState().equals("42Y55") || e.getSQLState().equals("42X05")) {

			} else {
				e.printStackTrace();
			}
		}
		try {
			this.sqlQuery = "Drop table Accounts";
			this.statement.executeUpdate(this.sqlQuery);
		} catch (SQLException e) {
			if (e.getSQLState().equals("42Y55") || e.getSQLState().equals("42X05")) {

			} else {
				e.printStackTrace();
			}
		}
		try {
			this.sqlQuery = "Drop table Characters";
			this.statement.executeUpdate(this.sqlQuery);
		} catch (SQLException e) {
			if (e.getSQLState().equals("42Y55") || e.getSQLState().equals("42X05")) {

			} else {
				e.printStackTrace();
			}
		}
		this.jm.DatabaseCreation();
		this.jm.CloseDatabaseConnection();

	}

	@Test
	public void HashPasswordTest() throws NoSuchAlgorithmException {
		account.hashPassword("test");
	}

	@Test
	public void RegisterOneAccount() throws NoSuchAlgorithmException {
		this.account.setUsername("Jason");
		this.account.setPasswordHash(this.account.hashPassword("test"));
		assertTrue(this.jm.RegisterAccount(this.account));
	}

	@Test
	public void loginAccount() throws NoSuchAlgorithmException {
		this.account.setUsername("Jason");
		this.account.setPassword("test");
		this.account.setPasswordHash(this.account.hashPassword("test"));
		this.jm.RegisterAccount(this.account);
		assertTrue(this.jm.LoginAccount(this.account));
	}


	@After
	public void Clean() {
		this.account = null;
		this.statement = this.jm.OpenDatabaseConnection();
		this.jm = null;
		try {
			this.sqlQuery = "Drop table Account_Own_Characters";
			this.statement.executeUpdate(this.sqlQuery);
		} catch (SQLException e) {
			if (e.getSQLState().equals("42Y55") || e.getSQLState().equals("42X05")) {

			} else {
				e.printStackTrace();
			}
		}
		try {
			this.sqlQuery = "Drop table Accounts";
			this.statement.executeUpdate(this.sqlQuery);
		} catch (SQLException e) {
			if (e.getSQLState().equals("42Y55") || e.getSQLState().equals("42X05")) {

			} else {
				e.printStackTrace();
			}
		}
		try {
			this.sqlQuery = "Drop table Characters";
			this.statement.executeUpdate(this.sqlQuery);
		} catch (SQLException e) {
			if (e.getSQLState().equals("42Y55") || e.getSQLState().equals("42X05")) {

			} else {
				e.printStackTrace();
			}
		}
		this.statement = null;

	}
}
