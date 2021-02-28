package model;

import java.util.List;

import org.newdawn.slick.Animation;

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
		this.dcm = new DatabaseCharacterManager();
		this.animations = dcm.getAllAnimations(characterName);
		
	}

	private String characterName;
	private int characterLevel,characterHealth,characterDefense,characterAttack,characterSpeed;
	private DatabaseCharacterManager dcm;
	private List<Animation> animations;
	
	
	
}
