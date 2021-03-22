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
import model.entity.Entity;

@Getter
@Setter
@NoArgsConstructor
public class Spell {

	protected List<BuffEffect> buffs;
	protected List<DebuffEffect> debuffs;
	protected List<PassiveEffect> passives;
	protected int cooldown;
	protected Image image;
	protected Random rnd;

	public Spell(List<DebuffEffect> debuffs, List<PassiveEffect> passives, List<BuffEffect> buffs, int cooldown) {
		this.debuffs = debuffs;
		this.buffs = buffs;
		this.passives = passives;
		this.cooldown = cooldown;
	}

	public void startDebutActiveEffects(Entity c, Entity e) {
		if (debuffs != null) {
			for (DebuffEffect d : debuffs) {
				if (d.isAppliedBeginning()) {
					if (d.isDebuffApplied()) {
						if(e.getActiveDebuffs().contains(d)) {
							e.getActiveDebuffs().remove(d);
							e.getActiveDebuffs().add(d);
						}else{
							e.getActiveDebuffs().add(d);
						}
					
					}
				}

			}
		}
		if (buffs != null) {
			for (BuffEffect b : buffs) {
				if (b.isAppliedBeginning()) {
					if(c.getActiveBuffs().contains(b)) {
						c.getActiveBuffs().remove(b);
						c.getActiveBuffs().add(b);
					}else{
						c.getActiveBuffs().add(b);
					}
				}
			}
		}

	}

	public void startEndPassiveEffects(Entity c, Entity e, int damageInflicated) {
		if (debuffs != null) {
			for (DebuffEffect d : debuffs) {
				if (!d.isAppliedBeginning()) {
					if (d.isDebuffApplied()) {
						if(e.getActiveDebuffs().contains(d)) {
							e.getActiveDebuffs().remove(d);
							e.getActiveDebuffs().add(d);
						}else{
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
			if(p instanceof LifeAbsorption) {
				int result = ((LifeAbsorption) p).start(damageInflicated);
				if(c.getMaxHealth() < c.getHealth() + result) {
					c.setHealth(c.getMaxHealth());
				}else {
					c.setHealth(c.getHealth() + result);
				}
				
			}
			
		}
	}

}
