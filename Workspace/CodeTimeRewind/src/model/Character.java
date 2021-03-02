package model;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Animation;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class Character {
	public Character(String characterName, int characterLevel, int characterHealth, int characterDefense,
			int characterAttack, int characterSpeed, int characterExperience) {
		this.characterName = characterName;
		this.characterLevel = characterLevel;
		this.characterHealth = characterHealth;
		this.characterDefense = characterDefense;
		this.characterAttack = characterAttack;
		this.characterSpeed = characterSpeed;
		this.currentExp = characterExperience;
		for(int i=0;i<=39;i++) {
			this.maxExperienceByLevel.add(i, firstLevelMaxExperience);
			this.firstLevelMaxExperience *=2;
		}
		this.dcm = new DatabaseCharacterManager();
		this.animations = dcm.getAllAnimations(characterName);
	}

	private String characterName;
	private int characterLevel,characterHealth,characterDefense,characterAttack,characterSpeed,currentExp, firstLevelMaxExperience = 150;
	private DatabaseCharacterManager dcm;
	private List<Animation> animations;
	private List<Integer> maxExperienceByLevel = new ArrayList<>();
	
	public int getMaxExperienceByLevel(int level) {
		return maxExperienceByLevel.get(level-1);
	}
	
	
}
