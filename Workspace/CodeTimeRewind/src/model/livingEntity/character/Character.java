package model.livingEntity.character;

import javax.persistence.Entity;
import javax.persistence.Transient;

import org.newdawn.slick.SlickException;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import model.databaseManager.DatabaseCharacterManager;
import model.image.LifeBars;
import model.livingEntity.LivingEntity;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Character extends LivingEntity {

	private String description;
	@Transient
	private int oldExperience;
	@Transient
	private int maxExperience;
	@Transient
	private int firstLevelMaxExperience;
	@Transient
	private int xpObtained;
	@Transient
	private int defaultXpObtained;

	public Character(String name, int level, int health, int defense, int attack, int speed, int width, int height,
			int experience, String Description) throws SlickException {
		super(name, level, health, defense, attack, speed, width, height);

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

	public Character(String name, int level, int health, int defense, int attack, int speed, String description) {
		super(name, level, health, defense, attack, speed, description);
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
