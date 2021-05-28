package model.livingEntity.character;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import model.livingEntity.LivingEntity;
import model.rarity.Rarity;

@Getter
@Setter
@NoArgsConstructor
@Entity
/**
 * This is a subclass of LivingEntity that contains every character
 * 
 * @author Mathieu Rabot
 *
 */
public class Character extends LivingEntity {

	/**
	 * This is the constructor of this class
	 * 
	 * @param name        This is the name of the character
	 * @param level       This is the level of the character
	 * @param health      This is the health of the character
	 * @param defense     This is the defense of the character
	 * @param attack      This is the attack of the character
	 * @param speed       This is the speed of the character
	 * @param description This is the description of the character
	 * @param rarity      This is the rarity of the character
	 * @param width       This is the width of the character sprite
	 * @param height      This is the height of the character sprite
	 */
	public Character(String name, int level, int health, int defense, int attack, int speed, String description,
			Rarity rarity, int width, int height) {
		super(name, level, health, defense, attack, speed, description, rarity, width, height);

	}
}
