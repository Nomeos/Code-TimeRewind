package model.spell;

import java.util.List;
import java.util.Random;

import org.newdawn.slick.Image;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import model.effect.activeEffect.buff.BuffEffect;
import model.effect.activeEffect.debuff.DebuffEffect;
import model.effect.passiveEffect.LifeAbsorption;
import model.effect.passiveEffect.PassiveEffect;
import model.livingEntity.LivingEntity;

@Getter
@Setter
@NoArgsConstructor
/**
 * This class contains every spells that the entities use
 * 
 * @author Mathieu Rabot
 *
 */
public class Spell {

	protected List<BuffEffect> buffs;
	protected List<DebuffEffect> debuffs;
	protected List<PassiveEffect> passives;
	protected int cooldown;
	protected Image image;
	protected Random rnd;

	/**
	 * This is the constructor of this class
	 * 
	 * @param debuffs  This is the list of debuff that the spell has
	 * @param passives This is the list of passives that the spell has
	 * @param buffs    This is the list of buffs that the spell has
	 * @param cooldown This is the cooldown of this spell
	 */
	public Spell(List<DebuffEffect> debuffs, List<PassiveEffect> passives, List<BuffEffect> buffs, int cooldown) {
		this.debuffs = debuffs;
		this.buffs = buffs;
		this.passives = passives;
		this.cooldown = cooldown;
	}

	/**
	 * This method starts all effects at the beginning of the turn
	 * 
	 * @param c Current entity that is attacking
	 * @param e Current entity that is defending
	 */
	public void startDebutActiveEffects(LivingEntity c, LivingEntity e) {
		if (debuffs != null) {
			for (DebuffEffect d : debuffs) {
				if (d.isAppliedBeginning()) {
					if (d.isDebuffApplied()) {
						if (e.getActiveDebuffs().contains(d)) {
							e.getActiveDebuffs().remove(d);
							e.getActiveDebuffs().add(d);
						} else {
							e.getActiveDebuffs().add(d);
						}

					}
				}

			}
		}
		if (buffs != null) {
			for (BuffEffect b : buffs) {
				if (b.isAppliedBeginning()) {
					if (c.getActiveBuffs().contains(b)) {
						c.getActiveBuffs().remove(b);
						c.getActiveBuffs().add(b);
					} else {
						c.getActiveBuffs().add(b);
					}
				}
			}
		}

	}

	/**
	 * This method starts all effects at the end of the turn
	 * 
	 * @param c                Current entity that is attacking
	 * @param e                Current entity that is defending
	 * @param damageInflicated That's the damage the entity dealt
	 */
	public void startEndPassiveEffects(LivingEntity c, LivingEntity e, int damageInflicated) {
		if (debuffs != null) {
			for (DebuffEffect d : debuffs) {
				if (!d.isAppliedBeginning()) {
					if (d.isDebuffApplied()) {
						if (e.getActiveDebuffs().contains(d)) {
							e.getActiveDebuffs().remove(d);
							e.getActiveDebuffs().add(d);
						} else {
							e.getActiveDebuffs().add(d);
						}

					}
				}

			}
		}
		if (buffs != null) {
			for (BuffEffect b : buffs) {
				if (!b.isAppliedBeginning()) {
					c.getActiveBuffs().add(b);
				}
			}
		}
		for (PassiveEffect p : this.passives) {
			if (p instanceof LifeAbsorption) {
				int result = ((LifeAbsorption) p).start(damageInflicated);
				if (c.getDefaultHealth() < c.getHealth() + result) {
					c.setHealth(c.getDefaultHealth());
				} else {
					c.setHealth(c.getHealth() + result);
				}

			}

		}
	}

}
