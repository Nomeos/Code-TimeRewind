package model.livingEntity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

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
import model.rarity.Rarity;
import model.spell.SingleTargetSpell;
import model.spell.Spell;

@NoArgsConstructor
@Getter
@Setter
@javax.persistence.Entity
@Table(name = "LivingEntities")
public class LivingEntity implements Comparable<Object> {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "livingEntity_Id")
	protected int id;

	@Column(name = "name")
	protected String name;

	@Column(name = "level")
	protected int level;

	@Column(name = "health")
	protected int health;

	@Column(name = "defense")
	protected int defense;

	@Column(name = "attack")
	protected int attack;

	@Column(name = "speed")
	protected int speed;

	@Column(name = "description")
	protected String description;

	@ManyToOne
	@JoinColumn(name = "rarity_Id")
	private Rarity rarity;

	@JoinColumn(name = "width")
	protected int width;

	@JoinColumn(name = "height")
	protected int height;

	@Transient
	protected int defaultHealth;
	@Transient
	protected int defaultDefense;
	@Transient
	protected int defaultAttack;
	@Transient
	protected int defaultSpeed;

	@Transient
	protected List<Spell> spells;
	@Transient
	protected List<DebuffEffect> activeDebuffs;
	@Transient
	protected List<BuffEffect> activeBuffs;
	@Transient
	protected LifeBars lifeBars;
	@Transient
	protected float alpha = 1;
	@Transient
	protected PathAnimation animation;
	@Transient
	protected boolean done = false;
	@Transient
	protected Image image;
	@Transient
	protected int currentSpell;
	@Transient
	private List<LivingEntity> listOfEntity;
	@Transient
	protected boolean isFadingOut;
	@Transient
	protected boolean isInBattle = true;
	@Transient
	private int Experience;
	@Transient
	private int maxExperience;
	@Transient
	private int firstLevelMaxExperience;
	@Transient
	private int xpObtained;
	@Transient
	private int defaultXpObtained;
	@Transient
	private boolean isAnEnemy = false;

	@Transient
	protected int x;
	@Transient
	protected int y;

	public LivingEntity(String name, int level, int health, int defense, int attack, int speed, String description,
			Rarity rarity, int width, int height) {

		this.name = name;
		this.level = level;
		this.health = health;
		this.defense = defense;
		this.attack = attack;
		this.speed = speed;
		this.description = description;
		this.rarity = rarity;
		this.width = width;
		this.height = height;

	}

	public boolean isHovering(float x, float y) {
		return this.x < x && (this.x + this.width) > x && this.y < y && (this.y + this.height) > y;
	}

	public void init() {
		this.image = this.getCharacterSprite(name);
		this.defaultHealth = health;
		this.animation = new PathAnimation();
		try {
			this.lifeBars = new LifeBars();
		} catch (SlickException e) {
			e.printStackTrace();
		}
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

	public void dealDamage(LivingEntity c, LivingEntity e) {
		// activateCurrentSpell(c, e);
		// effectThatApplyBeforeDamage(c, e);
		int result = c.getAttack();
		float result1 = 100 / (100 + (float) e.getDefense());
		int result2 = Math.round(result * result1);
		e.setHealth(e.getHealth() - result2);
		// activateCurrentSpell(c, e, result2);
		// effectThatApplyAfterDamage(c, e);
		// minusEffectCooldown(c);
		System.out.println(result2);
	}

	private void effectThatApplyBeforeDamage(LivingEntity c, LivingEntity e) {
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

	private void effectThatApplyAfterDamage(LivingEntity c, LivingEntity e) {
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

	private void minusEffectCooldown(LivingEntity currentEntity) {
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

	private void activateCurrentSpell(LivingEntity c, LivingEntity e) {
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

	private void activateCurrentSpell(LivingEntity c, LivingEntity e, int damageInflicated) {
		int i = 1;
		for (Spell s : this.spells) {
			if (i == this.currentSpell) {
				s.startEndPassiveEffects(c, e, damageInflicated);
			}
			i++;
		}

	}

	public Image getCharacterSprite(String nameOfTheCharacter) {
		Image image;
		try {
			image = new Image("/res/entity/" + nameOfTheCharacter + "/" + nameOfTheCharacter + ".png");
		} catch (SlickException e) {
			return null;
		}
		return image;

	}

	public void render(int x, int y, Graphics g) {
		if (isInBattle) {
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
		} else {
			this.x = x;
			this.y = y;
			this.image.draw(x, y);
			this.lifeBars.draw(x + this.width, y + (this.height / 2) + 20);
			g.drawString("Lv. " + this.level, x + this.width, y + (this.height / 2));

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
		// this.activeBuffs.clear();
		// this.activeDebuffs.clear();

	}

	public void calculateMaxExperience() {
		for (int i = 1; i <= level; i++) {
			if (i == 1)
				maxExperience = 150;
			else
				maxExperience *= 1.5;
		}
	}

	public void calculateExperienceEarned(int levelOfTheEnemy) {

		for (int i = 1; i <= levelOfTheEnemy; i++) {
			if (xpObtained == 0) {
				xpObtained = 10;
			}
			xpObtained *= 1.5;
		}
	}

	public void allocateEarnedExperience() {

		if (Experience + xpObtained >= this.maxExperience) {
			this.level += 1;
			this.Experience = (Experience + xpObtained) - maxExperience;
			calculateMaxExperience();
		} else {
			this.Experience += xpObtained;
		}

	}

	@Override
	public int compareTo(Object o) {
		LivingEntity e = (LivingEntity) o;
		return this.speed - e.speed;
	}

}
