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
	private int experience;
	private int maxExperience;
	private int firstLevelMaxExperience;

	public Character(String name, int level, int health, int defense, int attack, int speed, int x, int y, int width,
			int height, int experience, String Description) throws SlickException {
		super(name, level, health, defense, attack, speed, x, y, width, height);

		this.maxHealth = health;
		this.experience = experience;
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

	private void takePlayerSpells() {
		this.setSpells(DatabaseCharacterManager.getInstance().takeAllCharacterSpells(this.name)); 
	}

}
