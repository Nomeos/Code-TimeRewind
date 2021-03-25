package model.entity;

import org.newdawn.slick.SlickException;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import model.databaseManager.DatabaseCharacterManager;
import model.image.LifeBars;

@Getter
@Setter
@NoArgsConstructor
public class Character extends Entity {

	private String description;
	private int oldExperience;
	private int maxExperience;
	private int firstLevelMaxExperience;
	private int xpObtained;
	private int defaultXpObtained;

	public Character(String name, int level, int health, int defense, int attack, int speed, int x, int y, int width,
			int height, int experience, String Description) throws SlickException {
		super(name, level, health, defense, attack, speed, x, y, width, height);

		this.maxHealth = health;
		this.oldExperience = experience;
		this.xpObtained = 0;
		this.defaultXpObtained = 20;
		this.description = Description;
		this.firstLevelMaxExperience = 150;
		this.lifeBars = new LifeBars();
		calculateMaxExperience();
		this.image = DatabaseCharacterManager.getInstance().getCharacterPicture(this.name);
		takePlayerSpells();
	}

	public void calculateMaxExperience() {
		for (int i = 1; i <= level; i++) {
			if (i == 1)
				maxExperience = 150;
			else
				maxExperience *= 1.5;
		}
	}

	public void calculateExperienceEarned(int levelOfTheEnemy) {
		int current = xpObtained;
		for (int i = 1; i <= levelOfTheEnemy; i++) {
			xpObtained *= 1.5;
		}
	}

	public void allocateEarnedExperience() {

		if (oldExperience + xpObtained >= this.maxExperience) {
			this.level += 1;
			this.oldExperience = (oldExperience + xpObtained) - maxExperience;
			calculateMaxExperience();
		} else {
			this.oldExperience += xpObtained;
		}

	}

	private void takePlayerSpells() {
		this.setSpells(DatabaseCharacterManager.getInstance().getAllCharacterSpells(this.name));
	}

}
