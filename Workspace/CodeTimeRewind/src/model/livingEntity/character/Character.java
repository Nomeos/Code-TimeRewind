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
import model.rarity.Rarity;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Character extends LivingEntity {
	public Character(String name, int level, int health, int defense, int attack, int speed, String description,
			Rarity rarity, int width, int height) {
		super(name, level, health, defense, attack, speed, description, rarity, width, height);

	}
}
