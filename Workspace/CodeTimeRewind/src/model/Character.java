package model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class Character {
	public Character(String characterName, int characterLevel, int characterHealth, int characterDefense,
			int characterAttack, int characterSpeed) {
		this.characterName = characterName;
		this.characterLevel = characterLevel;
		this.characterHealth = characterHealth;
		this.characterDefense = characterDefense;
		this.characterAttack = characterAttack;
		this.characterSpeed = characterSpeed;
	}

	private String characterName;
	private int characterLevel,characterHealth,characterDefense,characterAttack,characterSpeed;
	

}
