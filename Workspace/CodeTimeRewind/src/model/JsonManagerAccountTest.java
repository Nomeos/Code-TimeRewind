package model;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.security.NoSuchAlgorithmException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class JsonManagerAccountTest {
	private String saveDirectoryPath = System.getProperty("user.dir") + "\\save\\accounts.json";
	private JsonManagerAccount jm;
	private Account account, account2;

	File file;

	@Before
	public void Init() {
		this.jm = new JsonManagerAccount();
		this.account = new Account();
		this.account2 = new Account();
		this.file = new File(saveDirectoryPath);
	}

	@Test
	public void HashPasswordTest() throws NoSuchAlgorithmException {
		account.hashPassword("test");
	}

	@Test
	public void GetAllDataFromJsonTest() throws NoSuchAlgorithmException {
		this.file.deleteOnExit();
		this.account.setUsername("Jason");
		this.account.setPasswordHash(this.account.hashPassword("test"));
		this.account2.setUsername("Albert");
		this.account2.setPasswordHash(this.account2.hashPassword("test2"));

		assertTrue(this.jm.RegisterAccount(this.account));
		assertTrue(this.jm.RegisterAccount(this.account2));
		this.jm.GetAllDataFromJson();
	}

	@Test
	public void RegisterOneAccount() throws NoSuchAlgorithmException {
		this.file.deleteOnExit();
		this.account.setUsername("Jason");
		this.account.setPasswordHash(this.account.hashPassword("test"));

		assertTrue(this.jm.RegisterAccount(this.account));
	}

	@Test
	public void registerTwoAccount() throws NoSuchAlgorithmException {
		this.file.deleteOnExit();
		this.account.setUsername("Jason");
		this.account.setPasswordHash(this.account.hashPassword("test"));
		this.account2.setUsername("Albert");
		this.account2.setPasswordHash(this.account2.hashPassword("test2"));

		assertTrue(this.jm.RegisterAccount(this.account));
		assertTrue(this.jm.RegisterAccount(this.account2));

	}

	@Test
	public void loginAccount() throws NoSuchAlgorithmException {
		this.file.deleteOnExit();
		this.account.setUsername("Jason");
		this.account.setPassword("test");
		this.account.setPasswordHash(this.account.hashPassword("test"));
		this.jm.RegisterAccount(this.account);
		if (!this.jm.LoginAccount(account)) {
			fail();
		}

	}

	@Test
	public void areThePasswordEqualsTest() throws NoSuchAlgorithmException {
		this.account.setUsername("Jason");
		this.account.setPasswordHash(this.account.hashPassword("test"));
		if (!this.account.areThePasswordEquals("test")) {
			fail();
		}

	}

	@After
	public void Clean() {
		this.file.delete();
		this.account = null;
		this.account2 = null;
		this.file = null;
		this.jm = null;
		this.saveDirectoryPath = null;
	}
}
