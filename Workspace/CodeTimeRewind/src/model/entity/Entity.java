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
	protected int maxHealth;
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
		this.maxHealth = health;
	}
	public boolean isHovering(float x, float y) {
		return this.x < x && (this.x + this.width) > x && this.y < y
				&& (this.y + this.height) > y;
	}
	
	public void setHealth(int health) {
		if(health <= 0) {
			this.health = 0;
		}else {
			this.health = health;
		}
		
		
	}
	

}
