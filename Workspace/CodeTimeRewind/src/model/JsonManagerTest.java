package model;

import java.security.NoSuchAlgorithmException;

import org.junit.Before;
import org.junit.Test;

public class JsonManagerTest {
	private String expected, actual;
	private JsonManager jm;
	private Account account;

	@Before
	public void init() {
		this.expected = "";
		this.jm = new JsonManager();
		this.account = new Account();
	}

	@Test
	public void hashPasswordTest() throws NoSuchAlgorithmException {
		this.expected = "$2a$10$dN913P8ghPUy6v7a4ac4i.FZNmAGAqBEIoKcmWEVUCe.iKEhJukke";
		this.actual = account.hashPassword("test");

	}

	@Test
	public void objectToJsonTest() throws NoSuchAlgorithmException {
		this.account.setUsername("Jason");
		this.account.setPasswordHash(this.account.hashPassword("test"));
		System.out.println("Working Directory = " + System.getProperty("user.dir"));
		jm.WriteOnJson(this.account);
	}

}
