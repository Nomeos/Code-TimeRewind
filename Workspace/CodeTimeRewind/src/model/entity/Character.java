package model.entity;

import org.newdawn.slick.Image;

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
	private int maxHealth;

	private Image character;
	// private List<Animation> animations;

	public Character(String name, int level, int health, int defense, int attack, int speed, int x, int y, int width,
			int height, int experience, String Description) {
		super(name, level, health, defense, attack, speed, x, y, width, height);

		this.maxHealth = health;
		this.experience = experience;
		this.description = Description;
		this.firstLevelMaxExperience = 150;
		calculateMaxExperience();
		this.character = DatabaseCharacterManager.getInstance().getCharacterPicture(this.name);

		// this.animations =
		// DatabaseCharacterManager.getInstance().getAllAnimations(name);
	}

	public void calculateMaxExperience() {
		for (int i = 1; i <= level; i++) {
			if (i == 1)
				maxExperience = 150;
			else
				maxExperience *= 1.5;
		}
	}

	public void render(int x, int y) {
		this.x = x;
		this.y = y;
		this.character.draw(x, y);
	}

}
