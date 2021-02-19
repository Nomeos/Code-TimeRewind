package model;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
public class Account {
	
	private int account_Level;
	private String username, passwordHash;
	private List<Character> listOfOwnedCharacter = new ArrayList<Character>();
	private transient String password = "";

	public Account(String username, String password) throws NoSuchAlgorithmException {
		this.username = username;
		this.passwordHash = hashPassword(password);
	}


	public String hashPassword(String password) throws NoSuchAlgorithmException {
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

		return passwordEncoder.encode(password);
	}

	public boolean areThePasswordEquals(String password) {
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		return passwordEncoder.matches(password, this.passwordHash);
	}
}
