package model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Entity {
	protected String name;
	protected int level;
	protected int health;
	protected int defense;
	protected int attack;
	protected int speed;

	public Entity(String name, int level, int health, int defense, int attack, int speed) {
		this.name = name;
		this.level = level;
		this.health = health;
		this.defense = defense;
		this.attack = attack;
		this.speed = speed;

	}

}
