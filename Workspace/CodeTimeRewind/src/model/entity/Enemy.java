package model.entity;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import model.image.LifeBars;

@Getter
@Setter
@NoArgsConstructor
public class Enemy extends Entity {
	
	private Image enemy;
	
	public Enemy(String name, int level, int health, int defense, int attack, int speed, int x, int y, int width,
			int height, Image enemy) throws SlickException {
		super(name, level, health, defense, attack, speed, x, y, width, height,enemy);
		this.enemy = enemy;
		this.maxHealth = health;
		this.lifeBars = new LifeBars();

	}
}
