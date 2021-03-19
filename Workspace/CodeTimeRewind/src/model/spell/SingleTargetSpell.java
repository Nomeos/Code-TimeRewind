package model.spell;

import java.util.List;

import model.effect.activeEffect.debuff.DebuffEffect;
import model.effect.passiveEffect.PassiveEffect;

public class SingleTargetSpell extends Spell {

	
	public SingleTargetSpell(List<DebuffEffect> debuffs) {
		super(debuffs);
		// TODO Auto-generated constructor stub
	}
	public SingleTargetSpell(List<DebuffEffect> debuffs, List<PassiveEffect> passives) {
		super(debuffs, passives);
		// TODO Auto-generated constructor stub
	}
	
}
