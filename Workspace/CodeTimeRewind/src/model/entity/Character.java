package model.entity;

import java.util.List;

import org.newdawn.slick.Animation;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import model.databaseManager.DatabaseCharacterManager;

@Getter
@Setter
@NoArgsConstructor
public class Character extends Entity {

	private String description;
	private int experience;
	private int maxExperience;
	private int firstLevelMaxExperience;
	
	private List<Animation> animations;

	public Character(String name, int level, int health, int defense, int attack, int speed, int experience,
			String Description) {
		super(name, level, health, defense, attack, speed);

		this.experience = experience;
		this.description = Description;
		this.firstLevelMaxExperience = 150;
		calculateMaxExperience();
		this.animations = DatabaseCharacterManager.getInstance().getAllAnimations(name);
	}

	public void calculateMaxExperience() {
		for (int i = 1; i <= level; i++) {
			if (i == 1)
				maxExperience = 150;
			maxExperience *= 1.5;
		}
	}

}
