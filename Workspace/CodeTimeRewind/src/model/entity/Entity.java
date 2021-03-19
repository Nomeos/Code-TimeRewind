package model.entity;

import java.util.List;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import model.animation.AnimationListener;
import model.animation.PathAnimation;
import model.effect.activeEffect.buff.BuffEffect;
import model.effect.activeEffect.debuff.DebuffEffect;
import model.image.LifeBars;
import model.spell.Spell;

@NoArgsConstructor
@Getter
@Setter
public class Entity implements Comparable<Object> {
	protected String name;
	protected int level;
	protected int health;
	protected int defense;
	protected int attack;
	protected int speed;
	protected List<Spell> spells;
	protected List<DebuffEffect> activeDebuffs;
	protected List<BuffEffect> activeBuffs;
	protected int x;
	protected int y;
	protected int width;
	protected int height;
	protected int maxHealth;
	protected LifeBars lifeBars;
	protected float alpha;
	protected PathAnimation animation;
	protected boolean done = false;
	protected Image image;

	public Entity(String name, int level, int health, int defense, int attack, int speed, int x, int y, int width,
			int height) throws SlickException {
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
		return this.x < x && (this.x + this.width) > x && this.y < y && (this.y + this.height) > y;
	}

	public void setHealth(int health) {
		if (health <= 0) {
			this.health = 0;
		} else {
			this.health = health;
		}
	}
	public void startAttack() {
		if (!done) {
			this.animation.start();
			done = true;
		}
	}
	
	public void render(int x, int y) {
		this.x = x;
		this.y = y;
		this.image.draw(x, y);
		this.lifeBars.draw(x, y - 20);
	}

	public void addAnimationListener(AnimationListener assignDamage, AnimationListener endAttack) {
		this.animation.addListener(1000, assignDamage);
		this.animation.addListener(2000, endAttack);
	}

	public void fadeOut() {
		this.alpha = this.image.getAlpha() - 0.01f;
		this.image.setAlpha(alpha);
		this.lifeBars.setAlpha(alpha);

	}

	@Override
	public int compareTo(Object o) {
		Entity e = (Entity) o;
		return this.speed - e.speed;
	}
	

}
