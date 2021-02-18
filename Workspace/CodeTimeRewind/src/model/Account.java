package model;

import java.security.NoSuchAlgorithmException;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.google.gson.annotations.Expose;

public class Account {
	private int account_Level;
	private String username, passwordHash;
	private transient String  password = "";
	
	


	public Account() {

	}

	public Account(String username, String password) throws NoSuchAlgorithmException {
		this.username = username;
		
		this.passwordHash = hashPassword(password);

	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getAccount_Level() {
		return account_Level;
	}

	public void setAccount_Level(int account_Level) {
		this.account_Level = account_Level;
	}

	public String hashPassword(String password) throws NoSuchAlgorithmException {
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

		return passwordEncoder.encode(password);
	}
	public boolean areThePasswordEquals(String password) {
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		return passwordEncoder.matches(password, this.passwordHash);
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
