package model.entity;

import org.newdawn.slick.Image;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Enemy extends Entity {
	private Image enemy;

	public Enemy(String name, int level, int health, int defense, int attack, int speed, int x, int y, int width,
			int height, Image enemy) {
		super(name, level, health, defense, attack, speed, x, y, width, height);
		this.enemy = enemy;
	}

	public void render(int x, int y) {
		this.x = x;
		this.y = y;
		this.enemy.draw(x, y);
	}
}
