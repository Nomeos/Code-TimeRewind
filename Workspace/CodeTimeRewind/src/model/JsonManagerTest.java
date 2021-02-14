package model;

import java.io.File;
import java.security.NoSuchAlgorithmException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class JsonManagerTest {
	private String expected, actual;
	private String saveDirectoryPath = System.getProperty("user.dir") + "\\save\\accounts.json";
	private JsonManager jm;
	private Account account, account2;

	File file;

	@Before
	public void Init() {
		this.expected = "";
		this.jm = new JsonManager();
		this.account = new Account();
		this.account2 = new Account();
		this.file = this.jm.GetFile();
	}

	@Test
	public void HashPasswordTest() throws NoSuchAlgorithmException {
		this.expected = "$2a$10$dN913P8ghPUy6v7a4ac4i.FZNmAGAqBEIoKcmWEVUCe.iKEhJukke";
		this.actual = account.hashPassword("test");

	}

	@Test
	public void GetAllDataFromJsonTest() throws NoSuchAlgorithmException {
		this.account.setUsername("Jason");
		this.account.setPasswordHash(this.account.hashPassword("test"));
		this.account2.setUsername("Albert");
		this.account2.setPasswordHash(this.account2.hashPassword("test2"));

		this.jm.RegisterAccount(this.account);
		this.jm.RegisterAccount(this.account2);
		this.jm.GetAllDataFromJson();
	}

	@Test
	public void RegisterOneAccount() throws NoSuchAlgorithmException {
		this.account.setUsername("Jason");
		this.account.setPasswordHash(this.account.hashPassword("test"));

		this.jm.RegisterAccount(this.account);
	}

	@Test
	public void registerTwoAccount() throws NoSuchAlgorithmException {
		this.account.setUsername("Jason");
		this.account.setPasswordHash(this.account.hashPassword("test"));
		this.account2.setUsername("Albert");
		this.account2.setPasswordHash(this.account2.hashPassword("test2"));

		this.jm.RegisterAccount(this.account);
		this.jm.RegisterAccount(this.account2);
	}
	@Test
	public void loginAccount() throws NoSuchAlgorithmException {
		this.account.setUsername("Jason");
		this.account.setPasswordHash(this.account.hashPassword("test"));
		this.jm.LoginAccount(account);
	}
	@Test
	public void areThePasswordEqualsTest() throws NoSuchAlgorithmException {
		this.account.setUsername("Jason");
		this.account.setPasswordHash(this.account.hashPassword("test"));
		this.account.areThePasswordEquals("test");
		
	}

	@After
	public void Clean() {
		this.account = null;
		this.account2 = null;
		this.actual = null;
		this.expected = null;
		this.file = null;
		this.jm = null;
		this.saveDirectoryPath = null;
	}
}
