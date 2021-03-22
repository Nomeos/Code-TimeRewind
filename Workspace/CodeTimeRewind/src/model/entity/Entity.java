package model.entity;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
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
import model.spell.SingleTargetSpell;
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
	protected int maxDefense;
	protected int maxAttack;
	protected int maxSpeed;
	protected List<Spell> spells;
	protected List<DebuffEffect> activeDebuffs;
	protected List<BuffEffect> activeBuffs;
	protected int x;
	protected int y;
	protected int width;
	protected int height;
	protected int maxHealth;
	protected LifeBars lifeBars;
	protected float alpha = 1;
	protected PathAnimation animation;
	protected boolean done = false;
	protected Image image;
	protected int currentSpell;
	private List<Entity> listOfEntity;
	protected boolean isFadingOut;
	protected boolean isInBattle = true;

	public Entity(String name, int level, int health, int defense, int attack, int speed, int x, int y, int width,
			int height, Image image) throws SlickException {
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
		this.maxDefense = defense;
		this.maxAttack = attack;
		this.maxSpeed = speed;
		this.image = image;
		this.activeBuffs = new ArrayList<BuffEffect>();
		this.activeDebuffs = new ArrayList<DebuffEffect>();

	}

	public Entity(List<Spell> spells) {
		this.spells = spells;
	}

	public Entity(String name, int level, int health, int defense, int attack, int speed, int x, int y, int width,
			int height) {
		super();
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
		this.activeBuffs = new ArrayList<BuffEffect>();
		this.activeDebuffs = new ArrayList<DebuffEffect>();
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

	public void dealDamage(Entity c, Entity e) {
		activateCurrentSpell(c, e);
		effectThatApplyBeforeDamage(c, e);
		int result = c.getAttack();
		float result1 = 100 / (100 + (float) e.getDefense());
		int result2 = Math.round(result * result1);
		e.setHealth(e.getHealth() - result2);
		activateCurrentSpell(c, e, result2);
		effectThatApplyAfterDamage(c, e);
		minusEffectCooldown(c);
		System.out.println(result2);
	}

	private void effectThatApplyBeforeDamage(Entity c, Entity e) {
		if (c.getActiveDebuffs() != null) {
			for (DebuffEffect d : c.getActiveDebuffs()) {
				if (d.isActivatedBeginning()) {
					d.applyEffect(c);
				}
			}
		}
		if (c.getActiveBuffs() != null) {
			for (BuffEffect b : c.getActiveBuffs()) {
				if (b.isActivatedBeginning()) {
					b.applyEffect(c);
				}
			}
		}
	}

	private void effectThatApplyAfterDamage(Entity c, Entity e) {
		if (this.activeDebuffs != null) {
			for (DebuffEffect d : this.activeDebuffs) {
				if (!d.isAppliedBeginning()) {

				}
			}
		}
		if (this.activeBuffs != null) {

			for (BuffEffect b : this.activeBuffs) {
				if (!b.isAppliedBeginning()) {

				}
			}
		}
	}

	private void minusEffectCooldown(Entity currentEntity) {
		if (currentEntity.getActiveDebuffs() != null) {
			for (DebuffEffect d : currentEntity.getActiveDebuffs()) {
				d.setNumberTurnEffectActive(d.getNumberTurnEffectActive() - 1);
				if (d.getNumberTurnEffectActive() <= 0) {
					activeDebuffs.remove(d);
				}
			}
		}
		if (currentEntity.getActiveBuffs() != null) {

			for (BuffEffect b : currentEntity.getActiveBuffs()) {
				if (!b.isAppliedBeginning()) {
					b.setNumberTurnEffectActive(b.getNumberTurnEffectActive() - 1);
					if (b.getNumberTurnEffectActive() <= 0) {
						activeBuffs.remove(b);
					}
				}
			}
		}
	}

	private void activateCurrentSpell(Entity c, Entity e) {
		int i = 1;
		for (Spell s : this.spells) {
			if (i == this.currentSpell) {
				s.startDebutActiveEffects(c, e);
			}
			i++;
		}

	}

	public boolean isSingleAttack() {
		int i = 1;
		boolean bool = false;
		for (Spell s : this.spells) {
			if (i == this.currentSpell) {
				if (s instanceof SingleTargetSpell) {
					bool = true;
				} else {
					bool = false;
				}
			}
			i++;
		}
		return bool;
	}

	private void activateCurrentSpell(Entity c, Entity e, int damageInflicated) {
		int i = 1;
		for (Spell s : this.spells) {
			if (i == this.currentSpell) {
				s.startEndPassiveEffects(c, e, damageInflicated);
			}
			i++;
		}

	}

	public void render(int x, int y, Graphics g) {
		if(isInBattle) {
			this.x = x;
			this.y = y;
			this.image.draw(x, y);
			this.lifeBars.draw(x, y - 20);
			g.setColor(Color.white);
			g.drawString("Lv. " + this.level, x, y - 40);
			int i = 60;
			if (this.activeDebuffs != null) {
				for (DebuffEffect d : this.activeDebuffs) {
					g.drawString(d.getDisplayEffect() + " " + d.getNumberTurnEffectActive() + "T", x, y - i);
					i += 20;
				}
			}
			if (this.activeBuffs != null) {
				g.setColor(Color.blue);
				for (BuffEffect b : this.activeBuffs) {
					g.drawString(b.getDisplayEffect() + " " + b.getNumberTurnEffectActive() + "T", x, y - i);
					i += 20;
				}
			}
		}else {
			this.x = x;
			this.y = y;
			this.image.draw(x, y);
			this.lifeBars.draw(x + this.width, y + (this.height/2) + 20);
			g.drawString("Lv. " + this.level, x + this.width, y + (this.height/2));
			
		}
		

	}

	public void addAnimationListener(AnimationListener assignDamage, AnimationListener endAttack) {
		this.animation.addListener(1000, assignDamage);
		this.animation.addListener(2000, endAttack);
	}

	public void fadeOut() {
		this.alpha = this.image.getAlpha() - 0.01f;
		this.image.setAlpha(alpha);
		this.lifeBars.setAlpha(alpha);
		this.activeBuffs.clear();
		this.activeDebuffs.clear();

	}

	@Override
	public int compareTo(Object o) {
		Entity e = (Entity) o;
		return this.speed - e.speed;
	}

}
