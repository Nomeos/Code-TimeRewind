package model.spell;

import java.util.List;
import java.util.Random;

import org.newdawn.slick.Image;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import model.effect.activeEffect.buff.BuffEffect;
import model.effect.activeEffect.debuff.DebuffEffect;
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

	public Spell(List<DebuffEffect> debuffs, List<PassiveEffect> passives) {
		this.debuffs = debuffs;
	}

	public Spell(List<DebuffEffect> debuffs) {
		this.debuffs = debuffs;
	}

	public void startDebutPassiveEffects(Entity c,Entity e) {
		for (DebuffEffect d : debuffs) {
			d.Start(c,e);
		}
	}

	public void startEndPassiveEffects(Entity c,Entity e) {

	}

}
