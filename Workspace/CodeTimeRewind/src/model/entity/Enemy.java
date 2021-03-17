package model.entity;

import org.newdawn.slick.Image;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import model.animation.AnimationListener;
import model.animation.PathAnimation;

@Getter
@Setter
@NoArgsConstructor
public class Enemy extends Entity {
	private Image enemy;
	private int maxHealth;
	private PathAnimation animation;
	private boolean done = false;

	public Enemy(String name, int level, int health, int defense, int attack, int speed, int x, int y, int width,
			int height, Image enemy) {
		super(name, level, health, defense, attack, speed, x, y, width, height);
		this.enemy = enemy;
		this.maxHealth = health;
	}

	public void render(int x, int y) {
		this.x = x;
		this.y = y;
		this.enemy.draw(x, y);
	}

	public void addAnimationListener(AnimationListener assignDamage, AnimationListener endAttack) {
		this.animation.addListener(1000, assignDamage);
		this.animation.addListener(2000, endAttack);
	}
	
	public void startAttack() {
		if(!done) {
			this.animation.start();
			System.out.println("Starting Animation");
			this.done = true;
		}
		
	}
}
