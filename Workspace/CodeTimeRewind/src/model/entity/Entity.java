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
	protected int x;
	protected int y;
	protected int width;
	protected int height;

	public Entity(String name, int level, int health, int defense, int attack, int speed, int x, int y, int width, int height) {
		this.name = name;
		this.level = level;
		this.health = health;
		this.defense = defense;
		this.attack = attack;
		this.speed = speed;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	

}
