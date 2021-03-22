package model.spell;

import java.util.List;

import model.effect.activeEffect.buff.BuffEffect;
import model.effect.activeEffect.debuff.DebuffEffect;
import model.effect.passiveEffect.PassiveEffect;

public class SingleTargetSpell extends Spell {

	public SingleTargetSpell(List<DebuffEffect> debuffs, List<PassiveEffect> passives, List<BuffEffect> buffs, int cooldown) {
		super(debuffs, passives, buffs, cooldown);
		// TODO Auto-generated constructor stub
	}

}